import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderPUTPATCHTest {

    private static Faker faker;
    private String fakeEmail;
    private String fakeName;
    private String fakeUserName;
    private String fakePhone;
    private String fakeWebsite;

    // Uruchomi się tylko raz przed wszystkimi testami
    @BeforeAll
    public static void beforeAll(){
        faker = new Faker();
    }

    // Uruchomi się przed każdym testem
    @BeforeEach
    public void beforeEach(){
        fakeEmail = faker.internet().emailAddress();
        fakeName = faker.name().name();
        fakeUserName = faker.name().username();
        fakePhone = faker.phoneNumber().phoneNumber();
        fakeWebsite = faker.internet().url();
    }

    @Test
    public void jsonplaceholderUpdateUserPUTTest(){

        JSONObject user = new JSONObject();
        user.put("name", fakeName);
        user.put("username", fakeUserName);
        user.put("email", fakeEmail);
        user.put("phone", fakePhone);
        user.put("website", fakeWebsite);

        JSONObject geo = new JSONObject();
        geo.put("lat", "-37.3159");
        geo.put("lng", "81.1496");

        JSONObject address = new JSONObject();
        address.put("street", "Ul. Sezamkowa");
        address.put("suite", "8");
        address.put("city", "Sezamkowo");
        address.put("zipcode", "10-100");
        address.put("geo", geo);

        user.put("address", address);

        JSONObject company = new JSONObject();
        company.put("name", "Firma Sezamkowa");
        company.put("catchPhrase", "Multi-layered client-server neural-net");
        company.put("bs", "harness real-time e-markets");

        user.put("company", company);

        System.out.println(user);

        Response response = given()
                .contentType("application/json")
                .body(user.toString())
                .when()
                .put("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(fakeName, json.get("name"));
        assertEquals(fakeUserName, json.get("username"));
        assertEquals(fakeEmail, json.get("email"));

    }

    @Test
    public void jsonplaceholderUpdateUserPATCHTest(){

        JSONObject userDetails = new JSONObject();
        userDetails.put("email", fakeEmail);


        Response response = given()
                .contentType("application/json")
                .body(userDetails.toString())
                .when()
                .patch("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(fakeEmail, json.get("email"));

    }

    }

