package jp.co.axa.apidemo.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;

/**
 * 従業員情報を操作するAPIのアクセスポイント。
 */
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	private EmployeeService employeeService;
	
	private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	/**
	 * コンストラクタ
	 * 
	 * @param employeeService
	 */
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * 従業員情報の一覧を取得。
	 * 
	 * @return 従業員情報の一覧
	 */
	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		List<Employee> employees = employeeService.retrieveEmployees();
		return employees;
	}

	/**
	 * 指定したIDの従業員情報を取得。
	 * 
	 * @param employeeId 従業員ID
	 * @return 従業員情報
	 */
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		Optional<Employee> employee = employeeService.getEmployee(employeeId);
		if (employee.isPresent()) {
			logger.debug("Employee Got Successfully");
			return employee.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	/**
	 * 新しい従業員情報を登録。
	 * 
	 * @param employee 登録する従業員情報 (idは指定しない)
	 */
	@PostMapping("/employees")
	public void saveEmployee(Employee employee) {
		employeeService.saveEmployee(employee);
		logger.debug("Employee Saved Successfully");
	}

	/**
	 * 指定したIDの従業員情報を削除。
	 * 
	 * 削除対象の従業員IDがない場合でも、無視して 200 (Ok) を返す。
	 * 
	 * @param employeeId 従業員ID
	 */
	@DeleteMapping("/employees/{employeeId}")
	public void deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		employeeService.deleteEmployee(employeeId);
		logger.debug("Employee Deleted Successfully");
	}

	/**
	 * 指定したIDの従業員情報を更新。
	 * 
	 * 更新対象の従業員IDが無い場合は、404 (Not found) を返す。
	 * 
	 * @param employee 更新したい従業員情報の値。
	 * @param employeeId 更新対象の従業員ID。
	 */
	@PutMapping("/employees/{employeeId}")
	public void updateEmployee(@RequestBody Employee employee, @PathVariable(name = "employeeId") Long employeeId) {
		Optional<Employee> emp = employeeService.updateEmployee(employeeId, employee);
		if (!emp.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		logger.debug("Employee Updated Successfully");
	}

}
