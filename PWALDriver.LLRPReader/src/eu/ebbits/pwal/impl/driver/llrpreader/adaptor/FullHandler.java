package eu.ebbits.pwal.impl.driver.llrpreader.adaptor;

import org.llrp.ltk.types.LLRPMessage;

import eu.ebbits.pwal.impl.driver.llrpreader.org.fosstrak.llrp.client.MessageHandler;

/**
 * Handler for a generic messages
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author    ISMB
 * @version    %I%, %G%
 * @since   PWAL 0.2.0
 */
public class FullHandler implements MessageHandler {
    
    @Override
    public void handle(String adaptorName, String readerName,
            LLRPMessage msg) {
        //System.out.println(String.format("received message ** %s **", msg.getName()));
    }
}