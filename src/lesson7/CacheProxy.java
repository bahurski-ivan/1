package lesson7;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ivan on 05/10/16.
 */
public class CacheProxy implements InvocationHandler {

    private final Object delegate;
    private Map<Method, Map<List<Object>, Object>> cache = new HashMap<>();

    public CacheProxy(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result;

        if (method.isAnnotationPresent(Cache.class)) {

            Map<List<Object>, Object> mm = cache.get(method);
            List<Object> argList = Arrays.asList(args);

            if (mm == null) {
                mm = new HashMap<>();
                cache.put(method, mm);
            }

            if (!mm.containsKey(argList)) {
                result = method.invoke(delegate, args);
                mm.put(argList, result);
            } else
                result = mm.get(argList);
        } else
            result = method.invoke(delegate, args);

        return result;
    }
}
