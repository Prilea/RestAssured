package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class GetListUsers {

    @Test
    public void getListUsers() {
        Response response = RestAssured
                .get("https://reqres.in/api/users?page=2")
                .andReturn();
        response.prettyPrint();
    }
}
