package view;

import javax.swing.*;

import controller.SoundController;
import model.SerializationIDs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class IntroView extends JFrame {

	private static final long serialVersionUID = SerializationIDs.introViewID;
	private SoundController soundController;
	private JButton nextBtn;
	private JTextArea scrollingTextArea;
	private String introText = "Heute ist der Tag, an dem du dein Abenteuer beginnst!\n"
	+ "\n"
	+ "Du hast deine gesamte Kindheit im beschaulichen kleinen Eichenhain verbracht, in den blühenden Wiesen gespielt, im Wald die höchsten Bäume erklommen und mit deinem selbstgebauten Holzschwert gegen Schattenmonster gekämpft. Nach dem Tod eurer Eltern hast du mit deinem Bruder Rida den Hof geführt, jeden Morgen frisches Brot gebacken, die Ziegen gemolken und das Feld bestellt. \n"
	+ "\n"
	+ "Doch nach dem letzten Winter ist kaum noch etwas von der Idylle übrig. Die nordische Kälte hat die Frühjahrsernte vernichtet, die Sommerernte wurde von Überflutungen weggeschwemmt und nun steht bereits der nächste Spätherbst ins Haus. Das einst laute, fröhliche Dorf steht nahezu leer, selbst die Händler machen sich kaum noch auf den Weg in diesen Teil des Königreiches. Nur die alte Margarete harrt noch aus und häkelt warme Söckchen für die Kinder, die bestimmt eines Tages wieder hier spielen werden. Nichts erinnert noch an den Ort, an dem du dein ganzes Leben verbringen wolltest.\n"
	+ "\n"
	+ "Eines Morgens schlägst du die Augen auf, schaust dich in deinem spärlich eingerichteten, staubigen Zimmer um und weißt, du musst hier raus. Du wartest auf Rida, der spät vom Holz fällen heim kommt, um ihm deinen Entschluss mitzuteilen: ein Neuanfang in der großen Stadt! \n"
	+ "\n"
	+ "Doch die Stadt ist weit entfernt und du hast viele Geschichten über Geister, Drachen und Zauberer gehört. Bist du für diese gefährliche, lange aber aufregende Reise bereit? ";

//    		"In einer Welt, in der Magie und Abenteuer allgegenwärtig sind, "
//    		+ "ruht das Schicksal auf den Schultern eines mutigen Helden. "
//    		+ "In einem malerischen Dorf namens Eichenhain, umgeben von majestätischen Wäldern "
//    		+ "und schroffen Bergen, lebt ein junger Mann namens Rida. Sein Herz brennt vor "
//    		+ "Abenteuerlust, und sein Geist sehnt sich nach dem Unbekannten, die jenseits der "
//    		+ "Grenzen seines kleinen Dorfes liegt. Inspiriert von dem Ruf des Abenteuers und dem "
//    		+ "Drang, seinem Volk zu dienen, beschließt Rida, sich der Mission anzuschließen. "
//    		+ "Mit einem Schwert an seiner Seite, einem Hauch von Magie in der Luft und der "
//    		+ "Unterstützung seiner treuen Freunde macht er sich auf den Weg zu einer Reise, "
//    		+ "die ihn durch gefährliche Wälder, eisige Gebirge und dunkle Höhlen führen wird. "
//    		+ "Die Straße vor ihm ist ungewiss und voller Herausforderungen, aber Rida weiß, "
//    		+ "dass er den Mut und die Entschlossenheit besitzt, um den Gefahren zu trotzen und "
//    		+ "das Böse zu besiegen. Seine Reise wird nicht nur sein eigenes Schicksal, sondern "
//    		+ "auch das Schicksal des gesamten Königreichs bestimmen. Begleite Rida auf seinem "
//    		+ "epischen Abenteuer, während er sich auf den Weg macht, um das Böse zu bekämpfen, "
//    		+ "das in den Schatten lauert, und die Welt von Dragons & Dungeons zu retten. "
//    		+ "\n"
//    		+ "Die Reise beginnt jetzt...";

	public IntroView() {
		setTitle("Intro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);

		// Soundcontroller Init
		soundController = new SoundController();

		scrollingTextArea = new JTextArea();
		scrollingTextArea.setFont(new Font("Calisto MT", Font.PLAIN, 32)); // Schriftart anpassen
		scrollingTextArea.setLineWrap(true); // Textumbruch aktivieren
		scrollingTextArea.setWrapStyleWord(true); // Wortumbruch aktivieren
		scrollingTextArea.setEditable(false); // Text nicht editierbar machen
		scrollingTextArea.setPreferredSize(new Dimension(400, 200));
		scrollingTextArea.setBackground(new Color(245, 245, 220));
		scrollingTextArea.setText(introText);

		nextBtn = new JButton("Auf geht's!");
		nextBtn.setFont(new Font("Old English Text MT", Font.PLAIN, 28));

		nextBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.stopMusicLoop();
				soundController.playButtonClickSound();
				new MapView();
				dispose();
			}
		});

		getContentPane().add(scrollingTextArea, BorderLayout.CENTER);
		getContentPane().add(nextBtn, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		setVisible(true);

		soundController.playMusicLoop("res/soundFX/music/Intro_Music.wav");
		animateText();

	}

	private void animateText() {
		new Thread(() -> {
			try {
				for (int i = 0; i <= introText.length(); i++) {
					final String currentText = introText.substring(0, i); // Aktuellen Text speichern
					SwingUtilities.invokeLater(() -> {
						scrollingTextArea.setText(currentText);
					});
					TimeUnit.MILLISECONDS.sleep(60); // Anpassen der Geschwindigkeit
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}).start();
	}
}
