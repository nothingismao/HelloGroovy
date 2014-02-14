package com.cn.m.dsl2

//import org.codehaus.groovy.scriptom.ActiveXObject;
/**
 * Created by macx on 21/1/14.
 */
class RuleEngine {
    def run(RuleSet ruleset, Object bo) {
        // Iterate over the rules
        ruleset.rules.each { rule ->
            println "Executing rule in " +
                    (rule.singlehit ? "singlehit" : "multihit") + " mode."
            def exit = false // Exit flag for singlehit mode
            // Iterate over the parameter sets
            rule.parameters.each { ArrayList params ->
                def pcounter = 0 // Points to the current parameter
                def success = true
                if (!exit) {
                    // Check all conditions
                    rule.conditions.each {
                        success = success && it(bo, params[pcounter])
                        pcounter++
                    }
                    // If all conditions true, perform actions
                    if (success && !exit) {
                        rule.actions.each {
                            it(bo, params[pcounter])
                            pcounter++
                        }
                        // If single hit, exit after first condition match
                        if (rule.singlehit) {
                            exit = true
                        }
                    }
                }
            }
        }
    }

    def addRule(RuleSet ruleset) {
        // Rule definition
        def rule = new Rule()
        // ****************** CONFIGURATION ******************
        rule.singlehit = true
        // ***************************************************
        rule.conditions = [
        // ****************** CONDITIONS *********************
                { bo, p -> bo.experience > p }, { bo, p -> bo.experience <= p }
        // ***************************************************
        ]
        rule.actions = [
        // ******************* ACTIONS ***********************
                { bo, p -> bo.level = p }
        // ***************************************************
        ]
        rule.parameters = [
                // **************** PARAMETERSETS ********************
                // Min experience, Max experience, Level
                [1, 3, 'Beginner'],
                [1, 3, 'Starter'],
                [4, 6, 'Junior'],
                [7, 10, 'Average'],
                [11, 20, 'Senior']
                // ***************************************************
        ]
        ruleset.rules << rule
    }

    def addRuleFromExcel(ruleset,filename){
        def file = new File('src/main/resources/ruleFile.txt');
        if(file.exists()){
            println 'ruleFile.txt is exist'
        }else{
            println 'ruleFile.txt is not exist'+file.getCanonicalPath()
        }
        def rule = new Rule()
        def lines= file.readLines()
        for (def  line: lines ) {
            def details = line.tokenize(',')
//            def set = []
//            details.each {it->
//                set<<it
//            }
            rule.parameters<<details
        }

        rule.conditions=[
                {bo,p->bo.experience > p.toInteger()},
                {bo,p->bo.experience <= p.toInteger()}
        ]
        rule.actions=[
                {bo,p->bo.level=p}
        ]
        ruleset.rules<<rule
        println rule

    }

}
