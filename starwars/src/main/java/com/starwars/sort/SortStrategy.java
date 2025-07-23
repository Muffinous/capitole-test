package com.starwars.sort;

import java.util.Comparator;

public interface SortStrategy<T> extends Comparator<T> {
    String getKey();
}
