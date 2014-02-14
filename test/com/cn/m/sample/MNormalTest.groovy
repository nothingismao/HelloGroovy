package com.cn.m.sample

/**
 * Created by macx on 20/1/14.
 */
class MNormalTest extends GroovyTestCase {
    def normal = new MNormal();
    void testNormal() {
        normal.normal();
        assert 4==4;

    }
    void testNormalString(){
        normal.normalString();
    }
    void testNormaCollection(){
        normal.normalCollection();
    }
    void testNormalMap(){
        normal.normalMap();
    }
    void testNormalPerson(){
        normal.normalPerson();
    }
    void testNormalEnum(){
        normal.normalEnum();
    }
    void testNormalDynmic(){
        normal.normalDynmic();
    }
    void testNormalRange(){
        normal.normalRange()
    }
    void testNormalRegular(){
        normal.normalRegular();
    }
}
