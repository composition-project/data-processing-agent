package eu.ebbits.pwal.impl.driver.llrpreader.adaptor;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import eu.ebbits.pwal.impl.driver.llrpreader.LLRPReaderDriverImpl;
import eu.ebbits.pwal.impl.driver.llrpreader.org.fosstrak.llrp.adaptor.Adaptor;
import eu.ebbits.pwal.impl.driver.llrpreader.org.fosstrak.llrp.adaptor.AdaptorManagement;
import eu.ebbits.pwal.impl.driver.llrpreader.org.fosstrak.llrp.adaptor.exception.LLRPRuntimeException;
import eu.ebbits.pwal.impl.driver.llrpreader.org.fosstrak.llrp.client.LLRPExceptionHandler;
import eu.ebbits.pwal.impl.driver.llrpreader.org.fosstrak.llrp.client.MessageHandler;

import org.apache.log4j.Logger;
import org.jdom.JDOMException;
import org.llrp.ltk.exceptions.InvalidLLRPMessageException;
import org.llrp.ltk.generated.enumerations.AccessReportTriggerType;
import org.llrp.ltk.generated.enumerations.AccessSpecState;
import org.llrp.ltk.generated.enumerations.AccessSpecStopTriggerType;
import org.llrp.ltk.generated.enumerations.AirProtocols;
import org.llrp.ltk.generated.interfaces.AccessCommandOpSpec;
import org.llrp.ltk.generated.messages.ADD_ACCESSSPEC;
import org.llrp.ltk.generated.messages.DELETE_ACCESSSPEC;
import org.llrp.ltk.generated.messages.DELETE_ROSPEC;
import org.llrp.ltk.generated.messages.ENABLE_ACCESSSPEC;
import org.llrp.ltk.generated.messages.ENABLE_ROSPEC;
import org.llrp.ltk.generated.messages.RO_ACCESS_REPORT;
import org.llrp.ltk.generated.parameters.AccessCommand;
import org.llrp.ltk.generated.parameters.AccessReportSpec;
import org.llrp.ltk.generated.parameters.AccessSpec;
import org.llrp.ltk.generated.parameters.AccessSpecStopTrigger;
import org.llrp.ltk.generated.parameters.C1G2Read;
import org.llrp.ltk.generated.parameters.C1G2TagSpec;
import org.llrp.ltk.generated.parameters.C1G2TargetTag;
import org.llrp.ltk.generated.parameters.C1G2Write;
import org.llrp.ltk.types.Bit;
import org.llrp.ltk.types.BitArray_HEX;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.TwoBitField;
import org.llrp.ltk.types.UnsignedInteger;
import org.llrp.ltk.types.UnsignedShort;
import org.llrp.ltk.types.UnsignedShortArray_HEX;
import org.llrp.ltk.util.Util;
import org.osgi.service.event.Event;


/**
 * Thread used to handle adaptors, to enqueue LLRPMessages, to handle errors from the reader site and to notify about incoming LLRPMessages.
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author    ISMB
 * @version    %I%, %G%
 * @since   PWAL 0.2.0
 */
public class AdaptorManagerThread extends Thread{
    private static Logger log = Logger.getLogger(AdaptorManagerThread.class.getName());
    
    private static final int SLEEP_INTERVAL = 2000;
    private static final int READ_INTERVAL = 1000;
    
    private static final int MAX_READINGS = 10;
    
    private static final int WRITE_ACCESSSPEC_ID = 555;
    private static final int READ_ACCESSSPEC_ID = 444;
    private static final int WRITE_OPSPEC_ID = 2121;
    private static final int READ_OPSPEC_ID = 1212;
    private static final int DEFAULT_READ_CYCLE = 1000;
    
    private String readConfig;
    private String writeConfig;
    private Boolean commitChanges;
    private String adapterName;
    private String readerName;
    private String readerAddress;
    private int readerPort;
    private String rospecFile;
    private boolean running;
    private int readCycle = DEFAULT_READ_CYCLE;

    private boolean inventoryOn = true;
    
    private UnsignedShortArray_HEX readData = null;

    private LLRPReaderDriverImpl driver;
    
    private static final int MEMORY_START = 0x20;
    

    /**
     * The AdaptorManagement handles your adaptors, enqueues LLRPMessages, handles errors from the reader site and notifies you about incoming LLRPMessages.
     *
     *    There are some common pitfalls when using the AdaptorManagement:
     *
     *    you must specify the repository where the messages shall be logged to (see example)
     *    you must register an exception handler (see example)
     *    you must shutdown the AdaptorManagement through the provided shutdown method. Otherwise the reader connections don't get shutdown properly (see example)
     *
     *    Below there is some sample-code, how you can use the AdaptorManagement:
     *
     *    // create a message handler
     *    MessageHandler msgHandler = new MessageHandler();
     *
     *    // create an exception handler
     *    ExceptionHandler handler = new ExceptionHandler();
     *
     *    // run the initializer method
     *    String readConfig = Utility.findWithFullPath("/readerDefaultConfig.properties");
     *    String writeConfig = readConfig;
     *    boolean commitChanges = true;
     *    AdaptorManagement.getInstance().initialize(
     *    readConfig, storeConfig, commitChanges, handler, msgHandler);
     *
     *    // now the management should be initialized and ready to be used
     *
     *    // create an adaptor
     *    String adaptorName = "myAdaptor";
     *    AdaptorManagement.getInstance().define(adaptorName, "localhost");
     *
     *    // create a reader
     *    String readerName = "myReader";
     *    Adaptor adaptor = AdaptorManagement.getAdaptor(adaptorName);
     *    adaptor.define(readerName, "192.168.1.23", 5084, true, true);
     *
     *    //Enqueue some LLRPMessage on the adaptor
     *    AdaptorManagement.enqueueLLRPMessage(adaptorName, readerName, message);
     *
     *    // when you shutdown your application call the shutdown method
     *    AdaptorManagement.getInstance().shutdown();
     *    
     * @param readConfig            path to the file with the read configurations of the reader
     * @param writeConfig            path to the file with the write configurations of the reader
     * @param commitChanges            indicates if the changes are commited to the configuration file or not
     * @param adapterName             name of the adapater
     * @param readerName            name of the reader
     * @param readerAddress            IP address of the reader
     * @param readerPort            TCP port of the reader
     * @param rospecFile             path to the rospec file of the reader
     * @param LLReaderDriverImpl    instance of the LLRP driver
     *  
     */
    
    public AdaptorManagerThread(String readConfig, 
                                String writeConfig, 
                                Boolean commitChanges, 
                                String adapterName, 
                                String readerName, 
                                String readerAddress, 
                                int readerPort, 
                                String rospecFile, 
                                LLRPReaderDriverImpl driver){
        this.setName("AdaptorManager Thread");
        this.readConfig = readConfig;
        this.writeConfig = writeConfig;
        this.commitChanges = commitChanges;
        this.adapterName = adapterName;
        this.readerName = readerName;
        this.readerAddress = readerAddress;
        this.readerPort = readerPort;
        this.rospecFile = rospecFile;
        this.driver = driver;
    }
    

    @Override
    public void run() {
        running=true;
        boolean started = false;
        while(running) {
            try {
                if(!started){
                    started = true;
                    MessageHandler fullHandler = new FullHandler(); 
                    
                    // create an exception handler
                    LLRPExceptionHandler handler = new ExceptionHandler();

                    try {
                        AdaptorManagement.getInstance().initialize(this.readConfig, this.writeConfig, this.commitChanges, handler, fullHandler);
                    } catch (LLRPRuntimeException e1) {
                        // TODO Auto-generated catch block
                        log.error(e1.getStackTrace(),e1);
                    }
            
                    // example for a partial handler
                    MessageHandler partialHandler = new ReportHandler(this);
                    AdaptorManagement.getInstance().registerPartialHandler(partialHandler, RO_ACCESS_REPORT.class);

                    // now the management should be initialized and ready to be used
                    Adaptor adaptor = null;
                    if(AdaptorManagement.containsAdaptor(this.adapterName)){
                        AdaptorManagement.getInstance().undefine(this.adapterName);
                    }
                    AdaptorManagement.getInstance().define(this.adapterName, null);
                    adaptor = AdaptorManagement.getAdaptor(this.adapterName);
                    if(adaptor.containsReader(this.readerName)){
                        adaptor.undefine(this.readerName);
                    }
                    adaptor.define(this.readerName, this.readerAddress, this.readerPort, true, true);
                    
                    if(adaptor.getReader(this.readerName).isConnected() /*&& adaptor.getReader(readerName2).isConnected()*/){
                        // delete ROSpec
                        log.info("Deleting ROSPEC message ...");
                        DELETE_ROSPEC del = new DELETE_ROSPEC();
                        del.setROSpecID(new UnsignedInteger(1));
                        adaptor.sendLLRPMessageToAllReaders(del.encodeBinary());
                        
                        // Deleting AccessSpec
                        deleteAccessSpecs();
                        
                        // load ADD_ROSPEC message
                        log.info("Loading ADD_ROSPEC message from file ADD_ROSPEC.xml ...");
                        LLRPMessage addRospec = Util.loadXMLLLRPMessage(new File(this.rospecFile));

                        // send message to LLRP reader and wait for response
                        log.info("Sending ADD_ROSPEC message ...");
                        adaptor.sendLLRPMessageToAllReaders(addRospec.encodeBinary());

                        log.info("Enable ROSPEC ...");
                        ENABLE_ROSPEC enable = new ENABLE_ROSPEC();
                        enable.setROSpecID(new UnsignedInteger(1));
                        adaptor.sendLLRPMessageToAllReaders(enable.encodeBinary());
                    }
                }
                else{
                    Thread.sleep(readCycle);
                }
            } catch (LLRPRuntimeException e) {
                // TODO Auto-generated catch block
                log.error(e.getStackTrace(),e);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                log.error(e.getStackTrace(),e);
            } catch (NotBoundException e) {
                // TODO Auto-generated catch block
                log.error(e.getStackTrace(),e);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                log.error(e.getStackTrace(),e);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                log.error(e.getStackTrace(),e);
            } catch (JDOMException e) {
                // TODO Auto-generated catch block
                log.error(e.getStackTrace(),e);
            } catch (InvalidLLRPMessageException e) {
                // TODO Auto-generated catch block
                log.error(e.getStackTrace(),e);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                log.error(e.getStackTrace(),e);
            }
        }        
    }
    
    /**
     * Method that stops the thread
     * 
     */
    public void stopThread() {
        // when you shutdown your application call the shutdown method
        AdaptorManagement.getInstance().shutdown();
        running = false;
    }
    
    /**
     * Retrieves the last data read from a tag
     * 
     * @return <code>UnsignedShortArray_HEX</code> containing the data read
     */
    public UnsignedShortArray_HEX getReadData() {
        return readData;
    }

    /**
     * Sets the last data read from a tag
     * 
     * @param readData data read from a tag
     */
    public void setReadData(UnsignedShortArray_HEX readData) {
        this.readData = readData;
    }
    
    /**
     * Sends a message as <code>org.osgi.service.Event</code>
     * 
     * @param msg - message received from the reader
     * 
     * return     a <code>boolean</code>, true if the event has been sent, false if something goes wrong 
     * 
     */
    public boolean sendEvent(LLRPMessage msg) {
        Dictionary<String, String> properties = new Hashtable<String, String>();
        try {
            properties.put("report", msg.toXMLString());
        } catch (InvalidLLRPMessageException e) {
            log.error(e.getStackTrace(),e);
            return false;
        }
        if(inventoryOn) {
            Event reportEvent = new Event("pwal/llrpreader/readreport", properties);
            driver.getEventsDelegate().sendEvent(reportEvent);
        }
        return true;
    }
    
    /**
     * Retrieves the read cycle of the reader
     * 
     * @return read cycle in milliseconds
     */
    public int getReadCycle() {
        return readCycle;
    }
    
    /**
     * Sets the read cycle of the reader
     * 
     * @param readCycle read interval to set (milliseconds)
     */
    public void setReadCycle(int readCycle) {
        this.readCycle = readCycle;
    }
    
    /**
     * Indicates if the inventory is on or off
     * 
     * @return true if the inventory is on, false otherwise
     */
    public boolean isInventoryOn() {
        return inventoryOn;
    }
    
    /**
     * Sets the inventory state of the reader
     * 
     * @param inventoryOn true to start the inventory, false to stop it
     */
    public void setInventoryOn(boolean inventoryOn) {
        this.inventoryOn = inventoryOn;
    }
    
    /**
     * Enables the AccessSpec to write and read on TAG User Memory
     * 
     * @param accessSpecID: ID for the accessSpec
     */
    public void enableAccessSpec(int accessSpecID) {
        log.info("Enabling the AccessSpec.");
        ENABLE_ACCESSSPEC enable = new ENABLE_ACCESSSPEC();
        enable.setAccessSpecID(new UnsignedInteger(accessSpecID));
        try {
            AdaptorManagement.getAdaptor(this.adapterName).sendLLRPMessageToAllReaders(enable.encodeBinary());
        } catch (Exception e) {
            log.error("Error enabling AccessSpec.");
            log.error(e.getStackTrace(),e);
        }
    }
      
    /**
     * Deletes all the access specs
     * 
     * @param accessSpecID: ID for the accessSpec
     */
    public void deleteAccessSpecs() {
        log.info("Deleting all AccessSpecs.");
        DELETE_ACCESSSPEC del = new DELETE_ACCESSSPEC();
        // Use zero as the ROSpec ID.
        // This means delete all AccessSpecs.
        del.setAccessSpecID(new UnsignedInteger(0));
        try {
            AdaptorManagement.getAdaptor(this.adapterName).sendLLRPMessageToAllReaders(del.encodeBinary());
        } catch (Exception e) {
            log.info("Error deleting AccessSpec.");
            log.error(e.getStackTrace(),e);
        }
    }
      
      
    /**
     * Creates a OpSpec that reads from user memory
     * 
     * @param bits: Memory bank
     *          0: Reserved
     *            1: EPC
     *            2: TID
     *            3: User
     * @param base: base to start in the memory bank
     * @param nOfWords: number of words to be read 
     */
    public C1G2Read buildReadOpSpec(int [] bits, int base, int nOfWords) {
        // Create a new OpSpec.
        // This specifies what operation we want to perform on the
        // tags that match the specifications above.
        // In this case, we want to read from the tag.
        C1G2Read opSpec = new C1G2Read();
        // Set the OpSpecID to a unique number.
        opSpec.setOpSpecID(new UnsignedShort(READ_OPSPEC_ID));
        opSpec.setAccessPassword(new UnsignedInteger(0));

        // for this demo, we'll read from user memory.
        TwoBitField opMemBank = setTwoBitField(bits);
                
        opSpec.setMB(opMemBank);
        // We'll read from the base of this memory bank (0x00).
        opSpec.setWordPointer(new UnsignedShort(base));
        // Read two words.
        opSpec.setWordCount(new UnsignedShort(nOfWords));
        
/* DEMO VALUES
       // For this demo, we'll read from user memory (bank 3).
        TwoBitField opMemBank = new TwoBitField();
        // Set bits 0 and 1 (bank 3 in binary).
        opMemBank.set(0);
        opMemBank.set(1);
        opSpec.setMB(opMemBank);
        // We'll read from the base of this memory bank (0x00).
        opSpec.setWordPointer(new UnsignedShort(0x00));
        // Read two words.
        opSpec.setWordCount(new UnsignedShort(2));
*/      
        return opSpec;
    }
      
 
    /**
     * Creates a OpSpec that writes to user memory
     * 
     * @param int bits: Memory bank
     *          0: Reserved
     *            1: EPC
     *            2: TID
     *            3: User
     * @param int base: base to start in the memory bank
     * @param int[] values: values to be written 
     */    
    public C1G2Write buildWriteOpSpec(int [] bits, int base, int[] values) {
        // Create a new OpSpec.
        // This specifies what operation we want to perform on the
        // tags that match the specifications above.
        // In this case, we want to write to the tag.
        C1G2Write opSpec = new C1G2Write();
        // Set the OpSpecID to a unique number.
        opSpec.setOpSpecID(new UnsignedShort(WRITE_OPSPEC_ID));
        opSpec.setAccessPassword(new UnsignedInteger(0));
        
        // For this demo, we'll write to user memory (bank 3).
        TwoBitField opMemBank = setTwoBitField(bits);
        opSpec.setMB(opMemBank);
        // We'll write to the base of this memory bank (i.e 0x00).
        opSpec.setWordPointer(new UnsignedShort(base));
        UnsignedShortArray_HEX writeData =
        new UnsignedShortArray_HEX();
        // We'll write 8 bytes or two words.
        for(int value : values) {
            writeData.add(new UnsignedShort(value));
        }
        opSpec.setWriteData(writeData);

/* DEMO VALUES
        // For this demo, we'll write to user memory (bank 3).
        TwoBitField opMemBank = new TwoBitField();
        // Set bits 0 and 1 (bank 3 in binary).
        opMemBank.set(0);
        opMemBank.set(1);
        opSpec.setMB(opMemBank);
        // We'll write to the base of this memory bank (0x00).
        opSpec.setWordPointer(new UnsignedShort(0x00));
        UnsignedShortArray_HEX writeData =
                new UnsignedShortArray_HEX();
        // We'll write 8 bytes or two words.
        writeData.add(new UnsignedShort (0xAABB));
        writeData.add(new UnsignedShort (0xCCDD));
        opSpec.setWriteData(writeData);
*/
        return opSpec;
    }
      
    /**
     * Creates an AccessSpec.
     * It will contain our two OpSpecs (read and write). 
     *
     * @param accessSpecID - ID for the accessSpec
     * @param tagMask - The reader will take each tag EPC and bitwise AND it with this parameter
     * @param targetEPC - EPC code to be used
     * @param bits - Memory bank
     *          0: Reserved
     *            1: EPC
     *            2: TID
     *            3: User
     * @param base - base to start in the memory bank
     * @param values - values to be written (it's null if it's used to read)
     * @param nOfWords - number of words to be read (it's null if it's used to write)
     *  
     */    
    public AccessSpec buildAccessSpec(int accessSpecID,
                                    String tagMask, 
                                    String targetEPC, 
                                    int [] bits, 
                                    int base, 
                                    int[] values,
                                    int nOfWords) {
        log.info("Building the AccessSpec.");
      
        AccessSpec accessSpec = new AccessSpec();
      
        accessSpec.setAccessSpecID(new UnsignedInteger(accessSpecID));
      
        // Set ROSpec ID to zero.
        // This means that the AccessSpec will apply to all ROSpecs.
        accessSpec.setROSpecID(new UnsignedInteger(0));
        // Antenna ID of zero means all antennas.
        accessSpec.setAntennaID(new UnsignedShort(0));
        accessSpec.setProtocolID(
            new AirProtocols(AirProtocols.EPCGlobalClass1Gen2));
        // AccessSpecs must be disabled when you add them.
        accessSpec.setCurrentState(
            new AccessSpecState(AccessSpecState.Disabled));
        AccessSpecStopTrigger stopTrigger = new AccessSpecStopTrigger();
        // Stop after the operating has been performed a certain number of times.
        // That number is specified by the Operation_Count parameter.
        stopTrigger.setAccessSpecStopTrigger
            (new AccessSpecStopTriggerType(
            AccessSpecStopTriggerType.Operation_Count));
        // OperationCountValue indicate the number of times this Spec is
        // executed before it is deleted. If set to 0, this is equivalent
        // to no stop trigger defined.
        stopTrigger.setOperationCountValue(new UnsignedShort(1));
        accessSpec.setAccessSpecStopTrigger(stopTrigger);
      
        // Create a new AccessCommand.
        // We use this to specify which tags we want to operate on.
        AccessCommand accessCommand = new AccessCommand();
      
        // Create a new tag spec.
        C1G2TagSpec tagSpec = new C1G2TagSpec();
        C1G2TargetTag targetTag = new C1G2TargetTag();
        targetTag.setMatch(new Bit(1));
        // We want to check memory bank 1 (the EPC memory bank).
        TwoBitField memBank = new TwoBitField();
        // Clear bit 0 and set bit 1 (bank 1 in binary).
        memBank.clear(0);
        memBank.set(1);
        targetTag.setMB(memBank);
        // The EPC data starts at offset 0x20.
        // Start reading or writing from there.
        targetTag.setPointer(new UnsignedShort(MEMORY_START));
        // This is the mask we'll use to compare the EPC.
        // We want to match all bits of the EPC, so all mask bits are set.
        BitArray_HEX tagMaskToSet = new BitArray_HEX(tagMask);
        targetTag.setTagMask(tagMaskToSet);
        // We only only to operate on tags with this EPC.
        BitArray_HEX tagData = new BitArray_HEX(targetEPC);
        targetTag.setTagData(tagData);
      
        // Add a list of target tags to the tag spec.
        List <C1G2TargetTag> targetTagList =
            new ArrayList<C1G2TargetTag>();
        targetTagList.add(targetTag);
        tagSpec.setC1G2TargetTagList(targetTagList);
      
        // Add the tag spec to the access command.
        accessCommand.setAirProtocolTagSpec(tagSpec);
      
        // A list to hold the op specs for this access command.
        List <AccessCommandOpSpec> opSpecList =
            new ArrayList<AccessCommandOpSpec>();
      
        // Are we reading or writing to the tag?
        // Add the appropriate op spec to the op spec list.
        if (accessSpecID == WRITE_ACCESSSPEC_ID) {
            opSpecList.add(buildWriteOpSpec(bits,base,values));
        } else {
            opSpecList.add(buildReadOpSpec(bits,base, nOfWords));
        }
      
        accessCommand.setAccessCommandOpSpecList(opSpecList);
      
        // Add access command to access spec.
        accessSpec.setAccessCommand(accessCommand);
      
        // Add an AccessReportSpec.
        // We want to get notification when the operation occurs.
        // Tell the reader to sent it to us with the ROSpec.
        AccessReportSpec reportSpec = new AccessReportSpec();
        reportSpec.setAccessReportTrigger
            (new AccessReportTriggerType(
            AccessReportTriggerType.Whenever_ROReport_Is_Generated));
      
        return accessSpec;
    }
      
 
    /**
     * Adds the AccessSpec to the reader. 
     *
     * @param accessSpecID - ID for the accessSpec
     * @param tagMask - The reader will take each tag EPC and bitwise AND it with this parameter
     * @param targetEPC - EPC code to be used
     * @param bits Memory bank
     *          0: Reserved
     *            1: EPC
     *            2: TID
     *            3: User
     * @param base: base to start in the memory bank
     * @param values: values to be written (it's null if it's used to read)
     * @param nOfWords: number of words to be read (it's null if it's used to write)
     *  
     */        
    public boolean addAccessSpec(int accessSpecID,
                                String tagMask, 
                                String targetEPC, 
                                int [] bits, 
                                int base, 
                                int [] values,
                                int nOfWords) {
        AccessSpec accessSpec = buildAccessSpec(accessSpecID,tagMask,targetEPC,bits,base,values,nOfWords);
        log.info("Adding the AccessSpec.");
        try {
            ADD_ACCESSSPEC accessSpecMsg = new ADD_ACCESSSPEC();
            accessSpecMsg.setAccessSpec(accessSpec);
            AdaptorManagement.getInstance().enqueueLLRPMessage(adapterName,readerName,accessSpecMsg);            
        } catch (Exception e) {
            log.error("Error adding AccessSpec.");
            log.error(e.getStackTrace(),e);
            return false;
        }
        return true;
    }
    
    
    
    /**
     * Method used to write an RFID tag
     * 
     * @param accessSpecID - ID for the accessSpec
     * @param tagMask - The reader will take each tag EPC and bitwise AND it with this parameter
     * @param targetEpc - epc to be used
     * @param bits - Memory bank
     *          0: Reserved
     *            1: EPC
     *            2: TID
     *            3: User
     * @param base - base to start in the memory bank
     * @param values - values to be written (it's null if it's used to read)
     */
    public boolean writeTagMemory(String tagID, String tagMask, int [] bits, int base, int [] values) {
        if(addAccessSpec(WRITE_ACCESSSPEC_ID,tagMask,tagID,bits,base,values,0)) {
            try {
                Thread.sleep(SLEEP_INTERVAL);
            } catch (InterruptedException e) {
                log.warn(e.getStackTrace(),e);
            }
            enableAccessSpec(WRITE_ACCESSSPEC_ID);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method used to read an RFID tag memory
     * 
     * @param accessSpecID - ID for the accessSpec
     * @param tagMask - The reader will take each tag EPC and bitwise AND it with this parameter
     * @param bits - Memory bank
     *          0: Reserved
     *            1: EPC
     *            2: TID
     *            3: User
     * @param base - base to start in the memory bank
     * @param nOfWords - number of words to be read (it's null if it's used to write)
     *
     * @return int [] : value read
     *
     */
    public int [] readTagMemory(String tagID, String tagMask, int[] bits, int base, int nOfWords) {
        if(addAccessSpec(READ_ACCESSSPEC_ID,tagMask,tagID,bits,base,null,nOfWords)) {
            try {
                Thread.sleep(SLEEP_INTERVAL);
            } catch (InterruptedException e) {
                log.warn(e.getStackTrace(),e);
            }
            enableAccessSpec(READ_ACCESSSPEC_ID);
            UnsignedShortArray_HEX values = null;
            int i=0;
            do {
                i++;
                values = getReadData();
                try {
                    Thread.sleep(READ_INTERVAL);
                } catch (InterruptedException e) {
                    log.error(e.getStackTrace(),e);
                }
                // After ten times stops reading
            } while(values==null && i<MAX_READINGS);
            if(values==null) {
                return null;
            }
            int [] result = new int[values.size()];
            for(i=0; i<values.size();i++) {
                result[i] = values.get(i).toInteger();
            }
            setReadData(null);
            return (int [])result;
        } else {
            return null;
        }
    }

    /**
     * Tool method used to set a <code>TwoBitField</code>
     * 
     * @param bits - values to be set
     * 
     * @return    the <code>TwoBitField</code> with the values set
     */
    private TwoBitField setTwoBitField(int[] bits) {
        TwoBitField opMemBank = new TwoBitField();
        // Set bits 0 and 1 (01-> bank 3 in binary).
        if(bits[0]==1) {
            opMemBank.set(0);
        } else {
            opMemBank.clear(0);
        }
        if(bits[1]==1) {
            opMemBank.set(1);
        } else {
            opMemBank.clear(1);
        }            
        return opMemBank;
    }

}