package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException{
		String path =System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\userdata.xlsx";
		ExcelUtility xl=new ExcelUtility(path);
		
		int rownum =xl.getRowCount(path,"Sheet1");
		int colcount = xl.getCellCount(path, "Sheet1", 1);
		
		String apidata[][] = new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++) {
			
			for(int j=0;j<colcount;j++) {
				
				apidata[i-1][j] =xl.getCellData(path, "Sheet1", i, j);
				
			}
		}
		
		return apidata;
	}
	
	//data provider to get only usernames data
	@DataProvider(name = "userNames")
	public String[] getUserNames() throws IOException {
		
		String path =System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\userdata.xlsx";
		ExcelUtility xl=new ExcelUtility(path);
		
		int rownum=xl.getRowCount(path, "Sheet1");
		
		String apidata[] =new String[rownum];
		
		for(int i=1;i<=rownum;i++) {
			
			apidata[i-1] =xl.getCellData(path, "Sheet1", i, 1);
		}
		
		return apidata;
	}
	

}
