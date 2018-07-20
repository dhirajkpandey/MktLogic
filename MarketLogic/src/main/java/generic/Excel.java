package generic;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class Excel {
	

	/**
	 * Read Data from Excel
	 * 
	 * @author Dhiraj.Pandey 
	 */
	
	public static String fnGetData(String afilePath,String aSheetName, int aRowNo, int aCellNo) 
	{
		String data="";
		try
		{
			FileInputStream fis = new FileInputStream(afilePath);
			Workbook wb = WorkbookFactory.create(fis);
			data = wb.getSheet(aSheetName).getRow(aRowNo).getCell(aCellNo).getStringCellValue();	
			
		} 
		catch (Exception e) 
		{
			
		}
		return data;		
	}
	
	
}
