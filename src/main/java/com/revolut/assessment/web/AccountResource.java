package com.revolut.assessment.web;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.assessment.constants.Endpoints;
import com.revolut.assessment.constants.Status;
import com.revolut.assessment.model.Account;
import com.revolut.assessment.model.AccountRequest;
import com.revolut.assessment.model.WebResponse;
import com.revolut.assessment.services.AccountService;
import com.revolut.assessment.util.ApplicationContextUtil;

@Path(value = Endpoints.Constants.CONTEXT)
public class AccountResource {

  private static Logger LOG = LoggerFactory.getLogger(AccountResource.class);

  private final AccountService accountService = ApplicationContextUtil.getAccountService();

  @POST
  @Path(value = Endpoints.Constants.ACCOUNTS)
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response create(AccountRequest accountRequest, @Context UriInfo uriInfo) {
    UriBuilder builder = uriInfo.getAbsolutePathBuilder();
    builder.path(accountRequest.getNumber());
    try {
      accountRequest.validate();
      accountService.create(accountRequest.getNumber(), Long.parseLong(accountRequest.getBalance()), accountRequest.getCurrency(), accountRequest.isStatus(),
          Long.parseLong(accountRequest.getLimit()));
      return Response.created(builder.build()).entity(new WebResponse(Status.SUCCESS.getStatus(), "account has been created successfully")).build();

    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return Response.status(Response.Status.BAD_REQUEST).entity("account has not created-Bad Request").build();
    }
  }

  @GET
  @Path(value = Endpoints.Constants.SEARCH)
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response find(@PathParam("accountNumber") String accountNumber, @Context UriInfo uriInfo) {
    UriBuilder builder = uriInfo.getAbsolutePathBuilder();
    builder.path(accountNumber);
    try {
      Account account = accountService.find(accountNumber);
      if (account == null) {
        return Response.status(Response.Status.NOT_FOUND).entity("Account not found for numner: " + accountNumber).build();
      }
      return Response.ok(builder.build()).entity(new WebResponse(Status.SUCCESS.getStatus(), "account exist with the account number in the request")).build();
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return Response.status(Response.Status.NOT_FOUND).entity("account not found in the system").build();

    }
  }
}
