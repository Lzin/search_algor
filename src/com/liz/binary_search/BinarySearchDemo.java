package com.liz.binary_search;

//注: 使用二分查找的前提是该数组是有序的。

//优化: 当数组中存在重复值的时候，可以全部找到

import java.util.ArrayList;

/**
 * 1. 在找到mid的时候，不要直接返回，
 * 2. 向mid索引值的左边扫描，将所有满足查找值的元素下标加入到一个集合arrayList中
 * 3. 向mid的索引值的右边扫描,将所有满足查找值的元素下标，加入到索引值中
 * 4. 返回arrayList
 * */
public class BinarySearchDemo {
    /**
     * arr: 数组
     * left: 左边索引
     * right: 右边索引
     * findVal: 待寻找的值
     */
    public static int count=0;
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        count++;
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        //如果没有找到
        if (left > right) {
            return -1;
        }
        //向右递归
        if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }


    //优化后的二分查找
    public static ArrayList binarySearchUp(int[] arr, int left, int right, int findVal) {
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        //如果没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        //向右递归
        if (findVal > midVal) {
            return binarySearchUp(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return binarySearchUp(arr, left, mid - 1, findVal);
        } else {
            //这里已经找到了mid值
            ArrayList<Integer> resIndexList=new ArrayList<Integer>();

            //向左扫描
            int temp=mid-1;
            while(true){
                //已经无法再向左扫描了
                if(temp<0 || arr[temp]!=findVal){
                    break;
                }
                //否则就把temp放入到集合中
                resIndexList.add(temp);
                temp--;
            }
            resIndexList.add(mid);

            //向右扫描
            temp=mid+1;
            while(true){
                //已经无法再向右扫描了
                if(temp>arr.length-1 || arr[temp]!=findVal){
                    break;
                }
                //否则就把temp放入到集合中
                resIndexList.add(temp);
                temp++;
            }
            return resIndexList;
        }

    }
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000,1000, 1234};
//        int resultIndex = binarySearch(arr, 0, arr.length, 1000);
//        if (resultIndex == -1) {
//            System.out.println("未找到当前数据");
//            return;
//        }
//        System.out.println("代码执行了"+count+"次"+" "+"找到的位置为:"+resultIndex);
        ArrayList<Integer>list=binarySearchUp(arr,0,arr.length,1000);
        if(list.size()==0){
            System.out.println("未找到当前数据");
            return;
        }
        System.out.println("找到的索引值为"+list);
    }
}
