package com.revolut.assessment.model;

import static java.util.Objects.requireNonNull;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

  @NotNull(message="Account number cannot be missing or empty")
  @JsonProperty("number")
  private String number;

  @NotNull(message="Account balance cannot be missing or empty")
  @JsonProperty("balance")
  private String balance;

  @NotNull(message="Currency cannot be missing or empty")
  @JsonProperty("currency")
  private String currency;

  @JsonProperty("status")
  private boolean status;

  @NotNull(message="Limit cannot be missing or empty")
  @JsonProperty("limit")
  private String limit;

  public void validate() {
    requireNonNull(number, "Account number is empty");
  }
}
