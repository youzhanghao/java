package com.yzh.learn.oneChapter;

import java.util.Arrays;
import java.util.List;

/**
 * @Author youzhanghao [m13732916591_1@163.com]
 * @Description:
 * @Date 2019/8/21
 */
public class StreamResult {

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1,2,3,4);
        System.out.println(intList);
        intList.stream().forEach(System.out::println);
        System.out.println(intList.stream().toArray());
        Integer[] result = intList.stream().toArray(Integer[]::new);
        System.out.println(result);
    }
}
