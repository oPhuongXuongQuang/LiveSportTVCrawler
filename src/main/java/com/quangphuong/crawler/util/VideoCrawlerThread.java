/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.util;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.gson.Gson;
import com.quangphuong.crawler.dbutil.DBWrapper;
import com.quangphuong.crawler.dbutil.ObjectIO;
import com.quangphuong.crawler.dto.Highlight;
import java.io.File;
import java.util.List;

/**
 *
 * @author quangphuong
 */
public class VideoCrawlerThread implements Runnable {

//    private static final WebClient webClient = new WebClient(BrowserVersion.CHROME);
    private static final String highlightPath = "/Users/quangphuong/Desktop/links.dat";
    private static final String highlightLongPath = "/Users/quangphuong/Desktop/links2.dat";
    private static final String fullmatchPath = "/Users/quangphuong/Desktop/links3.dat";
    private static final String cachePath1 = "/Users/quangphuong/Desktop/pos1.dat";
    private static final String cachePath2 = "/Users/quangphuong/Desktop/pos2.dat";
    private static final String cachePath3 = "/Users/quangphuong/Desktop/pos3.dat";
    private VideoKind videoKind;

    public VideoCrawlerThread() {
    }

    public VideoCrawlerThread(VideoKind videoKind) {
        this.videoKind = videoKind;
    }

    @Override
    public void run() {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        String whereClause = "";
        String dataPath = "";
        String cachePath = "";
        int oldPos = 0;
        switch (videoKind) {
            case Hightlight:
                whereClause = " WHERE highlightLink NOT LIKE \"\"";
                dataPath = highlightPath;
                cachePath = cachePath1;
                oldPos = 21105;
                break;
            case Longhighlight:
                whereClause = " WHERE longHighlightLink NOT LIKE \"\"";
                dataPath = highlightLongPath;
                cachePath = cachePath2;
                oldPos = 14705;
                break;
            case Fullmatch:
                whereClause = " WHERE fullmatchLink NOT LIKE \"\"";
                dataPath = fullmatchPath;
                cachePath = cachePath3;
                oldPos = 18406;
                break;
        }
        DBWrapper dBWrapper = new DBWrapper(false);
        
        try {
            List<Highlight> highlights;
            File f = new File(dataPath);
            if (f.exists() && !f.isDirectory()) {
                highlights = (List<Highlight>) ObjectIO.read(dataPath);
                System.out.println("Sizeeee: " + highlights.size());
            } else {
                System.out.println("hello");

                highlights = (List<Highlight>) (Object) dBWrapper.getEntitiesByCustom(new Highlight(), whereClause);
                ObjectIO.write(dataPath, highlights);
            }
                int pos = 0;
            File cache = new File(cachePath);
            if (cache.exists() && !cache.isDirectory()) {
                pos = Integer.parseInt(ObjectIO.read(cachePath).toString());
                System.out.println("Pos: " + pos);
            }
//            for (int i = pos; i < highlights.size(); i++) {
            for (int i = pos; i > oldPos; i--) {
                System.out.println("Current pos: " + i);
//                pos = i;
                Highlight highlight = highlights.get(i);
                boolean refresh = true;
                HtmlPage page = null;
                String pageLink = "";
                while (refresh) {
                    System.out.println("Refeshing...");
                    switch (videoKind) {
                        case Hightlight:
                            pageLink = highlight.getHighlightLink();
                            break;
                        case Longhighlight:
                            pageLink = highlight.getLongHighlightLink();
                            break;
                        case Fullmatch:
                            pageLink = highlight.getFullmatchLink();
                            break;
                    }
                    if (!pageLink.equals("")) {
                        System.out.println("page link: " + AppConstant.prefix + pageLink);
                        page = webClient.getPage(AppConstant.prefix + pageLink);
                        refresh = page.getWebResponse().getStatusCode() == 503;
                    } else {
                        refresh = false;
                    }
                }
                if (!pageLink.equals("")) {
                    HtmlElement el = page.getFirstByXPath(AppConstant.hightlightVideo1);
                    String link = "";
                    if (el == null) {
                        el = page.getFirstByXPath(AppConstant.hightlightVideo2);
                        if (el != null) {
                            link = el.getAttribute("src");
                        } else {
                            el = page.getFirstByXPath(AppConstant.hightlightVideo3);
                            if (el != null) {
                                link = el.getAttribute("src");
                            } else {
                                el = page.getFirstByXPath(AppConstant.hightlightVideo4);
                                if (el != null) {
                                    Gson gson = new Gson();
                                    link = gson.toJson(el.asXml());
                                }
                            }
                        }
                    } else {
                        link = el.getAttribute("value");
                    }
                    System.out.println("Link: " + link);
                    if (link != null && !link.equals("")) {
                        Highlight newHighlight = null;
                        switch (videoKind) {
                            case Hightlight:
                                newHighlight = new Highlight(highlight.getId(), null, null, null, null, null, link, null, null, null, null, null);
                                break;
                            case Longhighlight:
                                newHighlight = new Highlight(highlight.getId(), null, null, null, null, null, null, link, null, null, null, null);
                                break;
                            case Fullmatch:
                                newHighlight = new Highlight(highlight.getId(), null, null, null, null, null, null, null, link, null, null, null);
                                break;
                        }
                        dBWrapper.updateEntity(newHighlight);
                    }
                }
                ObjectIO.write(cachePath, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBWrapper.closeConnection();
        }

    }

//    public String crawl(HtmlPage page) {
//        HtmlElement el = page.getFirstByXPath(AppConstant.hightlightVideo1);
//        String link = "";
//        if (el == null) {
//            el = page.getFirstByXPath(AppConstant.hightlightVideo2);
//            if (el != null) {
//                link = el.getAttribute("src");
//            }
//        } else {
//            link = el.getAttribute("value");
//        }
//        System.out.println("Link: " + link);
//        return link;
//    }

}
