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
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 *
 * @author quangphuong
 */
public interface EventService {
    List<Event> getEvents() throws Exception;
    EventDetail getEventDetail(String eventLink, boolean isLive);
    String getVideoStream(Video video) throws Exception;
    List<Calendar.Round> getCalendar(String id) throws Exception;
    ByteArrayOutputStream printCalendar(String id);
}
