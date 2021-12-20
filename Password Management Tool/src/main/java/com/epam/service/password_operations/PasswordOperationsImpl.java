package com.epam.service.password_operations;

import com.epam.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

@Service
public class PasswordOperationsImpl implements PasswordOperations
{
    private static final Logger LOGGER = LogManager.getLogger(PasswordOperationsImpl.class);
    private static final String SECRET_KEY = "my_super_secret_key_yo_yo_yo";
    private static final String SALT = "ssshhhhhhhhhhh!!!!";

    @Override
    public String generatePassword(User user)
    {
        PreferredPassword preferredPassword = user.getPreferredPassword();
        return preferredPassword.generatePassword(preferredPassword);
    }


    @Override
    public String encryptPassword(String stringToEncrypt)
    {
        try
        {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(stringToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e)
        {
            LOGGER.info("Error while encrypting: " + e);
        }
        return null;
    }

    @Override
    public String decryptPassword(String stringToDecrypt)
    {
        try
        {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(stringToDecrypt)));
        } catch (Exception e)
        {
            LOGGER.info("Error while decrypting: " + e);
        }
        return null;
    }
}

	



