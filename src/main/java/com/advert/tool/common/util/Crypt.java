package com.advert.tool.common.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// import javax.mail.internet.MimeUtility;

@SuppressWarnings("restriction")
public class Crypt {
	private static Log log = LogFactory.getLog(Crypt.class);
	private static String serial = "iongloba";

	private static String conserial = "";// "jgoal_annia";

	private static String Algorithm = "DES"; //

	// DES,DESede,Blowfish

	public static String StrEnCrypt(String para_Source) {
		String enStr = DESEnCryptA(para_Source, serial);
		enStr = CryptUnit.Byte2Hex(enStr);
		return enStr;
	}

	public static String StrEnCrypt(String para_Source, String key) {
		String enStr = DESEnCryptA(para_Source, key);
		enStr = CryptUnit.Byte2Hex(enStr);
		return enStr;
	}

	public static String StrDeCrypt(String para_Source) {

		String enStr = CryptUnit.Hex2Str(para_Source);
		enStr = DESDeCryptA(enStr, serial);
		return enStr;
	}

	public static String StrDeCrypt(String para_Source, String key) {

		String enStr = CryptUnit.Hex2Str(para_Source);
		enStr = DESDeCryptA(enStr, key);
		return enStr;
	}

	public static String DESEnCrypt(String para_Source) {
		return DESEnCryptA(para_Source, serial);
	}

	public static String DESEnCrypt(String para_Source, String key) {
		return DESEnCryptA(para_Source, key);
	}

	private static String DESEnCryptA(String para_Source, String curserial) {
		String enStr = null;
		byte result[] = null;
		if (para_Source == null)
			return null;
		if (para_Source.equalsIgnoreCase(""))
			return new String("");
		try {
			result = Secret_Encode(para_Source.getBytes(CryptUnit.CharSet), curserial.getBytes(), "DES");
			enStr = new String(result, CryptUnit.CharSet);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Secret_Encode() Error:" + e.getMessage());
		}

		return enStr;
	}

	public static String DESDeCrypt(String para_Source) {
		return DESDeCryptA(para_Source, serial);
	}

	public static String DESDeCrypt(String para_Source, String key) {
		return DESDeCryptA(para_Source, key);
	}

	private static String DESDeCryptA(String para_Source, String curserial) {
		String enStr = null;
		if (para_Source == null)
			return null;
		if (para_Source.equalsIgnoreCase(""))
			return new String("");
		byte result[];
		try {
			result = Secret_Decode(para_Source.getBytes(CryptUnit.CharSet), curserial.getBytes(), "DES");
			enStr = new String(result, CryptUnit.CharSet);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Secret_Encode() Error:" + e.getMessage());
		}
		return enStr;
	}

	public static String Blowfish_EnCrypt(String para_Source) {
		return Blowfish_EnCryptA(para_Source, serial);
	}

	public static String Blowfish_EnCrypt(String para_Source, String key) {
		return Blowfish_EnCryptA(para_Source, key);
	}

	private static String Blowfish_EnCryptA(String para_Source, String curserial) {
		String enStr = null;
		if (para_Source == null)
			return null;
		if (para_Source.equalsIgnoreCase(""))
			return new String("");
		try {
			byte result[] = Secret_Encode(para_Source.getBytes(CryptUnit.CharSet), curserial.getBytes(), "Blowfish");
			enStr = new String(result, CryptUnit.CharSet);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Secret_Encode() Error:" + e.getMessage());
		}
		return enStr;
	}

	public static String Blowfish_DeCrypt(String para_Source) {
		return Blowfish_DeCryptA(para_Source, serial);
	}

	public static String Blowfish_DeCrypt(String para_Source, String key) {
		return Blowfish_DeCryptA(para_Source, key);
	}

	private static String Blowfish_DeCryptA(String para_Source, String curserial) {
		String enStr = null;
		if (para_Source == null)
			return null;
		if (para_Source.equalsIgnoreCase(""))
			return new String("");
		try {
			byte result[] = Secret_Decode(para_Source.getBytes(CryptUnit.CharSet), curserial.getBytes(), "Blowfish");
			enStr = new String(result, CryptUnit.CharSet);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Secret_Encode() Error:" + e.getMessage());
		}
		return enStr;
	}

	public void FileEncrypt(String sourcefile, String destfile) {
		FileEncrypt(sourcefile, destfile, serial);
	}

	public void FileDecrypt(String sourcefile, String destfile) {
		FileEncrypt(sourcefile, destfile, serial);
	}

	public void FileEncrypt(String sourcefile, String destfile, String cryptstr) {
		FileEncrypt(sourcefile, destfile, cryptstr, 0);
	}

	public void FileDecrypt(String sourcefile, String destfile, String cryptstr) {
		FileEncrypt(sourcefile, destfile, cryptstr, 1);
	}

	private void FileEncrypt(String sourcefile, String destfile, String cryptstr, int mode) {
		try {
			DataInputStream dataIS = new DataInputStream(new FileInputStream(sourcefile));
			DataOutputStream dataOS = new DataOutputStream(new FileOutputStream(destfile));
			int data = dataIS.read();
			int serialInt = cryptstr.hashCode();
			for (; data != -1; data = dataIS.read()) {
				if (mode == 0)
					dataOS.write(data + serialInt);
				else
					dataOS.write(data - serialInt);
			}
			dataIS.close();
			dataOS.close();
		} catch (Exception e) {

		}
	}

	static {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
	}

	public static byte[] getKey() {
		SecretKey deskey = null;
		try {
			KeyGenerator keygen = KeyGenerator.getInstance(Algorithm);
			deskey = keygen.generateKey();
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Secret_Encode() Error:" + e.getMessage());
		}
		//logger.debug("Genrate Key:" + StringUnit.Byte2Hex(deskey.getEncoded()));
		return deskey.getEncoded();
	}

	private static byte[] Secret_Encode(byte[] input, byte[] curkey, String crypt_Type) {
		try {
			SecretKey deskey = new javax.crypto.spec.SecretKeySpec(curkey, crypt_Type);
			Cipher c1 = Cipher.getInstance(crypt_Type);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			byte[] DestByte = c1.doFinal(input);
			return DestByte;
		} catch (Exception E) {
			log.warn("Secret_Encode() Error:" + E.getMessage());
			return null;
		}
	}

	private static byte[] Secret_Decode(byte[] input, byte[] curkey, String crypt_Type) {
		try {
			SecretKey deskey = new javax.crypto.spec.SecretKeySpec(curkey, crypt_Type);
			Cipher c1 = Cipher.getInstance(crypt_Type);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			byte[] SourceByte = c1.doFinal(input);
			return SourceByte;
		} catch (Exception E) {
			log.warn("Secret_Encode() Error:" + E.getMessage());
			return null;
		}

	}

	public static String MD5(String para_Source) {
		String enStr = null;
		if (para_Source == null)
			return null;
		if (para_Source.equalsIgnoreCase(""))
			return new String("");
		try {
			byte result[] = MD5A(para_Source.getBytes(CryptUnit.CharSet));
			enStr = new String(result, CryptUnit.CharSet);
		} catch (Exception E) {
			return null;
		}
		enStr = CryptUnit.Byte2Hex(enStr).toUpperCase();
		return enStr;
	}

	private static byte[] MD5A(byte[] input) {
		byte[] digest = null;
		try {
			java.security.MessageDigest alg = java.security.MessageDigest.getInstance("MD5"); // or "SHA-1"
			alg.update(input);
			digest = alg.digest();
		} catch (Exception E) {
			return null;
		}
		return digest;
	}

	public static String getConSerial() {
		if (conserial == null)
			setConSerial();
		return conserial;
	}

	public static void setConSerial() {
		conserial = StrDeCrypt("23F27BA8780069AB5EC02761D3390786");
	}

	public static void main(String[] args) {
		String str = "888888";
		str = Crypt.StrEnCrypt(str);
		System.out.println(str);
		str = Crypt.StrDeCrypt("663320B2519F3957");
		System.out.println(str);
	}
}