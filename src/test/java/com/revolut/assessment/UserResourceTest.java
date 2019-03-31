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
import com.revolut.assessment.model.UserRequest;
import com.revolut.assessment.web.UserResource;

public class UserResourceTest extends JerseyTest {

  @Override
  public Application configure() {
    enable(TestProperties.LOG_TRAFFIC);
    enable(TestProperties.DUMP_ENTITY);
    return new ResourceConfig(UserResource.class);
  }

  @Test
  public void createUserTest() {
    UserRequest userRequest = new UserRequest("testuser@gmail.com", "Test", "User");
    Response response = target(Endpoints.Constants.CONTEXT + Endpoints.Constants.USERS)
        .request()
        .post(Entity.entity(userRequest, MediaType.APPLICATION_JSON));

    assertEquals("201 created", 201, response.getStatus());
    assertNotNull("User created", response.getEntity());
  }

  @Test
  public void createInvalidUserTest() {
    UserRequest userRequest = new UserRequest();
    Response response = target( Endpoints.Constants.CONTEXT + Endpoints.Constants.USERS)
        .request()
        .post(Entity.entity(userRequest, MediaType.APPLICATION_JSON));

    assertEquals("500 created", 500, response.getStatus());
    assertNotNull("User request is invalid ", response.getEntity());
  }
}
