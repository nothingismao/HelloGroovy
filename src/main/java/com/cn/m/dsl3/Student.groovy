package com.cn.m.dsl3

/**
 * Created by macx on 21/1/14.
 */
class Student {
    @Override
    public String toString() {
        return "Student{" +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                ", gpa=" + gpa +
                ", dormHall=" + dormHall +
                '}';
    }

    enum DormHall {
        Honors, Preferred, Normal
    }
    def firstName
    def lastName
    def gpa

    // We only want dormHall to be assigned when it is deliberate, so throw an exception when
    // it is accidentally assigned outside of our rules engine
    private def dormHall
    public void setDormHall() {
        throw new RuntimeException("Student dorm hall is not directly assignable.")
    }

    static def list(){
        def students = [];
        for(def i in 0..5){
            def temp = new Student("firstName":"F","lastName":"L",gpa:i);
            students<<temp
        }
        return students

    }


}

/**
 *
 *   Structure:
 *    + module: services
 *      - class: com.danveloper.services.StudentService
 *
 **/
class StudentService {
    /**
     *   This may be a Gormish app, which means that we can garner
     *   some of the more flexible notation that GORM offers our model
     *   entities.
     *
     *   see: https://github.com/danveloper/gormish
     **/
    void save(Student student) {
       println student

    }
}
