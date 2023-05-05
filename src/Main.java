import java.awt.*;import java.awt.event.*;
import javax.swing.*;
public class Main extends JFrame implements ActionListener {    private static final long serialVersionUID = 1L;
    private JTextArea chatHistory;    private JTextField chatInput;
    private JButton sendButton;    private JLabel statusLabel;
    private String senderName;    private String receiverName;
    public Main(String sender, String receiver) {
        super(sender + " Chatting with " + receiver);        senderName = sender;
        receiverName = receiver;
        setDefaultCloseOperation(EXIT_ON_CLOSE);        setLayout(new BorderLayout());
        JPanel chatPanel = new JPanel(new BorderLayout());
        chatHistory = new JTextArea();        chatHistory.setEditable(false);
        chatPanel.add(new JScrollPane(chatHistory), BorderLayout.CENTER);
        JPanel inputPanel = new JPanel(new BorderLayout());        chatInput = new JTextField();
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);        statusLabel = new JLabel("Online");
        inputPanel.add(chatInput, BorderLayout.CENTER);        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.add(statusLabel, BorderLayout.WEST);
        add(chatPanel, BorderLayout.CENTER);        add(inputPanel, BorderLayout.SOUTH);
        setSize(400, 400);
        setLocationRelativeTo(null);        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        String message = chatInput.getText().trim();
        if (message.isEmpty()) {
            return;
        }        String chat = senderName + " to " + receiverName + ": " + message + "\n";
        chatHistory.append(chat);
        chatInput.setText("");
    }
    public static void main(String[] args) {        String sender = "Ruslan";
        String receiver = "Nursultan";        Main chatApp = new Main(sender, receiver);
    }}
