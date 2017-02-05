package sample.data.mongo;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository repository;
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/")
    public Customer custom(@RequestParam(value="name", defaultValue="World") String name) {
        return new Customer("Customer",
                            String.format(template, name));
    }
    @RequestMapping("/customers")
    public List<Customer> customs() {
    
        return repository.findAll();
    }
    
    @RequestMapping(value="/greetingpost",method=RequestMethod.POST)
    public Customer greetingPost(@RequestBody Customer customer) {
       System.out.println(customer.getFirstName());
       System.out.println(customer.getLastName());
       repository.save(customer);
       return customer;

    }
}
