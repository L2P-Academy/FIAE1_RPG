package view;

import javax.swing.*;

import controller.SoundController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class IntroView extends JFrame {

	private SoundController soundController;
	private JButton nextBtn;
    private JTextArea scrollingTextArea;
    private String introText = "In einer Welt, in der Magie und Abenteuer allgegenwärtig sind, "
    		+ "ruht das Schicksal auf den Schultern eines mutigen Helden. "
    		+ "In einem malerischen Dorf namens Eichenhain, umgeben von majestätischen Wäldern "
    		+ "und schroffen Bergen, lebt ein junger Mann namens Rida. Sein Herz brennt vor "
    		+ "Abenteuerlust, und sein Geist sehnt sich nach der Unbekannten, die jenseits der "
    		+ "Grenzen seines kleinen Dorfes liegt. Eines Tages, als die Sonne golden über den "
    		+ "Horizont klettert und die Welt in ein warmes Licht taucht, trifft eine Nachricht "
    		+ "aus der fernen Hauptstadt des Königreichs ein. Ein uraltes Böses erhebt sich aus "
    		+ "den Schatten, bedroht die Welt und versetzt das Land in Angst und Schrecken. "
    		+ "Der König ruft nach Helden, die sich ihm entgegenstellen und das Land vor dem "
    		+ "drohenden Unheil retten können. Inspiriert von dem Ruf des Abenteuers und dem "
    		+ "Drang, seinem Volk zu dienen, beschließt Rida, sich der Mission anzuschließen. "
    		+ "Mit einem Schwert an seiner Seite, einem Hauch von Magie in der Luft und der "
    		+ "Unterstützung seiner treuen Freunde macht er sich auf den Weg zu einer Reise, "
    		+ "die ihn durch gefährliche Wälder, eisige Gebirge und dunkle Höhlen führen wird. "
    		+ "Die Straße vor ihm ist ungewiss und voller Herausforderungen, aber Rida weiß, "
    		+ "dass er den Mut und die Entschlossenheit besitzt, um den Gefahren zu trotzen und "
    		+ "das Böse zu besiegen. Seine Reise wird nicht nur sein eigenes Schicksal, sondern "
    		+ "auch das Schicksal des gesamten Königreichs bestimmen. Begleite Rida auf seinem "
    		+ "epischen Abenteuer, während er sich auf den Weg macht, um das Böse zu bekämpfen, "
    		+ "das in den Schatten lauert, und die Welt von Dragons & Dungeons zu retten. "
    		+ "Die Reise beginnt jetzt...";

    public IntroView() {
        setTitle("Intro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        
        // Soundcontroller Init
        soundController = new SoundController();
        
        scrollingTextArea = new JTextArea();
        scrollingTextArea.setFont(new Font("Calisto MT", Font.PLAIN, 32)); // Schriftart anpassen
        scrollingTextArea.setLineWrap(true); // Textumbruch aktivieren
        scrollingTextArea.setWrapStyleWord(true); // Wortumbruch aktivieren
        scrollingTextArea.setEditable(false); // Text nicht editierbar machen
        scrollingTextArea.setPreferredSize(new Dimension(400, 200));
        scrollingTextArea.setBackground(new Color(245, 245, 220));
        
        nextBtn = new JButton("Weiter");
        nextBtn.setFont(new Font("Old English Text MT", Font.PLAIN, 28));
        
        nextBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				soundController.stopMusicLoop();
				MapView mapView = new MapView();
				dispose();
				
			}
		});
        
        getContentPane().add(scrollingTextArea);
        getContentPane().add(nextBtn, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);

        animateText();
        soundController.playMusicLoop("res/soundFX/music/Intro_Music.wav");
    }

    private void animateText() {
        new Thread(() -> {
            try {
                for (int i = 0; i <= introText.length(); i++) {
                    final String currentText = introText.substring(0, i); // Aktuellen Text speichern
                    SwingUtilities.invokeLater(() -> {
                        scrollingTextArea.setText(currentText);
                    });
                    TimeUnit.MILLISECONDS.sleep(40); // Anpassen der Geschwindigkeit
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}
