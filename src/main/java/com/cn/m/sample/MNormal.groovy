package com.cn.m.sample

/**
 * Created by macx on 20/1/14.
 */
class MNormal {

    def normal() {
        def var = "hello" + "world" + "groovy";


        for (def i = 0; i < 5; i++) {
            println var;
        }

        for(def i in 0..5){
            println i;
        }

        3.times {println 'hello'}
        def store="";
        // 数字从多少到多少
        1.upto(5){number->store+=number+' '};
        println store;
        10.downto(1){number->store+=number+' '};
        println store;
        store = '';
        0.step(0.5,0.1){number->store+=number+' '}
        println store;
    }

    def normalString(){
        def str = 'hello\n lxt008';
        print str+"\t";
        def name ='lxt008';
        print "\t hello ${name} \n"

        for(i in 0..<5){
            print "this is ${i} \t";
        }
        def comp='aaa'
        def res = comp?comp:"Unknow"
        def res2 = comp?:"Unknow"

        def s1 = "S1 $comp"
        print s1
        def self = "xxx-aaaa-bbb-cdef-EDF"
        def newName = self.split("-").collect {
            it.substring(0,1).toUpperCase()+it.substring(1,it.length())
        }.join("+")
        println newName
    }

    def normalCollection(){
        def collection=["a","b","c"];
        collection.add(1);
        collection<<"come on";
        collection[collection.size()]=100.0;
        print collection
        print "\t"+collection[collection.size()-1];
        print "\t"+collection[5];
        print "\t"+collection[-1];
        println();
        collection  = collection +5;
        collection  = collection -'a';
        print '\t'+collection;
        println();
        collection = collection-collection[0..2];
        print '\t'+collection

        collection.flatten();
        collection.sort();
        print collection
        def count = collection.count(5);
        print '\t'+count
        print collection.getClass();

    }

    def normalMap(){
        def map=['name':'john','age':14,'sex':'boy']
        map=map+['weight':111]
        map.put('height',222)
        map.father = 'Keller'
        print map
        println()
        map.each({
            key,value->
                print "key:${key},value:${value}"
        })
        println();
        map.each {print '\t'+it}
        println();
        def say = {word->
            print "\t ${word}"
        }
        say('groovy')
        map.each {it2->
            say(it2)
        }
    }

    def normalDynmic(){
        def msg='Hello!'
        println msg.metaClass
        //msg.metaClass.up={delegate.toUpperCase()}
        String.metaClass.up={delegate.toUpperCase()}
        println msg.up();

        msg.metaClass.methods.each{print it.name+'\t'}
        println();
        msg.metaClass.properties.each {print it.name+'\t'}

        if(msg.metaClass.hasProperty(msg,'bytes')){
            println msg.bytes.encodeBase64();
        }
    }

    def normalPerson(){
        def person = new MPerson();
        def person2 = new MPerson("name":'aa',age:19)
        person.age = '100'
        print person
    }

    def normalSystemIn(){
        def name = new BufferedReader(new InputStreamReader(System.in)).readLine();

    }

    def normalEnum(){
        def today = Day.MON;
        switch(today){
            case [Day.SUN]:print 'SUN';break
            case [Day.MON]:print 'MON'+'\t';
            case [Day.THU]:print 'THU'+'\t';
            default:print 'default'+'\t';

        }
        def p = Planet.M;
        print p;
    }

    enum Day{
        SUN,MON,TUE,WED,THU,FRI,SAT
    }

    enum Planet{
        M(3,4),
        V(4,5),
        def m;
        def v
        Planet(def m ,def v){
            this.m = m;
            this.v = v;
        }

        @Override
        public String toString() {
            return "Planet{" +
                    "m=" + m +
                    ", v=" + v +
                    '}';
        }
    }


    def normalRange(){
        def eRange = 1..10;
        print '\t'+eRange.size()
        print '\t'+eRange.contains(5)
        print '\t'+eRange.get(8)
        print '\t'+eRange.getFrom()
        println(eRange.getTo())
        print '\t'+eRange.isReverse()
        print '\t'+eRange.subList(3,6)
        print '\t'+eRange.getToInt();
    }

    def normalRegular(){
        def text = "John Jimbo jingeled happily ever after"

        if(text ==~/(\W*\w)*/){
            println "Match was successful"
        }else{
            println "Match was not successful"
        }
    }
}
