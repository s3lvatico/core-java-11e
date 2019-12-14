package org.gmnz.corejava11.ch01;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CountLongWords {

    static long t0;

    private static void startTimeMeasurement() {
        t0 = System.currentTimeMillis();
    }

    private static void printElapsedTime() {
        System.out.println("Time elapsed : " + (System.currentTimeMillis() - t0) + " ms");
    }

    public static void main(String[] args) throws Exception {
        final int WORD_LENGTH = 6;
        String contents = new String(Files.readAllBytes(Paths.get("classes/lipsum.txt")));
        List<String> words = List.of(contents.split("\\PL+"));

        long count = 0;
        System.out.println("-- cycling through collection --");
        startTimeMeasurement();
        for (String word : words) {
            if (word.length() > WORD_LENGTH) count++;
        }
        System.out.println(count);
        printElapsedTime();

        System.out.println("-- simple streaming --");
        startTimeMeasurement();
        count = words.stream().filter(word -> word.length() > WORD_LENGTH).count();
        System.out.println(count);
        printElapsedTime();

        System.out.println("-- parallel streaming --");
        startTimeMeasurement();
        count = words.parallelStream().filter(word -> word.length() > WORD_LENGTH).count();
        System.out.println(count);
        printElapsedTime();
    }
}
