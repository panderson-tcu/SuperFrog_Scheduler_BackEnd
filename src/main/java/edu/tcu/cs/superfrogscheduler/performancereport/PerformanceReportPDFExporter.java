package edu.tcu.cs.superfrogscheduler.performancereport;



import java.awt.Color;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class PerformanceReportPDFExporter {
    private List<PerformanceReport> performanceReportList;

    public PerformanceReportPDFExporter(List<PerformanceReport> performanceReportList) {
        this.performanceReportList = performanceReportList;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Report ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("First Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Last Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("No. Completed Request(s)", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Begin Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("End Date", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (PerformanceReport performanceReport : performanceReportList) {
            table.addCell(performanceReport.getReportId().toString());
            table.addCell(String.valueOf(performanceReport.getFirstName()));
            table.addCell(String.valueOf(performanceReport.getLastName()));
            table.addCell(performanceReport.getNumberOfCompletedAppearances().toString());
            table.addCell(performanceReport.getPeriodRange().getBeginDate().toString());
            table.addCell(performanceReport.getPeriodRange().getEndDate().toString());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(12);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("SuperFrog Students Performance Report", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
