import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class gameFacilitation {
    JFrame gframe;
    JPanel mygPanel, tPanel;
    JLabel gtitleLabel, gameLabel, patronNameLabel, eventsLabel, noOfMembersLabel, amountLabel, showAmountLabel, totalLabel, patronCommissionLabel, commissionLabel, errorLabel, damagesLabel, damagesCostLabel;
    JTextField patronNameField, noOfMembersField, damagesCostField, costFineField;
    JComboBox<String> gameBox;
    JRadioButton internalRadioButton, externalRadioButton, yesRadioButton, noRadioButton;
    JButton totalButton, costFineButton, payButton, backButton;
    private final double internalFee = 0.0;
    private final double externalFee = 500.0;
    boolean costFineButtonIsClicked = false;
    public gameFacilitation(){

        gframe = new JFrame("MARINGO");
        gframe.setSize(800,600);
        gframe.setLayout(new BorderLayout());

        tPanel = new JPanel();

        gtitleLabel = new  JLabel("Game Facilitation");
        Font font = new Font("Arial", Font.BOLD, 16);
        gtitleLabel.setForeground(Color.BLUE);
        gtitleLabel.setFont(font);

        tPanel.add(gtitleLabel);


        mygPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gameLabel = new  JLabel("Game");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        mygPanel.add(gameLabel, gbc);

        String[] games = {"Football", "Rugby", "Volleyball", "Basketball", "Netball", "Hockey", "Tennis", "Swimming", "Chess", "Darts", "Draft"};
        gameBox = new JComboBox<String>(games);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mygPanel.add(gameBox, gbc);

        gameBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    internalRadioButton.setEnabled(true);
                    externalRadioButton.setEnabled(true);
                    yesRadioButton.setEnabled(true);
                    noRadioButton.setEnabled(true);
                    String selectedGame = (String) gameBox.getSelectedItem();
                    patronNameField.setText(getPatronName(selectedGame));
                }
            }
        });

        patronNameLabel = new  JLabel("Patron Name");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mygPanel.add(patronNameLabel, gbc);

        patronNameField = new JTextField(20);
        patronNameField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mygPanel.add(patronNameField, gbc);

        eventsLabel = new  JLabel("Event:");
        eventsLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mygPanel.add(eventsLabel, gbc);

        internalRadioButton = new JRadioButton("Internal");
        gbc.gridx = 1;
        gbc.gridy = 2;
        mygPanel.add(internalRadioButton, gbc);

        internalRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        noOfMembersField.setEditable(true);
                        showAmountLabel.setText(String.valueOf(internalFee));
                    }
            }
        });

        externalRadioButton = new JRadioButton("External");
        gbc.gridx = 1;
        gbc.gridy = 3;
        mygPanel.add(externalRadioButton, gbc);

        externalRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    noOfMembersField.setEditable(true);
                    showAmountLabel.setText(String.valueOf(externalFee));
                }
            }
        });

        internalRadioButton.setEnabled(false);
        externalRadioButton.setEnabled(false);

        ButtonGroup tournaments = new ButtonGroup();
        tournaments.add(internalRadioButton);
        tournaments.add(externalRadioButton);

        noOfMembersLabel = new  JLabel("No of Members");
        gbc.gridx = 0;
        gbc.gridy = 4;
        mygPanel.add(noOfMembersLabel, gbc);

        noOfMembersField = new JTextField(5);
        noOfMembersField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        mygPanel.add(noOfMembersField, gbc);

        amountLabel = new  JLabel("Amount");
        gbc.gridx = 2;
        gbc.gridy = 4;
        mygPanel.add(amountLabel, gbc);

        showAmountLabel = new JLabel();
        gbc.gridx = 3;
        gbc.gridy = 4;
        mygPanel.add(showAmountLabel, gbc);

        totalButton = new  JButton("Total Amount");
        gbc.gridx = 0;
        gbc.gridy = 5;
        mygPanel.add(totalButton, gbc);

        totalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(noOfMembersField.getText().isEmpty()){
                    errorLabel.setText("Please Enter No of Members!");
                     if(!internalRadioButton.isSelected() && !externalRadioButton.isSelected()){
                         errorLabel.setText("Please Select Event!");
                }
                }else {
                    try {
                        errorLabel.setText("");

                        double members = Double.parseDouble(noOfMembersField.getText());
                        double amount = Double.parseDouble(showAmountLabel.getText());

                        double total = members * amount;
                        double patronCommission = total * 0.20;

                        totalLabel.setText(String.valueOf(total));
                        commissionLabel.setText(String.valueOf(patronCommission));
                    } catch (NumberFormatException e1) {
                        errorLabel.setText("Enter Valid Input!");
                    }
                }

            }
        });

        totalLabel = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 5;
        mygPanel.add(totalLabel, gbc);

        patronCommissionLabel = new JLabel("Patron's Commission");
        gbc.gridx = 2;
        gbc.gridy = 5;
        mygPanel.add(patronCommissionLabel, gbc);

        commissionLabel = new JLabel();
        gbc.gridx = 3;
        gbc.gridy = 5;
        mygPanel.add(commissionLabel, gbc);

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 13;
        mygPanel.add(errorLabel, gbc);

        damagesLabel = new JLabel("Any Damages?");
        gbc.gridx = 0;
        gbc.gridy = 7;
        mygPanel.add(damagesLabel, gbc);

        yesRadioButton = new JRadioButton("Yes");
        gbc.gridx = 1;
        gbc.gridy = 7;
        mygPanel.add(yesRadioButton, gbc);

        yesRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    JOptionPane.showMessageDialog(null, "For Damages,\n Captain and Team Members Will Pay\n The Market Value of The Items Damaged PLUS 10% of it!!!");
                    damagesCostLabel.setVisible(true);
                    damagesCostField.setVisible(true);
                    costFineButton.setVisible(true);
                    costFineField.setVisible(true);
                    payButton.setVisible(true);

                }
            }
        });

        noRadioButton = new JRadioButton("No");
        gbc.gridx = 1;
        gbc.gridy = 8;
        mygPanel.add(noRadioButton, gbc);

        noRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    JOptionPane.showMessageDialog(null, "Thank You For Maintaining The Items");
                }
            }
        });

        yesRadioButton.setEnabled(false);
        noRadioButton.setEnabled(false);

        ButtonGroup damage = new ButtonGroup();
        tournaments.add(yesRadioButton);
        tournaments.add(noRadioButton);

        damagesCostLabel = new JLabel("Market Value of Damages");
        damagesCostLabel.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 9;
        mygPanel.add(damagesCostLabel, gbc);

        damagesCostField = new JTextField(5);
        damagesCostField.setVisible(false);
        gbc.gridx = 1;
        gbc.gridy = 9;
        mygPanel.add(damagesCostField, gbc);

        costFineButton = new JButton("Fine For Damages");
        costFineButton.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 10;
        mygPanel.add(costFineButton, gbc);

        costFineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                costFineButtonIsClicked = true;
                try {
                    errorLabel.setText("");
                    double damagesCost = Double.parseDouble(damagesCostField.getText());
                    double fine = damagesCost * 1.10;

                    costFineField.setText(String.valueOf(fine));
                }catch (NumberFormatException ex){
                    errorLabel.setText("Enter Valid Input!");
                    costFineButtonIsClicked = false;
                }

            }
        });

        costFineField = new JTextField(5);
        costFineField.setVisible(false);
        costFineField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 10;
        mygPanel.add(costFineField, gbc);

        payButton = new JButton("Pay For Damages");
        payButton.setVisible(false);
        gbc.gridx = 1;
        gbc.gridy = 11;
        mygPanel.add(payButton, gbc);

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(costFineButtonIsClicked) {
                    JOptionPane.showInputDialog(null, ("Enter MPesa Number"));
                    JOptionPane.showMessageDialog(null, "An Mpesa prompt\n Has Been Sent To The MPesa Number\nPlease Enter MPesa Pin To Complete Transaction!\nAmount " + costFineField.getText() + " Will Be Deducted From Your MPesa Account!\n");
                    JOptionPane.showMessageDialog(null, "Damages Paid For Successfully!");
                    errorLabel.setText("");
                } else{
                    errorLabel.setText("Please Click 'Fine for Damages' button First!");
                }
            }
        });

        backButton = new JButton("Back");
        gbc.gridx = 1;
        gbc.gridy = 12;
        mygPanel.add(backButton, gbc);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gframe.dispose();
                //mainPage gmainpage = new mainPage();
            }
        });


        gframe.add(tPanel, BorderLayout.NORTH);
        gframe.add(mygPanel, BorderLayout.CENTER);
        gframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gframe.setLocationRelativeTo(null);
        gframe.setVisible(true);
    }

    private static String getPatronName(String game){

        switch (game){
            case "Football":
                return "Mohammed Ali";
            case "Rugby":
                return "David Khalwale";
            case "Volleyball":
                return "Mercy Anyango";
            case "Basketball":
                return "John Masila";
            case "Netball":
                return "Faith Kwamboka";
            case "Hockey":
                return "Cheryl Wamboi";
            case "Tennis":
                return "Brian Mark";
            case "Swimming":
                return "Agnes Karori";
            case "Chess":
                return "James Musyoki";
            case "Darts":
                return "Peter Ogulo";
            case "Draft":
                return "Jane Cheptoo";
            default:
                return "";

        }

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new gameFacilitation();
        });
    }

}
