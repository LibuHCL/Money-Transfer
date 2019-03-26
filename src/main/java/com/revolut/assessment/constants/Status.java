package com.revolut.assessment.constants;

public enum Status {
  SUCCESS("SUCCESS"),
  ERROR("FAILED");

  private String status;

  Status(final String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
