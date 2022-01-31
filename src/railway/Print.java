package railway;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.HeadlessException;
import java.awt.print.PrinterJob;

/**
 *
 * @author Xnyder
 */
public class Print {
    
    boolean cancelled = false;
    
    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n")){
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
        }
    }

    public Print(final String slip) {
        Printable contentToPrint = new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int page) throws PrinterException {
                if (page > 0) {
                    return NO_SUCH_PAGE;
                }
                pageFormat.setOrientation(PageFormat.PORTRAIT);
                Graphics2D g2d = (Graphics2D)graphics;

                g2d.setPaint(Color.black);
                g2d.setFont(new Font("Arial", Font.BOLD, 10));
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                drawString(g2d, slip, 0, 0); 

                return PAGE_EXISTS;
            }
        };

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(contentToPrint);
        try {
            job.printDialog();
            job.print();
        } catch (PrinterException e) {
            System.err.println(e.getMessage());
            cancelled = true;
        } catch (HeadlessException e) {
            System.err.println(e.getMessage());
            cancelled = true;
        }
    }
}
