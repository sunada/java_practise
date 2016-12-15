import java.io.*;
import java.util.*;

/**
 * Created by manda.sun on 2016/8/30.
 */
public class TreatScore {
    public Map<String, Double> sortByKey(Map<String, Double> map){
        Map<String, Double> sortedMap = new TreeMap<String, Double>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        sortedMap.putAll(map);
        return sortedMap;
    }

    public void sortMap(Map<String, Double> map){
        List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
//                return o1.getKey().compareTo(o2.getKey());
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        Iterator<Map.Entry<String, Double>> iter = list.iterator();
        Map.Entry<String, Double> tmp = null;
        while(iter.hasNext()){
            tmp = iter.next();
            System.out.println(tmp.getKey() + " " + tmp.getValue());
        }
//        return sortedMap;
    }

    public Map<String, Double> readFile(String filename){
        File file = new File(filename);
        String name = "";
        Double score = 0.0;
        Map<String, Double> map = new HashMap<String, Double>();
        try{
            if(file.isFile() && file.exists()){
                InputStreamReader bf = new InputStreamReader(new FileInputStream(file), "UTF8");
                String line = null;
                BufferedReader bfr = new BufferedReader(bf);
                String[] sep = null;
                while((line = bfr.readLine()) != null){
                    sep = line.split(" +");
                    if(sep.length > 0) {
                        name = sep[0];
                    }else{
                        continue;
                    }
                    if(sep.length > 1) {
                        score = Double.valueOf(sep[1]);
                    }else{
                        score = 0.0;
                    }
                    map.put(name, score);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    public void printMap(Map<String, Double> map){
        for(String key : map.keySet()){
            System.out.println(key + "  " + map.get(key));
        }
    }

    public static void main(String[] args){
        TreatScore ts = new TreatScore();
        Map<String, Double> map = ts.readFile("nameAndSore.txt");
        System.out.println("=======oridinary========= ");
        ts.printMap(map);
        Map<String, Double> sortByKey = ts.sortByKey(map);
        System.out.println("========sortByKey========");
        ts.printMap(sortByKey);
        System.out.println("========sortByValue========");
        ts.sortMap(map);
    }
}
