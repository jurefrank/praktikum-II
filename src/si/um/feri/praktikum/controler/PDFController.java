package si.um.feri.praktikum.controler;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;


import si.um.feri.praktikum.entity.Record;

@ManagedBean(name="pdfController")
@SessionScoped
public class PDFController {
	
	 private static Font mainTitle = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, BaseColor.BLACK);
	 private static Font tekstFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
	 
	
	public void createPDF(Record record) throws DocumentException, IOException {
		
		Document document = new Document();
		String fileName = "Record" + record.getTitle()
								   +"version"+ record.getVersion()
								   + "data"+ ".pdf";
		  FacesContext context = FacesContext.getCurrentInstance();
	        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	        context.responseComplete();
	        PdfWriter.getInstance(document, response.getOutputStream());
	        
	        
	        document.addAuthor("evidencaObdelave Services");
	        document.addCreationDate();
	        document.addTitle("Document with information about records and their data.");
	        document.open();
	        
	        LineSeparator lineSeparator = new LineSeparator();
	        lineSeparator.setOffset(-3);
	        
	        Paragraph mainTitle = new Paragraph(record.getTitle() + " " + "Version " + record.getVersion());
	        mainTitle.setFont(PDFController.mainTitle);
	        document.add(mainTitle);
	        
	        Paragraph recordKeeper = new Paragraph("Record keeper information ");
	        	recordKeeper.add(lineSeparator);
	        	Phrase phrase = new Phrase("Name:" + record.getNameManager() + "his contact data is : " 
	        								+ record.getEmailManager() + "phone number " 
	        								+ record.getPhoneNumberManager());
	        	
	        	
	        document.add(recordKeeper);
	        document.add(phrase);
	        
	        Paragraph storingData = new Paragraph("Storing data about");
	        		storingData.add(lineSeparator);
	        		
	        Phrase phrase2 = new Phrase( "Categories: " + record.getCategoriesOfUsersWhomPersonalDataDisclosed());
	        Phrase phrase3 = new Phrase("Which user data is being transfered: " + record.getInformationOnTransfersOfPersonalData());
	        
	        
	        document.add(storingData);
	        document.add(phrase2);
	        document.add(phrase3);
	        
	        document.close();
	        
	        
		
		
		
		
	}
	

}
