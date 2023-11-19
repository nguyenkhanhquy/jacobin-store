package com.jacobin.utils;

import java.security.MessageDigest;
import org.apache.commons.codec.binary.Base64;

public class PasswordEncryptorUtil {

	public static String toSHA1(String str) {
		// Làm cho mật khẩu phức tạp
		String salt = "asjrlkmcoewj@tjle;oxqskjhdjksjf1jurVn";
		String result = null;
		
		str = str + salt;
		try {
			byte[] dataBytes = str.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			result = Base64.encodeBase64String(md.digest(dataBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
