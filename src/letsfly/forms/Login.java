package letsfly.forms;

import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JButton loginButton;
	private JPasswordField passwordField;
	private JButton signUpButton;
	private JTextField usernameTextField;

	public Login() {
		initComponents();
	}

	private void initComponents() {

		loginButton = new JButton();
		loginButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		signUpButton = new JButton();
		usernameTextField = new JTextField();
		usernameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		jLabel1 = new JLabel();
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		jLabel2 = new JLabel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		loginButton.setText("Log in");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loginButtonActionPerformed(evt);
			}
		});

		signUpButton.setText("Sign Up");

		signUpButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				signUpButtonActionPerformed(evt);
			}
		});

		jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18));
		jLabel1.setText("Password");

		jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
		jLabel2.setText("Username");

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(73)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
								.addComponent(jLabel1).addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(passwordField, 203, 203, 203).addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(layout.createSequentialGroup()
										.addComponent(jLabel2).addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(usernameTextField, GroupLayout.PREFERRED_SIZE, 200,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.TRAILING)
												.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 91,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(signUpButton, GroupLayout.PREFERRED_SIZE, 91,
														GroupLayout.PREFERRED_SIZE))
										.addGap(69)))
						.addContainerGap(74, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(36)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(usernameTextField, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
				.addGap(31).addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(signUpButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
				.addGap(62)));
		getContentPane().setLayout(layout);

		pack();
	}

	private Connection connect() {
		String url = "jdbc:sqlite:lib/data.db";
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(url);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return con;
	}

	private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
		login();
	}

	private void login() {
		// log in
		String userName1 = usernameTextField.getText();
		String password1 = new String(passwordField.getPassword());
		String sql = "SELECT username, password, isAdmin FROM accounts";
		try (Connection con = connect(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			// pstmt.setString(1, userName1);
			// pstmt.setString(2, password1); //What is this for?//

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String userName2 = rs.getString("username");
				String password2 = rs.getString("password");
				// String name = rs.getString("name");
				Boolean isAdmin = rs.getBoolean("isAdmin");

				System.out.println(userName2 + "   " + password2 + "   " + isAdmin);
				if (userName2.equals(userName1) && password1.equals(password2)) {
					// log in succesful
					JOptionPane.showMessageDialog(null, "Log In successful");
					if (isAdmin) {
						this.dispose();
						new Admin().setVisible(true);
					}
					return;
				}
			}

			JOptionPane.showMessageDialog(null, "Account does not exist", "", JOptionPane.ERROR_MESSAGE);
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// open sign up window
		this.setVisible(false);
		SignUp s = new SignUp();
		s.setVisible(true);
		usernameTextField.setText("");
		usernameTextField.setText("");
		passwordField.setText("");
		passwordField.setText("");

	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Login().setVisible(true);
			}
		});
	}

}
