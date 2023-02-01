package ru.otus.interfaces;

import ru.otus.Banknote;

import java.util.Map;
import java.util.NavigableSet;

public interface IContainer {
    void put(Banknote banknot, Integer count);

    Map<Banknote, Integer> extract(Map<Banknote, Integer> mapForExtract);
    NavigableSet<Banknote> getSetBanknot();

    Integer getCountBanknots(Banknote banknot);

    Integer getBalance();

    Map<Banknote, Integer> getBalanceAsMap();
}