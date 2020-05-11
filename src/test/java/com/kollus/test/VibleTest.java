package com.kollus.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kollus.vod.JwtGenerator;
import com.kollus.vod.Mc;
import com.kollus.vod.McGenerator;
import com.kollus.multidrm.token.CustomDataGenerator;
import com.kollus.multidrm.token.TokenGenerator;
import com.kollus.multidrm.tokenrule.DrmTypes;
import com.kollus.multidrm.tokenrule.TokenRuleGenerator;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;


public class VibleTest {

    //인카 설정 값
    private static final String INKA_SITE_ID = "QF0B";
    // Site Key - https://console.pallycon.com/svc/setting.htm 확인 가능
    private static final String INKA_SITE_KEY = "6ask61ko92LMkPin5LlVgn0CSpz47edf";
    // Access Key - https://console.pallycon.com/svc/setting.htm 확인 가능
    private static final String INKA_ACCESS_KEY = "Xu3vAnEExlGYpbYu1zMUJB9os1ooX0SK";
    private static final String INKA_LICENSE_URL = "https://license.pallycon.com/ri/licenseManager.do";
    private static final String INKA_CERTIFICATE_URL = "https://license.pallycon.com/ri/fpsKeyManager.do?siteId=QF0B";
    private static final String INKA_CUSTOM_HEADER_NAME = "pallycon-customdata-v2";
    private static final int INKA_DURATION = 365 * 24 * 60 * 60;

    private static final String KOLLUS_MULTI_DRM_USER_KEY = "175bf8e549b415cb67ea0e61ab8e92c152b2b147f2091db629ef3aff8792c78d";
    private static final String KOLLUS_MULTI_DRM_SECRET_KEY = "kmds-vible";


    private static final String KOLLUS_KOLLUS_DRM_USER_KEY = "cd3264b6384f4aaa6826ebe61fa36afcb73f4ad106f7b1eff8323f95109f9ee3";
    private static final String KOLLUS_KOLLUS_DRM_SECRET_KEY = "comtrue";

    @Test
    public void createMultiDrmJwt() {
        String strTokenRule = "";
        String strToken = "";
        TokenRuleGenerator tokenRuleGenerator = new TokenRuleGenerator();
        TokenGenerator tokenGenerator = new TokenGenerator();

        CustomDataGenerator customDataGenerator = new CustomDataGenerator();

        McGenerator mcGenerator = new McGenerator();
        JwtGenerator jwtGenerator = new JwtGenerator();
        try {
            strTokenRule = tokenRuleGenerator
                    .site_key(INKA_SITE_KEY)
                    .limit(false)
                    .duration(INKA_DURATION)
                    .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        try {
            strToken = tokenGenerator
                    .drm_type(DrmTypes.FairPlay)
                    .site_id(INKA_SITE_ID)
                    .user_id("ABC")
                    .cid("20200423-m57d1dmh")
                    .token(strTokenRule)
                    .timestamp(new Date())
                    .hash(INKA_ACCESS_KEY)
                    .generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Mc mc = mcGenerator
                .mckey("EKRvryDo")
                .drm_policy_kind("inka")
                .drm_policy_streaming_type("hls") //hls or dash
                .drm_policy_data_license_url(INKA_LICENSE_URL)
                .drm_policy_data_certificate_url(INKA_CERTIFICATE_URL)//ios만 사용
                .drm_policy_data_custom_header_key(INKA_CUSTOM_HEADER_NAME)
                .drm_policy_data_custom_header_value(strToken)
                .build();
        String jwttoken = "";
        try {
             jwttoken = jwtGenerator.cuid("ABC")
                    .expt(2866957016l)
                    .secret_key(KOLLUS_MULTI_DRM_SECRET_KEY)
                    .mc(mc).generate();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        System.out.format("https://v.kr.kollus.com/si?jwt=%s&custom_key=%s\n", jwttoken, KOLLUS_MULTI_DRM_USER_KEY);
        System.out.println(strToken);
    }

    @Test
    public void createKollusDrmJwt() {
        String strTokenRule = "";
        String strToken = "";
        McGenerator mcGenerator = new McGenerator();
        JwtGenerator jwtGenerator = new JwtGenerator();
        Mc mc = mcGenerator
                .mckey("Rv2F3yVc")
                .build();
        String token = "";
        try {
            token = jwtGenerator.mc(mc)
                    .cuid("8149")
                    .expt(1593561750)
                    .secret_key(KOLLUS_KOLLUS_DRM_SECRET_KEY)
                    .generate();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        System.out.format("https://v.kr.kollus.com/s?jwt=%s&custom_key=%s", token, KOLLUS_KOLLUS_DRM_USER_KEY);
    }
}
