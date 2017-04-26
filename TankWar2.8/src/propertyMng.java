import java.io.IOException;
import java.util.Properties;

public class propertyMng {

	static Properties prop = new Properties();
	
	static {
		try {
			prop.load(propertyMng.class.getClassLoader().getResourceAsStream("config/tank.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private propertyMng() {}
	
	public static String getProperty(String key){
		return prop.getProperty(key);
	}
}
