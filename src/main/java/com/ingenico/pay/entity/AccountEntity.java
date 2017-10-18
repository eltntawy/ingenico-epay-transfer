package com.ingenico.pay.entity;

/**
 * Created by mohamedtantawy on 10/17/17.
 */
public class AccountEntity {


    private String id;
    private String name;
    private double balance;


    public String getId() {
        return id;
    }

    public synchronized void  setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountEntity that = (AccountEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id=" + id +
                '}';
    }
}
