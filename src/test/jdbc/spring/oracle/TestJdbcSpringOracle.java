package test.jdbc.spring.oracle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

class TestJdbcSpringOracle {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}	

    public void selectEmpSingle(){
        Map map1 =  template.queryForMap("SELECT empno,ename FROM emp where rownum <= 1");
        System.out.println("empno="+map1.get("empno")+"  "+"ename="+map1.get("ename"));
    }    
	
    public void selectEmpMulti(){
        List<EmpDto> empList = 
        template.query("SELECT empno,ename FROM emp", 
            new RowMapper<EmpDto>(){
                public EmpDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                    EmpDto edto = new EmpDto();
                    edto.setEmpno(rs.getString("empno"));
                    edto.setEname(rs.getString("ename"));
                    return edto;
                }
            });

        for(int i=0;i<empList.size();i++){
            System.out.println("empno="+empList.get(i).empno+"   "+"ename="+empList.get(i).ename );
        }
    }
    
    // error
    public void selectEmpMulti2(){
        List<EmpDto> empList = 
        template.query("SELECT empno,ename FROM emp", new BeanPropertyRowMapper<EmpDto>(EmpDto.class));

        for(int i=0;i<empList.size();i++){
            System.out.println("empno="+empList.get(i).empno+"   "+"ename="+empList.get(i).ename );
        }
    }

    public void updateEmp(final String ename,final int empno){
		String query = "update emp set ename = ? where empno = ?";
		this.template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, ename);
				ps.setInt(2, empno);
				
			}
		});
	}

    public void deleteEmp(final int empno){
		String query = "delete from emp where empno = ?";
		this.template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, empno);
			}
		});
	}
    
    
    public void createTable(){
        template.execute("create table mytable (id integer, name varchar(100))");
    }


}
