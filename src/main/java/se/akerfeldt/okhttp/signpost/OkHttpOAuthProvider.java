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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import oauth.signpost.AbstractOAuthProvider;
import oauth.signpost.http.HttpRequest;
import oauth.signpost.http.HttpResponse;

/**
 * {@code OkHttpOAuthProvider} is a {@link oauth.signpost.OAuthProvider} implementation that uses OkHttp
 * {@link Request}s.
 */
public class OkHttpOAuthProvider extends AbstractOAuthProvider {

    private transient OkHttpClient okHttpClient;

    /**
     * Constructs a new {@code OkHttpOAuthProvider} with a default {@link OkHttpClient}.
     */
    public OkHttpOAuthProvider(String requestTokenEndpointUrl, String accessTokenEndpointUrl,
                               String authorizationWebsiteUrl) {
        super(requestTokenEndpointUrl, accessTokenEndpointUrl, authorizationWebsiteUrl);
        this.okHttpClient = new OkHttpClient();
    }

    /**
     * Constructs a new {@code OkHttpOAuthProvider}.
     * @param okHttpClient the OkHttpClient to use for sending requests.
     */
    public OkHttpOAuthProvider(String requestTokenEndpointUrl, String accessTokenEndpointUrl,
                               String authorizationWebsiteUrl, OkHttpClient okHttpClient) {
        super(requestTokenEndpointUrl, accessTokenEndpointUrl, authorizationWebsiteUrl);
        this.okHttpClient = okHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Override
    protected HttpRequest createRequest(String endpointUrl) throws Exception {
        Request request = new Request.Builder().url(endpointUrl).build();
        return new OkHttpRequestAdapter(request);
    }

    @Override
    protected HttpResponse sendRequest(HttpRequest request) throws Exception {
        Response response = okHttpClient.newCall((Request) request.unwrap()).execute();
        return new OkHttpResponseAdapter(response);
    }
}
