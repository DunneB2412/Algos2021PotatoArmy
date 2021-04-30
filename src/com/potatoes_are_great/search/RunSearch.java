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
    public boolean isReady(){
        return ready;
    }


    @Override
    public void run() {
        while(!schedule.ready);// System.out.println("waiting on schedule");
        //while (true){
        while(ready || start==null || end==null){
            //System.out.println("waiting on sollection");
        }
        //if(schedule.ready && !ready && start!=null && end!=null){
            generatePlan();
        //}
    }

    private void generatePlan() {
        double min = Double.POSITIVE_INFINITY;
        for(Trip trip: start){
            if(trip.getTrueDistance(start,end)<min){
                plan = trip;
            }
        }
        ready = true;
    }

    private void astarSearch(){
        HashSet<Trip> visited = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (Trip trip: start) {
            queue.add(new Node(trip,start, null,0,0));
        }
        Node current = queue.peek();
        while (!queue.isEmpty() && !(current = queue.poll()).is(end)){
            if(!visited.contains(current.trip)){
                System.out.println(current.distance+","+current.stop.getGpsLocation().getDist(end.getGpsLocation())+"||"+queue.size()+":"+current.depth);
                System.out.println();
                visited.add(current.trip);
                for (Node node:current.getChildren()) {
                    if (!visited.contains(node.trip)) {
                        queue.add(node);
                    }
                }
            }
        }
        plan = new Trip(-1);
        while (current!=null){

            //plan.add(current.)
            current = current.parent;
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
            double h = stop.getGpsLocation().getDist(stop.getGpsLocation());
            if (this.trip.contains(end)){
                h-=10;
            }
            return h*10;
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
            if(depth<3){
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
            }
            return out;
        }

        public boolean is(Stop end) {
            return stop.equals(end);
        }
    }
}
