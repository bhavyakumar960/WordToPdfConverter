//package com.wordtopdf.model;
//
//import java.io.IOException;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//import org.springframework.web.multipart.MultipartFile;
//
//public class WordToPdfConverter {
//
//	private PDPageContentStream contentStream;
//
//	public WordToPdfConverter(PDPageContentStream contentStream) {
//		this.contentStream = contentStream;
//	}
//
//	public void convert(MultipartFile wordFile) throws IOException {
//		String fileName = wordFile.getOriginalFilename();
//		if (fileName.toLowerCase().endsWith(".docx")) {
//			XWPFDocument document = new XWPFDocument(wordFile.getInputStream());
//			convertDocxToPdf(document);
//			document.close();
//		} else if (fileName.toLowerCase().endsWith(".doc")) {
//			// Apache POI doesn't support direct .doc to PDF conversion, you may need to
//			// find another library or use Aspose.
//			// For demonstration purposes, we'll assume this is handled by another method
//			// for .doc files.
//			// Please refer to the previous responses for Aspose.Words implementation for
//			// .doc files.
//			throw new UnsupportedOperationException("Converting .doc files to PDF is not supported in this example.");
//		} else {
//			throw new IllegalArgumentException("Invalid file format. Supported formats: .doc, .docx");
//		}
//	}
//
//	public void convert(XWPFDocument document) throws IOException {
//		for (XWPFParagraph paragraph : document.getParagraphs()) {
//			for (XWPFRun run : paragraph.getRuns()) {
//				// Extract the text from the paragraph run and apply it to the PDF content
//				// stream.
//				String text = run.getText(0);
//				if (text != null) {
//					contentStream.beginText();
//					contentStream.setFont(PDType1Font.HELVETICA, 12);
//					contentStream.newLineAtOffset(100, 700); // Adjust the position as needed.
//					contentStream.showText(text);
//					contentStream.endText();
//				}
//			}
//		}
//	}
//
//}