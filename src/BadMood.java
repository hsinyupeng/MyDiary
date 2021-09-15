
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class BadMood implements Bottle {
private JFrame moodFrame;
private JButton check= new JButton("Check");
private JButton reset=new JButton("Reset");
private JButton back=new JButton("Back");
private String FileMainname;
private String savename;
private String deletename;
private String backname;
private BufferedImage image1;
private BufferedImage image2;
private BufferedImage image3;
private BufferedImage image4;
private JTextArea moodtext=new JTextArea();
private JLabel label=new JLabel("");
private String UserID;
private Font mf=new Font("TimesRoman",Font.PLAIN,16);
BadMood(String ID){
	    this.UserID=ID;
		LoadMainFile();
		SetMoodTable();
	}
BadMood(String ID,String Text){
    this.UserID=ID;
	LoadMainFile();
	SetMoodTable();
	moodtext.setText(Text);
	moodtext.setEditable(false);
	back.setActionCommand("BackPast");
	reset.setVisible(false);
	check.setVisible(false);
}
	
	public void SetMoodTable() {
    	moodFrame=new JFrame("");
    	JLabel scrollPane = new JLabel(new ImageIcon(image1));
    	label.setBounds(370, 20, 100, 50);
    	check.setIcon(new ImageIcon(image2));   	
    	check.setBounds(680, 260, image2.getWidth(), image2.getHeight());
    	check.setOpaque(false);
    	check.setBorder(null);
    	check.setContentAreaFilled(false);
    	
    	reset.setIcon(new ImageIcon(image3));   	
    	reset.setBounds(680, 360, image3.getWidth(), image3.getHeight());
    	reset.setOpaque(false);
    	reset.setBorder(null);
    	reset.setContentAreaFilled(false);
    	
    	back.setIcon(new ImageIcon(image4));   	
    	back.setBounds(680, 460, image4.getWidth(), image4.getHeight());
    	back.setOpaque(false);
    	back.setBorder(null);
    	back.setContentAreaFilled(false);
    	moodtext.setBounds(70, 117, 540, 460);
    	moodtext.setLineWrap(true);
    	moodtext.setWrapStyleWord(true);
    	moodtext.setBorder(null);
    	moodtext.setOpaque(false);
    	moodtext.setFont(mf);
    	moodFrame.setResizable(false);
    	moodFrame.add(moodtext);
    	moodFrame.add(check);
    	moodFrame.add(reset);
    	moodFrame.add(label);
    	moodFrame.add(back);
    	moodFrame.getContentPane().add(scrollPane);
    	moodFrame.pack();
    	moodFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	moodFrame.setTitle("Bad Mood");
    	moodFrame.setLocationRelativeTo(null);
    	moodFrame.setVisible(true);
    	
    	class ButtonListener implements ActionListener{   		
     		public void actionPerformed(ActionEvent event)
     		{   String command=event.getActionCommand();
     		if(command.equals("Reset")) {
     		moodtext.setText("");
     		}
     		else if(command.equals("Check")) {
     			try {
					inputdata();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
     		}
     		else if(command.equals("Back")) {
     			moodFrame.setVisible(false);
     			
				try {
					new MainFrame(UserID);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
     			
     		}
     		else if(command.equals("BackPast")) {
				moodFrame.setVisible(false);
			}
     		}
    	}
     		 ActionListener listener = new ButtonListener();
             reset.addActionListener(listener);
             check.addActionListener(listener);
             back.addActionListener(listener);
    }
	public void LoadMainFile()
    {
            FileMainname="img/badmood.jpg";//�]�w�ɦW
            savename="img/save.png";
            deletename="img/delete.png";
            backname="img/back.png";
            try
            {
                    image1=ImageIO.read(new File( FileMainname));//Ū���ɮ�
                    image2=ImageIO.read(new File( savename));//Ū���ɮ�
                    image3=ImageIO.read(new File( deletename));
                    image4=ImageIO.read(new File( backname));
                   
            }
            catch(Exception e)
            {
                    javax.swing.JOptionPane.showMessageDialog(null, "���J���ɿ��~: "+ FileMainname);
                    image1=null;//�p�G���~������ܿ��~�T��
            }
    }
	public void open() {
		moodFrame.setVisible(true);
	}
	public void inputdata() throws SQLException {
		String server = "jdbc:mysql://140.119.19.73:9306/";
		String database ="TG06?useUnicode=true&characterEncoding=UTF-8";
		String url= server + database;
		String username= "TG06";
		String sqlpassword= "bMIEqf";
		Connection conn =null;		
		try {
			conn= DriverManager.getConnection(url,username,sqlpassword);
			PreparedStatement stat ;
			stat=conn.prepareStatement("INSERT INTO `Diary`(`UserID`, `BadDiary`) VALUES(?,?)");
			stat.setString(1, UserID);
			stat.setString(2, moodtext.getText());
			int i=stat.executeUpdate();
			if(i==1) 
			{ 
			JOptionPane.showMessageDialog(null, "���\","���ܰT��",JOptionPane.WARNING_MESSAGE);
			moodFrame.setVisible(false);
			new MainFrame(UserID);
			}
			else 
			{ 
			JOptionPane.showMessageDialog(null, "����","���ܰT��",JOptionPane.ERROR_MESSAGE); 
			}           
	     }
	     catch(SQLException e){
	     	System.out.println("���~�T��:" + e.getMessage());
	     	System.out.println("��Ʈw�B�z���~!!!");	
	     }
	     finally {
	     	conn.close();
	     }
	}
}