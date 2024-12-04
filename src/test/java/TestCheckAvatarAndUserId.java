import Pojo.DataItem;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;



public class TestCheckAvatarAndUserId {
 private static String URL = "https://reqres.in/";

    @Test
    @DisplayName("Первый тест апи")
    public void checkAvatarAndUserId(){
        List<DataItem> user = given()
               .when()
               .contentType(ContentType.JSON)
                .baseUri(URL)
               .get("https://reqres.in/api/users?page=2")
               .then()
                .log().all()
                .extract().body().jsonPath().getList("data", DataItem.class);
user.forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
Assert.assertTrue(user.stream().allMatch(x->x.getEmail().endsWith("@reqres.in") ));

List<String> avatars = user.stream().map(DataItem::getAvatar).collect(Collectors.toList());
        List<String> emails = user.stream().map(DataItem::getEmail).collect(Collectors.toList());
        List<String> id = user.stream().map(x->x.getId().toString()).collect(Collectors.toList());
        for(int i =0; i<avatars.size(); i++){
            Assert.assertTrue(avatars.get(i).contains(id.get(i)));

        }
    }

    }

