import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class GET_StatesExample_RestAssured {
	public void GetWeatherDetails() {
		String[] city = { "Hyderabad" };
		ResponseOptions response = RestAssured
				.given()
				.when()
				.get("http://restapi.demoqa.com/utilities/weather/city/{cityName}",
						city);
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
	}

	public void GetStateDetails() {
		String[] state = { "UP" };
		ResponseOptions response = RestAssured
				.given()
				.when()
				.get("http://services.groupkt.com/state/get/IND/{StateName}",
						state);
		JsonPath jsonPathEvaluator = response.body().jsonPath();
		List jsonNodes = jsonPathEvaluator.getList("RestResponse.result.id");

		System.out.println("jsonPathEvaluator" + jsonNodes);
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
	}

	public void registerUser() {
		RestAssured.baseURI = "http://restapi.demoqa.com/customer/register";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		try {
			requestParams.append("FirstName", "Virender"); // Cast

			requestParams.append("LastName", "Singh");

			requestParams.append("UserName", "sdimpleuser2dd2011");
			requestParams.append("Password", "password1");

			requestParams.append("Email", "sample2ee26d9@gmail.com");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.body(requestParams.toString());
		ResponseOptions response = request.post();

		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
	}

	/*
	 * public void GetUserDetails() { // Specify the base URL to the RESTful web
	 * service // RestAssured.baseURI = "http://services.groupkt.com";
	 * 
	 * // Get the RequestSpecification of the request that you want to sent //
	 * to the server. The server is specified by the BaseURI that we have //
	 * specified in the above step. String[] state = {"UP"}; ResponseOptions
	 * response = RestAssured.given() .when()
	 * .get("http://services.groupkt.com/state/get/IND/{StateName}",state);
	 * 
	 * // Now let us print the body of the message to see what response // we
	 * have recieved from the server String responseBody =
	 * response.getBody().asString(); System.out.println("Response Body is =>  "
	 * + responseBody);
	 * 
	 * }
	 */
}
