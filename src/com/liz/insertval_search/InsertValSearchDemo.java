package com.liz.insertval_search;

//采取自适应mid的方法进行
//自适应公式: left+(right-low)*(findVal-arr[left])/(arr[high]-arr[low])

/**
 * 注意事项:
 *      对于数据量较大，关键词分布比较均匀的查找而言，采用插值查找速度较快
 *      关键字分布不均匀的情况下，该方法不一定比折半查找法好
 * */
public class InsertValSearchDemo {
    public static int count=0;
    public static void main(String[] args) {
        int arr[] = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int midValue=insertValSearch(arr,0,arr.length-1,20);
        System.out.println("代码执行了"+count+"次"+" "+"找到的位置为"+midValue);

    }

    public static int insertValSearch(int[] arr, int left, int right, int findVal) {
        count++;
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        //采用自适应算法求mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {//说明应该向右进行递归
            return insertValSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//说明应该向左进行递归
            return insertValSearch(arr,left,mid-1,findVal);
        }else{
            return mid;
        }
    }
}
