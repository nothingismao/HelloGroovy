package com.cn.m.dsl.delegate

/**
 * Created by macx on 21/1/14.
 */

class InParameterDelegate extends Delegate {
    def binding

    InParameterDelegate(binding) {
        this.binding = binding
    }

    def methodMissing(String name, Object args) {
        def inParameters = binding['inParameters']
        inParameters[name] = args
        binding[name] = args[1]
    }
}