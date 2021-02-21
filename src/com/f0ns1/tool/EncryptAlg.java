package com.f0ns1.tool;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EncryptAlg {
	private String key;

	public EncryptAlg(String key) {
		System.out.println("Encryption key: "+key);
		this.key = key;
	}

	public byte[] recovery(String file) {
		File input = new File(file);
		byte[] fileContent = null;
		try {
			fileContent = Files.readAllBytes(input.toPath());
			fileContent = xorEncrypt(fileContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
	}

	private byte[] xorEncrypt(byte[] fileContent) {
		byte[] out = new byte[fileContent.length];
		int pos = 0;
		for (byte d : fileContent) {
			out[pos] = (byte) (d ^ val(pos));
			pos++;
		}
		return out;
	}

	private byte val(int pos) {
		int val = (pos % key.length());
		byte out = (byte) key.toCharArray()[val];
		return out;
	}

}
