package com.jndi.entity

class Jndi {

    String path
    String className
    String factory
    String factoryLocation

    Jndi(String path, String className, String factory, String factoryLocation) {
        this.path = path
        this.className = className
        this.factory = factory
        this.factoryLocation = factoryLocation
    }

}
