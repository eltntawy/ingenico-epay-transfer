package com.ingenico.pay.dto;

/**
 * Created by mohamedtantawy on 10/17/17.
 */
public class AccountDto {


    private Integer id;
    private String name;
    private Double balance;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

        AccountDto accountDto = (AccountDto) o;

        return id == accountDto.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                '}';
    }


}
