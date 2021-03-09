package com.precompiler.utils;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author Richard Li
 */
public class EncoderUtils {
    public static byte[] encodeUTF16LEWithBOM(String s) {
        ByteBuffer content = StandardCharsets.UTF_16LE.encode(s);
        byte[] bom = { (byte) 0xff, (byte) 0xfe };
        return ByteBuffer.allocate(content.capacity() + bom.length).put(bom).put(content).array();
    }
    private EncoderUtils() {}
}
