package eu.ebbits.pwal.api.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to represent generic Objects types abstracted by the <code>PWAL</code>.
 * Specific objects extend this class.
 * <p>
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.1.0
 */
public class PWALObject {
    
    // TODO Should we add some sort of unique id & type fields?
    
    /** Object name. */
    private String name;
    
    /** Object metadata. */
    private Map<String, PWALValue> metadata = null;
    
    /**
     * Generic constructor.
     *
     * @param nam -    the object name <code>String</code>
    *
    * @since        PWAL 0.1.0
     */
    public PWALObject(final String nam) {
        this.name = nam;
    }
    
    /**
     * Retrieves the <code>PWALObject</code>'s name.
     * 
     * @return        the name <code>String</code>
     * 
     * @since        PWAL 0.1.0
     */
    public final String getName() {
        return name;
    }

    /**
     * Sets or replaces the <code>PWALObject</code>'s name.
     * 
     * @param nam -    the name <code>String</code> to set
     * 
     * @since        PWAL 0.1.0
     */
    public final void setName(final String nam) {
        this.name = nam;
    }
    
    /**
     * Checks whether the <code>PWALObject</code> has metadata.
     * 
     * @return        <code>true</code> if the object was tagged,
     *                 <code>false</code> otherwise
     * 
     * @since        PWAL 0.1.0
     */
    public final boolean isTagged() {
        return metadata != null;
    }

    /**
     * Retrieves the <code>PWALObject</code>'s metadata.
     * 
     * @return        the key-value <code>Map</code> metadata
     * 
     * @since        PWAL 0.1.0
     */
    public final Map<String, PWALValue> getMetadata() {
        return metadata;
    }

    /**
     * Sets or replaces the <code>PWALObject</code>'s metadata.
     * 
     * @param meta - the metadata key-value <code>Map</code> to set
     * 
     * @since        PWAL 0.1.0
     */
    public final void setMetadata(final Map<String, PWALValue> meta) {
        this.metadata = meta;
    }
    
    /**
     * Inserts a new key-value pair to the current <code>PWALObject</code>'s 
     * metadata.
     * 
     * @param key -     the <code>String</code> key to add
     * @param data - the <code>PWALValue</code> data to add
     * 
     * @since        PWAL 0.1.0
     */
    public final void addMetadata(final String key, final PWALValue data) {
        if (this.metadata == null) {
            this.metadata = new HashMap<String, PWALValue>();
        }
        this.metadata.put(key, data);
    }
    
    /**
     * Inserts a new key-value pair to the current <code>PWALObject</code>'s 
     * metadata.
     * 
     * @param key -  the <code>String</code> key to add
     * @param data - the <code>Object</code> data to add
     * 
     * @since        PWAL 0.1.0
     */
    public final void addMetadata(final String key, final Object data) {
        if (this.metadata == null) {
            this.metadata = new HashMap<String, PWALValue>();
        }
        this.metadata.put(key, new PWALValue(data));
    }
    
    /**
     * Inserts a new key-value pair to the current <code>PWALObject</code>'s 
     * metadata.
     * 
     * @param meta - the key-value <code>Map</code> entry to add
     * 
     * @since        PWAL 0.1.0
     */
    public final void addMetadata(final Map<String, PWALValue> meta) {
        if (this.metadata == null) {
            this.metadata = new HashMap<String, PWALValue>();
        }
        this.metadata.putAll(meta);
    }
    
    /**
     * Empties the <code>PWALObject</code>'s metadata.
     * 
     * @since        PWAL 0.1.0
     */
    public final void clearMetadata() {
        this.metadata.clear();
    }
    
    /**
     * Checks whether a key is present in the metadata.
     * 
     * @param key -    the key <code>String</code> to check
     *
     * @return        <code>true</code> if the key is present in the metadata,
     *                 <code>false</code> otherwise
     * 
     * @since        PWAL 0.1.0
     */
    public final boolean containsMetadata(final String key) {
        if (this.metadata != null) {
            return this.metadata.containsKey(key);
        } else {
            return false;
        }
    }
    
    /**
     * Extracts the <code>PWALValue</code> from the metadata given the key.
     * 
     * @param key -    the key <code>String</code> of the metadata to retrieve
     * 
     * @return        the <code>PWALValue</code> for the given key, 
     *                 or <code>null</code> if the key is not present
     * 
     * @since        PWAL 0.1.0
     */
    public final PWALValue retrieveMetadata(final String key) {
        if (this.metadata != null) {
            return this.metadata.get(key);
        } else {
            return null;
        }
    }
    
    /**
     * Deletes a metadata entry given the key.
     * 
     * @param key -    the key <code>String</code> to remove
     * 
     * @since        PWAL 0.1.0
     */
    public final void removeMetadata(final String key) {
        if (this.metadata != null) {
            this.metadata.remove(key);
        }
    }
    
}
