package com.kollus.util.encoder;

import com.kollus.util.exception.EncoderException;

public interface BinaryEncoder extends Encoder{
	byte[] encode(byte[] source) throws EncoderException;
}
