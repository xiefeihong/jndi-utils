package com.jndi.utils.compiler

import javax.tools.SimpleJavaFileObject

/**
 * 字符串java源代码。JavaFileObject表示
 */
class MemJavaFileObject extends SimpleJavaFileObject {

    /**
     *    表示java源代码
     */
    CharSequence content

    MemJavaFileObject(String className, String content) {
        super(URI.create('string:///' + className.replaceAll('\\.', '/') + Kind.SOURCE.extension), Kind.SOURCE)
        this.content = content
    }

    /**
     * 获取需要编译的源代码
     * @param ignoreEncodingErrors
     * @return
     * @throws IOException
     */
    @Override
    CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return content
    }

}