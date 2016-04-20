package se.akerfeldt.okhttp.signpost;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.http.HttpRequest;
import okhttp3.Request;
import org.apache.commons.codec.binary.Base64;

/**
 * {@code OkHttpOAuthConsumerUsingBasicAuthAtHttps} is a {@link oauth.signpost.OAuthConsumer} implementation capable of handling OkHttp
 * {@link Request}s. If the request is https, this uses basic authorization.
 */
public class OkHttpOAuthConsumerUsingBasicAuthAtHttps extends OkHttpOAuthConsumer {

    private String encodedAuthStr;

    /**
     * Constructs a new {@code OkHttpOAuthConsumerUsingBasicAuthAtHttps}.
     * @param consumerKey the consumer key.
     * @param consumerSecret the consumer secret.
     */
    public OkHttpOAuthConsumerUsingBasicAuthAtHttps(String consumerKey, String consumerSecret) {
        super(consumerKey, consumerSecret);

        String authStr = getConsumerKey() + ":" + getConsumerSecret();
        encodedAuthStr = new String(Base64.encodeBase64(authStr.getBytes()));
    }

    public synchronized HttpRequest sign(Object request) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
        return this.sign(this.wrap(request));
    }

    public synchronized HttpRequest sign(HttpRequest request) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
        if (request.getRequestUrl().startsWith("https://")) {
            request.setHeader("Authorization", "Basic " + encodedAuthStr);

            return request;
        } else {
            return super.sign(request);
        }
    }

}
