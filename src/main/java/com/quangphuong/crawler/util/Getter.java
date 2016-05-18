package com.quangphuong.crawler.util;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.quangphuong.crawler.dto.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.quangphuong.crawler.dto.Event;
import com.quangphuong.crawler.dto.EventDetail;
import com.quangphuong.crawler.dto.Events;
import com.quangphuong.crawler.dto.Team;
import com.quangphuong.crawler.dto.Video;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Getter {

    public Getter() {

    }

    public static List<Event> getEvents() {
        Events events = XMLUtil.unmarshallUtil(AppConstant.comingUpData, Events.class);
        return events.getEvent();
    }

    public static List<Calendar.Round> getCalendar(String calendar) {
        Calendar calendar1 = XMLUtil.unmarshallUtil(calendar, Calendar.class);
        return calendar1.getRound();
    }

    public static EventDetail getEventDetail(String eventLink, boolean isLive) {
        EventDetail eventDetail = new EventDetail();
        eventDetail.setLink(eventLink);
        List<Video> videos = new ArrayList<Video>();
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        ProxyConfig proxyConfig = new ProxyConfig("10.88.16.183", 8080);
        webClient.getOptions().setProxyConfig(proxyConfig); 
        try {
            HtmlPage page = webClient.getPage(eventLink);

            getEventTeamInfo(page, eventDetail);
            if (isLive) {
                // Get Videos
                List<HtmlElement> tables = (List<HtmlElement>) page.getByXPath(AppConstant.eventVideoTables);
                int max = 1;
                if (tables.size() >= 4 && tables.size() < 6) {
                    max = 2;
                }
                if (tables.size() >= 6) {
                    max = 3;
                }
                for (int k = 0; k < max; k++) {
                    String tbl = "";
                    switch (k) {
                        case 0:
                            tbl = AppConstant.eventVideoRows;
                            break;
                        case 1:
                            tbl = AppConstant.eventVideoSopcastRows;
                            break;
                        case 2:
                            tbl = AppConstant.eventVideoAceRows;
                            break;
                    }
                    List<HtmlElement> videoRows = (List<HtmlElement>) page.getByXPath(tbl);
                    for (int i = 1; i < videoRows.size(); i++) {
                        System.out.println("videoRow: " + i);
                        HtmlElement row = videoRows.get(i);
                        for (int j = 1; j <= 2; j++) {
                            System.out.println("j: " + j);
                            System.out.println("td[" + j + "]" + AppConstant.eventVideoLink);
                            HtmlElement ref = row.getFirstByXPath("td[" + j + "]" + AppConstant.eventVideoLink);
                            if (ref == null) {
                                break;
                            }
                            String link = ref.getAttribute("href");
                            HtmlElement birateEl = row.getFirstByXPath("td[" + j + "]" + AppConstant.eventVideoBitRate);
                            String birate = birateEl.getTextContent();
                            String kindAttr = ref.getAttribute("onclick");
                            String kind;
                            try {
                                kind = kindAttr.split("[\\(\\)]")[1].split("\\,")[0];
                            } catch (Exception ex) {
                                kind = "";
                            }

                            Video video;
                            if (link != null) {
                                video = new Video(link, kind, birate);
                                videos.add(video);
                                System.out.println(video);
                            }
                        }
                    }
                    eventDetail.setVideos(videos);
                }
            }
            System.out.println(" request web Client Memory size: " + Agent.sizeOf(webClient));
            System.out.println(" request page Memory size: " + Agent.sizeOf(page));
        } catch (Exception ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            webClient.close();
        }
        return eventDetail;
    }

    public static EventDetail getEventTeamInfo(HtmlPage page, EventDetail eventDetail) {
        // Get Team info
        for (int i = 1; i <= 2; i++) {
            System.out.println("team nth: " + i);
            HtmlElement team1 = (HtmlElement) page.getFirstByXPath(AppConstant.eventTeamColumns + "[" + i + "]");
            System.out.println("teammmmmmm: + " + team1.asXml());
            System.out.println(AppConstant.eventTeamColumns + "[" + i + "]");
            HtmlElement linkSection = team1.getFirstByXPath("a");
            Team team = new Team();
            if (linkSection != null) {
                HtmlElement imageSection = team1.getFirstByXPath("a/img");
                String image = imageSection.getAttribute("src");
                String name = imageSection.getAttribute("alt");
                String link = AppConstant.prefix + linkSection.getAttribute("href");
                team = new Team(name, image, link);
                System.out.println(team);
                if (i == 1) {
                    eventDetail.setTeam1(team);
                } else {
                    eventDetail.setTeam2(team);
                }
            }
        }
        return eventDetail;
    }

    public static List<String> getAdditionInfo(String eventLink) {
        // get addition info
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        ProxyConfig proxyConfig = new ProxyConfig("10.88.16.183", 8080);
        webClient.getOptions().setProxyConfig(proxyConfig);
        List<String> infos = new ArrayList<String>();
        try {
            HtmlPage page = webClient.getPage(eventLink);

            List<HtmlElement> additionEls = (List<HtmlElement>) page.getByXPath(AppConstant.additionInfoTables);
            int size = additionEls.size();
            System.out.println(size);
            if (size >= 5) {
                if ((size % 2) == 0) {
                    HtmlElement additionEl1 = (HtmlElement) page.getFirstByXPath(AppConstant.additionInfoTable1);
                    if (additionEl1 != null) {
                        infos.add(additionEl1.asXml());
                        System.out.println(additionEl1.asXml());
                    }
                } else {
                    HtmlElement additionEl2 = (HtmlElement) page.getFirstByXPath(AppConstant.additionInfoTable2);
                    if (additionEl2 != null) {
                        infos.add(additionEl2.asXml());
                        System.out.println(additionEl2.asXml());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webClient.close();
        }
        return infos;
    }

    public static String getVideoStream(Video video) {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        ProxyConfig proxyConfig = new ProxyConfig("10.88.16.183", 8080);
        webClient.getOptions().setProxyConfig(proxyConfig);
        try {
            System.out.println("Page link: " + video.getLink());
            HtmlPage page = webClient.getPage(video.getLink());
            HtmlElement videoEl = null;
            HtmlElement el = page.getFirstByXPath(AppConstant.videoWrapperStream);
            String wrapperVideoUrl = el.getAttribute("src");
            System.out.println("Video link: " + wrapperVideoUrl);
            webClient.getOptions().setJavaScriptEnabled(true);
            switch (video.getKind()) {
                case "'alieztv'":
                    page = webClient.getPage(wrapperVideoUrl);
                    videoEl = page.getFirstByXPath(AppConstant.aliezStream);
                    break;
                case "'ifr'":
                    page = webClient.getPage(wrapperVideoUrl);
                    videoEl = page.getFirstByXPath(AppConstant.ifrStream);
                    break;
                case "'youtube'":
                    String result = "<iframe src='" + wrapperVideoUrl +"' height=\"100%\" width=\"100%\" frameborder=\"0\"></iframe>";
                    return result;
            }
            System.out.println("Video HTML: " + videoEl.asXml());
            return videoEl.asXml();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webClient.close();
        }
        return null;
    }
}
