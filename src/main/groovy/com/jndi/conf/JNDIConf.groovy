package com.jndi.conf

import com.jndi.server.LDAPServer
import com.jndi.server.RMIServer
import com.jndi.server.impl.JNDIServer
import groovy.util.logging.Slf4j
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Slf4j
@Configuration
@ConfigurationProperties(prefix = 'jndi')
class JNDIConf {

    String type

    @Bean
    JNDIServer jndiServer(){
        def jndiServer
        switch(type){
            case 'ldap' :
                jndiServer = new LDAPServer()
                log.info('LDAP Service is created')
                break
            case 'rmi' :
                jndiServer = new RMIServer()
                log.info('RMI Service is created')
                break
        }
        jndiServer
    }

}
