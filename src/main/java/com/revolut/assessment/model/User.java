package com.revolut.assessment.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "User")

public class User implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "userId", unique = true, nullable = false)
  private Long id;

  @Column(name = "email", unique = true, nullable = false, length = 100)
  private String email;

  @Column(name = "first_name", unique = false, nullable = false, length = 100)
  private String firstName;

  @Column(name = "last_name", unique = false, nullable = false, length = 100)
  private String lastName;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "id")
  private Set<Account> accounts;

  public User() {
  }

  public User(final String email, final String firstName, final String lastName) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final User user = (User) o;

    return id.equals(user.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
