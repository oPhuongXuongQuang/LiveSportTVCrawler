package com.quangphuong.crawler.util;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Component;

import com.quangphuong.crawler.dto.Event;
import com.quangphuong.crawler.dto.Events;
import com.thoughtworks.xstream.XStream;

@Component
public class Getter {
	public static List<Event> getEvents(){
        File file = new File(AppConstant.comingUpData);
        XStream xStream = new XStream();
        xStream.processAnnotations(Events.class);
        xStream.processAnnotations(Event.class);
        Events events = new Events();
        try {
            events = (Events) xStream.fromXML(file);
        } catch (Exception e){
            e.printStackTrace();
        }
        return events.getEvent();
    }
}
