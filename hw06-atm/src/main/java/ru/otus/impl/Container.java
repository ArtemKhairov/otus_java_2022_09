package ru.otus.impl;

import ru.otus.Banknote;
import ru.otus.interfaces.IContainer;

import java.util.*;

import static ru.otus.Banknote.*;

public class Container implements IContainer {
    private final TreeMap<Banknote, Integer> moneyBox = new TreeMap<>(Comparator.comparing(Banknote::getValue).reversed());

    public void put(Banknote banknot, Integer count){
        if(count < 0)
            throw new IllegalArgumentException(String.format("argument can't be lower than zero: %d", count));

        moneyBox.put(banknot, getCountBanknots(banknot) + count);
    }

    private Map<Banknote, Integer> extract(Banknote banknot, Integer count) {
        if(count < 0) {
            throw new IllegalArgumentException(String.format("argument can't be lower than zero: %d", count));
        }
        if(moneyBox.getOrDefault(banknot,0) < count)
            throw  new IllegalArgumentException("Not enough banknots");

        moneyBox.compute(banknot,(k,v) -> v - count);
        return Map.of(banknot, count);
    }

    public Map<Banknote, Integer> extract(Map<Banknote, Integer> mapForExtract) {
        final Map<Banknote, Integer> money = new HashMap<>();
        mapForExtract.entrySet()
                .stream()
                .map(entry -> extract( entry.getKey(),entry.getValue()))
                .forEach(money::putAll);
        return money;
    }

    public NavigableSet<Banknote> getSetBanknot() {
        return moneyBox.navigableKeySet();
    }

    public Integer getCountBanknots(Banknote banknot) {return moneyBox.getOrDefault(banknot,0);}

    public Integer getBalance(){
        return moneyBox.entrySet()
                .stream()
                .map(x -> x.getValue()*x.getKey().getValue())
                .reduce(0,Integer::sum);
    }

    public Map<Banknote, Integer> getBalanceAsMap(){
        return Map.copyOf(moneyBox);
    }
}