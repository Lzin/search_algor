package com.liz.fibonacci_search;

import java.util.Arrays;

//1.有序数组
//2.借助斐波那契数列找到分割点
//3.斐波那契数列找的不是值 而是位置
//4.有两次自适应过程
public class FibonacciSearchDemo {
    private static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println("index: "+fibSearch(arr, 1000));
    }

    //因为使用到斐波那契数列，因此我们要先获取该数列（递归或者非递归的方式）
    //mid=low+F(k-1)-1
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //编写斐波那契查找算法
    //使用非递归的方式

    /**
     * a: 数组
     * key: 查找的数字
     * return 返回对应下标，如果没有返回-1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;
        int f[] = fib();//获取到斐波那契数列

        //将顺序表的长度和斐波那契数列进行自适应
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[K]可能大于数组的长度，因此需要使用Arrays构造一个新的数组，并指向a[],不足的部分会使用0去填充
        int[] temp = Arrays.copyOf(a, f[k]);
        //temp={1,8,10,89,1000,1234,0,0,0}->{1,8,10,89,1000,1234,1234，1234}
        //新构建的数组用最后数进行填充
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        //康一康temp中


        /**
         * 前面一系列操作完成的是对于斐波那契和待排序数组之间的匹配，以及待排序数组自身的拓展
         * */
        //使用while循环处理找到我们的数
        while (low <= high) {
            //借助斐波那契数列对mid进行定位
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {//向左查找
                high = mid - 1;
                /**
                 * 全部元素 = 前面元素+后面元素
                 * f[k]=f[k-1]+f[k-2]
                 * 前面有f[k-1]个元素可以继续进行拆分
                 * 本次循环条件mid = low+f[k-1]-1
                 * 下次循环条件mid= low+f[k-1-1]-1
                 * */
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                //下次循环为 mid= f[k-1-2]-1
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
