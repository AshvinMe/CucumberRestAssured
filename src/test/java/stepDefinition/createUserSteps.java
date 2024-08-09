package stepDefinition;

import groovy.lang.GString;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class createUserSteps {

    private RequestSpecification httpRequest;
    private Response response;
    private ResponseBody responseBody;
    private JsonPath jsonPath;
    private Map<String, Object> data;
    @Given("user hit the post user api")
    public void user_hit_post_user_api()
    {
        data = new HashMap();
        data.put("name", "ashvin");
        data.put("job", "IT");
        RestAssured.baseURI= "https://reqres.in/api";
        response = RestAssured.given()
                .body(data)
                .when()
                .post(RestAssured.baseURI +"/users");
        jsonPath = new JsonPath(response.asString());
        System.out.println("Response - " + response.prettyPrint());
    }

    @Then("^user get (\\d+) as status code after user created$")
    public void user_get_as_status_code_after_user_created(String arg)
    {
        String statusCode = Integer.toString(response.getStatusCode());

        System.out.println("statusCode - " + statusCode);
        assertEquals(arg,statusCode);
    }

    @Then("user verify the user has been created")
    public void user_verify_user_created() {
        // Write code here that turns the phrase above into concrete actions
        String id = jsonPath.getString("id");
        System.out.println("Id after user create: " + id);
        Assert.assertNotNull(id);
    }

}
