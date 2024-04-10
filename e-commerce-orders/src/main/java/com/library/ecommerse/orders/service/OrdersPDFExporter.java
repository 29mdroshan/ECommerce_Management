package com.library.ecommerse.orders.service;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.library.ecommerse.orders.model.Orders;
import com.library.ecommerse.orders.model.Products;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;

import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class OrdersPDFExporter {
	private void addTableHeader(PdfPTable table) {
		Stream.of("Product Id", "Product Name", "Category", " Unit Price","Quantity","Total amount").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(Color.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private void addTableData(List<Products> product_list, PdfPTable table) {
		for (var product : product_list) {
			table.addCell(String.valueOf(product.getProductId()));
			table.addCell(product.getProductName());
			table.addCell(product.getProductCategory());
			table.addCell(String.valueOf(product.getProductPrice()));
			table.addCell(String.valueOf(product.getQuantity()));
			table.addCell(String.valueOf(product.getProductPrice()*product.getQuantity()));
		}
	}
	
	private void addTotalAmount(double amount, PdfPTable table) {
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(16);
		PdfPCell cell = new PdfPCell(new Phrase("Total amount :",font));
        cell.setColspan(5);
        cell.setHorizontalAlignment(2);
        table.addCell(cell);
        
        Font fontamount = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontamount.setSize(16);
		fontamount.setColor(Color.RED);
        
		table.addCell( new Phrase(String.valueOf(amount),fontamount));
			
		
	}

	public void export(Orders order, HttpServletResponse response) throws DocumentException, IOException {

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		
		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(20);
		fontTitle.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Order Details", fontTitle);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);

		Font fontPara = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontPara.setSize(16);
		fontPara.setColor(Color.BLACK);

		Paragraph p2 = new Paragraph(
				"Order_id : " + order.getOrderId() + "\nCustomer_Details : " + order.getCutomer() + "\n\nProduct_Details : \n\n",
				fontPara);
		p2.setAlignment(Paragraph.ALIGN_LEFT);
		document.add(p2);

		
		PdfPTable table = new PdfPTable(6);
		addTableHeader(table);
		addTableData(order.getProduct(), table);
		addTotalAmount(order.getAmount(),table);
		document.add(table);
		
		
		Paragraph p4 = new Paragraph("Payment Information: \nPayment Through : "+order.getPaymentInfo()+"\n\n", fontPara);
		document.add(p4);

		document.close();

	}

}
