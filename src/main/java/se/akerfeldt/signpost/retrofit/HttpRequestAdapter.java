package se.akerfeldt.signpost.retrofit;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit.client.Header;
import retrofit.client.Request;

public class HttpRequestAdapter implements oauth.signpost.http.HttpRequest {

	private Request request;

	public HttpRequestAdapter(retrofit.client.Request request) {
        this.request = request;
    }
	
	@Override
	public Map<String, String> getAllHeaders() {
		HashMap<String, String> headers = new HashMap<String, String>(); 
		for(Header header : request.getHeaders()) {
			headers.put(header.getName(), header.getValue());
		}
		return headers;
	}

	@Override
	public String getContentType() {
		return "application/json";
	}

	@Override
	public String getHeader(String key) {
		for(Header header : request.getHeaders()) {
			if(key.equals(header.getName())) {
				return header.getValue();
			}
		}
		return null;
	}

	@Override
	public InputStream getMessagePayload() throws IOException {
		throw new RuntimeException(new UnsupportedOperationException());
	}

	@Override
	public String getMethod() {
		return request.getMethod();
	}

	@Override
	public String getRequestUrl() {
		return request.getUrl();
	}

	@Override
	public void setHeader(String key, String value) {
		ArrayList<Header> headers = new ArrayList<Header>();
		headers.addAll(request.getHeaders());
		headers.add(new Header(key, value));
		Request copy = new Request(request.getMethod(), request.getUrl(), headers, request.getBody());
		request = copy;
	}
	

	@Override
	public void setRequestUrl(String url) {
		 throw new RuntimeException(new UnsupportedOperationException());
	}

	@Override
	public Object unwrap() {
		return request;
	}

}
