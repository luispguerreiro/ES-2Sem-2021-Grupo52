package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

import central.History;
import net.miginfocom.swing.MigLayout;
import rules.Rule;
import rules.Rule.comparator;
import rules.Rule.operator;

@SuppressWarnings("serial")
public class EditRulesWindow extends JFrame {

	private GUI gui;

	private History history;

	private JPanel contentPane;
	private JTextField txtThreasholdMethodLOC;
	private JTextField txtThreasholdMethodCYCLO;
	private JTextField txtThreasholdClassLOC;
	private JTextField txtThreasholdClassNOM;
	private JTextField txtThreasholdClassWMC;

	private JCheckBox chckBoxLongMethod;
	private JComboBox<comparator> comboBoxLOCMethod;
	private JComboBox<comparator> comboBoxCYCLOMethod;
	private JComboBox<operator> comboBoxLongMethodoperator;
	private JCheckBox chckBoxGodClass;
	private JComboBox<comparator> comboBoxLOCClass;
	private JComboBox<comparator> comboBoxNOMClass;
	private JComboBox<comparator> comboBoxWMC;
	private JComboBox<operator> comboBoxGCoperator1;
	private JComboBox<operator> comboBoxGCoperator2;
	private JButton btnSaveAndApply;
	private JButton btnApplyWithoutSave;
	private ArrayList<Rule> rules = new ArrayList<>();

	private ArrayList<String> metricNames = new ArrayList<>();
	private ArrayList<comparator> comparators = new ArrayList<>();
	private ArrayList<operator> operators = new ArrayList<>();
	private ArrayList<Integer> limits = new ArrayList<>();
	private ArrayList<String> metricNames1 = new ArrayList<>();
	private ArrayList<comparator> comparators1 = new ArrayList<>();
	private ArrayList<operator> operators1 = new ArrayList<>();
	private ArrayList<Integer> limits1 = new ArrayList<>();

	private String ruleName;
	
	private String desktop = System.getProperty("user.home") + "/Desktop";

	private boolean LM = false;
	private boolean GC = false;

	/**
	 * Create the frame.
	 */
	public EditRulesWindow(GUI gui) {
		this.gui = gui;
		this.history = gui.getHistory();
		this.rules = gui.getRules();
		this.metricNames = gui.getMetricNames();
		this.comparators = gui.getComparators();
		this.operators = gui.getOperators();
		this.limits = gui.getLimits();
		this.metricNames1 = gui.getMetricNames1();
		this.comparators1 = gui.getComparators1();
		this.operators1 = gui.getOperators1();
		this.limits1 = gui.getLimits1();
		setResizable(false);
		setTitle("Editar Regras...");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 628, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[132.00,grow][127.00][17.00][137.00][14.00][grow]",
				"[35.00:35.00:35.00][32.00:32.00:32.00][32.00:32.00:32.00][32.00:32.00:32.00][35.00:35.00:35.00][32.00:32.00:32.00][32.00:32.00:32.00][32.00:32.00:32.00][32.00:32.00:32.00][5:5:5][32.00:32.00:32.00]"));

		LongMethod();
		GodClass();
		saveAndApplyButton();
		applyWithoutSaveButton();
		setVisible(true);
	}

	private void saveAndApplyButton() {
		JFileChooser fileChooserSave = new JFileChooser(desktop);
		fileChooserSave.setAcceptAllFileFilterUsed(false);
		fileChooserSave.setFileFilter(new FileFilter() {
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
		btnSaveAndApply = new JButton("Gravar e Aplicar");
		btnSaveAndApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckBoxGodClass.isSelected() || chckBoxLongMethod.isSelected()) {
					apply();
					if ((LM && chckBoxLongMethod.isSelected()) || (GC && chckBoxGodClass.isSelected())) {
						int retval = fileChooserSave.showSaveDialog(btnSaveAndApply);
						if (retval == JFileChooser.APPROVE_OPTION) {
							File file = fileChooserSave.getSelectedFile();
							if (file == null) {
								return;
							} else {
								ruleName = file.getName();
								history.setFolderPathToSave(file.getParent());
								history.setRuleName(ruleName);
								history.writeFile(rules);
							}
						}
						EditRulesWindow.this.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Nenhum code smell foi definido!\nTente novamente");
					return;
				}
			}
		});
		contentPane.add(btnSaveAndApply, "cell 0 10 2 1,growx,aligny center");
	}

	private void applyWithoutSaveButton() {
		btnApplyWithoutSave = new JButton("Aplicar sem Gravar");
		btnApplyWithoutSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckBoxGodClass.isSelected() || chckBoxLongMethod.isSelected()) {
					apply();
					EditRulesWindow.this.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Nenhum code smell foi definido!\nTente novamente");
					return;
				}
			}
		});
		contentPane.add(btnApplyWithoutSave, "cell 3 10 3 1,growx,aligny center");
	}

	private void LongMethod() {
		// First line of Frame » Long Method checkbox + LOC Method Threashold
		chckBoxLongMethod = new JCheckBox("Long Method");
		chckBoxLongMethod.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chckBoxLongMethod.isSelected()) {
					comboBoxCYCLOMethod.setEnabled(true);
					comboBoxLOCMethod.setEnabled(true);
					comboBoxLongMethodoperator.setEnabled(true);
					txtThreasholdMethodCYCLO.setEnabled(true);
					txtThreasholdMethodLOC.setEnabled(true);
				} else {
					comboBoxCYCLOMethod.setEnabled(false);
					comboBoxLOCMethod.setEnabled(false);
					comboBoxLongMethodoperator.setEnabled(false);
					txtThreasholdMethodCYCLO.setEnabled(false);
					txtThreasholdMethodLOC.setEnabled(false);
				}
			}
		});
		chckBoxLongMethod.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(chckBoxLongMethod, "cell 0 0,alignx left,aligny center");

		JLabel lblLOCMethod = new JLabel("Lines of Code");
		lblLOCMethod.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblLOCMethod, "cell 1 0,alignx left,aligny center");

		comboBoxLOCMethod = new JComboBox<comparator>();
		comboBoxLOCMethod.setEnabled(false);
		comboBoxLOCMethod.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxLOCMethod.setModel(new DefaultComboBoxModel<comparator>(comparator.values()));
		contentPane.add(comboBoxLOCMethod, "cell 3 0,growx,aligny center");

		txtThreasholdMethodLOC = new JTextField();
		txtThreasholdMethodLOC.setEnabled(false);
		txtThreasholdMethodLOC.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtThreasholdMethodLOC.getText().equals("Threashold"))
					txtThreasholdMethodLOC.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (txtThreasholdMethodLOC.getText().isBlank())
					txtThreasholdMethodLOC.setText("Threashold");
			}
		});
		txtThreasholdMethodLOC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtThreasholdMethodLOC.setText("Threashold");
		contentPane.add(txtThreasholdMethodLOC, "cell 5 0,growx");
		txtThreasholdMethodLOC.setColumns(10);

		// Combo box for operator [OR;AND] selection
		comboBoxLongMethodoperator = new JComboBox<operator>();
		comboBoxLongMethodoperator.setEnabled(false);
		comboBoxLongMethodoperator.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxLongMethodoperator.setModel(new DefaultComboBoxModel<operator>(operator.values()));
		contentPane.add(comboBoxLongMethodoperator, "cell 1 1,growx,aligny center");

		// CYCLO Method Threashold
		JLabel lblCYCLOMethod = new JLabel("Cyclomatic Complexity");
		lblCYCLOMethod.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblCYCLOMethod, "cell 1 2,alignx left,aligny center");

		comboBoxCYCLOMethod = new JComboBox<comparator>();
		comboBoxCYCLOMethod.setEnabled(false);
		comboBoxCYCLOMethod.setModel(new DefaultComboBoxModel<comparator>(comparator.values()));
		comboBoxCYCLOMethod.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(comboBoxCYCLOMethod, "cell 3 2,growx,aligny center");

		txtThreasholdMethodCYCLO = new JTextField();
		txtThreasholdMethodCYCLO.setEnabled(false);
		txtThreasholdMethodCYCLO.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtThreasholdMethodCYCLO.getText().equals("Threashold")) {
					txtThreasholdMethodCYCLO.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (txtThreasholdMethodCYCLO.getText().isBlank()) {
					txtThreasholdMethodCYCLO.setText("Threashold");
				}
			}
		});
		txtThreasholdMethodCYCLO.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtThreasholdMethodCYCLO.setText("Threashold");
		contentPane.add(txtThreasholdMethodCYCLO, "cell 5 2,growx");
		txtThreasholdMethodCYCLO.setColumns(10);
	}

	private void GodClass() {
		chckBoxGodClass = new JCheckBox("God Class");
		chckBoxGodClass.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chckBoxGodClass.isSelected()) {
					comboBoxGCoperator1.setEnabled(true);
					comboBoxGCoperator2.setEnabled(true);
					comboBoxLOCClass.setEnabled(true);
					comboBoxNOMClass.setEnabled(true);
					comboBoxWMC.setEnabled(true);
					txtThreasholdClassLOC.setEnabled(true);
					txtThreasholdClassNOM.setEnabled(true);
					txtThreasholdClassWMC.setEnabled(true);
				} else {
					comboBoxGCoperator1.setEnabled(false);
					comboBoxGCoperator2.setEnabled(false);
					comboBoxLOCClass.setEnabled(false);
					comboBoxNOMClass.setEnabled(false);
					comboBoxWMC.setEnabled(false);
					txtThreasholdClassLOC.setEnabled(false);
					txtThreasholdClassNOM.setEnabled(false);
					txtThreasholdClassWMC.setEnabled(false);
				}
			}
		});
		chckBoxGodClass.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(chckBoxGodClass, "cell 0 4,alignx left,aligny center");

		JLabel lblNewLabel_2 = new JLabel("Lines of Code");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblNewLabel_2, "cell 1 4");

		comboBoxLOCClass = new JComboBox<comparator>();
		comboBoxLOCClass.setEnabled(false);
		comboBoxLOCClass.setModel(new DefaultComboBoxModel<comparator>(comparator.values()));
		comboBoxLOCClass.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(comboBoxLOCClass, "cell 3 4,growx");

		txtThreasholdClassLOC = new JTextField();
		txtThreasholdClassLOC.setEnabled(false);
		txtThreasholdClassLOC.setColumns(10);
		txtThreasholdClassLOC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtThreasholdClassLOC.setText("Threashold");
		txtThreasholdClassLOC.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtThreasholdClassLOC.getText().equals("Threashold")) {
					txtThreasholdClassLOC.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (txtThreasholdClassLOC.getText().isBlank()) {
					txtThreasholdClassLOC.setText("Threashold");
				}
			}
		});
		contentPane.add(txtThreasholdClassLOC, "cell 5 4,growx");

		comboBoxGCoperator1 = new JComboBox<operator>();
		comboBoxGCoperator1.setEnabled(false);
		comboBoxGCoperator1.setModel(new DefaultComboBoxModel<operator>(operator.values()));
		comboBoxGCoperator1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(comboBoxGCoperator1, "cell 1 5,growx");

		JLabel lblNewLabel_3 = new JLabel("Number of Methods");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblNewLabel_3, "cell 1 6");

		comboBoxNOMClass = new JComboBox<comparator>();
		comboBoxNOMClass.setEnabled(false);
		comboBoxNOMClass.setModel(new DefaultComboBoxModel<comparator>(comparator.values()));
		comboBoxNOMClass.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(comboBoxNOMClass, "cell 3 6,growx");

		txtThreasholdClassNOM = new JTextField();
		txtThreasholdClassNOM.setEnabled(false);
		txtThreasholdClassNOM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtThreasholdClassNOM.setText("Threashold");
		txtThreasholdClassNOM.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtThreasholdClassNOM.getText().equals("Threashold")) {
					txtThreasholdClassNOM.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtThreasholdClassNOM.getText().isBlank()) {
					txtThreasholdClassNOM.setText("Threashold");
				}
			}
		});
		contentPane.add(txtThreasholdClassNOM, "cell 5 6,growx");
		txtThreasholdClassNOM.setColumns(10);

		comboBoxGCoperator2 = new JComboBox<operator>();
		comboBoxGCoperator2.setEnabled(false);
		comboBoxGCoperator2.setModel(new DefaultComboBoxModel<operator>(operator.values()));
		comboBoxGCoperator2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(comboBoxGCoperator2, "cell 1 7,growx");

		JLabel lblNewLabel_4 = new JLabel("Cyclomatic Complexity");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblNewLabel_4, "cell 1 8");

		comboBoxWMC = new JComboBox<comparator>();
		comboBoxWMC.setEnabled(false);
		comboBoxWMC.setModel(new DefaultComboBoxModel<comparator>(comparator.values()));
		comboBoxWMC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(comboBoxWMC, "cell 3 8,growx");

		txtThreasholdClassWMC = new JTextField();
		txtThreasholdClassWMC.setEnabled(false);
		txtThreasholdClassWMC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtThreasholdClassWMC.setText("Threashold");
		txtThreasholdClassWMC.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtThreasholdClassWMC.getText().equals("Threashold")) {
					txtThreasholdClassWMC.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (txtThreasholdClassWMC.getText().isBlank()) {
					txtThreasholdClassWMC.setText("Threashold");
				}
			}
		});
		contentPane.add(txtThreasholdClassWMC, "cell 5 8,growx");
		txtThreasholdClassWMC.setColumns(10);
	}

	private void apply() {
		if (chckBoxLongMethod.isSelected() && chckBoxGodClass.isSelected())
			gui.setTipoComparacao(1);
		if (chckBoxLongMethod.isSelected()) {
			if (txtThreasholdMethodLOC.getText().matches("[0-9]+") && !comboBoxLOCMethod.getSelectedItem().equals(comparator.EMPTY)) {
				limits.add(Integer.parseInt(txtThreasholdMethodLOC.getText()));
				comparators.add((comparator) comboBoxLOCMethod.getSelectedItem());
				metricNames.add("LOC_method");
				LM = true;
			}
			if (txtThreasholdMethodCYCLO.getText().matches("[0-9]+") && !comboBoxCYCLOMethod.getSelectedItem().equals(comparator.EMPTY)) {
				limits.add(Integer.parseInt(txtThreasholdMethodCYCLO.getText()));
				comparators.add((comparator) comboBoxCYCLOMethod.getSelectedItem());
				metricNames.add("CYCLO_method");
				LM = true;
			}
			if (!comboBoxLongMethodoperator.getSelectedItem().equals(operator.EMPTY))
				operators.add((operator) comboBoxLongMethodoperator.getSelectedItem());

			try {
				if (LM) {
					System.out.println(metricNames.size() + "  " + comparators.size() + "  " + limits.size() + "  " + operators.size());
					System.out.println("Rule name Long Method: " + ruleName);
					rules.add(new Rule(ruleName, 1, metricNames, comparators, limits, operators));
				}
//				else {
//					JOptionPane.showMessageDialog(null,
//							"Selecionou Long Method, no entanto não foram passados todos os parâmetros necessários!\nTente novamente");
//					return;
//				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			gui.setTipoComparacao(3);
		}
		if (chckBoxGodClass.isSelected()) {
			if (txtThreasholdClassLOC.getText().matches("[0-9]+") && !comboBoxLOCClass.getSelectedItem().equals(comparator.EMPTY)) {
				limits1.add(Integer.parseInt(txtThreasholdClassLOC.getText()));
				metricNames1.add("LOC_class");
				comparators1.add((comparator) comboBoxLOCClass.getSelectedItem());
				GC = true;
			}
			if (txtThreasholdClassNOM.getText().matches("[0-9]+") && !comboBoxNOMClass.getSelectedItem().equals(comparator.EMPTY)) {
				limits1.add(Integer.parseInt(txtThreasholdClassNOM.getText()));
				metricNames1.add("NOM_class");
				comparators1.add((comparator) comboBoxNOMClass.getSelectedItem());
				GC = true;
			}
			if (txtThreasholdClassWMC.getText().matches("[0-9]+") && !comboBoxWMC.getSelectedItem().equals(comparator.EMPTY)) {
				limits1.add(Integer.parseInt(txtThreasholdClassWMC.getText()));
				metricNames1.add("WMC_class");
				comparators1.add((comparator) comboBoxWMC.getSelectedItem());
				GC = true;
			}
			if (!comboBoxGCoperator1.getSelectedItem().equals(operator.EMPTY))
				operators1.add((operator) comboBoxGCoperator1.getSelectedItem());
			if (!comboBoxGCoperator2.getSelectedItem().equals(operator.EMPTY))
				operators1.add((operator) comboBoxGCoperator2.getSelectedItem());

			try {
				if (GC) {
					System.out.println(metricNames1.size() + "  " + comparators1.size() + "  " + limits1.size() + "  "
							+ operators1.size());
					System.out.println("Rule name God Class: " + ruleName);
					rules.add(new Rule(ruleName, 0, metricNames1, comparators1, limits1, operators1));
				}
//				else {
//					JOptionPane.showMessageDialog(null,
//							"Selecionou God Class, no entanto não foram passados todos os parâmetros necessários!\nTente novamente");
//					return;
//				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			gui.setTipoComparacao(2);
		}
	}
}