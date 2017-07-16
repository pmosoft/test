package test.jdbc.spring.oracle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	
    public static void main(String[] args) {
    	
		String configLocation = "classpath:springJdbcOracle.xml"; // src/main/resources/springJdbcOracle.xml
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		TestJdbcSpringOracle testJdbcSpringOracle = ctx.getBean("TestJdbcSpringOracle",TestJdbcSpringOracle.class);
		
		/* select */		
		//testJdbcSpringOracle.selectEmpSingle();
		//testJdbcSpringOracle.selectEmpMulti();
		//testJdbcSpringOracle.selectEmpMulti2();

		/* update */
		testJdbcSpringOracle.updateEmp("SMITH000",7369);
		testJdbcSpringOracle.selectEmpSingle();
		testJdbcSpringOracle.updateEmp("SMITH0",7369);
		testJdbcSpringOracle.selectEmpSingle();
		
		/* create */		
		//testJdbcSpringOracle.createTable();
		
		ctx.close();
    }
}
