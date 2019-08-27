package com.yzh.learn;

import com.yzh.learn.huawei.SixPage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author youzhanghao [m13732916591_1@163.com]
 * @Description:
 * @Date 2019/8/20
 */
public class HuaWeiTest {

    @Autowired
    private SixPage sixPage;

    @Test
    public void testMinimunCommonMultipl(){
        sixPage = new SixPage();
        System.out.println(sixPage.minimumCommonMultiple(3,0));
        System.out.println(sixPage.minimumCommonMultiple(6,4));
    }

    @Test
    public void testReverseString () {
        // 不是 :: 调用
        SixPage.reverseString("I am a student");
    }

    @Test
    public void testFunc(){
        String test_str = "hello";
        System.out.println(test_str.charAt(2));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(test_str);
        System.out.println(stringBuilder.reverse());


        // TODO 字符串转数组  如何访问末位字符串 可否使用-1

        System.out.println(test_str.charAt(0));
//        System.out.println(test_str.substring(2,1));
    }
}
