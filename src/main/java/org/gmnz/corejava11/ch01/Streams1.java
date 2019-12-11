package org.gmnz.corejava11.ch01;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Streams1 {

    public static void main(String[] args) {
        // commento test
        System.out.println("oh");

        Set<String> numbers = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            numbers.add(String.valueOf(i));
        }
        System.out.println("----- Executing sequential stream");
        long t0 = System.currentTimeMillis();
        execute(numbers.stream());
        long delta = System.currentTimeMillis() - t0;
        System.out.printf("processing took %d ms%n%n", delta);

        System.out.println("----- Executing parallel   stream");
        t0 = System.currentTimeMillis();
        execute(numbers.parallelStream());
        delta = System.currentTimeMillis() - t0;
        System.out.printf("processing took %d ms%n%n", delta);
    }

    private static void execute(Stream<String> stream) {
        stream.forEach(value -> {
            System.out.printf("%s | thread %-40s | value : %s%n", LocalTime.now(), Thread.currentThread().getName(), value);
            try {
                TimeUnit.MILLISECONDS.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}