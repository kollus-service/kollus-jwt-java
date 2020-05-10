package com.kollus.jwt;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JwtUtil {
    public String createJwt(final String headerJson, final String payloadJson, String secretKey)
            throws NoSuchAlgorithmException, InvalidKeyException {
        String header = Base64.encodeBase64URLSafeString(headerJson.getBytes(StandardCharsets.UTF_8));
        String payload = Base64.encodeBase64URLSafeString(payloadJson.getBytes(StandardCharsets.UTF_8));
        String content = String.format("%s.%s", header, payload);
        final Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signatureBytes = mac.doFinal(content.getBytes(StandardCharsets.UTF_8));
        String signature = Base64.encodeBase64URLSafeString(signatureBytes);
        return String.format("%s.%s", content, signature);
    }
    public String createJwt(final String payloadJson, String secretKey)
            throws InvalidKeyException, NoSuchAlgorithmException {
        String headerJson = "{\"alg\": \"HS256\",\"typ\": \"JWT\"}";
        return createJwt(headerJson, payloadJson, secretKey);
    }



    public String[] splitJwt(String jwt) throws Exception{
        String[] parts = jwt.split("\\.");
        if (parts.length == 2 && jwt.endsWith(".")) {
            parts = new String[] { parts[0], parts[1], "" };
        }
        if (parts.length != 3) {
            throw new Exception(String.format("The token was expected to have 3 parts, but got %s.", parts.length));
        }
        return parts;
    }
    public String[] decodeJwt(String jwt) throws Exception {

        String[] parts = splitJwt(jwt);
        String headerJson = StringUtils.newStringUtf8(Base64.decodeBase64(parts[0]));
        String payloadJson = StringUtils.newStringUtf8(Base64.decodeBase64(parts[1]));
        String signature = parts[2];
        return new String[]{headerJson, payloadJson, signature};
    }
    public boolean verify(String secretKey, String jwt) throws Exception{
        String[] parts = splitJwt(jwt);
        byte[] contentBytes = String.format("%s.%s", parts[0], parts[1]).getBytes(StandardCharsets.UTF_8);
        byte[] signatureBytes = Base64.decodeBase64(parts[2]);

        final Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] newSignatureBytes = mac.doFinal(contentBytes);
        return MessageDigest.isEqual(newSignatureBytes, signatureBytes);

    }
}
