package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface CustomerRepository extends MongoRepository<Customer, String> {

	 public Customer findByFirstName(String firstName);
	 public List<Customer> findByLastName(String lastName);
}
