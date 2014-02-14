package com.cn.m.sample

/**
 * Created by macx on 21/1/14.
 */
class MString_Regex {
    def noramlString() {
        def str1 = 'Groovy&Grails&MM'
        println '\t' + str1[4]
        println '\t' + str1[1..2]
        println '\t' + str1[4..2]
        println '\t' + str1[6, 2, 3]

        println 'x'<=>'x'
        println 'a'<=>'x'
        println 'a'.compareTo('x')

        println "'Groovy'*10\t" + 'Groovy' * 10
        println "'Groovy'*3-'Gro'\t" + 'Groovy' * 3 - 'Gro'
        println "'Groovy'.compareToIgnoreCase('groovy')\t" + 'Groovy'.compareToIgnoreCase('groovy')
        println "'Groovy'.equalsIgnoreCase('groovy')\t" + 'Groovy'.equalsIgnoreCase('groovy')
        println "'Groovy'.indexOf('0')\t" + 'Groovy'.indexOf('0')
        println "'Groovy'.substring(4)\t" + 'Groovy'.substring(4)
    }

    def normalSout() {
        def str2 = 'Groovy'
        println "[${str2.center(10)}]"
        println "[${str2.center(10, '=')}]"
        str2 << 'aa'
        def str4 = 'Groovy&Grails&lxt008'
        println str4.replaceAll('[a-z]', { ch -> ch.toUpperCase() })
        println str4.reverse();
        println str4.size();
        println str4.toCharacter();
        println '100'.toDouble();
        List<String> list = "aaabbbccc".toList();
        for (String s : list) {
            print s + '\t'
        }
    }

    def normalRegex() {
        def aRegex = ~'lxt';
        println aRegex.class
        def mat = 'lxt' =~ 'lxt'
        println mat.class
        assert 'lxt008' =~ aRegex
        assert 'lxt008' =~ 'lxt'
        assert !('lxt008' =~ 'txl')
        assert !('lxt008' ==~ 'lxt')

        // .为任意字符
        assert 'lxt' =~ 'l.t'
        assert !('lxxt' =~ 'l.t')

        // ^起始字符
        assert 'Groovy' =~ '^Gro'

        // $为结束字符
        assert 'Groovy' =~ 'vy$'

        // *为 0~n
        assert '' =~ 'a*'

        // +为 1~n
        assert !('' =~ 'a+')

        // ?为 0|1
        assert 'a' =~ 'a?'

        // []为范围 *8 为循环8次
        assert 'lxt008' ==~ 'l[a-z0-9]*8'

        // {3}为循环3次  {1,4} 循环1~4次
        assert !('lxt008' ==~ 'l[a-z0-9]{3}8')

        // 使用 \\来翻译特殊字符
        assert '$' ==~ '\\$'

        // (a|b|c) 或者
        assert 'ten' ==~ 't(a|e|i)n'

        // 组合
        assert 'ababc' ==~ '(ab)*c'

        // \d = [0-9]数字   \D=[^0-9]非数字  \w=[a-zA-Z0-9] \W=[^a-zA-Z0-9] \s=[\t\n\f\r\v]  \S=[^\t\n\f\r\v]

        assert '1.2'==~'\\d\\.\\d'
        assert '1 a'==~'\\d\\s\\w'
        def datePattern ='([A-Z]{3})\\s([0-9]{1,2}),\\s([0-9]{4})'
        def date = 'NOV 28,2008'
        def matcher = date=~datePattern
        matcher.matches()

        assert date=~datePattern
        println matcher[0]




    }
}
