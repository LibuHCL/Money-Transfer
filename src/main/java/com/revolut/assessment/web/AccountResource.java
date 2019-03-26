package com.revolut.assessment.web;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.math.BigDecimal;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.assessment.constants.Endpoints;
import com.revolut.assessment.constants.Status;
import com.revolut.assessment.model.AccountRequest;
import com.revolut.assessment.model.WebResponse;
import com.revolut.assessment.services.AccountService;
import com.revolut.assessment.services.Impl.AccountServiceImpl;

@Path("/payments")
public class AccountResource {

  private static Logger LOG = LoggerFactory.getLogger(AccountResource.class);

  @POST
  @Path(value = Endpoints.Constants.ACCOUNTS)
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public WebResponse create(AccountRequest accountRequest) {

    LOG.info("The account creation request for the account number {}", accountRequest.getNumber());

    final AccountService accountService = new AccountServiceImpl();

    try {
      accountRequest.validate();
      accountService.create(accountRequest.getNumber(), new BigDecimal(accountRequest.getBalance()), accountRequest.getCurrency(), accountRequest.isStatus(),
          new BigDecimal(accountRequest.getLimit()));
      return new WebResponse();

    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return new WebResponse(Status.SUCCESS.getStatus(), e.getMessage());
    }
  }
}
