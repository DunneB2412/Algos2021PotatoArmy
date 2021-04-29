package com.potatoes_are_great.utill;


public class Time implements Comparable<Time>{

    public final int hours, minutes, seconds;

    public Time(int hours, int minutes, int seconds) {
        assert minutes<60:"to many minutes <"+minutes+">";
        assert seconds<60:"to many secconds <"+seconds+">";
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
    public String toString() {
        return hours%24+":"+minutes+":"+seconds;
    }
}
