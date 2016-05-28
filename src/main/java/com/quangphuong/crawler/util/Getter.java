package com.quangphuong.crawler.util;

import com.gargoylesoftware.htmlunit.AjaxController;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Getter {

    @Autowired
    XMLUtil xMLUtil;

    public Getter() {

    }

    public List<Event> getEvents() throws Exception {
        Events events = xMLUtil.unmarshallUtil(AppConstant.comingUpData, Events.class);
        return events.getEvent();
    }

    public List<Calendar.Round> getCalendar(String calendar) throws Exception {
        Calendar calendar1 = xMLUtil.unmarshallUtil(calendar, Calendar.class);
        return calendar1.getRound();
    }

    public EventDetail getEventDetail(String eventLink, boolean isLive) {
        EventDetail eventDetail = new EventDetail();
        eventDetail.setLink(eventLink);
        List<Video> videos = new ArrayList<Video>();
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
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
                        HtmlElement row = videoRows.get(i);
                        for (int j = 1; j <= 2; j++) {
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

    public EventDetail getEventTeamInfo(HtmlPage page, EventDetail eventDetail) {
        // Get Team info
        for (int i = 1; i <= 2; i++) {
            HtmlElement team1 = (HtmlElement) page.getFirstByXPath(AppConstant.eventTeamColumns + "[" + i + "]");
            HtmlElement linkSection = team1.getFirstByXPath("a");
            Team team = new Team();
            if (linkSection != null) {
                HtmlElement imageSection = team1.getFirstByXPath("a/img");
                String image = imageSection.getAttribute("src");
                String name = imageSection.getAttribute("alt");
                String link = AppConstant.prefix + linkSection.getAttribute("href");
                team = new Team(name, image, link);
                if (i == 1) {
                    eventDetail.setTeam1(team);
                } else {
                    eventDetail.setTeam2(team);
                }
            }
        }
        return eventDetail;
    }

    public List<String> getAdditionInfo(String eventLink) {
        // get addition info
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
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

    public String getVideoStream(Video video) throws Exception {

        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.setAjaxController(new AjaxController() {
            @Override
            public boolean processSynchron(HtmlPage page, WebRequest request, boolean async) {
                return true;
            }
        });
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        System.out.println("Page link: " + video.getLink());
        HtmlPage page = null;
        if (!video.getKind().equals("'sopcast'")) {
            page = webClient.getPage(video.getLink());
        }
        HtmlElement videoEl = null;
        webClient.getOptions().setJavaScriptEnabled(true);
        String result = null;
        switch (video.getKind()) {
            case "'alieztv'":
                page = (HtmlPage) page.getFrames().get(1).getEnclosedPage();
                videoEl = page.getFirstByXPath(AppConstant.aliezStream);
                result = videoEl.asXml();
                break;
            case "'ifr'":
                HtmlElement ifr = page.getFirstByXPath(AppConstant.videoWrapperStream);
                String ifrLink = ifr.getAttribute("src");
                System.out.println("Iframe link: " + ifrLink);
//                if (ifrLink.contains("sportstream365.com")) {
                    result = "<iframe src='" + ifrLink + "'"
                            + " height=\"100%\" width=\"100%\" frameborder=\"0\">"
                            + "allowfullscreen=\"allowfullscreen\" mozallowfullscreen=\"mozallowfullscreen\" msallowfullscreen=\"msallowfullscreen\" oallowfullscreen=\"oallowfullscreen\" webkitallowfullscreen=\"webkitallowfullscreen\""
                            + "</iframe>";
//                } else {
//                    page = webClient.getPage(ifrLink);
////                    page = (HtmlPage) page.getFrames().get(1).getEnclosedPage();
//                    videoEl = page.getFirstByXPath(AppConstant.ifrStream);
//                    if (videoEl == null) {
//                        page = (HtmlPage) page.getFrames().get(0).getEnclosedPage();
//                        videoEl = page.getFirstByXPath(AppConstant.ifrStream);
//                    }
//                    String data = videoEl.getAttribute("data");
//                    boolean validURL = true;
//                    try {
//                        URL url = new URL("http://" + data);
//                    } catch(MalformedURLException e) {
//                        validURL = false;
//                    }
//                    if (!data.contains("http://") && !validURL) {
//                        URI uri = new URI(ifrLink);
//                        data = "http://" + uri.getHost() + "/" + data;
//                        videoEl.setAttribute("data", data);
//                    }
//                    result = videoEl.asXml();
//                }
                break;
            case "'youtube'":
                HtmlElement el = page.getFirstByXPath(AppConstant.videoWrapperStream);
                String youtubeLink = el.getAttribute("src");
                System.out.println("Youtube link: " + youtubeLink);
                result = "<iframe src='" + youtubeLink + "'"
                        + " height=\"100%\" width=\"100%\" frameborder=\"0\">"
                        + "allowfullscreen=\"allowfullscreen\" mozallowfullscreen=\"mozallowfullscreen\" msallowfullscreen=\"msallowfullscreen\" oallowfullscreen=\"oallowfullscreen\" webkitallowfullscreen=\"webkitallowfullscreen\""
                        + "</iframe>";
                break;
            case "'sopcast'":
                result = AppConstant.sopcastHead + video.getLink() + AppConstant.sopcastTail;
                break;
        }
        webClient.close();
        return result;
    }
}
