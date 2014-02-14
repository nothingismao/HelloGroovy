package com.cn.m.sample.closepackage

/**
 * Created by macx on 22/1/14.
 */
class Mcp {
    def normalCP(){
        print {};

        // 闭包是一个可执行的代码块
        def aClosure={
            println 'Hello Closure!'
        }
        print '\t'+aClosure
        print '\n'

        // 主动调用闭包
        aClosure.call();
        aClosure();

        def bClosure={
            print "Hello${it}"
        }
        bClosure.call('lxt008')
        bClosure('lxt008')
        bClosure 'lxt008'


        def cClosure={name,address->
            print "${name}|${address}"
        }
        cClosure("a","b")

        def dClosure={name,address='xx'->
            print "${name}|${address}"
        }
        dClosure("a")

//        def aa = []
//        aa.any()
//        aa.collect()
//        aa.every()
//        aa.find()
//        aa.findAll()
//        aa.findIndexOf {}
//        aa.inject {}
//        aa.sort()


        println();
        def value =[1,2,5,7,9]
        print value.find {e->e>6}
        print '\t'+ value.every {e->e>6}
        print '\t'+ value.any {e->e>6}
        print '\t'+ value.collect {e->e>6}
        print '\t'+ value.find {e->e>6}
        print '\t'+ (value.reverse()).reverse()
        print '\t'+ value.findIndexOf {e->e>6}
        print '\t'+ value.collect {e->e*2}
        print '\t'+ value.inject(1) {previous,e->e*previous}
        println()



    }
}
