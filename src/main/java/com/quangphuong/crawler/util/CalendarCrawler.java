/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.util;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.quangphuong.crawler.dto.Calendar;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Quang
 */
public class CalendarCrawler {

    private static final WebClient webClient = new WebClient(BrowserVersion.CHROME);

    public static void main(String[] args) {
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

        int[] calType = {1, 10, 11, 22, 36, 37, 93};
        for (int k : calType) {
            try {
                HtmlPage page = webClient.getPage(AppConstant.calendarPage + k);
                List<DomElement> els = (List<DomElement>) page.getByXPath(AppConstant.rounds);

                List<Calendar.Round> rounds = new ArrayList<Calendar.Round>();
                for (int i = 1; i < els.size() - 3; i++) {

                    DomElement tmp = els.get(i);
                    //                String round = tmp.getTextContent().replace("(Video)", "").trim();
                    //                System.out.println("Round: " + round);
                    DomNode parent = tmp.getParentNode().getParentNode().getParentNode().getParentNode();
//                    System.out.println("-----" + parent.asXml());

                    List<DomElement> trs = (List<DomElement>) parent.getByXPath("tbody/tr");
                    System.out.println("trs: " + trs.size());
                    String date = "";
                    List<Calendar.Round.Match> matches = new ArrayList<Calendar.Round.Match>();
                    for (int j = 1; j < trs.size() - 1; j++) {
                        DomElement tr = trs.get(j);
                        HtmlElement tmp2 = tr.getFirstByXPath("td");
                        if (tmp2.hasAttribute("bgcolor")) {
                            date = tr.getTextContent().trim();
                            System.out.println("Date: " + date);
                        } else {
                            try {
                                DomElement td = tr.getFirstByXPath("td[1]/a");
                                if(td == null) {
                                    td = tr.getFirstByXPath("td[1]");
                                }
                                String teams = td.getTextContent().trim();
                                System.out.println("Teams: " + teams);
                                String link = td.getAttribute("href");
                                td = tr.getFirstByXPath("td[2]/a/img");
                                String logoTeam1 = td.getAttribute("src");
                                td = tr.getFirstByXPath("td[3]");
                                String score = td.getTextContent().trim();
                                td = tr.getFirstByXPath("td[4]/a/img");
                                String logoTeam2 = td.getAttribute("src");

                                Calendar.Round.Match match = new Calendar.Round.Match(date, link, teams, logoTeam1, logoTeam2, score);
                                match.toString();
                                matches.add(match);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    rounds.add(new Calendar.Round(matches, i));
                }
                Calendar calendar = new Calendar(rounds);
                XMLUtil.marshallUtil(intToCalendar(k), calendar);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                webClient.close();
            }
        }
    }

    public static String intToCalendar(int k) {
        switch (k) {
            case 1:
                return AppConstant.EnglandCalendar;
            case 10:
                return AppConstant.SpainCalendar;
            case 11:
                return AppConstant.ItalyCalendar;
            case 22:
                return AppConstant.DutchCalendar;
            case 36:
                return AppConstant.GermanCalendar;
            case 37:
                return AppConstant.FranceCalendar;
            case 93:
                return AppConstant.BrazilCalendar;
        }
        return "";
    }
}
