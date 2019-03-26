package com.revolut.assessment.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/retail")
public class ServerConfig extends ResourceConfig {
  public ServerConfig() {
    packages("com.revolut.assessment.web");
  }
}
