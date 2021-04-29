package com.potatoes_are_great.search;

import com.potatoes_are_great.utill.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Edge {
    public final Stop a, b;
    public final int type;
    public final Integer minCost;

    public Edge(String line) throws IllegalAccessException {
        Matcher matcher = Patterns.EDGE_PATTERN.matcher(line);
        if(!matcher.matches()){
            throw new IllegalAccessException("line <"+line+"> is not correctly formated for a transfer");
        }
        a = Stop.getFromId(Integer.parseInt(matcher.group(1)));
        b = Stop.getFromId(Integer.parseInt(matcher.group(2)));
        type = Integer.parseInt(matcher.group(3));
        minCost = matcher.group(4).length()>0? Integer.parseInt(matcher.group(4)):null;
    }
}
