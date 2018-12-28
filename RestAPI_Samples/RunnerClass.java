import java.io.BufferedReader;

public class RunnerClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GET_WeatherExample weather = new GET_WeatherExample();

		weather.openConnection();
		// Scenario 1: Validate that response code is 200 on successful request
		weather.checkResponseCode();

		// Scenario 2: Validate request header
		BufferedReader br = weather.checkRequestHeaders();

		// Scenario 3: Validate response recieved
		String response = weather.validateResponseReceived(br);

		// Scenario 4: Validate particular Node value
		String nodeValueToSearch = "WeatherDescription";
		weather.validateSpecificNodeValue(nodeValueToSearch, response);
		weather.closeConnection();
	}

}
