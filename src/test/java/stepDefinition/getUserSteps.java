package stepDefinition;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class getUserSteps {

    private RequestSpecification httpRequest;
    private Response response;
    private ResponseBody responseBody;
    private JsonPath jsonPath;

    @Given("user hit the get user api")
    public void user_hit_get_user_api()
    {
        RestAssured.baseURI= "https://reqres.in/api";
        response = RestAssured.given()
                .header("", "")
                .when()
                .get(RestAssured.baseURI +"/users?page=2");
        jsonPath = new JsonPath(response.asString());
        System.out.println("Response - " + jsonPath);
    }

    @Then("^user get (\\d+) as status code$")
    public void user_get_as_status_code(String arg)
    {
        String statusCode = Integer.toString(response.getStatusCode());

        System.out.println("statusCode - " + statusCode);
        assertEquals(arg,statusCode);
    }

    @Then("user verify the result from response")
    public void user_verify_the_result_from_response() {
        // Write code here that turns the phrase above into concrete actions
        String id = jsonPath.getString("data[0].id");
        System.out.println("Id for 1st id: " + id);
        Assert.assertEquals(id,"7");
    }

}
