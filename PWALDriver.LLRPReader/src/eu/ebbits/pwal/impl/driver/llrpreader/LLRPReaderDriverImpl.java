package eu.ebbits.pwal.impl.driver.llrpreader;

import java.util.ArrayList;

import org.osgi.service.component.ComponentContext;

import eu.ebbits.pwal.api.driver.PWALEventsDelegate;
import eu.ebbits.pwal.api.driver.PWALServicesDelegate;
import eu.ebbits.pwal.api.driver.PWALVariablesDelegate;
import eu.ebbits.pwal.api.driver.llrpreader.LLRPReaderDriver;
import eu.ebbits.pwal.api.exceptions.PWALConfigurationNotPossibleException;
import eu.ebbits.pwal.impl.driver.PWALDriverImpl;
import eu.ebbits.pwal.impl.driver.llrpreader.adaptor.LLRPReaderBundleManager;

/**
 * <code>LLRPReaderDriver</code> services implementation.
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author    ISMB
 * @version    %I%, %G%
 * @since    PWAL 0.2.0
 */
public class LLRPReaderDriverImpl extends PWALDriverImpl implements LLRPReaderDriver {
    
    /**
     * Instance of the the LLRP bundle manager used to control the reader
     */
    private LLRPReaderBundleManager reader = null;
    

    protected LLRPReaderBundleManager getReader() {
        return reader;
    }

    @Override
    public void init(ComponentContext context) {
        reader = new LLRPReaderBundleManager(this);
    }

    @Override
    public void run() {
        reader.start();
    }

    @Override
    public void stop() {
        reader.stopThread();
        super.stop();
    }
    
    
    @Override
    public void configureLLRPDriver(ArrayList<Object> values) throws PWALConfigurationNotPossibleException {
        this.reader.configureLLRPDriver(values);
    }

    
    @Override
    public String getReadConfig() {
        return this.reader.getReadConfig();
    }

    @Override
    public void setReadConfig(String readConfig) throws PWALConfigurationNotPossibleException {
        this.reader.setReadConfig(readConfig);
    }

    
    @Override
    public String getWriteConfig() {
        return this.reader.getWriteConfig();
    }


    @Override
    public void setWriteConfig(String writeConfig) throws PWALConfigurationNotPossibleException {
        this.reader.setWriteConfig(writeConfig);
    }


    @Override
    public boolean isCommitChanges() {
        return this.reader.isCommitChanges();
    }

    @Override
    public void setCommitChanges(boolean commitChanges) {
        this.reader.setCommitChanges(commitChanges);
    }

    
    @Override
    public String getAdapterName() {
        return this.reader.getAdapterName();
    }


    @Override
    public void setAdapterName(String adapterName) throws PWALConfigurationNotPossibleException {
        this.reader.setAdapterName(adapterName);
    }
    
    @Override
    public String getReaderName() {
        return this.reader.getReaderName();
    }


    @Override
    public void setReaderName(String readerName) throws PWALConfigurationNotPossibleException {
        this.reader.setReaderName(readerName);
    }


    @Override
    public String getReaderAddress() {
        return this.reader.getReaderAddress();
    }


    @Override
    public void setReaderAddress(String readerAddress) throws PWALConfigurationNotPossibleException {
        this.reader.setReaderAddress(readerAddress);
    }


    @Override
    public int getReaderPort() {
        return this.reader.getReaderPort();
    }


    @Override
    public void setReaderPort(int readerPort) throws PWALConfigurationNotPossibleException {
        this.reader.setReaderPort(readerPort);
    }


    @Override
    public String getRospecFile() {
        return this.reader.getRospecFile();
    }


    @Override
    public void setRospecFile(String rospecFile) throws PWALConfigurationNotPossibleException {
        this.reader.setRospecFile(rospecFile);
    }

    @Override
    protected PWALVariablesDelegate initVariablesDelegate() {
        return (PWALVariablesDelegate) new LLRPReaderVariablesDelegate(this);
    }

    @Override
    protected PWALServicesDelegate initServicesDelegate() {
        return (PWALServicesDelegate) new LLRPReadererServicesDelegate(this);
    }

    @Override
    protected PWALEventsDelegate initEventsDelegate() {
        return (PWALEventsDelegate) new LLRPReaderEventsDelegate(this);
    }
}
