package com.cn.m.dsl_learn


import static  System.currentTimeMillis as now

/**
 *
 * 此类展示了Groovy语言中,有三种方法来实现有关方法名的DSL,分别是:静态引入\使用'as'关键字和定义可读性的闭包.
 * Created by macx on 8/2/14.
 */
class DslSample1 {
    /**
     * 这里的"println" 就是别名DSL . 将System.out.println as println
     */
    def outPut(){
        println "ABC"
    }

    /**
     * DSL别名,开头 import 将 System.currentTimeMillis as now
     */
    def outPut2(){
        println now();
    }

    /**
     * 通过闭包来实现DSL,提高代码的可读性.
     */
    def outDslColsure(){
        def account = new Account("1","A")
        def good1 = new Goods(no: "a",name: "b",price: 10,count: 1)
        def good2 = new Goods(no: "b",name: "c",price: 11,count: 2)
        def buy = account.&'bug'
        buy good1
        buy good2

        println "dear ${account.code} ,you have bougut:"
        def view = account.&'view'
        view()
    }
}

class Goods{
    String no
    String name
    float price
    int count
}

class Account{
    String code
    String name
    def goodses

    def Account(code,name){
        this.code = code;
        this.name = name;
        this.goodses  =[];
    }

    def bug(Goods goods){
        this.goodses << goods
    }

    def view(){
        this.goodses.each{
            println "${it.name}"
        }
    }
}


