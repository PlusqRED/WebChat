package ru.grape.ws.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Appender {
    public static String leftAppend(String url, Pattern pattern, String data) {
        Matcher matcher = pattern.matcher(data);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            String fileName = data.substring(matcher.start(), matcher.end());
            String urlPlusFileName = url + fileName;
            matcher.appendReplacement(stringBuffer, urlPlusFileName);
        }
        return stringBuffer.toString();
    }
}
