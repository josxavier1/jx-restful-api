package com.dandh.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

@ApplicationPath(value = "/")
@Path("/VertexAuthorization")
public class VertexAuthService extends Application {
	// prod
	// private static final String vertexOAuthURL =
	// "https://auth.vertexsmb.com/identity/connect/token";
	// private static final String vertexClientId = "986016994_01";
	// private static final String vertexSecretKey =
	// "4f545e906dc742dfbd0c42e2bc638756";
	// dev
	private static final String vertexOAuthURL = "https://auth.vertexsmb.com/identity/connect/token";
	private static final String vertexClientId = "986016994_01_dev";
	private static final String vertexSecretKey = "81dddde3814b450bbf4f828a92b8c868";

	private static final String vertexGrantType = "client_credentials";
	private static final String vertexScope = "vtms-internal-api ecw-wizard-api";

	private static final String fiddlerProxyServer = "127.0.0.1";
	private static final String fiddlerProxyPort = "8888";

	
	private static final String vertexEditTokenURL = "https://ecwportal.vertexsmb.com/ecw-service/wizard/v1/createEditToken";
	
	@GET
	@Path("token")
	@Produces({ "application/json" })
	public String getVertexAuthToken() {

		System.out.println("reached getVertexAuthToken!!");
		String output = "";
		try {
			SSLContext sslContext = DandHSSLContext.getInstance().getSSLContext();
			if (sslContext == null) {
				return "500";
			}
			String requestBody = "client_id=" + vertexClientId + "&client_secret=" + vertexSecretKey + "&grant_type="
					+ vertexGrantType + "&scope=" + vertexScope;
			URL url = new URL(vertexOAuthURL);

			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			// proxy call for debugging
			// Proxy proxy = new Proxy(Proxy.Type.HTTP,
			// new InetSocketAddress(fiddlerProxyServer,
			// Integer.parseInt(fiddlerProxyPort)));
			// HttpsURLConnection conn = (HttpsURLConnection) url.openConnection(proxy);

			conn.setSSLSocketFactory(sslContext.getSocketFactory());
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Length", Integer.toString(requestBody.length()));
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			try (OutputStream os = conn.getOutputStream()) {
				byte[] input = requestBody.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			if (conn.getResponseCode() != 200) {
				System.out.println(conn.getResponseMessage());
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());

			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
			output = "500";

		} catch (IOException e) {
			e.printStackTrace();
			output = "500";
		} catch (SSLLoadException e) {
			e.printStackTrace();
			output = "500";
		}
		return output;
	}

	@GET
	@Path("editToken")
	@Produces({ "application/json" })
	public String getEditToken() {
		System.out.println("reached getEditToken!!");
		String output = "";
		try {
			SSLContext sslContext = DandHSSLContext.getInstance().getSSLContext();
			if (sslContext == null) {
				return "500";
			}
			String requestBody = "client_id=" + vertexClientId + "&client_secret=" + vertexSecretKey + "&grant_type="
					+ vertexGrantType + "&scope=" + vertexScope;
			URL url = new URL(vertexOAuthURL);

			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			// proxy call for debugging
			// Proxy proxy = new Proxy(Proxy.Type.HTTP,
			// new InetSocketAddress(fiddlerProxyServer,
			// Integer.parseInt(fiddlerProxyPort)));
			// HttpsURLConnection conn = (HttpsURLConnection) url.openConnection(proxy);

			conn.setSSLSocketFactory(sslContext.getSocketFactory());
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Length", Integer.toString(requestBody.length()));
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			try (OutputStream os = conn.getOutputStream()) {
				byte[] input = requestBody.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			if (conn.getResponseCode() != 200) {
				System.out.println(conn.getResponseMessage());
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());

			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
			output = "500";

		} catch (IOException e) {
			e.printStackTrace();
			output = "500";
		} catch (SSLLoadException e) {
			e.printStackTrace();
			output = "500";
		}
		return output;
	}

	public static void main(String[] args) {
		new VertexAuthService().getVertexAuthToken();
	}
}
