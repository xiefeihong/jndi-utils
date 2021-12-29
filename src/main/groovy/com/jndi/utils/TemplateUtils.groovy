package com.jndi.utils

import freemarker.template.Configuration
import freemarker.template.Template
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.springframework.util.FileCopyUtils
import org.springframework.util.ResourceUtils

import java.nio.charset.StandardCharsets

@Component
class TemplateUtils {

    @Autowired
    Configuration configuration

    void analysis(String ftlName, String sourceName, Map<String, ?> map) {
        Template template = configuration.getTemplate(ftlName)
        Writer out = new FileWriter(new File(sourceName))
        template.process(map, out);
        out.close()
    }

    byte[] getClassBytes(String className) {
        new File(className).bytes
    }

}
