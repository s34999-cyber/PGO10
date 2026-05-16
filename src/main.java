import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

record UserForm(String email, String password, int age) {
    public UserForm{
        if(email.isBlank()) {
            throw new IllegalArgumentException("do not");
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

public class main {
    public static void main (String args[]) {
        UserValidator validator = new UserValidator();
        validator.addRule(form -> form.email().contains("@"));
        validator.addRule(form -> form.password().length() >= 8);
        validator.addRule(form -> form.age() >= 18);


        UserForm form = new UserForm("anna@example.com", "secure123", 20);
        System.out.println(validator.isValid(form));
    }
}
