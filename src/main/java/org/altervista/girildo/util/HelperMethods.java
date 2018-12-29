package org.altervista.girildo.util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class HelperMethods {
    private HelperMethods(){}

    /**
     * Fills result with the list of all possible permutations of a.
     * @param a The original array.
     * @param result The list of permuted lists.
     * @param <T> Type of elements contained in a.
     */
    public static <T> void permuteList(T[] a, int size, ArrayList<List<T>> result) {

        //a = (T[]) Arrays.stream(a).distinct().collect(Collectors.toList()).toArray();

        if (size == 1) {
            result.add(Arrays.asList(Arrays.copyOf(a, a.length)));
            return;
        }

        for (int i = 0; i < size; i++) {
            permuteList(a, size-1, result);

            if (size % 2 == 1) {
                T temp = a[0];
                a[0] = a[size - 1];
                a[size - 1] = temp;
            }


            else {
                T temp = a[i];
                a[i] = a[size - 1];
                a[size - 1] = temp;
            }
        }
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = new HashSet<>();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
