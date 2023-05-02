package ru.otus.converter.impl;

import ru.otus.converter.GoodConverter;
import ru.otus.dto.GoodDto;
import ru.otus.model.Good;
import org.springframework.stereotype.Component;

@Component
public class GoodConverterImpl implements GoodConverter {

    @Override
    public GoodDto convertToGoodDto(Good good) {
        GoodDto goodDto = new GoodDto();

        goodDto.setTitle(good.getTitle());
        goodDto.setPrice(good.getPrice());

        return goodDto;
    }
}
