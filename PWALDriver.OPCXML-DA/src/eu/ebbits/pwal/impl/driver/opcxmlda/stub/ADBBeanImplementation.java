package eu.ebbits.pwal.impl.driver.opcxmlda.stub;

import java.lang.reflect.Field;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.databinding.ADBBean;
import org.apache.axis2.databinding.ADBException;
import org.apache.log4j.Logger;

import java.security.PrivilegedAction;
import java.util.List;

/**
 * Abstract class used as base class for all the stub classes
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public abstract class ADBBeanImplementation implements ADBBean {
    
    /**
     * 
     */
    private static final long serialVersionUID = 6148771753017626684L;
    
    /* Logger instance */
    private Logger log = Logger.getLogger(ADBBeanImplementation.class.getName());
    
    
    //=======literal constants==============
    private static final String LOCAL_PROPERTY_NAMES = "localPropertyNames";
    
    private static final String LOCAL_ERRORS = "localErrors";
    
    private static final String LOCAL_ITEMS = "localItems";
    
    private static final String CANNOT_BE_NULL = " cannot be null!!";
    
    private static final String TYPE = "type";
    
    private static final String XML_SCHEMA_INSTANCE = "http://www.w3.org/2001/XMLSchema-instance";

    protected static final String OPC_NAMESPACE = "http://opcfoundation.org/webservices/XMLDA/1.0/";
    
    protected static final String OPC_PREFIX = "ns1";

    protected static final String FALSE = "false";
    
    /**
     * Returns a <code>java.lang.reflect.Field</code> of a class using reflection
     * 
     * @param fieldName - a <code>String</code> containing the field name to retrieve
     * 
     * @return   the <code>java.lang.reflect.Field</code> if it exists, null if a field with the name fieldName doesn't exist
     *  
     */
    private Field getProtectedField(final String fieldName) {
        final ADBBeanImplementation classToUse = this;
        return java.security.AccessController.doPrivileged (new PrivilegedAction<Field>() {
            @Override
            public Field run() {
                try {
                    Field fieldToUse = classToUse.getClass().getDeclaredField(fieldName);
                    fieldToUse.setAccessible(true);        
                    return fieldToUse;
                } catch (NoSuchFieldException ex) {
                    log.error("something wrong, this parameter is unsupported in this class");
                    return null;
                } catch (IllegalArgumentException e) {
                    log.error("something wrong retrieving "+fieldName,e);
                    return null;
                } 
            }});
    }
    
    /**
     * Returns the value contained in a <code>java.lang.reflect.Field</code> of a class using reflection
     * 
     * @param fieldName - a <code>String</code> containing the field name to retrieve
     * 
     * @return   the <code>Object</code> if it exists, null if a field with the name fieldName doesn't exist
     *  
     */    
    private Object getProtectedFieldValue(final String fieldName) {
        final ADBBeanImplementation classToUse = this;
        return java.security.AccessController.doPrivileged (new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                try {
                    Field fieldToUse = getProtectedField(fieldName);
                    return fieldToUse.get(classToUse);
                }  catch (IllegalArgumentException e) {
                    log.error("something wrong retrieving "+fieldName,e);
                    return null;
                } catch (IllegalAccessException e) {
                    log.error("something wrong retrieving "+fieldName,e);
                    return null;
                }
            }});
    }

    
    /**
     * Sets the value of a <code>java.lang.reflect.Field</code> of a class using reflection
     * 
     * @param fieldName - a <code>String</code> containing the field name to retrieve
     * @param value - an <code>Object</code> containing the value to set
     * 
     * @return - a <code>boolean</code> - true, if the value is set, false if something goes wrong
     */    
    private boolean setProtectedFieldValue(final String fieldName, final Object value) {
        final ADBBeanImplementation classToUse = this;
        return java.security.AccessController.doPrivileged (new PrivilegedAction<Boolean>() {
            @Override
            public Boolean run() {
                try {
                    Field fieldToUse = getProtectedField(fieldName);
                    fieldToUse.set(classToUse,value);
                    return true;
                } catch (IllegalArgumentException e) {
                    log.error("something wrong setting "+fieldName,e);
                    return false;
                } catch (IllegalAccessException e) {
                    log.error("something wrong setting "+fieldName,e);
                    return false;
                }
            }});
        
    }

    /**
     * Sets the value of a <code>java.lang.reflect.Field</code> of a class using reflection
     * furthermore sets also the value of the associated tracker
     * 
     * @param fieldName - a <code>String</code> containing the field name to retrieve
     * @param trackerdName - a <code>String</code> containing the tracker name to use 
     * @param value - an <code>Object</code> containing the value to set
     * 
     * @return - a <code>boolean</code> - true, if the value is set, false if something goes wrong
     * 
     */    
    protected void setProtectedFieldValueWithTracker(String fieldName,
                                                String trackerName,
                                                Object value) {
        if (value != null) {
            //update the setting tracker
            setProtectedFieldValue(trackerName,true);
        } else {
            setProtectedFieldValue(trackerName,false);
        }
        setProtectedFieldValue(fieldName,value);    
    }
    
    /**
     * Adds an element to a field of type array, using reflection
     *  
     * @param trackerdName - a <code>String</code> containing the tracker name to use 
     * @param oldList - the list of <code>Object</code>s contained in the array 
     * @param element - the <code>Object</code> to add
     * 
     * @return   a <code>List</code> with all the elements
     */
    private List<Object> addElementToProtectedArrayField(final String trackerName, 
                                                final Object[] oldList, 
                                                final Object element) {
        //update the setting tracker
        setProtectedFieldValue(trackerName,true);
        java.util.List<Object> list =
                org.apache.axis2.databinding.utils.ConverterUtil.toList(oldList);
        list.add(element);
        return list;
    }

    
    /**
     * Retrieves the ItemPath attribute of the implementation class if present using reflection
     * 
     * @return    a <code>String</code> containing the ItemPath attribute if it is present in the implementation class, 
     *               null if it is not present
     */
    public String getItemPath() {
        return (String) getProtectedFieldValue("localItemPath");
    }

    /**
     * Sets the ItemPath attribute of the implementation class if present using reflection
     * 
     * @param itemPath - a <code>String</code> containing the value to set for the ItemPath attribute
     */
    public void setItemPath(String itemPath) {
        setProtectedFieldValue("localItemPath",itemPath);
    };
    

    /**
     * Retrieves the ItemName attribute of the implementation class if present using reflection
     * 
     * @return   a <code>String</code> containing the ItemName attribute if it is present in the implementation class, 
     *           null if it is not present
     */

    public String getItemName() {
        return (String) getProtectedFieldValue("localItemName");
    }

    
    /**
     * Sets the ItemName attribute of the implementation class if present using reflection
     * 
     * @param itemName - a <code>String</code> containing the value to set for the ItemName attribute
     */
    public void setItemName(String itemName) {
        setProtectedFieldValue("localItemName",itemName);
    };

    
    /**
     * Retrieves the LocaleID attribute of the implementation class if present using reflection
     * 
     * @return    a <code>String</code> containing the LocaleID attribute if it is present in the implementation class, 
     *            null if it is not present
     */
    public String getLocaleID() {
        return (String) getProtectedFieldValue("localLocaleID");
    }


    /**
     * Sets the LocaleID attribute of the implementation class if present using reflection
     * 
     * @param localeID - a <code>String</code> containing the value to set for the LocaleID attribute
     * 
     */
    public void setLocaleID(String localeID) {
        setProtectedFieldValue("localLocaleID",localeID);
    };
    
    
    /**
     * Retrieves the ClientRequestHandle attribute of the implementation class if present using reflection
     * 
     * @return    a <code>String</code> containing the ClientRequestHandle attribute if it is present in the implementation class, 
     *            null if it is not present
     */
    public String getClientRequestHandle() {
        return (String) getProtectedFieldValue("localClientRequestHandle");
    }
    
    
    /**
     * Sets the ClientRequestHandle attribute of the implementation class if present using reflection
     * 
     * @param clientRequestHandle - a <code>String</code> containing the value to set for the ClientRequestHandle attribute
     * 
     */
    public void setClientRequestHandle(String localClientRequestHandle) {
        setProtectedFieldValue("localClientRequestHandle",localClientRequestHandle);
    };
    
    
    /**
     * Retrieves the ContinuationPoint attribute of the implementation class if present using reflection
     * 
     * @return    a <code>String</code> containing the ContinuationPoint attribute if it is present in the implementation class, 
     *            null if it is not present
     */
    public String getContinuationPoint() {
        return (String) getProtectedFieldValue("localContinuationPoint");
    }
    
    /**
     * Sets the ContinuationPoint attribute of the implementation class if present using reflection
     * 
     * @param continuationPoint - a <code>String</code> containing the value to set for the ContinuationPoint attribute
     * 
     */
    public void setContinuationPoint(String localClientRequestHandle) {
        setProtectedFieldValue("localContinuationPoint",localClientRequestHandle);
    };
    
    /**
     * Retrieves the ReqType attribute of the implementation class if present using reflection
     * 
     * @return   a <code>javax.xml.namespace.QName</code> containing the ReqType attribute if it is present in the implementation class, 
     *           null if it is not present
     */
    public javax.xml.namespace.QName getReqType() {
        return (javax.xml.namespace.QName) getProtectedFieldValue("localReqType");
    }
    
    /**
     * Sets the ReqType attribute of the implementation class if present using reflection
     * 
     * @param reqType - a <code>javax.xml.namespace.QName</code> containing the value to set for the ReqType attribute
     * 
     */
    public void setReqType(javax.xml.namespace.QName localReqType) {
        setProtectedFieldValue("localReqType",localReqType);
    };
    
    /**
     * Retrieves the ClientItemHandle attribute of the implementation class if present using reflection
     * 
     * @return   a <code>String</code> containing the ClientItemHandle attribute if it is present in the implementation class, 
     *           null if it is not present
     */
    public String getClientItemHandle() {
        return (String) getProtectedFieldValue("localClientItemHandle");
    }
    
    
    /**
     * Sets the ClientItemHandle attribute of the implementation class if present using reflection
     * 
     * @param clientItemHandle - a <code>String</code> containing the value to set for the ClientItemHandle attribute
     * 
     */
    public void setClientItemHandle(String localClientItemHandle) {
        setProtectedFieldValue("localClientItemHandle",localClientItemHandle);
    };
    
    /**
     * Retrieves the Deadband attribute of the implementation class if present using reflection
     * 
     * @return    a <code>float</code> containing the Deadband attribute if it is present in the implementation class, 
     *            null if it is not present
     */
    public float getDeadband() {
        return ((Float)getProtectedFieldValue("localDeadband")).floatValue();
    }
    
    
    /**
     * Sets the Deadband attribute of the implementation class if present using reflection
     * 
     * @param deadband - a <code>float</code> containing the value to set for the Deadband attribute
     * 
     */
    public void setDeadband(float localDeadband) {
        setProtectedFieldValue("localDeadband",new Float(localDeadband));
    };

    
    /**
     * Retrieves the RequestedSamplingRate attribute of the implementation class if present using reflection
     * 
     * @return    a <code>int</code> containing the RequestedSamplingRate attribute if it is present in the implementation class, 
     *            null if it is not present
     */
    public int getRequestedSamplingRate() {
        return ((Integer)getProtectedFieldValue("localRequestedSamplingRate")).intValue();
    }
    
    
    /**
     * Sets the RequestedSamplingRate attribute of the implementation class if present using reflection
     * 
     * @param requestedSamplingRate - a <code>int</code> containing the value to set for the RequestedSamplingRate attribute
     * 
     */
    public void setRequestedSamplingRate(int localRequestedSamplingRate) {
        setProtectedFieldValue("localRequestedSamplingRate", Integer.valueOf(localRequestedSamplingRate));
    };

    
    /**
     * Retrieves the EnableBuffering attribute of the implementation class if present using reflection
     * 
     * @return    a <code>boolean</code> containing the EnableBuffering attribute if it is present in the implementation class, 
     *            null if it is not present
     */
    public boolean getEnableBuffering() {
        return ((Boolean)getProtectedFieldValue("localEnableBuffering")).booleanValue();
    }
    
    /**
     * Sets the EnableBuffering attribute of the implementation class if present using reflection
     * 
     * @param enableBuffering - a <code>boolean</code> containing the value to set for the EnableBuffering attribute
     * 
     */
    public void setEnableBuffering(boolean localEnableBuffering) {
        setProtectedFieldValue("localEnableBuffering",Boolean.valueOf(localEnableBuffering));
    };
    
    /**
     * Retrieves the ReturnAllProperties attribute of the implementation class if present using reflection
     * 
     * @return    a <code>boolean</code> containing the ReturnAllProperties attribute if it is present in the implementation class, 
     *            null if it is not present
     */
    public boolean getReturnAllProperties() {
        return ((Boolean)getProtectedFieldValue("localReturnAllProperties")).booleanValue();
    }
    
    /**
     * Sets the ReturnAllProperties attribute of the implementation class if present using reflection
     * 
     * @param returnAllProperties - a <code>boolean</code> containing the value to set for the ReturnAllProperties attribute
     * 
     */
    public void setReturnAllProperties(boolean localReturnAllProperties) {
        setProtectedFieldValue("localReturnAllProperties", Boolean.valueOf(localReturnAllProperties));
    };

    /**
     * Retrieves the ReturnPropertyValues attribute of the implementation class if present using reflection
     * 
     * @return    a <code>boolean</code> containing the ReturnPropertyValues attribute if it is present in the implementation class, 
     *            null if it is not present
     */
    public boolean getReturnPropertyValues() {
        return ((Boolean)getProtectedFieldValue("localReturnPropertyValues")).booleanValue();
    }
    
    /**
     * Sets the ReturnPropertyValues attribute of the implementation class if present using reflection
     * 
     * @param returnPropertyValues - a <code>boolean</code> containing the value to set for the ReturnPropertyValues
     * 
     */
    public void setReturnPropertyValues(boolean localReturnPropertyValues) {
        setProtectedFieldValue("localReturnPropertyValues",Boolean.valueOf(localReturnPropertyValues));
    };
    
    
    /**
     * Retrieves the ReturnErrorText attribute of the implementation class if present using reflection
     * 
     * @return    a <code>boolean</code> containing the ReturnErrorText attribute if it is present in the implementation class, 
     *            null if it is not present
     */
    public boolean getReturnErrorText() {
        return ((Boolean)getProtectedFieldValue("localReturnErrorText")).booleanValue();
    }
    
    /**
     * Sets the ReturnErrorText attribute of the implementation class if present using reflection
     * 
     * @param returnErrorText - a <code>boolean</code> containing the value to set for the ReturnErrorText
     * 
     */
    public void setReturnErrorText(boolean localReturnErrorText) {
        setProtectedFieldValue("localReturnErrorText", Boolean.valueOf(localReturnErrorText));
    };
    
    
    /**
     * Retrieves the ResultID attribute of the implementation class if present using reflection
     * 
     * @return    a <code>javax.xml.namespace.QName</code> containing the ResultID attribute if it is present in the implementation class, 
     *            null if it is not present
     */
    public javax.xml.namespace.QName getResultID() {
        return (javax.xml.namespace.QName) getProtectedFieldValue("localResultID");
    }
    
    
    /**
     * Sets the ResultID attribute of the implementation class if present using reflection
     * 
     * @param resultID - a <code>javax.xml.namespace.QName</code> containing the value to set for the ResultID
     * 
     */
    public void setResultID(javax.xml.namespace.QName localResultID) {
        setProtectedFieldValue("localResultID",localResultID);
    };

    /**
     * Retrieves the MaxAge attribute of the implementation class if present using reflection
     * 
     * @return    a <code>int</code> containing the MaxAge attribute if it is present in the implementation class, 
     *            null if it is not present
     */
    public int getMaxAge() {
        return ((Integer)getProtectedFieldValue("localMaxAge")).intValue();
    }
    
    
    /**
     * Sets the MaxAge attribute of the implementation class if present using reflection
     * 
     * @param maxAge - a <code>int</code> containing the value to set for the MaxAge
     * 
     */
    public void setMaxAge(int localMaxAge) {
        setProtectedFieldValue("localMaxAge", Integer.valueOf(localMaxAge));
    };
    
    /**
     * Retrieves the RevisedSamplingRate attribute of the implementation class if present using reflection
     * 
     * @return    a <code>int</code> containing the RevisedSamplingRate attribute if it is present in the implementation class, 
     *            null if it is not present
     */
    public int getRevisedSamplingRate() {
        return ((Integer)getProtectedFieldValue("localRevisedSamplingRate")).intValue();
    }
    
    /**
     * Sets the RevisedSamplingRate attribute of the implementation class if present using reflection
     * 
     * @param RevisedSamplingRate - a <code>int</code> containing the value to set for the RevisedSamplingRate
     * 
     */
    public void setRevisedSamplingRate(int localRevisedSamplingRate) {
        setProtectedFieldValue("localRevisedSamplingRate", Integer.valueOf(localRevisedSamplingRate));
    };

    
    /**
     * Retrieves the ServerSubHandle attribute of the implementation class if present using reflection
     * 
     * @return    a <code>String</code> containing the ServerSubHandle attribute if it is present in the implementation class, 
     *            null if it is not present
     */
    public String getServerSubHandle() {
        return (String) getProtectedFieldValue("localServerSubHandle");
    }
    
    
    /**
     * Sets the ServerSubHandle attribute of the implementation class if present using reflection
     * 
     * @param serverSubHandle - a <code>String</code> containing the value to set for the ServerSubHandle
     * 
     */
    public void setServerSubHandle(String localServerSubHandle) {
        setProtectedFieldValue("localServerSubHandle",localServerSubHandle);
    };
    

    /**
     * Retrieves the Name attribute of the implementation class if present using reflection
     * 
     * @return    a <code>String</code> containing the Name attribute if it is present in the implementation class, 
     *            null if it is not present
     */
    public String getName() {
        return (String) getProtectedFieldValue("localName");
    }
    
    /**
     * Sets the Name attribute of the implementation class if present using reflection
     * 
     * @param name - a <code>String</code> containing the value to set for the Name
     * 
     */
    public void setName(String localName) {
        setProtectedFieldValue("localName",localName);
    };


    /**
     * Sets the Name attribute of the implementation class if present using reflection
     * 
     * @param name - a <code>org.apache.axiom.om.OMElement</code> containing the value to set for the Name
     * 
     */
    public void setValue(org.apache.axiom.om.OMElement localValue) {
        setProtectedFieldValueWithTracker("localValue","localValueTracker",localValue);
    };

    
    /**
     * Retrieves the list of the property names of the implementation class, if present, using reflection
     * 
     * @return    a <code>javax.xml.namespace.QName[]</code> containing the property names if they are present in the implementation class, 
     *            null if they are not present
     */
    public  javax.xml.namespace.QName[] getPropertyNames() {
        return (QName[]) getProtectedFieldValue(LOCAL_PROPERTY_NAMES);
    }
    
    
    /**
     * Adds an element to the list of the property names of the implementation class, if they are present, using reflection
     * 
     * @param param - the <code>javax.xml.namespace.QName</code> to add to the list
     * 
     */
    public void addPropertyNames(javax.xml.namespace.QName param) {
        javax.xml.namespace.QName[] localPropertyNames = (QName[]) getProtectedFieldValue(LOCAL_PROPERTY_NAMES);
        if (localPropertyNames == null) {
            setProtectedFieldValue(LOCAL_PROPERTY_NAMES,new javax.xml.namespace.QName[]{});
        }
        List<Object> list = addElementToProtectedArrayField("localPropertyNamesTracker",localPropertyNames,param);
        setProtectedFieldValue(LOCAL_PROPERTY_NAMES,list.toArray(new javax.xml.namespace.QName[list.size()]));
    }    

    /**
     * Validates the array for PropertyNames
     * 
     * @param param - the array to  be validated
     */
    protected void validatePropertyNames(javax.xml.namespace.QName[] param) {
    }

    /**
     * Sets the value passed as parameter as array of property names
     * 
     * @param param - an array of <code>javax.xml.namespace.QName</code> containing the property names to set
     */
    public void setPropertyNames(javax.xml.namespace.QName[] param) {
        javax.xml.namespace.QName[] propertyNamesToUse = param.clone();
        validatePropertyNames(propertyNamesToUse);
        setProtectedFieldValueWithTracker(LOCAL_PROPERTY_NAMES, "localPropertyNamesTracker", propertyNamesToUse);
    }    
    
    /**
     * Sets the RItemList attribute using its tracker
     * 
     * @param param - <code>ReplyItemList</code> to be set 
     */
    public void setRItemList(ReplyItemList param) {
        setProtectedFieldValueWithTracker("localRItemList", "localRItemListTracker", param);
    }
    
    
    /**
     * Retrieves the list of the errors of the implementation class, if present, using reflection
     * 
     * @return    a <code>OPCError[]</code> containing the errors if they are present in the implementation class, 
     *            null if they are not present
     */
    public OPCError[] getErrors() {
        return (OPCError[]) getProtectedFieldValue(LOCAL_ERRORS);
    }


    /**
     * Validates the array for Errors
     * 
     * @param param - the array to  be validated
     */
    protected void validateErrors(OPCError[] param) {
    }

    
    /**
     * Sets the value passed as parameter as array of errors
     * 
     * @param param - an array of <code>OPCError</code> containing the errors to set
     */
    public void setErrors(OPCError[] param) {
        OPCError[] errorsToUse = param.clone();
        validateErrors(errorsToUse);
        setProtectedFieldValueWithTracker(LOCAL_ERRORS, "localErrorsTracker", errorsToUse);
    }    
    
    /**
     * Adds an element to the list of the errors of the implementation class, if they are present, using reflection
     * 
     * @param param - the <code>OPCError</code> to add to the list
     * 
     */
    public void addErrors(OPCError param) {
        OPCError[] localErrors = (OPCError[]) getProtectedFieldValue(LOCAL_ERRORS);
        if (localErrors == null) {
            setProtectedFieldValue(LOCAL_ERRORS, new OPCError[]{});
        }
        List<Object> list = addElementToProtectedArrayField("localErrorsTracker",localErrors,param);
        setProtectedFieldValue(LOCAL_ERRORS,list.toArray(new OPCError[list.size()]));
    }

    
    /**
     * Retrieves the list of the properties of the implementation class, if present, using reflection
     * 
     * @return    a <code>ItemProperty[]</code> containing the properties if they are present in the implementation class, 
     *            null if they are not present
     */
    public  ItemProperty[] getProperties() {
        return (ItemProperty[]) getProtectedFieldValue("localProperties");
    }
    
    /**
     * Validates the array for Properties
     * 
     * @param param - the array to  be validated
     */
    protected void validateProperties(ItemProperty[] param) {
    }

    
    /**
     * Sets the value passed as parameter as array of properties
     * 
     * @param param - an array of <code>ItemProperty</code> containing the errors to set
     */
    public void setProperties(ItemProperty[] param) {
        ItemProperty[] propertiesToUse = param.clone();
        validateProperties(propertiesToUse);
        setProtectedFieldValueWithTracker("localProperties", "localPropertiesTracker", propertiesToUse);
    }

    
    /**
     * Adds an element to the list of the properties of the implementation class, if they are present, using reflection
     * 
     * @param param - the <code>ItemProperty</code> to add to the list
     * 
     */
    public void addProperties(ItemProperty param) {
        ItemProperty[] localProperties = (ItemProperty[]) getProtectedFieldValue("localProperties");
        if (localProperties == null) {
            localProperties = new ItemProperty[]{};
        }
        List<Object> list = addElementToProtectedArrayField("localPropertiesTracker",localProperties,param);
        setProtectedFieldValue(LOCAL_ERRORS,list.toArray(new ItemProperty[list.size()]));
    }

    
    
    /**
     * Retrieves the Options attribute of the implementation class, if present, using reflection
     * 
     * @return    a <code>RequestOptions</code> containing the options if they are present in the implementation class, 
     *            null if they are not present
     */
    public  RequestOptions getOptions() {
        return (RequestOptions) getProtectedFieldValue("localOptions");
    }
    
    /**
     * Sets the Options attribute using its tracker
     * 
     * @param param - <code>RequestOptions</code> to be set 
     */
    public void setOptions(RequestOptions param) {
        setProtectedFieldValueWithTracker("localOptions", "localOptionsTracker", param);
    }

    
    /**
     * Validates the array for Items
     * 
     * @param param - the array to  be validated
     */
    protected void validateItems(ItemValue[] param) {
    }

    
    /**
     * Sets the value passed as parameter as array of items
     * 
     * @param param - an array of <code>ItemValue</code> containing the items to set
     */
    public void setItems(ItemValue[] param) {
        ItemValue[] itemsToUse = param.clone();
        validateItems(itemsToUse);
        setProtectedFieldValueWithTracker(LOCAL_ITEMS, "localItemsTracker", itemsToUse);
    }



    /**
     * Adds an element to the list of the items of the implementation class, if they are present, using reflection
     * 
     * @param param - the <code>ItemValue</code> to add to the list
     * 
     */
    public void addItems(ItemValue param) {
        ItemValue[] localErrors = (ItemValue[]) getProtectedFieldValue(LOCAL_ITEMS);
        if (localErrors == null) {
            setProtectedFieldValue(LOCAL_ITEMS, new OPCError[]{});
        }
        List<Object> list = addElementToProtectedArrayField("localItemsTracker",localErrors,param);
        setProtectedFieldValue(LOCAL_ITEMS,list.toArray(new ItemValue[list.size()]));
    }

    
    

    /**
     * Return the prefix for the namespace passed as parameter
     * 
     * @param namespace - a <code>String</code> that contains the namespace
     * 
     * @return   the prefix for the namespace as <code>String</code>
     */
    protected String generatePrefix(String namespace) {
        if(namespace.equals(OPC_NAMESPACE)) {
            return OPC_PREFIX;
        }
        return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }    
    

    /**
     * 
     * Util method to write a start element xml
     * 
     * @param attValue - attribute to be written as <code>String</code>
     * @param prefix - prefix to be used as <code>String</code>
     * @param namespace - namespace to be used as <code>String</code>
     * @param emptyNamespace - <code>boolean</code> that indicates if the namespace is null (true), or not (false)
     * @param xmlWriter - <code>javax.xml.stream.XMLStreamWriter</code> to be used
     * 
     * @throws XMLStreamException - if something goes wrong writing the element
     */
    protected void writeStartElement(String attValue,
                                    String prefix,
                                    String namespace,
                                    boolean emptyNamespace,
                                    javax.xml.stream.XMLStreamWriter xmlWriter) throws XMLStreamException {
        if (!emptyNamespace) {
            if (prefix == null) {
                String prefix2 = generatePrefix(namespace);
                xmlWriter.writeStartElement(prefix2,attValue, namespace);
                xmlWriter.writeNamespace(prefix2, namespace);
                xmlWriter.setPrefix(prefix2, namespace);
            } else {
                xmlWriter.writeStartElement(namespace,attValue);
            }
        } else {
            xmlWriter.writeStartElement(attValue);
        }
    }
    

    /**
     * Util method to write an attribute with the ns prefix
     * 
     * @param prefix - prefix to be used as <code>String</code>
     * @param namespace - namespace to be used as <code>String</code>
     * @param attName - name of the attribute as <code>String</code>
     * @param attValue - value of the attribute as <code>String</code>
     * @param xmlWriter - <code>javax.xml.stream.XMLStreamWriter</code> to be used
     * 
     * @throws javax.xml.stream.XMLStreamException - if something goes wrong writing the attribute
     */
    protected void writeAttribute(String prefix,String namespace,String attName,
            String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
        if (xmlWriter.getPrefix(namespace) == null) {
            xmlWriter.writeNamespace(prefix, namespace);
            xmlWriter.setPrefix(prefix, namespace);
        }
        xmlWriter.writeAttribute(namespace,attName,attValue);
    }

    
    /**
     * Util method to write an attribute without the ns prefix
     * 
     * @param namespace - namespace to be used as <code>String</code>
     * @param attName - name of the attribute as <code>String</code>
     * @param attValue - value of the attribute as <code>String</code>
     * @param xmlWriter - <code>javax.xml.stream.XMLStreamWriter</code> to be used
     * 
     * @throws javax.xml.stream.XMLStreamException - if something goes wrong writing the attribute
     */
    protected void writeAttribute(String namespace,String attName,
            String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
        if(attValue!=null) {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                writeNaspaceWithPrefix(namespace, xmlWriter);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }
    }


    /**
     * Util method to write an attribute with the OPC namespace
     * 
     * @param tracker - indicates if the value has to be written (true) or not (false)
     * @param attName - name of the attribute as <code>String</code> 
     * @param attValue - value of the attribute, it can be a <code>String</code> or an <code>OMElement</code>
     * @param xmlWriter - <code>org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter</code> to be used
     * 
     * @throws javax.xml.stream.XMLStreamException - if something goes wrong writing the attribute
     */
    protected void writeAttributeWithNamespace(boolean tracker,
            String attName,
            Object attValue,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws XMLStreamException {
        if (tracker) {
            String namespace = OPC_NAMESPACE;
            if (! namespace.equals("")) {
                String prefix = xmlWriter.getPrefix(namespace);
                if (prefix == null) {
                    prefix = generatePrefix(namespace);
                    xmlWriter.writeStartElement(prefix, attName, namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace,attName);
                }
            } else {
                xmlWriter.writeStartElement(attName);
            }
            if (attValue==null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(attName+CANNOT_BE_NULL);
            }else{
                if(OMElement.class.isInstance(attValue)) {
                    ((OMElement) attValue).serialize(xmlWriter);
                } else if(String.class.isInstance(attValue)) {
                    xmlWriter.writeCharacters((String)attValue);
                }
            }
            xmlWriter.writeEndElement();
        }
    }
    
    
    /**
     * Util method to write a list of attributes, with the OPC namespace
     * 
     * @param tracker - indicates if the value has to be written (true) or not (false)
     * @param attName - name of the attribute as <code>String</code> 
     * @param attValues - values of the attribute, array of <code>Object</code>s
     * @param xmlWriter - <code>org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter</code> to be used
     * 
     * @throws javax.xml.stream.XMLStreamException - if something goes wrong writing the attribute
     */
    protected void writeAttributesWithNamespace(boolean tracker, 
                                String name, 
                                Object[] attributeValues,
                                org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
                                        throws XMLStreamException {
        if (tracker) {
            if (attributeValues!=null) {
                String namespace = OPC_NAMESPACE;
                boolean emptyNamespace = namespace == null || namespace.length() == 0;
                String prefix =  emptyNamespace ? null : xmlWriter.getPrefix(namespace);
                for (int i = 0;i < attributeValues.length;i++) {
                    if (attributeValues[i] != null) {
                        writeStartElement(name,prefix,namespace,emptyNamespace,xmlWriter);
                        xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(attributeValues[i]));
                        xmlWriter.writeEndElement();
                    } 
                }
            } else {
                throw new org.apache.axis2.databinding.ADBException(name+CANNOT_BE_NULL);
            }
        }

    }
    
    
    /**
     * Util method to write an attribute without the ns prefix
     * 
     * @param namespace - namespace to be used as <code>String</code>
     * @param attName - name of the attribute as <code>String</code>
     * @param qname - value of the attribute as <code>QName</code>
     * @param xmlWriter - <code>javax.xml.stream.XMLStreamWriter</code> to be used
     * 
     * @throws javax.xml.stream.XMLStreamException - if something goes wrong writing the attribute
     */
    protected void writeQNameAttribute(String namespace, String attName,
            javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

        if(qname!=null) {
            String attributeNamespace = qname.getNamespaceURI();
            String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
            if (attributePrefix == null) {
                attributePrefix = writeNaspaceWithPrefix(attributeNamespace, xmlWriter);
            }
            String attributeValue;
            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                writeNaspaceWithPrefix(namespace, xmlWriter);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }
    }


    /**
     * Method used to write <code>Qname</code> values
     * 
     * @param qname - the <code>Qname</code> to be written
     * @param xmlWriter - <code>javax.xml.stream.XMLStreamWriter</code> to be used
     * 
     * @throws javax.xml.stream.XMLStreamException - if something goes wrong writing the value
     */
    protected void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
        String namespaceURI = qname.getNamespaceURI();
        if (namespaceURI != null) {
            String prefix = xmlWriter.getPrefix(namespaceURI);
            if (prefix == null) {
                prefix = generatePrefix(namespaceURI);
                xmlWriter.writeNamespace(prefix, namespaceURI);
                xmlWriter.setPrefix(prefix,namespaceURI);
            }
            if (prefix.trim().length() > 0) {
                xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            } else {
                // i.e this is the default namespace
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        } else {
            xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }
    }

    
    /**
     * Method used to write an array of <code>javax.xml.namespace.Qname</code> values
     * 
     * @param qname - the <code>javax.xml.namespace.Qname[]</code> to be written
     * @param xmlWriter - <code>javax.xml.stream.XMLStreamWriter</code> to be used
     * 
     * @throws javax.xml.stream.XMLStreamException - if something goes wrong writing the value
     */
    protected void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
        if (qnames != null) {
            // we have to store this data until last moment since it is not possible to write any
            // namespace data after writing the charactor data
            StringBuffer stringToWrite = new StringBuffer();
            String namespaceURI = null;
            String prefix = null;

            for (int i = 0; i < qnames.length; i++) {
                if (i > 0) {
                    stringToWrite.append(" ");
                }
                namespaceURI = qnames[i].getNamespaceURI();
                if (namespaceURI != null) {
                    prefix = xmlWriter.getPrefix(namespaceURI);
                    if ((prefix == null) || (prefix.length() == 0)) {
                        prefix = generatePrefix(namespaceURI);
                        xmlWriter.writeNamespace(prefix, namespaceURI);
                        xmlWriter.setPrefix(prefix,namespaceURI);
                    }
                    if (prefix.trim().length() > 0) {
                        stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                } else {
                    stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                }
            }
            xmlWriter.writeCharacters(stringToWrite.toString());
        }
    }


    /**
     * Method used to generate a prefix from a namespace and to write them in an xml 
     * @param namespace - the namespace to be writte as a <code>String</code>
     * @param xmlWriter - <code>javax.xml.stream.XMLStreamWriter</code> to be used 
     * 
     * @return   the prefix generated as <code>String</code>
     * 
     * @throws javax.xml.stream.XMLStreamException - if something goes wrong writing the value
     */
    protected String writeNaspaceWithPrefix(String namespace, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
        String prefix = xmlWriter.getPrefix(namespace);
        if (prefix == null) {
            prefix = generatePrefix(namespace);
            while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
            }
            xmlWriter.writeNamespace(prefix, namespace);
            xmlWriter.setPrefix(prefix, namespace);
        }
        return prefix;
    }
    
    
    /**
     * 
     * Retrieves if a reader supports MTOM or not
     * 
     * @param reader - the <code>javax.xml.stream.XMLStreamReader</code> that has to be tested
     * 
     * 
     * @return true if the reader supports MTOM 
     */
    public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        try{
            isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e) {
            isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
    }
    
    
    
    /**
     *
     * Returns the <code>OMElement</code> for the implementation class using reflection
     *
     * @param parentQName - the parent <code>javax.xml.namespace.QName</code> to use
     * @param factory - the <code>org.apache.axiom.om.OMFactory</code> to use
     * 
     * @return   the <code>OMElement</code> for the implementation class
     * 
     */
    public org.apache.axiom.om.OMElement getOMElement (
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory) 
                    throws org.apache.axis2.databinding.ADBException{
        Field field = null;
        QName qNameToUse = null;
        try {
            field = this.getClass().getField("MY_QNAME");
            if(field!=null) {
                qNameToUse = (QName) field.get(this);
            }
        } catch (NoSuchFieldException e) {
            field = null;
            qNameToUse = parentQName;
        } catch (SecurityException e) {
            field = null;
            qNameToUse = parentQName;
        } catch (IllegalArgumentException e) {
            field = null;
            qNameToUse = parentQName;
        } catch (IllegalAccessException e) {
            field = null;
            qNameToUse = parentQName;
        }
        final QName finalQName = qNameToUse;
        org.apache.axiom.om.OMDataSource dataSource =
                new org.apache.axis2.databinding.ADBDataSource(this,finalQName) {
            @Override
            public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                ADBBeanImplementation.this.serialize(finalQName,factory,xmlWriter);
            }
        };
        return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
                finalQName,factory,dataSource);
    }
    
    @Override
    public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                    throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        serialize(parentQName,factory,xmlWriter,false);
    }

    @Override
    public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
            boolean serializeType)
                    throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{

        String prefix = ""; 
        String namespace = parentQName.getNamespaceURI();
        String localName = parentQName.getLocalPart();
        
        // For these classes we can safely assume an element has only one type associated with it
        if(getClassName().equals("LimitBits") ||
                getClassName().equals("InterfaceVersion") ||
                getClassName().equals("BrowserFilter") ||
                getClassName().equals("QualityBits")) {
            
            if (! namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);
                if (prefix == null) {
                    prefix = generatePrefix(namespace);
                    xmlWriter.writeStartElement(prefix, localName, namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, localName);
                }
            } else {
                xmlWriter.writeStartElement(localName);
            }
        } else {    
            prefix = parentQName.getPrefix();
            if ((namespace != null) && (namespace.trim().length() > 0)) {
                String writerPrefix = xmlWriter.getPrefix(namespace);
                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace, localName);
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }
    
                    xmlWriter.writeStartElement(prefix, localName, namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(localName);
            }
        }
        if (serializeType) {
            String namespacePrefix = writeNaspaceWithPrefix(OPC_NAMESPACE,xmlWriter);
            if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
                writeAttribute("xsi",XML_SCHEMA_INSTANCE,TYPE,
                        namespacePrefix+":"+getClassName(),
                        xmlWriter);
            } else {
                writeAttribute("xsi",XML_SCHEMA_INSTANCE,TYPE,
                        getClassName(),
                        xmlWriter);
            }
        }
        writeAttributes(prefix,namespace,factory,xmlWriter);
    }
    
    /**
     * Returns the correct <code>ADBBeanImplementation</code> extension class for the name passed as parameter
     * 
     * @param name - name of the extension as <code>String</code>
     * @param reader - the <code>javax.xml.stream.XMLStreamReader </code> that has to be tested
     *  
     * @return    the <code>ADBBeanImplementation</code> corresponding to the name passed as parameter
     * 
     * @throws Exception - if something goes wrong
     */
    protected static ADBBeanImplementation checkADBBeanType (String name, 
                                                            javax.xml.stream.XMLStreamReader reader) 
                                                            throws Exception {
        if (reader.getAttributeValue(XML_SCHEMA_INSTANCE,TYPE)!=null) {
            String fullTypeName = reader.getAttributeValue(XML_SCHEMA_INSTANCE,
                    TYPE);
            if (fullTypeName!=null) {
                String nsPrefix = null;
                if (fullTypeName.indexOf(':') > -1) {
                    nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(':'));
                }
                nsPrefix = nsPrefix==null?"":nsPrefix;
                String type = fullTypeName.substring(fullTypeName.indexOf(':')+1);
                if (!name.equals(type)) {
                    //find namespace for the prefix
                    String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                    return (ADBBeanImplementation)ExtensionMapper.getTypeObject(
                            nsUri,type,reader);
                }
            }
        }
        return null;
    }

    
    /**
     * Util method to add items to the element list
     * 
     * @param localItemsTracker - the <code>boolean</code> value of the tracker for the Items attribute  
     * @param localItems - the Items to be added as <code>Object[]</code>
     * @param namespace - the namespace to use as <code>String</code>
     * @param elementList - the <code>List<Object</code> to be used
     * 
     * @throws ADBException - if something goes wrong adding the Items
     */
    protected void addItems(boolean localItemsTracker,
            Object [] localItems,
            String namespace,
            java.util.List<Object> elementList) 
                    throws ADBException {
        if (localItemsTracker) {
            if (localItems!=null) {
                for (int i = 0;i < localItems.length;i++) {
                    if (localItems[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(OPC_NAMESPACE, namespace));
                        elementList.add(localItems[i]);
                    }                 
                }
            } else {
                throw new org.apache.axis2.databinding.ADBException("Items cannot be null!!");
            }
        }        
    }
    
    
    /**
     * Util method to add items to the element list
     * 
     * @param localItemTracker - the <code>boolean</code> value of the tracker for the Item attribute,
     *                               true has to be written, false not has to be written
     * @param localItem- the <code>Object</code> to be add to the list
     * @param namespace - the namespace to use as <code>String</code>
     * @param elementList - the <code>List<Object</code> to be used
     * 
     * @throws ADBException - if something goes wrong adding the Items
     */    
    protected void addItem(boolean localItemTracker,
            Object localItem,
            String namespace,
            java.util.List<Object> elementList) 
                    throws ADBException {
        if (localItemTracker) {
            elementList.add(new javax.xml.namespace.QName(OPC_NAMESPACE, namespace));
            if (localItem != null) {
                elementList.add(localItem);
            } else {
                throw new org.apache.axis2.databinding.ADBException(namespace+CANNOT_BE_NULL);
            }
        }
    }


    /**
     * Util method used to serialize a set of attributes
     * 
     * @param attrTracker - the <code>boolean</code> value of the tracker for the attributes, 
     *                                     true the attribute has to be written, false has not to be written
     * @param attrValues - attributes to be written as <code>ADBBeanImplementation[]</code>
     * @param attrName - the name to associate to the attributes as <code>String</code>
     * @param factory - the <code>org.apache.axiom.om.OMFactory</code> to be used
     * @param xmlWriter - <code>org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter</code> to be used 
     * 
     * @throws ADBException - if something goes wrong serializing the attributes
     * @throws XMLStreamException - if something goes wrong writing the attribtues
     */
    protected void serializeAttributes(boolean attrTracker,
            ADBBeanImplementation [] attrValues,
            String attrName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
                    throws ADBException, XMLStreamException{
        if (attrTracker) {
            if (attrValues!=null) {
                for (int i = 0;i < attrValues.length;i++) {
                    if (attrValues[i] != null) {
                        attrValues[i].serialize(new javax.xml.namespace.QName(OPC_NAMESPACE,attrName),
                                factory,xmlWriter);
                    }
                }
            } else {
                throw new org.apache.axis2.databinding.ADBException(attrName+CANNOT_BE_NULL);
            }
        }
        xmlWriter.writeEndElement();
    }
    
    /**
     * Util method used to serialize an attribute with a tracker
     * 
     * @param attrTracker -  the <code>boolean</code> value of the tracker for the attribute,
     *                                       true has to be written, false not has to be written
     * @param attrValue - value of the attribute to serialize as <code>ADBBeanImplementation</code>
     * @param attrName - name of the attribute as <code>String</code>
     * @param factory - the <code>org.apache.axiom.om.OMFactory factory</code> to be used
     * @param xmlWriter - <code>org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter</code> to be used 
     *
     * @throws XMLStreamException - if something goes wrong writing the attributes
     */
    protected void serializeAttribute(boolean attrTracker,
            ADBBeanImplementation attrValue,
            String attrName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
                    throws XMLStreamException {
        if (attrTracker) {
            if (attrValue==null) {
                throw new org.apache.axis2.databinding.ADBException(attrName+CANNOT_BE_NULL);
            }
            attrValue.serialize(new javax.xml.namespace.QName(OPC_NAMESPACE,attrName),
                    factory,xmlWriter);
        }
    }
    
    
    /**
     * Adds to the attributes list the items that are common to <code>SubscribeRequestItem</code and <code>ReadRequestItem</code> stubs
     * 
     * @param localItemPath - ItemPath attribute value as <code>String</code
     * @param localReqType - ReqType attribute value as <code>javax.xml.namespace.QName</code
     * @param localItemName - ItemName attribute value as <code>String</code
     * @param localClientItemHandle - ClientItemHandle attribute value as <code>String</code
     * @param attribList - attributes list as <code>ArrayList</code> of <code>Object</code>s
     */
    protected void addRequestItemAttributes(String localItemPath,
                        javax.xml.namespace.QName localReqType,
                        String localItemName,
                        String localClientItemHandle,
                        java.util.List<Object> attribList){
        attribList.add(new javax.xml.namespace.QName("","ItemPath"));
        attribList.add(localItemPath);
        attribList.add(new javax.xml.namespace.QName("","ReqType"));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqType));
        attribList.add(new javax.xml.namespace.QName("","ItemName"));
        attribList.add(localItemName);
        attribList.add(new javax.xml.namespace.QName("","ClientItemHandle"));
        attribList.add(localClientItemHandle);
    }
    
    /**
     * Adds to the attributes list the items that are common to <code>Write/code and <code>Subscribe</code> stubs
     * 
     * @param localOptionsTracker - the tracker for the Options attribute as <code>boolean</code>
     * @param localOptions - Options attribute value as <code>RequestOptions</code>
     * @param localItemListTracker - the tracker for the ItemList attribute as <code>boolean</code>
     * @param localItemList - LocalItemList attribute value as <code>Object</code>
     * @param localReturnValuesOnReply - LocalReturnValuesOnReply attribute value as <code>boolean</code>
     * @param attribList - attributes list as <code>List</code> of <code>Object</code>s
     * @param elementList - elements list as <code>List</code> of <code>Object</code>s
     * 
     * @throws ADBException - if something goes wrong adding an <code>Item</code>
     */
    protected void addSubscribeWriteCommonItems(boolean localOptionsTracker,
                                    RequestOptions localOptions,
                                    boolean localItemListTracker,
                                    Object localItemList,
                                    boolean localReturnValuesOnReply,
                                    java.util.List<Object> attribList,
                                    java.util.List<Object> elementList) throws ADBException {
        addItem(localOptionsTracker,localOptions,"Options",elementList);
        addItem(localItemListTracker,localItemList,"ItemList",elementList);
        attribList.add(new javax.xml.namespace.QName("","ReturnValuesOnReply"));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnValuesOnReply));
    }
    
    
    /**
     * 
     * Adds to the attributes list the items that are common to <code>SubscribeRequestItemList</code and <code>ReadRequestItemList</code> stubs
     * 
     * @param localItemsTracker - the tracker for the Items attribute as <code>boolean</code>
     * @param localItems - the array of the Items as <code>Object[]</code>
     * @param localItemPath - ItemPath attribute value as <code>String</code>
     * @param localReqType - ReqType attribute value as <code>javax.xml.namespace.QName</code>
     * @param attribList - attributes list as <code>List</code> of <code>Object</code>s
     * @param elementList - elements list as <code>List</code> of <code>Object</code>s
     * 
     * @throws ADBException - if something goes wrong adding an <code>Item</code>
     */
    protected void addRequestItemList(boolean localItemsTracker,
            Object[] localItems,
            String localItemPath,
            QName localReqType,
            java.util.List<Object> attribList,
            java.util.List<Object> elementList) throws ADBException {
        addItems(localItemsTracker,localItems,"Items",elementList);
        attribList.add(new javax.xml.namespace.QName("","ItemPath"));
        attribList.add(localItemPath);
        attribList.add(new javax.xml.namespace.QName("","ReqType"));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqType));
    }
    
    
    /**
     * Retrieves the name of the implementation class
     * 
     * @return the name of the implementation class as <code>String</code>
     */
    protected abstract String getClassName();


    /**
     * Util method used to write attributes in xml, this method is abstract and is implemented by the ADBBeanImplementation extensions,
     * each one with its attributes
     * 
     * @param prefix - prefix to be used as <code>String</code>
     * @param namespace - namespace to be used as <code>String</code>
     * @param factory - the <code>org.apache.axiom.om.OMFactory</code> to be used 
     * @param xmlWriter - <code>org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter</code> to be used
     *  
     * @throws ADBException - if something goes wrong serializing the attributes
     * @throws XMLStreamException - if something goes wrong writing the attributes
     * 
     */
    protected abstract void writeAttributes(String prefix,
                                            String namespace, 
                                            org.apache.axiom.om.OMFactory factory, 
                                            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
                                                    throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException;
}
