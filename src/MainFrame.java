import javax.swing.*;
import java.io.*;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class MainFrame extends JFrame {
 private BufferedImage img_main, img_history, img_bad, img_good, img_fortune, img_friend, img_setting, img_umbrella,img_logout;
 private JFrame mainframe;
 private JButton good = new JButton();
 private JButton bad = new JButton();
 private JButton fortune = new JButton();
 private JButton history = new JButton();
 private JButton friend = new JButton();
 private JButton setting = new JButton();
 private JButton logout=new JButton("Logout");
 private JLabel umbrella;
 private JLabel Billboard;
 private JLabel friend_label=new JLabel("Share Diary with Friends");
 private JLabel weather_label=new JLabel("Today's Weather");
 private JLabel good_label=new JLabel("Share your Good Mood");
 private JLabel bad_label=new JLabel("Release your Bad Mood");
 private JLabel fortune_label=new JLabel("Divine Today's Fortune");
 private JLabel history_label=new JLabel("Recall Your Past");
 private String ID;
 private Crawler c = new Crawler();
 private WeatherBuild w = new WeatherBuild();
 private WeatherPanel weather = new WeatherPanel(c, w);
 
 
 public MainFrame(String UserID) throws SQLException {
  LoadMainFile();
  SetMainTable();
  this.ID = UserID;
  new First(ID);
 }

 

 public void SetMainTable() {
  weather.setBounds(80, 70, weather.getWidth(), weather.getHeight());
  Billboard= new JLabel(new ImageIcon(img_logout));
  Billboard.setBounds(735, 216, img_logout.getWidth(), img_logout.getHeight());
  umbrella = new JLabel(new ImageIcon(img_umbrella));
  umbrella.setBounds(830, 266, img_umbrella.getWidth(), img_umbrella.getHeight());
  umbrella.setVisible(false);
  
  if (weather.getPath().equals("img/rainny.png")) {
   umbrella.setVisible(true);
  }
  mainframe = new JFrame("");
  JLabel scrollPane = new JLabel(new ImageIcon(img_main));// 把Image放進label裡

  friend_label.setBounds(541, 168, 140, 20);
  friend_label.setVisible(false);
  friend_label.setOpaque(true);
  friend_label.setBackground(new Color(255,255,255));
  mainframe.add(friend_label);
  weather_label.setBounds(270, 280, 100, 20);
  weather_label.setVisible(false);
  weather_label.setOpaque(true);
  weather_label.setBackground(new Color(255,255,255));
  mainframe.add(weather_label);
  good_label.setBounds(510, 285, 135, 20);
  good_label.setVisible(false);
  good_label.setOpaque(true);
  good_label.setBackground(new Color(255,255,255));
  mainframe.add( good_label);
  bad_label.setBounds(465, 285, 140, 20);
  bad_label.setVisible(false);
  bad_label.setOpaque(true);
  bad_label.setBackground(new Color(255,255,255));
  mainframe.add(bad_label);
  fortune_label.setBounds(580, 385, 135, 20);
  fortune_label.setVisible(false);
  fortune_label.setOpaque(true);
  fortune_label .setBackground(new Color(255,255,255));
  mainframe.add(fortune_label);
  history_label.setBounds(586, 285, 100, 20);
  history_label.setVisible(false);
  history_label.setOpaque(true);
  history_label.setBackground(new Color(255,255,255));
  mainframe.add(history_label);
  
  friend.setIcon(new ImageIcon(img_friend));
  bad.setIcon(new ImageIcon(img_bad));
  good.setIcon(new ImageIcon(img_good));
  history.setIcon(new ImageIcon(img_history));
  setting.setIcon(new ImageIcon(img_setting));
  fortune.setIcon(new ImageIcon(img_fortune));
  
  
  friend.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
  friend.setVerticalTextPosition(SwingConstants.CENTER);
  friend.setHorizontalTextPosition(SwingConstants.CENTER);

  friend.setBounds(520, 113, img_friend.getWidth(), img_friend.getHeight());
  bad.setBounds(445, 225, img_bad.getWidth(), img_bad.getHeight());
  good.setBounds(498, 225, img_good.getWidth(), img_good.getHeight());
  history.setBounds(556, 230, img_history.getWidth(), img_history.getHeight());
  setting.setBounds(445, 334, img_setting.getWidth(), img_setting.getHeight());
  fortune.setBounds(556, 334, img_fortune.getWidth(), img_fortune.getHeight());
  logout.setBounds(706, 120,160, 300);
  friend.setActionCommand("Friend");
  bad.setActionCommand("Bad");
  good.setActionCommand("Good");
  history.setActionCommand("History");
  setting.setActionCommand("Setting");
  fortune.setActionCommand("Fortune");
  logout.setActionCommand("Logout");
  logout.setForeground(new Color(255, 250, 250));
  logout.setFont(new Font("TimesRoman",Font.PLAIN,16));
  friend.setContentAreaFilled(false);
  bad.setContentAreaFilled(false);
  good.setContentAreaFilled(false);
  history.setContentAreaFilled(false);
  setting.setContentAreaFilled(false);
  fortune.setContentAreaFilled(false);
  logout.setContentAreaFilled(false);
  friend.setBorder(null);
  bad.setBorder(null);
  good.setBorder(null);
  history.setBorder(null);
  setting.setBorder(null);
  fortune.setBorder(null);
  logout.setBorder(null);
  mainframe.add(friend);
  mainframe.add(bad);
  mainframe.add(good);
  mainframe.add(history);
  mainframe.add(setting);
  mainframe.add(fortune);
  mainframe.add(weather);
  mainframe.add(umbrella);
  mainframe.add(logout);
  mainframe.add(Billboard);
  
  
  scrollPane.setSize(img_main.getWidth(), img_main.getHeight());
  mainframe.getContentPane().add(scrollPane);
  mainframe.pack();
  mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  mainframe.setTitle("Main");
  mainframe.setLocationRelativeTo(null);
  mainframe.setVisible(true);
  mainframe.setResizable(false);
  class ButtonListener implements ActionListener {
   public void actionPerformed(ActionEvent event) {
    String command = event.getActionCommand();
    if (command.equals("Good")) {
    new GoodMood(ID);
     
     mainframe.setVisible(false);
    } else if (command.equals("Bad")) {
     new BadMood(ID);
     
     mainframe.setVisible(false);
    } else if (command.equals("History")) {
     try {
      PastDiary past = new PastDiary(ID);
      past.open();
      mainframe.setVisible(false);
     } catch (SQLException e) {
      e.printStackTrace();
     }
    } else if (command.equals("Fortune")) {
     try {
      new Fortune(ID);
     } catch (SQLException e) {
      e.printStackTrace();
     }
     mainframe.setVisible(false);
    } else if (command.equals("Friend")) {
     try {
      new Friend_List(ID);
      mainframe.setVisible(false);
     } catch (SQLException e) {
      e.printStackTrace();
     }     
    } else if (command.equals("Setting")) {

    }
    else if (command.equals("Logout")) { 
     int op = JOptionPane.showConfirmDialog(null, "是否要登出", "提示",JOptionPane.YES_NO_OPTION);;
     if(op==JOptionPane.YES_OPTION){
      new LoginFrame();      
      mainframe.setVisible(false);
     }
     else if(op==JOptionPane.NO_OPTION){
      
     }
     
    }
   }
  }
  
  class mouselistener implements MouseListener {	
		@Override
		// 當滑鼠進入某物件範圍時
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == friend) {
				friend_label.setVisible(true);
			}
			if (e.getSource() == weather) {
				weather_label.setVisible(true);
			}
			if (e.getSource() == good) {
				 good_label.setVisible(true);
			}
			if (e.getSource() == bad) {
				bad_label.setVisible(true);
			}
			if (e.getSource() == fortune) {
				fortune_label.setVisible(true);
			}
			if (e.getSource() == history) {
				history_label.setVisible(true);
			}
		}
		@Override
		// 當滑鼠退出某物件範圍時
		public void mouseExited(MouseEvent e) {
			if (e.getSource() == friend) {
				friend_label.setVisible(false);
			}
			if (e.getSource() == weather) {
				weather_label.setVisible(false);
			}
			if (e.getSource() == good) {
				good_label.setVisible(false);
			}
			if (e.getSource() == bad) {
				bad_label.setVisible(false);
			}
			if (e.getSource() == fortune) {
				fortune_label.setVisible(false);
			}
			if (e.getSource() == history) {
				history_label.setVisible(false);
			}
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {						
		}
		@Override
		public void mousePressed(MouseEvent e) {			
		}
		@Override
		public void mouseReleased(MouseEvent e) {			
		}
		
		
		}
  MouseListener listener_touch=new mouselistener();
  ActionListener listener = new ButtonListener();
  friend.addActionListener(listener);  
  good.addActionListener(listener);
  bad.addActionListener(listener);
  history.addActionListener(listener);
  setting.addActionListener(listener);
  fortune.addActionListener(listener);
  logout.addActionListener(listener);
  friend.addMouseListener(listener_touch);
  weather.addMouseListener(listener_touch);
  good.addMouseListener(listener_touch);
  bad.addMouseListener(listener_touch);
  fortune.addMouseListener(listener_touch);
  history.addMouseListener(listener_touch);
  
  
 }

 public void LoadMainFile() {
  String historyPath = "img/history.png";
  String mainPath = "img/main.png";
  String badPath = "img/bad.png";
  String goodPath = "img/good.png";
  String fortunePath = "img/fortune.png";
  String friendPath = "img/friend.png";
  String settingPath = "img/setting.png";
  String umbrellaPath = "img/umbrella.png";
  String logoutPath = "img/logout.png";
  try {
   img_main = ImageIO.read(new File(mainPath));
   img_history = ImageIO.read(new File(historyPath));
   img_bad = ImageIO.read(new File(badPath));
   img_good = ImageIO.read(new File(goodPath));
   img_fortune = ImageIO.read(new File(fortunePath));
   img_friend = ImageIO.read(new File(friendPath));
   img_setting = ImageIO.read(new File(settingPath));
   img_umbrella = ImageIO.read(new File(umbrellaPath));
   img_logout= ImageIO.read(new File(logoutPath));
  } catch (Exception e) {
   javax.swing.JOptionPane.showMessageDialog(null, "載入圖檔錯誤: " + mainPath);
   javax.swing.JOptionPane.showMessageDialog(null, "載入圖檔錯誤: " + historyPath);
   javax.swing.JOptionPane.showMessageDialog(null, "載入圖檔錯誤: " + badPath);
   javax.swing.JOptionPane.showMessageDialog(null, "載入圖檔錯誤: " + goodPath);
   javax.swing.JOptionPane.showMessageDialog(null, "載入圖檔錯誤: " + fortunePath);
   javax.swing.JOptionPane.showMessageDialog(null, "載入圖檔錯誤: " + friendPath);
   javax.swing.JOptionPane.showMessageDialog(null, "載入圖檔錯誤: " + settingPath);
   javax.swing.JOptionPane.showMessageDialog(null, "載入圖檔錯誤: " + umbrellaPath);
   img_main = null;
   img_history = null;
   img_bad = null;
   img_good = null;
   img_fortune = null;
   img_friend = null;
   img_setting = null;
   img_umbrella = null;
  }
 }
 
 
}