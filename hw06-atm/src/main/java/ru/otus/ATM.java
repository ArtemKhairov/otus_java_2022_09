package ru.otus;

import ru.otus.interfaces.IContainer;
import ru.otus.interfaces.ICashCalculator;
import ru.otus.impl.Container;
import ru.otus.impl.CashCalculator;

import java.util.*;
import java.util.stream.StreamSupport;

public class ATM {
    private final IContainer container;
    private final ICashCalculator operator;

    public ATM() {
        this.container = new Container();
        this.operator = new CashCalculator(container);
    }

    public void receive(Map<Banknote, Integer> banknotes) {
        operator.receive(banknotes);
    }
    public void receive(List<Banknote> banknotes) {
        operator.receive(banknotes);
    }

    public Map<Banknote, Integer> give(Integer sum) {
        return operator.give(sum);
    }

    public Integer getBalance() {
        return container.getBalance();
    }

    public Map<Banknote, Integer> getBalanceAsMap() {
        return container.getBalanceAsMap();
    }
}