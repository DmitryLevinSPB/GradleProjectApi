import Specifications.Specification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class TestNoPojoFile  {
private String url = "https://reqres.in/";
    
@Test
@DisplayName("Тест без Pojo")
public  void firstTestNoPojo() {
       Specification.installSpecification(Specification.requestSpec(url), Specification.responseSpecOk200());
Response response = given()
        .when()
        .get("/api/users?page=2")
        .then().log().all()
        .body("total", equalTo (12))
        .body("data.email", notNullValue())
        .body("data.id", notNullValue())
        .body("data.email", notNullValue())
        .body("data.last_name", notNullValue())
        .body("data.first_name", notNullValue())
        .body("", notNullValue())

        .extract().response();
   JsonPath JsonPath = response.jsonPath();
   List<String> email = JsonPath.get("data.email");
   List<Integer> id = JsonPath.get("data.id");
   List<Integer> avatar = JsonPath.get("data.avatar");

for(int i=0; i<avatar.size();i++ ) {
   //Assert.assertTrue(avatar.get(i).contains(id.get(i).toString()));
}
Assert.assertTrue(email.stream().allMatch(x-> x.endsWith("@reqres.in")));
    }


}
