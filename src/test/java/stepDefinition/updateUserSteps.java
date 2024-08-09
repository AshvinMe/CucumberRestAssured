package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class updateUserSteps {

    private RequestSpecification httpRequest;
    private Response response;
    private ResponseBody responseBody;
    private JsonPath jsonPath;
    private Map<String, Object> data;
    @Given("user hit the put user api")
    public void user_hit_put_user_api()
    {
        data = new HashMap();
        data.put("name", "Kush");
        data.put("job", "Admin");
        RestAssured.baseURI= "https://reqres.in/api";
        response = RestAssured.given()
                .body(data)
                .when()
                .put(RestAssured.baseURI +"/users/2");
        jsonPath = new JsonPath(response.asString());
        System.out.println("Response - " + response.prettyPrint());
    }

    @Then("^user get (\\d+) as status code after user updated$")
    public void user_get_as_status_code_after_user_updated(String arg)
    {
        String statusCode = Integer.toString(response.getStatusCode());

        System.out.println("statusCode - " + statusCode);
        assertEquals(arg,statusCode);
    }

    @Then("user verify the user has been updated")
    public void user_verify_user_updated() {

       Assert.assertNotNull(response.asString());
    }

}
