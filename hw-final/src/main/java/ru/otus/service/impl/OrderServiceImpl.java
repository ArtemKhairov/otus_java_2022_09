package ru.otus.service.impl;

import ru.otus.converter.OrderConverter;
import ru.otus.dto.GoodDto;
import ru.otus.dto.OrderDto;
import ru.otus.model.Good;
import ru.otus.model.Order;
import ru.otus.service.GoodService;
import ru.otus.service.OrderService;
import ru.otus.dao.OrderDAO;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class.getName());

    private static final String ORDER_NOT_MADE = "Make your order\n";
    private static final String CHOSEN_GOODS = "You have already chosen:\n\n";
    private static final String ORDER = "order";
    private static final String EMPTY_VALUE = "";
    private static final String ORDER_NOT_PLACED = "order not placed yet\n";
    private static final String RESULT_ORDER = "your order:\n";

    private final OrderDAO orderDAO;
    private final GoodService goodService;
    private final OrderConverter orderConverter;

    @Override
    public OrderDto getOrderDto(HttpSession session) {
        if (session.getAttribute(ORDER) != null) {
            return (OrderDto) session.getAttribute(ORDER);
        }
        return new OrderDto();
    }

    @Override
    @Transactional
    public void save(OrderDto orderDto, String login, BigDecimal totalPrice) {
        Order order = orderConverter.fromOrderDto(orderDto, login, totalPrice);

        if (!totalPrice.equals(BigDecimal.valueOf(0))) {
            orderDAO.save(order);

            LOGGER.info("New order: {}", order);
        }
    }

    @Override
    @Transactional
    public void addGoodToOrder(String option, OrderDto orderDto) {
        GoodDto goodDto = goodService.getGoodFromOption(option);

        orderDto.getGoods().add(new Good(goodDto.getTitle(), goodDto.getPrice()));

        LOGGER.info("Your goods: {}", orderDto.getGoods());
    }

    @Override
    @Transactional
    public void deleteGoodFromOrder(String option, OrderDto orderDto) {
        GoodDto goodDto = goodService.getGoodFromOption(option);

        orderDto.getGoods().remove(new Good(goodDto.getTitle(), goodDto.getPrice()));

        LOGGER.info("Your goods after removing {} : {}", goodDto.getTitle(), orderDto.getGoods());
    }

    @Override
    public void updateData(HttpSession session, OrderDto orderDto) {

        orderDto.getGoods().clear();

        session.setAttribute(ORDER, orderDto);

        session.setAttribute("chosenGoods", ORDER_NOT_MADE);
    }

    @Override
    public String printChosenGoods(OrderDto orderDto) {
        if (orderDto.getGoods().isEmpty()) {
            return ORDER_NOT_MADE;
        }

        StringBuilder sb = new StringBuilder(CHOSEN_GOODS);

        int count = 1;

        for (Good good : orderDto.getGoods()) {
            sb.append(count)
                    .append(") ")
                    .append(good.getTitle())
                    .append(" ")
                    .append(good.getPrice())
                    .append(" $\n");

            count++;
        }

        return sb.toString();
    }

    @Override
    public String printOrder(OrderDto orderDto) {
        if (orderDto.getGoods().isEmpty()) {
            return EMPTY_VALUE;
        }

        return printChosenGoods(orderDto).replace(CHOSEN_GOODS, EMPTY_VALUE);
    }

    @Override
    public BigDecimal getTotalPrice(OrderDto orderDto) {
        BigDecimal count = BigDecimal.valueOf(0);

        for (Good good : orderDto.getGoods()) {
            count = count.add(good.getPrice());
        }

        LOGGER.info("Total price: {}", count);

        return count;
    }

    @Override
    public String orderResult(BigDecimal totalPrice) {
        if (totalPrice.equals(BigDecimal.valueOf(0))) {

            LOGGER.info(ORDER_NOT_PLACED);

            return ORDER_NOT_PLACED;
        }

        return RESULT_ORDER;
    }
}
