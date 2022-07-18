import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderPOSTTest {

    @Test
    public void jsonplaceholderCreateNewUser(){

        String jsonBody = "{\n" +
                "    \"name\": \"Bartek Testowy\",\n" +
                "    \"username\": \"Bartek\",\n" +
                "    \"email\": \"bartek@akademiaqa.pl\",\n" +
                "    \"address\": {\n" +
                "        \"street\": \"Ul. Sezamkowa\",\n" +
                "        \"suite\": \"8\",\n" +
                "        \"city\": \"Sezamkowo\",\n" +
                "        \"zipcode\": \"10-100\",\n" +
                "        \"geo\": {\n" +
                "            \"lat\": \"-37.3159\",\n" +
                "            \"lng\": \"81.1496\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"phone\": \"1-770-736-8031 x56442\",\n" +
                "    \"website\": \"hildegard.org\",\n" +
                "    \"company\": {\n" +
                "        \"name\": \"Firma Sezamkowa\",\n" +
                "        \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                "        \"bs\": \"harness real-time e-markets\"\n" +
                "    }\n" +
                "}";

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(201)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals("Bartek Testowy", json.get("name"));
        assertEquals("Bartek", json.get("username"));
        assertEquals("bartek@akademiaqa.pl", json.get("email"));


    }
}
