package com.quangphuong.crawler.util;

public class AppConstant {
    public static final String rootContext = "src/main/java";
    public static final String generatePackage = "com.quangphuong.crawler.dto";
    
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
    
    
}
