//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.13 at 11:05:37 AM EDT 
//


package ca.canadapost.cpcdp.rating.generated.discovery;

import java.math.BigDecimal;
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
 *         &lt;element name="service-code" type="{http://www.canadapost.ca/ws/ship/rate-v3}ServiceCodeType"/>
 *         &lt;element name="service-name" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="options" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="option" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;all>
 *                             &lt;element name="option-code" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
 *                             &lt;element name="option-name" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
 *                             &lt;element name="link" type="{http://www.canadapost.ca/ws/ship/rate-v3}LinkType"/>
 *                             &lt;element name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                             &lt;element name="qualifier-required" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                             &lt;element name="qualifier-max" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                           &lt;/all>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="restrictions">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="weight-restriction" type="{http://www.canadapost.ca/ws/ship/rate-v3}NumberRangeType"/>
 *                   &lt;element name="dimensional-restrictions">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;all>
 *                             &lt;element name="length" type="{http://www.canadapost.ca/ws/ship/rate-v3}NumberRangeType"/>
 *                             &lt;element name="width" type="{http://www.canadapost.ca/ws/ship/rate-v3}NumberRangeType"/>
 *                             &lt;element name="height" type="{http://www.canadapost.ca/ws/ship/rate-v3}NumberRangeType"/>
 *                             &lt;element name="length-plus-girth-max" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="length-height-width-sum-max" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="oversize-limit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                           &lt;/all>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="density-factor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="can-ship-in-mailing-tube" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="can-ship-unpackaged" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="allowed-as-return-service" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                 &lt;/all>
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
@XmlRootElement(name = "service")
public class Service {

    @XmlElement(name = "service-code", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String serviceCode;
    @XmlElement(name = "service-name", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String serviceName;
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String comment;
    protected Service.Options options;
    @XmlElement(required = true)
    protected Service.Restrictions restrictions;

    /**
     * Gets the value of the serviceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * Sets the value of the serviceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceCode(String value) {
        this.serviceCode = value;
    }

    /**
     * Gets the value of the serviceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Sets the value of the serviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceName(String value) {
        this.serviceName = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the options property.
     * 
     * @return
     *     possible object is
     *     {@link Service.Options }
     *     
     */
    public Service.Options getOptions() {
        return options;
    }

    /**
     * Sets the value of the options property.
     * 
     * @param value
     *     allowed object is
     *     {@link Service.Options }
     *     
     */
    public void setOptions(Service.Options value) {
        this.options = value;
    }

    /**
     * Gets the value of the restrictions property.
     * 
     * @return
     *     possible object is
     *     {@link Service.Restrictions }
     *     
     */
    public Service.Restrictions getRestrictions() {
        return restrictions;
    }

    /**
     * Sets the value of the restrictions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Service.Restrictions }
     *     
     */
    public void setRestrictions(Service.Restrictions value) {
        this.restrictions = value;
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
     *         &lt;element name="option" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;all>
     *                   &lt;element name="option-code" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
     *                   &lt;element name="option-name" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
     *                   &lt;element name="link" type="{http://www.canadapost.ca/ws/ship/rate-v3}LinkType"/>
     *                   &lt;element name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *                   &lt;element name="qualifier-required" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *                   &lt;element name="qualifier-max" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                 &lt;/all>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "options"
    })
    public static class Options {

        @XmlElement(name = "option", required = true)
        protected List<Service.Options.Option> options;

        /**
         * Gets the value of the options property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the options property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOptions().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Service.Options.Option }
         * 
         * 
         */
        public List<Service.Options.Option> getOptions() {
            if (options == null) {
                options = new ArrayList<Service.Options.Option>();
            }
            return this.options;
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
         *       &lt;all>
         *         &lt;element name="option-code" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
         *         &lt;element name="option-name" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
         *         &lt;element name="link" type="{http://www.canadapost.ca/ws/ship/rate-v3}LinkType"/>
         *         &lt;element name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
         *         &lt;element name="qualifier-required" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
         *         &lt;element name="qualifier-max" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
        public static class Option {

            @XmlElement(name = "option-code", required = true)
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String optionCode;
            @XmlElement(name = "option-name", required = true)
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String optionName;
            @XmlElement(required = true)
            protected Link link;
            protected boolean mandatory;
            @XmlElement(name = "qualifier-required")
            protected boolean qualifierRequired;
            @XmlElement(name = "qualifier-max")
            protected BigDecimal qualifierMax;

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
             * Gets the value of the link property.
             * 
             * @return
             *     possible object is
             *     {@link Link }
             *     
             */
            public Link getLink() {
                return link;
            }

            /**
             * Sets the value of the link property.
             * 
             * @param value
             *     allowed object is
             *     {@link Link }
             *     
             */
            public void setLink(Link value) {
                this.link = value;
            }

            /**
             * Gets the value of the mandatory property.
             * 
             */
            public boolean isMandatory() {
                return mandatory;
            }

            /**
             * Sets the value of the mandatory property.
             * 
             */
            public void setMandatory(boolean value) {
                this.mandatory = value;
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
             * Gets the value of the qualifierMax property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getQualifierMax() {
                return qualifierMax;
            }

            /**
             * Sets the value of the qualifierMax property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setQualifierMax(BigDecimal value) {
                this.qualifierMax = value;
            }

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
     *       &lt;all>
     *         &lt;element name="weight-restriction" type="{http://www.canadapost.ca/ws/ship/rate-v3}NumberRangeType"/>
     *         &lt;element name="dimensional-restrictions">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;all>
     *                   &lt;element name="length" type="{http://www.canadapost.ca/ws/ship/rate-v3}NumberRangeType"/>
     *                   &lt;element name="width" type="{http://www.canadapost.ca/ws/ship/rate-v3}NumberRangeType"/>
     *                   &lt;element name="height" type="{http://www.canadapost.ca/ws/ship/rate-v3}NumberRangeType"/>
     *                   &lt;element name="length-plus-girth-max" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="length-height-width-sum-max" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="oversize-limit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                 &lt;/all>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="density-factor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="can-ship-in-mailing-tube" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="can-ship-unpackaged" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="allowed-as-return-service" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    public static class Restrictions {

        @XmlElement(name = "weight-restriction", required = true)
        protected NumberRangeType weightRestriction;
        @XmlElement(name = "dimensional-restrictions", required = true)
        protected Service.Restrictions.DimensionalRestrictions dimensionalRestrictions;
        @XmlElement(name = "density-factor")
        protected BigDecimal densityFactor;
        @XmlElement(name = "can-ship-in-mailing-tube")
        protected boolean canShipInMailingTube;
        @XmlElement(name = "can-ship-unpackaged")
        protected boolean canShipUnpackaged;
        @XmlElement(name = "allowed-as-return-service")
        protected boolean allowedAsReturnService;

        /**
         * Gets the value of the weightRestriction property.
         * 
         * @return
         *     possible object is
         *     {@link NumberRangeType }
         *     
         */
        public NumberRangeType getWeightRestriction() {
            return weightRestriction;
        }

        /**
         * Sets the value of the weightRestriction property.
         * 
         * @param value
         *     allowed object is
         *     {@link NumberRangeType }
         *     
         */
        public void setWeightRestriction(NumberRangeType value) {
            this.weightRestriction = value;
        }

        /**
         * Gets the value of the dimensionalRestrictions property.
         * 
         * @return
         *     possible object is
         *     {@link Service.Restrictions.DimensionalRestrictions }
         *     
         */
        public Service.Restrictions.DimensionalRestrictions getDimensionalRestrictions() {
            return dimensionalRestrictions;
        }

        /**
         * Sets the value of the dimensionalRestrictions property.
         * 
         * @param value
         *     allowed object is
         *     {@link Service.Restrictions.DimensionalRestrictions }
         *     
         */
        public void setDimensionalRestrictions(Service.Restrictions.DimensionalRestrictions value) {
            this.dimensionalRestrictions = value;
        }

        /**
         * Gets the value of the densityFactor property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getDensityFactor() {
            return densityFactor;
        }

        /**
         * Sets the value of the densityFactor property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setDensityFactor(BigDecimal value) {
            this.densityFactor = value;
        }

        /**
         * Gets the value of the canShipInMailingTube property.
         * 
         */
        public boolean isCanShipInMailingTube() {
            return canShipInMailingTube;
        }

        /**
         * Sets the value of the canShipInMailingTube property.
         * 
         */
        public void setCanShipInMailingTube(boolean value) {
            this.canShipInMailingTube = value;
        }

        /**
         * Gets the value of the canShipUnpackaged property.
         * 
         */
        public boolean isCanShipUnpackaged() {
            return canShipUnpackaged;
        }

        /**
         * Sets the value of the canShipUnpackaged property.
         * 
         */
        public void setCanShipUnpackaged(boolean value) {
            this.canShipUnpackaged = value;
        }

        /**
         * Gets the value of the allowedAsReturnService property.
         * 
         */
        public boolean isAllowedAsReturnService() {
            return allowedAsReturnService;
        }

        /**
         * Sets the value of the allowedAsReturnService property.
         * 
         */
        public void setAllowedAsReturnService(boolean value) {
            this.allowedAsReturnService = value;
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
         *       &lt;all>
         *         &lt;element name="length" type="{http://www.canadapost.ca/ws/ship/rate-v3}NumberRangeType"/>
         *         &lt;element name="width" type="{http://www.canadapost.ca/ws/ship/rate-v3}NumberRangeType"/>
         *         &lt;element name="height" type="{http://www.canadapost.ca/ws/ship/rate-v3}NumberRangeType"/>
         *         &lt;element name="length-plus-girth-max" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="length-height-width-sum-max" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="oversize-limit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
        public static class DimensionalRestrictions {

            @XmlElement(required = true)
            protected NumberRangeType length;
            @XmlElement(required = true)
            protected NumberRangeType width;
            @XmlElement(required = true)
            protected NumberRangeType height;
            @XmlElement(name = "length-plus-girth-max")
            protected BigDecimal lengthPlusGirthMax;
            @XmlElement(name = "length-height-width-sum-max")
            protected BigDecimal lengthHeightWidthSumMax;
            @XmlElement(name = "oversize-limit")
            protected BigDecimal oversizeLimit;

            /**
             * Gets the value of the length property.
             * 
             * @return
             *     possible object is
             *     {@link NumberRangeType }
             *     
             */
            public NumberRangeType getLength() {
                return length;
            }

            /**
             * Sets the value of the length property.
             * 
             * @param value
             *     allowed object is
             *     {@link NumberRangeType }
             *     
             */
            public void setLength(NumberRangeType value) {
                this.length = value;
            }

            /**
             * Gets the value of the width property.
             * 
             * @return
             *     possible object is
             *     {@link NumberRangeType }
             *     
             */
            public NumberRangeType getWidth() {
                return width;
            }

            /**
             * Sets the value of the width property.
             * 
             * @param value
             *     allowed object is
             *     {@link NumberRangeType }
             *     
             */
            public void setWidth(NumberRangeType value) {
                this.width = value;
            }

            /**
             * Gets the value of the height property.
             * 
             * @return
             *     possible object is
             *     {@link NumberRangeType }
             *     
             */
            public NumberRangeType getHeight() {
                return height;
            }

            /**
             * Sets the value of the height property.
             * 
             * @param value
             *     allowed object is
             *     {@link NumberRangeType }
             *     
             */
            public void setHeight(NumberRangeType value) {
                this.height = value;
            }

            /**
             * Gets the value of the lengthPlusGirthMax property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getLengthPlusGirthMax() {
                return lengthPlusGirthMax;
            }

            /**
             * Sets the value of the lengthPlusGirthMax property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setLengthPlusGirthMax(BigDecimal value) {
                this.lengthPlusGirthMax = value;
            }

            /**
             * Gets the value of the lengthHeightWidthSumMax property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getLengthHeightWidthSumMax() {
                return lengthHeightWidthSumMax;
            }

            /**
             * Sets the value of the lengthHeightWidthSumMax property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setLengthHeightWidthSumMax(BigDecimal value) {
                this.lengthHeightWidthSumMax = value;
            }

            /**
             * Gets the value of the oversizeLimit property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getOversizeLimit() {
                return oversizeLimit;
            }

            /**
             * Sets the value of the oversizeLimit property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setOversizeLimit(BigDecimal value) {
                this.oversizeLimit = value;
            }

        }

    }

}
