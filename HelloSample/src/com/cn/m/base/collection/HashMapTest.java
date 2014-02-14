package com.cn.m.base.collection;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by macx on 13/2/14.
 */
public class HashMapTest {

    void putInfo(){
        HashMap<Person,Integer> persons = new HashMap<Person,Integer>();
        ConcurrentHashMap<Person,Integer> concurrentHashMap = new ConcurrentHashMap<Person, Integer>();


        /** 这两个实例含有同样的hashcode,但是实际上equals不同*/
        Person person1 = new Person(10,"aaa");
        Person person2 = new Person(10,"aaa");

        persons.put(person1,1);
        persons.put(person2,2);
        concurrentHashMap.put(person1,1);
        concurrentHashMap.put(person2,2);

        System.out.printf("Persons:"+persons);
    }



}

class Person{
    int age ;
    String name;

    Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (!name.equals(person.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result;
        return result;
    }
}
