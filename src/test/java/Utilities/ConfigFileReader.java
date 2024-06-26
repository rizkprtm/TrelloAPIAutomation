package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	private Properties prop;

    public ConfigFileReader() {
        String propertyFilePath = "src/test/resources/PropertyFile.properties";
        try (FileInputStream fis = new FileInputStream(propertyFilePath)) {
            prop = new Properties();
            prop.load(fis);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
	}

	public String getURL() {
        return prop.getProperty("URL");
	}

	public String getEmailID() {
        return prop.getProperty("EMAIL");
	}

	public String getPassword() {
        return prop.getProperty("PASSWORD");
	}

	public String getBoardBaseURL() {
        return prop.getProperty("BOARD_BASE_URL");
	}

	public String getCreateListURL() {
        return prop.getProperty("CREATE_LIST_URL");
	}

	public String getCreateCardURL() {
        return prop.getProperty("CREATE_CARD_URL");
	}

	public String getAPIKey() {
        return prop.getProperty("API_KEY");
	}

	public String getToken() {
        return prop.getProperty("TOKEN");
	}
	
	public String getBrowser() {
        return prop.getProperty("BROWSER");
	}

}
