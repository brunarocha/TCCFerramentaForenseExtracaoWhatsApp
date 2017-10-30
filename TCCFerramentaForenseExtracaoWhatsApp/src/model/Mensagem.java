/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author BRUNA
 */
public class Mensagem {
    
    private Long id;
    private Dispositivo dispositivo;
    private Contato keyRemoteJid; 
    private String remoteResource; 
    private boolean keyFromMe;
    private String data;
    private Date date;
    private Time time;
    private String mediaUrl;
    private String mediaMimeType;
    private int mediaSize;
    private String mediaName;
    private String mediaCaption;
    
    public Mensagem() {
    }

    public Mensagem(Contato keyRemoteJid, String remoteResource, boolean keyFromMe, String data, Date date, Time time, String mediaUrl, String mediaMimeType, int mediaSize, String mediaName, String mediaCaption) {
        this.keyRemoteJid = keyRemoteJid;
        this.remoteResource = remoteResource;
        this.keyFromMe = keyFromMe;
        this.data = data;
        this.date = date;
        this.time = time;
        this.mediaUrl = mediaUrl;
        this.mediaMimeType = mediaMimeType;
        this.mediaSize = mediaSize;
        this.mediaName = mediaName;
        this.mediaCaption = mediaCaption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public Contato getKeyRemoteJid() {
        return keyRemoteJid;
    }

    public void setKeyRemoteJid(Contato keyRemoteJid) {
        this.keyRemoteJid = keyRemoteJid;
    }

    public String getRemoteResource() {
        return remoteResource;
    }

    public void setRemoteResource(String remoteResource) {
        this.remoteResource = remoteResource;
    }

    public boolean isKeyFromMe() {
        return keyFromMe;
    }

    public void setKeyFromMe(boolean keyFromMe) {
        this.keyFromMe = keyFromMe;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        if(data == null){
            this.data = "MIDIA"; 
        } else{
            this.data = data;
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaMimeType() {
        return mediaMimeType;
    }

    public void setMediaMimeType(String mediaMimeType) {
        this.mediaMimeType = mediaMimeType;
    }

    public int getMediaSize() {
        return mediaSize;
    }

    public void setMediaSize(int mediaSize) {
        this.mediaSize = mediaSize;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getMediaCaption() {
        return mediaCaption;
    }

    public void setMediaCaption(String mediaCaption) {
        this.mediaCaption = mediaCaption;
    }
    
    @Override
    public String toString() {
        return "Mensagem{" + "data=" + data + '}';
    }
}