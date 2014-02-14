package com.cn.m.builder

import groovy.xml.MarkupBuilder

/**
 * Created by macx on 8/2/14.
 */

def xml = new MarkupBuilder()
xml.books(amount:2){
    description 'books to lean groovy'
    book1 (name:"groovy in action",ISBN:'1-1111-11')
    book2 (name:"Getting Started with Grails",ISBN:'987-121-1213-11')
}
xml.println()

def xml2 = new MarkupBuilder()
def names = ['Groovy','Getting']
def isbns = ['1-22-2','2-2-2-2']

xml2.books(amount:names.size()){
    description 'book'
    for (int  i in 0..names.size()-1){
        def s = "book"+(i+1)
        "$s" (name: names[i],isbn:isbns[i])
    }
}
xml2.println()