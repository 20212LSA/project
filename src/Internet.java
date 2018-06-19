import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

public class Internet extends JFrame{
	
	public Internet() {
		//menu begin
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar); //이걸 통해서 JMenuBar를 붙인다. add를 사용할 필요 없음
		
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
		menubar.add(HelpMenu); //메뉴 붙이기
		
		JMenuItem loadFromFilesAction = new JMenuItem("Load from file...");
		JMenuItem exportallAction = new JMenuItem("Export all...");
		JMenuItem exportSelectionAction = new JMenuItem("Export Selection...");
		JMenuItem quitAction = new JMenuItem("Quit");
		
		scanMenu.add(loadFromFilesAction);
		scanMenu.add(exportallAction);
		scanMenu.add(exportSelectionAction);
		scanMenu.addSeparator(); // 사이선
		scanMenu.add(quitAction); //scan 붙이기
		
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
		gotoMenu.addSeparator(); //사이선
		gotoMenu.add(previousalivehostAction);
		gotoMenu.add(previousopenpostAction);
		gotoMenu.add(previousdeadhostAction);
		gotoMenu.addSeparator(); //사이선
		gotoMenu.add(findAction); //goto 붙이기
		
		JMenuItem showdetailsAction = new JMenuItem("Show details");
		JMenuItem rescanIPAction = new JMenuItem("Rescan IP(s)");
		JMenuItem deleteIPAction = new JMenuItem("Delete IP(s)");
		JMenuItem copyIPAction = new JMenuItem("Copy IP");
		JMenuItem copydetalisAction = new JMenuItem("Copy details");
		JMenuItem openAction = new JMenuItem("Open");
		
		CommandsMenu.add(showdetailsAction);
		CommandsMenu.addSeparator(); //사이선
		CommandsMenu.add(rescanIPAction);
		CommandsMenu.add(deleteIPAction);
		CommandsMenu.addSeparator(); //사이선
		CommandsMenu.add(copyIPAction);
		CommandsMenu.add(copydetalisAction);
		CommandsMenu.addSeparator(); //사이선
		CommandsMenu.add(openAction); //commands 붙이기
		
		JMenuItem addcurrentAction = new JMenuItem("Add current...");
		JMenuItem managefavoritesAction = new JMenuItem("Manage favorites...");
		
		FavoritesMenu.add(addcurrentAction);
		FavoritesMenu.add(managefavoritesAction); //favorites 붙이기
		
		JMenuItem preferencesAction = new JMenuItem("Preferences...");
		JMenuItem fetchersAction = new JMenuItem("Fetchers...");
		JMenuItem selectionAction = new JMenuItem("Selection");
		JMenuItem scanstatisticsAction = new JMenuItem("Scan statistics");
		
		ToolsMenu.add(preferencesAction);
		ToolsMenu.add(fetchersAction);
		ToolsMenu.addSeparator(); //사이선
		ToolsMenu.add(selectionAction);
		ToolsMenu.add(scanstatisticsAction); //tools 붙이기
		
		JMenuItem gettingstartedAction = new JMenuItem("Getting Started");
		JMenuItem officialwebsiteAction = new JMenuItem("Official Website");
		JMenuItem faqAction = new JMenuItem("FAQ");
		JMenuItem reportanissueAction = new JMenuItem("Report an issue");
		JMenuItem pluginsAction = new JMenuItem("Plugins");
		JMenuItem commandlineusageAction = new JMenuItem("Command-line usage");
		JMenuItem checkfornewerversionAction = new JMenuItem("Check for newer version...");
		JMenuItem aboutAction = new JMenuItem("About");
		
		HelpMenu.add(gettingstartedAction);
		HelpMenu.addSeparator(); //사이선
		HelpMenu.add(officialwebsiteAction);
		HelpMenu.add(faqAction);
		HelpMenu.add(reportanissueAction);
		HelpMenu.add(pluginsAction);
		HelpMenu.addSeparator(); //사이선
		HelpMenu.add(commandlineusageAction);
		HelpMenu.addSeparator(); //사이선
		HelpMenu.add(checkfornewerversionAction);
		HelpMenu.addSeparator(); //사이선
		HelpMenu.add(aboutAction); //help 붙이기
		
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
		readyLabel.setPreferredSize(new Dimension(350,16)); // 사이즈를 늘려서 보여줌
		readyLabel.setBorder(new BevelBorder(BevelBorder.RAISED)); // 상태바 사이의 선
		JLabel displayLabel = new JLabel("Display: All");
		displayLabel.setPreferredSize(new Dimension(150,16)); // 사이즈를 늘려서 보여줌
		displayLabel.setBorder(new BevelBorder(BevelBorder.RAISED)); // 상태바 사이의 선
		JLabel threadLabel = new JLabel("Threads: 0");
		threadLabel.setPreferredSize(new Dimension(150,16)); // 사이즈를 늘려서 보여줌
		threadLabel.setBorder(new BevelBorder(BevelBorder.RAISED)); // 상태바 사이의 선
		statusPanel.add(readyLabel);
		statusPanel.add(displayLabel);
		statusPanel.add(threadLabel); //상태바 항목 붙이기
		
		//status bar end
		
		//table begin
		
		String[] title = new String[] {
				"IP", "Ping", "TTL", "Hostname", "Port"
		};
		
		Object[][] stats = initTable();
		
		JTable jTable = new JTable(stats, title);
		
		JScrollPane sp = new JScrollPane(jTable); //스크롤바 만들기
		add(sp, BorderLayout.CENTER); //스크롤바 붙이기
		
		//table end
		
		//tool bar begin
		
		Font myFont = new Font("Serif", Font.BOLD, 16); //안의 내용, 폰트 모양, 크기
		JToolBar toolbar1 = new JToolBar();
		toolbar1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JToolBar toolbar2 = new JToolBar();
		toolbar2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel rangeStartLabel= new JLabel("IP Range: "); //기분 툴바 만들기
		JTextField rangeStartTextField = new JTextField(10);
		JLabel rangeEndLabel= new JLabel("to"); 
		JTextField rangeEndTextField = new JTextField(10);
		JComboBox IPRangeComboBox = new JComboBox();
		IPRangeComboBox.addItem("IP Range"); //IP 고정주소가 C급
		IPRangeComboBox.addItem("Random");
		IPRangeComboBox.addItem("Text File");
		JButton settingButton = new JButton();
		settingButton.setIcon(new ImageIcon("D:\\setting.png"));
		
		rangeStartLabel.setFont(myFont); // 폰트 크기 설정 
		rangeStartLabel.setPreferredSize(new Dimension(90, 30));
		rangeEndLabel.setFont(myFont); // 폰트 크기 설정 
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
		JButton upButton = new JButton("↑IP");
		JComboBox optionComboBox = new JComboBox();
		optionComboBox.addItem("/24"); //IP 고정주소가 C급
		optionComboBox.addItem("/26");
		optionComboBox.addItem("/16");
		optionComboBox.addItem("255...192");
		optionComboBox.addItem("255...128");
		optionComboBox.addItem("255...0");
		optionComboBox.addItem("255...0.0");
		optionComboBox.addItem("255...0.0.0");
		JButton startButton = new JButton("▶Start");
		JButton MenuButton = new JButton();
		MenuButton.setIcon(new ImageIcon("D:\\menuIM.png"));
		
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
		
		//호스트 이름과 RANGE에 넣기 begin
		String myIP = null;
		String myHostname = null;
		
		try {
			InetAddress ia = InetAddress.getLocalHost();
			
			myIP = ia.getHostAddress();
			myHostname = ia.getHostName();
		} catch (UnknownHostException e1) {
			
		}
		String fixedIP = myIP.substring(0, myIP.lastIndexOf(".") + 1);
		rangeStartTextField.setText(fixedIP+ "1" );
		rangeEndTextField.setText(fixedIP+ "254" );
		hostNameTextfield.setText(myHostname);
		
		//호스트 이름과 RANGE에 넣기 end
		
		setSize(700,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		//start button action begin
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Pinging[] pg = new Pinging[254];
				PortScanner[] ps = new PortScanner[254];
				
				for(int i=0; i<=253; i++) {
					pg[i] = new Pinging(fixedIP + (i+1));
					pg[i].start();
				}
				for(int i =0; i<=253; i++) {
					Object[] msg = pg[i].getMsg();
					stats[i][0] = msg[0];
					if(msg[1] != null) {
						stats[i][1] = msg[1];
					}else {
						stats[i][1] = "[n/a]";
					}
					if(msg[1] != null) {
						stats[i][2] = msg[2];
					}else {
						stats[i][2] = "[n/s]";
					}
					if(msg[1] != null) {
						stats[i][3] = msg[3];
					}else {
						stats[i][3] = "[n/s]";
					}
				}
				
				//msg[1] != null || msg[2] != null || msg[3] != null
				//portscan
				//scan value == null -> stats[i][4] = "[n/s]"
				//scan value != null -> assgin value stats[i][4]
				jTable.repaint();
			}
		});
		//start button action end	
	}
	public Object[][] initTable(){ 
		// 테이블 크기
		Object[][] result = new Object[254][5];
		return result;
	}
	
	public static void main(String[] args) {
		Internet op = new Internet();
	}

}
