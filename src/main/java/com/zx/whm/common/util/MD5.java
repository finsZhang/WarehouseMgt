package com.zx.whm.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5算法
 * @author fangll
 *
 */
public final class MD5 {
    private static final Logger LOG = LoggerFactory.getLogger(MD5.class);
    private static final MessageDigest MD = getDigest();

    private MD5() {
    }

    private static MessageDigest getDigest() {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Can not get MD5 digester!", e);
        }
        return md;
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(
                Integer.toHexString(0x0100 + (b[i] & 0x00ff)).substring(1)
            );
        }
        return resultSb.toString();
    }

    public static String toMD5(String str) {
        if (str == null) {
            return null;
        }
        return byteArrayToHexString(MD.digest(str.getBytes()));
    }
   
    public static void main(String[] args) {
		System.out.println(MD5.toMD5("1"));
	}
    
}
