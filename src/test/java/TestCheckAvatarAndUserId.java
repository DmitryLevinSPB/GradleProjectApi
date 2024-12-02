import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Environments.Environment.BASE_URL;
import static Environments.Environment.EndPoint1;
import static io.restassured.RestAssured.given;

public class TestCheckAvatarAndUserId {

    @Test
    @DisplayName("Первый тест апи")
    public void firstTestApi(){

        given()
                .when()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .get(EndPoint1)
                .then();
    }
    @Test
    @DisplayName("Второй тест апи")
    public void secondTestApi(){
        String  BASE_URL = "https://reqres.in/";

        given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_URL)
                .then();
    }

}
