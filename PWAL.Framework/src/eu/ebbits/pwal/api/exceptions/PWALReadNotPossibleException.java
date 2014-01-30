package eu.ebbits.pwal.api.exceptions;

/**
 * PWAL Exception to mark impossibility to read a variable.
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.1.0
 */
public class PWALReadNotPossibleException extends PWALException {

    /**
     * Unique identification for serialization.
     */
    private static final long serialVersionUID = 3075573176642849477L;
    
    //TODO Idea for future releases: support reason of not possibility 
    //(e.g. read-only, ..I/O error...) 

}
