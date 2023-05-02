package ru.otus.converter;

import ru.otus.dto.OrderDto;
import ru.otus.model.Order;

import java.math.BigDecimal;

public interface OrderConverter {

    Order fromOrderDto(OrderDto orderDto, String login, BigDecimal totalPrice);
}
