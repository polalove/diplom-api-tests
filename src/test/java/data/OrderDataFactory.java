package data;

import model.Order;

public class OrderDataFactory {
    public static Order createTestOrder(Long orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setPetId(12345L);
        order.setQuantity(2);
        order.setStatus("placed");
        order.setComplete(true);
        return order;
    }
}