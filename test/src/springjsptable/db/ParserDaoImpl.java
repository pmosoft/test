package springjsptable.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


public class ParserDaoImpl implements ParserDao {

	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}	
	
//    public ArrayList<EmpDto> selectEmp(){
//    	ParseDao dao = sqlSession.getMapper(ParseDao.class);
//    	ArrayList<EmpDto> empList = dao.selectEmp();
//
//        for(int i=0;i<empList.size();i++){
//            System.out.println("empno="+empList.get(i).empno+"   "+"ename="+empList.get(i).ename );
//        }    	
//    	
//    	return empList;
//    }


	@Override
	public void deleteParseResult(String kind) {
		sqlSession.delete("deleteParseResult",kind);
	}

	@Override
	public void insertParseResult(List<HashMap<String, Object>> parseResultList) {
		sqlSession.insert("insertParseResult",parseResultList);
	}
	
	@Override
	public void insertElinateJspReduction() {
		sqlSession.insert("insertElinateJspReduction");
		//ParserDao dao = sqlSession.getMapper(ParserDao.class);
		//dao.insertElinateJspReduction(oldKind, newKind);
	}
	
	@Override
	public void insertControllerSemanticAnalysis(){
		sqlSession.insert("insertControllerSemanticAnalysis");
	}
	
	@Override
	public void insertServiceSemanticAnalysis(){
		sqlSession.insert("insertServiceSemanticAnalysis");
	}

	@Override
	public void insertSqlMapSemanticAnalysis(){
		sqlSession.insert("insertServiceSemanticAnalysis");
	}
		
	@Override
	public void insertAsafeSqlMapTableInfo01(){
		sqlSession.insert("insertAsafeSqlMapTableInfo01");
	}
		
	@Override
	public void insertAsafeSqlMapTableInfo02(){
		sqlSession.insert("insertAsafeSqlMapTableInfo02");
	}
	

	public void selectAsafeSqlMapTableInfo03(){
		//sqlSession.select("selectAsafeSqlMapTableInfo03");
	}	
	
	@Override
	public void insertWebSrcFileInfo(List<HashMap<String, String>> fileInfoList) {
	}

	@Override
	public void insertFileTraceInfo(
			List<HashMap<String, String>> fileTraceInfoList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTest() {
		// TODO Auto-generated method stub
		
	}


}
