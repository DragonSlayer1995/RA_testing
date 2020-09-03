import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

public class Hook {
    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/";
    }
}
