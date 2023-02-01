package ru.otus.impl;

import ru.otus.Banknote;
import ru.otus.interfaces.ICashCalculator;
import ru.otus.interfaces.IContainer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
// класс рассчитывает сколько именно купюр необходимо выдать/положить в Контейнер
public class CashCalculator implements ICashCalculator {
    private final IContainer container;

    public CashCalculator(IContainer container){
        this.container = container;
    }

    public void receive(Map<Banknote, Integer> mapOfBanknots){
        mapOfBanknots.forEach(container::put);
    }

    public void receive(Collection<Banknote> banknotes) {
        Map<Banknote, Integer> nominalMap = new HashMap<>();
        banknotes.forEach(banknote -> nominalMap.put(banknote, nominalMap.getOrDefault(banknote, 0) + 1));
        receive(nominalMap);
    }

    public Map<Banknote, Integer> give(Integer sum){
        var sortedListBanknot = container.getSetBanknot();
        var calculatedBanknots = calculate(sum, sortedListBanknot);
        return container.extract(calculatedBanknots);
    }

    private Map<Banknote, Integer> calculate(Integer sum, Set<Banknote> sortedListBanknot){
        Map<Banknote, Integer> mapForExtract = new HashMap<>();

        var tmpSum = 0;
        for(var nominal: sortedListBanknot){
            var countBanknot = (sum- tmpSum)/nominal.getValue();
            if(countBanknot > 0 ){
                int allBanknots = container.getCountBanknots(nominal);
                int countBanknotForGive = Math.min(allBanknots, countBanknot);
                if(countBanknotForGive > 0){
                    mapForExtract.put(nominal,countBanknotForGive);
                    tmpSum += countBanknotForGive * nominal.getValue();
                    if(tmpSum == sum){
                        break;
                    }
                }
            }
        }

        if (tmpSum != sum){
            throw new RuntimeException(String.format("Can't give requested amount of money"));
        }

        return mapForExtract;
    }
}