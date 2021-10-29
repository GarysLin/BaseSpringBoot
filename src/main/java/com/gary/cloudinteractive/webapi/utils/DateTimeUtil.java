package com.gary.cloudinteractive.webapi.utils;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Modified by Rex on 2020/7/24.
 * <p>
 * 日期，時間 工具
 */
public class DateTimeUtil {

    /**
     * 取得當前時間
     *
     * @return
     */
    public static LocalDateTime getNowLocal() {
        return LocalDateTime.now(ZoneId.systemDefault());
    }

    /**
     * 取得當前時間
     *
     * @return
     */
    public static Date getNowDate() {
        return Date.from(getNowLocal().toInstant(ZoneOffset.of("+08:00")));
    }

    /**
     * 取得當前時間字串
     *
     * @return
     */
    public static String getNowString() {
        return getNowLocal().format(DateConstants.YYYY_MM_DD_HH_mm_SS);
    }

    /**
     * 取得當前時間字串
     *
     * @return
     */
    public static String getNowStringMs() {
        return getNowLocal().format(DateConstants.YYYY_MM_DD_HH_mm_SS_MS);
    }

    /**
     * Date 2 LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertDate2LocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                      .atZone(ZoneId.systemDefault())
                      .toLocalDateTime();
    }

    /**
     * Timestamp 2 Date
     *
     * @param timestamp
     * @return
     */
    public static Date convertTimestamp2Date(Timestamp timestamp) {
        return Date.from(timestamp.toInstant());
    }

    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime.toInstant(ZoneOffset.of("+08:00"));
        Date date = Date.from(instant);
        return date;
    }

    /**
     * 日期格式化成yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String formatDateYYYY_MM_DD_HH_mm_SS(Date date) {
        if (date != null) {
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
                                .format(DateConstants.YYYY_MM_DD_HH_mm_SS);
        } else {
            return "";
        }

    }

    /**
     * 日期格式化成yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date, DateTimeFormatter formatter) {
        if (date != null) {
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
                                .format(formatter);
        } else {
            return "";
        }

    }

    /**
     * 日期格式化成yyyy-MM-dd HH:mm:ss.SSS
     *
     * @param date
     * @return
     */
    public static String formatDateYYYY_MM_DD_HH_mm_SS_MS(Date date) {
        if (date != null) {
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
                                .format(DateConstants.YYYY_MM_DD_HH_mm_SS_MS);
        } else {
            return "";
        }

    }

    /***
     * 輸入datetime格式為yyyyMMddHHmmss的字串，parse為LocalDateTime
     * @param strDate
     * @return LocalDateTimne
     * @throws DateTimeParseException
     */
    public static LocalDateTime parseToLocalDate(String strDate,
                                                 DateTimeFormatter formatter) throws DateTimeParseException {
        LocalDateTime dateTime = null;
        dateTime = LocalDateTime.parse(strDate, formatter);
        return dateTime;

    }

    public static Date parseToDate(String strDate, DateTimeFormatter formatter) throws DateTimeParseException {
        LocalDateTime dateTime = null;
        dateTime = LocalDateTime.parse(strDate, formatter);
        return convertLocalDateTimeToDate(dateTime);

    }

    public static Date parseToDateNoTime(String strDate, DateTimeFormatter formatter) throws DateTimeParseException {
        LocalDateTime dateTime = null;
        dateTime = LocalDate.parse(strDate, formatter).atStartOfDay();
        return convertLocalDateTimeToDate(dateTime);
    }

    public static String formatToStartDate(LocalDate startDate){
        return startDate.format(DateConstants.YYYY_MM_DD) + " 00:00:00";
    }

    public static String formatToEndDate(LocalDate endDate){
        return endDate.format(DateConstants.YYYY_MM_DD) + " 23:59:59";
    }

}
