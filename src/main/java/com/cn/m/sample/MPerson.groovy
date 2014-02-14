package com.cn.m.sample

/**
 * Created by macx on 20/1/14.
 */
class MPerson {
    def name;
    def age

    def getName() {
        return name
    }

    def getAge() {
        return age
    }


    @Override
    public String toString() {
        return "MPerson{" +
                "name=" + name +
                ", age=" + age +
                '}';
    }
}
