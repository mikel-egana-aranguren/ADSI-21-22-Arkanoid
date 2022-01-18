package eus.ehu.adsi.arkanoid.view;

import java.awt.Font;
import java.awt.Frame;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class AddFont extends Frame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Font impactttf = null;
    private static Font impact = null;
    private static InputStream myStream = null;
    private static final String FONT_PATH_GO3 = "src/main/resources/impact.ttf";

    public static Font createFont() {


            try {
                myStream = new BufferedInputStream(new FileInputStream(FONT_PATH_GO3));
                impactttf = Font.createFont(Font.TRUETYPE_FONT, myStream);
                impact = impactttf.deriveFont(Font.PLAIN, 24);               
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("Font not loaded.");
            }
            return impact;
    }
}