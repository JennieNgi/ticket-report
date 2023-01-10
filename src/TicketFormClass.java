import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;


public class TicketFormClass extends JFrame {
    
    private static final int FRAME_WIDTH = 350;
    private static final int FRAME_HEIGHT = 500;
    private static final int FRAME_X = 250;
    private static final int FRAME_Y = 250; 
    
    private JButton btnCreate;
    private JButton btnReset;
    
    //0 to 599
    private final String VALID_PATTERN = "^([0-9]|([1-9][0-9])|([1-5][0-9][0-9]))$";
    
    //0.00 to 999.99
    private final String VALID_PATTERN_DOUBLE = "^([0-9]\\.[0-9][0-9]|[1-9][0-9]\\.[0-9][0-9]|[1-9][0-9][0-9]\\.[0-9][0-9])$";
    
    private JLabel lblPromptEmpty;
    private JLabel lblCount;
    private JLabel lblPrice;
    
    private JTextField txtInputCountA;
    private JTextField txtInputCountB;
    private JTextField txtInputCountC;
    
    private JTextField txtInputPriceA;
    private JTextField txtInputPriceB;
    private JTextField txtInputPriceC;
 
    private JLabel lblPromptA;
    private JLabel lblPromptB;
    private JLabel lblPromptC;
    
    private Seat seatA;
    private Seat seatB;
    private Seat seatC;
    
    private Report report;
    private JTextArea txtOutput;
    
    // -------------------------------------------- constructor method
    public TicketFormClass() {
        this.setTitle("Concert Ticket Calculator");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocation(FRAME_X, FRAME_Y);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // prohibit the user from resizing the JFrame
        this.setResizable(false);

        //Border LayoutManager for the whole layout
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout()); 
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        this.add(contentPanel);
        
        
        //---------------------------------------------button panel
        JPanel buttonPanel = new JPanel();
        //Box LayoutManager for the button panel (horizontally)
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        //add space on the left
        buttonPanel.add(Box.createHorizontalGlue());
        
        // JButton  
        btnCreate = new JButton("Create Report");
        buttonPanel.add(btnCreate);
        btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onSubmit(e);
			}
        });
        
        btnCreate.setEnabled(false);
        
        //create some space between btnCreate and btnReset
        buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
        
        //reset button
        btnReset = new JButton("Reset");
        buttonPanel.add(btnReset);
        btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onReset(e);
			}
        });
        
       
        
        //add space on the right
        buttonPanel.add(Box.createHorizontalGlue());

        //SOUTH means at the bottom
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        
        //-----------------------------------------------input panel
        //Grid layout manager for input panel
        JPanel inputPanel = new JPanel((new GridLayout(4,3,6,6)));
        
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Data"));
        
        //Create the header
        lblPromptEmpty = new JLabel("");
        inputPanel.add(lblPromptEmpty);
        lblCount = new JLabel("Count");
        inputPanel.add(lblCount);
        lblPrice = new JLabel("Price ($)");
        inputPanel.add(lblPrice);
        
        //create seatA, seatB, seatB objects using the Seat Class
        seatA = new Seat("Seat A");
        seatB = new Seat("Seat B");
        seatC = new Seat("Seat C");
        
        lblPromptA = new JLabel("Enter for Seat A:");
        lblPromptB = new JLabel("Enter for Seat B:");
        lblPromptC = new JLabel("Enter for Seat C:");
        txtInputCountA = new JTextField();    
        txtInputCountB = new JTextField();   
        txtInputCountC = new JTextField();   
        txtInputPriceA = new JTextField();
        txtInputPriceB = new JTextField();
        txtInputPriceC = new JTextField();
        
        inputPanel.add(lblPromptA);
        inputPanel.add(txtInputCountA);
        inputPanel.add(txtInputPriceA);
        inputPanel.add(lblPromptB);
        inputPanel.add(txtInputCountB);
        inputPanel.add(txtInputPriceB);
        inputPanel.add(lblPromptC);
        inputPanel.add(txtInputCountC);
        inputPanel.add(txtInputPriceC);
        
          
        //add event listener for enter key
        txtInputPriceC.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				onSubmit(e);
        	}
        });
        
        //add the input panel to the content panel, NORTH means on the top
        contentPanel.add(inputPanel, BorderLayout.NORTH);
        
        // add eventlistener to validate userinput
        txtInputCountA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				onUserInput(e);
			}
		});
        
        txtInputCountB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				onUserInput(e);
			}
		});
        
        txtInputCountC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				onUserInput(e);
			}
		});
        
        txtInputPriceA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				onUserInput(e);
			}
		});
        
        txtInputPriceB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				onUserInput(e);
			}
		});
        
        txtInputPriceC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				onUserInput(e);
			}
		});
   
   
        //------------------------------------------------output panel
        //Border Layout Manager for output panel
        JPanel outputPanel = new JPanel ();
        outputPanel.setBorder(BorderFactory.createTitledBorder("Report"));
        
        //create report object using Report Class
        report = new Report();
        
        txtOutput = new JTextArea();
        
		txtOutput.setColumns(50);
	    //set the height
	    txtOutput.setRows(16);
	    txtOutput.setFont(new Font("monospaced", Font.BOLD, 10));
	    //create a border object by BorderFactory class (.createLineBorder is the method)
	    txtOutput.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	    //have to set the word wrap by manual
	    txtOutput.setLineWrap(true);
	    txtOutput.setWrapStyleWord(true);
	    //setEditable(true) so that you can click in it
	    txtOutput.setEditable(true);
//      report.setJTextAreaFormat();
        outputPanel.add(txtOutput);
        
        contentPanel.add(outputPanel, BorderLayout.CENTER);  
    }
    
    private LayoutManager GridLayout(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}


	private JPanel setBorder(Border createEmptyBorder) {
		// TODO Auto-generated method stub
		return null;
	}

    
    // ------------------------------------------- event handlers for submit button
    private void onSubmit(ActionEvent e) {
	
		  int countA = Integer.parseInt(txtInputCountA.getText());
		  double priceA = Double.parseDouble(txtInputPriceA.getText());
		  int countB = Integer.parseInt(txtInputCountB.getText());
		  double priceB = Double.parseDouble(txtInputPriceB.getText());
		  int countC = Integer.parseInt(txtInputCountC.getText());
		  double priceC = Double.parseDouble(txtInputPriceC.getText());

		  seatA.setTicketInfo(countA, priceA);
		  seatB.setTicketInfo(countB, priceB);
		  seatC.setTicketInfo(countC, priceC);

		  report.setSeats(seatA,seatB,seatC);
		  
	      txtOutput.setText(report.getReport());  	
    }
    
    // ------------------------------------------- event handlers for reset button
    private void onReset(ActionEvent e) {
    	//    	set everything to empty
    	txtInputCountA.setText("");
    	txtInputPriceA.setText("");      
    	txtInputCountB.setText("");
    	txtInputPriceB.setText(""); 
    	txtInputCountC.setText("");
    	txtInputPriceC.setText(""); 

       	txtOutput.setText("");
       	txtInputCountA.requestFocusInWindow();
    }
    
    
    private void onUserInput(KeyEvent e) {

        try {
        	if ( (txtInputCountA.getText().matches(VALID_PATTERN)) && (txtInputCountB.getText().matches(VALID_PATTERN)) && 			(txtInputCountC.getText().matches(VALID_PATTERN)) && (txtInputPriceA.getText().matches(VALID_PATTERN_DOUBLE))&& 			(txtInputPriceB.getText().matches(VALID_PATTERN_DOUBLE))&& (txtInputPriceC.getText().matches(VALID_PATTERN_DOUBLE)) ) {
            	btnCreate.setEnabled(true);
            } else {
            	btnCreate.setEnabled(false);
            }
        } catch (NumberFormatException nfe) {
        	btnCreate.setEnabled(false);
        }
        
	}
    
    //-------------------------------------------- main method
    public static void main(String[] args) {
    	TicketFormClass ticketForm = new TicketFormClass();
        ticketForm.setVisible(true);
	}
    
}
