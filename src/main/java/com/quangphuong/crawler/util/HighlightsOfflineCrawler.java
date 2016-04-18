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
import com.quangphuong.crawler.dbutil.DBWrapper;
import com.quangphuong.crawler.dbutil.ObjectIO;
import com.quangphuong.crawler.dto.Highlight;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author quangphuong
 */
public class HighlightsOfflineCrawler {

//    private static int stopDate = 20090120;
    private static int initDate = 20090000;
    private static int stopDate = 20121105;
    private static int startY = 20090000;
    private static int startD = 1;
    private static int startM = 100;
    private static final WebClient webClient = new WebClient(BrowserVersion.CHROME);
    private static final String cachePath = "/Users/quangphuong/Desktop/cacheDate.dat";

    public static void main(String[] args) {
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//        webClient.setJavaScriptTimeout(2 * 1000);
        int y = startY;
        int m = startM;
        int d = startD;
        int date = 0;
        File f = new File(cachePath);
        if (f.exists() && !f.isDirectory()) {
            date = (Integer) ObjectIO.read(cachePath);
            y = date / 10000;
            m = date % 10000;
            d = m % 100;
            System.out.println("D: " + d + "-M: " + m + "-Y: " + y);
        }
        boolean goNext = true;
        while (date != stopDate) {
            date = y + m + d;
            // Crawler
            String dateStr = String.valueOf(date);
            String link = AppConstant.videoPrefix + dateStr;
            try {
                System.out.println("Link: " + link);
                HtmlPage page = webClient.getPage(link);
                goNext = page.getWebResponse().getStatusCode() != 503;
                List<HtmlElement> tables = (List<HtmlElement>) page.getByXPath(AppConstant.hightlightTables);
                int count = 0;
                String kind = "";
                String tournament = "";
                for (HtmlElement table : tables) {
                    count++;

                    //Get kind
                    HtmlElement span = table.getFirstByXPath(AppConstant.highlightKind);
                    if (span != null && span.getAttribute("class").equals("whitetitle")) {
                        kind = span.getTextContent();
                        System.out.println(kind);
                    }

                    // Get Tournament
                    if (table.getAttribute("background") != null && !"".equals(table.getAttribute("background"))) {
                        HtmlElement el = table.getFirstByXPath(AppConstant.highlightTournament);
                        tournament = el.getTextContent();
                        System.out.println(tournament);
                    } else // Get matches
                    {
                        if (count != 1 && (table.getAttribute("bgcolor").equals(""))) {
                            List<HtmlElement> matches = (List<HtmlElement>) table.getByXPath(AppConstant.highlightMatches);
                            for (HtmlElement el : matches) {
//                            System.out.println(el.asXml());
                                HtmlElement tmp = el.getFirstByXPath(AppConstant.highlightMatch);
                                String match = tmp.getTextContent().trim();

                                tmp = el.getFirstByXPath(AppConstant.highlightMatchTime);
                                String time = tmp.getTextContent().trim();
                                tmp = el.getFirstByXPath(AppConstant.highlightMatchLogoTeam1);
                                String logoTeam1;
                                try {
                                    logoTeam1 = tmp.getAttribute("src");
                                } catch (Exception e) {
                                    logoTeam1 = "";
                                }
                                tmp = el.getFirstByXPath(AppConstant.highlightMatchScore);
                                String score = tmp.getTextContent().trim();
                                tmp = el.getFirstByXPath(AppConstant.highlightMatchLogoTeam2);
                                String logoTeam2;
                                try {
                                    logoTeam2 = tmp.getAttribute("src");
                                } catch (Exception e) {
                                    logoTeam2 = "";
                                }
//                            webClient.waitForBackgroundJavaScript(10 * 1000);
                                tmp = el.getFirstByXPath(AppConstant.highlightMatchLink);
                                String highlightLink;
                                try {
                                    highlightLink = tmp.getAttribute("href");
                                } catch (Exception e) {
                                    highlightLink = "";
                                }
                                tmp = el.getFirstByXPath(AppConstant.highlightMatchFullLink);
                                String fullMatchLink;
                                try {
                                    fullMatchLink = tmp.getAttribute("href");
                                } catch (Exception e) {
                                    fullMatchLink = "";
                                }
                                tmp = el.getFirstByXPath(AppConstant.highlightMatchLongLink);
                                String longHighlightLink;
                                try {
                                    longHighlightLink = tmp.getAttribute("href");
                                } catch (Exception e) {
                                    longHighlightLink = "";
                                }
                                System.out.println("----" + match + "-" + time + "-" + logoTeam1 + "-" + score + "-" + logoTeam2 + "-" + highlightLink
                                        + "-" + fullMatchLink + "-" + longHighlightLink);
//                            System.out.println("kindddddddddddddddd: " + kind);
                                Highlight highlight = new Highlight(0, kind,
                                        tournament, match, logoTeam1, logoTeam2, highlightLink, longHighlightLink, fullMatchLink, score, dateStr, time);

                                DBWrapper dBWrapper = new DBWrapper(false);
                                dBWrapper.updateEntity(highlight);
                            }
                        }
                    }
                }
//                System.out.println("-------------------");
//                System.out.println("Page memory: " + Agent.sizeOf(page));
            } catch (Exception ex) {
                Logger.getLogger(HighlightsOfflineCrawler.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObjectIO.write(cachePath, date);
            if (goNext) {
                if (m == 1200 && isLastDayOfMonth(d, m, y)) {
                    y += 10000;
                    m = 100;
                    d = 1;
                } else if (isLastDayOfMonth(d, m, y)) {
                    m += 100;
                    d = 1;
                } else {
                    d += 1;
                }
            }
        }
    }

    public static boolean isLastDayOfMonth(int day, int month, int year) {
        month = month / 100;
        int[] days = new int[]{1, 3, 5, 7, 8, 10, 12};
        if (month != 2) {
            if (day == 31 && ArrayUtils.contains(days, month)) {
                return true;
            } else if (day == 30 && !ArrayUtils.contains(days, month)) {
                return true;
            }
        } else {
            return (day == 28 && year % 4 == 0);
        }
        return false;
    }
}
