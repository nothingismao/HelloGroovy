package com.cn.m.dsl2.sample

/**
 * Created by macx on 21/1/14.
 */
class MRuleEngine {

    def normalRule(){
        MRule rule = new MRule()
        rule.conditions=[
                {bo,p->
                    bo.experience>p
                }
        ]
        MDeveloper dev = new MDeveloper()
        rule.conditions.each {
            if(it(dev,9)){
                print 'success'
            }else{
                print 'fail'
            }
        }


    }
}
