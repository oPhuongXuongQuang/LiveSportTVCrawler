package com.quangphuong.crawler.util;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Component;

import com.quangphuong.crawler.dto.Event;
import com.quangphuong.crawler.dto.Events;

@Component
public class Getter {

    public static List<Event> getEvents() {
        Events events = XMLUtil.unmarshallUtil(AppConstant.comingUpData, Events.class);
        return events.getEvent();
    }
}
