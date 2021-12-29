package com.jndi.controller

import com.jndi.server.ClassServer
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
    ClassServer classServer

    @GetMapping('/com/jndi/entity/{className}.class')
    ResponseEntity<byte[]> downloadByClass(@PathVariable String className) {
        def classPath = ("com/jndi/entity/${className}.class")
        log.info(classPath)
        HttpHeaders httpHeaders = new HttpHeaders()
        ContentDisposition contentDisposition = ContentDisposition.builder('attachment').filename(className + '.class').build()
        httpHeaders.setContentDisposition(contentDisposition)
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM)
        new ResponseEntity<byte[]>(classServer.getClassBytes(classPath), httpHeaders, HttpStatus.OK)
    }

    @GetMapping('/com/jndi/template/{className}.class')
    ResponseEntity<byte[]> downloadByTemplate(@PathVariable String className) {
        log.info(className)
        HttpHeaders httpHeaders = new HttpHeaders()
        ContentDisposition contentDisposition = ContentDisposition.builder('attachment').filename(className + '.class').build()
        httpHeaders.setContentDisposition(contentDisposition)
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM)
        new ResponseEntity<byte[]>(classServer.getClassBytesByTemplate(className + '.java.ftl'), httpHeaders, HttpStatus.OK)
    }

}
