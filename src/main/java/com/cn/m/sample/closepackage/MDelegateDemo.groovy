package com.cn.m.sample.closepackage

/**
 * Created by macx on 22/1/14.
 */
class MDelegateDemo {
    def someMethod(Closure closure){
        println "The original delegate of closure is:${closure.delegate}"
        closure.delegate = this
        println "The original delegate of closure is:${closure.delegate}"
        closure()
    }

    def greet(def words){
        println words
    }
}


