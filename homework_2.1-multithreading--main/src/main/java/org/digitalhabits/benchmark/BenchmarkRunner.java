package org.digitalhabits.benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

public class BenchmarkRunner {

    public static void main(String[] args) throws Exception {
        final var options = new OptionsBuilder()
                .measurementIterations(2)
                .measurementTime(TimeValue.seconds(5l))
                .warmupIterations(2)
                .warmupTime(TimeValue.seconds(5l))
                .timeUnit(TimeUnit.MILLISECONDS)
                .forks(1)
                .build();
        new Runner(options).run();
    }

}
