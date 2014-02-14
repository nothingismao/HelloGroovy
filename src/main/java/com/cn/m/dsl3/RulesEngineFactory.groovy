package com.cn.m.dsl3

/**
 *
 *   Structure:
 *    + module: application
 *      - class: com.danveloper.application.rules.RulesEngineFactory
 *
 **/
class RulesEngineFactory<E extends RulesEngine> {
    private final E engine
    public RulesEngineFactory(final Class<E> c) {
        this.engine = (E)c.newInstance()
        this.engine.engine = this.engine
    }

    def getObject(final def obj) {
        this.engine.obj = obj
        this.engine
    }
    def apply(final def obj){
        getObject(obj).apply();
    }

}