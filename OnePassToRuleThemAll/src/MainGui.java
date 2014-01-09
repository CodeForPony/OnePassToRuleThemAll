import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Choice;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MainGui extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainGui frame = new MainGui();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGui()
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 86);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMasterPass = new JLabel("Master Pass:");
		lblMasterPass.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMasterPass.setBounds(10, 11, 101, 14);
		contentPane.add(lblMasterPass);

		passwordField = new JPasswordField();

		passwordField.setBounds(121, 8, 223, 20);
		contentPane.add(passwordField);

		final Choice choice = new Choice();
		choice.setBounds(10, 31, 105, 20);
		contentPane.add(choice);
		choice.add("Google");
		choice.add("Facebook");
		choice.add("Twitter");
		choice.add("Whatever");

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(121, 31, 223, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		final JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (String.valueOf(passwordField.getPassword()).length() < 6)
				{
					textField.setText("Master password too short.");
				} else
				{
					textField.setText(md5(choice.getSelectedItem() + String.valueOf(passwordField.getPassword())));
				}
			}
		});
		getRootPane().setDefaultButton(btnGenerate);
		btnGenerate.setBounds(354, 7, 120, 44);
		contentPane.add(btnGenerate);
		// passwordField.addKeyListener(new KeyAdapter() {
		// @Override
		// public void keyPressed(KeyEvent arg0) {
		//
		// }
		// });
	}

	public String md5(String s)
	{
		try
		{
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++)
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			return hexString.toString();

		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return "";
	}
}
