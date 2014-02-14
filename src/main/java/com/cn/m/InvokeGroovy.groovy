package com.cn.m

import org.codehaus.groovy.runtime.InvokerHelper

import javax.xml.transform.Source

/**
 * Created by macx on 21/1/14.
 */
class InvokeGroovy {
    static void main(String[] args) {
//        ClassLoader cl = new InvokeGroovy().getClass().getClassLoader();
        def invoke = new InvokeGroovy()
        def cla = invoke.getClass()
        def cl = cla.getClassLoader()
        GroovyClassLoader groovyCl = new GroovyClassLoader(cl);
        try {
            //从文件中读取
            Class groovyClass = groovyCl.parseClass(new File("/Users/macx/IdeaProjects/HelloGroovy/src/com/cn/m/Foo.groovy"));
            //直接运用Groovy字符串,也能够获得正确成果
            //Class groovyClass = groovyCl.parseClass("package org.openjweb.groovy")
            //import org.openjweb.core.groovy.test.IFoo;\r\n

            // Class groovyClass = groovyCl.parseClass("package org.openjweb.groovy");
            //import org.openjweb.core.groovy.test.IFoo;
//            class Foo implements IFoo {
//                public Object run(Object foo) { return 2 + 2 > 1 }
//            } ;

            //这个回来true
            IFoo foo = (IFoo) groovyClass.newInstance();
            System.out.println(foo.run(new Integer(2)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Foo2 implements IFoo {
        public Object run(Object foo) { return 23 }
    }

    public Map<String,Object> execute(def methodName,def args ,Map<String,Object>params,Source source){
        GroovyCodeSource groovyCodeSource = new GroovyCodeSource();
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        Class scriptClass = groovyClassLoader.parseClass(source.getkey());
        Script script = InvokerHelper.createScript(scriptClass,XX);
        return script.invokeMethod(methodName,args);
    }
}
