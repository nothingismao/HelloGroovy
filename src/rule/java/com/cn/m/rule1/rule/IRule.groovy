package com.cn.m.rule1.rule

/**
 * Created by macx on 23/1/14.
 */
interface IRule {

    /**
     * 规则的具体匹配
     * @param param
     * @return
     */
    def execute(def param)

    /**
     * 用于规则的递归
     * @param param
     * @return
     */
    def matchRule(def param)
}
