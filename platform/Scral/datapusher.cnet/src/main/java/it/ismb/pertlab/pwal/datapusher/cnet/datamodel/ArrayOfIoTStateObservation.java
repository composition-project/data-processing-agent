//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.16 at 10:22:49 AM CEST 
//


package it.ismb.pertlab.pwal.datapusher.cnet.datamodel;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://linksmart.org/IoTEntity/1.0}IoTStateObservation" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ioTStateObservation"
})
@XmlRootElement(name = "ArrayOfIoTStateObservation")
public class ArrayOfIoTStateObservation {

    @XmlElement(name = "IoTStateObservation")
    protected List<IoTStateObservation> ioTStateObservation;

    /**
     * Gets the value of the ioTStateObservation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ioTStateObservation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIoTStateObservation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IoTStateObservation }
     * 
     * 
     */
    public List<IoTStateObservation> getIoTStateObservation() {
        if (ioTStateObservation == null) {
            ioTStateObservation = new ArrayList<IoTStateObservation>();
        }
        return this.ioTStateObservation;
    }

}
