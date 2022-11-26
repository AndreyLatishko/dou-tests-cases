package com.tests.example;

import com.tests.api.UserData;
import com.tests.helpers.ApiSpecifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ReqresTest {
    private final static String URL = "https://reqres.in/";
    @Test
    public void checkAvatarIdTesst(){
        ApiSpecifications.installSpecification
                (ApiSpecifications.requestSpecification(URL),ApiSpecifications.responseSpecification200());

        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.forEach(x-> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));

        Assertions.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));

        List<String> avatars = users.stream().map(UserData::getAvatar).toList();
        List<String> ids = users.stream().map(x->x.getId().toString()).toList();
        for (int i = 0; i < avatars.size(); i++) {
           Assertions.assertTrue(avatars.get(i).contains(ids.get(i)));
        }
    }
}
