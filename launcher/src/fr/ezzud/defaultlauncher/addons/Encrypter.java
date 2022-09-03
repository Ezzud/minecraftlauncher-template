package fr.ezzud.defaultlauncher.addons;

import org.apache.commons.codec.binary.Base64;


public class Encrypter {
	
	
	
  public static String encrypt(String key, final String text) {
    return Base64.encodeBase64String(Encrypter.xor(key, text.getBytes()));
  }
  
  
  
  public static String decrypt(String key, final String hash) {
    try {
      return new String(Encrypter.xor(key, Base64.decodeBase64(hash.getBytes())), "UTF-8");
    } catch (java.io.UnsupportedEncodingException ex) {
      throw new IllegalStateException(ex);
    }
  }
  
  
  private static byte[] xor(String key, final byte[] input) {
    final byte[] output = new byte[input.length];
    final byte[] secret = key.getBytes();
    int spos = 0;
    for (int pos = 0; pos < input.length; ++pos) {
      output[pos] = (byte) (input[pos] ^ secret[spos]);
      spos += 1;
      if (spos >= secret.length) {
        spos = 0;
      }
    }
    return output;
  }
  
}