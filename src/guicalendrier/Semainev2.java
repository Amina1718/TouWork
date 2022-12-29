package guicalendrier;

import javax.swing.*;
import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import calendrier.Date;
import calendrier.Event;

public class Semainev2 extends JFrame {

	private static final int NUM_HOURS = 16;
	public Object[][] data = new Object[NUM_HOURS][8];

	public Semainev2() {
		setTitle("Emploi Du Temps De La Semaine ");
		setSize(15000, 1500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// String[] columnNames = {"Heure", "Lundi", "Mardi", "Mercredi", "Jeudi",
		// "Vendredi", "Samedi", "Dimanche"};
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

		final JTable table = new JTable(data, columnNames);
		table.setShowGrid(true);
		table.setGridColor(Color.BLACK);
		table.setDefaultRenderer(Object.class, new Schedule());
		table.setRowHeight(40);
		table.setDefaultEditor(Object.class, null);
		
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				
				int row = (table.rowAtPoint(click.getPoint())+8);
			    int col = table.columnAtPoint(click.getPoint());
			    
			    String heureDebut= Integer.toString(row) + ":00";
			    if (row < 10) {
			    	heureDebut="0"+heureDebut;
			    }
			    
			     String heureFin= Integer.toString(row+1)+ ":00";
			    if (row < 9) {
			    	heureFin="0"+heureFin;
			    }
			   
			    
			    String jour = Integer.toString(col);
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
			    String numjour = LocalDate.now().with(DayOfWeek.MONDAY).format(formatter);
			    int numJour = (Integer.parseInt(numjour)+ col)-1;
			    System.out.println("numjour="+ numJour);
			    DateTimeFormatter formatterM = DateTimeFormatter.ofPattern("MM");
			    int numMois= Integer.parseInt(LocalDate.now().with(DayOfWeek.MONDAY).format(formatterM));
			    
			    formatter = DateTimeFormatter.ofPattern("yyyy");
			    int numAnnee= Integer.parseInt(LocalDate.now().with(DayOfWeek.MONDAY).format(formatter));
			    
			    System.out.println("numjour="+numJour+"numMois="+ numMois+"annee="+numAnnee);
			    
			    
			    System.out.println("jour="+jour);
			    
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
							
						
							Date startTime = new Date(numAnnee,numMois, numJour,getHourFromString(startTimeField.getText()), getMinuteFromString(startTimeField.getText()));
							
							Date endTime = new Date(numAnnee,numMois, numJour,getHourFromString(endTimeField.getText()), getMinuteFromString(endTimeField.getText()));
							 //endTimeField.getText();
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
					frame.add(addButton);
					frame.add(cancelButton);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setSize(800, 200);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
			}
		});
		add(new JScrollPane(table));
	}

	//public void addEvent(Event event) {
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
		//System.out.println(event.getTitle());
		//int jourEvent = event.getStartTime().getDay();
		//String numjour = LocalDate.now().with(DayOfWeek.MONDAY).format(formatter);
		//int numJour = Integer.parseInt(numjour);
		//int numCol = (jourEvent - numJour) + 1;
		//int duree = (event.getEndTime().getHour()) - (event.getStartTime().getHour());
		//for (int i = 0; i < duree; i++) {
			//this.data[event.getStartTime().getHour() - 8 + i][numCol] = event.getTitle();
		//}
	//}
	
	
	
	public void addEvent(Event event) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
			System.out.println(event.getTitle());
			int jourEvent = event.getStartTime().getDay();
			String numjour = LocalDate.now().with(DayOfWeek.MONDAY).format(formatter);
			int numJour = Integer.parseInt(numjour);
			int numCol = (jourEvent - numJour) + 1;
			if(numCol<1) {
				numCol+= LocalDate.now().minusMonths(1).lengthOfMonth();
			}
			else if(numCol>7) {
				numCol -= LocalDate.now().lengthOfMonth();
			}
			
			int duree = (event.getEndTime().getHour()) - (event.getStartTime().getHour());
			for (int i = 0; i < duree; i++) {
				this.data[event.getStartTime().getHour() - 8 + i][numCol] = event.getTitle()+"\n" + event.getDescription() ;
			}
		}
	
	public int getHourFromString(String hour) {
		return Integer.parseInt(hour.substring(0,2));
	}
	
	public int getMinuteFromString(String minute) {
		return Integer.parseInt(minute.substring(3,5));
	}

	
	
	private class Schedule extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,column);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setVerticalAlignment(SwingConstants.CENTER);
			//if (column > 0 && column % 2 == 1) {
			Color beige = new Color(245, 245, 220);
			label.setBackground(beige);
			//}
			return label;
		}
	}
	
	public static void main(String[] args) {
        Semainev2 frame = new Semainev2();
        frame.setVisible(true);
    }

}





