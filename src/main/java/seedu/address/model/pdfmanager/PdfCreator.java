package seedu.address.model.pdfmanager;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.BorderRadius;
import com.itextpdf.layout.property.UnitValue;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.company.Company;
import seedu.address.model.person.Driver;
import seedu.address.model.task.Task;

/**
 * Creates and saves details provided into a PDF file.
 */
public class PdfCreator {

    public final String filePath;

    public PdfCreator(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves drivers` tasks for a specific date into a PDF file.
     *
     * @param tasks tasks list.
     * @param dateOfDelivery date of delivery.
     * @throws IOException if directory used for saving is not found.
     */
    public void saveDriverTaskPdf(List<Task> tasks, List<Driver> drivers, LocalDate dateOfDelivery)
            throws IOException {
        Document document = createDocument();
        insertCoverPage(document, dateOfDelivery);
        insertDriverTask(document, tasks, drivers, dateOfDelivery);

        //close to save
        document.close();
    }

    /**
     * Generates delivery orders for all incomplete and assigned / ongoing tasks.
     *
     * @param tasks incomplete and assigned tasks.
     * @param company an organisation.
     */
    public void generateDeliveryOrderPdf(List<Task> tasks, Company company) throws IOException {
        PdfDocument pdfDocument = createPdfDocument();
        insertDeliveryOrders(pdfDocument, tasks, company);

        //close to save
        pdfDocument.close();
    }

    private void createFileIfMissing() throws IOException {
        FileUtil.createIfMissing(Paths.get(filePath));
    }

    private PdfDocument createPdfDocument() throws IOException {
        createFileIfMissing();
        PdfDocument pdf = new PdfDocument(new PdfWriter(filePath));

        return pdf;
    }

    /**
     * Creates a document in the PDF file.
     *
     * @return PDF document ready to be filled with content.
     * @throws IOException if file path is not created or found.
     */
    private Document createDocument() throws IOException {
        PdfDocument pdfDocument = createPdfDocument();
        Document newDocument = new Document(pdfDocument);
        newDocument.setMargins(30, 30, 30, 30);

        return newDocument;
    }

    /**
     * Inserts cover page into the PDF document.
     *
     * @param document PDF document.
     * @param dateOfDelivery date of delivery.
     */
    private void insertCoverPage(Document document, LocalDate dateOfDelivery) {
        //add cover page
        String title = "Deliveria";
        String subTitle = "Delivery Tasks for " + dateOfDelivery;

        PdfCoverPageLayout coverPageLayout = new PdfCoverPageLayout(document);
        coverPageLayout.addCoverPage(title, subTitle);
    }

    private void insertDriverTask(Document document, List<Task> tasks, List<Driver> drivers, LocalDate dateOfDelivery) {
        PdfWrapperLayout wrapperLayout = new PdfWrapperLayout(document);
        wrapperLayout.populateDocumentWithTasks(tasks, drivers, dateOfDelivery);
    }

    private void insertDeliveryOrders(PdfDocument pdfDocument, List<Task> tasks, Company company) {

    }
}
