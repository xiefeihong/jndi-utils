package com.jndi.server

import com.jndi.utils.ClassUtils
import com.jndi.utils.TemplateUtils
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.springframework.util.FileCopyUtils
import org.springframework.util.ResourceUtils

@Slf4j
@Component
class ClassServer {

    @Value('${spring.freemarker.prefix}')
    String ftlPath

    @Value('${jndi.ip}')
    String ip

    @Value('${source.java-path}')
    String javaPath

    @Value('${source.class-path}')
    String classPath

    @Autowired
    ClassUtils classUtils

    @Autowired
    TemplateUtils templateUtils

    byte[] getClassBytesByTemplate(String ftlName) {
        def map = [ip: ip, result: 'hello']
        log.info(map.values().join(': '))
        def javaName = ftlName - '.ftl'
        templateUtils.analysis(ftlName, javaPath + javaName, map)
        def classFilePath = classPath + ftlName.replaceFirst('.java.ftl', '.class')
        classUtils.compiler(javaPath + javaName, classPath)
        templateUtils.getClassBytes(classFilePath)
    }

    byte[] getClassBytes(String className) {
        classUtils.getClassBytes(className)
    }

}