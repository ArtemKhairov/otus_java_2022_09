package ru.otus.converter;

import ru.otus.dto.GoodDto;
import ru.otus.model.Good;

public interface GoodConverter {

    GoodDto convertToGoodDto(Good good);
}