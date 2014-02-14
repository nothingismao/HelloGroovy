package com.cn.m.dsl2

/**
 * Created by macx on 21/1/14.
 */
class Rule {
    boolean singlehit = true
    def conditions = new ArrayList ( )
    def actions = new ArrayList ( )
    def parameters = new ArrayList ( )


    @Override
    public String toString() {
        return "Rule{" +
                "singlehit=" + singlehit +
                ", conditions=" + conditions +
                ", actions=" + actions +
                ", parameters=" + parameters +
                '}';
    }
}
