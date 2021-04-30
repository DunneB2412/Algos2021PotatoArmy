package com.potatoes_are_great.utill;

import java.util.regex.Pattern;

public class Patterns {
    public static final Pattern TIME_PATTERN = Pattern.compile("([\\d\\s]\\d):(\\d\\d):(\\d\\d)");
    public static final Pattern TRIP_PATTERN = Pattern.compile("(\\d+),([\\d\\s]\\d:\\d\\d:\\d\\d),([\\d\\s]\\d:\\d\\d:\\d\\d),(\\d+),(\\d*),(.*),(\\d),(\\d),(\\d*.?\\d*)");
    public static final Pattern STOP_PATTERN = Pattern.compile("(\\d+),([\\s\\d]+),(.+),(.+),(-?\\d\\d\\d?.\\d*),(-?\\d\\d\\d?.\\d*),(.+),(.*),(\\d),(\\d*)");
    public static final Pattern EDGE_PATTERN = Pattern.compile("(\\d+),(\\d+),(\\d),(\\d*)");
}
