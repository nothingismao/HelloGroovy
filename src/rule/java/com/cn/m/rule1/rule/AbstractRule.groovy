package com.cn.m.rule1.rule

/**
 * Created by macx on 23/1/14.
 */
abstract class AbstractRule implements IRule{

    IRule nextRule = null;

    final def matchRule(def parm){
        def result = this.execute(parm)
        if(result == Boolean.TRUE){
            nextRule.matchRule(parm)
        }
    }

}
