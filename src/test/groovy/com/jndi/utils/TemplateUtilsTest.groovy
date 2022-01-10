package com.jndi.utils

import com.jndi.entity.Template
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TemplateUtilsTest {

    @Autowired
    TemplateUtils templateUtils

    @Test
    void analysis() {
        def tempName = 'EvilObj.java.ftl'
        def temp = new Template('EvilObj', 'hello', '127.0.0.1')
        println templateUtils.analysis(tempName, temp)
    }

}