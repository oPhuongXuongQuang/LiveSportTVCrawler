/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.dto;

import java.util.List;

/**
 *
 * @author quangphuong
 */
public class EventDetail {
    private String link;
    private List<Video> videos;
    private Team team1;
    private Team team2;
    private String additionInfo1;
    private String additionInfo2;

    public EventDetail() {
    }

    public EventDetail(String link, List<Video> videos, Team team1, Team team2, String additionInfo1, String additionInfo2) {
        this.link = link;
        this.videos = videos;
        this.team1 = team1;
        this.team2 = team2;
        this.additionInfo1 = additionInfo1;
        this.additionInfo2 = additionInfo2;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public String getAdditionInfo1() {
        return additionInfo1;
    }

    public void setAdditionInfo1(String additionInfo1) {
        this.additionInfo1 = additionInfo1;
    }

    public String getAdditionInfo2() {
        return additionInfo2;
    }

    public void setAdditionInfo2(String additionInfo2) {
        this.additionInfo2 = additionInfo2;
    }

    
    
    
}
