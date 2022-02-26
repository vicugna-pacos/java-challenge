package jp.co.axa.apidemo.services;

import java.util.List;
import java.util.Optional;

import jp.co.axa.apidemo.entities.Employee;

/**
 * 従業員情報を操作する Service
 */
public interface EmployeeService {

	/**
	 * 従業員情報の一覧を取得。
	 * 
	 * @return 従業員情報の一覧
	 */
    public List<Employee> retrieveEmployees();

    /**
     * 指定したIDの従業員情報を取得。
     * 
     * @param employeeId 従業員ID
     * @return 従業員情報
     */
    public Optional<Employee> getEmployee(Long employeeId);

    /**
     * 新しい従業員情報を登録。
     * 
     * @param employee 登録する従業員情報 (idは指定しない)
     */
    public void saveEmployee(Employee employee);

    /**
     * 指定したIDの従業員を更新。
     * 
     * @param employeeId 更新対象の従業員ID。
     * @param employee id以外の更新値
     * @return 更新対象がある場合、値あり。更新対象がない場合、null
     */
    public Optional<Employee> updateEmployee(Long employeeId, Employee employee);

    /**
     * 指定したIDの従業員を削除。
     * 
     * @param employeeId 従業員ID
     * @return 削除成功：true、削除対象なし：false
     */
    public boolean deleteEmployee(Long employeeId);

    /**
     * すべての従業員情報を削除する
     */
    public void deleteAllEmployees();
}