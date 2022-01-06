package com.jndi.conf

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = 'source')
class SourceConf {

    String javaPath
    String classPath

    void setJavaPath(String javaPath){
        def javaDir = new File(javaPath)
        if (!javaDir.exists()){
            javaDir.mkdirs()
        }
        this.javaPath = javaPath
    }

    void setClassPath(String classPath){
        def classDir = new File(classPath)
        if (!classDir.exists()){
            classDir.mkdirs()
        }
        this.classPath = classPath
    }

}
