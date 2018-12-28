import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class GET_WeatherExample {
	URL url = null;
	HttpURLConnection conn = null;
	String output;
	JSONObject myResponse;
	BufferedReader br = null;

	/**
	 * @param args
	 */
	public void openConnection() {

		// Initialize URL object
		try {
			url = new URL(
					"http://restapi.demoqa.com/utilities/weather/city/Hyderabad");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		// Get the URLConnection instance of URL
		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Set HTTP method
		try {
			conn.setRequestMethod("GET");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		// Set Header Property
		conn.setRequestProperty("Accept", "application/json");
	}

	public void checkResponseCode() {

		try {
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			} else {
				System.out.println(conn.getResponseCode());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedReader checkRequestHeaders() {

		try {
			br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(conn.getHeaderField("Content-Type"));
		System.out.println(conn.getHeaderField("Server"));
		// System.out.println(conn.getHeaderField("Content-Encoding"));
		return br;
	}

	public String validateResponseReceived(BufferedReader br) {
		System.out.println("Output from Server .... \n");
		StringBuffer response = new StringBuffer();
		try {
			while ((output = br.readLine()) != null) {
				response.append(output);
			}
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(response.toString());
		return response.toString();
	}

	public void validateSpecificNodeValue(String nodeValueToSearch,
			String response) {
		try {
			myResponse = new JSONObject(response);
			System.out.println("\n\n\nSearching particular Node JSON Response");
			Iterator keys = myResponse.keys();
			HashMap map = new HashMap();

			while (keys.hasNext()) {
				String key = (String) keys.next();
				Object val = myResponse.get(key);
				map.put(key, val.toString());
			}
			// System.out.println("statusCode- "+myResponse.getJSONArray("result"));
			System.out.println("Node" + nodeValueToSearch + " value:"
					+ map.get(nodeValueToSearch));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		conn.disconnect();
	}

}
