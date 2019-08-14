package com.opsigte.e.common.core.security;

import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.xml.bind.DatatypeConverter;


/**
 * <p> @ClassName: <i>PasswordManage</i></p>
 * <p> @Description: <i>密码寄存器</i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/8/14 11:12</i></p>
 * <p> @Version: <i>V1.0</i> </p>
 */
public class PasswordStorage {
	@SuppressWarnings("serial")
	static public class InvalidHashException extends Exception {
		public InvalidHashException(String message) {
			super(message);
		}

		public InvalidHashException(String message, Throwable source) {
			super(message, source);
		}
	}

	@SuppressWarnings("serial")
	static public class CannotPerformOperationException extends Exception {
		public CannotPerformOperationException(String message) {
			super(message);
		}

		public CannotPerformOperationException(String message, Throwable source) {
			super(message, source);
		}
	}

	public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

	// 可以在不破坏现有Hash的情况下更改这些常量
	public static final int SALT_BYTE_SIZE = 24;
	public static final int HASH_BYTE_SIZE = 18;
	public static final int PBKDF2_ITERATIONS = 64000;

	// 这些常量定义了编码，可能不会更改
	public static final int HASH_SECTIONS = 5;
	public static final int HASH_ALGORITHM_INDEX = 0;
	public static final int ITERATION_INDEX = 1;
	public static final int HASH_SIZE_INDEX = 2;
	public static final int SALT_INDEX = 3;
	public static final int PBKDF2_INDEX = 4;

	public static String createHash(String password) throws CannotPerformOperationException {
		return createHash(password.toCharArray());
	}

	public static String createHash(char[] password) throws CannotPerformOperationException {
		// 生成随机盐
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[SALT_BYTE_SIZE];
		random.nextBytes(salt);

		// Hash密码
		byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
		int hashSize = hash.length;

		// format: algorithm:iterations:hashSize:salt:hash
		String parts = "sha1:" + PBKDF2_ITERATIONS + ":" + hashSize + ":" + toBase64(salt) + ":" + toBase64(hash);
		return parts;
	}

	public static boolean verifyPassword(String password, String correctHash)
			throws CannotPerformOperationException, InvalidHashException {
		return verifyPassword(password.toCharArray(), correctHash);
	}

	public static boolean verifyPassword(char[] password, String correctHash)
			throws CannotPerformOperationException, InvalidHashException {
		// 将Hash解码为其参数
		String[] params = correctHash.split(":");
		if (params.length != HASH_SECTIONS) {
			throw new InvalidHashException("Fields are missing from the password hash.");
		}

		// 目前，Java只支持SHA1
		if (!params[HASH_ALGORITHM_INDEX].equals("sha1")) {
			throw new CannotPerformOperationException("Unsupported hash type.");
		}

		int iterations = 0;
		try {
			iterations = Integer.parseInt(params[ITERATION_INDEX]);
		} catch (NumberFormatException ex) {
			throw new InvalidHashException("Could not parse the iteration count as an integer.", ex);
		}

		if (iterations < 1) {
			throw new InvalidHashException("Invalid number of iterations. Must be >= 1.");
		}

		byte[] salt = null;
		try {
			salt = fromBase64(params[SALT_INDEX]);
		} catch (IllegalArgumentException ex) {
			throw new InvalidHashException("Base64 decoding of salt failed.", ex);
		}

		byte[] hash = null;
		try {
			hash = fromBase64(params[PBKDF2_INDEX]);
		} catch (IllegalArgumentException ex) {
			throw new InvalidHashException("Base64 decoding of pbkdf2 output failed.", ex);
		}

		int storedHashSize = 0;
		try {
			storedHashSize = Integer.parseInt(params[HASH_SIZE_INDEX]);
		} catch (NumberFormatException ex) {
			throw new InvalidHashException("Could not parse the hash size as an integer.", ex);
		}

		if (storedHashSize != hash.length) {
			throw new InvalidHashException("Hash length doesn't match stored hash length.");
		}

		/**
		 * 计算提供的密码的Hash，使用相同的盐，
		 * 迭代计数和Hash长度
		 */
		byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
		/**
		 * 比较固定时间的Hash。如果两个匹配，密码是正确的。
		 */
		return slowEquals(hash, testHash);
	}

	private static boolean slowEquals(byte[] a, byte[] b) {
		int diff = a.length ^ b.length;
		for (int i = 0; i < a.length && i < b.length; i++)
			diff |= a[i] ^ b[i];
		return diff == 0;
	}

	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
			throws CannotPerformOperationException {
		try {
			PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
			return skf.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException ex) {
			throw new CannotPerformOperationException("Hash algorithm not supported.", ex);
		} catch (InvalidKeySpecException ex) {
			throw new CannotPerformOperationException("Invalid key spec.", ex);
		}
	}

	private static byte[] fromBase64(String hex) throws IllegalArgumentException {
		return DatatypeConverter.parseBase64Binary(hex);
	}

	private static String toBase64(byte[] array) {
		return DatatypeConverter.printBase64Binary(array);
	}
}
