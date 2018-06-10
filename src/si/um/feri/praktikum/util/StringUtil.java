package si.um.feri.praktikum.util;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StringUtil {
	private static final Logger LOGGER = LoggerUtil.getDefaultLogger(StringUtil.class.getName());

	/**
	 * 
	 * @param input
	 *            Input string is going to be hashed with SHA-256 algorithm.
	 * @return Output is string hashed with SHA-256.
	 */
	public static String hashSHA256(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(input.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			return null;
		}
	}

	/**
	 * 
	 * @param privateKey
	 *            User's private key.
	 * @param input
	 *            String which is going to be encrypted or we get user signature.
	 * @return Encrypted string.
	 */
	public static byte[] applyDSASig(PrivateKey privateKey, String input) {
		Signature dsa;
		byte[] output = new byte[0];
		try {
			dsa = Signature.getInstance("DSA", "SUN");
			dsa.initSign(privateKey);
			byte[] strByte = input.getBytes();
			dsa.update(strByte);
			output = dsa.sign();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
		return output;
	}

	/**
	 * 
	 * @param publicKey
	 *            User's publickey.
	 * @param data
	 *            String data that wants to be compared with signature.
	 * @param signature
	 *            Encrypted data
	 * @return Boolean if signatures match return true otherwise false.
	 * @throws RuntimeException
	 *             If exception occurs when getting instance or while verifying,
	 *             RuntimeException will be thrown and error will be logged.
	 */
	public static boolean verifyDSASig(PublicKey publicKey, String data, byte[] signature) throws RuntimeException {
		try {
			Signature dsaVerify = Signature.getInstance("DSA", "SUN");
			dsaVerify.initVerify(publicKey);
			dsaVerify.update(data.getBytes());
			return dsaVerify.verify(signature);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			throw new RuntimeException();
		}
	}

	/**
	 * 
	 * @param key
	 *            User's key.
	 * @return String value of key.
	 */
	public static String getStringFromKey(Key key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}
}