package com.epam.passwordOperations;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.epam.model.MasterUsers;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PwdOperate implements PasswordOperations 
{

	private static final String SECRET_KEY = "my_super_secret_key_yo_yo_yo";
	private static final String SALT = "ssshhhhhhhhhhh!!!!";
	
	public PwdOperate() {}

	@Override
	public String generatePassword(MasterUsers user) 
	{
		PreferredPassword prefPass = user.getPrefPass();
		return prefPass.generatePassword(prefPass);
	}

	

	@Override
	public String encryptPassword(String strToEncrypt) 
	{
		try {
		      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		      IvParameterSpec ivspec = new IvParameterSpec(iv);
		 
		      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		      KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
		      SecretKey tmp = factory.generateSecret(spec);
		      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
		 
		      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
		      return Base64.getEncoder()
		          .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
		    } catch (Exception e) {
		      System.out.println("Error while encrypting: " + e);
		    }
		    return null;
		  }

	@Override
	public String decryptPassword(String strToDecrypt) 
	{
		try {
		      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		      IvParameterSpec ivspec = new IvParameterSpec(iv);
		 
		      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		      KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
		      SecretKey tmp = factory.generateSecret(spec);
		      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
		 
		      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
		      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		    } catch (Exception e) {
		      System.out.println("Error while decrypting: " + e);
		    }
		    return null;
	}
}

	



