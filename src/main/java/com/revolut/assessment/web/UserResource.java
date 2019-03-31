package com.revolut.assessment.web;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.assessment.constants.Endpoints;
import com.revolut.assessment.constants.Status;
import com.revolut.assessment.model.UserRequest;
import com.revolut.assessment.model.WebResponse;
import com.revolut.assessment.services.UserService;
import com.revolut.assessment.util.ApplicationContextUtil;

@Path(value = Endpoints.Constants.CONTEXT)
public class UserResource {

  private static Logger LOG = LoggerFactory.getLogger(UserResource.class);
  private final UserService userService = ApplicationContextUtil.getUserService();

  @POST
  @Path(value = Endpoints.Constants.USERS)
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response create(@Valid UserRequest userRequest, @Context UriInfo uriInfo) {
    UriBuilder builder = uriInfo.getAbsolutePathBuilder();
    builder.path(userRequest.getEmail());
    try {
      userRequest.validate();
      userService.create(userRequest.getEmail(), userRequest.getFirstName(), userRequest.getLastName());
      return Response.created(builder.build()).entity(new WebResponse(Status.SUCCESS.getStatus(), "user has been created successfully")).build();
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return Response.status(Response.Status.BAD_REQUEST).entity("user has not created-Bad Request").build();
    }
  }
}
