import com.qa.tests.vendorportal.model.VendorPortalTestData;
import com.qa.utils.JsonUtil;

public class TestFileData {

	public static void main(String[] args) {
	
		
		VendorPortalTestData v = JsonUtil.getTestData("./src/test/java/resources/test-data/vendor-portal/sam.json", VendorPortalTestData.class);
		
		System.out.println(v.annualEarning());

	}

}
