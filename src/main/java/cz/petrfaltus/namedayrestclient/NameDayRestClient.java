package cz.petrfaltus.namedayrestclient;

import java.awt.Dimension;

public class NameDayRestClient {
    public static void main(String[] args) {
        Dimension preferredSize = new Dimension(Const.APP_WINDOW_WIDTH, Const.APP_WINDOW_HEIGHT);

        Gui window = new Gui("Name day REST client");
        window.setLocationRelativeTo(null); // place to the middle of the screen
        window.setPreferredSize(preferredSize);
        window.pack();
        window.setDefaultCloseOperation(Gui.EXIT_ON_CLOSE);

        window.setVisible(true);
    }
}
