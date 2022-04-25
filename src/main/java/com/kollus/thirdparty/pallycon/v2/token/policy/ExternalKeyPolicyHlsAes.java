package com.kollus.thirdparty.pallycon.v2.token.policy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kollus.thirdparty.pallycon.v2.token.policy.common.TrackType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"track_type", "key", "iv"})
public class ExternalKeyPolicyHlsAes {

    @JsonProperty("track_type")
    private TrackType trackType;
    @JsonProperty("key")
    private String key;
    @JsonProperty("iv")
    private String iv;

    public ExternalKeyPolicyHlsAes(TrackType trackType, String key, String iv) {
        this.trackType = trackType;
        this.key = key;
        this.iv = iv;
    }

    public String getTrackType() {
        return trackType.getValue();
    }

    public String getKey() {
        return key;
    }

    public String getIv() {
        return iv;
    }
}
