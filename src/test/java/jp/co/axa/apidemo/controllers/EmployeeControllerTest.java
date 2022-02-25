package jp.co.axa.apidemo.controllers;

import static org.junit.Assert.*;
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
		// 使われていないIDを探す
		Long id = 1L;

		do {
			if (!id.equals(testEmployee1.getId()) && id.equals(testEmployee2.getId())
					&& id.equals(testEmployee3.getId())) {
				break;
			}
			id++;
		} while (id <= 10);

		// テスト
		String url = "/api/v1/employees/" + id;

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
