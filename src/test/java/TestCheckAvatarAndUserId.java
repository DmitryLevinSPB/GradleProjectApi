import Pojo.DataItem;
import Specifications.Specification;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.codehaus.groovy.util.SingleKeyHashMap;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;


public class TestCheckAvatarAndUserId {
    private static String url = "https://reqres.in/";

    @Test
    @DisplayName("Первый тест апи")
    public void checkAvatarAndUserId() {
        Specification.installSpecification(Specification.requestSpec(url), Specification.responseSpecOk200());
        List<DataItem> user = given()
                .when()
                .get("api/users?page=2")
                .then()
                .log().all()
                .extract().body().jsonPath().getList("data", DataItem.class);
//user.forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
//Assert.assertTrue(user.stream().allMatch(x->x.getEmail().endsWith("@reqres.in") ));

        List<String> avatars = user.stream().map(DataItem::getAvatar).collect(Collectors.toList());
        List<String> emails = user.stream().map(DataItem::getEmail).collect(Collectors.toList());
        List<String> id = user.stream().map(x -> x.getId().toString()).collect(Collectors.toList());
        for (int i = 0; i < avatars.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(id.get(i)));

        }
    }

    @Test
    @DisplayName("Успешная регистрация методом POST")
    public void testSuccessRegistration() {
        Specification.installSpecification(Specification.requestSpec(url), Specification.responseSpecOk200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessRegister success = given()
                .when()
                .body(user)
                .post("api/register")
                .then().log().all().extract().as(SuccessRegister.class);
        Assert.assertNotNull(SuccessRegister.getId());
        Assert.assertNotNull(SuccessRegister.getToken());
        Assert.assertEquals(id, SuccessRegister.getId());
        Assert.assertEquals(token, SuccessRegister.getToken());


    }

    @Test
    @DisplayName("Негативный кейс. Неуспешная регистрация")
   public void testErrorRegistr() {
        Specification.installSpecification(Specification.requestSpec(url), Specification.responseSpecError400());
        Register user = new Register("sydney@fife", "");
        UnsuccessRegister error = given()
                .when()
                .body(user)
                .post("api/register")
                .then().log().all().extract().as(UnsuccessRegister.class);
        Assert.assertEquals("Missing password", UnsuccessRegister. getError());

    }
    @Test
    @DisplayName("Проверка что года отсортированы по дате")
    public void TestSortByData() {
        Specification.installSpecification(Specification.requestSpec(url), Specification.responseSpecOk200());
        List<Response> user = given()
                .when()
                .get("api/unKnown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", Response.class);
        List<Integer> years  =user.stream().map(Response::getYear).collect(Collectors.toList());
        List<Integer> sortList = years.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(sortList, years);
        System.out.println(years);
        System.out.println(sortList);
    }


    @Test
    @DisplayName("123")
    public void TestDeleteUsers () {
        Specification.installSpecification(Specification.requestSpec(url), Specification.responseSpecUnique(204));
        given()
                .when()
                .delete("/api/users/2")
                .then().log().all();
    }

    @Test
   public void TestSinchronTime() {
Specification.installSpecification(Specification.requestSpec(url), Specification.responseSpecOk200());
TimeTestPojo user = new TimeTestPojo("morpheus", "zion resident");
UserTimeResponce responce = given()
        .body(user)
        .when()
        .put("api/users/2")
        .then().log().all()
        .extract().as(UserTimeResponce.class);

String regex = "(.{5})$";
String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex, "");
        System.out.println(currentTime);
Assert.assertEquals(currentTime, responce.getUpdatedAt().replaceAll(regex,""));
    }
}

