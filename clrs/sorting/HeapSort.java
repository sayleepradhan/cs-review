package clrs.sorting;

public class HeapSort {
    private int[] heap;
    private int size;

    HeapSort(int[] arr) {
        heap = arr;
    }

    private int getHeapSize() {
        return size;
    }

    private int getLeftChildIndex(int i) {
        return (2*i)+1;
    }

    private int getRightChildIndex(int i) {
        return (2*i)+2;
    }

    private void setHeapSize(int heapSize) {
        if (heapSize <= heap.length) {
            size = heapSize;
        }
    }

    private void maxHeapify(int i) {
        int largestValueIndex = -1;
        int leftChildIndex = getLeftChildIndex(i);
        int rightChildIndex = getRightChildIndex(i);

        if (
                leftChildIndex < getHeapSize()
                        && heap[leftChildIndex] > heap[i]
        ) {
            largestValueIndex = leftChildIndex;
        } else {
            largestValueIndex = i;
        }

        if (
                rightChildIndex < getHeapSize()
                        && heap[rightChildIndex] > heap[largestValueIndex]
        ) {
            largestValueIndex = rightChildIndex;
        }

        if (largestValueIndex != i) {
            // swap the values of i and largestValueIndex
            int temp = heap[i];
            heap[i] = heap[largestValueIndex];
            heap[largestValueIndex] = temp;

            maxHeapify(largestValueIndex);
        }
    }

    private void buildMaxHeap() {
        setHeapSize(heap.length);
        for (int i = heap.length/2; i>=0; i--) {
            maxHeapify(i);
        }
    }

    private void heapSort() {
        buildMaxHeap();
        for (int i = heap.length-1; i>=0; i--){
            // swap the first element with the last element
            // remove the last element from the heap
            // maxHeapify on the new first element
            int lastHeapElementIndex = getHeapSize()-1;
            int temp = heap[0];
            heap[0] = heap[lastHeapElementIndex];
            heap[lastHeapElementIndex] = temp;

            setHeapSize(getHeapSize()-1);
            maxHeapify(0);
        }
    }

    public static void main(String[] args) {
        int[] arr = {17, 12, 87, 54, 32, 65, 56};

        HeapSort heapSort = new HeapSort(arr);
        heapSort.heapSort();
        System.out.println();

        for (int p = 0; p < arr.length; p++) {
            System.out.print(arr[p]);
            if (p != arr.length-1) {
                System.out.print(" , ");
            }
        }
    }

}
