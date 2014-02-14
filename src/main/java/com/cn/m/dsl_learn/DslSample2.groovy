package com.cn.m.dsl_learn

import groovy.xml.MarkupBuilder

/**
 * Created by macx on 8/2/14.
 */
def invokeMethod(String name,def args){
    print "<${name}"
    args.each{arg->
        if(arg instanceof Map){
            arg.each{
                print "${it.key}='${it.value}'"
            }
        }else if(arg instanceof Closure){
            print '>'
            arg.delegate = this
            def value = arg.call()
            if(value){
                print "${value}"
            }
        }
    }
    println "</${name}>"
}

def sw = new StringWriter()
def html = new groovy.xml.MarkupBuilder(sw)
html.html{
    head {
        meta {

        }
    }
    body {
        table (style:'margin:5px;') {
            tr ('class':'trClass', style:'padding:5px;') {
                td {'Cell1'}
                td {'Cell2'}
                td {'Cell3'}
            }
        }
    }
}

def f = new File("index2.html")
f.write(sw.toString())
print sw.toString()


