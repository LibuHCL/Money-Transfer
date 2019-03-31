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

import com.revolut.assessment.constants.Endpoints;
import com.revolut.assessment.model.UserRequest;
import com.revolut.assessment.web.AccountResource;

public class UserResourceTest extends JerseyTest {

  @Override
  public Application configure() {
    enable(TestProperties.LOG_TRAFFIC);
    enable(TestProperties.DUMP_ENTITY);
    return new ResourceConfig(AccountResource.class);
  }

  public void createUserTest() {
    UserRequest userRequest = new UserRequest("testuser@gmail.com", "Test", "User");
    Response response = target("/revolut-bank" + Endpoints.Constants.CONTEXT + Endpoints.Constants.USERS)
        .request()
        .post(Entity.entity(userRequest, MediaType.APPLICATION_JSON));

    assertEquals("200 created", 200, response.getStatus());
    assertNotNull("User created", response.getEntity());
  }
}
