package com.cn.m.sample

/**
 * Created by macx on 20/1/14.
 */
class MConfig {
    def readConfig() {
        def propfile = "/Users/macx/IdeaProjects/HelloGroovy/resource/config.groovy"

        def config = new ConfigSlurper().parse(new File(propfile).toURL());
        def fileUpdateList = config.path.file_update_list;
        def debugFlag = config.constants.debug;

        println(fileUpdateList)
        println(debugFlag)

        def file = new File("/Users/macx/IdeaProjects/HelloGroovy/resource/config.groovy")
        file.eachLine {line->println line}

    }
    public static void main(String[]args){
        def file = new File("../src/a.txt");
        println file.getAbsolutePath();
        println file.getCanonicalPath();
    }
}
