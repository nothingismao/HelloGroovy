package com.cn.m.dsl_learn

/**
 * Created by macx on 8/2/14.
 */
class DslSample1Test extends GroovyTestCase {
    DslSample1 sample = new DslSample1()
    void testOutPut() {
       sample.outPut();
    }

    void testOutPut2(){
        sample.outPut2()
    }

    void testOutDslClosure(){
        sample.outDslColsure()
    }
}
