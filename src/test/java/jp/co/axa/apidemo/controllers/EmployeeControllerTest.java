package jp.co.axa.apidemo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;

/**
 * EmployeeControllerのテストクラス
 * 
 * @author あや
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private EmployeeService employeeService;

	private Employee testEmployee1;
	private Employee testEmployee2;
	private Employee testEmployee3;

	@Before
	public void before() {
		employeeService.deleteAllEmployees();

		// テストデータ1
		testEmployee1 = new Employee();
		testEmployee1.setName("test1");
		testEmployee1.setDepartment("dep1");
		testEmployee1.setSalary(100);

		employeeService.saveEmployee(testEmployee1);

		// テストデータ2
		testEmployee2 = new Employee();
		testEmployee2.setName("test2");
		testEmployee2.setDepartment("dep2");
		testEmployee2.setSalary(200);

		employeeService.saveEmployee(testEmployee2);

		// テストデータ3
		testEmployee3 = new Employee();
		testEmployee3.setName("test3");
		testEmployee3.setDepartment("dep3");
		testEmployee3.setSalary(300);

		employeeService.saveEmployee(testEmployee3);
	}

	@Test
	public void testGetEmployee() throws Exception {
		String url = "/api/v1/employees/" + testEmployee1.getId();

		ObjectMapper mapper = new ObjectMapper();
		String expected = mapper.writeValueAsString(testEmployee1);

		mockMvc.perform(get(url)).andExpect(status().isOk()).andExpect(content().json(expected));
	}

	@Test
	public void testGetEmployeeNotFound() throws Exception {
		String url = "/api/v1/employees/" + Long.MAX_VALUE; // MAX_VALUEは使われていないはず

		mockMvc.perform(get(url)).andExpect(status().isNotFound());
	}

	@Test
	public void testGetEmployees() throws Exception {
		String url = "/api/v1/employees";

		List<Employee> expectedList = new ArrayList<Employee>();
		expectedList.add(testEmployee1);
		expectedList.add(testEmployee2);
		expectedList.add(testEmployee3);

		ObjectMapper mapper = new ObjectMapper();
		String expected = mapper.writeValueAsString(expectedList);

		mockMvc.perform(get(url)).andExpect(status().isOk()).andExpect(content().json(expected));
	}

	@Test
	public void testSaveEmployee() throws Exception {
		String url = "/api/v1/employees";

		mockMvc.perform(post(url).param("name", "test4").param("department", "dep4").param("salary", "400"))
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdateEmployee() throws Exception {
		String url = "/api/v1/employees/" + testEmployee1.getId();

		Employee newEmployee = new Employee();
		newEmployee.setName("test4");
		newEmployee.setDepartment("dep4");
		newEmployee.setSalary(400);

		ObjectMapper mapper = new ObjectMapper();
		String requestBody = mapper.writeValueAsString(newEmployee);

		mockMvc.perform(put(url).content(requestBody).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testUpdateEmployeeNotFound() throws Exception {
		Long employeeId = Long.MAX_VALUE; // MAX_VALUEは使われていないはず
		String url = "/api/v1/employees/" + employeeId;

		Employee newEmployee = new Employee();
		newEmployee.setName("test4");
		newEmployee.setDepartment("dep4");
		newEmployee.setSalary(400);

		ObjectMapper mapper = new ObjectMapper();
		String requestBody = mapper.writeValueAsString(newEmployee);

		mockMvc.perform(put(url).content(requestBody).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testDeleteEmployee() throws Exception {
		String url = "/api/v1/employees/" + testEmployee1.getId();

		mockMvc.perform(delete(url)).andExpect(status().isOk());
	}

	@Test
	public void testDeleteEmployeeNotFound() throws Exception {
		String url = "/api/v1/employees/" + Long.MAX_VALUE;

		mockMvc.perform(delete(url)).andExpect(status().isOk());
	}
}
