package ru.otus.interfaces;

import ru.otus.Banknote;


import java.util.Collection;
import java.util.Map;

public interface ICashCalculator {

    void receive(Map<Banknote, Integer> mapOfBanknots);

    void receive(Collection<Banknote> banknotes);

    Map<Banknote, Integer> give(Integer sum);
}