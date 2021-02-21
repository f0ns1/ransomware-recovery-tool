package com.f0ns1.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Recovery {

	public static void main(String[] args) {
		System.out.println("Recovery tool ");
		String key;
		String dir;
		String recoveryDir;
		if (args.length != 3) {
			key = "f0ns1";
			dir = "/tmp/ransomDir/";
			recoveryDir = "/tmp/recovery/";
		} else {
			dir = args[0];
			recoveryDir = args[1];
			key = args[2];
		}
		List<String> list = listFilesForFolder(new File(dir));
		EncryptAlg enc = new EncryptAlg(key);
		for (String file : list) {
			System.out.println("File to recover : " + file);
			byte[] content = enc.recovery(file);
			copyProcess(recoveryDir, getName(file), content);
		}

	}

	private static String getName(String file) {
		String[] data = file.split("/");
		return data[data.length - 1];
	}

	private static void copyProcess(String recoveryDir, String name, byte[] content) {
		File create = new File(recoveryDir);
		try {
			// create directory if not exists
			if (!create.exists()) {
				create.mkdir();
			}
			// createnew file
			if (!create.exists()) {
				create = new File(recoveryDir + name);
				create.createNewFile();
			}
			try (FileOutputStream fos = new FileOutputStream(recoveryDir + name)) {
				// write content on file
				fos.write(content);
				System.out.println("RcoverFile succes at : " + recoveryDir + name);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<String> listFilesForFolder(final File folder) {
		List<String> list = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				list.addAll(listFilesForFolder(fileEntry));
			} else {
				list.add(fileEntry.getPath());
			}
		}
		return list;
	}
}
