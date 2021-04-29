package com.potatoes_are_great.utill;

import com.potatoes_are_great.search.Edge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patterns {
    public static final Pattern TIME_PATTERN = Pattern.compile("([\\d\\s]\\d):(\\d\\d):(\\d\\d)");
    public static final Pattern TRIP_PATTERN = Pattern.compile("(\\d+),([\\d\\s]\\d:\\d\\d:\\d\\d),([\\d\\s]\\d:\\d\\d:\\d\\d),(\\d+),(\\d*),(.*),(\\d),(\\d),(\\d*.?\\d*)");
    public static final Pattern STOP_PATTERN = Pattern.compile("(\\d+),(\\d+),(.+),(.+),(-?\\d\\d\\d?.\\d*),(-?\\d\\d\\d?.\\d*),(.+),(.*),(\\d),(\\d*)");
    public static final Pattern EDGE_PATTERN = Pattern.compile("(\\d+),(\\d+),(\\d),(\\d*)");

    public static Edge getEdgeFromPattern(String line){
        return  null;
        //TODO implimnet this and others
    }
    public static Time getTimeFromPattern(String line){
        Matcher matcher = TIME_PATTERN.matcher(line);
        if(!matcher.matches()){
            return null;
        }
        int hours = Integer.parseInt(matcher.group(1).replaceAll("\\s",""));//since we are allowing the space through, better chop it
        int minutes = Integer.parseInt(matcher.group(2));
        int secconds = Integer.parseInt(matcher.group(3));

        if(minutes>=60||secconds>=60) return null; //hard check limits

        return new Time(hours,minutes,secconds);
    }
}
