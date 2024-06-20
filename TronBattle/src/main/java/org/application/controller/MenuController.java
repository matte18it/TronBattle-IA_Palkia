package org.application.controller;

import com.github.pervoj.jiconfont.FontAwesomeSolid;
import jiconfont.swing.IconFontSwing;
import org.application.model.MenuModel;
import org.application.utility.Settings;
import org.application.view.GameFrame;
import org.application.view.MenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class MenuController {
    // Attributi
    private MenuModel model;
    private MenuView view;

    // Costruttore
    public MenuController(MenuModel model, MenuView view) {
        this.model = model;
        this.view = view;
    }

    // Metodi
    public void addListener() {
        // Listener per il bottone exit, alla pressione del bottone esce dal gioco
        view.getBtnExit().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                view.stopAnimation();
                System.exit(0);
            }
        });

        // Listener per il bottone Single Player, alla pressione del bottone lancia la modalità Single Player
        view.getBtnHumanVsIA().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(chooseSinglePlayerIA()){
                    view.stopAnimation();
                    GameFrame.singlePlayer();
                }
            }
        });
    }   // aggiunge i listener ai bottoni
    public boolean chooseSinglePlayerIA() {
        IconFontSwing.register(FontAwesomeSolid.getIconFont());
        Icon icon = IconFontSwing.buildIcon(FontAwesomeSolid.ROBOT, 40, new Color(82, 135, 172));
        Settings.iaNames = new String[] {"Palkia", "Umano"};
        return true;
    }   // permette di scegliere l'IA con cui giocare
}
