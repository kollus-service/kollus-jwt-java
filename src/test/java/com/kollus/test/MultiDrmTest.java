package com.kollus.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kollus.thirdparty.pallycon.policy.DrmTypes;
import com.kollus.thirdparty.pallycon.policy.TokenRuleGenerator;
import com.kollus.thirdparty.pallycon.token.TokenGenerator;
import com.kollus.url.StreamType;
import com.kollus.url.UrlFactory;
import com.kollus.url.UrlType;
import com.kollus.vod.VodTokenGenerator;
import com.kollus.vod.mediacontent.Mc;
import com.kollus.vod.mediacontent.McGenerator;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class MultiDrmTest {


    @Test
    public void createWidevineTest() throws Exception {
        String secret_key = "kmds-vible";
        String custom_key = "e97bbb6707ff1daf4921ee56f108357ea293061cd4020027e622a8e0257f5be8";
        String siteid = "QF0B";
        String sitekey = "6ask61ko92LMkPin5LlVgn0CSpz47edf";
        String accesskey = "Xu3vAnEExlGYpbYu1zMUJB9os1ooX0SK";
        VodTokenGenerator vodTokenGenerator = new VodTokenGenerator();
        McGenerator mcGenerator = new McGenerator();
        TokenRuleGenerator tokenRuleGenerator = new TokenRuleGenerator();
        TokenGenerator tokenGenerator = new TokenGenerator();
        String tokenRule = tokenRuleGenerator.site_key(sitekey)
                .allow_mobile_abnormal_device(true)
                .allow_external_display(true)
                .duration(3600)
                .limit(true)
                .build();
        String multidrmToken = tokenGenerator
                .drm_type(DrmTypes.Widevine)
                .site_id(siteid)
                .user_id("catenoid_test")
                .cid("20200513-5w7xq6di")
                .timestamp(new Date())
                .token(tokenRule)
                .hash(accesskey)
                .generate();
        Mc mc = mcGenerator.
                mckey("Smj0yL5r").
                drm_policy_kind("inka").
                drm_policy_streaming_type("dash").
                drm_policy_data_custom_header_key("pallycon-customdata-v2").
                drm_policy_data_custom_header_value(multidrmToken).
                drm_policy_data_license_url("https://license.pallycon.com/ri/licenseManager.do").
                drm_policy_data_certificate_url("https://license.pallycon.com/ri/fpsKeyManager.do?siteId=" + siteid).
                build();
        vodTokenGenerator
                .secret_key(secret_key)
                .cuid("catenoid-test")
                .expt(1620880290)
                .mc(mc);

        System.out.println(UrlFactory.create(UrlType.VOD, StreamType.STREAMING, vodTokenGenerator, custom_key, null));


    }
}
