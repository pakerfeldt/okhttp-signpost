/*
 * Copyright (C) 2015 Patrik Åkerfeldt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.akerfeldt.okhttp.signpost;

import com.squareup.okhttp.Request;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import oauth.signpost.http.HttpRequest;
import okio.Buffer;

public class OkHttpRequestAdapter implements HttpRequest {

    private Request request;

    public OkHttpRequestAdapter(Request request) {
        this.request = request;
    }

    @Override
    public Map<String, String> getAllHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        for (String key : request.headers().names()) {
            headers.put(key, request.header(key));
        }
        return headers;
    }

    @Override
    public String getContentType() {
        if (request.body() != null) {
            return (request.body().contentType() != null) ? request.body().contentType().toString() : null;
        }
        return null;
    }

    @Override
    public String getHeader(String key) {
        return request.header(key);
    }

    @Override
    public InputStream getMessagePayload() throws IOException {
        if (request.body() == null) {
            return null;
        }
        Buffer buf = new Buffer();
        request.body().writeTo(buf);
        return buf.inputStream();
    }

    @Override
    public String getMethod() {
        return request.method();
    }

    @Override
    public String getRequestUrl() {
        return request.urlString();
    }

    @Override
    public void setHeader(String key, String value) {
        request = request.newBuilder().header(key, value).build();
    }

    @Override
    public void setRequestUrl(String url) {
        request = request.newBuilder().url(url).build();
    }

    @Override
    public Object unwrap() {
        return request;
    }
}
