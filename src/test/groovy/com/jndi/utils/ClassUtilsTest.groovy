package com.jndi.utils


import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ClassUtilsTest {

    @Autowired
    ClassUtils classUtils

    @Test
    void getClassBytes() {
        classUtils.getClassBytes('com/jndi/entity/EvilObj.class')
    }

    @Test
    void compiler() {
        def javaFilePath = System.getProperty('user.dir') + '/src/main/resources/templates/EvilObj.java'
        classUtils.compiler(javaFilePath, null)
    }

    @Test
    void newInstance() {
        def obj = classUtils.newInstance('com.jndi.entity.EvilObj')
        println(obj.getClass().name)
    }

}