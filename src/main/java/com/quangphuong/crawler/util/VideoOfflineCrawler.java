/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.util;

/**
 *
 * @author quangphuong
 */
public class VideoOfflineCrawler {
    public static void main(String[] args) {
        try {
            Runnable runnable1 = new VideoCrawlerThread(VideoKind.Hightlight);
//            Runnable runnable2 = new VideoCrawlerThread(VideoKind.Longhighlight);
            Runnable runnable3 = new VideoCrawlerThread(VideoKind.Fullmatch);
            
            Thread thread1 = new Thread(runnable1);
//            Thread thread2 = new Thread(runnable2);
            Thread thread3 = new Thread(runnable3);
            
            thread1.start();
//            thread2.start();
            thread3.start();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    
}
