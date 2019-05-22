package other;

import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import models.UserModel;

public class ScoreBoard extends JFrame {
	public ScoreBoard() {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		Collections.sort(UserModel.getExtent());
		for (UserModel user : UserModel.getExtent()) {
			listModel.addElement(user.getName() + ": " + user.getScore());
		}

		System.out.println(listModel.size());
		JList<String> userList = new JList<>(listModel);

		JScrollPane scrPane = new JScrollPane(userList);
		getContentPane().add(scrPane);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setTitle("Scoreboard");
		this.setSize(200, 200);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public void hide() {
		this.dispose();
	}

}