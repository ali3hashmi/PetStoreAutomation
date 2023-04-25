package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook workBook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public CellStyle style;
	String filePath = null;

	ExcelUtility(String filePath){

		this.filePath=filePath;
	}

	public static int getRowCount(String xlfile,String xlsheet) throws IOException {

		fis = new FileInputStream(xlfile);
		workBook = new XSSFWorkbook(fis);
		sheet = workBook.getSheet(xlsheet);
		int rowCount = sheet.getLastRowNum();
		workBook.close();
		fis.close();
		return rowCount;
	}

	public static int getCellCount(String xlfile,String xlsheet,int rowNum) throws IOException {


		fis = new FileInputStream(xlfile);
		workBook = new XSSFWorkbook(fis);
		sheet = workBook.getSheet(xlsheet);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workBook.close();
		fis.close();
		return cellCount;
	}

	public static String getCellData(String xlfile,String xlsheet,int rowNum,int cellNum) throws IOException {

		fis = new FileInputStream(xlfile);
		workBook = new XSSFWorkbook(fis);
		sheet = workBook.getSheet(xlsheet);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);

		DataFormatter formatter =new DataFormatter();
		String data;
		try {

			data = formatter.formatCellValue(cell);
			//returns the formatted value of a cell as a string regardless of the cell type
		}
		catch (Exception e) {
			data =" ";
			// TODO: handle exception
		}
		workBook.close();
		fis.close();
		return data;
	}

	public void setCellData(String xlfile,String xlsheet,int rowNum,int cellNum,String data) throws IOException{

		File excelFile = new File(xlfile);
		if(!excelFile.exists()) { //if file not exist then create a new file

			workBook = new XSSFWorkbook();
			fos = new FileOutputStream(xlfile);
			workBook.write(fos);

		}

		fis = new FileInputStream(xlfile);
		workBook = new XSSFWorkbook(fis);

		if(workBook.getSheetIndex(xlsheet)==-1) { // if sheet not exist then create new sheet

			workBook.createSheet(xlsheet);
		}
		sheet = workBook.getSheet(xlsheet);

		if(sheet.getRow(rowNum)==null) { // if row not exist then create new row

			sheet.createRow(rowNum);
		}
		row = sheet.getRow(rowNum);

		cell = row.createCell(cellNum);
		cell.setCellValue(data);

		fos = new FileOutputStream(xlfile);
		workBook.write(fos);
		workBook.close();
		fis.close();
		fos.close();
	}

	public void fillGreenColor(String sheetName,int rowNum,int cellNum) throws IOException{

		fis = new FileInputStream(filePath);
		workBook = new XSSFWorkbook(fis);
		sheet = workBook.getSheet(sheetName);

		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);

		style = workBook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workBook.write(fos);
		workBook.close();
		fis.close();
		fos.close();
	}

	public void fillRedColor(String sheetName,int rowNum,int cellNum) throws IOException{

		fis = new FileInputStream(filePath);
		workBook = new XSSFWorkbook(fis);
		sheet = workBook.getSheet(sheetName);

		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);

		style = workBook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workBook.write(fos);
		workBook.close();
		fis.close();
		fos.close();
	}
}
