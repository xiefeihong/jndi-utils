package com.jndi.entity;

import org.junit.jupiter.api.Test

import javax.naming.ldap.LdapName

class BaseObjTest {

    @Test
    void getObjectInstance() {
        def evilObj = new BaseObj()
        def name = new LdapName('ssssss')
        evilObj.getObjectInstance(null, name, null, null)
    }

}