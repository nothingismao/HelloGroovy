package com.cn.m.sample

import groovy.xml.MarkupBuilder;

/**
 * Created by macx on 20/1/14.
 */
class Mxml {
    def xmlMethod() {
        def sample = """
            We'll create this xml:
        """;
        println(sample);

        //写XML
        def testxml = "/Users/macx/IdeaProjects/HelloGroovy/resource/test.xml";
        def xmlFile = new File(testxml)
        if (xmlFile.exists() == false) {
            if (xmlFile.getParentFile().exists() == false) {
                xmlFile.mkdirs();
                println "create folder"
            }
            xmlFile.createNewFile();
            println "create file"
        }
        def xml = new MarkupBuilder(new FileWriter(testxml));
        xml.beans {
            bean(id: "myBean1", class: "com.diegochen.Bean1") {
                property(name: "dao", ref: "dao2")
            }
            bean(id: "myBean2", class: "com.diegochen.Bean2") {
                property(name: "dao", ref: "dao2")
            }
        }
        println "Done creation.Now reading xml....\n"
        def start = System.currentTimeMillis();
        def node = new XmlParser().parse(new File(testxml));
        println "node name:" + node.name();
        def end = System.currentTimeMillis();
        println "elapsed time:" + (end - start) + " ms"

        println "How many beans?" + node.children().size()
        for (def bean : node.children()) {
            //   def bean2 = node.children()[1];

            println "2nd bean's id:" + bean."@id"
            println "2nd bean's class:" + bean."@class"
            println("2nd bean's dao property:" + bean.children()[0]."@ref")
        }
    }

    def xmlMethod2(){
//        Mxml.class.get
        System.out.println(System.getProperty("user.dir"));
        def testFile = new File(System.getProperty("user.dir")+"src/main/java/com/cn/m/sample/srcexample.groovy");
        print testFile.getCanonicalPath();
        if(!testFile.exists()){
            testFile.createNewFile();
        }

        def xml = new MarkupBuilder(new FileWriter(testFile))
        xml.root{
            person{
                name("bshs")
                site("baishanheishui.iteye.com")
            }
        }
        def xml2 = new File('example.groovy').text
        def builder = new MarkupBuilder()
        def shell = new GroovyShell(binding)
        def xmlContext=""
        shell.evaluate("xmlContext=builder.$xml2")
        println xmlContext.toString()



    }



}
