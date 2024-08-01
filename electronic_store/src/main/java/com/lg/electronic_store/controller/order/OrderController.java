package com.lg.electronic_store.controller.order;

import com.lg.electronic_store.dao.order.OrderDto;
import com.lg.electronic_store.dao.order.ResponseOrderDto;
import com.lg.electronic_store.dao.product.ProductDto;
import com.lg.electronic_store.dao.user.UserRequest;
import com.lg.electronic_store.entity.shoppingCart.ShoppingCart;
import com.lg.electronic_store.entity.shoppingCartItem.ShoppingCartItem;
import com.lg.electronic_store.entity.user.User;
import com.lg.electronic_store.service.order.OrderService;
import com.lg.electronic_store.service.product.ProductService;
import com.lg.electronic_store.service.user.UserService;
import com.lg.electronic_store.utils.date.DateUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService orderService, UserService userService, ProductService productService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }


    @PostMapping
    public ResponseEntity<ResponseOrderDto> placeOrder(@RequestBody OrderDto orderDto) {
        logger.info("Received order request: {}", orderDto);

        UserRequest user = userService.getUser(orderDto.getUserId());
        logger.info("Found user: {}", user);

        List<ShoppingCartItem> cartItems = orderDto.getCartItems().stream()
                .map(item -> {
                    ProductDto product = productService.get(item.getProductId());
                    logger.info("Found product: {}", product);

                    ShoppingCartItem cartItem = new ShoppingCartItem();
                    cartItem.setProductId(product.getId());
                    cartItem.setProductName(product.getTitle());
                    cartItem.setQuantity(item.getQuantity());
                    cartItem.setPrice(product.getPrice());
                    return cartItem;
                })
                .collect(Collectors.toList());

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setOrderDescription(orderDto.getOrderDescription());
        shoppingCart.setUser(modelMapper.map(user, User.class));
        cartItems.forEach(shoppingCart::addCartItem);

        BigDecimal totalAmount = shoppingCart.getCartItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        shoppingCart.setTotalPrice(totalAmount);

        ShoppingCart savedCart = orderService.saveOrder(shoppingCart);
        logger.info("Saved shopping cart: {}", savedCart);

        ResponseOrderDto responseOrderDto = new ResponseOrderDto(
                savedCart.getId(),
                totalAmount,
                DateUtil.getCurrentDateTime(),
                new Random().nextInt(1000),
                savedCart.getOrderDescription()
        );

        logger.info("Order placed successfully. Response: {}", responseOrderDto);

        return ResponseEntity.ok(responseOrderDto);
    }

    @GetMapping("/getOrder/{orderId}")
    public ResponseEntity<ShoppingCart> getOrderDetails(@PathVariable Long orderId) {
        logger.info("Fetching order details for orderId: {}", orderId);
        ShoppingCart order = orderService.getOrderDetail(orderId);
        logger.info("Found order: {}", order);
        return ResponseEntity.ok(order);
    }
}