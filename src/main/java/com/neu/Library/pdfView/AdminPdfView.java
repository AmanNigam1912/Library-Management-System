package com.neu.Library.pdfView;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.neu.Library.dao.CirculationDao;
import com.neu.Library.dao.LibraryUserDao;
import com.neu.Library.pojo.Book;
import com.neu.Library.pojo.BookCirculation;
import com.neu.Library.pojo.LibraryUser;

public class AdminPdfView extends AbstractPdfView{

	
	List<BookCirculation> results;
	
	LibraryUserDao libUserDao;
	
	@Autowired
	CirculationDao bookCirculationDAO;
	
	public AdminPdfView(List<BookCirculation> results) {
		this.results=results;
		this.libUserDao = new LibraryUserDao();
		
	}
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Font helvetica_15_grey = new Font(Font.TIMES_ROMAN, 15, Font.BOLDITALIC, Color.DARK_GRAY);
		
			
			Element e2 = new Paragraph("Books under circulation");
	
			PdfPTable table = new PdfPTable(10);
	        table.setWidthPercentage(100.0f);
	        table.setWidths(new float[] { 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f,3.0f, 3.0f});
	        table.setSpacingBefore(10);

	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.WHITE);
	        cell.setPadding(5);

	        cell.setPhrase(new Phrase(" Book Id ", helvetica_15_grey));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase(" Book Title ", helvetica_15_grey));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase(" Book Author ", helvetica_15_grey));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase(" Circulation Id ", helvetica_15_grey));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase(" Due Date ", helvetica_15_grey));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase(" Issued Date ", helvetica_15_grey));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase(" Library User Id ", helvetica_15_grey));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase(" First Name ", helvetica_15_grey));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase(" Last Name ", helvetica_15_grey));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase(" Email ", helvetica_15_grey));
	        table.addCell(cell);
	        
	        for(BookCirculation bc: results) {
	        	
	        	Book book=bc.getBook();	
	        	Long libUserId = bc.getLibUserAcc().getUserLibraryId();

				LibraryUser lu = libUserDao.getLibUser(libUserId);
				
	        	cell.setPhrase(new Phrase(""+book.getBookId(), helvetica_15_grey));
		        table.addCell(cell);
		        
		        cell.setPhrase(new Phrase(book.getTitle(), helvetica_15_grey));
		        table.addCell(cell);
		        
		        cell.setPhrase(new Phrase(book.getAuthor(), helvetica_15_grey));
		        table.addCell(cell);
		        
		        cell.setPhrase(new Phrase(""+bc.getCirculationId(), helvetica_15_grey));
		        table.addCell(cell);
		        
		        cell.setPhrase(new Phrase(""+bc.getDueDate(), helvetica_15_grey));
		        table.addCell(cell);
		        
		        cell.setPhrase(new Phrase(""+bc.getIssuedDate(), helvetica_15_grey));
		        table.addCell(cell);
		        
		        cell.setPhrase(new Phrase(""+lu.getLibUserAcc().getUserLibraryId(), helvetica_15_grey));
		        table.addCell(cell);
		        
		        cell.setPhrase(new Phrase(""+lu.getFirstName(), helvetica_15_grey));
		        table.addCell(cell);
		        
		        cell.setPhrase(new Phrase(""+lu.getLastName(), helvetica_15_grey));
		        table.addCell(cell);
		        
		        cell.setPhrase(new Phrase(""+lu.getEmail(), helvetica_15_grey));
		        table.addCell(cell);
		        						
		}
	        
	        document.add(e2);
	        document.add(table);

		
		
	}
	
}
