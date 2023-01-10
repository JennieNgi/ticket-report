/**
 * Seat Class
 * This class keeps track of ticket type: name,
 * price, and number of tickets sold.
 * 
 * @author Sean Morrow
 */

public class Seat {

    // data members	  
    private String name; 
    private double pricePerTicket; 
    private int ticketsSold;

    // constructor method
    public Seat(String ticketName) {
        name = ticketName;

        pricePerTicket = 0.0;
        ticketsSold = 0;
    }

    // Public Methods	                            
    public String getName() {
        return name;
    }

    public double getSales() {
        return ticketsSold * pricePerTicket;
    }

    public double getTicketPrice() {
        return pricePerTicket;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketInfo( int count, double price ) {
        ticketsSold    = count;
        pricePerTicket = price;
    }

}