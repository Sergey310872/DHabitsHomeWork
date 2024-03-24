package org.digitalhabits.impl;

import org.digitalhabits.Sorter;

import java.util.Arrays;

/**
 * Implementation which with you will compare your {@link ParallelSorter}
 */
public class DefaultJavaSorter implements Sorter {

    @Override
    public void sort(int[] source) {
        Arrays.sort(source);
    }

}
