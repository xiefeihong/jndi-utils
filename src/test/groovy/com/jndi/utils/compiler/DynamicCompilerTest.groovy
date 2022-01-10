package com.jndi.utils.compiler

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DynamicCompilerTest {

    @Autowired
    DynamicCompiler compiler

    String className

    @BeforeEach
    void setUp() {
//        fullName = 'com.xiefeihong.compiler.DynaClass'
        className = 'DynaClass'
    }

    @AfterEach
    void tearDown() {
        compiler.closeFileManager()
    }

    @Test
    void compiler(){
        def src = '''
//package com.xiefeihong.compiler;
public class DynaClass {
    public String toString() {
        return "Hello, I am " + this.getClass().getSimpleName();
    }
    public static void main(String[] args) {
        System.out.println("main");
    }
}
'''
        compiler.compiler(className, src.toString())
    }

    @Test
    void getBytes(){
        def bytes = compiler.getBytes(className)
        println new String(bytes)
    }

    @Test
    void getClazz(){
        def clazz = compiler.getClass(className)
        println clazz.getConstructor().newInstance()
    }

    @Test
    void cleanClass(){
        compiler.cleanClass(className)
    }

}