package com.kollus.util.encoder;

import com.kollus.util.exception.EncoderException;

public interface BinaryDecoder extends Decoder{
	byte[] decode(byte[] source) throws EncoderException;
}
