package com.jndi.utils

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TemplateUtilsTest {

    @Autowired
    TemplateUtils templateUtils

    @Test
    void analysis() {
        def ftlName = 'com.jndi.entity.EvilObj.java.ftl'
        def map = [ip: '127.0.0.1', result: 'hello']
        templateUtils.analysis(ftlName, null, map)
    }

    @Test
    void getClassBytes() {
        templateUtils.getClassBytes('com.jndi.entity.EvilObj.class')
    }

}