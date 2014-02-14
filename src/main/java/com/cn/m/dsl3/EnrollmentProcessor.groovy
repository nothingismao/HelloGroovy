package com.cn.m.dsl3

/**
 *
 *   Structure:
 *    + module: application
 *      - class: com.danveloper.application.processors.EnrollmentProcessor
 *
 **/
class EnrollmentProcessor {
    static def enrollmentRulesFactory = new RulesEngineFactory<EnrollmentRulesEngine>(EnrollmentRulesEngine)
    def studentService  = new StudentService()

    def annualDormHallAssignment() {
        def students = Student.list(/* do some filtering to make sure they are enrolling for this year */)
        def errors = []
        students.each { student ->
            try {
                enrollmentRulesFactory.apply(student)
                studentService.save(student)
            } catch (Exception e) {
                errors << student
            }
        }
    }

    public  static void main(String[]args){
        def processor = new EnrollmentProcessor()
        processor.annualDormHallAssignment();
    }
}