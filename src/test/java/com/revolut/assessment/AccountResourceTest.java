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
import com.revolut.assessment.model.AccountRequest;
import com.revolut.assessment.web.AccountResource;


public class AccountResourceTest extends JerseyTest {

  @Override
  public Application configure() {
    enable(TestProperties.LOG_TRAFFIC);
    enable(TestProperties.DUMP_ENTITY);
    return new ResourceConfig(AccountResource.class);
  }

  @Test
  public void createAccountTest() {
    AccountRequest accountRequest = new AccountRequest("101", "101", "INR", true, "1000");
    Response response = target( Endpoints.Constants.CONTEXT + Endpoints.Constants.ACCOUNTS)
        .request()
        .post(Entity.entity(accountRequest, MediaType.APPLICATION_JSON));

    assertEquals("Should return status 201", 201, response.getStatus());
    assertNotNull("Should return account", response.getEntity());
  }

  @Test
  public void createAccountInvalidAccountNumberTest() {
    AccountRequest accountRequest = new AccountRequest();
    Response response = target( Endpoints.Constants.CONTEXT + Endpoints.Constants.ACCOUNTS)
        .request()
        .post(Entity.entity(accountRequest, MediaType.APPLICATION_JSON));

    assertEquals("Should return status 500", 500, response.getStatus());
    assertNotNull("Invalid account number", response.getEntity());
  }


}
