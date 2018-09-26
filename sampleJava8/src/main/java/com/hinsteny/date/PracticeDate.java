package com.hinsteny.date;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;


/**
 * PracticeDate.java
 * 
 * @author: Hinsteny
 * @date: 2015年10月9日
 * @copyright: 2015 All rights reserved.
 * 
 */
public class PracticeDate {

	@Test
    public void LocalDateTest(){
		
        LocalDate today = LocalDate.now();
        System.out.println("today: "+today);
        
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        System.out.println("tomorrow: "+tomorrow);
        
        LocalDate yesterday = tomorrow.minusDays(2);
        System.out.println("yesterday: "+yesterday);
        
        LocalDate independenceDay = LocalDate.of(2014, Month.AUGUST, 18);
        System.out.println("independenceDay: "+independenceDay);
        
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println("dayOfWeek: "+dayOfWeek);
                
        LocalDateTime atStartOfDay = today.atStartOfDay();
        System.out.println("atStartOfDay: "+atStartOfDay);
        
        LocalDateTime localDateTime = today.atTime(12,23,39,2833829);
        System.out.println("localDateTime: "+localDateTime);
        
        LocalDateTime localDateTime2 = today.atTime(LocalTime.now());
        System.out.println("localDateTimeNow:"+localDateTime2);
        
        DateTimeFormatter germanFomatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
        String formatDate = today.format(germanFomatter);
        System.out.println("FormatDate: "+formatDate);
        LocalDate xmas = LocalDate.parse("18.08.2014",germanFomatter);
        System.out.println(xmas);
        
        //get
        System.out.println("getChronology: "+today.getChronology());
        System.out.println("getDayOfYear: "+today.getDayOfYear());
        System.out.println("getDayOfMonth: "+today.getDayOfMonth());
        System.out.println("getMonthValue: "+today.getMonthValue());
        System.out.println("getYear: "+today.getYear());
        System.out.println("getMonth: "+today.getMonth());
        System.out.println("getDayOfWeek: "+today.getDayOfWeek());
        System.out.println("getEra: "+today.getEra());
        
        System.out.println("hashCode: "+today.hashCode());
    }
    
    @Test
    public void LocalTimeTest(){
        LocalTime timeNow = LocalTime.now();
        System.out.println(timeNow);
        
        LocalTime now1 = LocalTime.now();
        LocalTime now2 = LocalTime.now();
        System.out.println(timeNow.equals(now1.plusMinutes(1)));
        System.out.println(timeNow.isBefore(now1.plusMinutes(1)));
        System.out.println(timeNow.isAfter(now1.plusMinutes(1)));
        System.out.println(now2.compareTo(timeNow.plusMinutes(1)));
    }
    
    @Test
    public void colckTest(){
    	Clock c1 = Clock.systemUTC();
        System.out.println(c1.millis());
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);   // legacy java.util.Date
        
        System.out.println("millis: "+millis);
        System.out.println("instant: "+instant);
        System.out.println("legacyDate: "+legacyDate);
    }
    
    @Test
    public void timeZoneTest(){
        System.out.println(ZoneId.getAvailableZoneIds());
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());
    }
    
    @Test
    public void testInstant() {
        Instant instant1 = Instant.now();
        System.out.println("Instant: " + instant1);
        System.out.println("Instant second from the Java epoch of 1970-01-01T00:00:00Z: " + instant1.getEpochSecond());
        Clock clock1 = Clock.systemUTC();
        Instant instant2 = Instant.now(clock1);
        System.out.println("Converts this instant to the number of milliseconds from the epoch of 1970-01-01T00:00:00Z: " + instant2.toEpochMilli());
        Clock clock2 = Clock.fixed(instant1, ZoneId.systemDefault());
        Instant instant3 = Instant.now(clock2);
        System.out.println(instant3.toEpochMilli());
    }
    
    @Test
    public void testDateTimeFormatter() {
        Instant instant = Instant.now();
        LocalDate today = LocalDate.now();
        System.out.println("today: "+today);
        LocalDateTime time = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDateTime time1 = LocalDateTime.now();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("Time of now1: " + time.format(formatter1));
        System.out.println("Time of now2: " + time1.format(formatter2));
        System.out.println("Time of now3: " + time.format(formatter3));
        System.out.println("Date of now2: " + today.format(formatter2));
        LocalDateTime d6 = LocalDateTime.parse("2015/12/31 23:59:59", formatter1);
        System.out.println("Time of appointed: " + formatter1.format(d6));
    }
}
