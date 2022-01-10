package com.jndi.entity

class Template {

    String fileName
    String className
    String message
    String ip

    Template(String className) {
        this.fileName = className + '.java.ftl'
        this.className = className
    }

    Template(String className, String message) {
        this.fileName = className + '.java.ftl'
        this.className = className
        this.message = message
    }

    Template(String className, String message, String ip) {
        this.fileName = className + '.java.ftl'
        this.className = className
        this.message = message
        this.ip = ip
    }

}
