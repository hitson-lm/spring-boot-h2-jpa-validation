package com.example.h2.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.env.PropertyResolver;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Clase para obtener propiedades de configuracion.
 */
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertyUtils {

  private static final String APPLICATION_CODE_PROPERTY = "spring.application.name";
  private static final String DEFAULT_CODE_PROPERTY = "unknown";

  // PropertyResolver sirve para acceder a las propiedades de configuracion
  private static PropertyResolver resolver;

  /**
   * Este metodo permite establecer el Objeto PropertyResolver que será
   * utilizada por Clase para resolver propiedades. Es necesario invocarlo
   * antes de usar los demas metodos.
   * */
  public static void setResolver(PropertyResolver resolverEnv) {
    PropertyUtils.resolver = resolverEnv;

  }

  public static Optional<String> getOptionalValue(String property) {

    return Optional.of(resolver.getProperty(property));

  }

  public static String getApplicationCode() {
    return getOptionalValue(APPLICATION_CODE_PROPERTY)
        .orElse(DEFAULT_CODE_PROPERTY);
  }



}
