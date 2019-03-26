package com.revolut.assessment.model;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

  @JsonProperty("number")
  private String number;

  @JsonProperty("balance")
  private String balance;

  @JsonProperty("currency")
  private String currency;

  @JsonProperty("status")
  private boolean status;

  @JsonProperty("limit")
  private String limit;

  public void validate() {
    requireNonNull(number, "Account number is empty");
  }
}
