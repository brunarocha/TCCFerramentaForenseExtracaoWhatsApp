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
public class Contato {
    
    private Long id;
    private Dispositivo dispositivo;
    private String jid;
    private String status;
    private Date statusDate;
    private Time statusTime;
    private String number;
    private String displayName;
    private String waName;
        
    public Contato() {
    }

    public Contato(String jid) {
        this.jid = jid;
    }
    
    public Contato(String jid, String status, String number, String displayName) {
        this.jid = jid;
        this.status = status;
        this.number = number;
        this.displayName = displayName;
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

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Time getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Time statusTime) {
        this.statusTime = statusTime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getWaName() {
        return waName;
    }

    public void setWa_name(String waName) {
        this.waName = waName;
    }

    @Override
    public String toString() {
        if(displayName != null){
            return displayName;
        }
        else if(waName != null){
            return waName;
        }
        else{
            return jid;
        }
    }
}