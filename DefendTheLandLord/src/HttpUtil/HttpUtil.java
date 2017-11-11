package HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
	
	private static RequestConfig requestConfig;
	
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class.getName());
	
	public static String doPostByParam(String url, Map<String, String> bodyParams) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String resultString = null;
		HttpPost httpPost = new HttpPost(url.trim());
		CloseableHttpResponse response = null;

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		if (bodyParams != null && bodyParams.size() > 0) {
			for (Map.Entry<String, String> entry : bodyParams.entrySet()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		try {
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			resultString = EntityUtils.toString(entity, "UTF-8");
		} catch (IOException e) {
			logger.error("error ",e);
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					logger.error("error ",e);
				}
			}
		}
		return resultString;
	}
}
