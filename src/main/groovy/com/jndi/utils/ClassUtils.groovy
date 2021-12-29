package com.jndi.utils

import groovy.util.logging.Slf4j
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

import javax.tools.JavaCompiler
import javax.tools.ToolProvider

@Slf4j
@Component
class ClassUtils {

    byte[] getClassBytes(String classPath) {
        def ins = ClassUtils.class.getClassLoader().getResourceAsStream(classPath)
        try {
            return ins.bytes
        } catch (e){
            log.error(e.message)
        }
    }

    void compiler(String javaFilePath, String classFilePath){
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        def fileMgr = compiler.getStandardFileManager(null, null, null)
        def fileObj = fileMgr.getJavaFileObjects(javaFilePath)
//        def options = ['-d', classFilePath]
        def options = null
        def task = compiler.getTask(null, fileMgr, null, options, null, fileObj)
        def success = task.call()
        if(!success){
            log.error("Java File: ${javaFilePath} Compiler Error")
        }
        fileMgr.close()
    }

    def newInstance(String className){
        def cl = ClassLoader.getSystemClassLoader()
        def clazz = cl.loadClass(className)
        clazz.newInstance()
    }

}