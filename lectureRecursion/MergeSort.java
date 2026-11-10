package lectureRecursion;

import java.util.Arrays;

public class MergeSort {
  public static void main(String[] args) {
    int[] arr = { 4, 3, 7, 1, 9, 2 };
    System.out.println(Arrays.toString(arr));
    mergeSort(arr, 0, arr.length - 1);
    System.out.println("\nSorted:\n");
    System.out.println(Arrays.toString(arr));
  }

  public static void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
      int mid = ((right - left) / 2) + left;

      mergeSort(arr, left, mid);
      mergeSort(arr, mid + 1, right);

      merge(arr, left, right, mid);
    }
  }

  public static void merge(int[] arr, int left, int right, int mid) {
    // Find sizes of two subarrays to be merged
    int n1 = mid - left + 1;
    int n2 = right - mid;

    // Create temporary arrays
    int[] leftArray = new int[n1];
    int[] rightArray = new int[n2];

    // Copy data to temporary arrays
    for (int i = 0; i < n1; ++i) {
      leftArray[i] = arr[left + i];
    }
    for (int j = 0; j < n2; ++j) {
      rightArray[j] = arr[mid + 1 + j];
    }

    int i = 0, j = 0;

    int k = left;
    while (i < n1 && j < n2) {
      if (leftArray[i] < rightArray[j]) {
        arr[k] = leftArray[i];
        k++;
        i++;
      } else {
        arr[k] = rightArray[j];
        k++;
        j++;
      }
    }

    while (i < n1) {
      arr[k] = leftArray[i];
      i++;
      k++;
    }

    while (j < n2) {
      arr[k] = rightArray[j];
      j++;
      k++;
    }
  }
}
