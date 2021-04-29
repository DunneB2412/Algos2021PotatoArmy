package com.potatoes_are_great;

import com.potatoes_are_great.search.RunSearch;
import com.potatoes_are_great.search.Schedule;

import java.io.File;


public class Main {
    public static final String path = "src/com/potatoes_are_great/inputs/";

    public static void main(String[] args) {

        Schedule schedule = new Schedule(new File(path+"stop_times.txt"));
    }
}
