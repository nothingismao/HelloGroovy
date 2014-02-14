package com.cn.m.dsl.delegate

/**
 * Created by macx on 21/1/14.
 */
abstract class Delegate {
    abstract methodMissing(String name, Object args)
    def propertyMissing(String name) {}
}
