okhttp-signpost
========

A [Signpost][1] extension for signing [OkHttp][2] requests.

Download
=======
Gradle:
```gradle
    compile 'se.akerfeldt:okhttp-signpost:1.0.0'
    compile 'okhttp3:okhttp:2.5.0'
    compile 'oauth.signpost:signpost-core:1.2.1.2'
```
Usage
=======

To use, simply create an instance of `OkHttpOAuthConsumer` passing in your consumer key/secret pair.
Set your token and token secret. Lastly, create a SigningInterceptor with your consumer and give it to your
`OkHttpClient.Builder`.

```java
OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
consumer.setTokenWithSecret(token, secret);

OkHttpClient client = new OkHttpClient.Builder()
        .addInterceptor(new SigningInterceptor(consumer))
        .build();
```

The `SigningInterceptor` is a convenience for signing the request but is not mandatory. You could also sign your request
manually like so:

```java
OkHttpOAuthConsumer consumer = ...
Request request = new Request.Builder().build();
Request signedRequest = (Request) consumer.sign(request).unwrap();

Call call = okHttpClient.newCall(signedRequest);
```

Obviously, this fits very well with [Retrofit 2][3] as well. Just pass your OkHttpClient (containing the interceptor) to
Retrofit:
```java
OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
consumer.setTokenWithSecret(token, secret);

OkHttpClient client = new OkHttpClient.Builder()
        .addInterceptor(new SigningInterceptor(consumer))
        .build();

return new Retrofit.Builder()
        ...
        .client(okHttpClient)
        .build();

```

License
=======

    Copyright 2015 Patrik Åkerfeldt

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


 [1]: https://github.com/mttkay/signpost
 [2]: https://github.com/square/okhttp
 [3]: https://github.com/square/retrofit
