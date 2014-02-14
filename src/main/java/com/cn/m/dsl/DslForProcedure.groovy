package com.cn.m.dsl

import com.cn.m.dsl.delegate.CallDelegate

/**
 * Created by macx on 21/1/14.
 */
class DslForProcedure {
    def templateFile = new File("com/cn/m/dsl/Template.groovy")
    def templateContent = templateFile.text

    DslForProcedure(){
        this.metaClass = createMetaClass(this.class){emc->
            emc.'call'=scriptClosure
        }
    }

    def scriptClosure = {args,callClosure->
        def binding = new Binding()
        binding['results'] = [:]
        binding['callType'] = args['type']
        binding['inParameters'] = [:]
        binding['outParameters'] = [:]

        callClosure.delegate = new CallDelegate(binding)
        callClosure.resolveStrategy = Closure.DELEGATE_FIRST
        callClosure()

        def simpleTemplateEngin = new groovy.text.SimpleTemplateEngine()
        def template = simpleTemplateEngin.createTemplate(templateContent)
        binding['url'] = args['url']
        def resultCode = template.make(binding.variables).toString()
        Script script = new GroovyShell(binding).parse(resultCode)
        def results = script.run()
        binding['result'] = results
        return binding['result']
    }

    def createMetaClass(Class clazz, Closure closure) {
        def emc = new ExpandoMetaClass(clazz,false)
        closure(emc)
        emc.initialize()
        return emc
    }

    def executeScript(dslScriptCode,rootName,closure){
        Script dslScript = new GroovyShell().parse(dslScriptCode)
        dslScript.metaClass = createMetaClass(dslScript.class){emc->
            emc."$rootName" = closure
        }
        return dslScript.run()
    }

    def executeScript(dslScriptCode){
        executeScript(dslScriptCode,'call',scriptClosure)
    }
}
