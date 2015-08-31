package aes2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class aes {
	private static SecretKeySpec secretKeySpec;

	
	public static void main(String[] args) {
		//passwort Decode/Encode Value
		setkey(args[0]);
		
	switch (args[2]) {
	case "Decode":
		System.out.println(verschluesselnAES(args[1]));
		break;
	case "Encode":
		System.out.println(entschluesselnAES(args[1]));
		break;
	
	}
	
	}
	
	private static String verschluesselnAES(String text) {

		 
		// Verschluesseln
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		byte[] encrypted = null;
		try {
			encrypted = cipher.doFinal(text.getBytes());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		// bytes zu Base64-String konvertieren (dient der Lesbarkeit)
		BASE64Encoder myEncoder = new BASE64Encoder();
		String geheim = myEncoder.encode(encrypted);
		 
		// Ergebnis
		return geheim;

	}

	private static String entschluesselnAES(String geheim) {
	      BASE64Decoder myDecoder2 = new BASE64Decoder();
	      byte[] crypted2 = null;
		try {
			crypted2 = myDecoder2.decodeBuffer(geheim);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	      // Entschluesseln
	      Cipher cipher2 = null;
		try {
			cipher2 = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      try {
			cipher2.init(Cipher.DECRYPT_MODE, secretKeySpec);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      byte[] cipherData2 = null;
		try {
			cipherData2 = cipher2.doFinal(crypted2);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      String erg = new String(cipherData2);
	 
	      return erg;
	}
	
	private static void setkey(String pkey) 
	{
	byte[] key = null;
	try {
		key = (pkey).getBytes("UTF-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	MessageDigest sha = null;
	try {
		sha = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	key = sha.digest(key);
	key = Arrays.copyOf(key, 16); 
	secretKeySpec = new SecretKeySpec(key, "AES");
	}
}
