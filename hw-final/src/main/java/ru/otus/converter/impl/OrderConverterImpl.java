package ru.otus.converter.impl;

import ru.otus.converter.OrderConverter;
import ru.otus.dto.OrderDto;
import ru.otus.model.Order;
import ru.otus.model.User;
import ru.otus.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class OrderConverterImpl implements OrderConverter {

    private final UserService userService;

    @Override
    public Order fromOrderDto(OrderDto orderDto, String login, BigDecimal totalPrice) {
        User user = userService.getByLogin(login);

        orderDto.setUser(user);
        orderDto.setTotalPrice(totalPrice);

        Order order = new Order();

        order.setUser(orderDto.getUser());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setGoods(orderDto.getGoods());

        return order;
    }
}
