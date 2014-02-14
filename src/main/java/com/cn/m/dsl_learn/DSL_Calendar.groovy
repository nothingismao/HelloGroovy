package com.cn.m.dsl_learn

/**
 * Created by macx on 8/2/14.
 */
def calendar = new AppointmentCalendar()

use (IntegerWithTimeSupport){
    calendar.add new Appointment("Dentist").from(4.am)
    calendar.add new Appointment("Conference call").from(5.pm).to(6.pm).at("555-123-4321")
}
calendar.print()

class AppointmentCalendar{
    def calendars = []
    def add(def appointment){
        calendars<<appointment
    }
    def print(){
        for (Object  cal:calendars ) {
            println(cal)
        }
    }
}

class IntegerWithTimeSupport{
    static Calendar getFromToday(Integer self){
        def target = Calendar.instance
        target.roll(Calendar.DAY_OF_MONTH,self)
        return target
    }
    static Integer getAm(Integer self){
        self ==12?0:self
    }
    static Integer getPm(Integer self){
        self == 12?12: self+12
    }
}

class Appointment{
    def name;
    def location;
    def date;
    def startTime;
    def endTime;

    Appointment(def apptName){
        name = apptName;
        date = Calendar.instance
    }

    def at(def loc){
        location = loc
        this
    }
    def formatTime(time){
        time>12 ?"${time-12}PM":"${time}AM"
    }
    def getStartTime(){
        formatTime(startTime)
    }
    def getEndTime(){
        formatTime(endTime)
    }

    def from(def start_time){
        startTime = start_time
        date.set(Calendar.HOUR_OF_DAY,start_time)
        this
    }
    def to(def endTime){
        this.endTime = endTime
        date.set(Calendar.HOUR_OF_DAY,endTime)
        this
    }
    def display(){
        print "Appointment:${name},Start:${formatTime(startTime)}"
        if(endTime)print ",Ends:${formatTime(endTime)}"
        if(location)print ",Location:${location}"
        println()
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "name=" + name +
                ", location=" + location +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}


