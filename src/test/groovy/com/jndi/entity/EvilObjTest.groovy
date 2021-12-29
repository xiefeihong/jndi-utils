package com.jndi.entity;

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

import javax.naming.Name
import javax.naming.ldap.LdapName;

import static org.junit.jupiter.api.Assertions.*;

class EvilObjTest {

    @Test
    void getObjectInstance() {
        def evilObj = new EvilObj()
        def name = new LdapName('ssssss')
        evilObj.getObjectInstance(null, name, null, null)
    }

}