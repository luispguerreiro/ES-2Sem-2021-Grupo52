package GUI;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import central.BoolResultado;
import central.Central;
import maisTestes.Comparador;
import rules.Rule;
import rules.Rule.comparator;
import rules.Rule.operator;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtSrcPath;
	private JScrollPane scrollPane;

	private File src_path;

	private Central c;

	private JLabel nPackagesLabel;
	private JLabel nClassesLabel;
	private JLabel nMethodsLabel;
	private JLabel nLinesLabel;
	private JLabel verdPositLabel;
	private JLabel verdNegatLabel;
	private JLabel falsePositLabel;
	private JLabel falseNegatLabel;

	private int tipoComparacao; // vai ser retirado consoante as checkbox de god class e long method

	private JTextField txtThreshold;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;

	private ArrayList<String> metricNames = new ArrayList<>();
	private ArrayList<comparator> comparators = new ArrayList<>();
	private ArrayList<operator> operators = new ArrayList<>();
	private ArrayList<Integer> limits = new ArrayList<>();
	private ArrayList<String> metricNames1 = new ArrayList<>();
	private ArrayList<comparator> comparators1 = new ArrayList<>();
	private ArrayList<operator> operators1 = new ArrayList<>();
	private ArrayList<Integer> limits1 = new ArrayList<>();
	
	ArrayList<Rule> rules = new ArrayList();
	
	private JTextField textField_4;
	private JTextField textField_5;
	
	private JTextField txtSelecioneONome;
	private JTextField txtSelecioneAPasta;


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
	
	public void cleanArrays() {
		metricNames.clear();
		metricNames1.clear();
		limits.clear();
		limits1.clear();
		operators.clear();
		operators1.clear();
		comparators.clear();
		comparators1.clear();
		rules.clear();
		
	}

	// Funciona sempre com as mesmas regras
	public ArrayList<Rule> PutCentralWorking() throws FileNotFoundException {
		tipoComparacao = 1;
		String ruleName = "RegraNew";
		ArrayList<String> metricName = new ArrayList<>();
		ArrayList<comparator> comp = new ArrayList<>();
		ArrayList<Integer> limits = new ArrayList<>();
		ArrayList<operator> oper = new ArrayList<>();
		metricName.add("NOM_class");
		metricName.add("LOC_class");
		metricName.add("WMC_class");
		comp.add(comparator.BIGGER);
		comp.add(comparator.BIGGER);
		comp.add(comparator.SMALLER);
		limits.add(20);
		limits.add(30);
		limits.add(40);
		oper.add(operator.AND);
		oper.add(operator.OR);

		Rule r = new Rule(ruleName, 0, metricName, comp, limits, oper);

		String ruleName1 = "Regra3";
		ArrayList<String> metricName1 = new ArrayList<>();
		ArrayList<comparator> comp1 = new ArrayList<>();
		ArrayList<Integer> limits1 = new ArrayList<>();
		ArrayList<operator> oper1 = new ArrayList<>();
		metricName1.add("LOC_method");
		metricName1.add("CYCLO_method");
		comp1.add(comparator.BIGGER);
		comp1.add(comparator.SMALLER);
		limits1.add(10);
		limits1.add(40);
		oper1.add(operator.AND);

		Rule r1 = new Rule(ruleName1, 1, metricName1, comp1, limits1, oper1);

		ArrayList<Rule> rules = new ArrayList();
		rules.add(r);
		rules.add(r1);
		return rules;

	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public GUI() throws IOException {
		setResizable(false);
		setTitle("Code Quality Assessor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1199, 681);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("Pasta");
		btnNewButton.setBounds(1072, 567, 103, 33);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser("Escolha a pasta");
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					txtSrcPath.setText(selectedFile.getAbsolutePath());
				}
				try {
					src_path = new File(txtSrcPath.getText());
					if (src_path.isDirectory()) {
						ArrayList<File> lista = new ArrayList<File>();
						Path path = Paths.get(src_path.getAbsolutePath());
						List<Path> paths = listFiles(path);
						List<File> files = pathsToFiles(paths);
						for (int i = 0; i < paths.size(); i++) {
							if (files.get(i).isFile() && files.get(i).getPath().endsWith(".java")) {
								lista.add(files.get(i));
							}
						}
						File[] v = new File[lista.size()];
						for (int i = 0; i < lista.size(); i++) {
							v[i] = lista.get(i);
							System.out.println(v[i]);
						}
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		contentPane.setLayout(null);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnNewButton);

		JButton btnRun = new JButton("Run");
		btnRun.setBounds(1072, 610, 103, 33);
		btnRun.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRun.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				ArrayList<Rule> rules;
				JFileChooser jfcrun = new JFileChooser();
				jfcrun.setDialogTitle("Escolha onde guardar o excel");
				jfcrun.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = jfcrun.showOpenDialog(null);
//				if (returnValue == JFileChooser.APPROVE_OPTION) {
//					File selectedFile = jfcrun.getSelectedFile();
//				}
				try {
//					rules = PutCentralWorking();
					c = new Central(rules, src_path, tipoComparacao);
					System.out.println(jfcrun.getSelectedFile().getAbsolutePath());
					c.setExcelFileDir(jfcrun.getSelectedFile().getAbsolutePath());
					c.ini();
					writeStatsLabels();
					scrollPane.setViewportView(
							escreveTabela(c.getBoolClass(), c.getBoolMethod(), c.getComparador(), tipoComparacao));
					cleanArrays();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnRun);

		txtSrcPath = new JTextField();
		txtSrcPath.setBounds(10, 567, 923, 33);
		txtSrcPath.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSrcPath.setText("SRC PATH");
		contentPane.add(txtSrcPath);
		txtSrcPath.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 206, 547);
		contentPane.add(panel);
		panel.setLayout(null);

		JList list = new JList();
		list.setBounds(10, 40, 186, 270);
		panel.add(list);

		JButton btnNewButton_1 = new JButton("Editar...");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(10, 333, 186, 21);
		btnNewButton_1.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame editar = new JFrame("Editar...");
				editar.setTitle("Editar...");
				editar.setBounds(100, 100, 443, 359);
				JPanel contentPane1 = new JPanel();
				contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
				editar.setContentPane(contentPane1);
				contentPane1.setLayout(null);

				JCheckBox chckbxNewCheckBox = new JCheckBox("Long Method");

				chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
				chckbxNewCheckBox.setBounds(6, 10, 109, 21);
				contentPane1.add(chckbxNewCheckBox);

				JComboBox<comparator> comboBox = new JComboBox<comparator>();
				comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBox.setModel(new DefaultComboBoxModel<>(comparator.values()));
				comboBox.setToolTipText("");
				comboBox.setBounds(257, 10, 82, 21);
				contentPane1.add(comboBox);

				JLabel lblNewLabel = new JLabel("Lines of Code");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel.setBounds(121, 14, 86, 13);
				contentPane1.add(lblNewLabel);

				JComboBox<operator> comboBox_1 = new JComboBox<operator>();
				comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBox_1.setModel(new DefaultComboBoxModel<>(operator.values()));
				comboBox_1.setBounds(131, 37, 57, 21);
				contentPane1.add(comboBox_1);

				JLabel lblNewLabel_1 = new JLabel("Lines of Code");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel_1.setBounds(121, 133, 86, 13);
				contentPane1.add(lblNewLabel_1);

				JComboBox<comparator> comboBox_2 = new JComboBox<comparator>();
				comboBox_2.setModel(new DefaultComboBoxModel<>(comparator.values()));
				comboBox_2.setToolTipText("");
				comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBox_2.setBounds(257, 125, 82, 21);
				contentPane1.add(comboBox_2);

				textField = new JTextField();
				textField.setText("Threshold");
				textField.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		                textField.setText("");
		            }
		        });
				textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
				textField.setColumns(10);
				textField.setBounds(349, 123, 70, 19);
				contentPane1.add(textField);

				JCheckBox chckbxGodClass = new JCheckBox("God Class");
				chckbxGodClass.setFont(new Font("Tahoma", Font.PLAIN, 14));
				chckbxGodClass.setBounds(6, 131, 109, 21);
				contentPane1.add(chckbxGodClass);

				JLabel lblNewLabel_1_1 = new JLabel("Cyclo");
				lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel_1_1.setBounds(121, 69, 34, 13);
				contentPane.add(lblNewLabel_1_1);

				JComboBox<comparator> comboBox_3 = new JComboBox<comparator>();
				comboBox_3.setModel(new DefaultComboBoxModel<>(comparator.values()));
				comboBox_3.setToolTipText("");
				comboBox_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBox_3.setBounds(257, 61, 82, 21);
				contentPane1.add(comboBox_3);

				textField_1 = new JTextField();
				textField_1.setText("Threshold");
				textField_1.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		                textField_1.setText("");
		            }
		        });
				textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				textField_1.setColumns(10);
				textField_1.setBounds(349, 61, 70, 19);
				contentPane1.add(textField_1);

				JComboBox<operator> comboBox_1_1 = new JComboBox<operator>();
				comboBox_1_1.setModel(new DefaultComboBoxModel<>(operator.values()));
				comboBox_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBox_1_1.setBounds(131, 156, 57, 21);
				contentPane1.add(comboBox_1_1);

				JLabel lblNewLabel_1_2 = new JLabel("Number of Methods");
				lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel_1_2.setBounds(121, 191, 126, 13);
				contentPane1.add(lblNewLabel_1_2);

				JComboBox<operator> comboBox_1_1_1 = new JComboBox<operator>();
				comboBox_1_1_1.setModel(new DefaultComboBoxModel<>(operator.values()));
				comboBox_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBox_1_1_1.setBounds(131, 214, 57, 21);
				contentPane1.add(comboBox_1_1_1);

				JLabel lblNewLabel_1_3 = new JLabel("WMC Class");
				lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel_1_3.setBounds(121, 249, 86, 13);
				contentPane1.add(lblNewLabel_1_3);

				JComboBox<comparator> comboBox_2_2 = new JComboBox<comparator>();
				comboBox_2_2.setModel(new DefaultComboBoxModel<>(comparator.values()));
				comboBox_2_2.setToolTipText("");
				comboBox_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBox_2_2.setBounds(257, 241, 82, 21);
				contentPane1.add(comboBox_2_2);

				textField_3 = new JTextField();
				textField_3.setText("Threshold");
				textField_3.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		                textField_3.setText("");
		            }
		        });
				textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
				textField_3.setColumns(10);
				textField_3.setBounds(349, 239, 70, 19);
				contentPane1.add(textField_3);

				JComboBox<comparator> comboBox_2_3 = new JComboBox<comparator>();
				comboBox_2_3.setModel(new DefaultComboBoxModel<>(comparator.values()));
				comboBox_2_3.setToolTipText("");
				comboBox_2_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBox_2_3.setBounds(257, 183, 82, 21);
				contentPane1.add(comboBox_2_3);

				textField_4 = new JTextField();
				textField_4.setText("Threshold");
				textField_4.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		                textField_4.setText("");
		            }
		        });
				textField_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
				textField_4.setColumns(10);
				textField_4.setBounds(349, 181, 70, 19);
				contentPane1.add(textField_4);

				textField_5 = new JTextField();
				textField_5.setText("Threshold");
				textField_5.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		                textField_5.setText("");
		            }
		        });
				textField_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
				textField_5.setColumns(10);
				textField_5.setBounds(349, 10, 70, 19);
				contentPane1.add(textField_5);

				JLabel lblCyclo = new JLabel("Cyclo");
				lblCyclo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblCyclo.setBounds(121, 67, 86, 13);
				contentPane1.add(lblCyclo);

				JButton btnNewButton = new JButton("Aplicar");
				btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnNewButton.setBounds(334, 292, 85, 21);
				contentPane1.add(btnNewButton);
				btnNewButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame frameaplicar = new JFrame("Aplicar...");
						frameaplicar.setBounds(100, 100, 396, 158);
						JPanel contentPaneaplicar = new JPanel();
						contentPaneaplicar.setBorder(new EmptyBorder(5, 5, 5, 5));
						contentPaneaplicar.setLayout(null);
						
						JButton btnNewButton = new JButton("Guardar");
						btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
						btnNewButton.setBounds(269, 92, 107, 21);
						contentPaneaplicar.add(btnNewButton);
						
						JButton btnNewButton_1 = new JButton("Pasta");
						btnNewButton_1.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								JFileChooser filechooseraplicar = new JFileChooser();
								filechooseraplicar.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
								filechooseraplicar.setDialogTitle("Selecione a pasta onde quer guardar a regra");
								int returnValue = filechooseraplicar.showOpenDialog(null);
								if (returnValue == JFileChooser.APPROVE_OPTION) {
									String nome = filechooseraplicar.getSelectedFile().getAbsolutePath();
									txtSelecioneAPasta.setText(nome);
								}
							}
						});
						btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
						btnNewButton_1.setBounds(269, 30, 107, 21);
						contentPaneaplicar.add(btnNewButton_1);
						
						JButton btnNewButton_2 = new JButton("Nome regra");
						btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
						btnNewButton_2.setBounds(269, 61, 107, 21);
						contentPaneaplicar.add(btnNewButton_2);
						
						txtSelecioneAPasta = new JTextField("Selecione a pasta onde quer guardar a regra");
						txtSelecioneAPasta.setFont(new Font("Tahoma", Font.PLAIN, 12));
						txtSelecioneAPasta.setBounds(10, 32, 249, 19);
						contentPaneaplicar.add(txtSelecioneAPasta);
						txtSelecioneAPasta.setColumns(10);
						
						txtSelecioneONome = new JTextField();
						txtSelecioneONome.setFont(new Font("Tahoma", Font.PLAIN, 12));
						txtSelecioneONome.setText("Selecione o nome da regra");
						txtSelecioneONome.setBounds(10, 63, 249, 18);
						contentPaneaplicar.add(txtSelecioneONome);
						txtSelecioneONome.setColumns(10);
						
						JButton btnNewButton_3 = new JButton("Voltar");
						btnNewButton_3.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								frameaplicar.dispose();
							}
						});
						btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
						btnNewButton_3.setBounds(10, 94, 85, 21);
						contentPaneaplicar.add(btnNewButton_3);
						
						frameaplicar.setContentPane(contentPaneaplicar);
						frameaplicar.setVisible(true);
						
						if (chckbxNewCheckBox.isSelected()) {
							if (!textField_5.getText().equals("Threshold")) {
								limits.add(Integer.parseInt(textField_5.getText()));
								metricNames.add("LOC_method");
							}
							if (!textField_1.getText().equals("Threshold")) {
								limits.add(Integer.parseInt(textField_1.getText()));
								metricNames.add("CYCLO_method");
							}
							if (!comboBox.getSelectedItem().equals(comparator.XXX))
								comparators.add((comparator) comboBox.getSelectedItem());
							if (!comboBox_3.getSelectedItem().equals(comparator.XXX))
								comparators.add((comparator) comboBox_3.getSelectedItem());
							if (!comboBox_1.getSelectedItem().equals(operator.XXX))
								operators.add((operator) comboBox_1.getSelectedItem());
							
							try {
								rules.add(new Rule("OLAAA", 1, metricNames, comparators, limits, operators));
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							tipoComparacao = 3;
						}
						if (chckbxGodClass.isSelected()) {
							if (!textField.getText().equals("Threshold")) {
								limits1.add(Integer.parseInt(textField.getText()));
								metricNames1.add("LOC_class");
							}
							if (!textField_4.getText().equals("Threshold")) {
								limits1.add(Integer.parseInt(textField_4.getText()));
								metricNames1.add("NOM_class");
							}
							if (!textField_3.getText().equals("Threshold")) {
								limits1.add(Integer.parseInt(textField_3.getText()));
								metricNames1.add("WMC_class");
							}
							if (!comboBox_2.getSelectedItem().equals(comparator.XXX))
								comparators1.add((comparator) comboBox_2.getSelectedItem());
							if (!comboBox_2_3.getSelectedItem().equals(comparator.XXX))
								comparators1.add((comparator) comboBox_2_3.getSelectedItem());
							if (!comboBox_2_2.getSelectedItem().equals(comparator.XXX))
								comparators1.add((comparator) comboBox_2_2.getSelectedItem());
							if (!comboBox_1_1.getSelectedItem().equals(operator.XXX))
								operators1.add((operator) comboBox_1_1.getSelectedItem());
							if (!comboBox_1_1_1.getSelectedItem().equals(operator.XXX))
								operators1.add((operator) comboBox_1_1_1.getSelectedItem());
							
							try {
								
								System.out.println(metricNames1.size()+ "  " + comparators1.size()+"  "+limits1.size()+ "  "+operators1.size());
								rules.add(new Rule("OLA 222", 0, metricNames1, comparators1, limits1, operators1));
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							tipoComparacao = 2;
						}
						if (chckbxNewCheckBox.isSelected() && chckbxGodClass.isSelected())
							tipoComparacao = 1;
						
//						editar.dispose();
// est� comentado pois carregue no aplicar, a janela de editar feca-se

					}
				});

				editar.setVisible(true);
			}
		});
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Importar Regras");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(10, 364, 186, 21);
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc1 = new JFileChooser("Escolha pasta a importar");
				jfc1.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnValue = jfc1.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc1.getSelectedFile();
					String pathimportar = selectedFile.getAbsolutePath();
					System.out.println(pathimportar);
				}
			}
		});
		panel.add(btnNewButton_2);

		JLabel lblNewLabel = new JLabel("Lista de Regras importadas");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 186, 31);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(943, 10, 242, 210);
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

		nPackagesLabel = new JLabel("n");
		nPackagesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nPackagesLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nPackagesLabel.setBounds(187, 34, 45, 13);
		panel_1.add(nPackagesLabel);

		nClassesLabel = new JLabel("n");
		nClassesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nClassesLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nClassesLabel.setBounds(187, 57, 45, 13);
		panel_1.add(nClassesLabel);

		nMethodsLabel = new JLabel("n");
		nMethodsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nMethodsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nMethodsLabel.setBounds(187, 80, 45, 13);
		panel_1.add(nMethodsLabel);

		nLinesLabel = new JLabel("n");
		nLinesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nLinesLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nLinesLabel.setBounds(187, 103, 45, 13);
		panel_1.add(nLinesLabel);

		JButton btnAbrirExcel = new JButton("Abrir Excel");
		btnAbrirExcel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAbrirExcel.setBounds(129, 126, 103, 33);
		btnAbrirExcel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					File file = c.getExcelFile();
					Desktop d = Desktop.getDesktop();
					d.open(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel_1.add(btnAbrirExcel);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(943, 263, 242, 191);
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

		verdPositLabel = new JLabel("n");
		verdPositLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		verdPositLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		verdPositLabel.setBounds(187, 11, 45, 13);
		panel_2.add(verdPositLabel);

		verdNegatLabel = new JLabel("n");
		verdNegatLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		verdNegatLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		verdNegatLabel.setBounds(187, 34, 45, 13);
		panel_2.add(verdNegatLabel);

		falsePositLabel = new JLabel("n");
		falsePositLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		falsePositLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		falsePositLabel.setBounds(187, 57, 45, 13);
		panel_2.add(falsePositLabel);

		falseNegatLabel = new JLabel("n");
		falseNegatLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		falseNegatLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		falseNegatLabel.setBounds(187, 80, 45, 13);
		panel_2.add(falseNegatLabel);

		JButton btnGrfico = new JButton("Gr\u00E1fico");
		btnGrfico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGrfico.setBounds(129, 103, 103, 33);
		btnGrfico.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultPieDataset dataset = new DefaultPieDataset();
				dataset.setValue("VP", 21);
				dataset.setValue("VN", 10);
				dataset.setValue("FP", 3);
				dataset.setValue("FN", 14);
				dataset.setValue("VP", c.getComparador().getCountVP());
				dataset.setValue("VN", c.getComparador().getCountVN());
				dataset.setValue("FP", c.getComparador().getCountFP());
				dataset.setValue("FN", c.getComparador().getCountFN());

				JFreeChart pieChart = ChartFactory.createPieChart("gr�fico", dataset, false, true, false);

				ChartPanel chartPanel = new ChartPanel(pieChart);
				chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
				chartPanel.setBackground(Color.white);

				JFrame frame1 = new JFrame("Pie Chart");
				frame1.setResizable(false);
				frame1.setBounds(100, 100, 500, 200);

				frame1.add(chartPanel);
				frame1.setVisible(true);
			}
		});
		panel_2.add(btnGrfico);

		JTable table = new JTable(30, 10);
		table.setToolTipText("");
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setEnabled(false);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(225, 10, 708, 504);
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);
	}

	public List<Path> listFiles(Path path) throws IOException {
		List<Path> result;
		try (Stream<Path> walk = Files.walk(path)) {
			result = walk.filter(Files::isRegularFile).collect(Collectors.toList());
		}
		return result;
	}

	public List<File> pathsToFiles(List<Path> path) {
		List<File> files = new ArrayList<File>();
		for (int i = 0; i < path.size(); i++) {
			files.add(path.get(i).toFile());
		}
		return files;
	}

	public JTable escreveTabela(ArrayList<BoolResultado> isgodclass, ArrayList<BoolResultado> islongmethod,
			Comparador comparador, int tipo) {
		ArrayList<String[]> list = new ArrayList<>();
		if (tipo == 1) {
			String[] fixo = { "Pacote", "Classe", "M�todo", "is_God_Class", "Verifca��o", "is_Long_Method",
					"Verifica��o" };
			list.add(fixo);
		}
		if (tipo == 2) {
			String[] fixo = { "Pacote", "Classe", "M�todo", "is_God_Class", "Verifca��o" };
			list.add(fixo);
		}
		if (tipo == 3) {
			String[] fixo = { "Pacote", "Classe", "M�todo", "is_Long_Method", "Verifica��o" };
			list.add(fixo);
		}
		Object[][] data = new Object[isgodclass.size()][list.get(0).length];
//		Object[][] data = new Object[c.getComparador().getClassCheck().size()][list.get(0).length];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = isgodclass.get(i).getPackage();
			data[i][1] = isgodclass.get(i).getClasses();
			data[i][2] = isgodclass.get(i).getMetodo();
			if (tipo == 2) { // caso utilizador selecione ambas ou apenas isgodclass
				data[i][3] = isgodclass.get(i).getVerificacao();
				data[i][4] = c.getComparador().getClassCheck().get(i);
			}
			if ( tipo == 3) { // caso utilizador selecione ambas ou apenas islongmethod
				data[i][3] = islongmethod.get(i).getVerificacao();
				data[i][4] = c.getComparador().getMethodCheck().get(i);
			}
			if(tipo == 1) {
				data[i][3] = isgodclass.get(i).getVerificacao();
				data[i][4] = c.getComparador().getClassCheck().get(i);
				data[i][5] = islongmethod.get(i).getVerificacao();
				data[i][6] = c.getComparador().getMethodCheck().get(i);
				
			}
		}

		JTable table = new JTable(data, list.get(0));
		table.setEnabled(false);
		return table;

	}

	public void writeStatsLabels() {
		nPackagesLabel.setText(Integer.toString(c.getNumberOfPackages()));
		nClassesLabel.setText(Integer.toString(c.getNumberOfClasses()));
		nMethodsLabel.setText(Integer.toString(c.getNumberOfMethods()));
		nLinesLabel.setText(Integer.toString(c.getNumberOfLines()));
		verdPositLabel.setText(Integer.toString(c.getComparador().getCountVP()));
		verdNegatLabel.setText(Integer.toString(c.getComparador().getCountVN()));
		falsePositLabel.setText(Integer.toString(c.getComparador().getCountFP()));
		falseNegatLabel.setText(Integer.toString(c.getComparador().getCountFN()));
		nPackagesLabel.updateUI();
		nClassesLabel.updateUI();
		nMethodsLabel.updateUI();
		nLinesLabel.updateUI();
		verdPositLabel.updateUI();
		verdNegatLabel.updateUI();
		falsePositLabel.updateUI();
		falseNegatLabel.updateUI();
	}

}
