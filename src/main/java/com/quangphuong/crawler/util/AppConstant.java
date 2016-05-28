package com.quangphuong.crawler.util;

public class AppConstant {

    public static final String rootContext = "src/main/java";
    public static final String generatePackage = "com.quangphuong.crawler.dto2";

    public static final String prefix = "http://livetv.sx/";
    public static final String videoPrefix = "http://livetv.sx/en/video/";

    public static final String comingUpData = "data.xml";
    public static final String comingUpSchema = "data.xsd";
    public static final String comingUpPage = "http://livetv.sx/en/allupcoming/";
    public static final String comingUpEventColumn = "//div[@id='aul']/table/tbody/tr/td";
    public static final String comingUpEventKind = "tbody/tr/td[2]/a/b";
    public static final String comingUpSameKindEvents = "tbody/tr/td/table";
    public static final String comingUpEventMatch = "tbody/tr/td[2]/a";
    public static final String comingUpEventLive = "tbody/tr/td[2]/img";
    public static final String comingUpEventTime = "tbody/tr/td[2]/span/text()";
    public static final String comingUpEventTournament = "tbody/tr/td[2]/span";
    public static final String comingUpEventImage = "tbody/tr/td[1]/img";

    public static final String saveLinks = "links.xml";
    public static final String linksSchema = "links.xsd";

    public static final String eventDetailData = "/Users/quangphuong/NetBeansProjects/LiveSportTVCrawler/eventDetail.xml";
    public static final String eventDetailSchema = "eventDetail.xsd";

    public static final String eventDemo = "http://livetv.sx/en/eventinfo/403949_tokyo_iwate/";
    public static final String eventVideoRows = "//*[@id='links_block']/table[2]/tbody/tr";
    public static final String eventVideoSopcastRows = "//*[@id='links_block']/table[4]/tbody/tr";
    public static final String eventVideoAceRows = "//*[@id='links_block']/table[6]/tbody/tr";
    public static final String eventVideoTables = "//*[@id='links_block']/table";
    public static final String eventVideoLink = "/table/tbody/tr/td[6]/a";
    public static final String eventVideoBitRate = "/table/tbody/tr/td[2]";
    public static final String eventTeamColumns = "/html/body/table/tbody/tr/td[2]/table/tbody/tr[3]/"
            + "td/table/tbody/tr/td[2]/div[1]/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/table[2]/tbody/tr/td";
    public static final String additionInfoTables = "/html/body/table/tbody/"
            + "tr/td[2]/table/tbody/tr[3]/td/table/tbody/tr/td[2]/div[1]/table/"
            + "tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/table";
    public static final String additionInfoTable1 = "/html/body/table/tbody/"
            + "tr/td[2]/table/tbody/tr[3]/td/table/tbody/tr/td[2]/div[1]/table/"
            + "tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/table[4]";
    public static final String additionInfoTable2 = "/html/body/table/tbody/"
            + "tr/td[2]/table/tbody/tr[3]/td/table/tbody/tr/td[2]/div[1]/table/"
            + "tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/table[5]";

    public static final String hightlightTables = "/html/body/table/tbody/tr/"
            + "td[2]/table/tbody/tr[3]/td/table/tbody/tr/td[2]/table/tbody/tr/"
            + "td/table/tbody/tr[2]/td/table/tbody/tr/td/table";

    public static final String highlightKind = "tbody/tr/td/span";
    public static final String highlightTournament = "tbody/tr/td/table/tbody/tr/td[3]/a";
    public static final String highlightMatches = "tbody/tr";
    public static final String highlightMatch = "td/table/tbody/tr/td[5]";
    public static final String highlightMatchTime = "td/table/tbody/tr/td[1]";
    public static final String highlightMatchLogoTeam1 = "td/table/tbody/tr/td[2]/img";
    public static final String highlightMatchScore = "td/table/tbody/tr/td[3]";
    public static final String highlightMatchLogoTeam2 = "td/table/tbody/tr/td[4]/img";
    public static final String highlightMatchLink = "td/table/tbody/tr/td[6]/a";
    public static final String highlightMatchFullLink = "td/table/tbody/tr/td[8]/div/table/tbody/tr[1]/td/nobr/a[1]";
    public static final String highlightMatchLongLink = "td/table/tbody/tr/td[9]/div/table/tbody/tr/td/nobr/a[1]";

    public static final String hightlightVideo1 = "/html/body/table/tbody/tr/td[2]/table/tbody/tr[3]"
            + "/td/table/tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[1]/table[2]/tbody/tr[1]/td/object/param[1]";

    public static final String hightlightVideo2 = "/html/body/table/tbody/tr/td[2]/table/tbody/tr[3]"
            + "/td/table/tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[1]/table[2]/tbody/tr[1]/td/embed";

    public static final String hightlightVideo3 = "/html/body/table/tbody/tr/td[2]/table/tbody/tr[3]/td/table/"
            + "tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[1]/table[2]/tbody/tr[1]/td/iframe";

    public static final String hightlightVideo4 = "/html/body/table/tbody/tr/td[2]/table/tbody/tr[3]/td/table/"
            + "tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[1]/table[2]/tbody/tr[1]/td/script";

    public static final String calendarPage = "http://livetv.sx/en/calendar/";
    public static final String rounds = "//span[@class='whitetitle']";

    public static final String EnglandCalendar = "england.xml";
    public static final String SpainCalendar = "spain.xml";
    public static final String ItalyCalendar = "italy.xml";
    public static final String DutchCalendar = "dutch.xml";
    public static final String GermanCalendar = "german.xml";
    public static final String FranceCalendar = "france.xml";
    public static final String BrazilCalendar = "brazil.xml";

    public static final String calendarSchema = "calendar.xsd";
    public static final String calendarXSL = "calendar.xsl";

    public static final String videoWrapperStream = "//*[@id='playerblock']/tbody/tr/td/iframe";
    public static final String aliezStream = "//*[@id='mediaspace']";
    public static final String ifrStream = "//object";
    public static final String sopCastStream = "//*[@id='playerblock']/tbody/tr/td/center/a";
    public static final String sopcastHead = "<object id='SopPlayer' name='SopPlayer'"
            + "classid='clsid:8FEFF364-6A5F-4966-A917-A3AC28411659'"
            + " height='100%' width='100%'>"
            + "<param name='AutoStart' value='1' />"
            + "<param name='SopAddress' value='";
    public static final String sopcastTail = "' />"
            + "<param name='ChannelName' value='Channel' />"
            + "</object>";
}
