package com.potatoes_are_great;

import com.potatoes_are_great.search.RunSearch;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File filea = new File("");
        File fileb = new File("");
        File filec = new File("");
        RunSearch search = new RunSearch(filea,fileb,filec);
        search.run();
    }
}
