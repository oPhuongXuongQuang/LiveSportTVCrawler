
package com.quangphuong.crawler.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="round" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="match" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="link" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="teams" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="logoTeam1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="logoTeam2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="score" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "round"
})
@XmlRootElement(name = "calendar")
public class Calendar {

    protected List<Calendar.Round> round;
    @XmlAttribute(name = "id")
    protected Integer id;

    public Calendar() {
    }

    public Calendar(List<Round> round, Integer id) {
        this.round = round;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the value of the round property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the round property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRound().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Calendar.Round }
     * 
     * 
     */
    public List<Calendar.Round> getRound() {
        if (round == null) {
            round = new ArrayList<Calendar.Round>();
        }
        return this.round;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="match" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="link" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="teams" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="logoTeam1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="logoTeam2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="score" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "match"
    })
    public static class Round {

        protected List<Calendar.Round.Match> match;
        @XmlAttribute(name = "id")
        protected Integer id;

        public Round() {
        }

        public Round(List<Match> match, Integer id) {
            this.match = match;
            this.id = id;
        }

        /**
         * Gets the value of the match property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the match property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMatch().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Calendar.Round.Match }
         * 
         * 
         */
        public List<Calendar.Round.Match> getMatch() {
            if (match == null) {
                match = new ArrayList<Calendar.Round.Match>();
            }
            return this.match;
        }

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setId(Integer value) {
            this.id = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="link" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="teams" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="logoTeam1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="logoTeam2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="score" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "date",
            "link",
            "teams",
            "logoTeam1",
            "logoTeam2",
            "score"
        })
        public static class Match {

            @XmlElement(required = true)
            protected String date;
            @XmlElement(required = true)
            protected String link;
            @XmlElement(required = true)
            protected String teams;
            @XmlElement(required = true)
            protected String logoTeam1;
            @XmlElement(required = true)
            protected String logoTeam2;
            @XmlElement(required = true)
            protected String score;

            public Match() {
            }

            public Match(String date, String link, String teams, String logoTeam1, String logoTeam2, String score) {
                this.date = date;
                this.link = link;
                this.teams = teams;
                this.logoTeam1 = logoTeam1;
                this.logoTeam2 = logoTeam2;
                this.score = score;
            }

            @Override
            public String toString() {
                return this.teams + "-" + this.score + "-" + this.logoTeam1 + "-" + this.logoTeam2 + "-" 
                        + this.link + "-";
            }

            
            /**
             * Gets the value of the date property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDate() {
                return date;
            }

            /**
             * Sets the value of the date property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDate(String value) {
                this.date = value;
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
             * Gets the value of the teams property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTeams() {
                return teams;
            }

            /**
             * Sets the value of the teams property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTeams(String value) {
                this.teams = value;
            }

            /**
             * Gets the value of the logoTeam1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLogoTeam1() {
                return logoTeam1;
            }

            /**
             * Sets the value of the logoTeam1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLogoTeam1(String value) {
                this.logoTeam1 = value;
            }

            /**
             * Gets the value of the logoTeam2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLogoTeam2() {
                return logoTeam2;
            }

            /**
             * Sets the value of the logoTeam2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLogoTeam2(String value) {
                this.logoTeam2 = value;
            }

            /**
             * Gets the value of the score property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getScore() {
                return score;
            }

            /**
             * Sets the value of the score property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setScore(String value) {
                this.score = value;
            }

        }

    }

}
