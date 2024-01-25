package com.FernandoSSI.Library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Document( collection = "wallet")
public class Wallet implements Serializable {

    //remember to implement ordering by date in pagination!!
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private List<Order> orders;
    private Double invoicing;
    private Double spending;
    private Double profit;

    public Wallet() {
    }

    public Wallet(LocalDate date, List<Order> orders, Double invoicing, Double spending, Double profit) {
        this.date = date;
        this.orders = orders;
        this.invoicing = invoicing;
        this.spending = spending;
        this.profit = profit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Double getInvoicing() {
        return invoicing;
    }

    public void setInvoicing(Double invoicing) {
        this.invoicing = invoicing;
    }

    public Double getSpending() {
        return spending;
    }

    public void setSpending(Double spending) {
        this.spending = spending;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(date, wallet.date) && Objects.equals(orders, wallet.orders) && Objects.equals(invoicing, wallet.invoicing) && Objects.equals(spending, wallet.spending) && Objects.equals(profit, wallet.profit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, orders, invoicing, spending, profit);
    }
}
