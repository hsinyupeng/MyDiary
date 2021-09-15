import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WeatherPanel extends JPanel {
	private JLabel imgLabel, textLabel;
	private Crawler crawler;
	private WeatherBuild weatherBuild;
	private BufferedImage img_weather;
	private String path;

	public WeatherPanel(Crawler crawler, WeatherBuild weatherBuild) {
		this.crawler = crawler;
		this.weatherBuild = weatherBuild;
		createComp();
	}

	public void createComp() {
		imgLabel = new JLabel();
		path = "";
		int i;
		for (i = 1; i <= 2; i++) {
			if (weatherBuild.getMap().get(crawler.getWeather()).equals("0" + Integer.toString(i))) {
				path = "img/" + "sunny" + ".png";
				break;
			}
		}
		for (i = 3; i >= 3 && i <= 7; i++) {
			if (weatherBuild.getMap().get(crawler.getWeather()).equals("0" + Integer.toString(i))) {
				path = "img/" + "cloudy" + ".png";
				break;
			}
		}
		for (i = 8; i >= 8 && i <= 23; i++) {
			if (i >= 8 && i <= 9) {
				if (weatherBuild.getMap().get(crawler.getWeather()).equals("0" + Integer.toString(i))) {
					path = "img/" + "rainny" + ".png";
					break;
				}
			}
			if (weatherBuild.getMap().get(crawler.getWeather()).equals(Integer.toString(i))) {
				path = "img/" + "rainny" + ".png";
				break;
			}
		}
		for (i = 24; i >= 24 && i <= 26; i++) {
			if (weatherBuild.getMap().get(crawler.getWeather()).equals(Integer.toString(i))) {
				path = "img/" + "sunny" + ".png";
				break;
			}
		}
		for (i = 27; i >= 27 && i <= 36; i++) {
			if (weatherBuild.getMap().get(crawler.getWeather()).equals(Integer.toString(i))) {
				path = "img/" + "cloudy" + ".png";
				break;
			}
		}
		for (i = 37; i >= 37 && i <= 41; i++) {
			if (weatherBuild.getMap().get(crawler.getWeather()).equals(Integer.toString(i))) {
				path = "img/" + "rainny" + ".png";
				break;
			}
		}
		try {
			img_weather = ImageIO.read(new File(path));
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "¸ü¤J¹ÏÀÉ¿ù»~: " + path);
		}
		imgLabel.setIcon(new ImageIcon(img_weather));
		add(imgLabel);
		setSize(img_weather.getWidth(), img_weather.getHeight());
		setOpaque(false);
	}
	
	public String getPath() {
		return path;
	}
}
