/**
 * Report Class
 * This class generates a report and displays it in the system window
 * 
 * @author Sean Morrow
 */

import java.text.*;

public class Report {

    // data members
    private DecimalFormat decimalFormat;
    private double totalSales;
    private Seat seatA, seatB, seatC;
    private String myReport;
    //private static final String TAB = "\t";
    //private static final String RETURN = "\n";

    // --------------------------------------- constructor method
    public Report() {
        // initialization
        decimalFormat = new DecimalFormat("0.00");
        totalSales = 0;
        myReport = "";
        seatA = null;
        seatB = null;
        seatC = null;
    }

    // --------------------------------------- Public Methods	
    public void setSeats(Seat a,Seat b,Seat c) {
        // setting up private properties	
        seatA = a;
        seatB = b;
        seatC = c;
        //Compute the total sales figure
        totalSales =  seatA.getSales() + seatB.getSales() + seatC.getSales();
    }
    
    public String getReport() {
        // check if setSeats method has been fired - if not return no report
        if (seatA == null) return "";
        
        // APPROACH I : String.format() with output formatting
        myReport = String.format("%-8s %-15s %-8s %-8s\n", "", "Tickets Sold", "Price", "Total");
        myReport += String.format("%-8s %-15s %-8s %-8s\n", "", "------------", "-----", "-----");
        myReport += String.format("%-8s %-15s %-8s %-8s\n", seatA.getName(), seatA.getTicketsSold(), "$" + decimalFormat.format(seatA.getTicketPrice()), "$" + decimalFormat.format(seatA.getSales()));
        myReport += String.format("%-8s %-15s %-8s %-8s\n", seatB.getName(), seatB.getTicketsSold(), "$" + decimalFormat.format(seatB.getTicketPrice()), "$" + decimalFormat.format(seatB.getSales()));
        myReport += String.format("%-8s %-15s %-8s %-8s\n", seatC.getName(), seatC.getTicketsSold(), "$" + decimalFormat.format(seatC.getTicketPrice()), "$" + decimalFormat.format(seatC.getSales()));        
        myReport += String.format("\nTotal Sales: $%-10s\n", decimalFormat.format(totalSales));
        
        /*
        // APPROACH II : Concatenation
        myReport = "";
        myReport += TAB + "Tickets sold" + TAB + "Price" + TAB + "Total" + RETURN; 
        myReport += TAB + "------------" + TAB + "-----" + TAB + "-----" + RETURN;
        myReport += seatA.getName() + TAB + 
            seatA.getTicketsSold() + TAB + TAB +
            "$" + decimalFormat.format(seatA.getTicketPrice()) + TAB +
            "$" + decimalFormat.format(seatA.getSales()) + RETURN;
        myReport += seatB.getName() + TAB +
            seatB.getTicketsSold() + TAB + TAB +
            "$" + decimalFormat.format(seatB.getTicketPrice()) + TAB +
            "$" + decimalFormat.format(seatB.getSales()) + RETURN;
        myReport += seatC.getName() + TAB +
            seatC.getTicketsSold() + TAB + TAB +
            "$" + decimalFormat.format(seatC.getTicketPrice()) + TAB +
            "$" + decimalFormat.format(seatC.getSales()) + RETURN + RETURN;
        myReport += "Total Sales: $ " + decimalFormat.format(totalSales) + RETURN;
        */

        return myReport;
    }

}