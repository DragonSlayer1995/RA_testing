import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestTest extends Hook {

    @Test
    public void GET() {

        String orderId = "2";

        Response response = given()
                .when()
                .get("store/order/{id}", orderId)
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        response.body().prettyPrint();
    }

    @Test
    public void POST() {

        Map<String, Object> requestBody = new HashMap<String, Object>(){{
            put("id", "0");
            put("username", "Alex");
            put("firstName", "Alex");
            put("lastName", "Strogov");
            put("email", "string");
            put("password", "string");
            put("phone", "string");
            put("userStatus", "0");
        }};

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(requestBody)
                .post("/user")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        response.body().prettyPrint();
    }

    @Test
    public void DELETE() {

        String petId = "15435006003461";

        given()
                .when()
                .delete("/pet/{id}", petId)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void PUT() {

        String json = "{\n" +
                "  \"id\": 15435006003590,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"PAVEL\"\n" +
                "  },\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(json)
                .put("/pet")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void postUserArray() {
        Map<String, Object> requestBody = new HashMap<String, Object>(){{
            put("id", "0");
            put("username", "Pavel");
            put("firstName", "Pavel");
            put("lastName", "Pavlov");
            put("email", "string");
            put("password", "string");
            put("phone", "string");
            put("userStatus", "0");
        }};

        Map<String, Object> requestBody2 = new HashMap<String, Object>(){{
            put("id", "0");
            put("username", "Ivan");
            put("firstName", "Ivan");
            put("lastName", "Ivanov");
            put("email", "string");
            put("password", "string");
            put("phone", "string");
            put("userStatus", "0");
        }};

        List<Map<String, Object>> jsonBody = new ArrayList<Map<String, Object>>();
        jsonBody.add(requestBody);
        jsonBody.add(requestBody2);

        Response response = given()
                .baseUri("https://petstore.swagger.io/v2/")
                .contentType(ContentType.JSON)
                .when()
                .body(jsonBody)
                .post("user/createWithArray")
                .then()
                .extract()
                .response();

        Integer code = response.path("code");
        String type = response.path("type");
        System.out.println(code);
        System.out.println(type);
    }
}
