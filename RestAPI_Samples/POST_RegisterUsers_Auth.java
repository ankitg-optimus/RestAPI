import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;


public class POST_RegisterUsers_Auth {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			// URL url = new
			// URL("http://services.groupkt.com/state/get/IND/UP");
			URL url = new URL("https://gorest.co.in/public-api/users?access-token=ggolvSv4UpUH_a9Qk5x5KAC2YudbptpltVYZ");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/json; charset=UTF-8");
			conn.setRequestProperty( "Accept", "*/*" );
			//conn.setRequestProperty("Content-Type", "application/" + "json");
			//conn.setRequestProperty("Authorization", "Basic" + "ggolvSv4UpUH_a9Qk5x5KAC2YudbptpltVYZ");
			//conn.connect();
			JSONObject requestParams = new JSONObject();
			try {
				requestParams.append("email", "test1@test.com");

				requestParams.append("first_name", "test");

				requestParams.append("last_name", "test001");
				requestParams.append("gender", "male");

			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*
			 * StringBuilder postData = new StringBuilder(); String currentKey;
			 * while(requestParams.keys().hasNext()) {
			 * 
			 * if (postData.length() != 0) postData.append("&"); currentKey =
			 * requestParams.keys().next().toString();
			 * System.out.println("currentKey:"+currentKey);
			 * postData.append(URLEncoder.encode(currentKey, "UTF-8"));
			 * postData.append("="); try {
			 * postData.append(URLEncoder.encode(requestParams
			 * .get(currentKey).toString(), "UTF-8")); } catch (JSONException e)
			 * { // TODO Auto-generated catch block e.printStackTrace(); } }
			 * byte[] postDataBytes = postData.toString().getBytes("UTF-8");
			 * conn.setRequestProperty("Content-Length",
			 * String.valueOf(postDataBytes.length));
			 */
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
