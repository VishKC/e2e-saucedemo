package src.test.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class MyUtilities {
	
	private WebDriver driver;

	FileInputStream fis;
	XSSFWorkbook dataWorkBook;
	List<String> productsToCart;
	int NoOfRows;

	public MyUtilities(WebDriver driver) {
		this.driver = driver;
	}
	
	public void openWorkBook() throws IOException {
		String fileName = "data.xlsx";
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\" + fileName;
//		System.out.println("filePath: "+filePath);
		fis = new FileInputStream(filePath);
		dataWorkBook = new XSSFWorkbook(fis);
	}
	
	public void closeWorkBook() throws IOException {
		if(fis != null) {
			fis.close();
		}
		if(dataWorkBook != null) {
			dataWorkBook.close();
		}
	}
	public List<String> readProductsdata() throws IOException {

		int minimum = 1;
		openWorkBook();
		List<String> productsToCart = new ArrayList<>();
		
		XSSFSheet productsNameSheet = dataWorkBook.getSheet("ProductsName");
		DataFormatter formatter = new DataFormatter();

		NoOfRows = productsNameSheet.getLastRowNum();
		int nums[] = getRandomThree(minimum, NoOfRows);
		
		for(int num:nums) {
			XSSFRow row = productsNameSheet.getRow(num);
			if(row == null)
				continue;
			
			String productName = formatter.formatCellValue(row.getCell(0));
			productsToCart.add(productName);
		}
		closeWorkBook();
		return productsToCart;
	}

	private int[] getRandomThree(int min, int max) {
		return new Random()
	            .ints(min, max + 1) // Generate a stream of random numbers in range
	            .distinct()         // Remove duplicates
	            .limit(3)           // Stop when we hit 3 elements
	            .toArray();  
	}

	public Map<String, String> getUsernameAndPassword(String SheetName) throws IOException {
	    Map<String, String> loginsMap = new HashMap<>();
		openWorkBook();
		XSSFSheet loginDataSheet = dataWorkBook.getSheet(SheetName);
		DataFormatter formatter = new DataFormatter();

        XSSFRow headerRow = loginDataSheet.getRow(0);
        NoOfRows = loginDataSheet.getLastRowNum();
		for(int num = 1; num <= NoOfRows;num++) {
			XSSFRow row = loginDataSheet.getRow(num);
			if(row == null)
				continue;
			XSSFCell cell0 = row.getCell(0);
	        XSSFCell cell1 = row.getCell(1);
	        
	        if (cell0 != null && cell1 != null) {
	            // Using the formatter correctly converts cell data to String safely
	            String username = formatter.formatCellValue(cell0);
	            String password = formatter.formatCellValue(cell1);
	            loginsMap.put(username, password);
	        }
		}
		//System.out.println("loginsMap: "+loginsMap);
		closeWorkBook();
		return loginsMap;
	}

}
