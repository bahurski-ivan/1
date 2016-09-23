package lesson4;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivan on 22/09/16.
 */
public class CountMap<T> {

    //добавляет элемент в этот контейнер.
    <V extends T> void add(V o) {
        Integer value = view.get(o);

        if (value == null)
            view.put(o, 1);
        else
            view.put(o, value + 1);
    }

    //Возвращает количество добавлений данного элемента
    <V extends T> int getCount(V o) {
        Integer value = view.get(o);
        return value == null ? 0 : value;
    }

    //Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
    <V extends T> int remove(V o) {
        return view.remove(o);
    }

    //количество разных элементов
    int size() {
        return view.size();
    }

    //Добавить все элементы из source в текущий контейнер, при совпадении ключей, суммировать значения
    void addAll(CountMap<? extends T> source) {
        for (Map.Entry<? extends T, Integer> e : source.view.entrySet()) {
            Integer value = view.get(e.getKey());

            if (value == null)
                view.put(e.getKey(), e.getValue());
            else
                view.put(e.getKey(), e.getValue() + value);
        }
    }

    //Вернуть java.util.Map. ключ - добавленный элемент, значение - количество его добавлений
    Map<T, Integer> toMap() {
        HashMap<T, Integer> mm = new HashMap<T, Integer>();
        toMap(mm);
        return mm;
    }

    //Тот же самый контракт как и toMap(), только всю информацию записать в destination
    void toMap(Map<? super T, ? super Integer> destination) {
        destination.clear();
        destination.putAll(view);
    }

    private HashMap<T, Integer> view = new HashMap<>();
}
