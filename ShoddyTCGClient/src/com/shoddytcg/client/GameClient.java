/**
 * Copyright (C) 2009 ShoddyTCG Development Team
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.shoddytcg.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.shoddytcg.client.gui.ImagePanel;


/**
 * @author Nushio
 *
 */
public class GameClient extends JFrame {

	private static final long serialVersionUID = -1068520962938575120L;
	
	private Border loginBorder;
	private TitledBorder loginTitledBorder;
	private JPanel loginPanel = new JPanel();
		
	public static void main(String args[]){
		new GameClient();
	}
	
	public GameClient(){
		try {
			// Making things look pretty!
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		try {
            initGui();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
		// Setting properties
		Dimension screen = getToolkit().getScreenSize();
		Rectangle bounds = getBounds();
		bounds.width = 460;
		bounds.height = 340;
		bounds.x = (screen.width - bounds.width) / 2;
		bounds.y = (screen.height - bounds.height) / 2;
		setBounds(bounds);

		setResizable(false);
		
		setTitle("ShoddyTCG");
		setupMenu();
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private void initGui() throws Exception {
		loginBorder = BorderFactory.createEtchedBorder(Color.white, new Color(148, 145, 140));
		loginTitledBorder = new TitledBorder(loginBorder, "Login");
        
		BufferedImage image = ImageIO.read(new File("res/pics/ShoddyTCG.png"));	       
		ImagePanel panel = new ImagePanel(image);
        panel.setBounds(new Rectangle(0, 20, 450, 66));
		
        loginPanel.setBorder(loginTitledBorder);
        loginPanel.setBounds(new Rectangle(10, 90, 435, 180));
        loginPanel.setLayout(null);
        
        JLabel serverLabel = new JLabel();
        serverLabel.setText("Server");
        serverLabel.setBounds(new Rectangle(114, 25, 85, 27));
        
        JComboBox serverCombo = new JComboBox();
        serverCombo.addItem("Ruins of Alph");
        serverCombo.setBounds(new Rectangle(195, 25, 150, 27));
        
        JLabel userLabel = new JLabel();
        userLabel.setText("Username");
        userLabel.setBounds(new Rectangle(90, 60, 85, 27));
        
        JLabel passLabel = new JLabel();
        passLabel.setText("Password");
        passLabel.setBounds(new Rectangle(95, 90, 85, 27));
        
        JTextField userField = new JTextField();
        userField.setText("Username");
        userField.setBounds(new Rectangle(195, 60, 150, 27));
        
        JPasswordField passField = new JPasswordField();
        passField.setText("password");
        passField.setBounds(new Rectangle(195, 90, 150, 27));
        
        JButton loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.setBounds(new Rectangle(95, 125, 125, 34));	
        
        JButton registerButton = new JButton();
        registerButton.setText("Register");
        registerButton.setBounds(new Rectangle(220, 125, 125, 34));	
        
        this.getContentPane().add(loginPanel, null);
        loginPanel.add(serverLabel);
        loginPanel.add(serverCombo);
        loginPanel.add(userLabel);
        loginPanel.add(passLabel);
        loginPanel.add(userField);
        loginPanel.add(passField);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);
        
        this.getContentPane().add(panel,null);
        this.getContentPane().setLayout(null);
    }
	
	private void setupMenu() {
		JMenu menu = new JMenu("Menu");
		menu.add("Deck Editor");
		menu.add(new AboutAction());

		// new stuff
		JMenuBar bar = new JMenuBar();
		bar.add(menu);

		setJMenuBar(bar);
	}
	
	public static class AboutAction extends AbstractAction {

		private static final long serialVersionUID = 5492173304463396871L;

		public AboutAction() {
			super("About");
		}

		public void actionPerformed(ActionEvent e) {
			JTextArea area = new JTextArea(8, 26);
			Font f = new Font(area.getFont().getName(), Font.PLAIN, 13);
			area.setFont(f);

			area.setText("This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.\n\nDeveloped by the ShoddyTCG Development Team\nWebsite: http://shoddytcg.com");

			area.setWrapStyleWord(true);
			area.setLineWrap(true);
			area.setEditable(false);

			JPanel p = new JPanel();
			area.setBackground(p.getBackground());

			JOptionPane.showMessageDialog(null, area, "About", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}