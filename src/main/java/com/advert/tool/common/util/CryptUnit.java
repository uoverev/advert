package com.advert.tool.common.util;

public class CryptUnit {

	public static String CharSet = "ISO-8859-1";

	public static String Byte2Hex(String sourcestr) {
		String enStr = "";
		try {
			enStr = Byte2HexA(sourcestr.getBytes(CharSet));
		} catch (Exception E) {
			E.printStackTrace();
		}
		return enStr;
	}

	public static String Byte2Hex(byte[] b) {
		return Byte2HexA(b);
	}

	private static String Byte2HexA(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static String Byte2Hex(byte inByte) {
		char[] tmpCharArr = Num2Base(inByte, "byte", 16);
		String hexStr = (new String(tmpCharArr)).toUpperCase();
		return (hexStr);
	}

	public static String Word2Hex(short inWord) {
		char[] tmpCharArr = Num2Base(inWord, "word", 16);
		String hexStr = (new String(tmpCharArr)).toUpperCase();
		return (hexStr);
	}

	public static String Long2Hex(int inLong) {
		char[] tmpCharArr = Num2Base(inLong, "long", 16);
		String hexStr = (new String(tmpCharArr)).toUpperCase();
		return (hexStr);
	}

	public static String Hex2Str(String strHex) {
		String str = "";
		byte n[] = new byte[strHex.length() / 2];
		for (int i = 0; i < strHex.length() / 2; i++) {
			String curstr = strHex.substring(i * 2, i * 2 + 2);
			n[i] = Hex2Byte(curstr);
		}
		try {
			str = new String(n, CharSet);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return str;
	}

	public static char Hex2Char(String strHex) {
		return (char) Hex2Byte(strHex);
	}

	public static byte Hex2Byte(String strHex) {
		return ((byte) Base2Num(strHex.toCharArray(), "byte", 16));
	}
	public static byte[] Hex2ByteArray(String hexStr) {  
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

	public static short Hex2Word(String strHex) {
		return ((short) Base2Num(strHex.toCharArray(), "word", 16));
	}

	public static int Hex2Long(String strHex) {
		return (Base2Num(strHex.toCharArray(), "long", 16));
	}

	public static byte GetBitsB(byte inByte, int firstBit, int lastBit) {
		char[] binByte = Num2Base(inByte, "byte", 2);
		String binStr = new String(binByte);
		String subStr = binStr.substring(7 - lastBit, 7 - firstBit + 1);
		return ((byte) Base2Num(subStr.toCharArray(), "byte", 2));
	}

	public static byte SetBitsB(byte varByte, int first, int last, byte tmpBits) {
		char[] binVarByte = Num2Base(varByte, "byte", 2);
		char[] binTmpBits = Num2Base(tmpBits, "byte", 2);
		for (int index = 0; index <= last - first; index++) {
			binVarByte[7 - first - index] = binTmpBits[7 - index];
		}
		return ((byte) Base2Num(binVarByte, "byte", 2));
	}

	public static short GetBitsW(short inWord, int firstBit, int lastBit) {
		char[] binWord = Num2Base(inWord, "word", 2);
		String binStr = new String(binWord);
		String subStr = binStr.substring(15 - lastBit, 15 - firstBit + 1);
		return ((short) Base2Num(subStr.toCharArray(), "word", 2));
	}

	public static byte GetByteW(short inWord, boolean MSB) {
		char[] binWord = Num2Base(inWord, "word", 2);
		String binStrWord = new String(binWord);
		String binStrByte;
		if (MSB) {
			binStrByte = binStrWord.substring(0, 8);
		} else {
			binStrByte = binStrWord.substring(8, 16);
		}
		return ((byte) Base2Num(binStrByte.toCharArray(), "byte", 2));
	}

	public static short SetBitsW(short varWord, int first, int last,
			short tmpBits) {
		char[] binVarWord = Num2Base(varWord, "word", 2);
		char[] binTmpBits = Num2Base(tmpBits, "word", 2);
		for (int index = 0; index <= last - first; index++) {
			binVarWord[15 - first - index] = binTmpBits[15 - index];
		}
		return ((short) Base2Num(binVarWord, "word", 2));
	}

	public static short SetByteW(short varWord, boolean MSB, byte tmpByte) {
		char[] binVarWord = Num2Base(varWord, "word", 2);
		char[] binTmpByte = Num2Base(tmpByte, "byte", 2);
		int offset = 0;
		if (!MSB) {
			offset = 8;
		}
		for (int index = 0; index <= 7; index++) {
			binVarWord[index + offset] = binTmpByte[index];
		}
		return ((short) Base2Num(binVarWord, "word", 2));
	}

	public static int GetBitsL(int inLong, int firstBit, int lastBit) {
		char[] binLong = Num2Base(inLong, "long", 2);
		String binStr = new String(binLong);
		String subStr = binStr.substring(31 - lastBit, 31 - firstBit + 1);
		return (Base2Num(subStr.toCharArray(), "long", 2));
	}

	public static byte GetByteL(int inLong, int pos) {
		char[] binLong = Num2Base(inLong, "long", 2);
		String binStrLong = new String(binLong);
		String binStrByte = "";
		if (pos == 0) {
			binStrByte = binStrLong.substring(24, 32);
		} else if (pos == 1) {
			binStrByte = binStrLong.substring(16, 24);
		} else if (pos == 2) {
			binStrByte = binStrLong.substring(8, 16);
		} else if (pos == 3) {
			binStrByte = binStrLong.substring(0, 8);
		}
		return ((byte) Base2Num(binStrByte.toCharArray(), "byte", 2));
	}

	public static short GetWordL(int inLong, boolean MSW) {
		char[] binLong = Num2Base(inLong, "long", 2);
		String binStrLong = new String(binLong);
		String binStrWord;
		if (MSW) {
			binStrWord = binStrLong.substring(0, 16);
		} else {
			binStrWord = binStrLong.substring(16, 32);
		}
		return ((short) Base2Num(binStrWord.toCharArray(), "word", 2));
	}

	public static int SetBitsL(int varLong, int first, int last, int tmpBits) {
		int lTmpBits = tmpBits;
		char[] binVarLong = Num2Base(varLong, "long", 2);
		char[] binTmpBits = Num2Base(lTmpBits, "long", 2);
		for (int index = 0; index <= last - first; index++) {
			binVarLong[31 - first - index] = binTmpBits[31 - index];
		}
		return (Base2Num(binVarLong, "long", 2));
	}

	public static int SetByteL(int varLong, int pos, byte tmpByte) {
		char[] binVarLong = Num2Base(varLong, "long", 2);
		char[] binTmpByte = Num2Base(tmpByte, "byte", 2);
		int offset = 0;
		if (pos == 0) {
			offset = 24;
		} else if (pos == 1) {
			offset = 16;
		} else if (pos == 2) {
			offset = 8;
		}
		for (int index = 0; index <= 7; index++) {
			binVarLong[index + offset] = binTmpByte[index];
		}
		return (Base2Num(binVarLong, "long", 2));
	}

	public static int SetWordL(int varLong, boolean MSW, short tmpWord) {
		char[] binVarLong = Num2Base(varLong, "long", 2);
		char[] binTmpWord = Num2Base(tmpWord, "word", 2);
		int offset = 0;
		if (!MSW) {
			offset = 16;
		}
		for (int index = 0; index <= 15; index++) {
			binVarLong[index + offset] = binTmpWord[index];
		}
		return (Base2Num(binVarLong, "long", 2));
	}

	public static char[] Num2Base(int num, String type, int base) {
		// boolean error = false;
		int length = 1;
		type = type.toUpperCase();
		if (type.compareTo("BYTE") == 0) {
			length = 8;
		} else if (type.compareTo("WORD") == 0) {
			length = 16;
		} else if (type.compareTo("LONG") == 0) {
			length = 32;
		} else {
			System.out.println("Type invalide dans l'appel a Num2Base");
		}
		char[] tmpCharArr = new char[length];
		for (int i = 0; i < length; i++) {
			tmpCharArr[i] = '0';
		}
		String numStr = Integer.toString(num, 2);
		int numLen = numStr.length();
		if (numLen > length) {
			numStr = numStr.substring(numLen - length, numLen);
			numLen = length;
		}
		char[] numCharArr = numStr.toCharArray();
		for (int j = 0; j < numLen; j++) {
			tmpCharArr[length - numLen + j] = numCharArr[j];
		}
		if (num < 0) {
			tmpCharArr = TwosComp(tmpCharArr, length);
		}
		if (base == 16) {
			tmpCharArr = Bin2Hex(tmpCharArr, length);
		}
		return (tmpCharArr);
	}

	public static int Base2Num(char[] inChar, String type, int base) {

		// boolean error = false;
		int binLen = 1;
		type = type.toUpperCase();
		if (type.compareTo("BYTE") == 0) {
			binLen = 8;
		} else if (type.compareTo("WORD") == 0) {
			binLen = 16;
		} else if (type.compareTo("LONG") == 0) {
			binLen = 32;
		} else {
			System.out.println("Type invalide dans l'appel a Base2Num");
		}
		char[] binCharArr = new char[binLen];
		for (int i = 0; i < binLen; i++) {
			binCharArr[i] = '0';
		}
		String tmpStr = new String(inChar);
		int tmpLen = tmpStr.length();
		if (tmpLen > binLen) {
			tmpStr = tmpStr.substring(tmpLen - binLen, tmpLen);
			tmpLen = binLen;
		}
		char[] tmpCharArr = tmpStr.toCharArray();
		for (int j = 0; j < tmpLen; j++) {
			binCharArr[binLen - tmpLen + j] = tmpCharArr[j];
		}
		if (base == 16) {
			binCharArr = Hex2Bin(inChar, binLen / 4);
		}
		String binStr;
		int retVal = 0;
		if (binCharArr[0] == '1') {
			binCharArr = TwosComp(binCharArr, binLen);
			if (binCharArr[0] == '1') {
				if (binLen == 8) {
					retVal = -128;
				} else if (binLen == 16) {
					retVal = -32768;
				} else if (binLen == 32) {
					retVal = -2147483648;
				}
			} else {
				binStr = new String(binCharArr);
				retVal = (-1) * Integer.parseInt(binStr, 2);
			}
		} else {
			binStr = new String(binCharArr);
			retVal = Integer.parseInt(binStr, 2);
		}
		return (retVal);
	}

	private static char[] TwosComp(char[] inChar, int length) {
		for (int i = 0; i < length; i++) {
			if (inChar[i] == '0') {
				inChar[i] = '1';
			} else if (inChar[i] == '1') {
				inChar[i] = '0';
			} else if (inChar[i] == '-') {
				inChar[i] = '1';
			} else {
				System.out
						.println("*** ERREUR: Caractere binaire invalide ***");
			}
		}
		int j = length - 1;
		while (inChar[j] == '1') {
			inChar[j] = '0';
			j--;
		}
		inChar[j] = '1';
		return (inChar);
	}

	private static char[] Bin2Hex(char[] bin, int binLen) {
		String hexStr = "";
		String subStr;
		String binStr = new String(bin);
		for (int i = 0; i < (binLen / 4); i++) {
			subStr = binStr.substring(4 * i, (4 * i) + 4);
			int tmpInt = Integer.parseInt(subStr, 2);
			String tmpStr = Integer.toString(tmpInt, 16);
			hexStr = hexStr + tmpStr;
		}
		return (hexStr.toCharArray());
	}

	private static char[] Hex2Bin(char[] hex, int hexLen) {
		String binStr = "";
		String subStr;
		String hexStr = new String(hex);
		int hexStrLen = hexStr.length();
		for (int i = 0; i < hexLen - hexStrLen; i++) {
			binStr = binStr + "0000";
		}
		for (int j = 0; j < hexStrLen; j++) {
			subStr = hexStr.substring(j, j + 1);
			int tmpInt = Integer.parseInt(subStr, 16);
			String tmpStr = Integer.toString(tmpInt, 2);
			int tmpLen = tmpStr.length();
			for (int k = 0; k < 4 - tmpLen; k++) {
				tmpStr = "0" + tmpStr;
			}
			binStr = binStr + tmpStr;
		}
		return (binStr.toCharArray());
	}
}
