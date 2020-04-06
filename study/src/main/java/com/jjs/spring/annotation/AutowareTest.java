package com.jjs.spring.annotation;

import com.jjs.spring.annotation.service.IService;
import com.jjs.spring.base.BaseTest;
import com.jjs.spring.config.SpringConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * @author jjs
 * @Version 1.0 2020/3/28
 */
public class AutowareTest extends BaseTest {

    @Autowired
    List<IService> lists;

    @Autowired
    Map<String, IService> maps;

    @Test
    public void test1() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(String.format("%s_%s", name, applicationContext.getBean(name)));
        }
    }

    @Test
    public void test2() {
        for (IService list : lists) {
            list.print();
        }
    }

    @Test
    public void test3() {
        for (Map.Entry<String, IService> entry : maps.entrySet()) {
            String key = entry.getKey();
            IService value = entry.getValue();
            System.out.println(key + value);
        }
    }

}
