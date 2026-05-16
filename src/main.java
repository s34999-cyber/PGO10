import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

record UserForm(String email, String password, int age) {
    public UserForm{
        if(email.isBlank()) {
            throw new IllegalArgumentException("Email is blank.");
        }
    }

}

class UserValidator {
    private List<Predicate<UserForm>> rules = new ArrayList<>();

    public void addRule(Predicate<UserForm> rule){
        rules.add(rule);
    }

    public boolean isValid(UserForm form){
        for (Predicate<UserForm> rule : rules) {
            if(!rule.test(form)){
                return false;
            }
        }


        return true;
    }
}

record ServiceOrder(String clientName, int hours, double hourRate){

}

@FunctionalInterface
//has exactly one method, above tag ensures that
interface PriceStrategy {
    double calculate(ServiceOrder order);
}



public class main {
    public static void main (String args[]) {
        UserValidator validator = new UserValidator();
        validator.addRule(form -> form.email().contains("@"));
        validator.addRule(form -> form.password().length() >= 8);
        validator.addRule(form -> form.age() >= 18);


        UserForm form = new UserForm("anna@example.com", "secure123", 20);
        System.out.println(validator.isValid(form));

        ServiceOrder order = new ServiceOrder("Alpha Company", 10, 100.0);
        PriceCalculator calculator = new PriceCalculator();

        PriceStrategy standard = o -> o.hours() * o.hourRate();
        PriceStrategy discount = o -> o.hours() * o.hourRate() * 0.90;
        PriceStrategy weekend = o -> o.hours() * o.hourRate() * 1.25;

        System.out.println("Order made by:" + order.clientName() + " For:" +calculator.calculate(order, standard));
        System.out.println(calculator.calculate(order, discount));
        System.out.println(calculator.calculate(order, weekend));

        Order order2 = new Order("ORD-100", "Anna Kowalska");

        order2.addItem(new Order.OrderItem("Keyboard", 249.99, 1));
        order2.addItem(new Order.OrderItem("Mouse", 99.99, 2));

        OrderSummary summary = new OrderSummary(
                order2.getOrderNumber(),
                order2.getCustomerName(),
                order2.total()
        );

        System.out.println(summary);
    }


}
