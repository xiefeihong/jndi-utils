package com.jndi.entity

class Jndi {

    String path
    String className
    String factory
    String factoryLocation
    Template template

    Jndi(String path, String className, String factoryLocation, Template template) {
        this.path = path
        this.className = className
        this.factory = 'com.jndi.template.' + className
        this.factoryLocation = factoryLocation
        this.template = template
    }

}
