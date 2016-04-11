/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.dto;

/**
 *
 * @author quangphuong
 */
public class Video {
    private String link;
    private String kind;
    private String bitRate;

    public Video() {
    }

    public Video(String link, String kind, String bitRate) {
        this.link = link;
        this.kind = kind;
        this.bitRate = bitRate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getBitRate() {
        return bitRate;
    }

    public void setBitRate(String bitRate) {
        this.bitRate = bitRate;
    }

    @Override
    public String toString() {
        return link + "-" + kind + "-" + bitRate; //To change body of generated methods, choose Tools | Templates.
    }
    
}
