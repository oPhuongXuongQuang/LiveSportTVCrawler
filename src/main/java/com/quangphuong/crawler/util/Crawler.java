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
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.quangphuong.crawler.dto.Calendar;
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
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//        ProxyConfig proxyConfig = new ProxyConfig("10.88.16.183", 8080);
//        webClient.getOptions().setProxyConfig(proxyConfig); 
    }

//    public static void main(String[] args) {
//        try {
//////            List<Event> events = comingupCrawler();
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
    public void schedule1() throws FileNotFoundException {
        List<Event> events = comingupCrawler();
        Events events1 = new Events(events);
        XMLUtil.marshallUtil(AppConstant.comingUpData, events1);
    }

    public static List<Event> comingupCrawler() {
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
    
//    @Scheduled(fixedDelay = 604800000)
//    public static void schedule2() {
//        WebClient client = webClient;
//        int[] calType = {1, 10, 11, 22, 36, 37, 93};
//        for (int k : calType) {
//            try {
//                HtmlPage page = client.getPage(AppConstant.calendarPage + k);
//                List<DomElement> els = (List<DomElement>) page.getByXPath(AppConstant.rounds);
//
//                List<Calendar.Round> rounds = new ArrayList<Calendar.Round>();
//                for (int i = 1; i < els.size() - 3; i++) {
//
//                    DomElement tmp = els.get(i);
//                    //                String round = tmp.getTextContent().replace("(Video)", "").trim();
//                    //                System.out.println("Round: " + round);
//                    DomNode parent = tmp.getParentNode().getParentNode().getParentNode().getParentNode();
////                    System.out.println("-----" + parent.asXml());
//
//                    List<DomElement> trs = (List<DomElement>) parent.getByXPath("tbody/tr");
//                    System.out.println("trs: " + trs.size());
//                    String date = "";
//                    List<Calendar.Round.Match> matches = new ArrayList<Calendar.Round.Match>();
//                    for (int j = 1; j < trs.size() - 1; j++) {
//                        DomElement tr = trs.get(j);
//                        HtmlElement tmp2 = tr.getFirstByXPath("td");
//                        if (tmp2.hasAttribute("bgcolor")) {
//                            date = tr.getTextContent().trim();
//                            System.out.println("Date: " + date);
//                        } else {
//                            try {
//                                DomElement td = tr.getFirstByXPath("td[1]/a");
//                                if(td == null) {
//                                    td = tr.getFirstByXPath("td[1]");
//                                }
//                                String teams = td.getTextContent().trim();
//                                System.out.println("Teams: " + teams);
//                                String link = td.getAttribute("href");
//                                td = tr.getFirstByXPath("td[2]/a/img");
//                                String logoTeam1 = td.getAttribute("src");
//                                td = tr.getFirstByXPath("td[3]");
//                                String score = td.getTextContent().trim();
//                                td = tr.getFirstByXPath("td[4]/a/img");
//                                String logoTeam2 = td.getAttribute("src");
//
//                                Calendar.Round.Match match = new Calendar.Round.Match(date, link, teams, logoTeam1, logoTeam2, score);
//                                match.toString();
//                                matches.add(match);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                    rounds.add(new Calendar.Round(matches, i));
//                }
//                Calendar calendar = new Calendar(rounds, k);
//                XMLUtil.marshallUtil(intToCalendar(k), calendar);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                client.close();
//            }
//        }
//    }
//
//    public static String intToCalendar(int k) {
//        switch (k) {
//            case 1:
//                return AppConstant.EnglandCalendar;
//            case 10:
//                return AppConstant.SpainCalendar;
//            case 11:
//                return AppConstant.ItalyCalendar;
//            case 22:
//                return AppConstant.DutchCalendar;
//            case 36:
//                return AppConstant.GermanCalendar;
//            case 37:
//                return AppConstant.FranceCalendar;
//            case 93:
//                return AppConstant.BrazilCalendar;
//        }
//        return "";
//    }
}
