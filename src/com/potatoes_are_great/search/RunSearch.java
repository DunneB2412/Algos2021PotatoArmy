package com.potatoes_are_great.search;


public class RunSearch implements Runnable{

    private boolean ready;
    private final Schedule schedule;
    private GPSLocation start;
    private GPSLocation end;

    public RunSearch(Schedule schedule) {
        this.schedule = schedule;
        ready = false;
    }

    public void setStart(GPSLocation s){
        this.start = s;
        ready = false;
    }
    public void setEnd(GPSLocation e){
        this.end = e;
        ready = false;
    }


    @Override
    public void run() {
        while (true){
            while(ready || start==null || end==null){
                System.out.println("waiting");
            }
        }
    }
}
