import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;


import static io.restassured.RestAssured.given;

public class RestAssuredApiTest {

    String login = "login";
    String password = "password";
    String loginAddLink = "/api/mobile/v1/loginAddLink";
    String baseURI = "baseURI";

    @Test
    public void getTokenVar() throws JSONException {

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("login", login);
        jsonObj.put("password", password);

        String token =

                given()
                        .baseUri(baseURI)
                        .header("Content-Type", "application/json")
                        .body(jsonObj.toString())

                        .when()
                        .post(loginAddLink)

                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract().path("token").toString();

        System.out.println(token);
    }
    
    @Test
    public void getResponceBody() throws JSONException {
        RestAssured.baseURI = baseURI;
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("login", login);
        loginCredentials.addProperty("password", password);
        httpRequest.body(loginCredentials.toString());
        Response response = httpRequest.post(loginAddLink);
        System.out.println(response.body().asString());
    }
}

