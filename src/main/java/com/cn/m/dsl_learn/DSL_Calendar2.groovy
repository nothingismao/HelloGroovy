package com.cn.m.dsl_learn

/**
 *
 * 给类 动态添加方法
 *
 *
 * Created by macx on 8/2/14.
 */
def calendar = new AppointmentCalendar()


Integer.metaClass.getAm = { ->
    delegate == 12 ? 0 : delegate
}
Integer.metaClass.getPm = { ->
    delegate == 12 ? 12 : delegate + 12
}

calendar.add new Appointment("Dentist").from(4.am)
calendar.add new Appointment("Conference call").from(5.pm).to(6.pm).at("555-123-4321")

calendar.print()
