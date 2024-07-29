package fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class DungeonFont {
    private Font dungeonFont;

    public DungeonFont() {
        try {
            dungeonFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/DungeonFont.ttf")).deriveFont(40f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(dungeonFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public Font getDungeonFont() {
        return dungeonFont;
    }
}

