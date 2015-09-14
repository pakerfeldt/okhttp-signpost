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

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import oauth.signpost.exception.OAuthException;

/**
 * An OKHttp interceptor that signs requests using oauth-signpost.
 */
public class SigningInterceptor implements Interceptor {

    private final OkHttpOAuthConsumer consumer;

    /**
     * Constructs a new {@code SigningInterceptor}.
     *
     * @param consumer the {@link OkHttpOAuthConsumer} used to sign the requests.
     */
    public SigningInterceptor(OkHttpOAuthConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        try {
            return chain.proceed((Request) consumer.sign(request).unwrap());
        } catch (OAuthException e) {
            throw new IOException("Could not sign request", e);
        }
    }
}
