package com.cn.m.rule1

/**
 * Created by macx on 23/1/14.
 */
interface IRuleEngine {

    /**
     * 用于初始化规则的数据
     * @param rules
     * @return
     */
    def loadRules(def rules)

    /**
     * 用于匹配规则
     * @param param
     */
    def matchRules(def param)

    /**
     * 用于重新加载规则数据
     * @param rules
     * @return
     */
    def reloadRules(def rules)

}
