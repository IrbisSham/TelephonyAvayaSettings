package com.it_zabota.jira.telephony.settings.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.it_zabota.jira.telephony.encryptng.SecurityFile;

public class FrmSettings extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -296257162753241722L;
	
	private static String propPath ; //= new File("telephony.properties").getAbsolutePath();
	private static String propPathEnc = new File("telephony.dat").getAbsolutePath();
	private static String FILE_ENCRIPT_KEY = "ROOT_789_WERT";
	
	private JPanel contentPane;
	private JTextField txtJiraHost;
	private JTextField txtJiraPort;
	private JTextField txtJiraUser;
	private JPasswordField txtJiraPass;
	private JTextField txtJiraUserGroups;
	private JTextField txtJiraFormIssueNumPar1;
	private JTextField txtJiraProjKey;
	private JTextField txtJiraFieldProject;
	private JTextField txtJiraIssueTypeDefault;
	private JTextField txtJiraIssueTypeList;
	private JTextField txtJiraPriorityDefault;
	private JTextField txtJiraFilterAbonentProjectKey;
	private JTextField txtJiraFormIssueIntervalPar2;
	private JTextField txtJiraFilterAbonentIssueType;
	private JTextField txtJiraFilterGlobalProjectKey;
	private JTextField txtJiraFilterGlobalIssueType;
	private JTextField txtJiraUserFieldEmail;
	private JTextField txtJiraUserFieldDisplayname;
	private JTextField txtJiraUserFieldLogin;
	private JTextField txtJiraIssueFieldId;
	private JTextField txtJiraIssueFieldName;
	private JTextField txtJiraIssueFieldKey;
	private JTextField txtJiraIssueFieldProject;
	private JTextField txtJiraIssueFieldReporter;
	private JTextField txtJiraIssueFieldAssignee;
	private JTextField txtJiraIssueFieldPriority;
	private JTextField txtJiraIssueFieldNo;
	private JTextField txtJiraIssueFieldTheme;
	private JTextField txtJiraIssueFieldDtUpd;
	private JTextField txtJiraIssueFieldStatus;
	private JTextField txtJiraIssueFieldDescription;
	private JTextField txtJiraIssueFieldLinkType;
	private JTextField txtJiraIssueFieldLinkTypeValue;
	private JTextField txtJiraIssueFieldLinkComment;
	private JTextField txtJiraIssueFieldLinkVisibleType;
	private JTextField txtJiraIssueFieldLinkVisibleValue;
	private JTextField txtLdapHost;
	private JTextField txtLdapPort;
	private JTextField txtLdapSearchbase;
	private JTextField txtLdapSearchfilter;
	private JTextField txtLdapUser;
	private JTextField txtAdFieldTelephone;
	private JTextField txtAdFieldMail;
	private JTextField txtAdFieldSurname;
	private JTextField txtAdFieldName;
	private JTextField txtAdFieldLogin;

	private JComboBox<String> cmbFieldSearchBase;

	private JComboBox<String> cmbFieldSearchBaseModification;

	private JComboBox<String> cmbLdapUse;

	private JComboBox<String> cmbSaveJiraIssuesImmediately;

	private JComboBox<String> cmbWorkFilesEncrypting;
	
	private JComboBox<String> cmbAppMode;
	

	private JComboBox<String> jiraCmbBoxEncod;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSettings frame = new FrmSettings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static final Properties prop = new Properties();
	private JPasswordField txtLdapPass;

	private File tempFile;
	private JTextField txtASSIGNEE_FIO;
	private JTextField txtJIRA_ISSUE_FIELD_TEL;
	
	private void loadProp() {
		try {
			FileInputStream fis = new FileInputStream(propPathEnc);
			FileOutputStream fos = new FileOutputStream(propPath);    		
 		    SecurityFile.decrypt(FILE_ENCRIPT_KEY, fis, fos);			
			
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(propPath), StandardCharsets.UTF_8));
			prop.load(in);
			in.close();
			fis.close();
			fos.close();
			new File(propPath).delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void saveProp() {
		try {
			// set the properties value
			setProp();			
			prop.store(new OutputStreamWriter(new FileOutputStream(propPath), "UTF-8"), "Edited ".concat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
				FileInputStream fis = new FileInputStream(propPath);
				FileOutputStream fos = new FileOutputStream(propPathEnc);
				SecurityFile.encrypt(FILE_ENCRIPT_KEY, fis, fos);
				fis.close();
				fos.close();		
				System.out.println(new File(propPath).getAbsolutePath());
				//new File(propPath).delete();				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@SuppressWarnings("deprecation")
	private void setProp() {
		prop.setProperty("FIELD_SEARCH_BASE", cmbFieldSearchBase.getSelectedItem().toString());
		prop.setProperty("FIELD_SEARCH_BASE_MODIFICATION", cmbFieldSearchBaseModification.getSelectedItem().toString());
		prop.setProperty("LDAP_USE", cmbLdapUse.getSelectedItem().toString());
		prop.setProperty("SAVE_JIRA_ISSUES_IMMEDIATELY", cmbSaveJiraIssuesImmediately.getSelectedItem().toString());
		prop.setProperty("WORK_FILES_ENCRYPTING", cmbWorkFilesEncrypting.getSelectedItem().toString());
		
		prop.setProperty("APP_MODE", cmbAppMode.getSelectedItem().toString());
		
		
		prop.setProperty("JIRA_HOST", txtJiraHost.getText());
		prop.setProperty("JIRA_PORT", txtJiraPort.getText());
		prop.setProperty("JIRA_USERNAME", txtJiraUser.getText());
		prop.setProperty("JIRA_PASSWORD", txtJiraPass.getText()); //  .getPassword().toString()
		prop.setProperty("JIRA_ENCODING", jiraCmbBoxEncod.getSelectedItem().toString());				
		prop.setProperty("JIRA_USER_GROUPS", txtJiraUserGroups.getText());
		
		prop.setProperty("JIRA_PROJECT_KEY", txtJiraProjKey.getText());
		prop.setProperty("JIRA_ISSUE_TYPE_DEFAULT", txtJiraIssueTypeDefault.getText());
		prop.setProperty("JIRA_ISSUE_TYPE_LIST", txtJiraIssueTypeList.getText());
		prop.setProperty("JIRA_PRIORITY_DEFAULT", txtJiraPriorityDefault.getText());
		prop.setProperty("FORM_ISSUE_NUM_PAR1", txtJiraFormIssueNumPar1.getText());
		prop.setProperty("FORM_ISSUE_INTERVAL_PAR2", txtJiraFormIssueIntervalPar2.getText());
		prop.setProperty("JIRA_FILTER_ABONENT_PROJECT_KEY", txtJiraFilterAbonentProjectKey.getText());
		prop.setProperty("JIRA_FILTER_ABONENT_ISSUE_TYPE", txtJiraFilterAbonentIssueType.getText());
		prop.setProperty("JIRA_FILTER_GLOBAL_PROJECT_KEY", txtJiraFilterGlobalProjectKey.getText());
		prop.setProperty("JIRA_FILTER_GLOBAL_ISSUE_TYPE", txtJiraFilterGlobalIssueType.getText());
		prop.setProperty("JIRA_FIELD_PROJECT", txtJiraFieldProject.getText());
		prop.setProperty("JIRA_ISSUE_FIELD_TEL", txtJIRA_ISSUE_FIELD_TEL.getText());
		
		prop.setProperty("JIRA_USER_FIELD_EMAIL", txtJiraUserFieldEmail.getText());
		prop.setProperty("JIRA_USER_FIELD_FULLNAME", txtJiraUserFieldDisplayname.getText());
		prop.setProperty("JIRA_USER_FIELD_LOGIN", txtJiraUserFieldLogin.getText());
		prop.setProperty("JIRA_ISSUE_FIELD_ID", txtJiraIssueFieldId.getText());
		prop.setProperty("JIRA_ISSUE_FIELD_NAME", txtJiraIssueFieldName.getText());
		prop.setProperty("JIRA_ISSUE_FIELD_KEY", txtJiraIssueFieldKey.getText());
		prop.setProperty("JIRA_ISSUE_FIELD_PROJECT", txtJiraIssueFieldProject.getText());
		prop.setProperty("JIRA_ISSUE_FIELD_REPORTER", txtJiraIssueFieldReporter.getText());
		prop.setProperty("JIRA_ISSUE_FIELD_ASSIGNEE", txtJiraIssueFieldAssignee.getText());
		prop.setProperty("ASSIGNEE_FIO", txtASSIGNEE_FIO.getText());
		prop.setProperty("JIRA_ISSUE_FIELD_PRIORITY", txtJiraIssueFieldPriority.getText());
		prop.setProperty("JIRA_ISSUE_FIELD_NO", txtJiraIssueFieldNo.getText());
		prop.setProperty("JIRA_ISSUE_FIELD_THEME", txtJiraIssueFieldTheme.getText());
		prop.setProperty("JIRA_ISSUE_FIELD_DT", txtJiraIssueFieldDtUpd.getText());
		prop.setProperty("JIRA_ISSUE_FIELD_STS", txtJiraIssueFieldStatus.getText());
		prop.setProperty("JIRA_ISSUE_FIELD_DESC", txtJiraIssueFieldDescription.getText());
		prop.setProperty("JIRA_ISSUE_FIELD_LINK_TYPE", txtJiraIssueFieldLinkType.getText());
		prop.setProperty("JIRA_ISSUE_FIELD_LINK_TYPE_VALUE", txtJiraIssueFieldLinkTypeValue.getText());
		prop.setProperty("JIRA_ISSUE_LINK_COMMENT", txtJiraIssueFieldLinkComment.getText());
		prop.setProperty("JIRA_ISSUE_LINK_VISIBILITY_TYPE", txtJiraIssueFieldLinkVisibleType.getText());
		prop.setProperty("JIRA_ISSUE_LINK_VISIBILITY_VALUE", txtJiraIssueFieldLinkVisibleValue.getText());
		
		prop.setProperty("LDAP_HOST", txtLdapHost.getText());
		prop.setProperty("LDAP_PORT", txtLdapPort.getText());
		prop.setProperty("LDAP_SEARCHBASE", txtLdapSearchbase.getText());
		prop.setProperty("LDAP_SEARCHFILTER", txtLdapSearchfilter.getText());
		prop.setProperty("LDAP_USERNAME", txtLdapUser.getText());
		prop.setProperty("LDAP_PASSWORD", txtLdapPass.getText()); // .getPassword().toString()
		prop.setProperty("AD_FIELD_TELEPHONE", txtAdFieldTelephone.getText());
		prop.setProperty("AD_FIELD_EMAIL", txtAdFieldMail.getText());
		prop.setProperty("AD_FIELD_SURNAME", txtAdFieldSurname.getText());
		prop.setProperty("AD_FIELD_NAME", txtAdFieldName.getText());
		prop.setProperty("AD_FIELD_LOGIN", txtAdFieldLogin.getText());
		 
	}
	
	
	private void closeFrame() {
		Object[] options = {"Сохранить изменения",
                "Не сохранять изменения"};
		int n = JOptionPane.showOptionDialog(rootPane,
			    "Сохранить изменения?",
			    "Действия при выходе",
			    JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[0]);
		if (n != JOptionPane.CANCEL_OPTION) 
		{
			if (n == JOptionPane.YES_OPTION) {
				saveProp();			
			}
			System.exit(0);
		}

	}
	
	/**
	 * Инициализация слушателей событий элементов формы
	 */		
	private void initActions() {
   	
        	addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent winEvt) {
                    closeFrame();
                }
            });
    }

	/**
	 * Create the frame.
	 */
	public FrmSettings() {
		setMaximumSize(new Dimension(2000, 2000));
		setMinimumSize(new Dimension(200, 200));
		InputStream is;
		try {
			is = FrmSettings.class.getResourceAsStream("/resources/logo.png");
			Image image = ImageIO.read(is);
			this.setIconImage(image);			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}						
		try {
			tempFile = File.createTempFile("prop", ".tmp");
			propPath = tempFile.getAbsolutePath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		loadProp();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		initActions();
		
		setFont(new Font("Arial", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 1, 0, 0));
		
		JTabbedPane mainPane = new JTabbedPane(JTabbedPane.TOP);
		mainPane.setFont(new Font("Arial", Font.BOLD, 12));
		contentPane.add(mainPane);
		
		JPanel appBasePnl = new JPanel();
		appBasePnl.setFont(new Font("Arial", Font.PLAIN, 12));
		mainPane.addTab("AppBase", null, appBasePnl, null);
		appBasePnl.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		JScrollPane appBaseScrPane = new JScrollPane();
		appBaseScrPane.setFont(new Font("Arial", Font.PLAIN, 12));
		appBasePnl.add(appBaseScrPane);
		
		JPanel panel = new JPanel();
		panel.setFont(new Font("Arial", Font.PLAIN, 12));
		appBaseScrPane.setViewportView(panel);
		panel.setLayout(new GridLayout(6, 2, 0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setFont(new Font("Arial", Font.PLAIN, 12));
		panel.add(splitPane);
		
		JLabel lblNewLabel = new JLabel("FIELD_SEARCH_BASE");
		lblNewLabel.setMaximumSize(new Dimension(140, 14));
		lblNewLabel.setPreferredSize(new Dimension(220, 14));
		lblNewLabel.setToolTipText("Поле данных пользователя для поиска при инициализации приложения");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane.setLeftComponent(lblNewLabel);
		
		cmbFieldSearchBase = new JComboBox<String>();
		cmbFieldSearchBase.setToolTipText("телефон в AD - AD_FIELD_TELEPHONE\r\nполе полного имени пользователя в Jira - JIRA_USER_FIELD_FULLNAME");
		cmbFieldSearchBase.setModel(new DefaultComboBoxModel<String>(new String[] {"AD_FIELD_TELEPHONE", "JIRA_USER_FIELD_FULLNAME"}));
		cmbFieldSearchBase.setSelectedIndex(0);
		String var = prop.getProperty("FIELD_SEARCH_BASE");
		switch (var) {
		case "AD_FIELD_TELEPHONE":
			cmbFieldSearchBase.setSelectedIndex(0);
			break;
		case "JIRA_USER_FIELD_FULLNAME":
			cmbFieldSearchBase.setSelectedIndex(1);
			break;			
		default:
			break;
		}
		
		cmbFieldSearchBase.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane.setRightComponent(cmbFieldSearchBase);
		
		JSplitPane splitPane_1 = new JSplitPane();
		panel.add(splitPane_1);
		
		JLabel lblNewLabel_1 = new JLabel("FIELD_SEARCH_BASE_MODIFICATION");
		lblNewLabel_1.setMaximumSize(new Dimension(220, 14));
		lblNewLabel_1.setPreferredSize(new Dimension(220, 14));
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setToolTipText("Поле данных пользователя для поиска при инициализации приложения. Вид модификации входного параметра.");
		splitPane_1.setLeftComponent(lblNewLabel_1);
		
		cmbFieldSearchBaseModification = new JComboBox<String>();
		cmbFieldSearchBaseModification.setToolTipText("ФАМ_ЗАПЯТАЯ_ИМЯ - преобразование, при котором для параметра из 2х слов 1е слово разделяется со 2м запятой (1 слово и больше 2 слов - без изменений)\r\nНЕТ - без модификаций");
		cmbFieldSearchBaseModification.setModel(new DefaultComboBoxModel<String>(new String[] {"ФАМ_ЗАПЯТАЯ_ИМЯ", "НЕТ"}));
		var = prop.getProperty("FIELD_SEARCH_BASE_MODIFICATION");
		switch (var) {
		case "ФАМ_ЗАПЯТАЯ_ИМЯ":
			cmbFieldSearchBaseModification.setSelectedIndex(0);
			break;
		case "НЕТ":
			cmbFieldSearchBaseModification.setSelectedIndex(1);
			break;			
		default:
			break;
		}
		cmbFieldSearchBaseModification.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_1.setRightComponent(cmbFieldSearchBaseModification);
		
		JSplitPane splitPane_2 = new JSplitPane();
		panel.add(splitPane_2);
		
		cmbLdapUse = new JComboBox<String>();
		cmbLdapUse.setModel(new DefaultComboBoxModel<String>(new String[] {"ДА", "НЕТ"}));
		var = prop.getProperty("LDAP_USE");
		switch (var) {
		case "ДА":
			cmbLdapUse.setSelectedIndex(0);
			break;
		case "НЕТ":
			cmbLdapUse.setSelectedIndex(1);
			break;			
		default:
			break;
		}
		cmbLdapUse.setFont(new Font("Arial", Font.PLAIN, 12));
		cmbLdapUse.setToolTipText("НЕТ - не использовать LDAP, пользователи берутся из базы Jira\r\nДА - использовать LDAP, пользователи берутся с сервера LDAP");
		splitPane_2.setRightComponent(cmbLdapUse);
		
		JLabel lblNewLabel_2 = new JLabel("LDAP_USE");
		lblNewLabel_2.setPreferredSize(new Dimension(220, 14));
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2.setToolTipText("LDAP. Использовать LDAP?");
		splitPane_2.setLeftComponent(lblNewLabel_2);
		
		JSplitPane splitPane_3 = new JSplitPane();
		panel.add(splitPane_3);
		
		JLabel lblNewLabel_3 = new JLabel("SAVE_JIRA_ISSUES_IMMEDIATELY");
		lblNewLabel_3.setPreferredSize(new Dimension(220, 14));
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_3.setToolTipText("Создавать ли заявки в Jira перед открытием окна редактирования. Иначе открывается форма сохранения заявки в Jira");
		splitPane_3.setLeftComponent(lblNewLabel_3);
		
		cmbSaveJiraIssuesImmediately = new JComboBox<String>();
		cmbSaveJiraIssuesImmediately.setModel(new DefaultComboBoxModel<String>(new String[] {"ДА", "НЕТ"}));
		var = prop.getProperty("SAVE_JIRA_ISSUES_IMMEDIATELY");
		switch (var) {
		case "ДА":
			cmbSaveJiraIssuesImmediately.setSelectedIndex(0);
			break;
		case "НЕТ":
			cmbSaveJiraIssuesImmediately.setSelectedIndex(1);
			break;			
		default:
			break;
		}
		cmbSaveJiraIssuesImmediately.setFont(new Font("Arial", Font.PLAIN, 12));
		cmbSaveJiraIssuesImmediately.setToolTipText("ДА - сохранять заявки в Jira перед открытием окна редактирования\r\nНЕТ - не сохранять заявки в Jira перед открытием окна редактирования");
		splitPane_3.setRightComponent(cmbSaveJiraIssuesImmediately);
		
		JSplitPane splitPane_4 = new JSplitPane();
		panel.add(splitPane_4);
		
		JLabel lblNewLabel_4 = new JLabel("WORK_FILES_ENCRYPTING");
		lblNewLabel_4.setToolTipText("Шифровать ли рабочие файлы?");
		lblNewLabel_4.setPreferredSize(new Dimension(220, 14));
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_4.setLeftComponent(lblNewLabel_4);
		
		cmbWorkFilesEncrypting = new JComboBox<String>();
		cmbWorkFilesEncrypting.setToolTipText("ДА - шифровать\r\nНЕТ - не шифровать");
		cmbWorkFilesEncrypting.setModel(new DefaultComboBoxModel<String>(new String[] {"ДА", "НЕТ"}));
		cmbWorkFilesEncrypting.setFont(new Font("Arial", Font.PLAIN, 12));
		var = prop.getProperty("WORK_FILES_ENCRYPTING");
		switch (var) {
		case "ДА":
			cmbWorkFilesEncrypting.setSelectedIndex(0);
			break;
		case "НЕТ":
			cmbWorkFilesEncrypting.setSelectedIndex(1);
			break;			
		default:
			break;
		}
		splitPane_4.setRightComponent(cmbWorkFilesEncrypting);
		
		JSplitPane splitPane_52 = new JSplitPane();
		panel.add(splitPane_52);
		
		JLabel lblAppmode = new JLabel("APP_MODE");
		lblAppmode.setToolTipText("Режим работы приложения");
		lblAppmode.setPreferredSize(new Dimension(220, 14));
		lblAppmode.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_52.setLeftComponent(lblAppmode);
		
		cmbAppMode = new JComboBox<String>();
		cmbAppMode.setModel(new DefaultComboBoxModel(new String[] {"GUI", "CONSOLE"}));
		cmbAppMode.setToolTipText("GUI - режим графического интерфейса.  CONSOLE - моментальное создание заявки с заполненением поля номера телефона");
		cmbAppMode.setFont(new Font("Arial", Font.PLAIN, 12));
		var = prop.getProperty("APP_MODE");
		if (var != null && !var.isEmpty()) {
			switch (var) {
			case "GUI":
				cmbAppMode.setSelectedIndex(0);
				break;
			case "CONSOLE":
				cmbAppMode.setSelectedIndex(1);
				break;			
			default:
				break;
			}					
		}
		splitPane_52.setRightComponent(cmbAppMode);
		
		
		JPanel jiraConPnl = new JPanel();
		jiraConPnl.setFont(new Font("Arial", Font.PLAIN, 12));
		mainPane.addTab("JiraCon", null, jiraConPnl, null);
		
		JScrollPane jiraConScrPane = new JScrollPane();
		jiraConScrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel panel_1 = new JPanel();
		panel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		jiraConScrPane.setViewportView(panel_1);
		panel_1.setLayout(new GridLayout(6, 2, 0, 0));
		
		JSplitPane splitPane_5 = new JSplitPane();
		panel_1.add(splitPane_5);
		
		JLabel lblNewLabel_5 = new JLabel("JIRA_HOST");
		lblNewLabel_5.setToolTipText("Подключение Jira. Хост");
		lblNewLabel_5.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_5.setLeftComponent(lblNewLabel_5);
		
		txtJiraHost = new JTextField(); 
		txtJiraHost.setToolTipText("{jcn");
		txtJiraHost.setText(prop.getProperty("JIRA_HOST"));
		txtJiraHost.setFont(new Font("Arial", Font.PLAIN, 12));
		txtJiraHost.setMaximumSize(new Dimension(100, 20));
		txtJiraHost.setPreferredSize(new Dimension(220, 20));
		splitPane_5.setRightComponent(txtJiraHost);
		txtJiraHost.setColumns(10);
		
		JSplitPane splitPane_6 = new JSplitPane();
		panel_1.add(splitPane_6);
		
		JLabel lblNewLabel_6 = new JLabel("JIRA_PORT");
		lblNewLabel_6.setToolTipText("Подключение Jira. Порт");
		lblNewLabel_6.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_6.setLeftComponent(lblNewLabel_6);
		
		txtJiraPort = new JTextField();
		txtJiraPort.setText(prop.getProperty("JIRA_PORT"));
		txtJiraPort.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_6.setRightComponent(txtJiraPort);
		txtJiraPort.setColumns(10);
		
		JSplitPane splitPane_7 = new JSplitPane();
		panel_1.add(splitPane_7);
		
		JLabel lblNewLabel_7 = new JLabel("JIRA_USERNAME");
		lblNewLabel_7.setToolTipText("Подключение Jira. Логин пользователя");
		lblNewLabel_7.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_7.setLeftComponent(lblNewLabel_7);
		
		txtJiraUser = new JTextField();
		txtJiraUser.setText(prop.getProperty("JIRA_USERNAME"));
		txtJiraUser.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_7.setRightComponent(txtJiraUser);
		txtJiraUser.setColumns(10);
		
		JSplitPane splitPane_8 = new JSplitPane();
		panel_1.add(splitPane_8);
		
		JLabel lblNewLabel_8 = new JLabel("JIRA_PASSWORD");
		lblNewLabel_8.setToolTipText("Подключение Jira. Пароль пользователя");
		lblNewLabel_8.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_8.setLeftComponent(lblNewLabel_8);
		
		txtJiraPass = new JPasswordField();
		txtJiraPass.setText(prop.getProperty("JIRA_PASSWORD"));
		txtJiraPass.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_8.setRightComponent(txtJiraPass);
		
		
		JSplitPane splitPane_9 = new JSplitPane();
		panel_1.add(splitPane_9);
		
		JLabel lblNewLabel_9 = new JLabel("JIRA_ENCODING");
		lblNewLabel_9.setToolTipText("Кодировка Jira");
		lblNewLabel_9.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_9.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_9.setLeftComponent(lblNewLabel_9);
		
		jiraCmbBoxEncod = new JComboBox<String>();
		jiraCmbBoxEncod.setFont(new Font("Arial", Font.PLAIN, 12));
		jiraCmbBoxEncod.setModel(new DefaultComboBoxModel<String>(new String[] {"UTF-8", "ANSI"}));
		splitPane_9.setRightComponent(jiraCmbBoxEncod);
		
				JSplitPane splitPane_81 = new JSplitPane();
				panel_1.add(splitPane_81);
				
				JLabel lblNewLabel_81 = new JLabel("JIRA_USER_GROUPS");
				lblNewLabel_81.setToolTipText("Список групп для поиска пользователей в базе Jira, через \",\"");
				lblNewLabel_81.setPreferredSize(new Dimension(230, 14));
				lblNewLabel_81.setFont(new Font("Arial", Font.PLAIN, 12));
				splitPane_81.setLeftComponent(lblNewLabel_81);
				
				txtJiraUserGroups = new JTextField();
				txtJiraUserGroups.setFont(new Font("Arial", Font.PLAIN, 12));
				txtJiraUserGroups.setText(prop.getProperty("JIRA_USER_GROUPS"));
				splitPane_81.setRightComponent(txtJiraUserGroups);
				txtJiraUserGroups.setColumns(10);
		var = prop.getProperty("WORK_FILES_ENCRYPTING");
		switch (var) {
		case "UTF-8":
			jiraCmbBoxEncod.setSelectedIndex(0);
			break;
		case "ANSI":
			jiraCmbBoxEncod.setSelectedIndex(1);
			break;			
		default:
			break;
		}
		jiraConPnl.setLayout(new GridLayout(0, 1, 0, 0));
		jiraConPnl.add(jiraConScrPane);
		
		
		JPanel jiraFldPnl = new JPanel();
		mainPane.addTab("JiraFld", null, jiraFldPnl, null);
		jiraFldPnl.setLayout(new GridLayout(1, 0, 0, 0));
		JScrollPane jiraFldScrPane = new JScrollPane();
		jiraFldScrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jiraFldPnl.add(jiraFldScrPane);
		
		JPanel panel_2 = new JPanel();
		jiraFldScrPane.setViewportView(panel_2);
		panel_2.setLayout(new GridLayout(33, 2, 0, 0));
		
		JSplitPane splitPane_20 = new JSplitPane();
		panel_2.add(splitPane_20);
		
		JLabel lblNewLabel_19 = new JLabel("JIRA_PROJECT_KEY");
		lblNewLabel_19.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_19.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_19.setToolTipText("Наименование ключа проекта Jira");
		splitPane_20.setLeftComponent(lblNewLabel_19);
		
		txtJiraProjKey = new JTextField();
		txtJiraProjKey.setText(prop.getProperty("JIRA_PROJECT_KEY"));
		txtJiraProjKey.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_20.setRightComponent(txtJiraProjKey);
		txtJiraProjKey.setColumns(10);
		
		JSplitPane splitPane_22 = new JSplitPane();
		panel_2.add(splitPane_22);
		
		JLabel lblNewLabel_10 = new JLabel("JIRA_ISSUE_TYPE_DEFAULT");
		lblNewLabel_10.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_10.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_10.setToolTipText("Тип заявки по-умолчанию");
		splitPane_22.setLeftComponent(lblNewLabel_10);
		
		txtJiraIssueTypeDefault = new JTextField();
		txtJiraIssueTypeDefault.setText(prop.getProperty("JIRA_ISSUE_TYPE_DEFAULT"));
		txtJiraIssueTypeDefault.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_22.setRightComponent(txtJiraIssueTypeDefault);
		txtJiraIssueTypeDefault.setColumns(10);
		
		JSplitPane splitPane_23 = new JSplitPane();
		panel_2.add(splitPane_23);
		
		JLabel lblNewLabel_11 = new JLabel("JIRA_ISSUE_TYPE_LIST");
		lblNewLabel_11.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_11.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_11.setToolTipText("Список наименований типов заявок, через \",\"");
		splitPane_23.setLeftComponent(lblNewLabel_11);
		
		txtJiraIssueTypeList = new JTextField();
		txtJiraIssueTypeList.setText(prop.getProperty("JIRA_ISSUE_TYPE_LIST"));
		txtJiraIssueTypeList.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_23.setRightComponent(txtJiraIssueTypeList);
		txtJiraIssueTypeList.setColumns(10);
		
		JSplitPane splitPane_24 = new JSplitPane();
		panel_2.add(splitPane_24);
		
		JLabel lblNewLabel_12 = new JLabel("JIRA_PRIORITY_DEFAULT");
		lblNewLabel_12.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_12.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_12.setToolTipText("Наименование приоритета по-умолчанию");
		splitPane_24.setLeftComponent(lblNewLabel_12);
		
		txtJiraPriorityDefault = new JTextField();
		txtJiraPriorityDefault.setText(prop.getProperty("JIRA_PRIORITY_DEFAULT"));
		txtJiraPriorityDefault.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_24.setRightComponent(txtJiraPriorityDefault);
		txtJiraPriorityDefault.setColumns(10);
		
		JSplitPane splitPane_25 = new JSplitPane();
		panel_2.add(splitPane_25);
		
		JLabel lblNewLabel_13 = new JLabel("FORM_ISSUE_NUM_PAR1");
		lblNewLabel_13.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_13.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_13.setToolTipText("Максимальное количество сообщений для показа на экране при выборе фильтра обращений \"Обращения абонента\"");
		splitPane_25.setLeftComponent(lblNewLabel_13);
		
		txtJiraFormIssueNumPar1 = new JTextField();
		txtJiraFormIssueNumPar1.setText(prop.getProperty("FORM_ISSUE_NUM_PAR1"));
		txtJiraFormIssueNumPar1.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_25.setRightComponent(txtJiraFormIssueNumPar1);
		txtJiraFormIssueNumPar1.setColumns(10);
		
		JSplitPane splitPane_26 = new JSplitPane();
		panel_2.add(splitPane_26);
		
		JLabel lblNewLabel_14 = new JLabel("FORM_ISSUE_INTERVAL_PAR2");
		lblNewLabel_14.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_14.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_14.setToolTipText("Выражение для фильтрации даты, меньшей, либо равной FORM_ISSUE_INTERVAL_PAR2 (выражение: JIRA_ISSUE_FIELD_DT <= FORM_ISSUE_INTERVAL_PAR2) обращений \"Глобальные обращения\"");
		splitPane_26.setLeftComponent(lblNewLabel_14);
		
		txtJiraFormIssueIntervalPar2 = new JTextField();
		txtJiraFormIssueIntervalPar2.setText(prop.getProperty("FORM_ISSUE_INTERVAL_PAR2"));
		txtJiraFormIssueIntervalPar2.setFont(new Font("Arial", Font.PLAIN, 12));
		txtJiraFormIssueIntervalPar2.setToolTipText("Выражение фильтра в формате JQL");
		splitPane_26.setRightComponent(txtJiraFormIssueIntervalPar2);
		txtJiraFormIssueIntervalPar2.setColumns(10);
		
		JSplitPane splitPane_27 = new JSplitPane();
		panel_2.add(splitPane_27);
		
		JLabel lblNewLabel_15 = new JLabel("JIRA_FILTER_ABONENT_PROJECT_KEY");
		lblNewLabel_15.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_15.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_15.setToolTipText("Фильтр обращений абонента по ключам проектов");
		splitPane_27.setLeftComponent(lblNewLabel_15);
		
		txtJiraFilterAbonentProjectKey = new JTextField();
		txtJiraFilterAbonentProjectKey.setText(prop.getProperty("JIRA_FILTER_ABONENT_PROJECT_KEY"));
		txtJiraFilterAbonentProjectKey.setFont(new Font("Arial", Font.PLAIN, 12));
		txtJiraFilterAbonentProjectKey.setToolTipText("Возможные значения: ALL - если выборку в фильтре нужно осуществлять по всем проектам, либо ключи проектов через запятую - если нужно осущетвлять выборку по определенным проектам");
		splitPane_27.setRightComponent(txtJiraFilterAbonentProjectKey);
		txtJiraFilterAbonentProjectKey.setColumns(10);
		
		JSplitPane splitPane_28 = new JSplitPane();
		panel_2.add(splitPane_28);
		
		JLabel lblNewLabel_16 = new JLabel("JIRA_FILTER_ABONENT_ISSUE_TYPE");
		lblNewLabel_16.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_16.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_16.setToolTipText("Фильтр обращений абонента по типам заявок");
		splitPane_28.setLeftComponent(lblNewLabel_16);
		
		txtJiraFilterAbonentIssueType = new JTextField();
		txtJiraFilterAbonentIssueType.setText(prop.getProperty("JIRA_FILTER_ABONENT_ISSUE_TYPE"));
		txtJiraFilterAbonentIssueType.setFont(new Font("Arial", Font.PLAIN, 12));
		txtJiraFilterAbonentIssueType.setToolTipText("Возможные значения: ALL - если выборку в фильтре нужно осуществлять по всем типам заявок, либо типы заявок через запятую - если нужно осущетвлять выборку по определенным типам заявок");
		splitPane_28.setRightComponent(txtJiraFilterAbonentIssueType);
		txtJiraFilterAbonentIssueType.setColumns(10);
		
		JSplitPane splitPane_29 = new JSplitPane();
		panel_2.add(splitPane_29);
		
		JLabel lblNewLabel_17 = new JLabel("JIRA_FILTER_GLOBAL_PROJECT_KEY");
		lblNewLabel_17.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_17.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_17.setToolTipText("Фильтр глобальных обращений по ключам проектов");
		splitPane_29.setLeftComponent(lblNewLabel_17);
		
		txtJiraFilterGlobalProjectKey = new JTextField();
		txtJiraFilterGlobalProjectKey.setText(prop.getProperty("JIRA_FILTER_GLOBAL_PROJECT_KEY"));
		txtJiraFilterGlobalProjectKey.setFont(new Font("Arial", Font.PLAIN, 12));		
		splitPane_29.setRightComponent(txtJiraFilterGlobalProjectKey);
		txtJiraFilterGlobalProjectKey.setColumns(10);
		
		JSplitPane splitPane_30 = new JSplitPane();
		panel_2.add(splitPane_30);
		
		JLabel lblNewLabel_18 = new JLabel("JIRA_FILTER_GLOBAL_ISSUE_TYPE");
		lblNewLabel_18.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_18.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_18.setToolTipText("Фильтр глобальных обращений по типам заявок");
		splitPane_30.setLeftComponent(lblNewLabel_18);
		
		txtJiraFilterGlobalIssueType = new JTextField();
		txtJiraFilterGlobalIssueType.setText(prop.getProperty("JIRA_FILTER_GLOBAL_ISSUE_TYPE"));
		txtJiraFilterGlobalIssueType.setFont(new Font("Arial", Font.PLAIN, 12));		
		splitPane_30.setRightComponent(txtJiraFilterGlobalIssueType);
		txtJiraFilterGlobalIssueType.setColumns(10);
		
		JSplitPane splitPane_21 = new JSplitPane();
		panel_2.add(splitPane_21);
		
		JLabel lblJirafieldproject = new JLabel("JIRA_FIELD_PROJECT");
		lblJirafieldproject.setPreferredSize(new Dimension(230, 14));
		lblJirafieldproject.setFont(new Font("Arial", Font.PLAIN, 12));
		lblJirafieldproject.setToolTipText("Поле Jira. Наименование поля \"Проект\"");
		splitPane_21.setLeftComponent(lblJirafieldproject);
		
		txtJiraFieldProject = new JTextField();
		txtJiraFieldProject.setText(prop.getProperty("JIRA_FIELD_PROJECT"));
		txtJiraFieldProject.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_21.setRightComponent(txtJiraFieldProject);
		txtJiraFieldProject.setColumns(10);
		
		JSplitPane splitPane_31 = new JSplitPane();
		panel_2.add(splitPane_31);
		
		JLabel lblNewLabel_20 = new JLabel("JIRA_USER_FIELD_EMAIL");
		lblNewLabel_20.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_20.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_20.setToolTipText("Наименование поля пользователя. Электронный почтовый адрес");
		splitPane_31.setLeftComponent(lblNewLabel_20);
		
		txtJiraUserFieldEmail = new JTextField();
		txtJiraUserFieldEmail.setText(prop.getProperty("JIRA_USER_FIELD_EMAIL"));
		txtJiraUserFieldEmail.setFont(new Font("Arial", Font.PLAIN, 12));		
		splitPane_31.setRightComponent(txtJiraUserFieldEmail);
		txtJiraUserFieldEmail.setColumns(10);
		
		JSplitPane splitPane_32 = new JSplitPane();
		panel_2.add(splitPane_32);
		
		JLabel lblNewLabel_21 = new JLabel("JIRA_USER_FIELD_FULLNAME");
		lblNewLabel_21.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_21.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_21.setToolTipText("Наименование поля пользователя. Полное имя");
		splitPane_32.setLeftComponent(lblNewLabel_21);
		
		txtJiraUserFieldDisplayname = new JTextField();
		txtJiraUserFieldDisplayname.setText(prop.getProperty("JIRA_USER_FIELD_FULLNAME"));
		txtJiraUserFieldDisplayname.setFont(new Font("Arial", Font.PLAIN, 12));		
		splitPane_32.setRightComponent(txtJiraUserFieldDisplayname);
		txtJiraUserFieldDisplayname.setColumns(10);
		
		JSplitPane splitPane_33 = new JSplitPane();
		panel_2.add(splitPane_33);
		
		txtJiraUserFieldLogin = new JTextField();
		txtJiraUserFieldLogin.setText(prop.getProperty("JIRA_USER_FIELD_LOGIN"));
		txtJiraUserFieldLogin.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_33.setRightComponent(txtJiraUserFieldLogin);
		txtJiraUserFieldLogin.setColumns(10);
		
		JLabel lblNewLabel_22 = new JLabel("JIRA_USER_FIELD_LOGIN");
		lblNewLabel_22.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_22.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_22.setToolTipText("Наименование поля пользователя. Логин");
		splitPane_33.setLeftComponent(lblNewLabel_22);
		
		JSplitPane splitPane_34 = new JSplitPane();
		panel_2.add(splitPane_34);
		
		txtJiraIssueFieldId = new JTextField();
		txtJiraIssueFieldId.setText(prop.getProperty("JIRA_ISSUE_FIELD_ID"));		
		txtJiraIssueFieldId.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_34.setRightComponent(txtJiraIssueFieldId);
		txtJiraIssueFieldId.setColumns(10);
		
		JLabel lblNewLabel_23 = new JLabel("JIRA_ISSUE_FIELD_ID");
		lblNewLabel_23.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_23.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_23.setToolTipText("Наименование поля заявок пользователя. ID");
		splitPane_34.setLeftComponent(lblNewLabel_23);
		
		JSplitPane splitPane_35 = new JSplitPane();
		panel_2.add(splitPane_35);
		
		txtJiraIssueFieldName = new JTextField();
		txtJiraIssueFieldName.setText(prop.getProperty("JIRA_ISSUE_FIELD_NAME"));
		txtJiraIssueFieldName.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_35.setRightComponent(txtJiraIssueFieldName);
		txtJiraIssueFieldName.setColumns(10);
		
		JLabel lblNewLabel_24 = new JLabel("JIRA_ISSUE_FIELD_NAME");
		lblNewLabel_24.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_24.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_24.setToolTipText("Наименование поля заявок пользователя. Название");
		splitPane_35.setLeftComponent(lblNewLabel_24);
		
		JSplitPane splitPane_19 = new JSplitPane();
		panel_2.add(splitPane_19);
		
		txtJiraIssueFieldKey = new JTextField();
		txtJiraIssueFieldKey.setText(prop.getProperty("JIRA_ISSUE_FIELD_KEY"));
		txtJiraIssueFieldKey.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_19.setRightComponent(txtJiraIssueFieldKey);
		txtJiraIssueFieldKey.setColumns(10);
		
		JLabel lblNewLabel_25 = new JLabel("JIRA_ISSUE_FIELD_KEY");
		lblNewLabel_25.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_25.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_25.setToolTipText("Наименование поля заявок пользователя. Ключ");
		splitPane_19.setLeftComponent(lblNewLabel_25);
		
		JSplitPane splitPane_36 = new JSplitPane();
		panel_2.add(splitPane_36);
		
		JLabel lblNewLabel_26 = new JLabel("JIRA_ISSUE_FIELD_PROJECT");
		lblNewLabel_26.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_26.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_26.setToolTipText("Наименование поля заявок пользователя. Проект");
		splitPane_36.setLeftComponent(lblNewLabel_26);
		
		txtJiraIssueFieldProject = new JTextField();
		txtJiraIssueFieldProject.setText(prop.getProperty("JIRA_ISSUE_FIELD_PROJECT"));
		txtJiraIssueFieldProject.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_36.setRightComponent(txtJiraIssueFieldProject);
		txtJiraIssueFieldProject.setColumns(10);
		
		JSplitPane splitPane_54 = new JSplitPane();
		panel_2.add(splitPane_54);
		
		JLabel lblJiraissuefieldtel = new JLabel("JIRA_ISSUE_FIELD_TEL");
		lblJiraissuefieldtel.setToolTipText("Наименование поля заявок пользователя. Телефон");
		lblJiraissuefieldtel.setPreferredSize(new Dimension(230, 14));
		lblJiraissuefieldtel.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_54.setLeftComponent(lblJiraissuefieldtel);
		
		txtJIRA_ISSUE_FIELD_TEL = new JTextField();
		txtJIRA_ISSUE_FIELD_TEL.setText(prop.getProperty("JIRA_ISSUE_FIELD_TEL"));
		txtJIRA_ISSUE_FIELD_TEL.setFont(new Font("Arial", Font.PLAIN, 12));
		txtJIRA_ISSUE_FIELD_TEL.setColumns(10);
		splitPane_54.setRightComponent(txtJIRA_ISSUE_FIELD_TEL);
		
		JSplitPane splitPane_37 = new JSplitPane();
		panel_2.add(splitPane_37);
		
		JLabel lblNewLabel_27 = new JLabel("JIRA_ISSUE_FIELD_REPORTER");
		lblNewLabel_27.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_27.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_27.setToolTipText("Наименование поля заявок пользователя. Автор");
		splitPane_37.setLeftComponent(lblNewLabel_27);
		
		txtJiraIssueFieldReporter = new JTextField();
		txtJiraIssueFieldReporter.setText(prop.getProperty("JIRA_ISSUE_FIELD_REPORTER"));
		txtJiraIssueFieldReporter.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_37.setRightComponent(txtJiraIssueFieldReporter);
		txtJiraIssueFieldReporter.setColumns(10);
		
		JSplitPane splitPane_38 = new JSplitPane();
		panel_2.add(splitPane_38);
		
		JLabel lblNewLabel_28 = new JLabel("JIRA_ISSUE_FIELD_ASSIGNEE");
		lblNewLabel_28.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_28.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_28.setToolTipText("Наименование поля заявок пользователя. Исполнитель");
		splitPane_38.setLeftComponent(lblNewLabel_28);
		
		txtJiraIssueFieldAssignee = new JTextField();
		txtJiraIssueFieldAssignee.setText(prop.getProperty("JIRA_ISSUE_FIELD_ASSIGNEE"));
		txtJiraIssueFieldAssignee.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_38.setRightComponent(txtJiraIssueFieldAssignee);
		txtJiraIssueFieldAssignee.setColumns(10);
		
		JSplitPane splitPane_53 = new JSplitPane();
		panel_2.add(splitPane_53);
		
		JLabel lblAssigneefio = new JLabel("ASSIGNEE_FIO");
		lblAssigneefio.setToolTipText("Наименование поля заявок пользователя. ФИО исполнителя");
		lblAssigneefio.setPreferredSize(new Dimension(230, 14));
		lblAssigneefio.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_53.setLeftComponent(lblAssigneefio);
		
		txtASSIGNEE_FIO = new JTextField();
		txtASSIGNEE_FIO.setText(prop.getProperty("ASSIGNEE_FIO"));
		txtASSIGNEE_FIO.setFont(new Font("Arial", Font.PLAIN, 12));
		txtASSIGNEE_FIO.setColumns(10);
		splitPane_53.setRightComponent(txtASSIGNEE_FIO);
		
		JSplitPane splitPane_10 = new JSplitPane();
		panel_2.add(splitPane_10);
		
		JLabel lblASSIGNEE_FIO = new JLabel("JIRA_ISSUE_FIELD_PRIORITY");
		lblASSIGNEE_FIO.setPreferredSize(new Dimension(230, 14));
		lblASSIGNEE_FIO.setFont(new Font("Arial", Font.PLAIN, 12));
		lblASSIGNEE_FIO.setToolTipText("Наименование поля заявок пользователя. Приоритет");
		splitPane_10.setLeftComponent(lblASSIGNEE_FIO);
		
		txtJiraIssueFieldPriority = new JTextField();
		txtJiraIssueFieldPriority.setText(prop.getProperty("JIRA_ISSUE_FIELD_PRIORITY"));
		txtJiraIssueFieldPriority.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_10.setRightComponent(txtJiraIssueFieldPriority);
		txtJiraIssueFieldPriority.setColumns(10);
		
		JSplitPane splitPane_11 = new JSplitPane();
		panel_2.add(splitPane_11);
		
		JLabel lblNewLabel_30 = new JLabel("JIRA_ISSUE_FIELD_NO");
		lblNewLabel_30.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_30.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_30.setToolTipText("Наименование поля заявок пользователя. Номер");
		splitPane_11.setLeftComponent(lblNewLabel_30);
		
		txtJiraIssueFieldNo = new JTextField();
		txtJiraIssueFieldNo.setText(prop.getProperty("JIRA_ISSUE_FIELD_NO"));
		txtJiraIssueFieldNo.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_11.setRightComponent(txtJiraIssueFieldNo);
		txtJiraIssueFieldNo.setColumns(10);
		
		JSplitPane splitPane_12 = new JSplitPane();
		panel_2.add(splitPane_12);
		
		JLabel lblNewLabel_31 = new JLabel("JIRA_ISSUE_FIELD_THEME");
		lblNewLabel_31.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_31.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_31.setToolTipText("Наименование поля заявок пользователя. Тема");
		splitPane_12.setLeftComponent(lblNewLabel_31);
		
		txtJiraIssueFieldTheme = new JTextField();
		txtJiraIssueFieldTheme.setText(prop.getProperty("JIRA_ISSUE_FIELD_THEME"));
		txtJiraIssueFieldTheme.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_12.setRightComponent(txtJiraIssueFieldTheme);
		txtJiraIssueFieldTheme.setColumns(10);
		
		JSplitPane splitPane_13 = new JSplitPane();
		panel_2.add(splitPane_13);
		
		JLabel lblNewLabel_32 = new JLabel("JIRA_ISSUE_FIELD_DT");
		lblNewLabel_32.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_32.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_32.setToolTipText("Наименование поля заявок пользователя. Дата обновления");
		splitPane_13.setLeftComponent(lblNewLabel_32);
		
		txtJiraIssueFieldDtUpd = new JTextField();
		txtJiraIssueFieldDtUpd.setText(prop.getProperty("JIRA_ISSUE_FIELD_DT"));
		txtJiraIssueFieldDtUpd.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_13.setRightComponent(txtJiraIssueFieldDtUpd);
		txtJiraIssueFieldDtUpd.setColumns(10);
		
		JSplitPane splitPane_14 = new JSplitPane();
		panel_2.add(splitPane_14);
		
		JLabel lblNewLabel_33 = new JLabel("JIRA_ISSUE_FIELD_STS");
		lblNewLabel_33.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_33.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_33.setToolTipText("Наименование поля заявок пользователя. Статус");
		splitPane_14.setLeftComponent(lblNewLabel_33);
		
		txtJiraIssueFieldStatus = new JTextField();
		txtJiraIssueFieldStatus.setText(prop.getProperty("JIRA_ISSUE_FIELD_STS"));
		txtJiraIssueFieldStatus.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_14.setRightComponent(txtJiraIssueFieldStatus);
		txtJiraIssueFieldStatus.setColumns(10);
		
		JSplitPane splitPane_15 = new JSplitPane();
		panel_2.add(splitPane_15);
		
		JLabel lblNewLabel_34 = new JLabel("JIRA_ISSUE_FIELD_DESC");
		lblNewLabel_34.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_34.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_34.setToolTipText("Наименование поля заявок пользователя. Комментарий");
		splitPane_15.setLeftComponent(lblNewLabel_34);
		
		txtJiraIssueFieldDescription = new JTextField();
		txtJiraIssueFieldDescription.setText(prop.getProperty("JIRA_ISSUE_FIELD_DESC"));
		txtJiraIssueFieldDescription.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_15.setRightComponent(txtJiraIssueFieldDescription);
		txtJiraIssueFieldDescription.setColumns(10);
		
		JSplitPane splitPane_16 = new JSplitPane();
		panel_2.add(splitPane_16);
		
		JLabel lblNewLabel_35 = new JLabel("JIRA_ISSUE_FIELD_LINK_TYPE");
		lblNewLabel_35.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_35.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_35.setToolTipText("Наименование поля связанных запросов. Тип");
		splitPane_16.setLeftComponent(lblNewLabel_35);
		
		txtJiraIssueFieldLinkType = new JTextField();
		txtJiraIssueFieldLinkType.setText(prop.getProperty("JIRA_ISSUE_FIELD_LINK_TYPE"));
		txtJiraIssueFieldLinkType.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_16.setRightComponent(txtJiraIssueFieldLinkType);
		txtJiraIssueFieldLinkType.setColumns(10);
		
		JSplitPane splitPane_17 = new JSplitPane();
		panel_2.add(splitPane_17);
		
		JLabel lblNewLabel_36 = new JLabel("JIRA_ISSUE_FIELD_LINK_TYPE_VALUE");
		lblNewLabel_36.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_36.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_36.setToolTipText("Наименование поля связанных запросов. Значение");
		splitPane_17.setLeftComponent(lblNewLabel_36);
		
		txtJiraIssueFieldLinkTypeValue = new JTextField();
		txtJiraIssueFieldLinkTypeValue.setText(prop.getProperty("JIRA_ISSUE_FIELD_LINK_TYPE_VALUE"));
		txtJiraIssueFieldLinkTypeValue.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_17.setRightComponent(txtJiraIssueFieldLinkTypeValue);
		txtJiraIssueFieldLinkTypeValue.setColumns(10);
		
		JSplitPane splitPane_18 = new JSplitPane();
		panel_2.add(splitPane_18);
		
		JLabel lblNewLabel_37 = new JLabel("JIRA_ISSUE_LINK_COMMENT");
		lblNewLabel_37.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_37.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_37.setToolTipText("Наименование поля связанных запросов. Комментарий");
		splitPane_18.setLeftComponent(lblNewLabel_37);
		
		txtJiraIssueFieldLinkComment = new JTextField();
		txtJiraIssueFieldLinkComment.setText(prop.getProperty("JIRA_ISSUE_LINK_COMMENT"));
		txtJiraIssueFieldLinkComment.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_18.setRightComponent(txtJiraIssueFieldLinkComment);
		txtJiraIssueFieldLinkComment.setColumns(10);
		
		JSplitPane splitPane_39 = new JSplitPane();
		panel_2.add(splitPane_39);
		
		JLabel lblNewLabel_38 = new JLabel("JIRA_ISSUE_LINK_VISIBILITY_TYPE");
		lblNewLabel_38.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_38.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_38.setToolTipText("Наименование поля связанных запросов. Тип видимости");
		splitPane_39.setLeftComponent(lblNewLabel_38);
		
		txtJiraIssueFieldLinkVisibleType = new JTextField();
		txtJiraIssueFieldLinkVisibleType.setText(prop.getProperty("JIRA_ISSUE_LINK_VISIBILITY_TYPE"));
		txtJiraIssueFieldLinkVisibleType.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_39.setRightComponent(txtJiraIssueFieldLinkVisibleType);
		txtJiraIssueFieldLinkVisibleType.setColumns(10);
		
		JSplitPane splitPane_40 = new JSplitPane();
		panel_2.add(splitPane_40);
		
		JLabel lblNewLabel_39 = new JLabel("JIRA_ISSUE_LINK_VISIBILITY_VALUE");
		lblNewLabel_39.setPreferredSize(new Dimension(230, 14));
		lblNewLabel_39.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_39.setToolTipText("Наименование поля связанных запросов. Значение видимости");
		splitPane_40.setLeftComponent(lblNewLabel_39);
		
		txtJiraIssueFieldLinkVisibleValue = new JTextField();
		txtJiraIssueFieldLinkVisibleValue.setText(prop.getProperty("JIRA_ISSUE_LINK_VISIBILITY_VALUE"));
		txtJiraIssueFieldLinkVisibleValue.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_40.setRightComponent(txtJiraIssueFieldLinkVisibleValue);
		txtJiraIssueFieldLinkVisibleValue.setColumns(10);
		
		
		JPanel ldapPnl = new JPanel();
		mainPane.addTab("LDAP", null, ldapPnl, null);
		ldapPnl.setLayout(new GridLayout(1, 0, 0, 0));
		JScrollPane ldapScrlPane = new JScrollPane();
		ldapScrlPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ldapPnl.add(ldapScrlPane);
		
		JPanel panel_3 = new JPanel();
		ldapScrlPane.setViewportView(panel_3);
		panel_3.setLayout(new GridLayout(11, 2, 0, 0));
		
		JSplitPane splitPane_42 = new JSplitPane();
		panel_3.add(splitPane_42);
		
		JLabel lblNewLabel_40 = new JLabel("LDAP_HOST");
		lblNewLabel_40.setToolTipText("Подключение LDAP. Хост");
		lblNewLabel_40.setPreferredSize(new Dimension(230, 0));
		lblNewLabel_40.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_42.setLeftComponent(lblNewLabel_40);
		
		txtLdapHost = new JTextField();
		txtLdapHost.setText(prop.getProperty("LDAP_HOST"));
		txtLdapHost.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_42.setRightComponent(txtLdapHost);
		txtLdapHost.setColumns(10);
		
		JSplitPane splitPane_43 = new JSplitPane();
		panel_3.add(splitPane_43);
		
		JLabel lblNewLabel_41 = new JLabel("LDAP_PORT");
		lblNewLabel_41.setToolTipText("Подключение LDAP. Порт");
		lblNewLabel_41.setPreferredSize(new Dimension(230, 0));
		lblNewLabel_41.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_43.setLeftComponent(lblNewLabel_41);
		
		txtLdapPort = new JTextField();
		txtLdapPort.setText(prop.getProperty("LDAP_PORT"));
		txtLdapPort.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_43.setRightComponent(txtLdapPort);
		txtLdapPort.setColumns(10);
		
		JSplitPane splitPane_44 = new JSplitPane();
		panel_3.add(splitPane_44);
		
		JLabel lblNewLabel_42 = new JLabel("LDAP_SEARCHBASE");
		lblNewLabel_42.setToolTipText("Подключение LDAP. Каталог для поиска базы пользователей");
		lblNewLabel_42.setPreferredSize(new Dimension(230, 0));
		lblNewLabel_42.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_44.setLeftComponent(lblNewLabel_42);
		
		txtLdapSearchbase = new JTextField();
		txtLdapSearchbase.setText(prop.getProperty("LDAP_SEARCHBASE"));
		txtLdapSearchbase.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_44.setRightComponent(txtLdapSearchbase);
		txtLdapSearchbase.setColumns(10);
		
		JSplitPane splitPane_45 = new JSplitPane();
		panel_3.add(splitPane_45);
		
		JLabel lblNewLabel_43 = new JLabel("LDAP_SEARCHFILTER");
		lblNewLabel_43.setToolTipText("Подключение LDAP. Фильтр для поиска пользователей");
		lblNewLabel_43.setPreferredSize(new Dimension(230, 0));
		lblNewLabel_43.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_45.setLeftComponent(lblNewLabel_43);
		
		txtLdapSearchfilter = new JTextField();
		txtLdapSearchfilter.setText(prop.getProperty("LDAP_SEARCHFILTER"));
		txtLdapSearchfilter.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_45.setRightComponent(txtLdapSearchfilter);
		txtLdapSearchfilter.setColumns(10);
		
		JSplitPane splitPane_46 = new JSplitPane();
		panel_3.add(splitPane_46);
		
		JLabel lblNewLabel_44 = new JLabel("LDAP_USERNAME");
		lblNewLabel_44.setToolTipText("Подключение LDAP. Логин");
		lblNewLabel_44.setPreferredSize(new Dimension(230, 0));
		lblNewLabel_44.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_46.setLeftComponent(lblNewLabel_44);
		
		txtLdapUser = new JTextField();
		txtLdapUser.setText(prop.getProperty("LDAP_USERNAME"));
		txtLdapUser.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_46.setRightComponent(txtLdapUser);
		txtLdapUser.setColumns(10);
		
		JSplitPane splitPane_41 = new JSplitPane();
		panel_3.add(splitPane_41);
		
		JLabel lblNewLabel_45 = new JLabel("LDAP_PASSWORD");
		lblNewLabel_45.setToolTipText("Подключение LDAP. Пароль");
		lblNewLabel_45.setPreferredSize(new Dimension(230, 0));
		lblNewLabel_45.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_41.setLeftComponent(lblNewLabel_45);
		
		txtLdapPass = new JPasswordField();
		txtLdapPass.setText(prop.getProperty("LDAP_PASSWORD"));
		txtLdapPass.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_41.setRightComponent(txtLdapPass);
		
		JSplitPane splitPane_47 = new JSplitPane();
		panel_3.add(splitPane_47);
		
		JLabel lblNewLabel_46 = new JLabel("AD_FIELD_TELEPHONE");
		lblNewLabel_46.setToolTipText("Поле LDAP. Телефон ");
		lblNewLabel_46.setPreferredSize(new Dimension(230, 0));
		lblNewLabel_46.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_47.setLeftComponent(lblNewLabel_46);
		
		txtAdFieldTelephone = new JTextField();
		txtAdFieldTelephone.setText(prop.getProperty("AD_FIELD_TELEPHONE"));
		txtAdFieldTelephone.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_47.setRightComponent(txtAdFieldTelephone);
		txtAdFieldTelephone.setColumns(10);
		
		JSplitPane splitPane_48 = new JSplitPane();
		panel_3.add(splitPane_48);
		
		JLabel lblNewLabel_47 = new JLabel("AD_FIELD_EMAIL");
		lblNewLabel_47.setToolTipText("Поле LDAP. Адрес электронной почты");
		lblNewLabel_47.setPreferredSize(new Dimension(230, 0));
		lblNewLabel_47.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_48.setLeftComponent(lblNewLabel_47);
		
		txtAdFieldMail = new JTextField();
		txtAdFieldMail.setText(prop.getProperty("AD_FIELD_EMAIL"));
		txtAdFieldMail.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_48.setRightComponent(txtAdFieldMail);
		txtAdFieldMail.setColumns(10);
		
		JSplitPane splitPane_49 = new JSplitPane();
		panel_3.add(splitPane_49);
		
		JLabel lblNewLabel_48 = new JLabel("AD_FIELD_SURNAME");
		lblNewLabel_48.setToolTipText("Поле LDAP. Фамилия");
		lblNewLabel_48.setPreferredSize(new Dimension(230, 0));
		lblNewLabel_48.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_49.setLeftComponent(lblNewLabel_48);
		
		txtAdFieldSurname = new JTextField();
		txtAdFieldSurname.setText(prop.getProperty("AD_FIELD_SURNAME"));
		txtAdFieldSurname.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_49.setRightComponent(txtAdFieldSurname);
		txtAdFieldSurname.setColumns(10);
		
		JSplitPane splitPane_50 = new JSplitPane();
		panel_3.add(splitPane_50);
		
		JLabel lblNewLabel_49 = new JLabel("AD_FIELD_NAME");
		lblNewLabel_49.setToolTipText("Поле LDAP. Имя");
		lblNewLabel_49.setPreferredSize(new Dimension(230, 0));
		lblNewLabel_49.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_50.setLeftComponent(lblNewLabel_49);
		
		txtAdFieldName = new JTextField();
		txtAdFieldName.setText(prop.getProperty("AD_FIELD_NAME"));
		txtAdFieldName.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_50.setRightComponent(txtAdFieldName);
		txtAdFieldName.setColumns(10);
		
		JSplitPane splitPane_51 = new JSplitPane();
		panel_3.add(splitPane_51);
		
		JLabel lblNewLabel_50 = new JLabel("AD_FIELD_LOGIN");
		lblNewLabel_50.setToolTipText("Поле LDAP. Логин");
		lblNewLabel_50.setPreferredSize(new Dimension(230, 0));
		lblNewLabel_50.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_51.setLeftComponent(lblNewLabel_50);
		
		txtAdFieldLogin = new JTextField();
		txtAdFieldLogin.setText(prop.getProperty("AD_FIELD_LOGIN"));
		txtAdFieldLogin.setFont(new Font("Arial", Font.PLAIN, 12));
		splitPane_51.setRightComponent(txtAdFieldLogin);
		txtAdFieldLogin.setColumns(10);
	

	
				
				
	}

}
