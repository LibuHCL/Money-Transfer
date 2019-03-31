package com.revolut.assessment.model;

import static java.util.Objects.requireNonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

  @Email
  @JsonProperty("email")
  private String email;

  @NotNull(message = "First name cannot be missing or empty")
  @Size(min = 2, message = "First name must not be less than 2 characters")
  @JsonProperty("firstName")
  private String firstName;

  @NotNull(message = "Last name cannot be missing or empty")
  @Size(min = 2, message = "Last name must not be less than 2 characters")
  @JsonProperty("lastName")
  private String lastName;

  public void validate() {
    requireNonNull(email, "email is empty");
  }

}
