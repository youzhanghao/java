package com.yzh.learn.oneChapter;


import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author youzhanghao [m13732916591_1@163.com]
 * @Description:
 * @Date 2019/8/10
 */

// TODO Stream源码解读
/* 流的操作：以其想要的任何方式来调度这些操作，只要结果正确即可（线程的顺序性么？）
* 1.流不存储元素==>底层集合
* 2.流的操作不会修改其数据源==>新流
* 3.流的操作尽可能惰性执行的==>无限流创建
* 流的典型流程：
* 1.创建流
* 2.中间操作 filter()
* 3.终止操作 count()
* */
public class CreatingStreams {

    // 读取流的前10项
    public static <T> void show(String title, Stream<T> stream){
        final int SIZE = 10;
        List<T>  firstElements = stream.limit(SIZE+1).collect(Collectors.toList());
        System.out.println(title+": ");

        for (int i = 0; i < firstElements.size() ; i++){
            if (i > 0) System.out.print(",");
            if (i < SIZE) System.out.print(firstElements.get(i));
            else System.out.print("...");
        }
        System.out.println();

    }

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("alice.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        // array
        Stream<String> words = Stream.of(contents.split("//P+"));
        show("words",words);

        // string
        Stream<String> song = Stream.of("gently","down","the","stream");
//        System.out.println(song.findFirst());
        show("song",song);


        // empty stream 产生一个不包含任何元素的流
        Stream<String> slience = Stream.empty();
        show("slienct",slience);

        // 创建无限流的静态方法  反复调用
        Stream<String> echos = Stream.generate(()->"Echo");
        show("echos",echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms",randoms);

        // 无限序列 种子 和 方法
        Stream<BigInteger> integerStream = Stream.iterate(BigInteger.ONE,n->n.add(BigInteger.ONE));
        show("integers",integerStream);

        // pattern创建
        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
        show("wordsAnotherWay",wordsAnotherWay);

        // Files.lines
        try(Stream<String> lines = Files.lines(path,StandardCharsets.UTF_8))
        {
            show("lines",lines);
        }
//        System.out.println(wordsAnotherWay.filter(s->s.contains("0")).findFirst());
        // flatMap
        // 单子论 某种类型T转换为G<U>的函数f和将类型U转换为G<V>的函数g，先应用f，后应用g


    }



}
