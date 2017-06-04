package com.example.guojia.util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class HttpUtil {
	
	public static void sendHttpRequest(final String address,
			final HttpCallbackListener listener){
		new Thread(new Runnable() {
			public void run() {
				//HttpURLConnection connection=null;
				try{
					//System.setProperty("http.keepAlive", "false"); 
//					URL url=new URL(address);//ctrl+shift+c注释
					HttpClient httpClient=new DefaultHttpClient();
					HttpGet httpGet=new HttpGet(address);
					HttpResponse httpResponse=httpClient.execute(httpGet);
					String response="";
					if(httpResponse.getStatusLine().getStatusCode()==200){
						HttpEntity entity=httpResponse.getEntity();
						response=EntityUtils.toString(entity,"utf-8");
					}
					if(listener!=null){
						listener.onFinish(response);
					}
//					Log.d("HttpUtil url",address);
//					//connection=(HttpURLConnection)url.openConnection();
//				     connection = (HttpURLConnection)url.openConnection();
//				    // connection.setRequestProperty("Connection", "close");
//					if(connection!=null){
//						Log.d("connection","is not null");
//					}
//					connection.setRequestMethod("GET");
//					connection.setConnectTimeout(8000);
//					connection.setReadTimeout(8000);
//					InputStream in=connection.getInputStream();
//					if(in!=null){
//						Log.d("in","is not null");
//					}
//					BufferedReader reader=new BufferedReader(new InputStreamReader(in));
//					StringBuilder response=new StringBuilder();
//					String line = null;
//					while((line=reader.readLine())!=null){
//						response.append(line);
//						Log.d("xxx",line.toString());
//					}
//					Log.d("sss","xxx");	
				}catch(Exception e){
					if(listener!=null){
						listener.onError(e);
					}
				}
			}
		}).start();
	}
}
