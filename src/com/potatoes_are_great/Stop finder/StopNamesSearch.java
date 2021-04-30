import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**@author TIANZE ZHANG*/


public class StopNamesSearch {
    private static ArrayList<String> stopNames;
    private static ArrayList<String>stopIds;

    //return TST containing all the stop names
    public static TST getStopsInTST() {
        int i=0;
        stopNames=new ArrayList<>();
        stopIds= new ArrayList<>();

        TST<Integer> st = new TST<>();
        try (BufferedReader br = new BufferedReader(new FileReader("stops.txt"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] stop=line.split(",");
                stopNames.add(stop[2]);
                stopIds.add(stop[0]);

                String[] stopNameSplited=stopNames.get(i).split(" ");
                String firstStr = stopNameSplited[0];
                StringBuilder modifiedStop= new StringBuilder();
                for(int j=1;j<stopNameSplited.length;j++){
                    modifiedStop.append(stopNameSplited[j]);
                    modifiedStop.append(" ");
                }
                modifiedStop.append(firstStr);
                st.put(modifiedStop.toString(), i);
                i++;
            }
        }catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return st;
    }
    //return all stops names as arraylist
    public static ArrayList<String> getStopNamesInList(){

        return stopNames;
    }
    //return all stops ids as array list
    public static ArrayList<String> getStopIdsInList(){

        return stopIds;
    }
    //Testing
    public static void main(String[] args){

        TST<String>st = getStopsInTST();
        System.out.println("Test1");
        for (String s : st.keysWithPrefix("KINGSWAY AVE N"))
            System.out.println(s);

        System.out.println("Test2");
        for (String s : st.keysWithPrefix("KITTSON PARKWAY FS SUMMIT CRES EB"))
            System.out.println(s);
        //given an stop name and access stop id example
        System.out.println("Test3");
        ArrayList<String>stopNamesList=getStopNamesInList();
        ArrayList<String>stopIdsList=getStopIdsInList();
        for (int i=0;i<stopNamesList.size();i++){
            if(stopNamesList.get(i).equals("WB ROBSON DR FS PURCELL DR")){
                System.out.println(stopIdsList.get(i));
            }
        }


    }


}
