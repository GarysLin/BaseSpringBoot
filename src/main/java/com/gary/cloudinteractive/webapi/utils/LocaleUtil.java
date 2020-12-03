package com.gary.cloudinteractive.webapi.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LocaleUtil {
    private static MessageSource messageSource;

    public LocaleUtil(MessageSource messageSource)
    {
        LocaleUtil.messageSource = messageSource;
    }

    /**
     * 獲取單個國際化翻譯值
     */
    public static String get(String msgKey)
    {
        try
        {
            return messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale());
        }
        catch (Exception e)
        {
            return msgKey;
        }
    }
}
