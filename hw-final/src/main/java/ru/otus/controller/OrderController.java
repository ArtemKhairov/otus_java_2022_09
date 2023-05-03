package ru.otus.controller;

import ru.otus.dto.OrderDto;
import ru.otus.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order")
    public String saveOrder(Model model, HttpSession session, Authentication authentication, OrderDto orderDto) {
//        System.out.println(authentication)
        String login = authentication.getName();
        BigDecimal totalPrice = (BigDecimal) session.getAttribute("totalPrice");
        String orderList = (String) session.getAttribute("order");
        String orderResult = orderService.orderResult(totalPrice);

        model.addAttribute("login", login);
        model.addAttribute("orderList", orderList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("orderResult", orderResult);

        orderService.save(orderDto, login, totalPrice);

        orderService.updateData(session, orderDto);

        return "order";
    }
}
