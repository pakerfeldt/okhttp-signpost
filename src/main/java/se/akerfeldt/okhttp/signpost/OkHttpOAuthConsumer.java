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

import okhttp3.Request;

import oauth.signpost.AbstractOAuthConsumer;
import oauth.signpost.http.HttpRequest;

/**
 * {@code OkHttpOAuthConsumer} is a {@link oauth.signpost.OAuthConsumer} implementation capable of handling OkHttp
 * {@link Request}s.
 */
public class OkHttpOAuthConsumer extends AbstractOAuthConsumer {

    /**
     * Constructs a new {@code OkHttpOAuthConsumer}.
     * @param consumerKey the consumer key.
     * @param consumerSecret the consumer secret.
     */
    public OkHttpOAuthConsumer(String consumerKey, String consumerSecret) {
        super(consumerKey, consumerSecret);
    }

    @Override
    protected HttpRequest wrap(Object request) {
        if (!(request instanceof Request)) {
            throw new IllegalArgumentException("This consumer expects requests of type " + Request.class.getCanonicalName());
        }
        return new OkHttpRequestAdapter((Request) request);
    }

}
