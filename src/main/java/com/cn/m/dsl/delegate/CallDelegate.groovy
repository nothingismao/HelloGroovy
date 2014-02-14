package com.cn.m.dsl.delegate

/**
 * Created by macx on 21/1/14.
 */
class CallDelegate extends Delegate {
    def binding
    CallDelegate(binding) {
        this.binding = binding
    }
    def methodMissing(String name, Object args) {
        if ('inParameter' == name && args[0] instanceof Closure) {
            def inParameterClosure = args[0]
            inParameterClosure.delegate = new InParameterDelegate(binding)
            inParameterClosure.resolveStrategy = Closure.DELEGATE_FIRST
            inParameterClosure()
        } else if ('outParameter' == name && args[0] instanceof Closure) {
            def outParameterClosure = args[0]
            outParameterClosure.delegate = new OutParameterDelegate(binding)
            outParameterClosure.resolveStrategy = Closure.DELEGATE_FIRST
            outParameterClosure()
        }
    }
}