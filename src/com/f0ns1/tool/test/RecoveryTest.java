package com.f0ns1.tool.test;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import com.f0ns1.tool.Recovery;

public class RecoveryTest {

	public static void main(String[] args) {
		System.out.println("Rcovery Test: ");
		File privKey = new File("/tmp/keys/rsa_test.key");
		DataInputStream dis;
		try {
			dis = new DataInputStream(new FileInputStream("/tmp/keys/rsa_test.key"));
			byte[] privKeyBytes = new byte[(int) privKey.length()];
			dis.read(privKeyBytes);
			dis.close();
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(privKeyBytes);
			PrivateKey privKeyData = (PrivateKey) keyFactory.generatePrivate(privSpec);
			String[] argv = { "/tmp/ransomTest/", "/tmp/recovery/",
					Base64.getEncoder().encodeToString(privKeyData.getEncoded()) };
			Recovery recovery = new Recovery();
			recovery.main(argv);

		} catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}