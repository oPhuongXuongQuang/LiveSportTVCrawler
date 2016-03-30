
package com.quangphuong.crawler.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Event complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Event"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="kind" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="live" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tournament" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="match" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="link" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Event", propOrder = {
    "kind",
    "live",
    "tournament",
    "match",
    "time",
    "link",
    "image"
})
public class Event {

    @XmlElement(required = true)
    protected String kind;
    @XmlElement(required = true)
    protected String live;
    @XmlElement(required = true)
    protected String tournament;
    @XmlElement(required = true)
    protected String match;
    @XmlElement(required = true)
    protected String time;
    @XmlElement(required = true)
    protected String link;
    @XmlElement(required = true)
    protected String image;

    public Event() {
    }

    public Event(String kind, String live, String tournament, String match, String time, String link, String image) {
        this.kind = kind;
        this.live = live;
        this.tournament = tournament;
        this.match = match;
        this.time = time;
        this.link = link;
        this.image = image;
    }

    
    /**
     * Gets the value of the kind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKind() {
        return kind;
    }

    /**
     * Sets the value of the kind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKind(String value) {
        this.kind = value;
    }

    /**
     * Gets the value of the live property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLive() {
        return live;
    }

    /**
     * Sets the value of the live property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLive(String value) {
        this.live = value;
    }

    /**
     * Gets the value of the tournament property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTournament() {
        return tournament;
    }

    /**
     * Sets the value of the tournament property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTournament(String value) {
        this.tournament = value;
    }

    /**
     * Gets the value of the match property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatch() {
        return match;
    }

    /**
     * Sets the value of the match property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatch(String value) {
        this.match = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTime(String value) {
        this.time = value;
    }

    /**
     * Gets the value of the link property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the value of the link property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLink(String value) {
        this.link = value;
    }

    /**
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImage(String value) {
        this.image = value;
    }

}
