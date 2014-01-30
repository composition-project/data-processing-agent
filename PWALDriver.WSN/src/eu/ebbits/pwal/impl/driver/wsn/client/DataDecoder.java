/** @file DataDecoder.java
 * @brief The class that decodes the received payload and extracts the data.
 * @author Hussein Khaleel, Mauricio Caceres
 */

package eu.ebbits.pwal.impl.driver.wsn.client;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.osgi.service.event.Event;

import eu.ebbits.pwal.impl.driver.wsn.WSNDriverImpl;

/**
 * Class used to decode the received packets from the WSN
 *
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author    ISMB
 * @version    %I%, %G%
 * @since    M36demo 1.0
 * 
 */
public class DataDecoder {
    //===================numeric constants=================
    private static final int N_12 = 12;
    private static final int N_20 = 20;
    
    private Logger log = Logger.getLogger(this.getClass().getName());
    
    private WSNDriverImpl driver;
    
    /**
     * Constructor of the decoder, for the WSN data
     * 
     * @param driver - <code>WSNDriverImpl</code> that uses the decoder
     */
    public DataDecoder(WSNDriverImpl driver) {
        this.driver = driver;
    }

    /** @name decode
     * @brief Method to decode the received payload by UDPReceive.
     * @param address The source IP address of the received packet.
     * @param port The source port of the received packet.
     * @param payloadLength The length of the received payload.
     * @param payloadData The array containing the received payload.
     */
    public void decode(InetAddress address, int port, int payloadLength, byte[] payloadData) throws IOException {
        int type = 0;
        String typeHex = "";
        int seqNo = 0;
        int temperature = 0;
        int accXAxis = 0;
        int accYAxis = 0;
        int accZAxis = 0;
        int samplingRate = 0;
        int command = 0;
        String commandHex = "";
        int ackData = 0;
        
        log.info("Received from: " + address.getHostName() + 
                ", from port: " + port + 
                ", payload length: " + payloadLength + " bytes");

       
        //print the raw received bytes of the payload
        log.info("Raw bytes of payload: ");
        for (int i=0;i<payloadLength;i++) {
            log.info(payloadData[i] + "  ");
        }
        
        /*
        //convert the received byte payload to short payload
        for (int i=0;i<payload_length;i++) {
            data[i] = (short) payload_data[i];
        }
        */

        //packet type identification
        type = FormatDecoder.decode8U(payloadData, 0);
        typeHex = Integer.toHexString(type);
        if(typeHex.equalsIgnoreCase("d0")) {
            log.info("Received data packet, type "+ typeHex);
            seqNo = FormatDecoder.decode16U(payloadData, 2, false);
            temperature = (int)FormatDecoder.decode32(payloadData, WSNDriverImpl.N_4);
            accXAxis = (int)FormatDecoder.decode32(payloadData, WSNDriverImpl.N_8);
            accYAxis = (int)FormatDecoder.decode32(payloadData, N_12);
            accZAxis = (int)FormatDecoder.decode32(payloadData, WSNDriverImpl.N_16);
            samplingRate = FormatDecoder.decode16U(payloadData, N_20, false);
            log.info("seq_no "+seqNo+", temperature " + temperature
                    +", acc_x_axis "+accXAxis+", acc_y_axis "+accYAxis+", acc_z_axis "+accZAxis
                    +", sampling_rate "+samplingRate);
            //get the raw bytes of the sensor node IP address
            byte[] nodeAddress = address.getAddress();
            
            //create the payload
            byte[] payload = new byte[payloadLength + nodeAddress.length];
            //filling up the payloadData
            System.arraycopy(payloadData,0,payloadData,0,payloadLength);
            //filling up the nodeAddress, starting from index: payloadLength+1
            for(int i=0;i<(nodeAddress.length);i++) {
                payload[payloadLength+i]=nodeAddress[i];
            }
            Dictionary<String, byte[]> properties = new Hashtable<String, byte[]>();
            properties.put("packet", payload);

            Event packetEvent = new Event("pwal/wsndriver/packet", properties);
            driver.getEventsDelegate().sendEvent(packetEvent);
        }
        /*
        else if(type_hex.equalsIgnoreCase("d1")) {
            System.out.println("Received buffered data packet, type "+ type_hex);
            seq_no = encode16(data[2], data[3]);
            temperature = encode32(data[4], data[5], data[6], data[7]);
            buf_len = encode32(data[8], data[9], data[10], data[11]);
            //System.out.println();
            //System.out.println("seq_no "+seq_no);
            //System.out.println("temperature "+temperature);
            //System.out.println("buf_len "+buf_len);
            array_idx = 12;//readings start from here
            for (int i=0;i<buf_len;i++) {
                accel_x_axis[i]=encode32(data[array_idx], data[array_idx+1], data[array_idx+2], data[array_idx+3]);
                array_idx = (short) (array_idx + 4);
                //System.out.println("accel_x_axis["+i+"] "+accel_x_axis[i]);
            }
            for (int i=0;i<buf_len;i++) {
                accel_y_axis[i]=encode32(data[array_idx], data[array_idx+1], data[array_idx+2], data[array_idx+3]);
                array_idx = (short) (array_idx + 4);
                //System.out.println("accel_y_axis["+i+"] "+accel_y_axis[i]);
            }
            for (int i=0;i<buf_len;i++) {
                accel_z_axis[i]=encode32(data[array_idx], data[array_idx+1], data[array_idx+2], data[array_idx+3]);
                array_idx = (short) (array_idx + 4);
                //System.out.println("accel_z_axis["+i+"] "+accel_z_axis[i]);
            }
            sampling_rate = encode16(data[array_idx], data[array_idx+1]);
            //System.out.println("sampling_rate "+sampling_rate);
            //System.out.println();
            //proxy.ReceivedBufferedDataPacket(address, seq_no, temperature, buf_len, accel_x_axis, accel_y_axis, accel_z_axis, sampling_rate);
        }
        */
        else if(typeHex.equalsIgnoreCase("a0")) {
            log.info("Received ack packet, type "+ typeHex);
            //command = encode16(data[2], data[3]);
            //ack_data = encode16(data[4], data[5]);
            command = FormatDecoder.decode16U(payloadData, WSNDriverImpl.N_2, false);
            commandHex = Integer.toHexString(command);
            ackData = FormatDecoder.decode16U(payloadData, WSNDriverImpl.N_4, false);
            //get the raw bytes of the sensor node IP address
            byte[] nodeAddress = address.getAddress();
            
            //create the payload
            byte[] payload = new byte[payloadLength + nodeAddress.length];
            //filling up the payloadData
            System.arraycopy(payloadData,0,payloadData,0,payloadLength);
            //filling up the nodeAddress, starting from index: payloadLength+1
            for(int i=0;i<(nodeAddress.length);i++) {
                payload[payloadLength+i]=nodeAddress[i];
            }
            Dictionary<String, byte[]> properties = new Hashtable<String, byte[]>();
            properties.put("packet", payload);

            Event packetEvent = new Event("pwal/wsndriver/packet", properties);
            driver.getEventsDelegate().sendEvent(packetEvent);
        }
        else {
            log.info("Unknown packet received");
        }
    }

    /** @name encode16
     * @brief Method to create a 16-bit integer from 2 received bytes.
     * @param b1 The first byte.
     * @param b2 The second byte.
     * @return The created 16-bit integer.
     */
    /*
    private static short encode16(short b1, short b2){
        short ret = 0;
        if(b2>=0){ //positive number
            if(b1<0) b1=(short) (b1+256);
            ret = (short) (b1 + b2*256);
        }
        else { //negative number
            if(b1>=0) b1=(short) (b1-256);
            ret = (short) (b1 + (b2+1)*256);
        }
        return ret;
    }
    */

    /** @name encode32
     * @brief Method to create a 32-bit integer from 4 received bytes.
     * @param b1 The first byte.
     * @param b2 The second byte.
     * @param b3 The third byte.
     * @param b4 The fourth byte.
     * @return The created 32-bit integer.
     */
    /*
    private static int encode32(short b1, short b2, short b3, short b4){
        int ret = 0;
        if(b4>=0){ //positive number
            if(b1<0) b1=(short) (b1+256);
            if(b2<0) b2=(short) (b2+256);
            if(b3<0) b3=(short) (b3+256);
            ret = (int) (b1 + b2*256 + b3*256*256 + b4*256*256*256);
        }
        else { //negative number
            if(b1>=0) b1=(short) (b1-256);
            if(b2>=0) b2=(short) (b2-256);
            if(b3>=0) b3=(short) (b3-256);
            ret = (int) (b1 + (b2+1)*256 + (b3+1)*256*256 + (b3+1)*256*256*256);
        }
        return ret;
    }
    */
}