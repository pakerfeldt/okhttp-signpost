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

import com.squareup.okhttp.Response;
import oauth.signpost.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;

/**
 * A {@link HttpResponse} implementation that is backed by an OkHttp {@link Response} object. This is used by
 * oauth-signpost to read the {@link Response}.
 */
public class OkHttpResponseAdapter implements HttpResponse {

    private Response response;

    public OkHttpResponseAdapter(Response response) {
        this.response = response;
    }

    @Override
    public int getStatusCode() throws IOException {
        return response.code();
    }

    @Override
    public String getReasonPhrase() throws Exception {
        return response.message();
    }

    @Override
    public InputStream getContent() throws IOException {
        return response.body().byteStream();
    }

    @Override
    public Object unwrap() {
        return response;
    }
}
