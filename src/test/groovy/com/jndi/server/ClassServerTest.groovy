package com.jndi.server;

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ClassServerTest {

    @Autowired
    ClassServer classServer

    @Test
    void getClassBytesByTemplate() {
        classServer.getClassBytesByTemplate('', null)
    }

    @Test
    void getClassBytes() {
        classServer.getClassBytes('')
    }
}