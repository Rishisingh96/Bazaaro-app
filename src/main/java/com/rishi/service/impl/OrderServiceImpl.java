package com.rishi.service.impl;

import com.rishi.domain.OrderStatus;
import com.rishi.domain.PaymentStatus;
import com.rishi.modal.*;
import com.rishi.repository.AddressRepository;
import com.rishi.repository.OrderItemRepository;
import com.rishi.repository.OrderRepository;
import com.rishi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    public final AddressRepository addressRepository;
    public final OrderRepository orderRepository;
    public final OrderItemRepository orderItemRepository;

    @Override
    public Set<Order> createOrder(User user, Address shippingAddress, Cart cart) {
        if (!user.getAddresses().contains(shippingAddress)) {
            user.getAddresses().add(shippingAddress);
        }
        Address address = addressRepository.save(shippingAddress);

        // brand 1 => 4 shirt
        // brand 2 => 3 pants
        // brand 3 => 1 watch

        Map<Long, List<CartItem>> itemsBySeller = cart.getCartItems().stream()
                .collect(Collectors.groupingBy(item -> item.getProduct()
                        .getSeller().getId()));
        Set<Order> orders = new HashSet<>();

        for(Map.Entry<Long, List<CartItem>> entry:itemsBySeller.entrySet()){
            Long sellerId = entry.getKey();
            List<CartItem> cartItems = entry.getValue();

           int totalOrderAmount = cartItems.stream().mapToInt(
                   CartItem::getSellingPrice
           ).sum();
           int totalItem = cartItems.stream().mapToInt(CartItem::getQuantity).sum();

           Order createCartOrder = new Order();
                createCartOrder.setUser(user);
                createCartOrder.setSellerId(sellerId);
                createCartOrder.setTotalMrpPrice(totalOrderAmount);
                createCartOrder.setTotalSellingPrice(totalOrderAmount);
                createCartOrder.setTotalItem(totalItem);
                createCartOrder.setShippingAddress(address);
                createCartOrder.setOrderStatus(OrderStatus.PENDING);
                createCartOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);

                Order savedOrder = orderRepository.save(createCartOrder);
                orders.add(savedOrder);

                List<OrderItem> orderItems = new ArrayList<>();

                for(CartItem cartItem:cartItems){
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(savedOrder);
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setMrpPrice(cartItem.getMrpPrice());
                    orderItem.setSize(cartItem.getSize());
                    orderItem.setSellingPrice(cartItem.getSellingPrice());
                    savedOrder.getOrderItems().add(orderItem);

                    OrderItem savedOrderItem = orderItemRepository.save(orderItem);
                    orderItems.add(savedOrderItem);
                }
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long Id) throws Exception {
        return orderRepository.findById(Id).orElseThrow(()->
            new Exception("Order not found with id: " + Id));
    }

    @Override
    public List<Order> userOrderHistory(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> sellerOrder(Long sellerId) {
        return orderRepository.findBySellerId(sellerId);
    }

    @Override
    public Order updateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        order.setOrderStatus(orderStatus);
        return orderRepository.save(order);
    }

    @Override
    public Order cancelOrder(Long orderId,User user) throws Exception {
        Order order = findOrderById(orderId);
        if(!user.getId().equals(order.getUser().getId())){
            throw new Exception("Unauthorized to cancel this order");
        }

        order.setOrderStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    @Override
    public OrderItem getOrderItemById(Long id) throws Exception {
        return orderItemRepository.findById(id).orElseThrow(()->
                new Exception("Order item not found with id: " + id));
    }
}
