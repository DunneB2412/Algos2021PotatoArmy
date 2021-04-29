package com.potatoes_are_great.search;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class RunSearch implements Runnable{

    private boolean ready;
    private final Schedule schedule;
    //private GPSLocation start;
    //private GPSLocation end;
    private Stop start, end;
    Trip plan;

    public RunSearch(Schedule schedule) {
        this.schedule = schedule;
        ready = false;
    }

//    public void setStart(GPSLocation s){
//        this.start = s;
//        ready = false;
//    }
//    public void setEnd(GPSLocation e){
//        this.end = e;
//        ready = false;
//    }

    public void setStart(Stop s){
        this.start = s;
        ready = false;
        plan = null;
    }
    public void setEnd(Stop e){
        this.end = e;
        ready = false;
        plan = null;
    }
    public Trip getPlan(){
        return plan;
    }


    @Override
    public void run() {
        while(!schedule.ready) System.out.println("waiting on schedule");
        //while (true){
        while(ready || start==null || end==null){
            System.out.println("waiting on sollection");
        }
        //if(schedule.ready && !ready && start!=null && end!=null){
            generatePlan();
        //}
    }

    private void generatePlan(){
        HashSet<Trip> visited = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Node current = new Node(null, start, null, 0, 0);
        queue.add(current);
        while (!queue.isEmpty() && !(current = queue.poll()).is(end)){
            if(visited.contains(current.trip)){
                visited.add(current.trip);
                queue.addAll(current.getChildren());
            }
        }
        plan = new Trip(-1);
        while (current!=null){
            plan.add
        }
    }


    private class Node implements Comparable<Node>{

        Trip trip;
        Stop stop;
        Node parent;
        int depth;
        double distance;

        public Node(Trip trip, Stop stop, Node parent, int depth, double distance){
            this.trip = trip;
            this.stop = stop;
            this.parent = parent;
            this.depth = depth;
            this.distance = distance;
        }


        private double getHuristic(){
            return 0;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(distance+getHuristic(),o.distance+o.getHuristic());
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Node){
                Node n = (Node) obj;
                return trip.equals(n.trip)&&stop.equals(n.stop);
            }
            return false;
        }

        public ArrayList<Node> getChildren(){
            ArrayList<Node> out = new ArrayList<>();
            boolean reachedStop = false;
            for (Stop stop: trip) {
                if(stop.equals(this.stop)){
                    reachedStop = true;
                }else if(reachedStop){
                    for (Trip trip: stop) {
                        if(trip!=this.trip){
                            out.add( new Node(trip, stop, this, depth+1,distance+this.trip.getTrueDistance(this.stop, stop)));
                        }
                    }
                }
            }
            return out;
        }

        public boolean is(Stop end) {
            return stop.equals(end);
        }
    }
}
