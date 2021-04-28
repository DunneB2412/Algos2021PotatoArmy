
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;





public class tripFinder {
	
	int tripID[];
	int arrivalTimes[];
	int departureTimes[];
	int stopID[];
	int stopHeadsign[];
	int pickupType[];
	int dropoffType[];
	double distTravelled[];
	
	//class to return all the data needed
	public class tripsInfo{
		public int tripID[];
		public int arrivalTimes[];
		public int departureTimes[];
		public int stopID[];
		public int stopHeadsign[];
		public int pickupType[];
		public int dropoffType[];
		public double distTravelled[];
		
		public tripsInfo(int tripID[], int arrivalTimes[], int departureTimes[], int stopID[], int stopHeadsign[], int pickupType[],
				int dropoffType[], double distTravelled[]) {
			
			this.tripID = tripID;
			this.arrivalTimes = arrivalTimes;
			this.departureTimes = departureTimes;
			this.stopID = stopID;
			this.stopHeadsign = stopHeadsign;
			this.pickupType = pickupType;
			this.dropoffType = dropoffType;
			this.distTravelled = distTravelled;
		}
	}
	
	tripFinder() throws IOException{
		
		//get the space needed
		int numberOfLines = count("stop_times.txt");
		
		//initialize the arrays to the appropriate size
		tripID = new int[numberOfLines];
		arrivalTimes = new int[numberOfLines];
		departureTimes = new int[numberOfLines];
		stopID = new int[numberOfLines];
		stopHeadsign = new int[numberOfLines];
		pickupType = new int[numberOfLines];
		dropoffType = new int[numberOfLines];
		distTravelled  = new double[numberOfLines];
		
		
		
	}
	
	//counts the number of lines in a txt file;
	public int count(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean endsWithoutNewLine = false;
	        while ((readChars = is.read(c)) != -1) {
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n')
	                    ++count;
	            }
	            endsWithoutNewLine = (c[readChars - 1] != '\n');
	        }
	        if(endsWithoutNewLine) {
	            ++count;
	        } 
	        return count;
	    } finally {
	        is.close();
	    }
	}
	
	//reads in all the data
	public void readInData(String filename) {
		try(BufferedReader in = new BufferedReader(new FileReader("stop_times"))) {
		    String str;
		    String data[] = new String[9];
		    int currentLine = 0;
		    while ((str = in.readLine()) != null) {
		        data = str.split(",");
		        
		        //TODO correctly parse the times
		        tripID[currentLine] = Integer.parseInt(data[0]);
				arrivalTimes[currentLine] = Integer.parseInt(data[0]);
				departureTimes[currentLine] = Integer.parseInt(data[0]);
				stopID[currentLine] = Integer.parseInt(data[0]);
				stopHeadsign[currentLine] = Integer.parseInt(data[0]);
				pickupType[currentLine] = Integer.parseInt(data[0]);
				dropoffType[currentLine] = Integer.parseInt(data[0]);
				distTravelled[currentLine] = Integer.parseInt(data[0]);
		    }
		}
		catch (IOException e) {
		    System.out.println("File Read Error");
		}
	}
	
	//TODO
	//finds the trips with a given arrival time
	public void findTrips(int time) {
		
	}
		
	
}
