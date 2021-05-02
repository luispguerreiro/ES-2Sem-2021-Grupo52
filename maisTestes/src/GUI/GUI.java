package GUI;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
import central.History;
import maisTestes.Comparador;
import rules.Rule;
import rules.Rule.comparator;
import rules.Rule.operator;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private JPanel contentPanePrincipal;
	private JTextField textFieldSrcPath;
	private JScrollPane scrollPaneTabela;

	private File src_path;
	private File excelFile;

	private Central central;

	private History history;

	private JLabel nPackagesLabel;
	private JLabel nClassesLabel;
	private JLabel nMethodsLabel;
	private JLabel nLinesLabel;
	private JLabel verdadeiroPositivoLabel;
	private JLabel verdadeiroNegativoLabel;
	private JLabel falsePositivoLabel;
	private JLabel falseNegativoLabel;

	private int tipoComparacao; // vai ser retirado consoante as checkbox de god class e long method

	private JTextField textFieldIsLongMethodLOC;
	private JTextField textFieldIsLongMethodCyclo;
	private JTextField textFieldIsGodClassLOC;
	private JTextField textFieldIsGodClassNOM;
	private JTextField textFieldIsGodClassWMC;

	private ArrayList<String> metricNames = new ArrayList<>();
	private ArrayList<comparator> comparators = new ArrayList<>();
	private ArrayList<operator> operators = new ArrayList<>();
	private ArrayList<Integer> limits = new ArrayList<>();
	private ArrayList<String> metricNames1 = new ArrayList<>();
	private ArrayList<comparator> comparators1 = new ArrayList<>();
	private ArrayList<operator> operators1 = new ArrayList<>();
	private ArrayList<Integer> limits1 = new ArrayList<>();
	private List<File> folders = new ArrayList<>();
	private ArrayList<File> files = new ArrayList<>();

	ArrayList<Rule> rules = new ArrayList<>();

	private JTextField textFieldSelecioneONome;
	private JTextField textFieldSelecioneAPasta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation((int) (dimension.getWidth() - frame.getWidth()) / 2,
							(int) (dimension.getHeight() - frame.getHeight()) / 2);
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

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public GUI() throws IOException {
		history = new History();
		setResizable(false);
		setTitle("Code Quality Assessor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1199, 681);
		contentPanePrincipal = new JPanel();
		contentPanePrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanePrincipal);

		JButton botaoPasta = new JButton("Pasta");
		botaoPasta.setBounds(1072, 567, 103, 33);
		botaoPasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File selectedFile = new File("");
				JFileChooser jFileChooserPasta = new JFileChooser("Escolha a pasta");
				jFileChooserPasta.setDialogTitle("Escolha a pasta");
				jFileChooserPasta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = jFileChooserPasta.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					selectedFile = jFileChooserPasta.getSelectedFile();
					textFieldSrcPath.setText(selectedFile.getAbsolutePath());
				}
				try {
					Path directory = selectedFile.getAbsoluteFile().toPath();
					List<Path> filePaths;
					try (Stream<Path> walk = Files.walk(directory)) {
						filePaths = walk.filter(Files::isRegularFile).collect(Collectors.toList());
					}
					for (int i = 0; i < filePaths.size(); i++) {
						folders.add(filePaths.get(i).toFile());
					}
					boolean hasFiles = false;
					for (File file : folders) {
						if (file.getAbsolutePath().endsWith(".java")) {
							hasFiles = true;
							files.add(file);
						}
					}
					if (hasFiles == false) {
						JOptionPane.showMessageDialog(null, "Selecione uma pasta com um projeto Java.");
					}
					src_path = new File(textFieldSrcPath.getText());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		contentPanePrincipal.setLayout(null);
		botaoPasta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPanePrincipal.add(botaoPasta);

		DefaultListModel defaultListModel = new DefaultListModel();
		JList list = new JList(defaultListModel);
		list.setBounds(10, 40, 186, 270);

		JButton botaoRun = new JButton("Run");
		botaoRun.setBounds(1072, 610, 103, 33);
		botaoRun.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botaoRun.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooserRun = new JFileChooser();
				jFileChooserRun.setDialogTitle("Escolha onde guardar o excel");
				jFileChooserRun.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = jFileChooserRun.showOpenDialog(null);
				try {
					if (textFieldSrcPath.getText().equals("Selecione a pasta do seu projeto"))
						JOptionPane.showMessageDialog(null, "Não selecionou uma pasta de projeto!");
					if (rules.isEmpty())
						JOptionPane.showMessageDialog(null,
								"Precisa de selecionar pelo menos um Code Smell!\n Tente novamente");

					central = new Central(rules, src_path, tipoComparacao, files);
					System.out.println(jFileChooserRun.getSelectedFile().getAbsolutePath());
					central.setExcelFileDir(jFileChooserRun.getSelectedFile().getAbsolutePath());
					central.ini();
					excelFile = central.getExcelFile();
					writeStatsLabels();
					scrollPaneTabela.setViewportView(escreveTabela(central.getBoolClass(), central.getBoolMethod(),
							central.getComparador(), tipoComparacao));
					cleanArrays();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPanePrincipal.add(botaoRun);

		textFieldSrcPath = new JTextField();
		textFieldSrcPath.setBounds(10, 567, 923, 33);
		textFieldSrcPath.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldSrcPath.setText("Selecione a pasta do seu projeto");
		textFieldSrcPath.setEditable(false);
		contentPanePrincipal.add(textFieldSrcPath);
		textFieldSrcPath.setColumns(10);

		JPanel panelImportRegras = new JPanel();
		panelImportRegras.setBounds(10, 10, 206, 547);
		contentPanePrincipal.add(panelImportRegras);
		panelImportRegras.setLayout(null);

		JButton botaoEditar = new JButton("Editar...");
		botaoEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botaoEditar.setBounds(10, 333, 186, 21);
		botaoEditar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frameEditar = new JFrame("Editar...");
				frameEditar.setTitle("Editar...");
				frameEditar.setBounds(100, 100, 443, 359);
				JPanel contentPaneEditar = new JPanel();
				contentPaneEditar.setBorder(new EmptyBorder(5, 5, 5, 5));
				frameEditar.setContentPane(contentPaneEditar);
				contentPaneEditar.setLayout(null);

				JCheckBox checkBoxIsLongMethod = new JCheckBox("Long Method");

				checkBoxIsLongMethod.setFont(new Font("Tahoma", Font.PLAIN, 14));
				checkBoxIsLongMethod.setBounds(6, 10, 109, 21);
				contentPaneEditar.add(checkBoxIsLongMethod);

				JComboBox<comparator> comboBoxIsLongMethodLOC = new JComboBox<comparator>();
				comboBoxIsLongMethodLOC.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBoxIsLongMethodLOC.setModel(new DefaultComboBoxModel<>(comparator.values()));
				comboBoxIsLongMethodLOC.setToolTipText("");
				comboBoxIsLongMethodLOC.setBounds(257, 10, 82, 21);
				contentPaneEditar.add(comboBoxIsLongMethodLOC);

				JLabel labelIsLongMethodLOC = new JLabel("Lines of Code");
				labelIsLongMethodLOC.setFont(new Font("Tahoma", Font.PLAIN, 14));
				labelIsLongMethodLOC.setBounds(121, 14, 86, 13);
				contentPaneEditar.add(labelIsLongMethodLOC);

				JComboBox<operator> comboBoxIsLongMethodANDOR = new JComboBox<operator>();
				comboBoxIsLongMethodANDOR.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBoxIsLongMethodANDOR.setModel(new DefaultComboBoxModel<>(operator.values()));
				comboBoxIsLongMethodANDOR.setBounds(131, 37, 65, 21);
				contentPaneEditar.add(comboBoxIsLongMethodANDOR);

				JLabel labelIsGodClassLOC = new JLabel("Lines of Code");
				labelIsGodClassLOC.setFont(new Font("Tahoma", Font.PLAIN, 14));
				labelIsGodClassLOC.setBounds(121, 133, 86, 13);
				contentPaneEditar.add(labelIsGodClassLOC);

				JComboBox<comparator> comboBoxIsGodClassLOC = new JComboBox<comparator>();
				comboBoxIsGodClassLOC.setModel(new DefaultComboBoxModel<>(comparator.values()));
				comboBoxIsGodClassLOC.setToolTipText("");
				comboBoxIsGodClassLOC.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBoxIsGodClassLOC.setBounds(257, 125, 82, 21);
				contentPaneEditar.add(comboBoxIsGodClassLOC);

				textFieldIsGodClassLOC = new JTextField();
				textFieldIsGodClassLOC.setText("Threshold");
				textFieldIsGodClassLOC.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						textFieldIsGodClassLOC.setText("");
					}
				});
				textFieldIsGodClassLOC.setFont(new Font("Tahoma", Font.PLAIN, 14));
				textFieldIsGodClassLOC.setColumns(10);
				textFieldIsGodClassLOC.setBounds(349, 123, 70, 19);
				contentPaneEditar.add(textFieldIsGodClassLOC);

				JCheckBox checkBoxIsGodClass = new JCheckBox("God Class");
				checkBoxIsGodClass.setFont(new Font("Tahoma", Font.PLAIN, 14));
				checkBoxIsGodClass.setBounds(6, 131, 109, 21);
				contentPaneEditar.add(checkBoxIsGodClass);

				JLabel labelIsLongMethodCYCLO = new JLabel("Cyclo");
				labelIsLongMethodCYCLO.setFont(new Font("Tahoma", Font.PLAIN, 14));
				labelIsLongMethodCYCLO.setBounds(121, 69, 34, 13);
				contentPanePrincipal.add(labelIsLongMethodCYCLO);

				JComboBox<comparator> comboBoxIsLongMethodCYCLO = new JComboBox<comparator>();
				comboBoxIsLongMethodCYCLO.setModel(new DefaultComboBoxModel<>(comparator.values()));
				comboBoxIsLongMethodCYCLO.setToolTipText("");
				comboBoxIsLongMethodCYCLO.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBoxIsLongMethodCYCLO.setBounds(257, 61, 82, 21);
				contentPaneEditar.add(comboBoxIsLongMethodCYCLO);

				textFieldIsLongMethodCyclo = new JTextField();
				textFieldIsLongMethodCyclo.setText("Threshold");
				textFieldIsLongMethodCyclo.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						textFieldIsLongMethodCyclo.setText("");
					}
				});
				textFieldIsLongMethodCyclo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				textFieldIsLongMethodCyclo.setColumns(10);
				textFieldIsLongMethodCyclo.setBounds(349, 61, 70, 19);
				contentPaneEditar.add(textFieldIsLongMethodCyclo);

				JComboBox<operator> comboBoxIsGodClassLOCNOM = new JComboBox<operator>();
				comboBoxIsGodClassLOCNOM.setModel(new DefaultComboBoxModel<>(operator.values()));
				comboBoxIsGodClassLOCNOM.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBoxIsGodClassLOCNOM.setBounds(131, 156, 65, 21);
				contentPaneEditar.add(comboBoxIsGodClassLOCNOM);

				JLabel labelIsGodClassNOM = new JLabel("Number of Methods");
				labelIsGodClassNOM.setFont(new Font("Tahoma", Font.PLAIN, 14));
				labelIsGodClassNOM.setBounds(121, 191, 126, 13);
				contentPaneEditar.add(labelIsGodClassNOM);

				JComboBox<operator> comboBoxIsGodClassNOMWMC = new JComboBox<operator>();
				comboBoxIsGodClassNOMWMC.setModel(new DefaultComboBoxModel<>(operator.values()));
				comboBoxIsGodClassNOMWMC.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBoxIsGodClassNOMWMC.setBounds(131, 214, 65, 21);
				contentPaneEditar.add(comboBoxIsGodClassNOMWMC);

				JLabel labelIsGodClassWMC = new JLabel("WMC Class");
				labelIsGodClassWMC.setFont(new Font("Tahoma", Font.PLAIN, 14));
				labelIsGodClassWMC.setBounds(121, 249, 86, 13);
				contentPaneEditar.add(labelIsGodClassWMC);

				JComboBox<comparator> comboBoxIsGodClassWMC = new JComboBox<comparator>();
				comboBoxIsGodClassWMC.setModel(new DefaultComboBoxModel<>(comparator.values()));
				comboBoxIsGodClassWMC.setToolTipText("");
				comboBoxIsGodClassWMC.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBoxIsGodClassWMC.setBounds(257, 241, 82, 21);
				contentPaneEditar.add(comboBoxIsGodClassWMC);

				textFieldIsGodClassWMC = new JTextField();
				textFieldIsGodClassWMC.setText("Threshold");
				textFieldIsGodClassWMC.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						textFieldIsGodClassWMC.setText("");
					}
				});
				textFieldIsGodClassWMC.setFont(new Font("Tahoma", Font.PLAIN, 14));
				textFieldIsGodClassWMC.setColumns(10);
				textFieldIsGodClassWMC.setBounds(349, 239, 70, 19);
				contentPaneEditar.add(textFieldIsGodClassWMC);

				JComboBox<comparator> comboBoxIsGodClassNOM = new JComboBox<comparator>();
				comboBoxIsGodClassNOM.setModel(new DefaultComboBoxModel<>(comparator.values()));
				comboBoxIsGodClassNOM.setToolTipText("");
				comboBoxIsGodClassNOM.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBoxIsGodClassNOM.setBounds(257, 183, 82, 21);
				contentPaneEditar.add(comboBoxIsGodClassNOM);

				textFieldIsGodClassNOM = new JTextField();
				textFieldIsGodClassNOM.setText("Threshold");
				textFieldIsGodClassNOM.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						textFieldIsGodClassNOM.setText("");
					}
				});
				textFieldIsGodClassNOM.setFont(new Font("Tahoma", Font.PLAIN, 14));
				textFieldIsGodClassNOM.setColumns(10);
				textFieldIsGodClassNOM.setBounds(349, 181, 70, 19);
				contentPaneEditar.add(textFieldIsGodClassNOM);

				textFieldIsLongMethodLOC = new JTextField();
				textFieldIsLongMethodLOC.setText("Threshold");
				textFieldIsLongMethodLOC.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						textFieldIsLongMethodLOC.setText("");
					}
				});
				textFieldIsLongMethodLOC.setFont(new Font("Tahoma", Font.PLAIN, 14));
				textFieldIsLongMethodLOC.setColumns(10);
				textFieldIsLongMethodLOC.setBounds(349, 10, 70, 19);
				contentPaneEditar.add(textFieldIsLongMethodLOC);

				JLabel labelIsLongMethodCyclo = new JLabel("Cyclo");
				labelIsLongMethodCyclo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				labelIsLongMethodCyclo.setBounds(121, 67, 86, 13);
				contentPaneEditar.add(labelIsLongMethodCyclo);

				JButton botaoAplicar = new JButton("Aplicar");
				botaoAplicar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				botaoAplicar.setBounds(334, 292, 85, 21);
				contentPaneEditar.add(botaoAplicar);
				botaoAplicar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame frameAplicar = new JFrame("Aplicar...");
						frameAplicar.setBounds(100, 100, 396, 158);
						JPanel contentPaneAplicar = new JPanel();
						contentPaneAplicar.setBorder(new EmptyBorder(5, 5, 5, 5));
						contentPaneAplicar.setLayout(null);

						JButton botaoAplicarGuardar = new JButton("Guardar");
						botaoAplicarGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
						botaoAplicarGuardar.setBounds(269, 92, 107, 21);
						contentPaneAplicar.add(botaoAplicarGuardar);
						botaoAplicarGuardar.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								history.setFolderPathToSave(textFieldSelecioneAPasta.getText());
								history.setRuleName(textFieldSelecioneONome.getText());
								history.writeFile(rules);

								frameAplicar.dispose();
								frameEditar.dispose();
							}
						});

						JButton botaoAplicarPasta = new JButton("Pasta");
						botaoAplicarPasta.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								JFileChooser filechooseraplicar = new JFileChooser();
								filechooseraplicar.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
								filechooseraplicar.setDialogTitle("Selecione a pasta onde quer guardar a regra");
								int returnValue = filechooseraplicar.showOpenDialog(null);
								if (returnValue == JFileChooser.APPROVE_OPTION) {
									String nome = filechooseraplicar.getSelectedFile().getAbsolutePath();
									textFieldSelecioneAPasta.setText(nome);
								}
							}
						});
						botaoAplicarPasta.setFont(new Font("Tahoma", Font.PLAIN, 14));
						botaoAplicarPasta.setBounds(269, 30, 107, 21);
						contentPaneAplicar.add(botaoAplicarPasta);

						textFieldSelecioneAPasta = new JTextField("Selecione a pasta onde quer guardar a regra");
						textFieldSelecioneAPasta.setFont(new Font("Tahoma", Font.PLAIN, 12));
						textFieldSelecioneAPasta.setBounds(10, 32, 249, 19);
						contentPaneAplicar.add(textFieldSelecioneAPasta);
						textFieldSelecioneAPasta.setColumns(10);
						textFieldSelecioneAPasta.setEditable(false);

						textFieldSelecioneONome = new JTextField();
						textFieldSelecioneONome.setFont(new Font("Tahoma", Font.PLAIN, 12));
						textFieldSelecioneONome.setText("Selecione o nome da regra");
						textFieldSelecioneONome.setBounds(10, 63, 249, 18);
						contentPaneAplicar.add(textFieldSelecioneONome);
						textFieldSelecioneONome.setColumns(10);
						textFieldSelecioneONome.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								textFieldSelecioneONome.setText("");
							}
						});

						JButton botaoAplicarSemGuardar = new JButton("Continuar sem guardar");
						botaoAplicarSemGuardar.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								frameAplicar.dispose();
								frameEditar.dispose();
							}
						});
						botaoAplicarSemGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
						botaoAplicarSemGuardar.setBounds(10, 94, 200, 21);
						contentPaneAplicar.add(botaoAplicarSemGuardar);

						frameAplicar.setContentPane(contentPaneAplicar);
						frameAplicar.setVisible(true);

						if (checkBoxIsLongMethod.isSelected()) {
							if (textFieldIsLongMethodLOC.getText().matches("[0-9]+")) {
								limits.add(Integer.parseInt(textFieldIsLongMethodLOC.getText()));
								metricNames.add("LOC_method");
							}
							if (textFieldIsLongMethodCyclo.getText().matches("[0-9]+")) {
								limits.add(Integer.parseInt(textFieldIsLongMethodCyclo.getText()));
								metricNames.add("CYCLO_method");
							}
							if (!comboBoxIsLongMethodLOC.getSelectedItem().equals(comparator.Select))
								comparators.add((comparator) comboBoxIsLongMethodLOC.getSelectedItem());
							if (!comboBoxIsLongMethodCYCLO.getSelectedItem().equals(comparator.Select))
								comparators.add((comparator) comboBoxIsLongMethodCYCLO.getSelectedItem());
							if (!comboBoxIsLongMethodANDOR.getSelectedItem().equals(operator.Select))
								operators.add((operator) comboBoxIsLongMethodANDOR.getSelectedItem());

							try {
								rules.add(new Rule(textFieldSelecioneONome.getText(), 1, metricNames, comparators,
										limits, operators));
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}
							tipoComparacao = 3;
						}
						if (checkBoxIsGodClass.isSelected()) {
							if (textFieldIsGodClassLOC.getText().matches("[0-9]+")) {
								limits1.add(Integer.parseInt(textFieldIsGodClassLOC.getText()));
								metricNames1.add("LOC_class");
							}
							if (textFieldIsGodClassNOM.getText().matches("[0-9]+")) {
								limits1.add(Integer.parseInt(textFieldIsGodClassNOM.getText()));
								metricNames1.add("NOM_class");
							}
							if (textFieldIsGodClassWMC.getText().matches("[0-9]+")) {
								limits1.add(Integer.parseInt(textFieldIsGodClassWMC.getText()));
								metricNames1.add("WMC_class");
							}
							if (!comboBoxIsGodClassLOC.getSelectedItem().equals(comparator.Select))
								comparators1.add((comparator) comboBoxIsGodClassLOC.getSelectedItem());
							if (!comboBoxIsGodClassNOM.getSelectedItem().equals(comparator.Select))
								comparators1.add((comparator) comboBoxIsGodClassNOM.getSelectedItem());
							if (!comboBoxIsGodClassWMC.getSelectedItem().equals(comparator.Select))
								comparators1.add((comparator) comboBoxIsGodClassWMC.getSelectedItem());
							if (!comboBoxIsGodClassLOCNOM.getSelectedItem().equals(operator.Select))
								operators1.add((operator) comboBoxIsGodClassLOCNOM.getSelectedItem());
							if (!comboBoxIsGodClassNOMWMC.getSelectedItem().equals(operator.Select))
								operators1.add((operator) comboBoxIsGodClassNOMWMC.getSelectedItem());

							try {
								System.out.println(metricNames1.size() + "  " + comparators1.size() + "  "
										+ limits1.size() + "  " + operators1.size());
								rules.add(new Rule("", 0, metricNames1, comparators1, limits1, operators1));
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}
							tipoComparacao = 2;
						}
						if (checkBoxIsLongMethod.isSelected() && checkBoxIsGodClass.isSelected())
							tipoComparacao = 1;
					}
				});
				frameEditar.setVisible(true);
			}
		});
		panelImportRegras.add(botaoEditar);

		JPanel panelResumoMetricas = new JPanel();
		panelResumoMetricas.setBounds(943, 10, 242, 210);
		contentPanePrincipal.add(panelResumoMetricas);
		panelResumoMetricas.setLayout(null);

		JButton botaoImportarRegras = new JButton("Importar Regras");
		botaoImportarRegras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botaoImportarRegras.setBounds(10, 364, 186, 21);
		botaoImportarRegras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				defaultListModel.clear();
				rules.clear();
				JFileChooser jFileChooserImportar = new JFileChooser("Escolha pasta a importar");
				jFileChooserImportar.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnValue = jFileChooserImportar.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					rules = (history.readFile(jFileChooserImportar.getSelectedFile().getAbsolutePath()));
					for (int i = 0; i < rules.size(); i++) {
						if (rules.size() == 1) {
							if (rules.get(i).getRuleType() == 0) {
								defaultListModel.addElement("        *GOD CLASS*");
								tipoComparacao = 2;
							} else {
								defaultListModel.addElement("        *LONG METHOD*");
								tipoComparacao = 3;
							}
						} else {
							if (rules.get(i).getRuleType() == 0)
								defaultListModel.addElement("        *GOD CLASS*");
							else
								defaultListModel.addElement("        *LONG METHOD*");

							tipoComparacao = 1;
						}
						for (int l = 0; l < rules.get(i).getMetricName().size(); l++) {
							String line = rules.get(i).getMetricName().get(l) + " " + rules.get(i).getComp().get(l)
									+ " " + rules.get(i).getLimits().get(l);
							defaultListModel.addElement(line);
							if (l < rules.get(i).getMetricName().size() - 1)
								defaultListModel.addElement(rules.get(i).getOper().get(i));
						}
						defaultListModel.addElement("  ");
					}
				}
			}
		});
		panelImportRegras.add(list);
		panelImportRegras.add(botaoImportarRegras);

		JLabel labelRegrasImportadas = new JLabel("Lista de Regras importadas");
		labelRegrasImportadas.setHorizontalAlignment(SwingConstants.CENTER);
		labelRegrasImportadas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelRegrasImportadas.setBounds(10, 10, 186, 31);
		panelImportRegras.add(labelRegrasImportadas);

		JLabel labelResumoRegras = new JLabel("Resumo visualiza\u00E7\u00E3o das m\u00E9tricas:");
		labelResumoRegras.setHorizontalAlignment(SwingConstants.CENTER);
		labelResumoRegras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelResumoRegras.setBounds(10, 10, 222, 13);
		panelResumoMetricas.add(labelResumoRegras);

		JLabel labelNPacotes = new JLabel("N\u00BA packages:");
		labelNPacotes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelNPacotes.setBounds(10, 33, 98, 13);
		panelResumoMetricas.add(labelNPacotes);

		JLabel labelNClasses = new JLabel("N\u00BA classes:");
		labelNClasses.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelNClasses.setBounds(10, 56, 98, 13);
		panelResumoMetricas.add(labelNClasses);

		JLabel labelNMethods = new JLabel("N\u00BA m\u00E9todos:");
		labelNMethods.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelNMethods.setBounds(10, 79, 98, 13);
		panelResumoMetricas.add(labelNMethods);

		JLabel labelNLinhas = new JLabel("N\u00BA linhas:");
		labelNLinhas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelNLinhas.setBounds(10, 102, 98, 13);
		panelResumoMetricas.add(labelNLinhas);

		nPackagesLabel = new JLabel("n");
		nPackagesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nPackagesLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nPackagesLabel.setBounds(187, 34, 45, 13);
		panelResumoMetricas.add(nPackagesLabel);

		nClassesLabel = new JLabel("n");
		nClassesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nClassesLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nClassesLabel.setBounds(187, 57, 45, 13);
		panelResumoMetricas.add(nClassesLabel);

		nMethodsLabel = new JLabel("n");
		nMethodsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nMethodsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nMethodsLabel.setBounds(187, 80, 45, 13);
		panelResumoMetricas.add(nMethodsLabel);

		nLinesLabel = new JLabel("n");
		nLinesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nLinesLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nLinesLabel.setBounds(187, 103, 45, 13);
		panelResumoMetricas.add(nLinesLabel);

		JButton botaoAbrirExcel = new JButton("Abrir Excel");
		botaoAbrirExcel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botaoAbrirExcel.setBounds(129, 126, 103, 33);
		botaoAbrirExcel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (excelFile == null)
						JOptionPane.showMessageDialog(null,
								"Impossível abrir o ficheiro Excel!\nPor favor faça 'Run', para que o mesmo seja criado.");
					Desktop desktop = Desktop.getDesktop();
					desktop.open(excelFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panelResumoMetricas.add(botaoAbrirExcel);

		JPanel panelComparacaoMetricas = new JPanel();
		panelComparacaoMetricas.setBounds(943, 263, 242, 191);
		contentPanePrincipal.add(panelComparacaoMetricas);
		panelComparacaoMetricas.setLayout(null);

		JLabel labelVP = new JLabel("Verdadeiros Positivos:");
		labelVP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelVP.setBounds(10, 10, 121, 13);
		panelComparacaoMetricas.add(labelVP);

		JLabel labelVN = new JLabel("Verdadeiros Negativos:");
		labelVN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelVN.setBounds(10, 33, 133, 13);
		panelComparacaoMetricas.add(labelVN);

		JLabel labelFP = new JLabel("Falsos Positvos:");
		labelFP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelFP.setBounds(10, 56, 98, 13);
		panelComparacaoMetricas.add(labelFP);

		JLabel labelFN = new JLabel("Falsos Negativos:");
		labelFN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelFN.setBounds(10, 79, 98, 13);
		panelComparacaoMetricas.add(labelFN);

		verdadeiroPositivoLabel = new JLabel("n");
		verdadeiroPositivoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		verdadeiroPositivoLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		verdadeiroPositivoLabel.setBounds(187, 11, 45, 13);
		panelComparacaoMetricas.add(verdadeiroPositivoLabel);

		verdadeiroNegativoLabel = new JLabel("n");
		verdadeiroNegativoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		verdadeiroNegativoLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		verdadeiroNegativoLabel.setBounds(187, 34, 45, 13);
		panelComparacaoMetricas.add(verdadeiroNegativoLabel);

		falsePositivoLabel = new JLabel("n");
		falsePositivoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		falsePositivoLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		falsePositivoLabel.setBounds(187, 57, 45, 13);
		panelComparacaoMetricas.add(falsePositivoLabel);

		falseNegativoLabel = new JLabel("n");
		falseNegativoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		falseNegativoLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		falseNegativoLabel.setBounds(187, 80, 45, 13);
		panelComparacaoMetricas.add(falseNegativoLabel);

		JButton botaoGrafico = new JButton("Gr\u00E1fico");
		botaoGrafico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botaoGrafico.setBounds(129, 103, 103, 33);
		botaoGrafico.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				if (central == null)
					JOptionPane.showMessageDialog(null,
							"Impossível vizualizar o gráfico!\nPor favor faça 'Run', para que o mesmo seja criado.");
				DefaultPieDataset dataset = new DefaultPieDataset();
				dataset.setValue("VP", 21);
				dataset.setValue("VN", 10);
				dataset.setValue("FP", 3);
				dataset.setValue("FN", 14);
				dataset.setValue("VP", central.getComparador().getCountVP());
				dataset.setValue("VN", central.getComparador().getCountVN());
				dataset.setValue("FP", central.getComparador().getCountFP());
				dataset.setValue("FN", central.getComparador().getCountFN());

				JFreeChart pieChart = ChartFactory.createPieChart("gráfico", dataset, false, true, false);

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
		panelComparacaoMetricas.add(botaoGrafico);

		JTable table = new JTable(30, 10);
		table.setToolTipText("");
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setEnabled(false);

		scrollPaneTabela = new JScrollPane();
		scrollPaneTabela.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTabela.setBounds(225, 10, 708, 504);
		scrollPaneTabela.setViewportView(table);
		contentPanePrincipal.add(scrollPaneTabela);
	}

	public JTable escreveTabela(ArrayList<BoolResultado> isgodclass, ArrayList<BoolResultado> islongmethod,
			Comparador comparador, int tipo) {
		ArrayList<String[]> list = new ArrayList<>();
		if (tipo == 1) {
			String[] fixo = { "Method ID", "Pacote", "Classe", "Método", "is_God_Class", "Verifcação", "is_Long_Method",
					"Verificação" };
			list.add(fixo);
		}
		if (tipo == 2) {
			String[] fixo = { "Method ID", "Pacote", "Classe", "Método", "is_God_Class", "Verifcação" };
			list.add(fixo);
		}
		if (tipo == 3) {
			String[] fixo = { "Method ID", "Pacote", "Classe", "Método", "is_Long_Method", "Verificação" };
			list.add(fixo);
		}
		Object[][] data = new Object[isgodclass.size()][list.get(0).length];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = isgodclass.get(i).getId();
			data[i][1] = isgodclass.get(i).getPackage();
			data[i][2] = isgodclass.get(i).getClasses();
			data[i][3] = isgodclass.get(i).getMetodo();
			if (tipo == 2) { // caso utilizador selecione apenas isgodclass
				data[i][4] = isgodclass.get(i).getVerificacao();
				data[i][5] = central.getComparador().getClassCheck().get(i);
			}
			if (tipo == 3) { // caso utilizador selecione apenas islongmethod
				data[i][4] = islongmethod.get(i).getVerificacao();
				data[i][5] = central.getComparador().getMethodCheck().get(i);
			}
			if (tipo == 1) { // caso utilizador selecione ambas
				data[i][4] = isgodclass.get(i).getVerificacao();
				data[i][5] = central.getComparador().getClassCheck().get(i);
				data[i][6] = islongmethod.get(i).getVerificacao();
				data[i][7] = central.getComparador().getMethodCheck().get(i);

			}
		}
		JTable table = new JTable(data, list.get(0));
		table.setEnabled(false);
		return table;
	}

	public void writeStatsLabels() {
		nPackagesLabel.setText(Integer.toString(central.getNumberOfPackages()));
		nClassesLabel.setText(Integer.toString(central.getNumberOfClasses()));
		nMethodsLabel.setText(Integer.toString(central.getNumberOfMethods()));
		nLinesLabel.setText(Integer.toString(central.getNumberOfLines()));
		verdadeiroPositivoLabel.setText(Integer.toString(central.getComparador().getCountVP()));
		verdadeiroNegativoLabel.setText(Integer.toString(central.getComparador().getCountVN()));
		falsePositivoLabel.setText(Integer.toString(central.getComparador().getCountFP()));
		falseNegativoLabel.setText(Integer.toString(central.getComparador().getCountFN()));
		nPackagesLabel.updateUI();
		nClassesLabel.updateUI();
		nMethodsLabel.updateUI();
		nLinesLabel.updateUI();
		verdadeiroPositivoLabel.updateUI();
		verdadeiroNegativoLabel.updateUI();
		falsePositivoLabel.updateUI();
		falseNegativoLabel.updateUI();
	}
}