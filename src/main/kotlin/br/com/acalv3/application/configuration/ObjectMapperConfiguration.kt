package br.com.acalv3.application.configuration

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.format.DateTimeFormatter
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class ObjectMapperConfiguration {

    private val dateFormat = "yyyy-MM-dd"
    private val dateTimeFormat = "yyyy-MM-dd HH:mm:ss"

    @Bean
    fun jsonCustomizer(): Jackson2ObjectMapperBuilderCustomizer? =
       Jackson2ObjectMapperBuilderCustomizer {
           builder: Jackson2ObjectMapperBuilder ->
           builder.simpleDateFormat(dateTimeFormat)
           builder.serializers(LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)))
           builder.serializers(LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)))
       }
}