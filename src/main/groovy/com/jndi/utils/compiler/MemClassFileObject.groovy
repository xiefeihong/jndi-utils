package com.jndi.utils.compiler

import javax.tools.SimpleJavaFileObject

/**
 * 存储编译后的字节码
 */
class MemClassFileObject extends SimpleJavaFileObject {

    /**
     * Compiler编译后的byte数据会存在这个ByteArrayOutputStream对象中，
     * 后面可以取出，加载到JVM中。
     */
    ByteArrayOutputStream byteArrayOutputStream
    final String name

    MemClassFileObject(String className, Kind kind) {
        super(URI.create('string:///' + className.replaceAll('\\.', '/') + kind.extension), kind)
        this.byteArrayOutputStream = new ByteArrayOutputStream()
        this.name = className
    }

    /**
     * 覆盖父类SimpleJavaFileObject的方法。
     * 该方法提供给编译器结果输出的OutputStream。
     * 
     * 编译器完成编译后，会将编译结果输出到该 OutputStream 中，我们随后需要使用它获取编译结果
     *
     * @return
     * @throws IOException
     */
    @Override
    OutputStream openOutputStream() {
        return this.byteArrayOutputStream
    }

    @Override
    String getName() {
        return name
    }

    /**
     * FileManager会使用该方法获取编译后的byte，然后将类加载到JVM
     */
    byte[] getBytes() {
        return this.byteArrayOutputStream.toByteArray()
    }

}