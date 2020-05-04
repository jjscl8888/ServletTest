package com.jjs.collection;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author jjs
 * @Version 1.0 2020/5/2
 */
public class EnumerationTest {

    public static void main(String[] args) {
        Enumeration<Person> allPerson = getAllPerson();
        while (allPerson.hasMoreElements()) {
            Person person = allPerson.nextElement();
            System.out.println(person.getName());
        }
    }

    public static Enumeration<Person> getAllPerson() {

        List<Person> lists = new ArrayList<>();
        Person pes = new Person();
        pes.setName("jjs");
        lists.add(pes);

        return new Enumeration<Person>(){

            private Integer i = 0;
            @Override
            public boolean hasMoreElements() {
                return lists != null && i >= 0 && i < lists.size();
            }

            @Override
            public Person nextElement() {
                return lists.get(i++);
            }
        };
    }
}

class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
