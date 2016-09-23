package lesson4;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivan on 22/09/16.
 */
class CountMapTest {

    public static void main(String[] args) {

        CountMapTest cmt = new CountMapTest();

        try {
            cmt.testAddAndGetCount();
            cmt.testRemove();
            cmt.testSize();
            cmt.testToMap();
            cmt.testToMap1();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + " failure");
            return;
        }
    }

    void testAddAndGetCount() throws java.lang.Exception {
        CountMap<String> c = new CountMap<>();

        c.add("a");
        c.add("a");
        c.add("a");

        c.add("b");
        c.add("b");
        c.add("b");

        c.add("c");
        c.add("c");
        c.add("c");

        if (c.getCount("a") != 3 || c.getCount("b") != 3 || c.getCount("c") != 3 || c.getCount("abc") != 0)
            throw new Exception("add() test#1");

        CountMap<Number> c2 = new CountMap<>();

        int n1 = 10;
        float n2 = 10.2f;
        double n3 = 10.22;

        c2.add(n1);
        c2.add(n2);
        c2.add(n3);

        if (c2.getCount(n1) != 1 || c2.getCount(n2) != 1 || c2.getCount(n3) != 1 || c2.getCount(10000) != 0)
            throw new Exception("add() test#2");

        CountMap<Integer> map = new CountMap<>();

        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        if (map.getCount(5) != 2 || map.getCount(6) != 1 || map.getCount(10) != 3)
            throw new Exception("add() test#3");
    }

    void testRemove() throws java.lang.Exception {
        CountMap<String> c = new CountMap<>();
        c.add("a");
        c.add("a");
        c.add("a");

        c.add("b");
        c.add("b");
        c.add("b");

        c.add("c");
        c.add("c");
        c.add("c");

        if (c.remove("c") != 3 || c.getCount("c") != 0)
            throw new Exception("remove() test");
    }

    void testSize() throws java.lang.Exception {
        CountMap<String> c = new CountMap<>();

        if (c.size() != 0)
            throw new Exception("initial size() test");

        c.add("a");
        c.add("a");
        c.add("a");

        c.add("b");
        c.add("b");
        c.add("b");

        c.add("c");
        c.add("c");
        c.add("c");

        if (c.size() != 3)
            throw new Exception("size() test");
    }

    void testAddAll() throws java.lang.Exception {
        CountMap<Number> dst = new CountMap<>();
        CountMap<Integer> src = new CountMap<>();

        dst.add(100);
        dst.add(300);

        src.add(100);
        src.add(100);
        src.add(200);

        dst.addAll(src);

        if (dst.getCount(100) != 3 || dst.getCount(200) != 1 ||
                dst.getCount(300) != 1 || dst.getCount(400) != 0)
            throw new Exception("addAll() test");
    }

    void testToMap() throws java.lang.Exception {
        CountMap<String> c = new CountMap<>();
        c.add("a");
        c.add("a");
        c.add("a");

        c.add("b");
        c.add("b");
        c.add("b");

        c.add("c");
        c.add("c");
        c.add("c");

        Map<String, Integer> mm = c.toMap();

        if (!compareCountMapWithMap(c, mm))
            throw new Exception("toMap1() test");
    }

    void testToMap1() throws java.lang.Exception {
        CountMap<Integer> cc = new CountMap<>();

        cc.add(1);
        cc.add(2);
        cc.add(3);
        cc.add(1);

        HashMap<Number, Integer> mm = new HashMap<>();

        cc.toMap(mm);

        if (!compareCountMapWithMap(cc, mm))
            throw new Exception("toMap2() test");
    }

    private <T, V extends T> boolean compareCountMapWithMap(CountMap<V> cc, Map<? extends T, ? extends Integer> mm) {
        if (cc.size() != mm.size())
            return false;

        for (Map.Entry<? extends T, ? extends Integer> e : mm.entrySet()) {
            V key = (V) e.getKey();

            if (cc.getCount(key) != e.getValue())
                return false;
        }

        return true;
    }
}
