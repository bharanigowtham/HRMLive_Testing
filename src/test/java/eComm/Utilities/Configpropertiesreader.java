package eComm.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class Configpropertiesreader {

	private Properties prop;
	
	public  Properties init_properties() throws Exception {
		
		prop = new Properties();
		
		try {
			FileInputStream fis = new FileInputStream(".//config.properties");
			prop.load(fis);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
