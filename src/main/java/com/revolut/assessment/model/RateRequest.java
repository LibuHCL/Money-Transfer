package com.revolut.assessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateRequest {

  @JsonProperty("rateId")
  private int rateId;

  @JsonProperty("currencyRate")
  private String currencyRate;

  @JsonProperty("currencyConversion")
  private String currencyConversion;

}
