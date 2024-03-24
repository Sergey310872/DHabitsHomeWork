package org.digitalhabits;

import org.digitalhabits.impl.ParallelSorter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParallelSorterTest {

    private int[] array = new int[]{1, 6, 2, 8, 1, 25, 8, 31, 0};

    @Test
    @DisplayName("Parrallel sorter should sort array correctly")
    void sortTest() {
        ParallelSorter sorter = new ParallelSorter();

        sorter.sort(array);

        assertThat(array).isSorted();
    }
}