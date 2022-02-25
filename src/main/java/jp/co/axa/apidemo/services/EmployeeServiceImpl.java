package jp.co.axa.apidemo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> retrieveEmployees() {
		List<Employee> employees = employeeRepository.findAllByOrderById();
		return employees;
	}

	@Override
	public Optional<Employee> getEmployee(Long employeeId) {
		return employeeRepository.findById(employeeId);
	}

	@Override
	public void saveEmployee(Employee employee) {
		// 自動採番にする
		employee.setId(null);
		employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void deleteAllEmployees() {
		employeeRepository.deleteAll();
	}
}