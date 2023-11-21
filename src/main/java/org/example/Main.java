package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

enum SortingType {
    BubbleSorter,
    ShellSorter,
    MergeSorter,
    QuickSorter
}

public class Main {
    public static void main(String[] args) {
        for (int count : new int[]{10, 1_000, 10_000, 1_000_000}) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            fill(arrayList, count);
            System.out.printf("\n\nCount: %d --> ", count);
            printArray(arrayList);

            for (SortingType type : new SortingType[]{SortingType.BubbleSorter, SortingType.MergeSorter, SortingType.QuickSorter, SortingType.ShellSorter}) { // BubbleSorter - долго піздец
                Sorter sorter = fabricSorter(type);
                ArrayList<Integer> input = new ArrayList<>(arrayList);

                long startTime = System.currentTimeMillis();
                ArrayList<Integer> sortedList = sorter.sort(input);
                long endTime = System.currentTimeMillis();

                System.out.printf("%s: %dms --> ", type, endTime - startTime);
                printArray(sortedList);

            }
        }
    }

    private static void fill(ArrayList<Integer> arrayList, int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            arrayList.add(random.nextInt(count));
        }
    }

    private static void printArray(ArrayList<Integer> arrayList) {
        int limit = Math.min(arrayList.size(), 50);
        List<Integer> printList = arrayList.subList(0, limit);
        System.out.println(printList.toString());
    }

    public static Sorter fabricSorter(SortingType type) {
        switch (type) {
            case BubbleSorter -> {
                return new BubbleSorter();
            }
            case MergeSorter -> {
                return new MergeSorter();
            }
            case QuickSorter -> {
                return new QuickSorter();
            }
            case ShellSorter -> {
                return new ShellSorter();
            }
            default -> {
                return null;
            }
        }
    }
}