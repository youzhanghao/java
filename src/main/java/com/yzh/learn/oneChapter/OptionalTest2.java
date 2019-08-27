package com.yzh.learn.oneChapter;

import jdk.nashorn.internal.runtime.options.Option;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author youzhanghao [m13732916591_1@163.com]
 * @Description:
 * @Date 2019/8/11
 */
public class OptionalTest2 {

    public static void main(String[] args) throws IOException {
        // 读取文件中的内容，返回含有s的第一个单词 没有返回no contents
        String  contents = new String(Files.readAllBytes(Paths.get("alice.txt")),StandardCharsets.UTF_8);

        List<String> wordsList = Arrays.asList(contents.split("\\PL+"));
//        System.out.println(wordsList);

        // 流处理
//        Optional<String> result = wordsList.stream().filter(s -> s.contains("fred")).peek(s -> System.out.println(s)).findFirst();
//        System.out.print("result:"+result.orElse("No word") + " contains fred");

//        System.out.println(wordsList.stream());
        Optional<String> optionalValue = wordsList.stream()
                .filter(s -> s.contains("fred"))
                .findFirst();
        System.out.println(optionalValue.orElse("No word") + " contains fred");

        String test_str = "hello";
        if (test_str.contains("fred")) System.out.println("yes");


    }
}
