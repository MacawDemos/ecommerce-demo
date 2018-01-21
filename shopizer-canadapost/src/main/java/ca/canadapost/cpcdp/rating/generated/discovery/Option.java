//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.13 at 11:05:37 AM EDT 
//


package ca.canadapost.cpcdp.rating.generated.discovery;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="option-code" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
 *         &lt;element name="option-name" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
 *         &lt;element name="option-class" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
 *         &lt;element name="prints-on-label" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="qualifier-required" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="conflicting-options" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="option-code" type="{http://www.w3.org/2001/XMLSchema}normalizedString" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="prerequisite-options" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="option-code" type="{http://www.w3.org/2001/XMLSchema}normalizedString" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "option")
public class Option {

    @XmlElement(name = "option-code", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String optionCode;
    @XmlElement(name = "option-name", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String optionName;
    @XmlElement(name = "option-class", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String optionClass;
    @XmlElement(name = "prints-on-label")
    protected boolean printsOnLabel;
    @XmlElement(name = "qualifier-required")
    protected boolean qualifierRequired;
    @XmlElement(name = "conflicting-options")
    protected Option.ConflictingOptions conflictingOptions;
    @XmlElement(name = "prerequisite-options")
    protected Option.PrerequisiteOptions prerequisiteOptions;

    /**
     * Gets the value of the optionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptionCode() {
        return optionCode;
    }

    /**
     * Sets the value of the optionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptionCode(String value) {
        this.optionCode = value;
    }

    /**
     * Gets the value of the optionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptionName() {
        return optionName;
    }

    /**
     * Sets the value of the optionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptionName(String value) {
        this.optionName = value;
    }

    /**
     * Gets the value of the optionClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptionClass() {
        return optionClass;
    }

    /**
     * Sets the value of the optionClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptionClass(String value) {
        this.optionClass = value;
    }

    /**
     * Gets the value of the printsOnLabel property.
     * 
     */
    public boolean isPrintsOnLabel() {
        return printsOnLabel;
    }

    /**
     * Sets the value of the printsOnLabel property.
     * 
     */
    public void setPrintsOnLabel(boolean value) {
        this.printsOnLabel = value;
    }

    /**
     * Gets the value of the qualifierRequired property.
     * 
     */
    public boolean isQualifierRequired() {
        return qualifierRequired;
    }

    /**
     * Sets the value of the qualifierRequired property.
     * 
     */
    public void setQualifierRequired(boolean value) {
        this.qualifierRequired = value;
    }

    /**
     * Gets the value of the conflictingOptions property.
     * 
     * @return
     *     possible object is
     *     {@link Option.ConflictingOptions }
     *     
     */
    public Option.ConflictingOptions getConflictingOptions() {
        return conflictingOptions;
    }

    /**
     * Sets the value of the conflictingOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Option.ConflictingOptions }
     *     
     */
    public void setConflictingOptions(Option.ConflictingOptions value) {
        this.conflictingOptions = value;
    }

    /**
     * Gets the value of the prerequisiteOptions property.
     * 
     * @return
     *     possible object is
     *     {@link Option.PrerequisiteOptions }
     *     
     */
    public Option.PrerequisiteOptions getPrerequisiteOptions() {
        return prerequisiteOptions;
    }

    /**
     * Sets the value of the prerequisiteOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Option.PrerequisiteOptions }
     *     
     */
    public void setPrerequisiteOptions(Option.PrerequisiteOptions value) {
        this.prerequisiteOptions = value;
    }


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
     *         &lt;element name="option-code" type="{http://www.w3.org/2001/XMLSchema}normalizedString" maxOccurs="unbounded"/>
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
        "optionCodes"
    })
    public static class ConflictingOptions {

        @XmlElement(name = "option-code", required = true)
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        @XmlSchemaType(name = "normalizedString")
        protected List<String> optionCodes;

        /**
         * Gets the value of the optionCodes property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the optionCodes property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOptionCodes().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getOptionCodes() {
            if (optionCodes == null) {
                optionCodes = new ArrayList<String>();
            }
            return this.optionCodes;
        }

    }


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
     *         &lt;element name="option-code" type="{http://www.w3.org/2001/XMLSchema}normalizedString" maxOccurs="unbounded"/>
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
        "optionCodes"
    })
    public static class PrerequisiteOptions {

        @XmlElement(name = "option-code", required = true)
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        @XmlSchemaType(name = "normalizedString")
        protected List<String> optionCodes;

        /**
         * Gets the value of the optionCodes property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the optionCodes property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOptionCodes().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getOptionCodes() {
            if (optionCodes == null) {
                optionCodes = new ArrayList<String>();
            }
            return this.optionCodes;
        }

    }

}
