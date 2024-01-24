package tech.hidetora.springboot3_2demo;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.prompt.Prompt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

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

@Controller
@ResponseBody
@RequiredArgsConstructor
class AiController {
    private final OpenAiChatClient openAiChatClient;

    // curl localhost:8080/customers
    @GetMapping("/story")
    public Map<String, String> send() {
        var prompt = "tell a story about the the delicious food in Belgium, and do it in the voice of a 19th century French chef";
        return Map.of("response", openAiChatClient.generate(prompt));
    }
}

interface CustomerRepository extends ListCrudRepository<Customer, Integer> {
    Collection<Customer> findByName(String name);
}

record Customer(@Id Integer id, String name) {
}
