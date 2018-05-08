package com.nytimes.subscription.platform.interview.pojo;

import java.util.List;

public class Result {

    List<?> order;

    int sum;

    public List<?> getOrder() {
        return order;
    }

    public void setOrder(List<?> order) {
        this.order = order;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }


    public void print() {
        order.stream().map(t -> t + " ").forEach(System.out::print);
        System.out.println("(" + sum + ")");

    }
}
