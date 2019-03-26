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
    public static final String ACCOUNTS = "ACCOUNTS";
  }
}
