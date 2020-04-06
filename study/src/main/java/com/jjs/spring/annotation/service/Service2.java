package com.jjs.spring.annotation.service;

import org.springframework.stereotype.Component;

/**
 * @author jjs
 * @Version 1.0 2020/3/28
 */
@Component
public class Service2 implements IService {

    @Override
    public void print() {
        System.out.println("++++++++++");
    }
}
