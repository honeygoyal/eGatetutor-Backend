package com.tutor.egate.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tutor.egate.service.StorageService;

@RestController
public class UploadController {

	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	@Autowired
	StorageService storageService;

	@RequestMapping("/upload")
	public StringBuilder upload(@RequestParam("file") MultipartFile[] files) {
		StringBuilder fileNames = new StringBuilder();
		for (MultipartFile file : files) {

			Path fileNameAndPath = Paths.get(uploadDirectory,
					file.getOriginalFilename() + "_" + new Date().getTime() + "_" + randomAlphaNumeric(8));
			fileNames.append(file.getOriginalFilename() + "_" + new Date().getTime() + "_" + randomAlphaNumeric(8));
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileNames;
	}
}
