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
import com.revolut.assessment.model.AccountRequest;
import com.revolut.assessment.web.AccountResource;


public class AccountResourceTest extends JerseyTest {

  @Override
  public Application configure() {
    enable(TestProperties.LOG_TRAFFIC);
    enable(TestProperties.DUMP_ENTITY);
    return new ResourceConfig(AccountResource.class);
  }

  public void createAccountTest() {
    AccountRequest accountRequest = new AccountRequest("101", "101", "INR", true, "1000");
    Response response = target("/revolut-bank" + Endpoints.Constants.CONTEXT + Endpoints.Constants.ACCOUNTS)
        .request()
        .post(Entity.entity(accountRequest, MediaType.APPLICATION_JSON));

    assertEquals("Should return status 200", 200, response.getStatus());
    assertNotNull("Should return account", response.getEntity());
  }

  public void createAccountInvalidAccountNumberTest() {
    AccountRequest accountRequest = new AccountRequest("", "101", "EUR", true, "10000");
    Response response = target("/revolut-bank" + Endpoints.Constants.CONTEXT + Endpoints.Constants.ACCOUNTS)
        .request()
        .post(Entity.entity(accountRequest, MediaType.APPLICATION_JSON));

    assertEquals("Should return status 400", 400, response.getStatus());
    assertNotNull("Invalid account number", response.getEntity());
  }


}
