package jp.co.axa.apidemo.services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
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
	
	@Before
	public void before() {
		employeeService.deleteAllEmployees();
	}

	/**
	 * 登録が成功するかどうかのテスト
	 */
	@Test
	public void testSaveEmployee() {
		Employee newEmployee = createTestEmployee1();

		employeeService.saveEmployee(newEmployee);
		
		assertNotNull(newEmployee.getId());
	}

	/**
	 * 検索のテスト
	 */
	@Test
	public void testGetEmployee() {
		// テストデータ登録
		Employee newEmployee = createTestEmployee1();
		employeeService.saveEmployee(newEmployee);
		
		// 検索
		Employee employee = employeeService.getEmployee(newEmployee.getId());
		
		assertNotSame("トランザクション外なのでインスタンスが別になるはず", newEmployee, employee);
		assertEquals("登録した通りに検索できる", newEmployee, employee);
	}

//	@Test
//	public void testGetEmployeeEmpty() {
//		Employee employee = employeeService.getEmployee(1L);
//	}

	/**
	 * 全件検索のテスト
	 */
	@Test
	public void testRetrieveEmployees() {
		// テストデータ登録
		Employee newEmployee1 = createTestEmployee1();
		employeeService.saveEmployee(newEmployee1);

		Employee newEmployee2 = createTestEmployee2();
		employeeService.saveEmployee(newEmployee2);

		List<Employee> employees = employeeService.retrieveEmployees();
		
		assertEquals("件数のチェック", 2, employees.size());
		
		assertEquals("1件目", newEmployee1, employees.get(0));
		assertEquals("2件目", newEmployee2, employees.get(1));

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

	/**
	 * テスト用Employeeの作成1
	 * 
	 * @return
	 */
	private Employee createTestEmployee1() {
		Employee newEmployee = new Employee();
		newEmployee.setName("test1");
		newEmployee.setDepartment("dep1");
		newEmployee.setSalary(100);
		return newEmployee;
	}

	/**
	 * テスト用Employeeの作成2
	 * 
	 * @return
	 */
	private Employee createTestEmployee2() {
		Employee newEmployee = new Employee();
		newEmployee.setName("test2");
		newEmployee.setDepartment("dep2");
		newEmployee.setSalary(200);
		return newEmployee;
	}
}
