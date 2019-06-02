package com.anagramsolver.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashManager {

	public static byte[] getMD5Hash(String src) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] bytesOfAnagram = src.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		return md.digest(bytesOfAnagram);
	}
}
