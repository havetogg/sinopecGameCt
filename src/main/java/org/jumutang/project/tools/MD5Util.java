package org.jumutang.project.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具
 * @author YuanYu
 *
 */
public final class MD5Util {
	
	private MD5Util(){
	}
	
	private static final String getMD5(String sourceStr){
		String result = "";
		try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes("UTF-8"));
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
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 16位大写md5摘要
	 * @param sourceStr
	 * @return
	 */
	public static final String getUpperCaseMD5For16(String sourceStr){
		String result = getMD5(sourceStr);
		return result.substring(8, 24).toUpperCase();
	}
	/**
	 * 32位大写md5摘要
	 * @param sourceStr
	 * @return
	 */
	public static final String getUpperCaseMD5For32(String sourceStr){
		String result = getMD5(sourceStr);
		return result.toUpperCase();
	}
	/**
	 * 16位小写md5摘要
	 * @param sourceStr
	 * @return
	 */
	public static final String getLowerCaseMD5For16(String sourceStr){
		String result = getMD5(sourceStr);
		return result.substring(8, 24).toLowerCase();
	}
	/**
	 * 32位小写md5摘要
	 * @param sourceStr
	 * @return
	 */
	public static final String getLowerCaseMD5For32(String sourceStr){
		String result = getMD5(sourceStr);
		return result.toLowerCase();
	}

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String md5Encodenew(String sourceStr){
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}

		byte[] byteArray = null;
		try {
			byteArray = sourceStr.getBytes("UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
}
