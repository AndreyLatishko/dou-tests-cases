package com.tests.example;

import com.tests.api.Register;
import com.tests.api.SuccessRegister;
import com.tests.api.UnSuccessRegister;
import com.tests.api.UserData;
import com.tests.helpers.ApiSpecifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ReqresTest {
    private final static String URL = "https://reqres.in/";
    @Test
    public void checkAvatarIdTest(){
        ApiSpecifications.installSpecification
                (ApiSpecifications.requestSpecification(URL),ApiSpecifications.responseSpecification(200));

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

    @Test
    public void successRegisterTest(){
        ApiSpecifications.installSpecification
                (ApiSpecifications.requestSpecification(URL),ApiSpecifications.responseSpecification(200));
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register user = new Register("eve.holt@reqres.in","pistol");
        SuccessRegister successRegister = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessRegister.class);
        Assertions.assertNotNull(successRegister.getId());
        Assertions.assertNotNull(successRegister.getToken());

        Assertions.assertEquals(id, successRegister.getId());
        Assertions.assertEquals(token, successRegister.getToken());
    }

    @Test
    public void unSuccessRegisterTest(){
        ApiSpecifications.installSpecification
                (ApiSpecifications.requestSpecification(URL),ApiSpecifications.responseSpecification(400));
        Register user = new Register("sydney@fife","");
        UnSuccessRegister unSuccessRegister = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessRegister.class);
        Assertions.assertEquals("Missing password", unSuccessRegister.getError());
    }

    @Test
    public void userDeleteTest(){
        ApiSpecifications.installSpecification
                (ApiSpecifications.requestSpecification(URL),ApiSpecifications.responseSpecification(204));
        given().when().delete("api/users/2").then().log().all();
    }

    @Test
    public void singleUserNotFoundTest(){
        ApiSpecifications.installSpecification
                (ApiSpecifications.requestSpecification(URL),ApiSpecifications.responseSpecification(404));
        given().when().get("api/users/23").then().log().all();
    }
}
