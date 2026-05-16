import java.util.ArrayList;
import java.util.List;

record OrderSummary(String customerName, String orderNumber, double totalAmount) {
}

public class Order {
    private String orderNumber;
    private String customerName;
    private List<OrderItem> items = new ArrayList<>();

    public Order(String customerName, String orderNumber) {
        this.customerName = customerName;
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public double total() {
        double total = 0;
        for(OrderItem item : items) {
            total =+ item.total();
        }
        return total;
    }
    public static class OrderItem{
        private String productName;
        private Double unitPrice;
        private int quantity;
        public OrderItem(String productName, Double unitPrice, int quantity) {
            this.productName = productName;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
        }
        public double total(){
            return unitPrice * quantity;
        }

    }
}
