package Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

public class HttpRequestUtil {
	
	public static JSONObject httpRequest(String requestUrl,String requestMethod,String outputStr) throws Exception{
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		InputStream inputStream = null;
		
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection)url.openConnection();
			httpUrlConn.setDoOutput(true);//以后就可以使用conn.getOutputStream().write()  
			httpUrlConn.setDoInput(true);//以后就可以使用conn.getInputStream().read()
			httpUrlConn.setUseCaches(false);
			
			//设置请求方式(GET/POST)
			httpUrlConn.setRequestMethod(requestMethod);
			if("POST".equalsIgnoreCase(requestMethod)){
				httpUrlConn.connect();
			}
			
			if(null != outputStr){
				OutputStream os = httpUrlConn.getOutputStream();
				os.write(outputStr.getBytes("UTF-8"));
				os.close();
			}
			
			inputStream = httpUrlConn.getInputStream();
			InputStreamReader isr = new InputStreamReader(inputStream,"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			String str = null;
			while((str = br.readLine()) != null){
				buffer.append(str);
			}
			br.close();
			isr.close();
			
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("connection timed out");
			
		} finally{
			try{
				if(inputStream != null){
					inputStream.close();
				}
					
			
			}catch(IOException e){
			 e.printStackTrace();
			}
		
	}
		return jsonObject;
	}
	
}
