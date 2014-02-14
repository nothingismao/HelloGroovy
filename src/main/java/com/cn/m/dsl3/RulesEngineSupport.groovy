package com.cn.m.dsl3

/**
 *
 *   Structure:
 *    + module: application
 *      - class: com.danveloper.application.rules.RulesEngineSupport
 *
 **/
class RulesEngineSupport<E> {
    RulesEngine engine
    E obj
    // Deduce the rule list for a given RulesEngine instance
    def getRules() {
        def rules = []
        engine.class.declaredFields.each {
            def field = engine."${it.name}"
            if (!it.isSynthetic() && field instanceof Closure && it.name.endsWith("Rule")) {
                rules << it.name
            }
        }
        rules
    }

    def apply() {
        rules.each { rule ->
            engine."$rule"(obj)
        }
    }
}