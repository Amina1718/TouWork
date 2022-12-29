package guicalendrier;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import calendrier.Date;
import calendrier.Event;
import calendrier.Calendar;

public class Semaine extends JFrame implements ActionListener {

	private static final int NUM_HOURS = 16;
	public Object[][] data = new Object[NUM_HOURS][8];
	private LocalDate currentWeek;

	public Semaine() {
		setTitle("Emploi Du Temps De La Semaine ");
		setSize(1500, 1500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
		String[] columnNames = { "Heure", "Lundi " + LocalDate.now().with(DayOfWeek.MONDAY).format(formatter),
				"Mardi " + LocalDate.now().with(DayOfWeek.TUESDAY).format(formatter),
				"Mercredi " + LocalDate.now().with(DayOfWeek.WEDNESDAY).format(formatter),
				"Jeudi " + LocalDate.now().with(DayOfWeek.THURSDAY).format(formatter),
				"Vendredi " + LocalDate.now().with(DayOfWeek.FRIDAY).format(formatter),
				"Samedi " + LocalDate.now().with(DayOfWeek.SATURDAY).format(formatter),
				"Dimanche " + LocalDate.now().with(DayOfWeek.SUNDAY).format(formatter) };

		for (int i = 0; i < NUM_HOURS; i++) {
			this.data[i][0] = Integer.toString(i + 8) + ":00";
		}

		currentWeek = LocalDate.now().with(DayOfWeek.MONDAY);
		updateWeekView(currentWeek);

		// Ajout des boutons "Semaine précédente" et "Semaine suivante" à l'interface
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		JButton previousWeekButton = new JButton("Semaine précédente");
		previousWeekButton.addActionListener(this);
		buttonPanel.add(previousWeekButton);
		JButton nextWeekButton = new JButton("Semaine suivante");
		nextWeekButton.addActionListener(this);
		buttonPanel.add(nextWeekButton);

		JPanel navPanel = new JPanel();
		navPanel.add(previousWeekButton);
		navPanel.add(nextWeekButton);
		add(navPanel, BorderLayout.NORTH);

		// Ajout de la table à la vue

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Semaine précédente")) {
			currentWeek = currentWeek.minusWeeks(1);
		} else if (e.getActionCommand().equals("Semaine suivante")) {
			currentWeek = currentWeek.plusWeeks(1);
		}
		updateWeekView(currentWeek);
	}

	private void updateWeekView(LocalDate week) {
		// Mise à jour des noms de colonnes avec les dates de la semaine
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
		String[] columnNames = { "Heure", "Lundi " + week.format(formatter),
			    "Mardi " + week.plusDays(1).format(formatter),
			    "Mercredi " + week.plusDays(2).format(formatter),
			    "Jeudi " + week.plusDays(3).format(formatter),
			    "Vendredi " + week.plusDays(4).format(formatter),
			    "Samedi " + week.plusDays(5).format(formatter),
			    "Dimanche " + week.plusDays(6).format(formatter) };

		for (int i = 0; i < NUM_HOURS; i++) {
			this.data[i][0] = Integer.toString(i + 8) + ":00";
		}

// Mise à jour de la vue de la semaine
		final JTable table = new JTable(data, columnNames);
		table.setShowGrid(true);
		table.setGridColor(Color.BLACK);
		table.setDefaultRenderer(Object.class, new Schedule());
		table.setRowHeight(40);
		table.setDefaultEditor(Object.class, null);

// Ajout de la vue de la semaine à l'interface
		add(table, BorderLayout.CENTER);
		revalidate();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {

				int row = (table.rowAtPoint(click.getPoint()) + 8);
				int col = table.columnAtPoint(click.getPoint());

				String heureDebut = Integer.toString(row) + ":00";
				if (row < 10) {
					heureDebut = "0" + heureDebut;
				}

				String heureFin = Integer.toString(row + 1) + ":00";
				if (row < 9) {
					heureFin = "0" + heureFin;
				}

				String jour = Integer.toString(col);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
				String numjour = LocalDate.now().with(DayOfWeek.MONDAY).format(formatter);
				int numJour = (Integer.parseInt(numjour) + col) - 1;
				System.out.println("numjour=" + numJour);
				DateTimeFormatter formatterM = DateTimeFormatter.ofPattern("MM");
				int numMois = Integer.parseInt(LocalDate.now().with(DayOfWeek.MONDAY).format(formatterM));

				formatter = DateTimeFormatter.ofPattern("yyyy");
				int numAnnee = Integer.parseInt(LocalDate.now().with(DayOfWeek.MONDAY).format(formatter));

				System.out.println("numjour=" + numJour + "numMois=" + numMois + "annee=" + numAnnee);

				System.out.println("jour=" + jour);

				if (click.getClickCount() == 1) {

					// nvlle fntre
					JFrame frame = new JFrame("Ajouter un cours");
					JTextField titleField = new JTextField();
					JTextField descriptionField = new JTextField();
					JTextField startTimeField = new JTextField(heureDebut);
					JTextField endTimeField = new JTextField(heureFin);
					frame.setLayout(new GridLayout(5, 2));
					frame.add(new JLabel("Nom:"));
					frame.add(titleField);
					frame.add(new JLabel("Description:"));
					frame.add(descriptionField);
					frame.add(new JLabel("Heure de début:"));
					frame.add(startTimeField);
					frame.add(new JLabel("Heure de fin:"));
					frame.add(endTimeField);

					JButton addButton = new JButton("Ajouter");
					addButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// Récupérez les données saisies dans les champs de texte
							String title = titleField.getText();
							String description = descriptionField.getText();

							Date startTime = new Date(numAnnee, numMois, numJour,
									getHourFromString(startTimeField.getText()),
									getMinuteFromString(startTimeField.getText()));

							Date endTime = new Date(numAnnee, numMois, numJour,
									getHourFromString(endTimeField.getText()),
									getMinuteFromString(endTimeField.getText()));
							// endTimeField.getText();
							Event event = new Event(title, description, startTime, endTime);
							addEvent(event);
							frame.dispose();
						}
					});

					JButton cancelButton = new JButton("Annuler");
					cancelButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							frame.dispose();
						}
					});
					
					// Ajout du bouton "Supprimer" à l'interface
					calendrier.Calendar calendar = new calendrier.Calendar();
					JButton deleteButton = new JButton("Supprimer");
					deleteButton.addActionListener(new ActionListener() {
					    @Override
					    public void actionPerformed(ActionEvent e) {
					    	String title = titleField.getText();
							String description = descriptionField.getText();

							Date startTime = new Date(numAnnee, numMois, numJour,
									getHourFromString(startTimeField.getText()),
									getMinuteFromString(startTimeField.getText()));

							Date endTime = new Date(numAnnee, numMois, numJour,
									getHourFromString(endTimeField.getText()),
									getMinuteFromString(endTimeField.getText()));
						
					        // Récupération de l'événement sélectionné dans la table
					        int row = table.getSelectedRow();
					        int col = table.getSelectedColumn();
					        if (col > 0) {
					            Event event = new Event(title, description, startTime, endTime);
					            if (event != null) {
					                // Demande de confirmation de suppression de l'événement
					                int result = JOptionPane.showConfirmDialog(Semaine.this, "Voulez-vous supprimer cet événement?", "Confirmation", JOptionPane.YES_NO_OPTION);
					                if (result == JOptionPane.YES_OPTION) {
					                    // Suppression de l'événement
					                    calendar.removeEvent(event);
					                    // Mise à jour de l'affichage de la semaine
					                    updateWeekView(currentWeek);
					                }
					            }
					        }
					    }
					});
					
					frame.add(addButton);
					frame.add(deleteButton);
					frame.add(cancelButton);
					
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setSize(1000, 150);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
			}
		});
        add(new JScrollPane(table), BorderLayout.CENTER);
        
  
        table.addMouseListener(new MouseAdapter() {
        	Calendar calendar= new Calendar();
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (col > 0) {
                    // Récupération de l'événement à cet emplacement dans le tableau
                    Event event = calendar.getEventAt(currentWeek, DayOfWeek.of(col), LocalTime.parse((String) data[row][0]));
                    if (event != null) {
                        // Demande de confirmation de suppression de l'événement
                        int result = JOptionPane.showConfirmDialog(Semaine.this, "Voulez-vous supprimer cet événement?", "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            // Suppression de l'événement
                            calendar.removeEvent(event);
                            // Mise à jour de l'affichage de la semaine
                            updateWeekView(currentWeek);
                        }
                    }
                }
            }
        });

        
        
        
	}

	public void addEvent(Event event) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
		System.out.println(event.getTitle());
		int jourEvent = event.getStartTime().getDay();
		String numjour = LocalDate.now().with(DayOfWeek.MONDAY).format(formatter);
		int numJour = Integer.parseInt(numjour);
		int numCol = (jourEvent - numJour) + 1;
		if (numCol < 1) {
			numCol += LocalDate.now().minusMonths(1).lengthOfMonth();
		} else if (numCol > 7) {
			numCol -= LocalDate.now().lengthOfMonth();
		}

		int duree = (event.getEndTime().getHour()) - (event.getStartTime().getHour());
		for (int i = 0; i < duree; i++) {
			this.data[event.getStartTime().getHour() - 8 + i][numCol] = event.getTitle() + "\n"
					+ event.getDescription();
		}
	}

	public int getHourFromString(String hour) {
		return Integer.parseInt(hour.substring(0, 2));
	}

	public int getMinuteFromString(String minute) {
		return Integer.parseInt(minute.substring(3, 5));
	}

	private class Schedule extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
					column);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setVerticalAlignment(SwingConstants.CENTER);
			// if (column > 0 && column % 2 == 1) {
			Color beige = new Color(245, 245, 220);
			label.setBackground(beige);
			// }
			return label;
		}
	}

}
