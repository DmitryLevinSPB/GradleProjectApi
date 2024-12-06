import Specifications.Specification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class TestNoPojoFile {
    private String url = "https://reqres.in/";

    @Test
    @DisplayName("Тест без Pojo")
    public void firstTestNoPojo() {
        Specification.installSpecification(Specification.requestSpec(url), Specification.responseSpecOk200());
        Response response = given()
                .when()
                .get("/api/users?page=2")
                .then().log().all()
                .body("total", equalTo(12))
                .body("data.email", notNullValue())
                .body("data.id", notNullValue())
                .body("data.avatar", notNullValue())
                .body("data.last_name", notNullValue())
                .body("data.first_name", notNullValue())

                .extract().response();
        JsonPath JsonPath = response.jsonPath();
        List<String> email = JsonPath.get("data.email");
        List<Integer> id = JsonPath.get("data.id");
        List<String> avatar = JsonPath.get("data.avatar");

        for (int i = 0; i < avatar.size(); i++) {
            Assert.assertTrue(avatar.get(i).contains(id.get(i).toString()));
        }
        Assert.assertEquals("id", equalTo(4));
        Assert.assertTrue(email.stream().allMatch(x -> x.endsWith("@reqres.in")));
    }

    @Test
    @DisplayName("Регистрация без Pojo")

    public void RegistrWithoutPojo() {
        Specification.installSpecification(Specification.requestSpec(url), Specification.responseSpecOk200());
        Map<String, String> user = new HashMap<>();
        user.put("email", "eve.holt@reqres.in");
        user.put("password", "pistol");
        given()
                .body(user)
                .when()
                .post("/api/register")
                .then().log().all()
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));

    }

    @Test
    @DisplayName("Регистрация без Pojo c responce")
    public void RegistrWithoutPojoAndWithResponce() {
        Specification.installSpecification(Specification.requestSpec(url), Specification.responseSpecOk200());
        Map<String, String> user = new HashMap<>();
        user.put("email", "eve.holt@reqres.in");
        user.put("password", "pistol");
        Response response = given()
                .body(user)
                .when()
                .post("/api/register")
                .then().log().all()
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"))
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("id");
String token = jsonPath.getString("token");

Assert.assertEquals("QpwL5tke4Pnpja7X4", token);
Assert.assertEquals(4, id);
    }
}