package jp.co.axa.apidemo.controllers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

	@Before
	public void before() {
		employeeService.deleteAllEmployees();

		testEmployee1 = new Employee();
		testEmployee1.setName("test1");
		testEmployee1.setDepartment("dep1");
		testEmployee1.setSalary(100);

		employeeService.saveEmployee(testEmployee1);
	}

	@Test
	public void testGetEmployee() throws Exception {
		String url = "/api/v1/employees/" + testEmployee1.getId();

		ObjectMapper mapper = new ObjectMapper();
		String expected = mapper.writeValueAsString(testEmployee1);

		mockMvc.perform(get(url)).andExpect(status().isOk()).andExpect(content().json(expected));
	}

	@Test
	public void testGetEmployees() throws Exception {
		String url = "/api/v1/employees";
		mockMvc.perform(get(url)).andExpect(status().isOk());
	}

	@Test
	public void testSaveEmployee() {
		fail("まだ実装されていません");
	}

	@Test
	public void testDeleteEmployee() {
		fail("まだ実装されていません");
	}

	@Test
	public void testUpdateEmployee() {
		fail("まだ実装されていません");
	}

}
