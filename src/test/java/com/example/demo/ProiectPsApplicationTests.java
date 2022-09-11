package com.example.demo;/*package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProiectPsApplicationTests {

	@Test
	void contextLoads() {
	}

}*/


import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)

public class ProiectPsApplicationTests {

	/*@InjectMocks
	private StudentService studentService;
	@Mock
	private StudentRepo studentRepo;
	@Mock
	ModelMapper modelMapper;
	@Test
	public void save_givenStudent_expectTheStudent() throws ParseException {
		StudentDto studentDto = StudentDto.builder()
				.nume("Aron Adela")
				.cnp("8764985316489")
				.email("adela@yahoo.com")
				.anStudiu("2")
				.build();

		Student student = Student.builder()
				.nume("Aron Adela")
				.cnp("8764985316489")
				.email("adela@yahoo.com")
				.anStudiu("2")
				.build();

		when(studentRepo.save(any(Student.class))).thenReturn(student);

		StudentDto result = studentService.addStudent(studentDto);
		studentDto.setId(result.getId());
		verify(studentRepo).save(any(Student.class));
		assertEquals(studentDto, result);
	}




	@Test
	public void update_givenUidAndUpdatedValues_expectTheUpdatedEmployee() throws ParseException {
		CustomerDto customerDto = CustomerDto.builder()
				.email("adsa@yahoo.com")
				.name("aaa")
				.ron(22)
				.phone("0755476116")
				.build();

		Customer customer = Customer.builder()
				.uid("22")
				.email("adsa@yahoo.com")
				.name("aaa")
				.ron(22)
				.phone("0755476116")
				.build();


		when(customerRepo.findCustomerByUid("22")).thenReturn(Optional.of(customer));
		when(customerRepo.save(any(Customer.class))).thenReturn(customer);

		customerDto.setUid("22");
		CustomerDto result = customerService.update(customerDto, "22");

		verify(customerRepo).save(any(Customer.class));

		assertEquals(customerDto, result);
	}

	@Test
	public void findByUid_givenUid_expectTheEmployee() throws ParseException {

		Customer customer = Customer.builder()
				.uid("22")
				.email("adsa@yahoo.com")
				.name("aaa")
				.ron(22)
				.phone("0755476116")
				.build();

		when(customerRepo.findCustomerByUid(anyString())).thenReturn(Optional.of(customer));

		CustomerDto fetchedEmployee = customerService.findByUid(customer.getUid());

		verify(customerRepo).findCustomerByUid("22");

		assertEquals(fetchedEmployee.getName(), customer.getName());
		assertEquals(fetchedEmployee.getEmail(), customer.getEmail());
		assertEquals((int)(fetchedEmployee.getRon()), (int)(customer.getRon()));
		assertEquals(fetchedEmployee.getPhone(), customer.getPhone());
*/
	}