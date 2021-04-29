package com.potatoes_are_great.utill;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Time implements Comparable<Time>{

    public static Time getTimeFromPattern(String line, Pattern pattern){
        Matcher matcher = pattern.matcher(line);
        if(!matcher.matches()){
            return null;
        }
        int hours = Integer.parseInt(matcher.group(1).replaceAll("\\s",""));//since we are allowing the space through, better chop it
        int minutes = Integer.parseInt(matcher.group(2));
        int seconds = Integer.parseInt(matcher.group(3));

        if(minutes>=60||seconds>=60) return null; //hard check limits

        return new Time(hours,minutes,seconds);
    }

    private int hours, minutes, seconds;

    public Time(int hours, int minutes, int seconds) {
        assert minutes<60:"to many minutes <"+minutes+">";
        assert seconds<60:"to many seconds <"+seconds+">";
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public int compareTo(Time o) {
        int out = Integer.compare(hours,o.hours);
        if(out==0) out = Integer.compare(minutes,o.minutes);
        if(out==0) out = Integer.compare(seconds,o.seconds);
        return out;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Time){
            Time t = (Time) obj;
            return this.compareTo(t)==0;
        }
        return false;
    }

    @Override
    public String toString() {
        return hours%24+":"+minutes+":"+seconds;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public Time dayFilter(){
        this.hours = (24+this.hours%24)%24;
        return this;
    }


    public Time add(Time o){
        int carry = 0;
        this.seconds+=o.seconds;
        carry = this.seconds/60;
        this.seconds = this.seconds%60;

        this.minutes+=o.minutes+carry;
        carry = this.minutes/60;
        this.minutes = this.minutes%60;

        this.hours+=o.hours+carry;
        return this;
    }

    public Time sub(Time o){
        int carry = 0;
        this.seconds-=o.seconds;
        carry = (Math.abs(this.seconds)+60)/60;
        this.seconds = 60+this.seconds%60;

        this.minutes-=(o.minutes+carry);
        carry = (Math.abs(this.minutes)+60)/60;
        this.minutes = 60+this.minutes%60;

        this.hours-=(o.hours+carry);
        return this;
    }
}
