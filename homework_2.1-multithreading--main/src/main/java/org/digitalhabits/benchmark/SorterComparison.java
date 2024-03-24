package org.digitalhabits.benchmark;

import org.digitalhabits.Sorter;
import org.digitalhabits.impl.DefaultJavaSorter;
import org.digitalhabits.impl.ParallelSorter;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Fork(jvmArgsAppend = {"-Xms2g", "-Xmx2g"})
public class SorterComparison {

    private final Sorter defaultSorter = new DefaultJavaSorter();
    private final Sorter parallelSorter = new ParallelSorter();

    @State(Scope.Benchmark)
    public static class ArrayContainer {

        @Param({"10000", "1000000", "10000000"})
        int arraySize;

        private int[] array;
        private int[] arrayCopy;

        @Setup(Level.Trial)
        public void setUp() {
            array = new Random().ints(arraySize).toArray();
        }

        @Setup(Level.Invocation)
        public void createCopy() {
            arrayCopy = array.clone();
        }

        int[] getArrayToSort() {
            return arrayCopy;
        }
    }

    @Benchmark
    public void parallelSortBenchmark(ArrayContainer d, Blackhole b) {
        int[] arr = d.getArrayToSort();
        parallelSorter.sort(arr);
        b.consume(arr);
    }

    @Benchmark
    public void defaultSortBenchmark(ArrayContainer d, Blackhole b) {
        int[] arr = d.getArrayToSort();
        defaultSorter.sort(arr);
        b.consume(arr);
    }
}