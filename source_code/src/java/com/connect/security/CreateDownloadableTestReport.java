/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect.security;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Devanand
 */
public class CreateDownloadableTestReport extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
      try {
            // Get the text that will be added to the PDF
            //PrintWriter out = response.getWriter();
            //out.print("Please Wait....");
          response.setHeader("Expires", "0");
            response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"result.pdf\"");
            HttpSession UserSession = request.getSession(false);
            if(UserSession.getAttribute("Username") == null)
       {
   response.sendRedirect("index.html");
      

}
else
       {
    
           Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
           Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
            // step 1
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            
            int Score = Integer.parseInt(UserSession.getAttribute("ExamScore").toString()); 
            String performance = "";
            if(Score>=50 && Score<60)
            {
                performance = "Satisfactory";
            }
            if(Score>=60 && Score<70)
            {
                performance = "Fair";
            }
            if(Score>=70 && Score<80)
            {
                performance = "Good";
            }
            if(Score>=80 && Score<=100)
            {
                performance = "Excellent";
            }
            if(Score<50)
            {
                performance = "Poor";
            }
            // step 3
            document.open();
            // step 4
            Paragraph para1 = new Paragraph("ONLINE EXAMINATION PORTAL", catFont);
            para1.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph para2 = new Paragraph("This is to certify that", subFont);
            para2.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph newLine = new Paragraph(" ");
            
            Paragraph UserNameLine = new Paragraph(UserSession.getAttribute("Username").toString().toUpperCase(),catFont);
            UserNameLine.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph para3 = new Paragraph("has completed the exam titled", subFont);
            para3.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph ExamNameLine = new Paragraph(UserSession.getAttribute("ExamName").toString(),catFont);
            ExamNameLine.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph ExamIDLine = new Paragraph("(UEID : "+UserSession.getAttribute("UEID").toString()+")",subFont);
            ExamIDLine.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph para4 = new Paragraph("secured a score of", subFont);
            para4.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph ExamScoreLine = new Paragraph(UserSession.getAttribute("ExamScore").toString()+" / 100",catFont);
            ExamScoreLine.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph para5 = new Paragraph("and found the candidate's performance to be", subFont);
            para5.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph ExamPerformanceLine = new Paragraph(performance,catFont);
            ExamPerformanceLine.setAlignment(Element.ALIGN_CENTER);
            
            String absoluteDiskPath = getServletContext().getRealPath("/images/examCap.png");            
            Image image = Image.getInstance(absoluteDiskPath);
            image.scaleAbsolute(400, 100);
            image.setAlignment(Element.ALIGN_CENTER);
            
            PdfPTable table = new PdfPTable(2);
            PdfPCell c1 = new PdfPCell(new Phrase("User ID : "+UserSession.getAttribute("UserId")));
                c1.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(c1);
                
             c1 = new PdfPCell(new Phrase("Certificate ID : "+UserSession.getAttribute("UEID")));
                c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(c1);
               // Rectangle page1 = new Rectangle(500,500);
                

           // document.addCreationDate();
            document.addAuthor("Online Examination Portal");
            document.addTitle("Examination Report");
           document.add(image);
            //document.add(para1);
            document.add(newLine);
            document.add(newLine);
            document.add(para2);
            document.add(UserNameLine);
            document.add(newLine);
            document.add(para3);
            document.add(ExamNameLine);
            document.add(ExamIDLine);
            document.add(newLine);
            document.add(para4);
            document.add(ExamScoreLine);
            document.add(newLine);
            document.add(para5); 
            document.add(ExamPerformanceLine);
            document.add(newLine); 
            document.add(newLine); 
            document.add(newLine); 
           // document.add(table);
            // step 5
            document.close();
 
    
            
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            ServletOutputStream os = response.getOutputStream();
            os.write(baos.toByteArray());
            //baos.writeTo(os);
            os.flush();
            os.close();
       }
            return;
        }
        catch(Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //service(request, response);
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
