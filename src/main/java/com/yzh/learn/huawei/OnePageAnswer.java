package com.yzh.learn.huawei;
import java.util.*;

/**
 * @Author youzhanghao [m13732916591_1@163.com]
 * @Description:
 * @Date 2019/8/25
 */
public class OnePageAnswer {

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()){
//            String s = in.nextLine();
//            System.out.println(lastWordLen(s));
//        }


//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()){
//            StringBuilder stringBuilder = new StringBuilder();
//            String str = scanner.nextLine();
//            stringBuilder.append(str);
//            int size = stringBuilder.length();
//            int addZero = 8 - size%8;
//            if(addZero > 0 && addZero < 8){
//                while(addZero > 0){
//                    stringBuilder.append('0');
//                    addZero--;
//                }
//            }
//            String  str1 = stringBuilder.toString();
//            while(str1.length()>0){
//                System.out.println(str1.substring(0, 8));
//                str1 = str1.substring(8);
//            }
//
//        }
//        Scanner in = new Scanner(System.in);
//        while(in.hasNext()){
//            StringBuilder strBuild = new StringBuilder();
//            String s = in.nextLine();
//            strBuild.append(s);
//            int len = strBuild.length();
//            int addZero = 8 - len%8;
//            if ( addZero > 0 && addZero < 8 ){
//                while ( addZero > 0 ){
//                    strBuild.append('0');
//                    addZero--;
//                }
//            }
//            String str1 = strBuild.toString();
//            while ( str1.length() > 0 ){
//                System.out.println(str1.substring(0,8));
//                str1 = str1.substring(8);
//            }
//
//        }
        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
        int n = Integer.parseInt(in.nextLine());
        SortedMap<Integer,Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i ++ ){
            String[] mid = in.nextLine().split("\\s");
            addPare(map,mid);
        }
        System.out.println(mapToString(map));

    }

    public static int lastWordLen(String s){
        String[] sArr = s.split("\\s");
        int last = sArr.length - 1;
        return  sArr[last].length();
    }

    public static void addPare(SortedMap<Integer,Integer> map, String[] mid){
        int key = Integer.parseInt(mid[0]);
        int value = Integer.parseInt(mid[1]);
        if ( map.containsKey(key)){
            map.put(key,map.get(key) + value);
        }else {
            map.put(key,value);
        }
    }

    public static String mapToString(SortedMap<Integer,Integer> map){
        StringBuilder stringBuilder = new StringBuilder();
        for (SortedMap.Entry<Integer,Integer> e: map.entrySet()){
            stringBuilder.append(e.getKey()).append(" ").append(e.getValue()).append("\r");
        }
        return stringBuilder.toString();
    }

}
