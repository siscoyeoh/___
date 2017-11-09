package yl.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class CellTypeTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file = new File("C:/ttt.xls");
		HSSFWorkbook book = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = book.getSheet("Sheet1");
		HSSFRow row = sheet.getRow(3);
		HSSFCell cell = row.getCell(3);
		System.out.println(cell.getCellTypeEnum());
		book.close();
	}
	
	

}
