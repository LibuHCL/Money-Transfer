package com.revolut.assessment.model;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Account")
public class Account implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "accountId")
  private Long id;

  @Column(name = "number")
  private String number;

  @Column(name = "balance")
  private Long balance;

  @Column(name = "currency")
  private String currency;

  @Column(name = "status")
  private boolean status;

  @Column(name = "range")
  private Long limit;

  @ManyToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name = "userId")
  private User user;

  public Account() {
  }

  public Account(final String number, final Long balance, final String currency, final boolean status, final Long limit) {
    this.number = number;
    this.balance = balance;
    this.currency = currency;
    this.status = status;
    this.limit = limit;
  }

  public BigDecimal getBalance() {
    return balance != null ? new BigDecimal(balance) : ZERO;
  }

  public String getNumber() {
    return number;
  }

  public String getCurrency() {
    return currency;
  }

  public void debit(Long amount) {
    balance = balance - amount;
  }

  public void credit(Long amount) {
    balance = balance == null ? amount : balance + amount;
  }

  public boolean greater(Account account) {
    return number.compareTo(account.number) > 0;
  }

  public void checkInsufficientBalance(Long amount) {
    if (balance.compareTo(amount) < 0) {
      throw new RuntimeException(format("Insufficient balance (%s) for account:%s, required %s", balance, number, amount));
    }
  }

  public boolean isActive() {
    final boolean status = true;
    if (!status) {
      return false;
    }
    return status;
  }

  public boolean hasLimitedAmount(Long amount) {
    final boolean status = true;
    if (amount.compareTo(limit) > 0) {
      return false;
    }
    return true;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final Account account = (Account) o;

    return id.equals(account.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
