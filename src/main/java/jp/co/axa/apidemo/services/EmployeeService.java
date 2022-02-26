package jp.co.axa.apidemo.services;

import java.util.List;
import java.util.Optional;

import jp.co.axa.apidemo.entities.Employee;

public interface EmployeeService {

    public List<Employee> retrieveEmployees();

    public Optional<Employee> getEmployee(Long employeeId);

    public void saveEmployee(Employee employee);

    /**
     * 更新
     * 
     * @param employeeId 更新対象のid
     * @param employee id以外の更新値
     * @return 更新対象がある場合、値あり。更新対象がない場合、null
     */
    public Optional<Employee> updateEmployee(Long employeeId, Employee employee);

    /**
     * 削除
     * @param employeeId
     * @return 削除成功：true、削除対象なし：false
     */
    public boolean deleteEmployee(Long employeeId);

    /**
     * すべての従業員を削除する
     */
    public void deleteAllEmployees();
}