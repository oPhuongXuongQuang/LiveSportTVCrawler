/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.service;

import com.quangphuong.crawler.dto.Calendar;
import com.quangphuong.crawler.dto.Event;
import com.quangphuong.crawler.dto.EventDetail;
import com.quangphuong.crawler.dto.Video;
import com.quangphuong.crawler.util.AppConstant;
import com.quangphuong.crawler.util.Crawler;
import com.quangphuong.crawler.util.Getter;
import com.quangphuong.crawler.util.XMLUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author quangphuong
 */
@Service
public class EventServiceImpl implements EventService{

    
    @Override
    public List<Event> getEvents() throws Exception {
        return Getter.getEvents();
    }

    @Override
    public EventDetail getEventDetail(String eventLink, boolean isLive) {
        return Getter.getEventDetail(eventLink, isLive);
    }

    @Override
    public String getVideoStream(Video video) throws Exception {
        return Getter.getVideoStream(video);
    }

    @Override
    public List<Calendar.Round> getCalendar(String id) throws Exception {
        String calendar = idToCalendar(id);
        return Getter.getCalendar(calendar);
    }
    
    public String idToCalendar(String id) {
        switch (id) {
            case "england":
                return AppConstant.EnglandCalendar;
            case "spain":
                return AppConstant.SpainCalendar;
            case "italy":
                return AppConstant.ItalyCalendar;
            case "dutch":
                return AppConstant.DutchCalendar;
            case "german":
                return AppConstant.GermanCalendar;
            case "france":
                return AppConstant.FranceCalendar;
            case "brazil":
                return AppConstant.BrazilCalendar;
        }
        return "";
    }

    @Override
    public ByteArrayOutputStream printCalendar(String id) {
        try {
            String calendar = idToCalendar(id);
//            String filename = String.format("%s.%s",RandomStringUtils.randomAlphanumeric(10),"pdf");
            ByteArrayOutputStream out = XMLUtil.convertToPDF(calendar, AppConstant.calendarXSL, "");
            return out;
        } catch (IOException ex) {
            Logger.getLogger(EventServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
