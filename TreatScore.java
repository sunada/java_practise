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

    public Map<String, Double> sortMap(Map<String, Double> map){
        List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
//                return o1.getKey().compareTo(o2.getKey());
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        Map<String, Double> sortedMap = new HashMap<String, Double>();
        Iterator<Map.Entry<String, Double>> iter = list.iterator();
        Map.Entry<String, Double> tmp = null;
        while(iter.hasNext()){
            tmp = iter.next();
            sortedMap.put(tmp.getKey(), tmp.getValue());
        }
        return sortedMap;
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
                    sep = line.split("\t");
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
}
