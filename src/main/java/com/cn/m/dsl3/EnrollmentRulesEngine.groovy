package com.cn.m.dsl3

/**
 *
 *   Structure:
 *    + module: application
 *      - class: com.danveloper.application.rules.student.EnrollmentRulesEngine
 *
 **/
class EnrollmentRulesEngine extends RulesEngineSupport<Student> implements RulesEngine {
    def dormHallRule = { obj ->
        if (obj.gpa >= 3.5) {
            obj.@dormHall = Student.DormHall.Honors
        } else if (obj.gpa >= 3.0) {
            obj.@dormHall = Student.DormHall.Preferred
        } else {
            obj.@dormHall = Student.DormHall.Normal
        }
    }
}