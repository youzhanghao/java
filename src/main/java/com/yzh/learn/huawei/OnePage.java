package com.yzh.learn.huawei;

import com.google.common.primitives.Ints;
import com.sun.xml.internal.fastinfoset.util.CharArray;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author youzhanghao [m13732916591_1@163.com]
 * @Description:
 * @Date 2019/8/21
 */
public class OnePage {


    /**
     * 计算字符串最后一个单词的长度，单词以空格隔开
     * @param words
     * @return
     */
    public static int getLastWordLength(String words)
    {

        // TODO 数组如何访问最后一个元素
        words = words.trim();
        String[] wordsArr = words.split("\\s");
        return wordsArr[wordsArr.length - 1].length();

    }

    /**
     * 计算字符串第一个单词的长度，单词以空格隔开  流式处理
     * @param words
     * @return
     */
    public static int getFirstWordLengthByStream(String words)
    {

        return Pattern.compile("\\s").splitAsStream(words.trim()).findFirst().get().length();

    }
    /**
     * 写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。
     * @param words
     * @param charWords
     * @return
     */
    public static long getChartNum(String words,Character charWords)
    {
        String wordsUpper = words.trim().toUpperCase();
        String wordsLower = words.trim().toLowerCase();

        int tmp_sum = 0;
        for ( int i = 0, len = words.length(); i < len; i++ ){

            if ( charWords.equals( wordsUpper.charAt(i) ) || charWords.equals(wordsLower.charAt(i)) ) tmp_sum++;
        }
        return tmp_sum;

//        Scanner in = new Scanner(System.in);
//        String s = in.nextLine().toLowerCase();
//        Character ch = in.nextLine().charAt(0);
//        Character.toLowerCase(ch);
//        int sum = 0;
//        for (int i = 0; i < s.length(); i ++ ) {
//            if ( ch.equals(s.charAt(i))) {
//                sum ++ ;
//            }
//        }
//        System.out.println(sum);

    }
    /**
     * 写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。
     * @param words
     * @param charWords
     * @return
     */
    public static long getChartNumByStream(String words,Character charWords)
    {
        words = words.trim().toUpperCase();
        char[] chars = words.toCharArray();
        return Stream.of( chars ).peek(System.out::println).filter(s->s.equals(charWords)).count();

    }

    /**
     * 去重与排序
     * @param n
     * @param randomArray
     * @return
     */
    public static Object[] getSortUniqueArray(int n, int[] randomArray )
    {
        List<Integer> list = Ints.asList(randomArray);
        System.out.println(list.size());
        System.out.println(list.stream().distinct().count());
        System.out.println("array:"+Arrays.stream(randomArray).distinct().count());
        Stream.of(list).distinct().sorted().peek(System.out::println);
        Stream<Integer> res = list.stream().distinct().sorted().peek(s-> System.out.println("单个:"+s));
        return res.collect(Collectors.toList()).toArray();

    }

    /**
     * 连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组
     * @param a
     * @return
     */
//    public static String[] getNewStringArr(String a, int i)
//    {
//        // TODO Java如何给定默认值
//        a = a.trim();
//        int aLen = a.length();
//        String tmp_a;
//        if ( aLen > 0 && aLen < 100  ){
//            // 递归
//            // 条件 如果长度大于8则继续调用
//            if ( a.length() > 8 ){
//                tmp_a = a.substring(0,7);
//                i ++ ;
//                getNewStringArr(a.substring(8,aLen),i);
//            } else {
//                // 长度小于8 填充0
//                String b = "00000000";
//                tmp_a =  aLen == 8 ? a : a.concat(b.substring(0,7-aLen));
//            }
//            // TODO 出现浮点数怎么处理 Math取大于等于商的最小整数
//            String[] res = new String[aLen/8];
//            res[i] = tmp_a;
//            return res;
//
//        }
//    }

    /**
     * 测试方法
     * @param args
     */
    public static void main(String[] args) {

        String words = "hello world";
//        System.out.println(getLastWordLength(words));
        char a = 'o';
//        System.out.println(getChartNum(words,a));
        int n = 15;
        int[] arr = new int[n];
        Random random = new Random();
        IntStream test = random.ints(1, 20).limit(n);
        for ( int i = 0; i < n ; i ++ ){
            arr[i] = random.ints(1,20).findFirst().getAsInt();
        }
        String[] array = {"java", "c"};
        List<String> list = Arrays.asList(array);
        // asList接受的参数是一个泛型的变长参数，基本数据类型无法泛化
        int[] test_arr = {1,3,4,4,3};
//        List<int> list = Arrays.asList(test_arr);
        List<Integer> integerList = Ints.asList(test_arr);
//        System.out.println(integerList);


//        System.out.println(arr);
//        test.forEach(s-> System.out.println(s));
//        Stream<int> stream = Stream.generate((int)Math.floor());
        System.out.println("res:"+getSortUniqueArray(n,arr));


    }

}
