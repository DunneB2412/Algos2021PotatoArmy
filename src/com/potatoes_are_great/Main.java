package com.potatoes_are_great;

import com.potatoes_are_great.search.RunSearch;
import com.potatoes_are_great.search.Schedule;
import com.potatoes_are_great.search.Stop;

import java.io.File;


public class Main {
    public static final String path = "src/com/potatoes_are_great/inputs/";

    public static void main(String[] args) {
        Stop.prepare(new File(path+"stops.txt"));
        Schedule schedule = new Schedule(new File(path+"stop_times.txt"));
        RunSearch run = new RunSearch(schedule);


        run.setStart(Stop.getRandom());
        run.setEnd(Stop.getRandom());

        run.run();

        //while(!run.isReady()) System.out.println("waiting");;
        //System.out.println(run.getPlan().getId());
    }
}
