package com.zx.whm.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-2-6
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public abstract class DESUtil {
	/** 加密算法,可用 DES,DESede,Blowfish. */
	private final static String ALGORITHM = "DES";

	/**
	 * 用指定的key对数据进行DES加密.
	 *
	 * @param data
	 *            待加密的数据
	 * @param key
	 *            DES加密的key, 必须要不少于8位
	 * @return 返回DES加密后的数据
	 * @throws Exception
	 */
	public static String encrypt(String data, String key) throws Exception {
		key = paddingOne(key,8);
		byte[] dt = data.getBytes("UTF-8");
		byte[] ky = key.getBytes("UTF-8");
		byte[] res = encrypt(dt, ky);
		BASE64Encoder base64encoder = new BASE64Encoder();
		String encode = base64encoder.encode(res).replace("\n", "").replace("\r", "");
		return encode;
	}

	/**
	 * 用指定的key对数据进行DES解密.
	 *
	 * @param data
	 *            待解密的数据
	 * @param key
	 *            DES解密的key, 必须要不少于8位
	 * @return 返回DES解密后的数据
	 * @throws Exception
	 */
	public static String decrypt(String data, String key) throws Exception {
		key = paddingOne(key,8);
		BASE64Decoder base64decoder = new BASE64Decoder();
		byte[] encodeByte = base64decoder.decodeBuffer(data);
		byte[] ky = key.getBytes("UTF-8");
		byte[] res = decrypt(encodeByte, ky);
		return new String(res, "UTF-8");
	}

	/**
	 * 用指定的key对数据进行DES加密.
	 *
	 * @param data
	 *            待加密的数据
	 * @param key
	 *            DES加密的key
	 * @return 返回DES加密后的数据
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(data);
	}

	/** */
	/**
	 * 用指定的key对数据进行DES解密.
	 *
	 * @param data
	 *            待解密的数据
	 * @param key
	 *            DES解密的key
	 * @return 返回DES解密后的数据
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(data);
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		String s = DESUtil.encode("4166666666666666660010C2AAD9CF3B864072A637080A2AA564B8000000000000000000000000000000000000000000000000000000000000000000");
		System.out.println(s);
        System.out.println();
        String rst = DESUtil.decode(s);
		System.out.println(rst);
	}

	public static String paddingOne(String key,int length){
		StringBuffer returnValue = new StringBuffer(key);
		while (returnValue.length() < length)
			returnValue.append("0");
		return returnValue.toString();
	}


	public static String encode(String data) throws Exception {
		BASE64Encoder base64encoder = new BASE64Encoder();
		String encode = base64encoder.encode(data.getBytes("UTF-8")).replace("\r","").replace("\n","");
		return encode;
	}

	public static String decode(String data) throws Exception {
		BASE64Decoder base64Decoder = new BASE64Decoder();
		String decode = new String(base64Decoder.decodeBuffer(data),"UTF-8");
		return decode;
	}

}
