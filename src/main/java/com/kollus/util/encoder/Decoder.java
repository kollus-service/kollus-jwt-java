package com.kollus.util.encoder;

import com.kollus.util.exception.DecoderException;

public interface Decoder {
	Object decode(Object source) throws DecoderException;
}
