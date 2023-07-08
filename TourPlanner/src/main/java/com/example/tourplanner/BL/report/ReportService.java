package com.example.tourplanner.BL.report;

import com.example.tourplanner.DAL.dal.Repository.MapQuestStaticImageAPI;
import com.example.tourplanner.models.Tour;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

import com.example.tourplanner.models.TourLogs;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ReportService {
    private final MapQuestStaticImageAPI mapQuestStaticImageAPI;
    public ReportService(MapQuestStaticImageAPI mapQuestStaticImageAPI) {
        this.mapQuestStaticImageAPI = mapQuestStaticImageAPI;
    }
    public void generateReport(Tour tour, List<TourLogs> tourLogs) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.beginText();
            contentStream.newLineAtOffset(25, 450);
            contentStream.setFont(PDType1Font.COURIER_BOLD, 20);
            contentStream.setNonStrokingColor(Color.red);
            contentStream.showText("Tour Report");
            contentStream.setNonStrokingColor(Color.getHSBColor(0, 0, 0));
            contentStream.setFont(PDType1Font.COURIER, 12);

            String mapImageUrl = mapQuestStaticImageAPI.getStaticImage(tour.getFrom(), tour.getTo());
            BufferedImage originalImage = ImageIO.read(new URL(mapImageUrl));
            BufferedImage resizedImage = resize(originalImage, 250, 250);  // Resize to 250x250
            PDImageXObject pdImage = LosslessFactory.createFromImage(document, resizedImage);

            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Tour ID: " + tour.getId());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Name: " + tour.getName());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Description: " + tour.getDescription());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Start Location: " + tour.getFrom());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("End Location: " + tour.getTo());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Transport Type: " + tour.getTransportType());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Distance: " + tour.getDistance());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Estimated Time: " + tour.getEstimatedTime());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Route Info: " + tour.getRouteInfo());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Child Friendliness: " + tour.calculateChildFriendliness((float) tour.getDistance()));
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Accessibility: " + Tour.calculateAccessibility(tour.getTransportType(), (float) tour.getDistance()));
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Tour Logs: ");

            for (TourLogs tourLog : tourLogs) {
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Tour Log ID: " + tourLog.getId());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Date: " + tourLog.getDateTime());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Comment: " + tourLog.getComment());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Difficulty: " + tourLog.getDifficulty());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Total Time: " + tourLog.getTotalTime());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Rating: " + tourLog.getRating());
                contentStream.newLineAtOffset(0, -20);
            }

            contentStream.endText();

            contentStream.drawImage(pdImage, 300, 500);
            contentStream.setStrokingColor(110, 6, 6);
            contentStream.setLineWidth(1.5f);
            contentStream.moveTo(20, 470);
            contentStream.lineTo(580, 470);
            contentStream.stroke();

            contentStream.close();

            document.save("TourReport_" + tour.getName() + ".pdf");
        }
    }


    public BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}
