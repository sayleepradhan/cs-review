package sorting;

public class MergeSort {
//    arr, 0,3,6
    private void merge(int[] arr, int start, int mid, int end) {
        int len1 = mid - start + 1;
        int len2 = end - mid;
        int[] left = new int[len1];
        int[] right = new int[len2];

        System.arraycopy(arr,start,left,0,len1);
        System.arraycopy(arr,mid+1,right,0,len2);

        int i = 0;
        int j = 0;
        for (int k = start; k<=end; k++) {
            if (i < len1 && j < len2) {
                if (left[i] <= right[j]) {
                    arr[k] = left[i++];
                } else {
                    arr[k] = right[j++];
                }
            } else {
                if (i < len1) {
                    arr[k] = left[i++];
                } else {
                    arr[k] = right[j++];
                }
            }
        }
    }


    private void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr,mid+1, end);
            merge(arr, start, mid, end);
        }
    }

    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        int[] a = {15,70,85,45,89,2,5,6,3,4,9,1};

        System.out.println();
        for (int p = 0; p < a.length; p++) {
            System.out.print(a[p]);
            if (p != a.length-1) {
                System.out.print(" , ");
            }
        }
        System.out.println();

        ms.mergeSort(a,0,a.length-1);
        for (int p = 0; p < a.length; p++) {
            System.out.print(a[p]);
            if (p != a.length-1) {
                System.out.print(" , ");
            }
        }
        System.out.println();
    }
}
