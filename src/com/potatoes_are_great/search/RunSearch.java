package com.potatoes_are_great.search;

import java.io.File;

public class RunSearch implements Runnable{

    private final File a,b,c;
    private boolean started;
    private boolean ready;
    private GPSLocation start ,end;

    public RunSearch(File a, File b, File c) {
        this.a = a;
        this.b = b;
        this.c = c;
        ready = false;
    }

    public void setStart(GPSLocation start){
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.start = start;
        ready = false;
        this.notify();
    }
    public void setEnd(GPSLocation end){
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.end = end;
        ready = false;
        this.notify();
    }

    @Override
    public void run() {
        while (true){
            if(!started){
                setUp();
            }
            while(ready || start==null || end==null);

        }
    }

    private void setUp() {

    }
}
