package com.fis.restcontroller;

import com.fis.service.QRCodeGenerator;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fis.service.ExternalUserService;

import java.io.IOException;

@RestController
public class ExternalUserController {

	@Autowired
	ExternalUserService externalUserService;

	@GetMapping("/externalUser")
	public String getExternalUserData() {
		return externalUserService.getUserData();
	}



	@GetMapping("/QRCode/contents/{contents}/width/{width}/height/{height}")
	public ResponseEntity<byte[]> getQrCode(
			@PathVariable("contents") String contents,
			@PathVariable("width") int width,
			@PathVariable("height") int height
	) {
		byte[] qrCodeInBytes = null;
		try {
			 qrCodeInBytes =  QRCodeGenerator.getQRCodeI(contents, width,height);
		} catch (WriterException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return new ResponseEntity<>(qrCodeInBytes, HttpStatus.OK);
	}

}
