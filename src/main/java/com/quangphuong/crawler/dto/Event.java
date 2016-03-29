/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author quangphuong
 */
@XStreamAlias("event")
public class Event {
    @XStreamAlias("kind")
    private String kind;
    @XStreamAlias("live")
    private String live;
    @XStreamAlias("tournament")
    private String tournament;
    @XStreamAlias("match")
    private String match;
    @XStreamAlias("time")
    private String time;
    @XStreamAlias("link")
    private String link;
    @XStreamAlias("image")
    private String image;
    
    public Event(){}

    public Event(String kind, String live, String tournament, String match, String time, String link, String image) {
		super();
		this.kind = kind;
		this.live = live;
		this.tournament = tournament;
		this.match = match;
		this.time = time;
		this.link = link;
		this.image = image;
	}

	public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

	public String getLive() {
		return live;
	}

	public void setLive(String live) {
		this.live = live;
	}
    
    
}
