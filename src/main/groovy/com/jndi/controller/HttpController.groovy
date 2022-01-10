package com.jndi.controller

import com.jndi.server.SimpleJNDIServer
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
class HttpController {

    @Autowired
    SimpleJNDIServer jndiService

    @GetMapping('/com/jndi/template/{className}.class')
    ResponseEntity<byte[]> downloadByTemplate(@PathVariable String className) {
        log.info("class [${className}] download")
        def httpHeaders = new HttpHeaders()
        def contentDisposition = ContentDisposition.builder('attachment').filename(className + '.class').build()
        httpHeaders.setContentDisposition(contentDisposition)
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM)
        new ResponseEntity<byte[]>(jndiService.getBytes('com.jndi.template.' + className), httpHeaders, HttpStatus.OK)
    }

}
