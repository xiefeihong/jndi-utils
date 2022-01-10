package com.jndi.utils.compiler

import org.springframework.stereotype.Component
import javax.tools.JavaCompiler
import javax.tools.ToolProvider

/**
 * 运行时编译
 */
@Component
class DynamicCompiler {

    MemFileManager fileManager

    {
        JavaCompiler javaCompiler = ToolProvider.systemJavaCompiler
        fileManager = new MemFileManager(javaCompiler.getStandardFileManager(null, null, null))
    }

    /**
     * 编译源码并加载，获取Class对象
     * @param className
     * @param sourceCode
     * @return
     * @throws ClassNotFoundException
     */
    boolean compiler(String className, String sourceCode) {
        def javaCompiler = ToolProvider.systemJavaCompiler
        def javaFileObjects = [new MemJavaFileObject(className, sourceCode)]
        javaCompiler.getTask(null, fileManager, null, null, null, javaFileObjects).call()
    }

    Class<?> getClass(String className){
        this.fileManager.getClassLoader(null).loadClass(className)
    }

    byte[] getBytes(String className){
        this.fileManager.classFileObjects[className]?.bytes
    }

    void cleanClass(String className){
        this.fileManager.classFileObjects.remove(className)
    }
    
    /**
     * 关闭fileManager
     * @throws IOException
     */
    void closeFileManager() {
        this.fileManager.close()
    }

}
