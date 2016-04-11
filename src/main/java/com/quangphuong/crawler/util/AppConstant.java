package com.quangphuong.crawler.util;

public class AppConstant {
    public static final String rootContext = "src/main/java";
    public static final String generatePackage = "com.quangphuong.crawler.dto2";
    
    public static final String prefix = "http://livetv.sx/";

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
    
    public static final String videoWrapperStream = "//*[@id='playerblock']/tbody/tr/td/iframe";
    public static final String videoStream = "//*[@id='mediaspace']";
}
