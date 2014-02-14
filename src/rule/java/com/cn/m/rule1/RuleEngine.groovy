package com.cn.m.rule1

import com.cn.m.rule1.rule.IRule

/**
 * Created by macx on 23/1/14.
 */
class RuleEngine implements  IRuleEngine
{
    // 规则列表
    def rules = new ArrayList<IRule>();

    /**
     * 用于初始化规则的数据
     * @param rules
     * @return
     */
    @Override
    def loadRules(def Object rules) {
        return null
    }

    /**
     * 用于匹配规则
     * @param param
     */
    @Override
    def matchRules(def Object param) {
        for (def rule  :rules ) {
            rule.execute(param)
        }
    }

    @Override
    def reloadRules(def Object rules) {
        return null
    }
}
