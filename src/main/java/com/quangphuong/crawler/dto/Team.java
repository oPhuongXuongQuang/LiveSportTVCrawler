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
public class Team {
    private String name;
    private String image;
    private String link;

    public Team() {
    }

    public Team(String name, String image, String link) {
        this.name = name;
        this.image = image;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return name + "-" + "-" + link + "-" + image; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
