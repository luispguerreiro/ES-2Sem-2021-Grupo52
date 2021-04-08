package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtSrcPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setResizable(false);
		setTitle("Code Quality Assessor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 901, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Pasta");
		btnNewButton.setBounds(774, 415, 103, 33);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.setLayout(null);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnNewButton);
		
		JButton btnRun = new JButton("Run");
		btnRun.setBounds(774, 458, 103, 33);
		btnRun.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnRun);
		
		txtSrcPath = new JTextField();
		txtSrcPath.setBounds(10, 415, 754, 33);
		txtSrcPath.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSrcPath.setText("SRC PATH");
		contentPane.add(txtSrcPath);
		txtSrcPath.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 206, 395);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JList list = new JList();
		list.setBounds(10, 40, 186, 270);
		panel.add(list);
		
		JButton btnNewButton_1 = new JButton("Editar...");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(10, 333, 186, 21);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Importar Regras");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(10, 364, 186, 21);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("Lista de Regras importadas");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 186, 31);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(635, 10, 242, 210);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Resumo visualiza\u00E7\u00E3o das m\u00E9tricas:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 10, 222, 13);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("N\u00BA packages:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 33, 98, 13);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("N\u00BA classes:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 56, 98, 13);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("N\u00BA m\u00E9todos:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 79, 98, 13);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("N\u00BA linhas:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(10, 102, 98, 13);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("n");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(187, 34, 45, 13);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("n");
		lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6_1.setBounds(187, 57, 45, 13);
		panel_1.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_6_2 = new JLabel("n");
		lblNewLabel_6_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6_2.setBounds(187, 80, 45, 13);
		panel_1.add(lblNewLabel_6_2);
		
		JLabel lblNewLabel_6_3 = new JLabel("n");
		lblNewLabel_6_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6_3.setBounds(187, 103, 45, 13);
		panel_1.add(lblNewLabel_6_3);
		
		JButton btnAbrirExcel = new JButton("Abrir Excel");
		btnAbrirExcel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAbrirExcel.setBounds(129, 126, 103, 33);
		panel_1.add(btnAbrirExcel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(635, 214, 242, 191);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Verdadeiros Positivos:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(10, 10, 121, 13);
		panel_2.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Verdadeiros Negativos:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_2.setBounds(10, 33, 133, 13);
		panel_2.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Falsos Positvos:");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_3.setBounds(10, 56, 98, 13);
		panel_2.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("Falsos Negativos:");
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_4.setBounds(10, 79, 98, 13);
		panel_2.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_6_4 = new JLabel("n");
		lblNewLabel_6_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6_4.setBounds(187, 11, 45, 13);
		panel_2.add(lblNewLabel_6_4);
		
		JLabel lblNewLabel_6_5 = new JLabel("n");
		lblNewLabel_6_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6_5.setBounds(187, 34, 45, 13);
		panel_2.add(lblNewLabel_6_5);
		
		JLabel lblNewLabel_6_6 = new JLabel("n");
		lblNewLabel_6_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6_6.setBounds(187, 57, 45, 13);
		panel_2.add(lblNewLabel_6_6);
		
		JLabel lblNewLabel_6_7 = new JLabel("n");
		lblNewLabel_6_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6_7.setBounds(187, 80, 45, 13);
		panel_2.add(lblNewLabel_6_7);
		
		JButton btnGrfico = new JButton("Gr\u00E1fico");
		btnGrfico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGrfico.setBounds(129, 148, 103, 33);
		panel_2.add(btnGrfico);
		
		JTable table = new JTable(247, 6);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Classe", "is_God_Class", "Verifica\u00E7\u00E3o", "Metodo", "is_Long_Method", "Verifica\u00E7\u00E3o"
			}
		));
		table.setToolTipText("");
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(225, 10, 400, 395);
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);
	}
}