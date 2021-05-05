package no.hvl.dat110.aciotdevice.client;

import java.io.IOException;

import com.google.gson.Gson;

import okhttp3.*;

public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
		
	}

	private static String logpath = "/accessdevice/log";

	@SuppressWarnings("deprecation")
	public void doPostAccessEntry(String message) {

		String accessentry = new Gson().toJson(new AccessMessage(message));
		
		RequestBody accessentryBody = RequestBody.create(MediaType.parse("application/json"), accessentry);
		
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://localhost:8080" + logpath)
		  .post(accessentryBody)
		  .build();
			
		try (Response response = client.newCall(request).execute()) {
		      System.out.println (response.body().string());
		    }
	   catch (IOException e) {
		   e.printStackTrace();
	   }
	}
		
	private static String codepath = "/accessdevice/code";
	
	public AccessCode doGetAccessCode() {

		AccessCode accessCode = null;
		
			Gson gson = new Gson();

			OkHttpClient client = new OkHttpClient();

			Request request = new Request.Builder()
					.url("http://localhost:8080" + codepath)
					.build();

			try (Response response = client.newCall(request).execute()) {
				String responseBody = response.body().string();
				System.out.println (responseBody);
				accessCode = gson.fromJson(responseBody, AccessCode.class);
			}
			catch (IOException e) {
				e.printStackTrace();
			}

		return accessCode;
	}
	
}

