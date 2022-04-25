package com.kollus.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kollus.thirdparty.pallycon.v1.policy.DrmTypes;
import com.kollus.thirdparty.pallycon.v1.policy.TokenRuleGenerator;
import com.kollus.thirdparty.pallycon.v1.token.TokenGenerator;
import com.kollus.thirdparty.pallycon.v2.exception.PallyConTokenException;
import com.kollus.thirdparty.pallycon.v2.token.PallyConDrmTokenClient;
import com.kollus.thirdparty.pallycon.v2.token.PallyConDrmTokenPolicy;
import com.kollus.thirdparty.pallycon.v2.token.policy.*;
import com.kollus.thirdparty.pallycon.v2.token.policy.common.ResponseFormat;
import com.kollus.thirdparty.pallycon.v2.token.policy.common.TrackType;
import com.kollus.thirdparty.pallycon.v2.token.policy.playbackPolicy.AllowedTrackTypes;
import com.kollus.thirdparty.pallycon.v2.token.policy.securityPolicy.fairplay.FairplayHdcpEnforcement;
import com.kollus.thirdparty.pallycon.v2.token.policy.securityPolicy.playready.AnalogVideoProtection;
import com.kollus.thirdparty.pallycon.v2.token.policy.securityPolicy.playready.DigitalAudioProtection;
import com.kollus.thirdparty.pallycon.v2.token.policy.securityPolicy.playready.DigitalVideoProtection;
import com.kollus.thirdparty.pallycon.v2.token.policy.securityPolicy.playready.PlayreadySecurityLevel;
import com.kollus.thirdparty.pallycon.v2.token.policy.securityPolicy.widevine.RequiredCgmsFlags;
import com.kollus.thirdparty.pallycon.v2.token.policy.securityPolicy.widevine.RequiredHdcpVersion;
import com.kollus.thirdparty.pallycon.v2.token.policy.securityPolicy.widevine.WidevineSecurityLevel;
import com.kollus.url.StreamType;
import com.kollus.url.UrlFactory;
import com.kollus.url.UrlType;
import com.kollus.vod.VodTokenGenerator;
import com.kollus.vod.mediacontent.Mc;
import com.kollus.vod.mediacontent.McGenerator;
import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

public class MultiDrmUrlTest {
    @Test
    public void createUrl(){



        String secret_key = "multi-drm";
        String custom_key = "259b191c87748e77834fae4d83502ba6e7fcdc3677f9bc54a4646a40a2d3929d";
        String siteid = "4ZMG";
        String sitekey = "7wM3lblTudAZnMezpKTmst2l3HEmLtBT";
        String accesskey = "TtSGajCoNO9s9hrVW106Iz6f9CbfuB97";
        VodTokenGenerator vodTokenGenerator = new VodTokenGenerator();
        McGenerator mcGenerator = new McGenerator();


        PallyConDrmTokenPolicy policy = null;
        PallyConDrmTokenClient token = null;



        PlaybackPolicy playbackPolicy = new PlaybackPolicy();
        SecurityPolicy securityPolicy = new SecurityPolicy();
        ExternalKeyPolicy externalKeyPolicy = new ExternalKeyPolicy();

        //setup playbackPolicy
        playbackPolicy
                .allowedTrackTypes(AllowedTrackTypes.SD_HD)
                .persistent(false);


        SecurityPolicyWidevine widevineForAll = new SecurityPolicyWidevine()
                .securityLevel(WidevineSecurityLevel.SW_SECURE_CRYPTO)
                .requiredHdcpVersion(RequiredHdcpVersion.HDCP_NONE)
                .requiredCgmsFlags(RequiredCgmsFlags.CGMS_NONE)
                .overrideDeviceRevocation(true);
        SecurityPolicyFairplay fairplayForAll = new SecurityPolicyFairplay()
                .hdcpEnforcement(FairplayHdcpEnforcement.HDCP_NONE)
                .allowAvAdapter(true)
                .allowAirplay(true);
        SecurityPolicyPlayready playreadyForAll = new SecurityPolicyPlayready()
                .securityLevel(PlayreadySecurityLevel.LEVEL_150)
                .analogVideoProtection(AnalogVideoProtection.LEVEL_100)
                .digitalVideoProtection(DigitalVideoProtection.LEVEL_100)
                .digitalAudioProtection(DigitalAudioProtection.LEVEL_100);
        //constructs subpolicies for SecurityPolicy
        securityPolicy
                .widevine(widevineForAll)
                .fairplay(fairplayForAll)
                .playready(playreadyForAll)
                .trackType(TrackType.ALL);

        // setup ExternalKeyPolicy
        ExternalKeyPolicyMpegCenc mpegCenc = new ExternalKeyPolicyMpegCenc(
                TrackType.ALL_VIDEO, "<Key ID>", "<Key>");
        externalKeyPolicy.mpegCenc(Arrays.asList(mpegCenc, mpegCenc, mpegCenc));

        String licenseToken;
        try {
            policy = new PallyConDrmTokenPolicy
                    .PolicyBuilder()
                    .playbackPolicy(playbackPolicy)
                    .externalKey(externalKeyPolicy)
                    .securityPolicy(securityPolicy)
                    .build();

            /**
             * 3. create token
             * */
            token = new PallyConDrmTokenClient()
                    .widevine()
                    .siteId("<Site ID>")
                    .siteKey("<Site Key>")
                    .accessKey("<Access Key>")
                    .userId("<tester-user>")
                    .cId("<Content ID>")
                    .policy(policy)
                    .responseFormat(ResponseFormat.ORIGINAL);

            licenseToken = token.execute();

        } catch (PallyConTokenException e) {
            licenseToken = e.getMessage();
        } catch (Exception e) {
            licenseToken = "unexpected Exception || " + e.getMessage();
        }

        Mc mc = mcGenerator.
                mckey("ujUBIU3I").
                drm_policy_kind("inka").
                drm_policy_streaming_type("dash").
                drm_policy_data_custom_header_key("pallycon-customdata-v2").
                drm_policy_data_custom_header_value(licenseToken).
                drm_policy_data_license_url("https://license.pallycon.com/ri/licenseManager.do").
                drm_policy_data_certificate_url("https://license.pallycon.com/ri/fpsKeyManager.do?siteId=" + siteid).
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
