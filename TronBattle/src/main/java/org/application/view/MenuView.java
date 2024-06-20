package org.application.view;

import lombok.Getter;
import lombok.Setter;
import org.application.model.MenuModel;
import org.application.controller.MenuController;
import org.application.loop.MenuLoop;
import org.application.utility.ResourceLoader;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel {
    // Attributi
    private MenuController controller;
    private MenuModel model;
    private ResourceLoader loader;
    private JPanel panelMenu, panelButton;
    // Getters
    @Getter
    private JButton btnHumanVsIA, btnExit;
    private JLabel titleLabel;
    private Font font;
    // Attributi per l'animazione
    private MenuLoop menuLoop;
    @Setter
    private int currentFrame = 0;

    // Costruttore
    public MenuView () {
        // creo il loader
        loader = ResourceLoader.getInstance();

        //setto il cursore personalizzato
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(loader.getBufferedImage("/cursor/pointer.png", 32, 32, false), new Point(20, 20), "Cursor"));

        // Carico il font personalizzato
        font = loader.getFont("/font/batmfa.ttf", 28, Font.PLAIN);

        // creo model e controller
        model = new MenuModel(this);
        controller = new MenuController(model, this);

        //Setto il layout del pannello principale
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Inizializzo il menù
        initMenu();

        initImageAnimation();
    }

    // Metodi
    private void initMenu() {
        // Creo il menu
        initComponent();    // qua creo tutti i componenti del menu e li metto nel panelMenu
        controller.addListener();
        this.add(panelMenu);    // aggiungo il panelMenu al pannello principale
        repaint();
        revalidate();
    }
    private void initComponent() {
        // Inizializzo i vari componenti che poi userò nel menù
        btnHumanVsIA = new JButton("Gioca");
        btnHumanVsIA.setIcon(new ImageIcon(loader.getBufferedImage("/button/buttonBG.png", 300, 75, false)));
        btnHumanVsIA.setHorizontalTextPosition(JButton.CENTER);
        btnHumanVsIA.setVerticalTextPosition(JButton.CENTER);
        btnHumanVsIA.setFont(font.deriveFont(Font.PLAIN, 20));
        btnHumanVsIA.setBorderPainted(false);
        btnHumanVsIA.setFocusPainted(false);
        btnHumanVsIA.setContentAreaFilled(false);
        btnHumanVsIA.setForeground(Color.WHITE);

        btnExit = new JButton("Esci");
        btnExit.setIcon(new ImageIcon(loader.getBufferedImage("/button/buttonBG.png", 300, 75, false)));
        btnExit.setHorizontalTextPosition(JButton.CENTER);
        btnExit.setVerticalTextPosition(JButton.CENTER);
        btnExit.setFont(font.deriveFont(Font.PLAIN, 25));
        btnExit.setBorderPainted(false);
        btnExit.setFocusPainted(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setForeground(Color.WHITE);

        titleLabel = new JLabel();
        ImageIcon image = new ImageIcon(loader.getBufferedImage("/title/TronBattleTitle.png", 604, 85, false));
        titleLabel.setIcon(image);

        // Aggiungo i vari componenti al pannello
        panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
        panelMenu.setOpaque(false);

        panelButton = new JPanel();
        panelButton.setOpaque(false);
        panelButton.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        c.insets = new Insets(20, 0, 0, 0);

        // Aggiungo il titolo alla cella 0,0
        c.gridx = 0; c.gridy = 0;
        panelButton.add(titleLabel, c);

        // Aggiungo il bottone "Giocatore Singolo" alla cella 0,1
        c.gridx = 0; c.gridy = 1;
        panelButton.add(btnHumanVsIA, c);

        // Aggiungo il bottone "Esci" alla cella 0,4
        c.gridx = 0; c.gridy = 4;
        panelButton.add(btnExit, c);

        panelMenu.add(panelButton);
    }
    private void initImageAnimation() {
        // Inizializza l'animazione con MenuLoop
        menuLoop = new MenuLoop(this);
        menuLoop.startAnimation();
    }

    public void stopAnimation() {
        if (menuLoop != null) {
            menuLoop.stopAnimation();
        }
    }

    // Override del metodo paintComponent per disegnare l'immagine di sfondo
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (menuLoop.getFrames() != null && !menuLoop.getFrames().isEmpty()) {
            g.drawImage(menuLoop.getFrames().get(currentFrame), 0, 0, this);
        }
    }

}
