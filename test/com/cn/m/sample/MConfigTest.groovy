package com.cn.m.sample

/**
 * Created by macx on 20/1/14.
 */
class MConfigTest extends GroovyTestCase {
    void testReadConfig() {
        def config = new MConfig();
        config.readConfig();

    }
}
