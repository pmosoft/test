package test.jdbc.spring.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmpRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		EmpDto empDto = new EmpDto();
		empDto.setEmpno(rs.getString("empno"));
		empDto.setEname(rs.getString("ename"));
		return empDto; 
	}
}
