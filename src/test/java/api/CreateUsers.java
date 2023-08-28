package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasKey;

public class CreateUsers {

    @Test
    public void createUser() {
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\t\"name\": \"Din\",\n" +
                        "\t\"job\": \"plumber\"\n" +
                        "} ")
                .when()
                .post("https://reqres.in/api/users")
                .andReturn();
        response.prettyPrint();
    }


    @Test
    public void CheckKeys() {
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\t\"name\": \"Din\",\n" +
                        "\t\"job\": \"plumber\"\n" +
                        "} ")
                .when()
                .post("https://reqres.in/api/users")
                .andReturn();
        response.prettyPrint();
        String[] expectedKeys = {"name", "job", "id", "createdAt"};
        for (String expectedKey: expectedKeys) {
            response.then().assertThat().body("$", hasKey(expectedKey));
        }
    }

    @Test
    public void CheckStatusCode() {

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\t\"name\": \"Din\",\n" +
                        "\t\"job\": \"plumber\"\n" +
                        "} ")
                .when()
                .post("https://reqres.in/api/users");
                int statusCode = response.getStatusCode();
        Assertions.assertEquals(201, response.statusCode());
        response.prettyPrint();
    }

    @Test
    public void NegativeCreateUserWithoutBodyParam() {
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .when()
                .post("https://reqres.in/api/users")
                .andReturn();
        int statusCode = response.getStatusCode();
        Assertions.assertEquals(400, response.statusCode());
        if (statusCode == 400) {
            System.out.println("Введите значения");
        }
        response.prettyPrint();
    }

    @Test
    public void NegativeCreateUserWithOnlyParamJob() {
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\t\"job\": \"plumber\"\n" +
                        "} ")
                .when()
                .post("https://reqres.in/api/users")
                .andReturn();
        int statusCode = response.getStatusCode();
        Assertions.assertEquals(400, response.statusCode());
        if (statusCode == 400) {
            System.out.println("Введите имя");
        }
        response.prettyPrint();
    }

    //    @Test
//    public void createUser1() {
//        Map<String, String> body = new HashMap<>();
//        body.put("name","Din");
//        body.put("job","plumber");
//        Response response = RestAssured
//                .given()
//                .header("Content-Type", "application/json")
//                .body(body)
//                .when()
//                .post("https://reqres.in/api/users")
//                .andReturn();
//        response.prettyPrint();
//    }
}
