package com.jndi.controller

import com.jndi.utils.JndiMapping
import com.jndi.entity.Jndi
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JndiController {

    @Value('${server.port}')
    Integer httpPort

    @Value('${jndi.ip}')
    String ip

    String getUrl(){
        "http://${ip}:${httpPort}/"
    }

    @JndiMapping
    Jndi evil(){
        new Jndi('evil', 'evilObj', 'com.jndi.template.EvilObj', url)
    }

    @JndiMapping
    Jndi base(){
        new Jndi('base', 'baseObj', 'com.jndi.entity.BaseObj', url)
    }

    @JndiMapping
    Jndi hello(){
        new Jndi('hello', 'nullObj', 'com.jndi.template.NullObj', url)
    }

}
