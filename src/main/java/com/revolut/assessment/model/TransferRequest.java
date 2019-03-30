package com.revolut.assessment.model;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.DOWN;
import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

  @JsonProperty("from")
  private String from;

  @JsonProperty("to")
  private String to;

  @JsonProperty("amount")
  private Long amount;

  @JsonProperty("currency")
  private String currency;

  public void validate() {
    requireNonNull(from, "From account is null");
    requireNonNull(to, "To account is null");
    requireNonNull(amount, "Amount is null");
    requireNonNull(currency, "Currency is null");
    if (from.equals(to)) {
      throw new IllegalArgumentException("Accounts are equal");
    }
    BigDecimal amountScale = new BigDecimal(this.amount).setScale(2, DOWN);
    if (amountScale.compareTo(ZERO) < 0) {
      throw new IllegalArgumentException("Amount is negative");
    }
  }

}
