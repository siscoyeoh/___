package yl.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.record.cf.FontFormatting;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

public class TestSuperSub {
	

	public static void createExcel(File file) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
			HSSFSheet sheet =  workbook.getSheet("Sheet1");
			HSSFRow row = sheet.getRow(1);
			
			
			//设置上标
			HSSFCell cell2 = row.getCell(2);
			cell2.setCellType(CellType.STRING);
			String cell2Str = "23";
			HSSFRichTextString hrSuper = new HSSFRichTextString(cell2Str);
			HSSFFont fontSuper = workbook.createFont();
			fontSuper.setTypeOffset(FontFormatting.SS_SUPER);
			hrSuper.applyFont(1, 2, fontSuper); //int startIndex, int endIndex, Font font
			cell2.setCellValue(hrSuper);
			//设置下标
			HSSFCell cell4 = row.getCell(4);
			cell4.setCellType(CellType.STRING);
			String cell4Str = "56";
			HSSFRichTextString hrSub = new HSSFRichTextString(cell4Str);
			HSSFFont fontSub = workbook.createFont();
			fontSub.setTypeOffset(FontFormatting.SS_SUB);
			hrSub.applyFont(1, 2, fontSub);
			cell4.setCellValue(hrSub);
			
			FileOutputStream out = new FileOutputStream(file);
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[]args) {
		File file = new File("d:/test.xls");
		createExcel(file);
	}
	

}
