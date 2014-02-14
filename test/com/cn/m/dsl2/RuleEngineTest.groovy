package com.cn.m.dsl2

/**
 * Created by macx on 21/1/14.
 */
class RuleEngineTest extends GroovyTestCase {
    def engine = new RuleEngine()
    void testRun() {

    }

    void testAddRule() {
        // Business object creation
        def dev = new Developer(name:"Peter",experience:2)
        println "Before:" + dev
        // Run the rules
        def ruleset = new RuleSet()
        engine.addRule(ruleset)

        engine.run(ruleset,dev)
        // Show result
        println "After:" + dev

    }
    void testAddRuleExcel(){
        // Business object creation
        def dev = new Developer(name:"Peter",experience:10)
        println "Before:" + dev
        // Run the rules
        def ruleset = new RuleSet()
        engine.addRuleFromExcel(ruleset,'b')

        engine.run(ruleset,dev)
        // Show result
        println "After:" + dev
    }
}
