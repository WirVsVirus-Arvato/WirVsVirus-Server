package org.wirvsvirushackathon.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.*


@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
    }
}
