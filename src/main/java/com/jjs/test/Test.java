package com.jjs.test;

import java.util.Properties;

/**
 * @author jjs
 * @Version 1.0 2020/3/22
 */
public class Test {

    public static void main(String[] args) {
        //Map<String, String> map = System.getenv();
        Properties map = System.getProperties();
        map.forEach((key, value) -> {
            System.out.println(key + " == " + value);
        });
    }

}
