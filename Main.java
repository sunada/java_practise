import java.util.Map;

public class Main {

    public static void main(String[] args) {
        TreatScore treatScore = new TreatScore();
        Map<String, Double> map = treatScore.readFile("src/nameAndSore.txt");
        treatScore.printMap(map);
        System.out.println("================");
        Map<String,Double> mapSortedByKey = treatScore.sortByKey(map);
        treatScore.printMap(mapSortedByKey);
        System.out.println("================");
        treatScore.sortMap(map);
    }
}
