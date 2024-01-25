package tech.hidetora.springboot3_2demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

@Controller
@ResponseBody
class CustomerHttpController {
    private final CustomerRepository customerRepository;

    CustomerHttpController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // curl localhost:8080/customers
    @GetMapping("/customers")
    public Collection<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    // curl localhost:8080/customers/1
    @GetMapping("/customers/{name}")
    public Collection<Customer> getCustomer(@PathVariable String name) {
        return this.customerRepository.findByName(name);
    }
}

//@Controller
//@ResponseBody
//@RequiredArgsConstructor
//class AiController {
//    private final ChatClient chatClient;
//
//    // curl localhost:8080/customers
//    @GetMapping("/ai/generate")
//    public Map<String, String> generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
//        return Map.of("response", chatClient.generate(message));
//    }
//}

interface CustomerRepository extends ListCrudRepository<Customer, Integer> {
    Collection<Customer> findByName(String name);
}

record Customer(@Id Integer id, String name) {
}
