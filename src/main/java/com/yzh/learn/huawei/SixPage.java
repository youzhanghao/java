package com.yzh.learn.huawei;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @Author youzhanghao [m13732916591_1@163.com]
 * @Description:
 * @Date 2019/8/20
 */
public class SixPage {

    public final static int ZERO = 0;
    public final static int STRING_LENGTH = 100;
    public final static int STATUS = 0;
    // 空格
    public final static String SPACE = "";


    // 最小公倍数
    public int minimumCommonMultiple(int a,int b){
        // 范围 [Max(a,b),a*b]
        // 遍历i 条件 i/a & i/b 取模为0
        assert a > 0;assert b > 0;
        int miniValue = Math.max(a,b);
        int maxValue = a*b;
        int result = 0;
        // 可以考虑流处理提高效率
        for ( int i = miniValue; i <= maxValue; i++ ){
            if ( i % a == ZERO && i % b == ZERO ){
                result = i;
                break;
            }
        }
        return result;

    }


    public static int minimumCommonMultipleSecond(int a,int b){

        if ( a <= 0 || b <= 0) return ZERO;
        int minValue = Math.max(a,b);
        int maxValue = a*b;
        // Java生成动态有序流
        int length = maxValue - minValue + 1 ;
        Stream<Integer> integerStream = Stream.iterate(minValue, n->++n).limit(length);
//        System.out.println(Arrays.toString(integerStream.toArray()));
        // 流处理
        // 断言式接口参数
        Predicate<Integer> p1 = n->n % a == 0;
        Predicate<Integer> p2 = n->n % b == 0;
        System.out.println(integerStream.filter(n->(n % a == 0 && n % b == 0)).findFirst().get());

        System.out.println(integerStream.filter(p1.and(p2)).findFirst().get());
//        Optional<Integer> result = integerStream.filter(n-> (n % a == 0 && n % b == 0)).findFirst();
//        return result.get();
        return ZERO;



    }


    public static int minimumCommonMultipleThird(int a,int b){

        if ( a <= 0 || b <= 0) return ZERO;
        int minValue = Math.max(a,b);
        int maxValue = a*b;
        // Java生成动态有序流
        int length = maxValue - minValue + 1 ;
        Stream<Integer> integerStream = Stream.iterate(minValue, n->++n).limit(length);
//        System.out.println(Arrays.toString(integerStream.toArray()));
        // 流处理
        // 断言式接口参数
        Predicate<Integer> p1 = n->n % a == 0;
        Predicate<Integer> p2 = n->n % b == 0;
//        System.out.println(integerStream.filter(n->(n % a == 0 && n % b == 0)).findFirst().get());

        System.out.println(integerStream.filter(p1.and(p2)).findFirst().get());
//        Optional<Integer> result = integerStream.filter(n-> (n % a == 0 && n % b == 0)).findFirst();
//        return result.get();
        return ZERO;



    }

    // 求解立方根
    public static double getCubicRoot(double input){
        // 三个数相乘为input
        // 范围 [1,input] 实际相乘值大于input则报错
//        for ( ){}
        return ZERO;

    }

    // 二分法

    // 字符逆序
    // 将一个字符串str的内容颠倒过来，并输出。str的长度不超过100个字符。
    // TODO 参数验证可否写在函数中？
    public static String reverseString(String words)
    {
        words = words.trim();
        if ( words.length() > STRING_LENGTH || SPACE.equals(words)){
            System.out.println("error params");
            System.exit(STATUS);
        }
        // 翻转 TODO StringBuffer与StringBuilder区别
        StringBuffer result = new StringBuffer();
        int length = words.length();
        for (int i = length - 1; i >= 0; i-- ){
            result.append(words.charAt(i));
        }

        System.out.println(result.toString());
        return result.toString();


    }

    // 从输入任意个整型数，统计其中的负数个数并求所有非负数的平均值
    public static NegativeNumResult getNegativeAndArgResult(int[] num){
        // TODO 查看源码 num.length到底需不需要变量存储
        NegativeNumResult result = new NegativeNumResult();
        int tmp_sum_num = 0;
        int tmp_sum = 0;
        for ( int i = 0; i < num.length; i ++ ){
            if ( num[i] < 0 ) {
                tmp_sum_num++;
                continue;
            }
            tmp_sum += num[i];
        }
        result.setNegativeNum(tmp_sum_num);
        result.setAvg(tmp_sum/(num.length - tmp_sum_num));

        return result;
    }

    // 从输入任意个整型数，统计其中的负数个数并求所有非负数的平均值  流式处理
    public static NegativeNumResult getNegativeAndArgResultByStream(int[] num){

        NegativeNumResult result = new NegativeNumResult();
        // TODO 有何不同 为什么会有错误
        Stream<int[]> stream = Stream.of(num); // 显示指定 int[]
        stream.filter(i->i.length>0);
        Stream.of(num).filter(i-> i.length > 0).peek(s-> System.out.println("jj:"+s.getClass().toString())).count();
        return result;


    }

    // 质数算法  2 到 根号n 无法整除
    // 判断质数
    public static boolean isPrime(int n){
        if ( n <= 3 ) return n > 1;
        for ( int i = 2 ; i <= Math.sqrt(n); i ++ ){
            if ( n % i == 0 ) return false;
        }
        return true;

    }


    public static void main(String[] args) {

        int[] data = new int[2];
        data[0] = 1;
        data[1] = 2;
//        System.out.println(data);
//        getNegativeAndArgResultByStream(data);
        minimumCommonMultipleSecond(2,3);
    }



}
