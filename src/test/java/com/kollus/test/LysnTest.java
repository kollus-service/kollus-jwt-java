package com.kollus.test;

import com.kollus.vod.JwtGenerator;
import com.kollus.vod.Mc;
import com.kollus.vod.McGenerator;
import com.kollus.multidrm.token.CustomDataGenerator;
import com.kollus.multidrm.token.TokenGenerator;
import com.kollus.multidrm.tokenrule.DrmTypes;
import com.kollus.multidrm.tokenrule.TokenRuleGenerator;

import java.util.Date;

public class LysnTest {
    private static final String INKA_SITE_KEY = "6ask61ko92LMkPin5LlVgn0CSpz47edf";
    private static final int INKA_DURATION = 365 * 24 * 60 * 60;
    private static final String INKA_SITE_ID = "QF0B";
    private static final String INKA_ACCESS_KEY = "Xu3vAnEExlGYpbYu1zMUJB9os1ooX0SK";

    private static final String INKA_LICENSE_URL = "https://license.pallycon.com/ri/licenseManager.do";
    private static final String INKA_CERTIFICATE_URL = "https://license.pallycon.com/ri/fpsKeyManager.do?siteId="+INKA_SITE_ID;
    private static final String INKA_CUSTOM_HEADER_NAME = "pallycon-customdata-v2";

    public static void main(String[] args) throws Exception {

        String strTokenRule = "";
        String strToken = "";
        TokenRuleGenerator tokenRuleGenerator = new TokenRuleGenerator();
        TokenGenerator tokenGenerator = new TokenGenerator();

        CustomDataGenerator customDataGenerator = new CustomDataGenerator();

        McGenerator mcGenerator = new McGenerator();
        JwtGenerator jwtGenerator = new JwtGenerator();
        strTokenRule = tokenRuleGenerator
                .site_key(INKA_SITE_KEY)
                .limit(false)
                .duration(INKA_DURATION)
                .build();

        strToken = tokenGenerator
                .drm_type(DrmTypes.FairPlay)
                .site_id(INKA_SITE_ID)
                .user_id("ABC")
                .cid("20200422-yp087m7x")
                .token(strTokenRule)
                .timestamp(new Date())
                .hash(INKA_ACCESS_KEY)
                .generate();
        Mc mc = mcGenerator
                .mckey("6YkLr4jQ")
                .drm_policy_kind("inka")
                .drm_policy_streaming_type("hls") //hls or dash
                .drm_policy_data_license_url(INKA_LICENSE_URL)
                .drm_policy_data_certificate_url(INKA_CERTIFICATE_URL)//ios만 사용
                .drm_policy_data_custom_header_key(INKA_CUSTOM_HEADER_NAME)
                .drm_policy_data_custom_header_value(strToken)
                .build();
        String jwttoken = jwtGenerator.cuid("taehoonkim@smtown.com")
                .expt(2866957016l)
                .secret_key("kmds-vible")
                .mc(mc).generate();

        System.out.format("URL: https://v.kr.kollus.com/s?jwt=%s&custom_key=%s\n", jwttoken, "47e5217fb8d4c2bb2407bd1a36621554de492801856715c8c648afdf175f130c");
        System.out.format("DRM Token: %s\n", strToken);
        System.out.format("DRN URL: https://license.pallycon.com/ri/licenseManager.do\n");
//        System.out.println(INKA_CERTIFICATE_URL);


    }
}
