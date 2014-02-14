package com.cn.m.dsl2

/**
 * Created by macx on 21/1/14.
 */
class Developer {
    private String name
    private int experience
    private String level="unknown"


    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                ", experience=" + experience +
                ", level='" + level + '\'' +
                '}';
    }
}
