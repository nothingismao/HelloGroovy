package com.cn.m.sample

/**
 * Created by macx on 20/1/14.
 */
class MFile {
    void fileExample(){
        println 'fielExample start';
        BufferedReader reader = new File("/Users/macx/IdeaProjects/HelloGroovy/resource/a.txt").newReader('GBK');
        BufferedWriter writer = new File("/Users/macx/IdeaProjects/HelloGroovy/resource/b.txt").newWriter('UTF-8');
        reader.eachLine {line->
                if(line && line[0]!='#'){
                    writer.writeLine(line);
                }
        }
        writer.close();
    }

    def createFile(path,createIfNotExeist){
        def file = new File(path);
        if(!file.exists()){
            if(createIfNotExeist){
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                    print 'parentFile().mkdirs created.\n';
                }
            }
            file.createNewFile();
            print 'file created \n';
        }
        return file;
    }

    def copyFile(String fromPath,String toPath,boolean createDestIfNotExist){
        def fromFile = new File(fromPath);
        if(!fromFile.exists()){
            println("############Missing file:"+formFile+"\n");
            return false;
        }else{
            println("Copying file:"+fromPath+"\n");
        }
        def toFile  = createFile(toPath,createDestIfNotExist);
        toFile.withWriter {file->
            fromFile.eachLine {line->
                file.writeLine(line);
        }}
        return true;
    }

    static void main(String[] args){
        MFile file = new MFile()
        file.fileExample();
        def path = '/Users/macx/IdeaProjects/HelloGroovy/resource/cc/c.txt';
        file.createFile(path,true);
    }
}
