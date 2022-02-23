package jp.co.axa.apidemo.services;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.axa.apidemo.entities.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@Test
	public void testRetrieveEmployees() {
		List<Employee> employees = employeeService.retrieveEmployees();
	}

	@Test
	public void testGetEmployee() {
		Employee employee = employeeService.getEmployee(1L);
	}

	@Test
	public void testSaveEmployee() {
		Employee newEmployee = new Employee();
		employeeService.saveEmployee(newEmployee);
	}

	@Test
	public void testDeleteEmployee() {
		employeeService.deleteEmployee(1L);
	}

	@Test
	public void testUpdateEmployee() {
		Employee employee = new Employee();
		employeeService.updateEmployee(employee);
	}

}
