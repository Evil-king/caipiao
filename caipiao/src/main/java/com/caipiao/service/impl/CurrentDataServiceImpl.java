package com.caipiao.service.impl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.caipiao.service.CurrentDataService;

@Service
public class CurrentDataServiceImpl implements CurrentDataService {

	@Override
	public Object getData() {
		String url = "http://f.apiplus.net/cqssc-10.json";
		String result = "";
        int timeout = 6 * 1000;
        JSONObject jsonObject = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
//            //是否使用证书
//            if (useCert) {
//                
//            }
            HttpGet httpPost = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(timeout)
                    .setConnectTimeout(timeout)
                    .setConnectionRequestTimeout(timeout)
                    .build();
            httpPost.setConfig(requestConfig);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                jsonObject = JSON.parseObject(result);
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
            }
        }
        return jsonObject.get("data");
	}

}
