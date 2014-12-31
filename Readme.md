signpost-retrofit
========

A [Retrofit][1] extension to [oauth-signpost][2].

The extension comes with a `SigningOkClient` helper class to use with [OkHttp][3] when building the `RestAdapter`.

Usage
=======

To use, simply create an instance of `SigningOkClient` providing it a `RetrofitHttpOAuthConsumer`.

This will add the necessary oAuth headers to each request.

```java
RetrofitHttpOAuthConsumer oAuthConsumer = new RetrofitHttpOAuthConsumer(key, secret);
oAuthConsumer.setTokenWithSecret(token, secret);
OkClient client = new SigningOkClient(oAuthConsumer);
RestAdapter adapter = new RestAdapter.Builder()
                                    .setEndpoint("...")
                                    .setClient(client)
                                    .build();
```

Note that this extension does not (currently) include `HttpOAuthProvider` and `HttpResponseAdapter` implementations.

License
=======

    Copyright 2013 Patrik Åkerfeldt

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


 [1]: https://github.com/square/retrofit
 [2]: https://github.com/mttkay/signpost
 [3]: https://github.com/square/okhttp
