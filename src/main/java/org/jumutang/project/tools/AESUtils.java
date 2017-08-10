package org.jumutang.project.tools;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.http.ParseException;
import org.apache.http.util.TextUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AESUtils {
	/**
	 * 加密
	 *
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	// private static String Key = "1234567890abcdef";

	// private static byte[] _key1 = { 0x12, 0x34, 0x56, 0x78, (byte) 0x90,
	// (byte) 0xAB, (byte) 0xCD, (byte) 0xEF, 0x12, 0x34, 0x56, 0x78,
	// (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };

	private static String _key1 = "L%n67}G/Mk@k%:~Y";

//	private static String key_paddiing_type = "AES/CBC/PKCS7Padding";
	private static String key_paddiing_type = "AES/CBC/PKCS5Padding";

	public static String encode(String stringToEncode, String key)
			throws NullPointerException {

		try {
			SecretKeySpec skeySpec = getKey(key);
			byte[] clearText = stringToEncode.getBytes("UTF8");
			IvParameterSpec ivParameterSpec = new IvParameterSpec(
					_key1.getBytes());
			byte[] iv = ivParameterSpec.getIV();
//			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance(key_paddiing_type);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
			// String encrypedValue = Base64.encodeToString(
			// cipher.doFinal(clearText), Base64.DEFAULT);

			String encrypedValue = Base64.encode(cipher.doFinal(clearText));

			return encrypedValue;

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * AES解密
	 *
	 * @param encryptBytes
	 *            待解密的byte[]
	 * @param key
	 *            解密密钥
	 * @return 解密后的String
	 * @throws Exception
	 *             08-10 09:24:04.796: I/(19189):
	 *             =========encode=====GzO419AM8euZzQf+Ot7Ecw==
	 */
	public static String aesDecryptByBytes(String encryptBytes, String key)
			throws Exception {
		SecretKeySpec skeySpec = getKey(key);
		byte[] bytes = Base64.decode(encryptBytes);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(_key1.getBytes());
//		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		Cipher cipher = Cipher.getInstance(key_paddiing_type);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
		String decryptString = new String(cipher.doFinal(bytes), "UTF-8")
				.trim();
		return decryptString;
	}

	private static SecretKeySpec getKey(String key) {
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
		return skeySpec;
	}

	/**
	 * 字符串中取数字
	 *
	 * @param content
	 * @return
	 */
	public static List<String> getNumbers(String content) {
		List<String> digitList = new ArrayList<String>();
		Pattern p = Pattern.compile("[^0-9]");
		Matcher m = p.matcher(content);
		String result = m.replaceAll("");
		for (int i = 0; i < result.length(); i++) {
			digitList.add(result.substring(i, i + 1));

		}
		return digitList;
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public static String getKeyContent(String key) {
		if (key != null) {
			if (key.length() < 16) {
				StringBuffer sBuffer = new StringBuffer();
				sBuffer.append(key);
				for (int i = 0; i < 16; i++) {
					sBuffer.append("0");
					if (sBuffer.length() == 16) {
						return sBuffer.toString().trim();
					}
				}
			} else if (key.length() > 16) {
				key = key.substring(0, 16);
			} else {
				return key;
			}
		}
		return key;
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(smdate));
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time1 = cal.getTimeInMillis();
		try {
			cal.setTime(sdf.parse(bdate));
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 判断加密方式
	 *
	 * @param content
	 *            要加密的内容
	 * @param eType
	 *            0 都不加密,1都加密,2服务器加 ,3 客户端加
	 * @param isEncryption
	 *            true 加密 false解密
	 *
	 * @return
	 */
	/*public static String encryptionType(String content, String eType,
										Boolean isEncryption) {
		String returnString = "";
		if (TextUtils.isEmpty(eType)) {
			return content;
		} else {
			if (eType.equals("0")) {
				// 0 都不加密
				return content;
			} else if (eType.equals("1")) {
				// 1都加密
				if (isEncryption) {
					returnString = AESUtils.encode(content, PropertiesUtil.props.getProperty("aeskey"));
					return returnString;
				} else {
					try {
						returnString = AESUtils.aesDecryptByBytes(content,
								PropertiesUtil.props.getProperty("aeskey"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return returnString;
				}

			} else if (eType.equals("2")) {
				// 2服务器加
				if (isEncryption) {
					return content;
				} else {
					try {
						returnString = AESUtils.aesDecryptByBytes(content,
								PropertiesUtil.props.getProperty("aeskey"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return returnString;
				}
			} else if (eType.equals("3")) {
				// 3 客户端加
				if (isEncryption) {
					returnString = AESUtils.encode(content, PropertiesUtil.props.getProperty("aeskey"));
					return returnString;
				} else {
					return content;
				}
			}
		}

		return returnString;
	}*/

	/**将二进制转换成16进制
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**将16进制转换为二进制
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length()/2];
		for (int i = 0;i< hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static String toSignString(Map<String,String> map,String key){
		Map<String,String> sortedMap = new TreeMap<>(map);
		StringBuffer retStr = new StringBuffer();
		for(String keyStr:sortedMap.keySet()){
			retStr.append(keyStr+"="+sortedMap.get(keyStr)+"&");
		}
		retStr.append("key="+key);
		return MD5Util.md5Encodenew(new String(retStr)).toUpperCase();
	}




	public static void main(String[] args) throws Exception{
		//System.out.println(URLEncoder.encode(AESUtils.encode("18399","ws57x644g34k5e56"),"utf-8"));
		//System.out.println(AESUtils.aesDecryptByBytes(URLDecoder.decode("e1jMvLT4I8vtHzoiR6ZSDA%3D%3D","utf-8"),"ws57x644g34k5e56"));

		/*Map<String,String> sortMap = new TreeMap<>();
		sortMap.put("jfuserid","e1jMvLT4I8vtHzoiR6ZSDA%3D%3D");
		sortMap.put("usercode","xKh0SRv%2BP9oj21lylN%2FbJQ%3D%3D");
		sortMap.put("timestamp","olwuNL1OPvBf0H8Z1A1oQq9BuOyR64TeR2vHP%2FapN0k%3D");
		Iterator iterator = sortMap.keySet().iterator();
		while(iterator.hasNext()){
			System.out.println(sortMap.get(iterator.next().toString()));
		}*/
		/*Map<String,String> map = new HashMap<>();
		map.put("jfuserid","e1jMvLT4I8vtHzoiR6ZSDA%3D%3D");
		map.put("usercode","xKh0SRv%2BP9oj21lylN%2FbJQ%3D%3D");
		map.put("timestamp","olwuNL1OPvBf0H8Z1A1oQq9BuOyR64TeR2vHP%2FapN0k%3D");
		System.out.println(AESUtils.toSignString(map,"1e22953212ed4c553w37r2e6saf2e121"));*/
	}
}