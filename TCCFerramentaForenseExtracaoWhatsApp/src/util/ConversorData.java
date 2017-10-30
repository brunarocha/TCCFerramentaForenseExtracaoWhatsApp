/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author BRUNA
 */
public class ConversorData {
    private static final Calendar calendarStatic = Calendar.getInstance();
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public static String getStringDataAtual() {
        return simpleDateFormat.format(calendarStatic.getTime());        
    }
    
    
    public static String getStringDeCalendar(Calendar calendar){
        Date data = calendar.getTime();
        return simpleDateFormat.format(data);
    }
    
    
    public static String getStringDeDate(Date date){
        return simpleDateFormat.format(date);
    }
    
    
    public static String getStringDeTime(Time time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time.getTime());
        
        return calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
    }
    
    
    public static Calendar getCalendarDeDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        return calendar;
    }
    
    
    public static java.sql.Date getDateDeString(String data){
        try {
            simpleDateFormat.parse(data);
            Calendar calendar = simpleDateFormat.getCalendar();
            return new java.sql.Date(calendar.getTimeInMillis());
        } catch (ParseException ex) {
            return null;
        }      
    }
    
    
    public static java.sql.Date getDateDataAtual(){
        return new java.sql.Date(calendarStatic.getTimeInMillis());      
    }
    
    
    public static java.sql.Date getDateDeTimestamp(Timestamp timestamp){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        
        return new java.sql.Date(calendar.getTimeInMillis());
    }
    
    
    public static java.sql.Time getTimeDeString(String horaRecebida){
        int hora = Integer.parseInt(horaRecebida.substring(0, horaRecebida.indexOf(":")));
        int minuto = Integer.parseInt(horaRecebida.substring(horaRecebida.indexOf(":")+1));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hora);
        calendar.set(Calendar.MINUTE, minuto);
        Time time = new Time(calendar.getTimeInMillis());

        return time;
    }
    
    
    public static java.sql.Time getTimeDeTimestamp(Timestamp timestamp){
        return new Time(timestamp.getTime());
    }
    
}