package com.kollus.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kollus.thirdparty.pallycon.v1.policy.TokenRuleGenerator;
import com.kollus.thirdparty.pallycon.v1.token.TokenGenerator;
import com.kollus.url.StreamType;
import com.kollus.url.UrlFactory;
import com.kollus.url.UrlType;
import com.kollus.vod.VodTokenGenerator;
import com.kollus.vod.mediacontent.Mc;
import com.kollus.vod.mediacontent.McGenerator;
import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class KollusVodUrlTest {
    @Test
    public void createUrl(){
        String secret_key = "catenoid-sample";
        String custom_key = "05a467c813c51f519d515f08ae52c190163650abbb84ed8feef6f4449fc6dd36";
        VodTokenGenerator vodTokenGenerator = new VodTokenGenerator();
        McGenerator mcGenerator = new McGenerator();
        Mc mc = mcGenerator.
                mckey("3af0hJI4").
                build();
        vodTokenGenerator
                .secret_key(secret_key)
                .cuid("catenoid-test")
                .expt(1620880290)
                .mc(mc);

        String playUrl = "";
        try {
            playUrl  = UrlFactory.create(UrlType.VOD, StreamType.STREAMING, vodTokenGenerator, custom_key, null);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
