import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

public class Internet extends JFrame{

	public Internet() {
		//menu begin
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar); //�̰� ���ؼ� JMenuBar�� ���δ�. add�� ����� �ʿ� ����

		JMenu scanMenu = new JMenu("sacn");
		JMenu gotoMenu = new JMenu("Go to");
		JMenu CommandsMenu = new JMenu("Commands");
		JMenu FavoritesMenu = new JMenu("Favorites");
		JMenu ToolsMenu = new JMenu("Tools");
		JMenu HelpMenu = new JMenu("Help");

		menubar.add(scanMenu);
		menubar.add(gotoMenu);
		menubar.add(CommandsMenu);
		menubar.add(FavoritesMenu);
		menubar.add(ToolsMenu);
		menubar.add(HelpMenu); //�޴� ���̱�

		JMenuItem loadFromFilesAction = new JMenuItem("Load from file...");
		JMenuItem exportallAction = new JMenuItem("Export all...");
		JMenuItem exportSelectionAction = new JMenuItem("Export Selection...");
		JMenuItem quitAction = new JMenuItem("Quit");

		scanMenu.add(loadFromFilesAction);
		scanMenu.add(exportallAction);
		scanMenu.add(exportSelectionAction);
		scanMenu.addSeparator(); // ���̼�
		scanMenu.add(quitAction); //scan ���̱�

		JMenuItem nextalivehostAction = new JMenuItem("Next alive host");
		JMenuItem nextopenpostAction = new JMenuItem("Next open post");
		JMenuItem nextdeadhostAction = new JMenuItem("Next dead host");
		JMenuItem previousalivehostAction = new JMenuItem("Previous alive host");
		JMenuItem previousopenpostAction = new JMenuItem("Previous open post");
		JMenuItem previousdeadhostAction = new JMenuItem("Previous dead host");
		JMenuItem findAction = new JMenuItem("Find...");

		gotoMenu.add(nextalivehostAction);
		gotoMenu.add(nextopenpostAction);
		gotoMenu.add(nextdeadhostAction);
		gotoMenu.addSeparator(); //���̼�
		gotoMenu.add(previousalivehostAction);
		gotoMenu.add(previousopenpostAction);
		gotoMenu.add(previousdeadhostAction);
		gotoMenu.addSeparator(); //���̼�
		gotoMenu.add(findAction); //goto ���̱�

		JMenuItem showdetailsAction = new JMenuItem("Show details");
		JMenuItem rescanIPAction = new JMenuItem("Rescan IP(s)");
		JMenuItem deleteIPAction = new JMenuItem("Delete IP(s)");
		JMenuItem copyIPAction = new JMenuItem("Copy IP");
		JMenuItem copydetalisAction = new JMenuItem("Copy details");
		JMenuItem openAction = new JMenuItem("Open");

		CommandsMenu.add(showdetailsAction);
		CommandsMenu.addSeparator(); //���̼�
		CommandsMenu.add(rescanIPAction);
		CommandsMenu.add(deleteIPAction);
		CommandsMenu.addSeparator(); //���̼�
		CommandsMenu.add(copyIPAction);
		CommandsMenu.add(copydetalisAction);
		CommandsMenu.addSeparator(); //���̼�
		CommandsMenu.add(openAction); //commands ���̱�

		JMenuItem addcurrentAction = new JMenuItem("Add current...");
		JMenuItem managefavoritesAction = new JMenuItem("Manage favorites...");

		FavoritesMenu.add(addcurrentAction);
		FavoritesMenu.add(managefavoritesAction); //favorites ���̱�

		JMenuItem preferencesAction = new JMenuItem("Preferences...");
		JMenuItem fetchersAction = new JMenuItem("Fetchers...");
		JMenuItem selectionAction = new JMenuItem("Selection");
		JMenuItem scanstatisticsAction = new JMenuItem("Scan statistics");

		ToolsMenu.add(preferencesAction);
		ToolsMenu.add(fetchersAction);
		ToolsMenu.addSeparator(); //���̼�
		ToolsMenu.add(selectionAction);
		ToolsMenu.add(scanstatisticsAction); //tools ���̱�

		JMenuItem gettingstartedAction = new JMenuItem("Getting Started");
		JMenuItem officialwebsiteAction = new JMenuItem("Official Website");
		JMenuItem faqAction = new JMenuItem("FAQ");
		JMenuItem reportanissueAction = new JMenuItem("Report an issue");
		JMenuItem pluginsAction = new JMenuItem("Plugins");
		JMenuItem commandlineusageAction = new JMenuItem("Command-line usage");
		JMenuItem checkfornewerversionAction = new JMenuItem("Check for newer version...");
		JMenuItem aboutAction = new JMenuItem("About");

		HelpMenu.add(gettingstartedAction);
		HelpMenu.addSeparator(); //���̼�
		HelpMenu.add(officialwebsiteAction);
		HelpMenu.add(faqAction);
		HelpMenu.add(reportanissueAction);
		HelpMenu.add(pluginsAction);
		HelpMenu.addSeparator(); //���̼�
		HelpMenu.add(commandlineusageAction);
		HelpMenu.addSeparator(); //���̼�
		HelpMenu.add(checkfornewerversionAction);
		HelpMenu.addSeparator(); //���̼�
		HelpMenu.add(aboutAction); //help ���̱�

		quitAction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		//menu end

		//status bar begin

		JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		add(statusPanel, BorderLayout.SOUTH);
		JLabel readyLabel = new JLabel("Ready");
		readyLabel.setPreferredSize(new Dimension(250,16)); // ����� �÷��� ������
		readyLabel.setBorder(new BevelBorder(BevelBorder.RAISED)); // ���¹� ������ ��
		JLabel displayLabel = new JLabel("Display: All");
		displayLabel.setPreferredSize(new Dimension(130,16)); // ����� �÷��� ������
		displayLabel.setBorder(new BevelBorder(BevelBorder.RAISED)); // ���¹� ������ ��
		JLabel threadLabel = new JLabel("Threads: 0");
		threadLabel.setPreferredSize(new Dimension(130,16)); // ����� �÷��� ������
		threadLabel.setBorder(new BevelBorder(BevelBorder.RAISED)); // ���¹� ������ ��
		
		JProgressBar jProgressBar = new JProgressBar();
		jProgressBar.setBounds(50,50,250,30);
		jProgressBar.setValue(0);
		jProgressBar.setStringPainted(true);
		jProgressBar.setPreferredSize(new Dimension(145,16)); // ����� �÷��� ������
		jProgressBar.setBorder(new BevelBorder(BevelBorder.RAISED)); // ���¹� ������ ��
		
		statusPanel.add(readyLabel);
		statusPanel.add(displayLabel);
		statusPanel.add(threadLabel); 
		statusPanel.add(jProgressBar);//���¹� �׸� ���̱�
		
		
		//status bar end

		//table begin

		String[] title = new String[] {
				"IP", "Ping", "TTL", "Hostname", "Port"
		};

		Object[][] stats = initTable();
		// stats[i][4] port
		JTable jTable = new JTable(stats, title);

		JScrollPane sp = new JScrollPane(jTable); //��ũ�ѹ� �����
		add(sp, BorderLayout.CENTER); //��ũ�ѹ� ���̱�

		//table end

		//tool bar begin

		Font myFont = new Font("Serif", Font.BOLD, 16); //���� ����, ��Ʈ ���, ũ��
		JToolBar toolbar1 = new JToolBar();
		toolbar1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JToolBar toolbar2 = new JToolBar();
		toolbar2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel rangeStartLabel= new JLabel("IP Range: "); //��� ���� �����
		JTextField rangeStartTextField = new JTextField(10);
		JLabel rangeEndLabel= new JLabel("to"); 
		JTextField rangeEndTextField = new JTextField(10);
		JComboBox IPRangeComboBox = new JComboBox();
		IPRangeComboBox.addItem("IP Range"); //IP �����ּҰ� C��
		IPRangeComboBox.addItem("Random");
		IPRangeComboBox.addItem("Text File");
		JButton settingButton = new JButton();
		settingButton.setIcon(new ImageIcon("./Image/setting.png"));

		rangeStartLabel.setFont(myFont); // ��Ʈ ũ�� ���� 
		rangeStartLabel.setPreferredSize(new Dimension(85, 30));
		rangeEndLabel.setFont(myFont); // ��Ʈ ũ�� ���� 
		rangeEndLabel.setPreferredSize(new Dimension(15, 30));
		IPRangeComboBox.setPreferredSize(new Dimension(90, 30));
		settingButton.setFont(myFont);
		settingButton.setPreferredSize(new Dimension(30, 30));

		toolbar1.add(rangeStartLabel);
		toolbar1.add(rangeStartTextField);
		toolbar1.add(rangeEndLabel);
		toolbar1.add(rangeEndTextField);
		toolbar1.add(IPRangeComboBox);
		toolbar1.add(settingButton);

		JLabel hostNameLabel = new JLabel("Hostname: ");
		JTextField hostNameTextfield = new JTextField(10);
		JButton upButton = new JButton("��IP");
		JComboBox optionComboBox = new JComboBox();
		optionComboBox.addItem("/24"); //IP �����ּҰ� C��
		optionComboBox.addItem("/26");
		optionComboBox.addItem("/16");
		optionComboBox.addItem("255...192");
		optionComboBox.addItem("255...128");
		optionComboBox.addItem("255...0");
		optionComboBox.addItem("255...0.0");
		optionComboBox.addItem("255...0.0.0");
		JButton startButton = new JButton("��Start");
		JButton MenuButton = new JButton();
		MenuButton.setIcon(new ImageIcon("./Image/menu.png"));

		hostNameLabel.setFont(myFont);
		hostNameTextfield.setPreferredSize(new Dimension(90, 30));
		upButton.setPreferredSize(new Dimension(50, 30));
		optionComboBox.setPreferredSize(new Dimension(90, 30));
		startButton.setPreferredSize(new Dimension(90, 30));
		MenuButton.setFont(myFont);
		MenuButton.setPreferredSize(new Dimension(30, 30));

		toolbar2.add(hostNameLabel);
		toolbar2.add(hostNameTextfield);
		toolbar2.add(upButton);
		toolbar2.add(optionComboBox);
		toolbar2.add(startButton);
		toolbar2.add(MenuButton);

		JPanel pane = new JPanel(new BorderLayout());
		pane.add(toolbar1, BorderLayout.NORTH);
		pane.add(toolbar2, BorderLayout.SOUTH);

		add(pane,BorderLayout.NORTH);

		//tool bar end

		//ȣ��Ʈ �̸��� RANGE �ֱ� begin

		String myIp = null;
		String myHostname = null;
		try {
			InetAddress ia = InetAddress.getLocalHost();

			myIp = ia.getHostAddress();
			myHostname = ia.getHostName();
		} catch (Exception e) {

		}
		String fixedIp = myIp.substring(0, myIp.lastIndexOf(".")+1);
		rangeStartTextField.setText(fixedIp+"1");
		rangeEndTextField.setText(fixedIp+"254");
		hostNameTextfield.setText(myHostname);

		//ȣ��Ʈ �̸��� RANGE �ֱ� end

		setSize(700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		//start button action begin

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new Thread(() -> {
				Pinging[] pi = new Pinging[254];
				for(int i=0; i<=253; i++) {
					pi[i] = new Pinging(fixedIp + (i+1));
					pi[i].start();
				}
				for(int i=0; i<=253; i++) {
					Object[] msg = pi[i].getMsg();
					if (msg[1] == null) {
						msg[1] = "[n/a]";
						msg[2] = "[n/s]";
						msg[3] = "[n/s]";

					} else if (msg[3] == null) {
						msg[3] = "[n/a]";
					} //���� host name�� �����̴�-> [n/a]�� ǥ��
					stats[i][0] = msg[0];
					stats[i][1] = msg[1];
					stats[i][2] = msg[2];
					stats[i][3] = msg[3];

					if(msg[1] != null || msg[2] != null || msg[3] != null) {
						final ExecutorService es = Executors.newFixedThreadPool(20);//20���� Ǯ, �����带 ����ڴٴ� �̾߱�
						final String ip = (String)msg[0];
						final int timeout = 20;
						final List<Future<ScanResult>> futures = new ArrayList<>();

						for (int port = 1; port <= 1024; port++) {

							futures.add(portlsOpen(es, ip, port, timeout));	
						}
						try {
							es.awaitTermination(200L, TimeUnit.MILLISECONDS);
						} catch (InterruptedException e1) {

							e1.printStackTrace();
						}
						int openPorts = 0;
						String openPortNumber = "";
						for(final Future<ScanResult>f : futures) {
							try {
								if(f.get().isOpen()) {
									openPorts++;
									openPortNumber += f.get().getPort()+","; 
									//��Ʈ��ȣ�� �����ؼ� ������ �־����, ��ȣ�� ȹ���� �� �ִ� ����

								}
							} catch (InterruptedException e1) {

								e1.printStackTrace();
							} catch (ExecutionException e1) {

								e1.printStackTrace();
							}
						}
						if(openPortNumber != null) {
							stats[i][4] = openPortNumber;
							//System.out.println(openPortNumber.substring(0, openPortNumber.length()-1));
						}else {
							//msg[4] = "[n/s]";
							stats[i][4] = "[n/s]";
						}

					}	
				}
				jTable.repaint();
				}).start();
			}
		});
		//start button action end	
	}
	public Object[][] initTable(){ 
		// ���̺� ũ��
		Object[][] result = new Object[254][5];
		return result;
	}

	public static Future<ScanResult>portlsOpen(final ExecutorService es, final String ip, final int port, final int timeout){
		return es.submit(new Callable<ScanResult>() { //submit�� �������� start�� ����ϴ�
			@Override
			public ScanResult call() {
				try {
					Socket socket = new Socket(); //���� ����ؼ� ������ ����
					socket.connect(new InetSocketAddress(ip, port), timeout);
					socket.close();
					return new ScanResult(port, true);
				}catch(Exception ex) {
					return new ScanResult(port, false);
				}//���������� �ϸ� �ð��� �ʹ� �����ɷ��� �����带 ����ؾ��Ѵ�.
			}
		});
	}

	public static void main(String[] args) {
		Internet op = new Internet();
	}

}
