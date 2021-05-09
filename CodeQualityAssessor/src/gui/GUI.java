package gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import central.BoolResultado;
import central.Central;
import central.History;
import excel.Comparador;
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

	private int tipoComparacao; // vai ser retirado consoante as checkbox de god class e long method // 1 = God Class + Long Method; 2 = God Class; 3 = Long Method

	private ArrayList<String> metricNames = new ArrayList<>();
	private ArrayList<comparator> Comparators = new ArrayList<>();
	private ArrayList<operator> Operators = new ArrayList<>();
	private ArrayList<Integer> limits = new ArrayList<>();
	private ArrayList<String> metricNames1 = new ArrayList<>();
	private ArrayList<comparator> Comparators1 = new ArrayList<>();
	private ArrayList<operator> Operators1 = new ArrayList<>();
	private ArrayList<Integer> limits1 = new ArrayList<>();
	private List<File> folders = new ArrayList<>();
	private ArrayList<File> files = new ArrayList<>();

	private ArrayList<Rule> rules = new ArrayList<>();

	private String desktop = System.getProperty("user.home") + "/Desktop";

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
		Operators.clear();
		Operators1.clear();
		Comparators.clear();
		Comparators1.clear();
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
		setBounds(100, 100, 1215, 613);
		contentPanePrincipal = new JPanel();
		contentPanePrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanePrincipal);

		textFieldSrcPath = new JTextField();
		textFieldSrcPath.setBounds(10, 531, 923, 33);
		textFieldSrcPath.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldSrcPath.setText("Selecione a pasta do seu projeto");
		textFieldSrcPath.setEditable(false);
		contentPanePrincipal.add(textFieldSrcPath);
		textFieldSrcPath.setColumns(10);
		
		selectProjectFolderButton();
		runMetricsButton();
		rulesPanel();
		panelResumoMetricas();
		panelComparacaoMetricas();
		excelTablePanel();

	}

	private void panelResumoMetricas() {
		JPanel panelResumoMetricas = new JPanel();
		panelResumoMetricas.setBounds(943, 10, 242, 210);
		contentPanePrincipal.add(panelResumoMetricas);
		panelResumoMetricas.setLayout(null);

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
					if (excelFile == null) {
						JOptionPane.showMessageDialog(null,
								"Impossível abrir o ficheiro Excel!\nPor favor faça 'Run', para que o mesmo seja criado.");
						return;
					}
					Desktop desktop = Desktop.getDesktop();
					desktop.open(excelFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panelResumoMetricas.add(botaoAbrirExcel);
	}

	private void panelComparacaoMetricas() {
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

			@Override
			public void actionPerformed(ActionEvent e) {
				if (central == null) {
					JOptionPane.showMessageDialog(null,
							"Impossível vizualizar o gráfico!\nPor favor faça 'Run', para que o mesmo seja criado.");
					return;
				}
				DefaultPieDataset<String> dataset = new DefaultPieDataset<String>();
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

				frame1.getContentPane().add(chartPanel);
				frame1.setVisible(true);
			}
		});
		panelComparacaoMetricas.add(botaoGrafico);
	}

	private void excelTablePanel() {
		String[] fixo = { "Method ID", "Pacote", "Classe", "Método", "is_God_Class", "Verifcação", "is_Long_Method",
				"Verificação" };
		JTable table = new JTable(new DefaultTableModel(fixo, 30));
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
		this.repaint();
	}

	private void selectProjectFolderButton() {
		JButton botaoPasta = new JButton("Pasta");
		botaoPasta.setBounds(943, 481, 242, 33);
		botaoPasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File selectedFile = new File("");
//				JFileChooser jFileChooserPasta = new JFileChooser("Escolha a pasta");
				JFileChooser jFileChooserPasta = new JFileChooser(desktop);
				jFileChooserPasta.setDialogTitle("Escolha a pasta");
				jFileChooserPasta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = jFileChooserPasta.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					selectedFile = jFileChooserPasta.getSelectedFile();
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
							return;
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					textFieldSrcPath.setText(selectedFile.getAbsolutePath());
					src_path = new File(textFieldSrcPath.getText());
				}
			}
		});
		contentPanePrincipal.setLayout(null);
		botaoPasta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPanePrincipal.add(botaoPasta);
	}

	private void runMetricsButton() {
		JButton botaoRun = new JButton("Run");
		botaoRun.setBounds(943, 530, 242, 33);
		botaoRun.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botaoRun.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rules.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Precisa de selecionar pelo menos um Code Smell!\nTente novamente");
					return;
				} else if (textFieldSrcPath.getText().equals("Selecione a pasta do seu projeto")) {
					JOptionPane.showMessageDialog(null, "Não selecionou uma pasta de projeto!");
					return;
				} else {
					JFileChooser jFileChooserRun = new JFileChooser(desktop);
					jFileChooserRun.setDialogTitle("Escolha onde guardar o excel");
					jFileChooserRun.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int returnValue = jFileChooserRun.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						try {
							central = new Central(rules, src_path, tipoComparacao, files);
//							System.out.println(jFileChooserRun.getSelectedFile().getAbsolutePath());
							central.setExcelFileDir(jFileChooserRun.getSelectedFile().getAbsolutePath());
							central.ini();
							excelFile = central.getExcelFile();
							writeStatsLabels();
							scrollPaneTabela.setViewportView(escreveTabela(central.getBoolClass(),
									central.getBoolMethod(), central.getComparador(), tipoComparacao));
							cleanArrays();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		contentPanePrincipal.add(botaoRun);
	}

	private void rulesPanel() {
		DefaultListModel<Object> defaultListModel = new DefaultListModel<Object>();
		JList<Object> list = new JList<Object>(defaultListModel);
		list.setBounds(10, 40, 186, 270);
		
		JPanel panelImportRegras = new JPanel();
		panelImportRegras.setBounds(10, 10, 206, 504);
		contentPanePrincipal.add(panelImportRegras);
		panelImportRegras.setLayout(null);

		JLabel labelRegrasImportadas = new JLabel("Lista de Regras importadas");
		labelRegrasImportadas.setHorizontalAlignment(SwingConstants.CENTER);
		labelRegrasImportadas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelRegrasImportadas.setBounds(10, 10, 186, 31);
		panelImportRegras.add(labelRegrasImportadas);

		JButton botaoImportarRegras = new JButton("Importar Regras");
		botaoImportarRegras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botaoImportarRegras.setBounds(10, 370, 186, 20);
		botaoImportarRegras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				defaultListModel.clear();
				rules.clear();
//				JFileChooser jFileChooserImportar = new JFileChooser("Escolha pasta a importar");

				JFileChooser jFileChooserImportar = new JFileChooser(desktop);
				jFileChooserImportar.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jFileChooserImportar.setAcceptAllFileFilterUsed(false);
				jFileChooserImportar.setFileFilter(new FileFilter() {

					@Override
					public String getDescription() {
						return "Rule (*.jrule)";
					}

					@Override
					public boolean accept(File f) {
						if (f.isDirectory()) {
							return true;
						} else {
							String filename = f.getName().toLowerCase();
							return filename.endsWith(".jrule");
						}
					}
				});

				int returnValue = jFileChooserImportar.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					rules = history.readFile(jFileChooserImportar.getSelectedFile().getPath());
					if (!rules.isEmpty()) {
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
					} else {
						System.err.println("Não foi possível importar a regra");
					}
				}
			}
		});
		panelImportRegras.add(list);
		panelImportRegras.add(botaoImportarRegras);

		JButton botaoEditar = new JButton("Criar/Editar Regra");
		botaoEditar.setBounds(10, 330, 186, 20);
		panelImportRegras.add(botaoEditar);
		botaoEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botaoEditar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EditRulesWindow(GUI.this);
			}
		});
	}

	public History getHistory() {
		return history;
	}

	public ArrayList<Rule> getRules() {
		return rules;
	}

	public ArrayList<String> getMetricNames() {
		return metricNames;
	}

	public ArrayList<comparator> getComparators() {
		return Comparators;
	}

	public ArrayList<operator> getOperators() {
		return Operators;
	}

	public ArrayList<Integer> getLimits() {
		return limits;
	}

	public ArrayList<String> getMetricNames1() {
		return metricNames1;
	}

	public ArrayList<comparator> getComparators1() {
		return Comparators1;
	}

	public ArrayList<operator> getOperators1() {
		return Operators1;
	}

	public ArrayList<Integer> getLimits1() {
		return limits1;
	}

	public int getTipoComparacao() {
		return tipoComparacao;
	}

	public void setTipoComparacao(int tipoComparacao) {
		this.tipoComparacao = tipoComparacao;
	}
}