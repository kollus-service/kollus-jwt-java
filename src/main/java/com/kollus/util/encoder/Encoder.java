package com.kollus.util.encoder;

import com.kollus.util.exception.EncoderException;

public interface Encoder {
	Object encode(Object source) throws EncoderException;
}
