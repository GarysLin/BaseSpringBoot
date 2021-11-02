package com.gary.cloudinteractive.webapi.utils;

import java.time.format.DateTimeFormatter;

public class DateConstants {

    public static final String YMD = "yyyy-MM-dd";
    public static final String HMS = "HH:mm:ss";
    public static final String HM = "HH:mm";
    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YMDHMS2 = "yyyy/MM/dd HH:mm:ss";
    public static final String YMDHMSSSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String YMDHMSS = "yyyy-MM-dd HH:mm:ss.S";
    public static final String MDHM = "MM/dd HH:mm";
    public static final String YMDwithoutSeparator = "yyyyMMddHHmmss";
    public static final String YMDOnly = "yyyyMMdd";
    public static final String HmsOnly = "HHmmss";




    /**
     * 日期格式化成yyyy-MM-dd
     */
    public static final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern(YMD);
    /**
     * 日期格式化成HH:mm:ss
     */
    public static final DateTimeFormatter HH_mm_SS = DateTimeFormatter.ofPattern(HMS);
    /**
     * 日期格式化成HH:mm
     */
    public static final DateTimeFormatter HH_mm = DateTimeFormatter.ofPattern(HM);
    /**
     * 日期格式化成yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter YYYY_MM_DD_HH_mm_SS = DateTimeFormatter.ofPattern(YMDHMS);
    /**
     * 日期格式化成yyyy/MM/dd HH:mm:ss，如2018/04/05 21:50:58
     */
    public static final DateTimeFormatter YYYY_MM_DD_HH_mm_SS2 = DateTimeFormatter.ofPattern(YMDHMS2);
    /**
     * 日期格式化成MM/dd HH:mm
     */
    public static final DateTimeFormatter MM_DD_HH_mm = DateTimeFormatter.ofPattern(MDHM);
    /**
     * 日期格式化成yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final DateTimeFormatter YYYY_MM_DD_HH_mm_SS_MS = DateTimeFormatter.ofPattern(YMDHMSSSS);
    /**
     * 日期格式化成yyyy-MM-dd HH:mm:ss.S
     */
    public static final DateTimeFormatter YYYY_MM_DD_HH_mm_SS_S = DateTimeFormatter.ofPattern(YMDHMSS);
    /**
     * 日期格式化成yyyyMMddHHmmss
     */
    public static final DateTimeFormatter DATETIME_FORMAT_NO_SLASH = DateTimeFormatter.ofPattern(YMDwithoutSeparator);
    /**
     * 日期格式化成yyyyMMdd
     */
    public static final DateTimeFormatter DATE_FORMAT_NO_SLASH = DateTimeFormatter.ofPattern("yyyyMMdd");
    /**
     * 日期格式化成HHmmss
     */
    public static final DateTimeFormatter TIME_FORMAT_NO_SLASH = DateTimeFormatter.ofPattern("HHmmss");

}
