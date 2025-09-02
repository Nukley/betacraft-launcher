package uk.betacraft.auth;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;
import org.bouncycastle.tls.crypto.impl.bc.BcTlsCrypto;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import com.google.gson.Gson;
import com.nukley.tls.*;

import uk.betacraft.util.WebData;

public class RequestUtil {
	private static boolean debug = false;

	public static String webDataToString(WebData data) {
		if (data.getData() != null) {
			try {
				String response = new String(data.getData(), "UTF-8");
				if (debug) System.out.println("INCOMING: " + response);
				return response;
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		return null;
	}

	public static String performPOSTRequest(Request req) {
		WebData data = performRawPOSTRequest(req);
		return webDataToString(data);
	}

	public static WebData performRawPOSTRequest(Request req) {
		CloseableHttpClient client = null;
		try {
			client = createHttpClient();
			HttpPost post = new HttpPost(req.REQUEST_URL);
			setHeaders(post, req.PROPERTIES);

			if (req.POST_DATA == null) {
				Gson gson = new Gson();
				String json = gson.toJson(req);
				if (debug) System.out.println("OUTGOING: " + json);
				post.setEntity(new StringEntity(json, "UTF-8"));
			} else {
				if (debug) System.out.println("OUTGOING: " + req.POST_DATA);
				post.setEntity(new StringEntity(req.POST_DATA, "UTF-8"));
			}

			CloseableHttpResponse response = client.execute(post);
			try {
				return handleResponse(response);
			} finally {
				response.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return new WebData(null, -1);
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String performGETRequest(Request req) {
		WebData data = performRawGETRequest(req);
		return webDataToString(data);
	}

	public static WebData performRawGETRequest(Request req) {
		CloseableHttpClient client = null;
		try {
			client = createHttpClient();
			HttpGet get = new HttpGet(req.REQUEST_URL);
			setHeaders(get, req.PROPERTIES);

			CloseableHttpResponse response = client.execute(get);
			try {
				return handleResponse(response);
			} finally {
				response.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return new WebData(null, -1);
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static {
		// Add Bouncy Castle as a security provider
		Security.addProvider(new BouncyCastleJsseProvider());
	}

	private static CloseableHttpClient createHttpClient() {
		try {
			// Register Bouncy Castle as a security provider
			Security.addProvider(new BouncyCastleProvider());

			// Create Bouncy Castle TLS crypto
			SecureRandom secureRandom = new SecureRandom();
			BcTlsCrypto randomCrypt = new BcTlsCrypto(secureRandom);

			// Create the custom SSL Socket Factory
			CustomTlsSocketFactory sslSocketFactory = new CustomTlsSocketFactory(randomCrypt);

			// Create HttpClient with the custom SSL Socket Factory
			return HttpClients.custom()
					.setSSLSocketFactory(new SSLConnectionSocketFactory(sslSocketFactory, NoopHostnameVerifier.INSTANCE))
					.build();

		} catch (Exception e) {
			throw new RuntimeException("Failed to create HTTP client", e);
		}
	}

	private static void setHeaders(HttpPost post, Map properties) {
		for (Iterator it = properties.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			post.addHeader(key, (String) properties.get(key));
		}
	}

	private static void setHeaders(HttpGet get, Map properties) {
		for (Iterator it = properties.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			get.addHeader(key, (String) properties.get(key));
		}
	}

	private static WebData handleResponse(HttpResponse response) throws IOException {
		int httpCode = response.getStatusLine().getStatusCode();
		byte[] data = readInputStream(response.getEntity().getContent());
		return new WebData(data, httpCode);
	}

	public static byte[] readInputStream(InputStream in) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int count;
			while ((count = in.read(buffer)) != -1) {
				baos.write(buffer, 0, count);
			}
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}




