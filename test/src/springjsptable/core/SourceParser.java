package springjsptable.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SourceParser {
	
	public List getSrcInfoList();
	public void setSrcInfoList(List<HashMap<String, String>> srcInfoList);

	public void process();
	public String elimateComment(String src, String kind);
	public void parseBasic(String src);

	public void insertParseResult();
	public void insertSemanticAnalysis();
	
	public List getParseResultList();
	public void setParseResultList(List<HashMap<String, Object>> parseResultList);
}	
