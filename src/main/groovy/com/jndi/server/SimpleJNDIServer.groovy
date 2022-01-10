package com.jndi.server

import com.jndi.controller.JndiController
import com.jndi.entity.Jndi
import com.jndi.utils.JndiMapping
import com.jndi.utils.TemplateUtils
import com.jndi.utils.compiler.DynamicCompiler
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import java.lang.reflect.Method

@Slf4j
@Component
class SimpleJNDIServer {

    @Value('${jndi.type}')
    String type

    @Autowired
    JndiController jndiController

    @Autowired
    TemplateUtils templateUtils

    @Autowired
    DynamicCompiler compiler

    Jndi generateClass(Method method) {
        def jndi
        if(method?.isAnnotationPresent(JndiMapping.class)) {
            jndi = method.invoke(jndiController) as Jndi
        } else if (type == 'ldap'){
            jndi = jndiController.hello()
        }
        if(jndi){
            if (type == 'ldap'){
                def className = 'C' + System.currentTimeMillis()
                jndi.factory = 'com.jndi.template.' + className
                jndi.template.className = className
            }
            def template = jndi.template
            def sourceCode = templateUtils.analysis(template.fileName, template)
            compiler.compiler(jndi.factory, sourceCode)
            log.info("class [Template: ${jndi.className}; Class: ${jndi.factory}] created")
        }
        jndi
    }

    byte[] getBytes(String className){
        def bytes = compiler.getBytes(className)
        if (type == 'ldap'){
            compiler.cleanClass(className)
            log.info("class [${className}] cleand")
        }
        bytes
    }

}