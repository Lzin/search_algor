package com.liz.seq_search;

public class SeqSearchDemo {
    private static int count=0;
    public static void main(String[] args) {
        //无序序列
        int arr[] = {1, 9, 11, -1, 34, 55};
        int index=seqSearch(arr,55);
        if(index==-1){
            System.out.println("未找到");
        }
        System.out.println("代码执行了"+count+"次"+" "+"找到的位置为"+index);

    }

    //线性查找: 逐一比对，发现有相同的值时，返回下标
    public static int seqSearch(int[] arr, int value) {

        for (int i = 0; i < arr.length; i++) {
            count++;
            if(arr[i]==value){
                return i;
            }
        }
        return -1;
    }
}
