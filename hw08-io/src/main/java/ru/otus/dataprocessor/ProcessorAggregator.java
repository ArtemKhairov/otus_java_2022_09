package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        //группирует выходящий список по name, при этом суммирует поля value
        Map<String, Double> map = new TreeMap<>();
        for (Measurement datum : data) {
            map.merge(datum.getName(), datum.getValue(), Double::sum);
        }
        return map;
    }
}
