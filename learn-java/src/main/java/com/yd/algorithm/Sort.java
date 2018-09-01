package com.yd.algorithm;

/**
 * 排序算法
 *
 * @author Yd on  2018-08-30
 * @description
 **/
public class Sort {

    /**
     * 冒泡排序
     * <p>
     * 1、比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 2、对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 3、针对所有的元素重复以上的步骤，除了最后一个；
     * 4、重复步骤1~3，直到排序完成。
     * </p>
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        //这里不进行非空校验
        for (int i = 0; i < arr.length - 1; i++) { //只需要冒泡 N-1 次
            for (int j = 0; j < arr.length - 1 - i; j++) { //每冒泡一次，后面比较的次数减1
                if (arr[j] < arr[j + 1]) {        // 相邻元素两两对比
                    int temp = arr[j + 1];        // 元素交换
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * <p>
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
     * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 以此类推，直到所有元素均排序完毕。
     * </p>
     *
     * @param arr
     * @return
     */
    public static int[] selectionSort(int[] arr) {
        int minIndex, temp;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] < arr[j]) {
                    minIndex = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        return arr;
    }

    /**
     * 插入排序
     * 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入
     * <p>
     * 1、从第一个元素开始，该元素可以认为已经被排序；
     * 2、取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * 3、如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * 4、重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * 5、将新元素插入到该位置后；
     * 6、重复步骤2~5
     * </p>
     *
     * @param arr
     * @return
     */
    public static int[] insertionSort(int[] arr) {
        int preIndex, current;
        for (int i = 1; i < arr.length; i++) {
            preIndex = i - 1;
            current = arr[i];
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
        return arr;
    }

    /**
     * 希尔排序
     * 简单插入排序的改进版。它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
     * <p>
     * 1、选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
     * 2、按增量序列个数k，对序列进行k 趟排序；
     * 3、每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
     * 仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     * </p>
     *
     * @param arr
     * @return
     */
    public static int[] shellSort(int[] arr) {
        int i, j, gap;
        // gap为步长，每次减为原来的一半。
        for (gap = arr.length / 2; gap > 0; gap /= 2) {
            // 共gap个组，对每一组都执行直接插入排序
            for (i = 0; i < gap; i++) {
                for (j = i + gap; j < arr.length; j += gap) {
                    // 如果a[j] < a[j-gap]，则寻找a[j]位置，并将后面数据的位置都后移。
                    if (arr[j] < arr[j - gap]) {
                        int tmp = arr[j];
                        int k = j - gap;
                        while (k >= 0 && arr[k] > tmp) {
                            arr[k + gap] = arr[k];
                            k -= gap;
                        }
                        arr[k + gap] = tmp;
                    }
                }
            }

        }
        return arr;
    }

    /**
     * 归并排序
     * <p>建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
     * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
     * <p>
     * 1、把长度为n的输入序列分成两个长度为n/2的子序列；
     * 2、对这两个子序列分别采用归并排序；
     * 3、将两个排序好的子序列合并成一个最终的排序序列。
     * </p>
     *
     * @param arr
     * @return
     */
    public static int[] mergeSort(int[] arr) {

        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 1, 1, 3, 4};
//        bubbleSort(arr);
//        selectionSort(arr);
//        insertionSort(arr);
        shellSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }

    }

}
