package AiSD.Task6;

import java.util.*;

public class MergeSort {

    public static void main(String[] args) {
        int[] data = new int[20];
        fillArray(data);
        showArray(data);
        data = mergeSort(data);
        showArray(data);
    }

    public static int[] mergeSort(int[] data) {
        int length = data.length;
        if (length <= 1) {
            return data;
        }

        int middleIndex = length / 2;
        int[] a = Arrays.copyOfRange(data, 0, middleIndex);
        int[] b = Arrays.copyOfRange(data, middleIndex, length);
        return merge(mergeSort(a), mergeSort(b));
    }

    private static int[] merge(int[] firstArray, int[] secondArray) {
        int firstLength = firstArray.length;
        int secondLength = secondArray.length;
        int tempA = 0;
        int tempB = 0;
        int length = firstLength + secondLength;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            if (tempB < secondLength && tempA < firstLength) {
                if (firstArray[tempA] > secondArray[tempB]) {
                    result[i] = secondArray[tempB++];
                }
                else {
                    result[i] = firstArray[tempA++];
                }
            } else if (tempB < secondLength) {
                result[i] = secondArray[tempB++];
            } else {
                result[i] = firstArray[tempA++];
            }
        }
        return result;
    }

    public static void fillArray(int[] data){
        Random rand = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = rand.nextInt(30);
        }
    }

    public static void showArray(int[] data){
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
}
