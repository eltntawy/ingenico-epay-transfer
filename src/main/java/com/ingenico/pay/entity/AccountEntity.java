package com.ingenico.pay.entity;

/**
 * Created by mohamedtantawy on 10/17/17.
 */
public class AccountEntity {


    private Integer id;
    private String name;
    private Double balance;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountEntity accountEntity = (AccountEntity) o;

        return id == accountEntity.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id=" + id +
                '}';
    }
}
