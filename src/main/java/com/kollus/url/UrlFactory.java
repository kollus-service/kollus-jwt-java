package com.kollus.url;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kollus.jwt.IGenerator;
import com.kollus.thirdparty.akamai.EdgeAuth;
import com.kollus.thirdparty.akamai.EdgeAuthBuilder;
import com.kollus.thirdparty.akamai.EdgeAuthException;

import java.io.IOException;
import java.net.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class UrlFactory {
    public static String create(UrlType urlType, StreamType streamType, IGenerator tokenGenerator, String custom_key, QueryString queryString, boolean force_http) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        StringBuilder builder = new StringBuilder();
        if (force_http) {
            builder.append("http://");
        } else {
            builder.append("https://");
        }
        builder.append(urlType.getDomain());
        builder.append(streamType.getValue());
        builder.append("jwt=");
        builder.append(tokenGenerator.generate());
        builder.append("&custom_key=");
        builder.append(custom_key);
        if (queryString != null && !queryString.isEmpty()) {
            builder.append("&");
            builder.append(queryString.build());
        }
        return builder.toString();
    }

    public static String createAkamai(UrlType urlType, IGenerator tokenGenerator, String custom_key, QueryString queryString, String akamai_key, Long windowSeconds) throws NoSuchAlgorithmException, InvalidKeyException, IOException, URISyntaxException, EdgeAuthException {
        String srUrl = create(urlType, StreamType.REDIRECT, tokenGenerator, custom_key, queryString, false);
        URL url = new URL(srUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("HEAD");
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.connect();
        int resCode = httpURLConnection.getResponseCode();

        if (resCode == HttpURLConnection.HTTP_SEE_OTHER ||
                resCode == HttpURLConnection.HTTP_MOVED_PERM ||
                resCode == HttpURLConnection.HTTP_MOVED_TEMP) {
            String location = httpURLConnection.getHeaderField("Location");
            System.out.println(location);
            System.out.println("");
            String uri = location.split("\\?")[0];
            URI redirectedUrl = new URI(location);
            String[] splitPath = redirectedUrl.getPath().split("/");
            String acl = "";
            for (int index = 0; index <= 3; index++) {
                acl += splitPath[index] + "/";
            }
            acl += "*";

            EdgeAuth edgeAuth = new EdgeAuthBuilder()
                    .key(akamai_key)
                    .tokenName("hdnts")
                    .windowSeconds(windowSeconds)
                    .build();
            String akamai_token = edgeAuth.generateACLToken(acl);
            return String.format("%s?hdnts=%s\n", uri, akamai_token);
        } else {
            return "";
        }
    }

    public static String create(UrlType urlType, StreamType streamType, IGenerator tokenGenerator, String custom_key, QueryString queryString) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        return create(urlType, streamType, tokenGenerator, custom_key, queryString, false);

    }
}
