package com.potatoes_are_great.search;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Trip {
    private static final Pattern PATTERN = Pattern.compile("(\\d+),(\\d+),(\\d\\d?:\\d\\d:\\d\\d),(\\d\\d?:\\d\\d:\\d\\d),(\\d+),(.*),(\\d),(\\d),(\\d+.\\d*)?"); //TODO repair last parretn

    private final List<Stop> stops = new ArrayList<>();
    private final List<Time[]> time = new ArrayList<>();
    private final List<Double> dist = new ArrayList<>();
    private final List<Integer[]> pDTypes = new ArrayList<>();
    private final int id;

    //TODO set up trip stuff
}
