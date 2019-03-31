package com.revolut.assessment.web;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

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
import com.revolut.assessment.model.RateRequest;
import com.revolut.assessment.model.TransferRequest;
import com.revolut.assessment.model.WebResponse;
import com.revolut.assessment.services.RateService;
import com.revolut.assessment.util.ApplicationContextUtil;

@Path(value = Endpoints.Constants.CONTEXT)
public class RateResource {

  private static Logger LOG = LoggerFactory.getLogger(RateResource.class);
  private final RateService rateService = ApplicationContextUtil.getRateService();

  @POST
  @Path(value = Endpoints.Constants.RATE)
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response create(RateRequest rateRequest, @Context UriInfo uriInfo) {
    UriBuilder builder = uriInfo.getAbsolutePathBuilder();
    builder.path(rateRequest.getCurrencyConversion());
    try {
      rateService.create(rateRequest.getRateId(), rateRequest.getCurrencyConversion(), rateRequest.getCurrencyRate());
      return Response.created(builder.build()).entity(new WebResponse(Status.SUCCESS.getStatus(), "currency has been converted successfully")).build();
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return Response.status(Response.Status.BAD_REQUEST).entity("currency has not converted-Bad Request").build();
    }
  }

  @POST
  @Path(value = Endpoints.Constants.CONVERSION)
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response convert(TransferRequest transferRequest, @Context UriInfo uriInfo) {
    UriBuilder builder = uriInfo.getAbsolutePathBuilder();
    builder.path(transferRequest.getCurrency());
    try {
      transferRequest.validate();
      rateService.converter(transferRequest.getFrom(), transferRequest.getTo(), transferRequest.getAmount());
      return Response.created(builder.build()).entity(new WebResponse(Status.SUCCESS.getStatus(), "currency has been converted successfully")).build();
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return Response.status(Response.Status.PRECONDITION_FAILED).entity("currency conversion is not possible at this moment").build();
    }

  }

}
