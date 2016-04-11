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
public class Link {
    private String value;
    private boolean live;

    public Link() {
    }

    public Link(String value, boolean live) {
        this.value = value;
        this.live = live;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
    
}
