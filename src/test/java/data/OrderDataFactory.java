package data;

import model.Order;

public class OrderDataFactory {
    public static Order generateTestOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setPetId(12345L);
        order.setQuantity(2);
        order.setStatus("placed");
        order.setComplete(true);
        return order;
    }

}