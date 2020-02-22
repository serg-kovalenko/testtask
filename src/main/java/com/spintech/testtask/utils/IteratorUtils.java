package com.spintech.testtask.utils;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class IteratorUtils {
    private IteratorUtils() {
    }

    public static <E> List<E> toList(Iterator<E> iterator) {
        return toStream(iterator)
                .collect(Collectors.toList());
    }

    public static <E> Stream<E> toStream(Iterator<E> iterator) {
        return toStream(iterator, false);
    }

    public static <E> Stream<E> toStream(Iterator<E> iterator, boolean parallel) {
        final Iterable<E> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), parallel);
    }
}
