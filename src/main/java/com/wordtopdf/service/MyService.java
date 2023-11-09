package com.wordtopdf.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.groupdocs.conversion.Converter;
import com.groupdocs.conversion.options.convert.PdfConvertOptions;

@Service
public class MyService {

	public ResponseEntity<byte[]> convertToPdf(MultipartFile wordFile) {
		String outputFilePath = "src/main/resources/converted-docx-to-pdf.pdf";

		try {
			
			// Save the provided Word document to a temporary file
            Path tempFilePath = Files.createTempFile("temp_", ".docx");
            wordFile.transferTo(tempFilePath.toFile());
            
			// Convert Word document (DOC/DOCX) to PDF using the "Converter" library
			Converter converter = new Converter(tempFilePath.toString());
			converter.convert(outputFilePath, new PdfConvertOptions());

			// Read the converted PDF file as a byte array
			Path path = Paths.get(outputFilePath);
			byte[] pdfBytes = Files.readAllBytes(path);

			// Set the response headers for download
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("attachment", "output.pdf");

			// Return the PDF content as a byte array in the response with download headers
			ResponseEntity<byte[]> response = ResponseEntity.ok().headers(headers).body(pdfBytes);

			// Delete the temporary Word and PDF files after the response is sent
            Files.delete(tempFilePath);
            Files.delete(path);

			return response;
		} catch (IOException e) {
			// Handle any exceptions and return an error response
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
}
