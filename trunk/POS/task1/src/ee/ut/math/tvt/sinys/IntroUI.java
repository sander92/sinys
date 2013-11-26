package ee.ut.math.tvt.sinys;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

public class IntroUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(IntroUI.class);

	private String teamName;
	private String teamLeader;
	private String teamLeaderEmail;
	private String[] teamMembers;
	private ImageIcon teamLogo;

	public IntroUI() throws HeadlessException, IOException {
		super();
		//org.apache.log4j.BasicConfigurator.configure();
	    PropertyConfigurator.configure("etc/log4j.properties");

		Properties propApp = new Properties();
    	try {
			propApp.load(new FileInputStream(new File("etc/application.properties")));
		} catch (IOException e) {
			log.warn(e.getMessage());
		}
		this.teamName = propApp.getProperty("team.name");
		this.teamLeader = propApp.getProperty("team.leader");
		this.teamLeaderEmail = propApp.getProperty("team.leader.email");
		this.teamMembers = propApp.getProperty("team.members").split(",");
		Image img = ImageIO.read(new File(propApp.getProperty("team.logo.path")));

		this.teamLogo = new ImageIcon(img.getScaledInstance(img.getWidth(null)/7, img.getHeight(null)/7, Image.SCALE_FAST));

		setTitle("Sales system");
		getContentPane().setLayout(new GridBagLayout());

		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());

		} catch (UnsupportedLookAndFeelException e1) {
			log.warn(e1.getMessage());
		}

		int width = 600;
		int height = 400;
		setSize(width, height);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - width) / 2, (screen.height - height) / 2);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				log.info("Intro window closed");
				System.exit(0);
			}
			
		});

		addItems();
		setVisible(true);
		log.info("Intro window started");

	}

	private void addItems() {
		JPanel panel=new JPanel();
		GridBagLayout layout=new GridBagLayout();
		
		panel.setLayout(layout);
		JLabel tName=new JLabel(teamName);
		JLabel tLeader=new JLabel("Fearless leader: "+teamLeader);
		JLabel tLeaderEmail=new JLabel(teamLeaderEmail);
		

		JLabel tLogo=new JLabel();
		tLogo.setIcon(teamLogo);

		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 1.0;
		gc.insets=new Insets(5, 5, 5, 5);
		    


		panel.add(tName,gc);
		gc.fill = GridBagConstraints.BOTH;

		panel.add(tLeader,gc);
		panel.add(tLeaderEmail,gc);
		JLabel titleMembers = new JLabel("Members:");
		titleMembers.setFont(new Font("Arial",Font.ITALIC,18));
		panel.add(titleMembers,gc);
		
		gc.insets=new Insets(5, 10, 5, 5);
		for (int i = 0; i < teamMembers.length; i++) {
			JLabel person = new JLabel(teamMembers[i]);
			person.setFont(new Font("Arial",Font.ITALIC,15));

			panel.add(person,gc);
		}

		panel.add(tLogo,gc);
		
		//for soft ver nr
		Properties prop = new Properties();
    	try {
			prop.load(new FileInputStream(new File("etc/version.properties")));
		} catch (IOException e) {
			log.warn(e.getMessage());
		}
		gc.fill = GridBagConstraints.CENTER;

		panel.add(new JLabel("Software revision nr: "+prop.getProperty("build.number")),gc);

		tName.setFont(new Font("Arial",Font.ITALIC,30));
		tLeader.setFont(new Font("Arial",Font.ITALIC,20));
		tLeaderEmail.setFont(new Font("Arial",Font.ITALIC,20));
		
		add(panel);

		
		
	}

}
