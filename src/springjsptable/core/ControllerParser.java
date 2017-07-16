package springjsptable.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springjsptable.Constant;

public class ControllerParser extends CommonParser {

	public static void main(String[] args) {

		// -----------------------------------------------------------
		// 소스 정보를 파일과 폴더에서 읽어와 리스트에 저장
		// -----------------------------------------------------------
		List<HashMap<String, String>> srcInfoList = new ArrayList<HashMap<String, String>>();
		SourceFileInfo trace = new SourceFileInfo();
		srcInfoList = trace.readSrcList(Constant.srcPath);

		// -----------------------------------------------------------
		// controller 내에 서비스를 호출하는 정보를 추출하여 리스트에 저장
		// -----------------------------------------------------------
		SourceParser controllerParser = new ControllerParser();
		controllerParser.setSrcInfoList(srcInfoList);
		controllerParser.process();
		
		// DB에 저장
		controllerParser.insertParseResult();
		controllerParser.insertSemanticAnalysis();
	}

	public ControllerParser() {
		System.out.println("Start controllerParser");
	}

	String fileName = "";
	String filePathName = "";
	String src = "";
	String src2 = "";
	String pos = "";
	String parseResult = "";
	
	
	@Override
	public void process() {
		System.out.println("srcInfoList.size=" + srcInfoList.size());
		
		for (int i = 0; i < srcInfoList.size(); i++) {
		//for (int i = 0; i < 247; i++) {

			fileName = srcInfoList.get(i).get("fileName");
			filePathName = srcInfoList.get(i).get("filePathName");
			
			if (fileName.matches("(?i).*Controller.java")) {
				System.out.println(i+"="+fileName);
				src = srcInfoList.get(i).get("src");
				src = elimateComment(src,"java");
				parseBasic(src);
			}	
		}
	}

	public void parseBasic(String src) {

		Pattern p;
		Matcher m;
		String parseRule;
		boolean a;
		
		HashMap<String, String> map = new HashMap<String, String>();
		//this.fileParseResultList.add(map);


		parseRule = ".*@Resource.*\\)";
		p = Pattern.compile(parseRule); m = p.matcher(src); a = false;
		while (a = m.find()) { 
			pos = String.valueOf(m.start()); parseResult = m.group();
			setParseResultList();
		}		
		
		parseRule = "(?i).*@RequestMapping.*";
		p = Pattern.compile(parseRule); m = p.matcher(src); a = false;
		while (a = m.find()) { 
			pos = String.valueOf(m.start()); parseResult = m.group();
			setParseResultList();
		} 

		parseRule = "(?i).*public.*";
		p = Pattern.compile(parseRule);	m = p.matcher(src); a = false;
		while (a = m.find()) { 
			pos = String.valueOf(m.start()); parseResult = m.group();
			setParseResultList();
		}

		parseRule = "(?i).*service.*";
		p = Pattern.compile(parseRule);	m = p.matcher(src); a = false;
		while (a = m.find()) {
			pos = String.valueOf(m.start()); parseResult = m.group();
			setParseResultList();
		}
		
	}

	public void setParseResultList() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fileName"    , fileName);
		map.put("filePathName", filePathName);
		map.put("kind", "CTRL1");
		map.put("pos", pos );
		map.put("parseResult", parseResult.replace('"', '#'));
		
		System.out.println("parseResult="+parseResult);
		
		this.parseResultList.add(map);
	}
	
	public void insertParseResult() {
		dao.deleteParseResult("CTRL1");
		dao.insertParseResult(parseResultList);
	}

	public void insertSemanticAnalysis() {
		dao.deleteParseResult("CTRL2");
		dao.insertControllerSemanticAnalysis();
	}

}
