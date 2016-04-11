/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.util;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.quangphuong.crawler.dto.Event;
import com.quangphuong.crawler.dto.Events;
import java.math.BigDecimal;

/**
 *
 * @author quangphuong
 */
@Configuration
@EnableScheduling
public class Crawler {
    private static final WebClient webClient = new WebClient(BrowserVersion.CHROME);

    public Crawler() {
        webClient.getOptions().setJavaScriptEnabled(false);
    }

//    public static void main(String[] args) {
//        try {
//////            List<Event> events = scrapping();
//////            Events events1 = new Events(events);
//////            XMLUtil.marshallUtil(AppConstant.comingUpData, events1);
////
////            Events events = XMLUtil.unmarshallUtil(AppConstant.comingUpData, Events.class);
////
////            List<Event> listEvent = events.getEvent();
////            for (Event event : listEvent) {
////                System.out.println("Match: " + event.getMatch());
////            }
//            getEventDetail(AppConstant.eventDemo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @Scheduled(fixedDelay = 60000)
    public void run() throws FileNotFoundException {
        List<Event> events = scrapping();
        Events events1 = new Events(events);
        XMLUtil.marshallUtil(AppConstant.comingUpData, events1);
//        Links links = new Links(listLinks);
//        XMLUtil.marshallUtil(AppConstant.saveLinks, links);
    }

    public static List<Event> scrapping() {
        List<Event> events = new ArrayList();
        WebClient client = webClient;
        try {
            HtmlPage page = client.getPage(AppConstant.comingUpPage);
            List<HtmlElement> tds = new ArrayList();
            tds = (List<HtmlElement>) page.getByXPath(AppConstant.comingUpEventColumn);
//            listLinks = new ArrayList<String>();
            for (int i = 0; i < tds.size(); i++) {
                List<HtmlElement> tables = (List<HtmlElement>) tds.get(i).getByXPath("table");
                for (int j = 0; j < tables.size(); j = j + 2) {
                    HtmlElement kindTable = tables.get(j);
                    HtmlElement detailTable = tables.get(j + 1);
                    HtmlElement kindtext = (HtmlElement) kindTable.getFirstByXPath(AppConstant.comingUpEventKind);
                    String kind = kindtext.asText();

                    List<HtmlElement> matches = (List<HtmlElement>) detailTable.getByXPath(AppConstant.comingUpSameKindEvents);
                    for (int k = 0; k < matches.size(); k++) {
                        HtmlElement matchtext = (HtmlElement) matches.get(k).getFirstByXPath(AppConstant.comingUpEventMatch);
                        HtmlElement liveText = (HtmlElement) matches.get(k).getFirstByXPath(AppConstant.comingUpEventLive);
                        DomText timeText = (DomText) matches.get(k).getFirstByXPath(AppConstant.comingUpEventTime);
                        HtmlElement tournamentText = (HtmlElement) matches.get(k).getFirstByXPath(AppConstant.comingUpEventTournament);
                        HtmlElement img = (HtmlElement) matches.get(k).getFirstByXPath(AppConstant.comingUpEventImage);
                        try {
                            if (tournamentText != null) {
                                String tournament = tournamentText.asText().replace(timeText.asText(), "").replaceAll("\\(", "").replaceAll("\\)", "").trim();
                                String match = matchtext.asText();
                                String time = timeText.asText();
                                String link = AppConstant.prefix + matchtext.getAttribute("href");
                                String image = img.getAttribute("src");
                                String live = "";
                                if (liveText != null) {
                                    live = liveText.getAttribute("src");
                                }
//                                listLinks.add(link);
                                Event event = new Event(kind, live, tournament.replace("\n", ""), match, time, link, image);
                                events.add(event);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
            }
            System.out.println("-------------------");
            System.out.println(" web Client Memory size: " + Agent.sizeOf(webClient));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
        return events;
    }
}
