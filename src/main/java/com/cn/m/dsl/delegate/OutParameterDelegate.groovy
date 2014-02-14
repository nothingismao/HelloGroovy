package com.cn.m.dsl.delegate

/**
 * Created by macx on 21/1/14.
 */
import java.sql.*;
import groovy.sql.*;
//import oracle.jdbc.driver.OracleTypes;

class OutParameterDelegate extends Delegate {
    def binding
    OutParameterDelegate(binding) {
        this.binding = binding
    }
    def methodMissing(String name, Object args) {
        def outParameters = binding['outParameters']
        outParameters[name] = args
    }
}