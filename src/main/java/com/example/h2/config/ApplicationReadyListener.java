package com.example.h2.config;

import com.example.h2.utils.PropertyUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Clase que escucha el evento ApplicationReadyListener que se dispara
 * cuando el contexto de la aplicacion está completamente cargado.
 */
@Component
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

  /**
   * Metodo que obtiene el Environment del contexto de la aplicacion y lo establece
   * en la clase PropertyUtils para acceder a las propiedades de configuracion de manera centralizada.
   * @param event
   */
  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {

    final Environment env = event.getApplicationContext().getEnvironment();
    PropertyUtils.setResolver(env);
  }
}
