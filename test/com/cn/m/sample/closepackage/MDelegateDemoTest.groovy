package com.cn.m.sample.closepackage

/**
 * Created by macx on 22/1/14.
 */
class MDelegateDemoTest extends GroovyTestCase {
    MDelegateDemo delegateDemo = new MDelegateDemo()
    void testSomeMethod() {
        delegateDemo.someMethod {
            delegate.greet("Hello")
        }

        def mydef={
            println "mydef"
        }
        delegateDemo.someMethod {
            println "so"
        }
        delegateDemo.someMethod(mydef)
    }
}
