import java.util.HashMap;
import java.util.Map;

public class Most_Popular_Canteen_Order {

    static Object[] mostPopular(String[] orders) {
        Map<String, Integer> frequency = new HashMap<>();

        for (String item : orders) {
            frequency.put(
                item,
                frequency.getOrDefault(item, 0) + 1
            );
        }

        String bestItem = orders[0];
        int bestCount = 0;

        for (String item : orders) {
            int count = frequency.get(item);

            if (count > bestCount) {
                bestCount = count;
                bestItem = item;
            }
        }

        return new Object[]{bestItem, bestCount};
    }

    public static void main(String[] args) {
        String[] orders = {
            "dosa", "idli", "vada", "dosa",
            "idli", "dosa", "tea"
        };

        Object[] result = mostPopular(orders);

        System.out.println(
            "(\"" + result[0] + "\", " + result[1] + ")"
        );

        String[] orders2 = {"tea", "coffee", "coffee", "tea"};

        Object[] result2 = mostPopular(orders2);

        System.out.println(
            "(\"" + result2[0] + "\", " + result2[1] + ")"
        );
    }
}