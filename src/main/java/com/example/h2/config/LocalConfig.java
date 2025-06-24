package com.example.h2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

/*
* Class LocalConfig
* Configuración para forzar el contexto local dependiendo al pais que se encuentre.
* **/
@Configuration
public class LocalConfig {

  @Bean
  public LocaleResolver localeResolver() {
    return new FixedLocaleResolver(Locale.ENGLISH);
 }

}
