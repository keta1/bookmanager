package icu.ketal.bookmanager.util;

import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import icu.ketal.bookmanager.ui.main.MainApp;

import java.io.IOException;

public class UIHelper {
    public static void loadSVG() {
        new Thread(() -> {
            try {
                SVGGlyphLoader.loadGlyphsFont(MainApp.class.getResourceAsStream("/fonts/icomoon.svg"),
                        "icomoon.svg");
            } catch (IOException ioExc) {
                ioExc.printStackTrace();
            }
        }).start();
    }

    public static SVGGlyph getSVG(String glyphName, int size) throws Exception {
        String fileName = "icomoon.svg";
        var ret = SVGGlyphLoader.getIcoMoonGlyph(fileName + "." + glyphName);
        ret.setSize(size);
        return ret;
    }
}
