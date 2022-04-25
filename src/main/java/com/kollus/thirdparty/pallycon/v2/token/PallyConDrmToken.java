package com.kollus.thirdparty.pallycon.v2.token;

import com.kollus.thirdparty.pallycon.v2.exception.PallyConTokenException;

public interface PallyConDrmToken {

    String getDrmType();

    String getSiteId();

    String getUserId();

    String getCId();

    String getPolicy();

    String getSiteKey();

    String getAccessKey();

    String toJsonString() throws PallyConTokenException;

}
