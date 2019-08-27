package com.yzh.learn.oneChapter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @Author youzhanghao [m13732916591_1@163.com]
 * @Description:
 * @Date 2019/8/10
 */
public class LearnStream {


    public static void main(String[] args) {

        {
            String contents = null;
            try {
                // Read file into string
                contents = new String(Files.readAllBytes(Paths.get("alice.txt")), StandardCharsets.UTF_8);


            } catch (IOException e) {
                e.printStackTrace();
            }
            // split into words;nonletters are delimiters
            List<String> words = Arrays.asList(contents.split("\\PL+"));
            System.out.println(words);

            long count = words.stream().filter(w->w.length() > 7).count();
            // 并行流处理 TODO 有线程问题么？
            long paramCount = words.parallelStream().filter(w->w.length() > 7).count();
            System.out.println(count);
//            System.out.println(contents);

        }
    }
}
