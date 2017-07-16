//package excel;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
// 
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// 
//public class PoiTestMain {
// 
//    public static void main(String[] args) {
//        
//        try {
//            // 엑셀파일
//            File file = new File("D:/test.xlsx");
// 
//            // 엑셀 파일 오픈
//            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
//            
//            Cell cell = null;
//            
//            // 첫번재 sheet 내용 읽기
//            for (Row row : wb.getSheetAt(0)) { 
//                // 셋째줄부터..
//                if (row.getRowNum() < 2) {
//                    continue;
//                }
//                
//                // 두번째 셀이 비어있으면 for문을 멈춘다.
//                if(row.getCell(1) == null){
//                    break;
//                }
//                
//                // 콘솔 출력
//                System.out.println("[row] 이름 : " + row.getCell(1) + ", 나이: " + row.getCell(2)
//                                + ", 성별: " + row.getCell(3) + ", 비고: " + row.getCell(4));
//                
//                // 4번째 셀 값을 변경
//                cell = row.createCell(4);
//                cell.setCellValue("확인");
//            }
//            
//            // 엑셀 파일 저장
//            FileOutputStream fileOut = new FileOutputStream(file);
//            wb.write(fileOut);
//        } catch (FileNotFoundException fe) {
//            System.out.println("FileNotFoundException >> " + fe.toString());
//        } catch (IOException ie) {
//            System.out.println("IOException >> " + ie.toString());
//        }
//    }
//}