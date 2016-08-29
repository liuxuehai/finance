

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import hello.Customer;
import hello.CustomerRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class CustomerRepositoryTest {

	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	public void testFindByFirstName() { 
		hello.Customer s=customerRepository.findByFirstName("");
		System.out.println(s);
		s=customerRepository.findByFirstName("234");
		System.out.println(s);
	}

	@Test
	public void testFindByLastName() {
		hello.Customer s=(Customer) customerRepository.findByLastName("");
		System.out.println(s);
		s=customerRepository.findByFirstName("32432");
		System.out.println(s);
	}

}
