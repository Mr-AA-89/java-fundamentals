//Mastermind Encrypted Save Class - Patka - 5/8/2014
//Static methods to encrypt/decrypt the game data

import java.security.MessageDigest;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import javax.crypto.spec.*;
import sun.misc.*;

public class MastermindEncryptedSave
{
    private static byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static IvParameterSpec ivspec = new IvParameterSpec(iv);
    
	/*
     * Encrypt.
     *
     * @param strText   		16 bit string to encrypt.
	 * @param strEncryptionKey  16 bit string key to use for encryption.
     * @return                  String encrypted text.
     */
    public static String encrypt(String strText, String strEncryptionKey) throws Exception
    {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec secretKey = new SecretKeySpec(strEncryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
        
        byte[] encVal = cipher.doFinal(strText.getBytes());
        return new String(new BASE64Encoder().encode(encVal));
    }
    
	/*
     * Decrypt.
     *
     * @param strCipherText   	String to decrypt.
	 * @param strEncryptionKey  16 bit string key to use for decryption.
     * @return                  String decrypted text.
     */
    public static String decrypt(String strCipherText, String strEncryptionKey) throws Exception
    {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec secretKey = new SecretKeySpec(strEncryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
        
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(strCipherText);
        byte[] decValue = cipher.doFinal(decordedValue);
        return new String(new String(decValue));
    }
}
