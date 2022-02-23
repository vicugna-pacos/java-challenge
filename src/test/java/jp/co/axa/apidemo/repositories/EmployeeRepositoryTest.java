package jp.co.axa.apidemo.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class EmployeeRepositoryTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * 全件検索(id昇順)が実行できるかのテスト
	 */
	@Test
	public void testFindAllByOrderById() {
		employeeRepository.findAllByOrderById();
	}

}
