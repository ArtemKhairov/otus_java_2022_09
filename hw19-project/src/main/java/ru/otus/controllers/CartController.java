package ru.otus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.model.Cart;
import ru.otus.model.CartItem;
import ru.otus.model.Product;
import ru.otus.service.ProductService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private HttpSession session;

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String showCart(Model model) {
        Cart cart = new Cart();
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("")
    public String addToCart(@RequestParam Long productId, Model model) {
        Product product = productService.getProduct(productId);
        CartItem item = new CartItem(product);
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        cart.addItem(item);
        model.addAttribute("cart", cart);
        return "cart";
    }
}
