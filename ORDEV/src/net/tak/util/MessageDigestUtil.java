package net.tak.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import sun.misc.BASE64Encoder;

public final class MessageDigestUtil {
	private static final int MAX_PWD_LENGTH = 10;

	private static final int MIN_PWD_LENGTH = 6;

	private static final String PWD_CHARS = "abcdefghjkmnpqrstuvwxyz23456789";

	private static final Random RANDOMIZER = new Random(System.currentTimeMillis());
	public static String encrypt(FileInputStream pFileInputStream)
			throws NoSuchAlgorithmException, IOException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] buffer = new byte[pFileInputStream.available()];
		pFileInputStream.read(buffer);
		md.update(buffer);
		byte[] raw = md.digest();
		BigInteger bigInt = new BigInteger(1, raw);
		String cipher = bigInt.toString(16);
		return cipher;
	}
	public static String encrypt(String plaintext)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA");
		md.update(plaintext.getBytes("UTF-8"));
		byte raw[] = md.digest();
		String cipher = (new BASE64Encoder()).encode(raw);
		return cipher;
	}
	public static String genPassword() {
		StringBuffer lvPwdBuffer = new StringBuffer();
		int pwdLength = RANDOMIZER.nextInt(MAX_PWD_LENGTH - MIN_PWD_LENGTH + 1)
				+ MIN_PWD_LENGTH;
		System.err.println(pwdLength);
		for (int i = 0; i < pwdLength; i++)
			lvPwdBuffer.append(PWD_CHARS.charAt(RANDOMIZER.nextInt(PWD_CHARS.length())));
		return lvPwdBuffer.toString();
	}

	public static void main(String[] args) throws NoSuchAlgorithmException,	UnsupportedEncodingException {
		System.out.println(MessageDigestUtil.encrypt("12345"));
		
		System.out.println(MessageDigestUtil.encrypt(MessageDigestUtil.genPassword()));
	}

	public MessageDigestUtil() {
	}

}
