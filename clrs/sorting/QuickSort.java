package clrs.sorting;

import java.util.Random;

public class QuickSort {
    private int randomizedPartition(int[] arr, int start, int end) {
        Random random = new Random();
        int randomIndex = random.nextInt((end-start) + 1)+start;
        int temp = arr[end];
        arr[end] = arr[randomIndex];
        arr[randomIndex] = temp;
        return getPartition(arr,start,end);
    }
    private void randomizedSort(int[] arr, int start, int end) {
        if (start < end) {
            int partition = randomizedPartition(arr, start, end);
            randomizedSort(arr, start, partition-1);
            randomizedSort(arr, partition+1, end);
        }
    }
    private void sort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = getPartition(arr, start, end);
            sort(arr,start, mid-1);
            sort(arr, mid+1, end);
        }
    }

    private int getPartition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int partitionCounter = start - 1;
        for (int currIndex = start; currIndex<end; currIndex++) {
            if (arr[currIndex] <= pivot) {
                partitionCounter++;

                //swap the values of partitionCounter and currIndex index
                int temp = arr[partitionCounter];
                arr[partitionCounter] = arr[currIndex];
                arr[currIndex] = temp;
            }
        }

        // swap the values of the pivot and the index next to the partitionCounter
        int temp = arr[end];
        arr[end] = arr[partitionCounter+1];
        arr[partitionCounter+1] = temp;

        return partitionCounter+1;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = {2,8,7,1,3,5,6,4};
        int[] arr2 = {2,8,7,1,3,5,6,4};
        quickSort.sort(arr,0,arr.length-1);
        quickSort.randomizedSort(arr2,0,arr2.length-1);
        System.out.println("Quick sort results:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length-1) {
                System.out.print(" , ");
            }
        }

        System.out.println("\n");
        System.out.println("Randomized Quick sort results:");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i]);
            if (i != arr2.length-1) {
                System.out.print(" , ");
            }
        }

    }
}
