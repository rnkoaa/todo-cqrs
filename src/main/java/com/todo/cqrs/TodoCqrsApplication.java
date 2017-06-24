package com.todo.cqrs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.google.common.eventbus.EventBus;
import com.todo.cqrs.lib.command.CommandBus;
import com.todo.cqrs.lib.command.SimpleCommandBus;
import com.todo.cqrs.lib.util.JSR310DateTimeSerializer;
import com.todo.cqrs.lib.util.JSR310LocalDateDeserializer;
import com.todo.cqrs.lib.util.JSR310LocalDateTimeConverters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.*;

@SpringBootApplication
public class TodoCqrsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoCqrsApplication.class, args);
    }

    @Bean
    public EventBus commandEventBus() {
        return new EventBus("commandEventBus");
    }

    @Bean
    public CommandBus commandBus() {
        return new SimpleCommandBus("simpleCommandBus");
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(OffsetDateTime.class, JSR310DateTimeSerializer.INSTANCE);
        module.addSerializer(ZonedDateTime.class, JSR310DateTimeSerializer.INSTANCE);
        //module.addSerializer(LocalDateTime.class, JSR310DateTimeSerializer.INSTANCE);

        module.addSerializer(LocalDateTime.class, new JSR310LocalDateTimeConverters.LocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new JSR310LocalDateTimeConverters.LocalDateTimeDeserializer());
        module.addDeserializer(OffsetDateTime.class, InstantDeserializer.OFFSET_DATE_TIME);
        module.addSerializer(Instant.class, JSR310DateTimeSerializer.INSTANCE);
        module.addDeserializer(LocalDate.class, JSR310LocalDateDeserializer.INSTANCE);
        objectMapper.registerModule(module);
        return objectMapper;
    }
/*
    @Bean
    @Scope("prototype")
    public Logger logger(InjectionPoint injectionPoint){
        return LoggerFactory.getLogger(injectionPoint.getMethodParameter().getContainingClass());
    }*/
}
