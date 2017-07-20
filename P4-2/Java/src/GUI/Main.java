package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Models.Camera;
import Models.Iluminacao;
import Models.Objeto;
import Models.Plano;

import javax.swing.border.EmptyBorder;

public class Main extends JFrame{
	int ResX = 640;
	int ResY = 480;

	//Atributos para interface grafica
	JPanel painel;
	JLabel labelObj;
	JTextField fieldObj;
	JLabel labelCamera;
	JTextField fieldCamera;
	JLabel labelLuz;
	JTextField fieldLuz;
	JLabel labelPlano;
	JTextField fieldPlanoX;
	JTextField fieldPlanoY;
	JButton btn;

	//Janela com o objeto
	Phong phong;

	public Main(){
		// Define nome da janela
		super("Menu");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Define tamanho da janela
		setBounds(100, 100, 300, 480);
        painel = new JPanel();
		//getContentPane().add(painel);
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painel);
		painel.setLayout(null);

		JLabel titulo1 = new JLabel("Projeta a sombra do");
		titulo1.setBounds(10, 10, 300, 30);
		titulo1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		painel.add(titulo1);

		JLabel titulo2 = new JLabel("objeto sobre um plano:");
		titulo2.setBounds(10, 40, 400, 30);
		titulo2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		painel.add(titulo2);

		labelObj = new JLabel("Objeto:");
		labelObj.setBounds(10, 110, 80, 20);
		labelObj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		painel.add(labelObj);

		fieldObj = new JTextField("calice2");
		fieldObj.setBounds(80, 108, 120, 25);
		painel.add(fieldObj);

		labelCamera = new JLabel("Camera:");
		labelCamera.setBounds(10, 140, 80, 20);
		labelCamera.setFont(new Font("Tahoma", Font.PLAIN, 16));
		painel.add(labelCamera);

		fieldCamera = new JTextField("calice2");
		fieldCamera.setBounds(80, 140, 120, 25);
		painel.add(fieldCamera);

		labelLuz = new JLabel("Luz:");
		labelLuz.setBounds(10, 170, 80, 20);
		labelLuz.setFont(new Font("Tahoma", Font.PLAIN, 16));
		painel.add(labelLuz);

		fieldLuz = new JTextField("iluminacao");
		fieldLuz.setBounds(80, 170, 120, 25);
		painel.add(fieldLuz);

		labelPlano = new JLabel("Plano:");
		labelPlano.setBounds(10, 200, 80, 20);
		labelPlano.setFont(new Font("Tahoma", Font.PLAIN, 16));
		painel.add(labelPlano);

		fieldPlanoX = new JTextField("100");
		fieldPlanoX.setBounds(80, 200, 30, 25);
		painel.add(fieldPlanoX);

		JLabel labelX = new JLabel(" x ");
		labelX.setBounds(110, 203, 80, 20);
		labelX.setFont(new Font("Tahoma", Font.PLAIN, 16));
		painel.add(labelX);

		fieldPlanoY = new JTextField("100");
		fieldPlanoY.setBounds(130, 200, 30, 25);
		painel.add(fieldPlanoY);

		labelX = new JLabel("pxls");
		labelX.setBounds(165, 203, 80, 20);
		labelX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		painel.add(labelX);

		btn = new JButton("START");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					readFiles();
					setup();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn.setBounds(80, 235, 120, 23);
		painel.add(btn);

		//Cria-se uma Janela para o objeto apresentado por Phong.
		phong = new Phong(415, 100, ResX, ResY);
		phong.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	public static void main(String[] args) {
		Main frame = new Main();
		frame.setVisible(true);
	}

	public void setup(){
		Camera.setCamera();
		Camera.convertObject(ResX, ResY);

		Iluminacao.setIluminacao();

		Camera.setIntervalos();
		System.out.println("-> Variaveis manipuladas");

		phong.scanLine3D();
		phong.repaint();
		System.out.println("Cores Calculadas e Objeto pintado com Phong");
	}

	public void readFiles() throws IOException{
		// Carrega Objeto
		String workingDir=System.getProperty("user.dir");

		String objectName = fieldObj.getText();
		Objeto.setObjeto(workingDir+"/P4-2/Java/src/Entradas/Objetos/"+objectName+".byu");
		System.out.println("1 -> Objeto lido e inicializado");

		// Carrega Iluminação
		String iluminacaoName = fieldLuz.getText();
		Iluminacao.initIluminacao(workingDir+"/P4-2/Java/src/Entradas/"+iluminacaoName+".txt");
		System.out.println("2 -> Dados da cena lidos e inicializados");

		// Carrega plano
		String planoBase = fieldPlanoX.getText();
		String planoAltura = fieldPlanoY.getText();
		Plano.setPlano(planoBase, planoAltura);
		System.err.println("3 -> Plano lido e inicializado");

		// Carrega Camera
		String cameraName = fieldCamera.getText();
		Camera.initCamera(workingDir+"/P4-2/Java/src/Entradas/Cameras/"+cameraName+".cfg");
		System.out.println("4 -> Camera lida e inicializada");
	}

}
