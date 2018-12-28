import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class POST_RegisterCustomer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			URL url = new URL("http://restapi.demoqa.com/customer/register");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Accept", "application/json");

			JSONObject requestParams = new JSONObject();
			try {
				requestParams.append("FirstName", "test");
				requestParams.append("LastName", "test");
				requestParams.append("UserName", "test001");
				requestParams.append("Password", "password1");
				requestParams.append("Email", "test@gmail.com");

			} catch (JSONException e1) {
				e1.printStackTrace();
			}

			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			System.out.println(requestParams.toString());
			wr.write(requestParams.toString());
			wr.flush();
			wr.close();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			} else {
				System.out.println(conn.getResponseCode());
				System.out.println(conn.getResponseMessage());
			}
			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
