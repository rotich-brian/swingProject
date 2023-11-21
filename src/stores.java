import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class stores extends JFrame {

    private Map<String, Integer> itemPrices;
    private Map<String, JCheckBox> itemCheckboxes;
    private Map<String, JTextField> itemQuantityFields;
    private JTextArea receiptArea;
    int maxStockLevel = 100;

    public stores() {
        itemPrices = new HashMap<>();
        itemCheckboxes = new HashMap<>();
        itemQuantityFields = new HashMap<>();
        initializeItemPrices();

        setTitle("Sports Club Store");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel storeInfoPanel = new JPanel();
        storeInfoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel title = new JLabel("MARINGO SPORTS CLUB STORE");
        Font font = new Font("Arial", Font.BOLD, 16);
        title.setForeground(Color.BLUE);
        title.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 0;
        storeInfoPanel.add(title, gbc);

        JLabel clerk = new JLabel("Clerk: ");
        clerk.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        storeInfoPanel.add(clerk, gbc);

        JLabel clerkName = new JLabel("JOHN POMBE");
        clerkName.setFont(font);
        clerkName.setForeground(Color.GREEN);
        gbc.gridx = 1;
        gbc.gridy = 1;
        storeInfoPanel.add(clerkName, gbc);

        JLabel buyerName = new JLabel("Buyer's Name");
        buyerName.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        storeInfoPanel.add(buyerName, gbc);

        JTextField buyerNameTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        storeInfoPanel.add(buyerNameTextField, gbc);

        JLabel error = new JLabel("Please Enter Buyer's Name!");
        error.setVisible(false);
        error.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 3;
        storeInfoPanel.add(error, gbc);


        JPanel purchasePanel = new JPanel();

        JPanel itemsPanel = new JPanel(new GridLayout(0, 5, 2, 5));

        for (String item : itemPrices.keySet()) {
            JLabel itemLabel = new JLabel(item);
            JLabel priceLabel = new JLabel("KSh " + itemPrices.get(item));
            JTextField quantityField = new JTextField("1", 2);
            JCheckBox checkBox = new JCheckBox();
            checkBox.setName(item); // Set the name of the checkbox to the item name

            itemCheckboxes.put(item, checkBox);
            itemQuantityFields.put(item, quantityField);

            itemsPanel.add(checkBox);
            itemsPanel.add(itemLabel);
            itemsPanel.add(priceLabel);
            itemsPanel.add(new JLabel("Quantity:"));
            itemsPanel.add(quantityField);

        }

        JButton sbackButton = new JButton("Back");
        purchasePanel.add(sbackButton);
        JButton totalButton = new JButton("Total");
        purchasePanel.add(totalButton);
        JButton purchaseButton = new JButton("Purchase");
        purchasePanel.add(purchaseButton);


        add(storeInfoPanel, BorderLayout.NORTH);
        add(itemsPanel, BorderLayout.CENTER);
        add(purchasePanel, BorderLayout.SOUTH);

        receiptArea = new JTextArea();
        receiptArea.setEditable(false);

        add(receiptArea, BorderLayout.EAST);

        sbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              dispose();

              //mainPage mMainPage = new mainPage();
            }
        });

        totalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buyerNameTextField.getText().isEmpty()){
                    error.setVisible(true);
                }
                purchaseItem();
            }
        });

        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInputDialog(null, ("Enter MPesa Number"));
                JOptionPane.showMessageDialog(null, "An Mpesa prompt\nHas Been Sent To The MPesa Number\nPlease Enter MPesa Pin To Complete Transaction!\n");
                JOptionPane.showMessageDialog(null, "Payment Successfull!");

            }
        });
    }

    private void initializeItemPrices() {
        itemPrices.put("Bloomer", 250);
        itemPrices.put("Games shorts", 750);
        itemPrices.put("Hockey stick", 2000);
        itemPrices.put("Soccer Cleats", 4000);
        itemPrices.put("Running Shoes", 2500);
        itemPrices.put("Cross-Training shoes", 1500);
        itemPrices.put("Socks", 350);
        itemPrices.put("Track suit", 1000);
        itemPrices.put("T-shirt", 800);
        itemPrices.put("Wrapper", 450);
    }

    private void purchaseItem() {
        int totalAmount = 0;

        receiptArea.setText(""); // Clear previous content

        for (String item : itemCheckboxes.keySet()) {
            JCheckBox checkBox = itemCheckboxes.get(item);
            JTextField quantityField = itemQuantityFields.get(item);

            if (checkBox.isSelected()) {
                int quantity = Integer.parseInt(quantityField.getText());
                maxStockLevel -= quantity;

                int itemPrice = itemPrices.get(item);
                int itemTotalAmount = quantity * itemPrice;

                totalAmount += itemTotalAmount;

                receiptArea.append("Item: " + item + "\n");
                receiptArea.append("Quantity: " + quantity + "\n");
                receiptArea.append("Amount: KSh " + itemTotalAmount + "\n\n");

                checkStockLevel(item, quantity);
            }
        }

        if (totalAmount > 10000) {
            totalAmount -= (totalAmount * 0.05);
            receiptArea.append("5% for Purchasing items above 10,000 \n");
        }

        receiptArea.append("Total Amount: KSh " + totalAmount + "\n\n");

    }


    private void checkStockLevel(String selectedItem, int quantity) {

        if (maxStockLevel > (0.2 * maxStockLevel)) {
            JOptionPane.showMessageDialog(this, "Stock level for " + selectedItem + " is low. Reorder needed!",
                    "Stock Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new stores().setVisible(true);
            }
        });
    }
}
