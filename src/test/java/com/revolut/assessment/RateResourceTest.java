package com.revolut.assessment;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import com.revolut.assessment.constants.Endpoints;
import com.revolut.assessment.model.RateRequest;
import com.revolut.assessment.web.RateResource;

public class RateResourceTest extends JerseyTest {

  @Override
  public Application configure() {
    enable(TestProperties.LOG_TRAFFIC);
    enable(TestProperties.DUMP_ENTITY);
    return new ResourceConfig(RateResource.class);
  }

  @Test
  public void createValidRateFromCurrencyTest() {
    RateRequest userRequest = new RateRequest(100, "100", "EUR");
    Response response = target( Endpoints.Constants.CONTEXT + Endpoints.Constants.RATE)
        .request()
        .post(Entity.entity(userRequest, MediaType.APPLICATION_JSON));

    assertEquals("201 created", 201, response.getStatus());
    assertNotNull("Rate created", response.getEntity());
  }

  @Test
  public void createInvalidRateFromCurrencyTest() {
    RateRequest userRequest = new RateRequest();
    Response response = target( Endpoints.Constants.CONTEXT + Endpoints.Constants.RATE)
        .request()
        .post(Entity.entity(userRequest, MediaType.APPLICATION_JSON));

    assertEquals("500 created", 500, response.getStatus());
    assertNotNull("Rate not created", response.getEntity());
  }

}
