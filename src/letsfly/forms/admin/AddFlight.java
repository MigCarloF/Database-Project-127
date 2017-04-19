package letsfly.forms.admin;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class AddFlight extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton addButton;
	private JLabel lblDestTo;
	private JComboBox<String> destToBox;
	private JLabel lblDestFrom;
	private JComboBox<String> destFromBox;
	private JLabel lblAirline;
	private JComboBox<String> airlineBox;
	private JLabel date;
	private JLabel lblDestFromAirport;
	private JComboBox<String> destFromAirportBox;
	private JLabel lblDestToAirport;
	private JComboBox<String> destToAirportBox;
	private JLabel title;
	private JTextField txtYear;
	private JTextField txtMonth;
	private JTextField txtDay;
	private JTextField txtHour;
	private JTextField txtMinute;
	private JToggleButton tglBtnAmpm;
	private String[] listArr;

	private String destFrom;
	private String destTo;

	private String timeOfDay;

	public AddFlight() {
		initComponents();
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

	private void initComponents() {

		title = new JLabel();
		lblDestFrom = new JLabel();
		lblDestTo = new JLabel();
		lblDestFromAirport = new JLabel();
		lblDestToAirport = new JLabel();
		lblAirline = new JLabel();
		destFromBox = new JComboBox<>();
		destToBox = new JComboBox<>();
		destToBox.setEnabled(false);
		destFromAirportBox = new JComboBox<>();
		destFromAirportBox.setEnabled(false);
		destToAirportBox = new JComboBox<>();
		destToAirportBox.setEnabled(false);
		airlineBox = new JComboBox<>();
		airlineBox.setEnabled(false);
		addButton = new JButton();
		addButton.setEnabled(false);
		date = new JLabel();

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				new Admin().setVisible(true);
			}
		});

		title.setFont(new Font("Times New Roman", 1, 18)); // NOI18N
		title.setText("Add Flight");

		lblDestFrom.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
		lblDestFrom.setText("Destination From");

		lblDestTo.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
		lblDestTo.setText("Destination To");

		lblDestFromAirport.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
		lblDestFromAirport.setText("Destination From Airport");

		lblDestToAirport.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
		lblDestToAirport.setText("Destination To Airport");

		lblAirline.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
		lblAirline.setText("Airline");

		destFromBox.setModel(
				new DefaultComboBoxModel<>(new String[] { "Choose...", "Airport 1", "Airport 2", "Airport 3" }));

		destToBox.setModel(
				new DefaultComboBoxModel<>(new String[] { "Choose...", "Airplane 1", "Airplane 2", "Airplane 3" }));

		destFromAirportBox.setModel(
				new DefaultComboBoxModel<>(new String[] { "Choose...", "Country 1", "Country 2", "Country 3" }));

		destToAirportBox.setModel(
				new DefaultComboBoxModel<>(new String[] { "Choose...", "Country 1", "Country 2", "Country 3" }));

		airlineBox.setModel(new DefaultComboBoxModel<>(new String[] { "Choose...", "100", "150", "200" }));

		addButton.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
		addButton.setText("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addButtonActionPerformed(evt);
			}
		});

		date.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
		date.setText("Date");

		txtYear = new JTextField();
		txtYear.setEnabled(false);
		txtYear.setText("YYYY");
		txtYear.setColumns(10);

		txtMonth = new JTextField();
		txtMonth.setEnabled(false);
		txtMonth.setText("MM");
		txtMonth.setColumns(10);

		txtDay = new JTextField();
		txtDay.setEnabled(false);
		txtDay.setText("dd");
		txtDay.setColumns(10);

		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		tglBtnAmpm = new JToggleButton("AM");
		tglBtnAmpm.setEnabled(false);

		txtHour = new JTextField();
		txtHour.setEnabled(false);
		txtHour.setText("HH");
		txtHour.setColumns(10);

		txtMinute = new JTextField();
		txtMinute.setEnabled(false);
		txtMinute.setText("mm");
		txtMinute.setColumns(10);

		// listeners set here
		setListeners();

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(182)
										.addComponent(title, GroupLayout.PREFERRED_SIZE, 96,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGap(35)
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblDestFrom).addComponent(lblDestFromAirport)
												.addComponent(lblDestToAirport).addComponent(lblDestTo)
												.addComponent(lblAirline).addComponent(date).addComponent(lblTime))
										.addGap(54)
										.addGroup(layout.createParallelGroup(Alignment.TRAILING)
												.addComponent(airlineBox, 0, 218, Short.MAX_VALUE)
												.addComponent(destToBox, 0, 218, Short.MAX_VALUE)
												.addComponent(destFromAirportBox, 0, 218, Short.MAX_VALUE)
												.addComponent(destToAirportBox, 0, 218, Short.MAX_VALUE)
												.addComponent(destFromBox, Alignment.LEADING,
														GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
												.addGroup(layout.createSequentialGroup()
														.addGroup(layout.createParallelGroup(Alignment.TRAILING)
																.addComponent(txtHour, GroupLayout.DEFAULT_SIZE, 69,
																		Short.MAX_VALUE)
																.addComponent(txtMonth, GroupLayout.DEFAULT_SIZE, 69,
																		Short.MAX_VALUE))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(layout.createParallelGroup(Alignment.TRAILING)
																.addGroup(layout.createSequentialGroup()
																		.addComponent(txtDay,
																				GroupLayout.PREFERRED_SIZE, 59,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(txtYear,
																				GroupLayout.PREFERRED_SIZE, 78,
																				GroupLayout.PREFERRED_SIZE))
																.addGroup(layout.createSequentialGroup()
																		.addComponent(txtMinute,
																				GroupLayout.DEFAULT_SIZE, 69,
																				Short.MAX_VALUE)
																		.addPreferredGap(ComponentPlacement.UNRELATED)
																		.addComponent(tglBtnAmpm,
																				GroupLayout.PREFERRED_SIZE, 64,
																				GroupLayout.PREFERRED_SIZE)))))))
						.addGap(72))
				.addGroup(layout.createSequentialGroup().addGap(166)
						.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(242, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap().addComponent(title).addGap(34)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblDestFrom)
						.addComponent(destFromBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblDestFromAirport)
						.addComponent(destFromAirportBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(destToBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDestTo))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblDestToAirport)
						.addComponent(destToAirportBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblAirline)
						.addComponent(airlineBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(date)
						.addComponent(txtMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(txtYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblTime).addComponent(tglBtnAmpm)
						.addComponent(txtHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(txtMinute, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
				.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
		getContentPane().setLayout(layout);
		setResizable(false);
		pack();
	}

	private void setListeners() {

		ArrayList<String> list = new ArrayList<String>();
		list.add("Choose...");
		try {
			String sql = "SELECT DISTINCT country FROM airports";
			Connection con = connect();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("country"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		listArr = new String[list.size()];
		listArr = list.toArray(listArr);

		destFromBox.setModel(new DefaultComboBoxModel<>(listArr));
		list.clear();

		destFromBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (destFromBox.getSelectedIndex() == 0) {
					resetForm();
				} else {
					list.add("Choose...");
					try {
						String sql = "SELECT DISTINCT airportID FROM airports WHERE country == '"
								+ destFromBox.getSelectedItem() + "'";
						Connection con = connect();
						PreparedStatement pstmt = con.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery();
						while (rs.next()) {
							list.add(rs.getString("airportID"));
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					listArr = new String[list.size()];
					listArr = list.toArray(listArr);
					destFromAirportBox.setModel(new DefaultComboBoxModel<>(listArr));
					list.clear();
					destFromAirportBox.setEnabled(true);
					destFrom = (String) destFromBox.getSelectedItem();
				}
			}
		});

		destFromAirportBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (destFromAirportBox.getSelectedIndex() == 0) {
					disableTo();
					disableToAirport();
					disableAirline();
					disableDate();
					disableTime();
					addButton.setEnabled(false);
				} else {
					list.clear();
					list.add("Choose...");
					try {
						String sql = "SELECT DISTINCT country FROM airports WHERE country != '" + destFrom + "'";
						Connection con = connect();
						PreparedStatement pstmt = con.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery();
						while (rs.next()) {
							list.add(rs.getString("country"));
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					listArr = new String[list.size()];
					listArr = list.toArray(listArr);
					destToBox.setModel(new DefaultComboBoxModel<>(listArr));
					destToBox.setEnabled(true);
				}
			}
		});

		destToBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (destToBox.getSelectedIndex() == 0) {
					disableToAirport();
					disableAirline();
					disableDate();
					disableTime();
					addButton.setEnabled(false);
				} else {
					list.clear();
					list.add("Choose...");
					try {
						String sql = "SELECT DISTINCT airportID FROM airports WHERE country == '"
								+ destToBox.getSelectedItem() + "'";
						Connection con = connect();
						PreparedStatement pstmt = con.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery();
						while (rs.next()) {
							list.add(rs.getString("airportID"));
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					listArr = new String[list.size()];
					listArr = list.toArray(listArr);
					destToAirportBox.setModel(new DefaultComboBoxModel<>(listArr));
					destToAirportBox.setEnabled(true);
					destTo = (String) destToBox.getSelectedItem();
				}
			}
		});

		destToAirportBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (destToAirportBox.getSelectedIndex() == 0) {
					disableAirline();
					disableDate();
					disableTime();
					addButton.setEnabled(false);
				} else {
					list.clear();
					list.add("Choose...");
					try {
						String sql = "SELECT DISTINCT airlines.airlineID FROM airlines "
								+ "INNER JOIN airports ON airports.airportID = airlines.airportID "
								+ "WHERE country = '" + destFrom + "' INTERSECT "
								+ "SELECT DISTINCT airlines.airlineID FROM AIRLINES "
								+ "INNER JOIN airports ON airports.airportID = airlines.airportID "
								+ "WHERE country = '" + destTo + "'";
						Connection con = connect();
						PreparedStatement pstmt = con.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery();
						while (rs.next()) {
							list.add(rs.getString("airlines.airlineID"));
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					listArr = new String[list.size()];
					listArr = list.toArray(listArr);
					airlineBox.setModel(new DefaultComboBoxModel<>(listArr));
					airlineBox.setEnabled(true);
				}
			}
		});

		airlineBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (airlineBox.getSelectedIndex() == 0) {
					disableDate();
					disableTime();
					addButton.setEnabled(false);
				} else {
					txtMonth.setEnabled(true);
					txtDay.setEnabled(true);
					txtYear.setEnabled(true);
					txtMinute.setEnabled(true);
					txtHour.setEnabled(true);
					tglBtnAmpm.setEnabled(true);
					addButton.setEnabled(true);
				}
			}
		});

		txtMonth.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtMonth.getText().equals("MM") == false) {
					if (numberCheck(txtMonth.getText())) {
						int month = Integer.parseInt(txtMonth.getText());
						if (month < 1 || month > 12) {
							JOptionPane.showMessageDialog(null, "Invalid month. 1 - 12 only", "",
									JOptionPane.ERROR_MESSAGE);
							txtMonth.setText("MM");
						}
					} else {
						txtMonth.setText("MM");
					}
				}
			}
		});

		txtDay.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				if (txtDay.getText().equals("dd") == false) {
					if (txtMonth.getText().equals("MM")) {
						JOptionPane.showMessageDialog(null, "Input month first", "", JOptionPane.ERROR_MESSAGE);
						txtDay.setText("dd");
					} else if (numberCheck(txtDay.getText())) {
						int day = Integer.parseInt(txtDay.getText());
						int month = Integer.parseInt(txtMonth.getText());
						if (month == 2) {
							if (day < 1 || day > 28) {
								JOptionPane.showMessageDialog(null, "Invalid day. 1 - 28 only", "",
										JOptionPane.ERROR_MESSAGE);
								txtDay.setText("dd");
							}
						} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
								|| month == 12) {
							if (day < 1 || day > 31) {
								JOptionPane.showMessageDialog(null, "Invalid day. 1 - 31 only", "",
										JOptionPane.ERROR_MESSAGE);
								txtDay.setText("dd");
							}
						} else {
							if (day < 1 || day > 30) {
								JOptionPane.showMessageDialog(null, "Invalid day. 1 - 30 only", "",
										JOptionPane.ERROR_MESSAGE);
								txtDay.setText("dd");
							}
						}
					} else {
						txtDay.setText("dd");
					}
				}
			}
		});

		txtYear.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtYear.getText().equals("YYYY") == false) {
					if (numberCheck(txtYear.getText())) {
						int currentYear = Calendar.getInstance().get(Calendar.YEAR);
						int year = Integer.parseInt(txtYear.getText());
						if (year < currentYear) {
							JOptionPane.showMessageDialog(null, "Invalid year, time travel not possible", "",
									JOptionPane.ERROR_MESSAGE);
							txtMonth.setText("YY");
						}
					} else {
						txtMonth.setText("YY");
					}
				}
			}
		});

		txtHour.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtHour.getText().equals("HH") == false) {
					if (numberCheck(txtHour.getText())) {
						int hour = Integer.parseInt(txtHour.getText());
						if (hour < 1 || hour > 12) {
							JOptionPane.showMessageDialog(null, "Invalid hour. 1 - 12 only", "",
									JOptionPane.ERROR_MESSAGE);
							txtHour.setText("HH");
						}
					} else {
						txtHour.setText("HH");
					}
				}
			}
		});

		txtMinute.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtMinute.getText().equals("HH") == false) {
					if (numberCheck(txtMinute.getText())) {
						int min = Integer.parseInt(txtMinute.getText());
						if (min < 0 || min > 59) {
							JOptionPane.showMessageDialog(null, "Invalid Minute. 0 - 59 only", "",
									JOptionPane.ERROR_MESSAGE);
							txtMinute.setText("mm");
						}
					} else {
						txtMinute.setText("mm");
					}
				}
			}
		});

		tglBtnAmpm.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tglBtnAmpm.isSelected()) {
					tglBtnAmpm.setText("PM");
				} else {
					tglBtnAmpm.setText("AM");
				}
			}
		});
	}

	private Boolean numberCheck(String text) { // returns true if no exceptions
												// were encountered
		try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Numbers only", "", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	private void resetForm() {
		disableFromAirport();
		disableTo();
		disableToAirport();
		disableAirline();
		disableDate();
		disableTime();
		addButton.setEnabled(false);
	}

	private void disableFromAirport() {
		destFromAirportBox.setSelectedIndex(0);
		destFromAirportBox.setEnabled(false);
	}

	private void disableTo() {
		destToBox.setSelectedIndex(0);
		destToBox.setEnabled(false);
	}

	private void disableToAirport() {
		destToAirportBox.setSelectedIndex(0);
		destToAirportBox.setEnabled(false);
	}

	private void disableAirline() {
		airlineBox.setSelectedIndex(0);
		airlineBox.setEnabled(false);
	}

	private void disableDate() {
		txtMonth.setText("MM");
		txtMonth.setEnabled(false);
		txtDay.setText("dd");
		txtDay.setEnabled(false);
		txtYear.setText("YYYY");
		txtYear.setEnabled(false);
	}

	private void disableTime() {
		txtHour.setText("HH");
		txtHour.setEnabled(false);
		txtMinute.setText("mm");
		txtMinute.setEnabled(false);
		tglBtnAmpm.setSelected(false);
		tglBtnAmpm.setText("AM");
		tglBtnAmpm.setEnabled(false);
	}

	private void addButtonActionPerformed(ActionEvent evt) {
		if (tglBtnAmpm.isEnabled() == false || txtHour.getText().equals("HH") || txtMinute.getText().equals("mm")
				|| txtMonth.getText().equals("MM") || txtDay.getText().equals("dd")
				|| txtYear.getText().equals("YYYY")) {
			JOptionPane.showMessageDialog(null, "Incomplete data", "", JOptionPane.ERROR_MESSAGE);
		} else if (Calendar.getInstance().get(Calendar.MONTH) < Integer.parseInt(txtMonth.getText())
				&& Calendar.getInstance().get(Calendar.YEAR) == Integer.parseInt(txtYear.getText())) {

			JOptionPane.showMessageDialog(null, "Invalid date, time travel not possible", "",
					JOptionPane.ERROR_MESSAGE);
		} else if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) < Integer.parseInt(txtDay.getText())
				&& Calendar.getInstance().get(Calendar.MONTH) == Integer.parseInt(txtMonth.getText())
				&& Calendar.getInstance().get(Calendar.YEAR) == Integer.parseInt(txtYear.getText())) {
			JOptionPane.showMessageDialog(null, "Invalid date, time travel not possible", "",
					JOptionPane.ERROR_MESSAGE);
		} else {
			String sql = "Insert Into flights (airlineID, destinationFrom, destinationTo, date, departureTime) "
					+ "Values (?, ?, ?, ?, ?)";

			try (Connection con = connect(); PreparedStatement pstmt = con.prepareStatement(sql)) {

				pstmt.setString(1, airlineBox.getSelectedItem().toString());
				pstmt.setString(2, destFromAirportBox.getSelectedItem().toString() + ", " + destFrom);
				pstmt.setString(3, destToAirportBox.getSelectedItem().toString() + ", " + destTo);
				pstmt.setString(4, txtMonth.getText().toString() + "/" + txtDay.getText().toString() + "/"
						+ txtYear.getText().toString());
				// pstmt.setString(5, dateBox.getSelectedItem().toString());
				if (tglBtnAmpm.isSelected()) {
					timeOfDay = "PM";
				} else {
					timeOfDay = "AM";
				}
				pstmt.setString(5, txtHour.getText().toString() + ":" + txtMinute.getText().toString() + timeOfDay);

				pstmt.executeUpdate();
				con.close();
				pstmt.close();
				JOptionPane.showMessageDialog(null, "Successfully added flight");
				int answer = JOptionPane.showConfirmDialog(this, "Do you want to add another flight?", "",
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.NO_OPTION) {
					this.dispose();
					new Admin().setVisible(true);
				} else {
					destToBox.setSelectedIndex(0);
					destFromBox.setSelectedIndex(0);
					destFromAirportBox.setSelectedIndex(0);
					destToAirportBox.setSelectedIndex(0);
					airlineBox.setSelectedIndex(0);
					// dateBox.setSelectedIndex(0);
				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
