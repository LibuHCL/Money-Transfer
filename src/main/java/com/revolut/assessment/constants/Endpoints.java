package com.revolut.assessment.constants;

public enum Endpoints {

  ACCOUNTS("/accounts");

  private String endpoint;

  Endpoints(final String endpoint) {
    this.endpoint = endpoint;
  }

  public String endpoint() {
    return endpoint;
  }

  public static class Constants {
    public static final String CONTEXT="/payments";
    public static final String ACCOUNTS = "/accounts";
    public static final String SEARCH = "/accounts/{accountNumber}";
    public static final String USERS = "/users";
    public static final String RATE="/rates";
    public static final String CONVERSION="/conversions";

  }
}
