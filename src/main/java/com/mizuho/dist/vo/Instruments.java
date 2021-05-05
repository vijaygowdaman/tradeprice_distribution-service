package com.mizuho.dist.vo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "instrument"
})
@XmlRootElement(name = "instruments")
public class Instruments {

    protected List<Instruments.Instrument> instrument;

    /**
     * Gets the value of the instrument property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the instrument property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstrument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Instruments.Instrument }
     * 
     * 
     */
    public List<Instruments.Instrument> getInstrument() {
        if (instrument == null) {
            instrument = new ArrayList<Instruments.Instrument>();
        }
        return this.instrument;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "instType",
        "instId",
        "instName",
        "tradeId",
        "vendor",
        "price"
    })
    public static class Instrument {

        @XmlElement(name = "inst_type", required = true)
        protected String instType;
        @XmlElement(name = "inst_id", required = true)
        protected String instId;
        @XmlElement(name = "inst_name", required = true)
        protected String instName;
        @XmlElement(name = "trade_id")
        protected byte tradeId;
        @XmlElement(required = true)
        protected String vendor;
        protected float price;

        /**
         * Gets the value of the instType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInstType() {
            return instType;
        }

        /**
         * Sets the value of the instType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInstType(String value) {
            this.instType = value;
        }

        /**
         * Gets the value of the instId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInstId() {
            return instId;
        }

        /**
         * Sets the value of the instId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInstId(String value) {
            this.instId = value;
        }

        /**
         * Gets the value of the instName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInstName() {
            return instName;
        }

        /**
         * Sets the value of the instName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInstName(String value) {
            this.instName = value;
        }

        /**
         * Gets the value of the tradeId property.
         * 
         */
        public byte getTradeId() {
            return tradeId;
        }

        /**
         * Sets the value of the tradeId property.
         * 
         */
        public void setTradeId(byte value) {
            this.tradeId = value;
        }

        /**
         * Gets the value of the vendor property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVendor() {
            return vendor;
        }

        /**
         * Sets the value of the vendor property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVendor(String value) {
            this.vendor = value;
        }

        /**
         * Gets the value of the price property.
         * 
         */
        public float getPrice() {
            return price;
        }

        /**
         * Sets the value of the price property.
         * 
         */
        public void setPrice(float value) {
            this.price = value;
        }

    }

}
