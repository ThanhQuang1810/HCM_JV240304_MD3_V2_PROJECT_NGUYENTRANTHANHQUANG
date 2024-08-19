package ra.designimpl;

import ra.design.IGeneric;
import ra.entity.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements IGeneric<Order, Integer> {
    private List<Order> orders = new ArrayList<>();

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(orders);
    }

    @Override
    public void save(Order order) {
        orders.add(order);
    }

    @Override
    public Order findById(Integer orderId) {
        Optional<Order> order = orders.stream()
                .filter(o -> o.getOrderId() == orderId)
                .findFirst();
        return order.orElse(null);
    }

    @Override
    public void delete(Integer orderId) {
        orders.removeIf(o -> o.getOrderId() == orderId);
    }
}
