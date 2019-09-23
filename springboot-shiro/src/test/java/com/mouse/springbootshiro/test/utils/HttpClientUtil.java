package com.mouse.springbootshiro.test.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by djh on 14-1-15.
 */
public class HttpClientUtil {
	private static final String CONTENT_TYPE = "text/html;charset=UTF-8";
	private static final String CONTENT_TYPE_POST = "application/json;charset=UTF-8";
	public static final String ENCODING = "UTF-8";
	public static final String CHARSET_GBK = "GBK";
	public static final String CONTENT_TYPE_TEXT_HTML = "text/html";
	public static final String CONTENT_TYPE_APPLICATION_FORM = "application/x-www-form-urlencoded";
	private static final String HTTP_METHOD_GET = "GET";
	private static final String HTTP_METHOD_POST = "POST";

	public static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * http get
	 * 
	 * @param charSet
	 *            default utf-8
	 * @return
	 */
	private static String get(GetMethod get, String charSet) {
		HttpClient httpClient = new HttpClient();
		int status = 0;
		InputStream is = null;

		try {
			status = httpClient.executeMethod(get);
			is = get.getResponseBodyAsStream();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		//logger.info("status :" + status);
		return getResponse(status, is, charSet);
	}

	private static String getResponse(int status, InputStream is, String charSet) {
		String encode = StringUtils.isNotEmpty(charSet) ? charSet : HttpClientUtil.ENCODING;
		StringBuilder sb = new StringBuilder();
		if (status == HttpStatus.SC_OK && is != null) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, encode));
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return sb.toString();
	}

	public static String getRequest(String url, List<NameValuePair> list, String charSet, String contentType) {
		String encode = StringUtils.isNotEmpty(charSet) ? charSet : HttpClientUtil.ENCODING;
		String type = StringUtils.isNotEmpty(contentType) ? contentType : HttpClientUtil.CONTENT_TYPE_TEXT_HTML;

		String encodeUrl = url;
		try {
			encodeUrl = URIUtil.encodeQuery(url, encode);
		} catch (URIException e) {
			logger.error(e.getMessage(), e);
		}
		GetMethod get = new GetMethod(encodeUrl);
		get.addRequestHeader("Content-Type", type + ";charset=" + encode);

		if (list != null && !list.isEmpty()) {
			NameValuePair[] arr = new NameValuePair[list.size()];
			get.setQueryString(list.toArray(arr));
		}
		logger.debug("url=" + url);
		return get(get, encode);
	}

	public static String getRequest(String url, List<NameValuePair> list) {
		return request(url, HTTP_METHOD_GET, list, "");
	}

	public static String getRequest(String url, String queryString) {
		return request(url, HTTP_METHOD_GET, null, queryString);
	}

	public static String postRequest(String url, List<NameValuePair> list) {
		return request(url, HTTP_METHOD_POST, list, "");
	}

	public static String postRequest(String url, String requestBody) {
		return request(url, HTTP_METHOD_POST, null, requestBody);
	}

	@SuppressWarnings("deprecation")
	private static String request(String url, String method, List<NameValuePair> list, String body) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isEmpty(url)) {
			return "";
		}
		HttpClient httpClient = new HttpClient();
		int status = 0;
		InputStream is = null;
		if (method.equalsIgnoreCase("get")) {
			String encodeUrl = url;
			try {
				encodeUrl = URIUtil.encodeQuery(url, ENCODING);
			} catch (URIException e) {
				logger.error(e.getMessage(), e);
			}
			GetMethod get = new GetMethod(encodeUrl);
			get.addRequestHeader("Content-Type", CONTENT_TYPE);

			if (list != null && !list.isEmpty()) {
				NameValuePair[] arr = new NameValuePair[list.size()];
				get.setQueryString(list.toArray(arr));
			} else if (StringUtils.isNotEmpty(body)) {
				get.setQueryString(body);
			}
			try {
				status = httpClient.executeMethod(get);
				is = get.getResponseBodyAsStream();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		} else if (method.equalsIgnoreCase("post")) {
			PostMethod post = new PostMethod(url);
			post.addRequestHeader("Content-Type", CONTENT_TYPE_POST);
			if (list != null && !list.isEmpty()) {
				NameValuePair[] arr = new NameValuePair[list.size()];
				// post.setRequestBody(list.toArray(arr));
				post.addParameters(list.toArray(arr));
			} else if (StringUtils.isNotEmpty(body)) {
				post.setRequestBody(body);
			}
			try {
				status = httpClient.executeMethod(post);
				is = post.getResponseBodyAsStream();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}

		if (status == HttpStatus.SC_OK && is != null) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, ENCODING));
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return sb.toString();
	}

	public static String getRequest(String url, String queryString, String charSet, String contentType) {
		String encode = StringUtils.isNotEmpty(charSet) ? charSet : HttpClientUtil.ENCODING;
		String type = StringUtils.isNotEmpty(contentType) ? contentType : HttpClientUtil.CONTENT_TYPE_TEXT_HTML;

		String encodeUrl = url;
		try {
			encodeUrl = URIUtil.encodeQuery(url, encode);
		} catch (URIException e) {
			logger.error(e.getMessage(), e);
		}
		GetMethod get = new GetMethod(encodeUrl);
		get.addRequestHeader("Content-Type", type + ";charset=" + encode);

		if (StringUtils.isNotEmpty(queryString)) {
			get.setQueryString(queryString);
		}
		return get(get, encode);
	}

	public static String postHttpClient(String url, Map<String, String> mapParams) {
		String result = "";
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		try {
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			CloseableHttpClient client = httpClientBuilder.build();
			RequestConfig rc = RequestConfig.custom().setConnectTimeout(1200).build();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(rc);
			for (Entry<String, String> entry : mapParams.entrySet()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
			httpPost.setEntity(entity);
			CloseableHttpResponse response = client.execute(httpPost);
			if (200 == response.getStatusLine().getStatusCode()) {
				result = EntityUtils.toString(response.getEntity());
				//logger.info("HttpClientSent Response：{" + result + "}");
			} else {
				throw new Exception("接口调用失败！");
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			params = null;
		}
		return result;
	}

	/**
	 * 调用集中作业接口通用方法
	 * 
	 * @param url
	 * @param postMethod
	 * @param response
	 * @return String
	 * @author c_yulei-017
	 */
	public static String ammassBussHttpPost(String url, PostMethod postMethod, HttpServletResponse response) {
		String responseStr = "";
		HttpClient httpClient = null;
		try {
			// 设置格式
			postMethod.getParams().setContentCharset("UTF-8");
			// PrintWriter out = response.getWriter();
			httpClient = new HttpClient();
			// 执行postMethod
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				byte[] bodydata = postMethod.getResponseBody();
				// 取得返回值
				responseStr = new String(bodydata, "UTF-8");
				//logger.info("结果返回" + responseStr.toString());
				// out.print(responseStr.toString());
				// out.flush();
			}
		} catch (Exception e) {
			logger.error(url + "调用接口失败!" + "方法：" + postMethod.getParameter("CALLFUNCTION"));
		}
		return responseStr;
	}

}