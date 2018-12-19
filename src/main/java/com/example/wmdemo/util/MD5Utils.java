package com.example.wmdemo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具
 * 
 * @author hechunyang
 *
 */
public class MD5Utils {

	/**
	 * 
	 * @param msg
	 * @return
	 */
	public static String getMD5Token16(String msg) {
		return getMD5Token32(msg).substring(8, 24);
	}

	/**
	 * 
	 * @param msg
	 * @return
	 */
	public static String getMD5Token32(String msg) {
		try {
			// digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符   
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(msg.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

}