package jp.co.axa.apidemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.axa.apidemo.entities.Employee;

/**
 * 従業員情報の Repository
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
	
	/**
	 * 全件検索(ID昇順)
	 * 
	 * @return 全件検索結果(ID昇順)
	 */
	public List<Employee> findAllByOrderById();	
}
