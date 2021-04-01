package maisTestes;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {
	
	//aqui está o código que vai buscar todos os ficheiros de uma pasta

	public static void main(String[] args) {
		Main m = new Main();
		m.setupGUI();

	}

	public void setupGUI() {
		JFrame frame = new JFrame("CodeQualityAssessor");

		frame.setLayout(new BorderLayout());

		JPanel panel1 = new JPanel(new BorderLayout());
		JPanel panel2 = new JPanel(new GridLayout(1, 2));

		JLabel label = new JLabel();
		label.setText("olá");
		JTextField jtf = new JTextField("");
		JButton b = new JButton("Pasta");
		b.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser("Escolha a pasta");
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					jtf.setText(selectedFile.getAbsolutePath());
				}
				try {
					File dir = new File(jtf.getText());
					if (dir.isDirectory()) {
						ArrayList<File> lista = new ArrayList<File>();
						Path path = Paths.get(dir.getAbsolutePath());
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
						JList<File> list = new JList<>(v);
						panel1.add(list, BorderLayout.EAST);
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		panel1.add(label);
		panel2.add(jtf);
		panel2.add(b);

		frame.add(panel1, BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.SOUTH);

		frame.setSize(800, 600);
		frame.setLocation(200, 100);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
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

}