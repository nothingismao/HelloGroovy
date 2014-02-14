package com.cn.m.dsl_learn

/**
 *
 * 通过categories开放类
 * categories的概念是从Smalltalk和Objective-C语言中借的.一个categories可以使用use块指令,围绕代码调用创建一个包装器,含有一个或多个开放类.
 *
 *
 * Created by macx on 8/2/14.
 */
//class DslSample3 {
def expected = ["event_map": "eventMap", "name": "name", "test_date": "testDate"]

//    void test_Camelize(){
use(StringCategory) {
    expected.each { key, value ->

        /** 通过StringCategory 追加方法*/
        if (value == key.camelize()) {
            print "Y \t"
        } else {
            print "N \t"
        }
    }
}
//    }
//}

class StringCategory {
    static String camelize(String self) {
        def newName = self.split("_").collect() {
            it.substring(0, 1).toUpperCase() + it.substring(1, it.length())
        }.join()
        newName.substring(0, 1).toLowerCase() + newName.substring(1, newName.length())
    }
}

