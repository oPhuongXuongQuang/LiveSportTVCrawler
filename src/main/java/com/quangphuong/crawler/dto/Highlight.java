/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.dto;

import com.quangphuong.crawler.dbutil.AutoIncrement;
import com.quangphuong.crawler.dbutil.FullTextIndex;
import com.quangphuong.crawler.dbutil.Id;
import com.quangphuong.crawler.dbutil.Ignore;
import com.quangphuong.crawler.dbutil.Mark;
import java.io.Serializable;

/**
 *
 * @author quangphuong
 */
public class Highlight implements Serializable{
    @Id
    @AutoIncrement
    private int id;
    @FullTextIndex
    private String kind;
    @FullTextIndex
    private String tournament;
    @Mark
    @FullTextIndex
    private String matches;
    private String logoTeam1;
    private String logoTeam2;
    private String highlightLink;
    private String longHighlightLink;
    private String fullmatchLink;
    private String score;
    @Mark
    @FullTextIndex
    private String date;
    private String time;
    @Ignore
    public double rank;

    public Highlight() {
    }
    
    public Highlight(String date) {
        this.date = date;
    }

    public Highlight(int id, String kind, String tournament, String matches, String logoTeam1, String logoTeam2, String highlightLink, String longHighlightLink, String fullmatchLink, String score, String date, String time, double rank) {
        this.id = id;
        this.kind = kind;
        this.tournament = tournament;
        this.matches = matches;
        this.logoTeam1 = logoTeam1;
        this.logoTeam2 = logoTeam2;
        this.highlightLink = highlightLink;
        this.longHighlightLink = longHighlightLink;
        this.fullmatchLink = fullmatchLink;
        this.score = score;
        this.date = date;
        this.time = time;
        this.rank = rank;
    }
    
    public Highlight(int id, String kind, String tournament, String matches, String logoTeam1, String logoTeam2, String highlightLink, String longHighlightLink, String fullmatchLink, String score, String date, String time) {
        this.id = id;
        this.kind = kind;
        this.tournament = tournament;
        this.matches = matches;
        this.logoTeam1 = logoTeam1;
        this.logoTeam2 = logoTeam2;
        this.highlightLink = highlightLink;
        this.longHighlightLink = longHighlightLink;
        this.fullmatchLink = fullmatchLink;
        this.score = score;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMatches() {
        return matches;
    }

    public void setMatches(String matches) {
        this.matches = matches;
    }

    public String getLogoTeam1() {
        return logoTeam1;
    }

    public void setLogoTeam1(String logoTeam1) {
        this.logoTeam1 = logoTeam1;
    }

    public String getLogoTeam2() {
        return logoTeam2;
    }

    public void setLogoTeam2(String logoTeam2) {
        this.logoTeam2 = logoTeam2;
    }

    public String getHighlightLink() {
        return highlightLink;
    }

    public void setHighlightLink(String highlightLink) {
        this.highlightLink = highlightLink;
    }

    public String getLongHighlightLink() {
        return longHighlightLink;
    }

    public void setLongHighlightLink(String longHighlightLink) {
        this.longHighlightLink = longHighlightLink;
    }

    public String getFullmatchLink() {
        return fullmatchLink;
    }

    public void setFullmatchLink(String fullmatchLink) {
        this.fullmatchLink = fullmatchLink;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
}
