/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.controller;

/**
 *
 * @author quangphuong
 */
public class Response {
    public int status;
    public String messages;
    public Object data;

    public Response() {
    }

    public Response(int status, String message, Object data) {
        this.status = status;
        this.data = data;
        this.messages = message;
    }
    
}
