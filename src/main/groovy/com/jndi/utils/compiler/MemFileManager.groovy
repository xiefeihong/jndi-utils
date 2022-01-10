package com.jndi.utils.compiler

import javax.tools.FileObject
import javax.tools.ForwardingJavaFileManager
import javax.tools.JavaFileManager
import javax.tools.JavaFileObject

/**
 * 输出字节码到JavaClassFile
 */
class MemFileManager extends ForwardingJavaFileManager {

    /**
     * 存储编译后的代码数据
     */
    Map<String, MemClassFileObject> classFileObjects

    MemFileManager(JavaFileManager fileManager) {
        super(fileManager)
        classFileObjects = new HashMap<>()
    }

    /**
     * 编译后加载类
     * <p>
     * 返回一个匿名的SecureClassLoader:
     * 加载由JavaCompiler编译后，保存在ClassJavaFileObject中的byte数组。
     */
    @Override
    ClassLoader getClassLoader(Location location) {
        return new ClassLoader() {
            @Override
            Class<?> findClass(String name) {
                JavaFileObject jfo = classFileObjects.get(name)
                byte[] bytes = jfo.bytes
                return super.defineClass(name, bytes, 0, bytes.length)
            }
        }
    }

    /**
     * 给编译器提供JavaClassObject，编译器会将编译结果写进去
     */
    @Override
    JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) {
        def classFileObject = new MemClassFileObject(className, kind)
        classFileObjects[className] = classFileObject
        return classFileObject
    }

}