package lesson4;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Ivan on 23/09/16.
 */
public class CollectionUtilsTest {
    public static void main(String[] args) {
        /* newarraylist test */
        List<Integer> a1 = CollectionUtils.newArrayList();
        List<Number> a2 = CollectionUtils.newArrayList();

        /* add test */
        CollectionUtils.add(a1, 10);
        //add(a1, 3.14);
        //add(a1, 0.2f);

        CollectionUtils.add(a2, 10);
        CollectionUtils.add(a2, 3.14);
        CollectionUtils.add(a2, 0.2f);

        System.out.println("add test: ");
        System.out.println("  a1: " + a1);
        System.out.println("  a2: " + a2);

        /* addall test */
        a2.remove((Integer) 10);
        CollectionUtils.addAll(a1, a2);
        //addAll(a2, a1);

        System.out.println("addAll test: ");
        System.out.println("  a2: " + a2);

        /* indexof test */
        System.out.printf("indexOf test:");

        System.out.println("  indexOf(a2, 10):   " + CollectionUtils.indexOf(a2, 10));
        System.out.println("  indexOf(a2, 3.14): " + CollectionUtils.indexOf(a2, 3.14));
        System.out.println("  indexOf(a2, 0.2f): " + CollectionUtils.indexOf(a2, 0.2f));
        System.out.println("  indexOf(a2, 100):  " + CollectionUtils.indexOf(a2, 100));

        System.out.println("  indexOf(a1, 10):   " + CollectionUtils.indexOf(a1, 10));
        //indexOf(a1, 3.14);
        //indexOf(a1, 0.2f);

        /* limit test */
        System.out.println("limit test: ");
        {
            System.out.println("  empty list");
            List<Integer> a3 = CollectionUtils.newArrayList();
            System.out.println("  limit(a3, 100): " + CollectionUtils.limit(a3, 100));
            System.out.println("  limit(a3, 0):   " + CollectionUtils.limit(a3, 0));
        }

        System.out.println("  not empty list");
        System.out.println("  limit(a2, 15): " + CollectionUtils.limit(a2, 15));
        System.out.println("  limit(a2, 0):  " + CollectionUtils.limit(a2, 0));
        System.out.println("  limit(a2, 1):  " + CollectionUtils.limit(a2, 1));

        /* removeAll test */
        System.out.println("removeAll test:");
        CollectionUtils.removeAll(a2, a1);
        a1.add(100);
        System.out.println("  a2: " + a2);

        /* containsAll test */
        System.out.println("containsAll test: ");
        {
            List<Integer> a3 = CollectionUtils.newArrayList();
            List<Number> a4 = CollectionUtils.newArrayList();

            System.out.println("  a3: " + a3);
            System.out.println("  a4: " + a4);

            System.out.println("  containsAll(a3, a4): " + CollectionUtils.containsAll(a3, a4));
            System.out.println("  containsAll(a4, a3): " + CollectionUtils.containsAll(a4, a3));

            a3.add(10);
            a3.add(15);

            a4.add(10);
            a4.add(15);

            System.out.println("  a3: " + a3);
            System.out.println("  a4: " + a4);

            System.out.println("  containsAll(a3, a4): " + CollectionUtils.containsAll(a3, a4));
            System.out.println("  containsAll(a4, a3): " + CollectionUtils.containsAll(a4, a3));

            a3.add(20);

            System.out.println("  a3: " + a3);
            System.out.println("  a4: " + a4);

            System.out.println("  containsAll(a3, a4): " + CollectionUtils.containsAll(a3, a4));
            System.out.println("  containsAll(a4, a3): " + CollectionUtils.containsAll(a4, a3));
        }

        /* containsAny test */
        System.out.println("containsAny test: ");
        {
            List<Integer> a3 = CollectionUtils.newArrayList();
            List<Number> a4 = CollectionUtils.newArrayList();

            System.out.println("  a3: " + a3);
            System.out.println("  a4: " + a4);

            System.out.println("  containsAll(a3, a4): " + CollectionUtils.containsAny(a3, a4));
            System.out.println("  containsAll(a4, a3): " + CollectionUtils.containsAny(a4, a3));

            a3.add(10);
            a3.add(15);

            a4.add(10);
            a4.add(15);

            System.out.println("  a3: " + a3);
            System.out.println("  a4: " + a4);

            System.out.println("  containsAll(a3, a4): " + CollectionUtils.containsAny(a3, a4));
            System.out.println("  containsAll(a4, a3): " + CollectionUtils.containsAny(a4, a3));

            a3.add(20);

            System.out.println("  a3: " + a3);
            System.out.println("  a4: " + a4);

            System.out.println("  containsAll(a3, a4): " + CollectionUtils.containsAny(a3, a4));
            System.out.println("  containsAll(a4, a3): " + CollectionUtils.containsAny(a4, a3));
        }

        /* range1 test */
        System.out.println("range#1 test: ");
        System.out.println("  range(a1, 1, 9): " + CollectionUtils.range(a1, 1, 9));
        System.out.println("  range(a1, 1, 10): " + CollectionUtils.range(a1, 1, 10));
        System.out.println("  range(a1, 10, 10): " + CollectionUtils.range(a1, 10, 10));

        System.out.println("range#2 test: ");
        List<Number> ll = CollectionUtils.range(a2, 1, 4.5f,
                new Comparator<Number>() {
                    @Override
                    public int compare(Number o1, Number o2) {
                        return Double.compare(o1.doubleValue(), o2.doubleValue());
                    }
                });

        System.out.println("  range(a2, 1, 4.5f, ...): " + ll);
    }
}
