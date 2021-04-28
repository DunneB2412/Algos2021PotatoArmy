package com.potatoes_are_great.search;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Trip {
    private static final Pattern PATTERN = Pattern.compile("(\\d+),(\\d+),(.+),(.+),(-?\\d\\d\\d?.\\d*),(-?\\d\\d\\d?.\\d*),([A-Z][A-Z]\\s\\d\\d),(.*),(\\d),(\\d*)");

    private final List<Stop> stops = new ArrayList<>();
    private final List<Time[]> time = new ArrayList<>();
    //private final
    private final int id, pPype, dType;

    //TODO set up trip stuff
}
