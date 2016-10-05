package lesson7;

import java.lang.reflect.Proxy;

/**
 * Created by Ivan on 05/10/16.
 */

interface Test {
    @Cache
    double superHardCalculations(double seed);

    double nonCachedCalculations(double seed);
}

class TestImpl implements Test {
    @Cache
    public double superHardCalculations(double seed) {
        for (int i = 0; i < 1000000; ++i)
            seed = seed + Math.sin(i % 180) + Math.pow(seed, Math.cos(seed % 60));
        return seed;
    }

    public double nonCachedCalculations(double seed) {
        return seed + seed;
    }
}

public class Homework {
    public static void main(String[] args) {

        Test test = (Test)
                Proxy.newProxyInstance(
                        ClassLoader.getSystemClassLoader(),
                        new Class[]{Test.class},
                        new CacheProxy(new TestImpl())
                );

        double result = test.superHardCalculations(100.);
        System.out.println("r = " + result);

        for (int i = 0; i < 1000; ++i)
            if (test.superHardCalculations(100.) != result)
                throw new RuntimeException("cached != result");

        test.nonCachedCalculations(200.);

    }
}
