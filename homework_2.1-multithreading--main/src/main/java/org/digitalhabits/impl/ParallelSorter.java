package org.digitalhabits.impl;

import org.digitalhabits.Sorter;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Your parallel version of {@link Sorter} to implement
 */
public class ParallelSorter implements Sorter {

    @Override
    public void sort(int[] source) {
//        throw new UnsupportedOperationException("To implement");
        ForkJoinPool fjPool = new ForkJoinPool();
        ForkJoinQuicksortTask mainTask = new ForkJoinQuicksortTask(source);
        fjPool.invoke(mainTask);

        System.arraycopy(mainTask.result, 0, source, 0, source.length);
    }
}

class ForkJoinQuicksortTask extends RecursiveAction {
    int[] arrayA;
    int[] result;

    protected ForkJoinQuicksortTask(int[] a) {
        this.arrayA = a;
        this.result = new int[a.length];
    }

    @Override
    protected void compute() {
        if (arrayA == null) {
            result = null;
        } else if (arrayA.length < 2) {
            result = arrayA;
        } else {
            int[] arrayB = new int[arrayA.length / 2];
            System.arraycopy(arrayA, 0, arrayB, 0, arrayA.length / 2);

            int[] arrayC = new int[arrayA.length - arrayA.length / 2];
            System.arraycopy(arrayA, arrayA.length / 2, arrayC, 0, arrayA.length - arrayA.length / 2);

            ForkJoinQuicksortTask taskB = new ForkJoinQuicksortTask(arrayB);
            ForkJoinQuicksortTask taskC = new ForkJoinQuicksortTask(arrayC);
            invokeAll(taskB, taskC);

            arrayB = taskB.result;
            arrayC = taskC.result;

            int positionB = 0, positionC = 0;
            for (int i = 0; i < result.length; i++) {
                if ((i - positionB) >= arrayB.length) {
                    result[i] = arrayC[i - positionC];
                    positionB++;
                } else if ((i - positionC) >= arrayC.length) {
                    result[i] = arrayB[i - positionB];
                    positionC++;
                } else if (arrayB[i - positionB] < arrayC[i - positionC]) {
                    result[i] = arrayB[i - positionB];
                    positionC++;
                } else {
                    result[i] = arrayC[i - positionC];
                    positionB++;
                }
            }
        }
    }
}