package com.jndi.utils

import com.jndi.entity.Template
import freemarker.template.Configuration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TemplateUtils {

    @Autowired
    Configuration configuration

    String analysis(String tempName, Template map) {
        def template = configuration.getTemplate(tempName)
        def out = new StringWriter()
        template.process(map, out)
        out.close()
        out.toString()
    }

}
