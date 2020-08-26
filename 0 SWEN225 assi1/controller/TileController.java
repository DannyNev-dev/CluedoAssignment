package controller;

import model.Tile;
import model.Token;
import view.TileView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TileController {
    Tile model;
    TileView view;

    TileController(Tile aModel) {
        this.model = aModel;
        //get symbol of tile
        char symbol = model.getUnderlyingSymbol();
        String fileName = "";
        String toolTip = "";
        //get image based on symbol
        switch (symbol) {
            case '*':    //hallTile
                fileName = "hallTile.png";
                toolTip = "Tile: Hall";
                break;
            case 'K':
                fileName = "roomTile.png";
                toolTip = "Tile: Kitchen";
                break;
            case 'B':
                fileName = "roomTile.png";
                toolTip = "Tile: Ball Room";
                break;
            case 'C':
                fileName = "roomTile.png";
                toolTip = "Tile: Conservatory";
                break;
            case 'G':
                fileName = "roomTile.png";
                toolTip = "Tile: Billard Room";
                break;
            case 'Y':
                fileName = "roomTile.png";
                toolTip = "Tile: Library";
                break;
            case 'S':
                fileName = "roomTile.png";
                toolTip = "Tile: Study";
                break;
            case 'H':
                fileName = "roomTile.png";
                toolTip = "Tile: Hall";
                break;
            case 'L':
                fileName = "roomTile.png";
                toolTip = "Tile: Lounge";
                break;
            case 'D':
                fileName = "roomTile.png";
                toolTip = "Tile: Dining Room";
                break;
            default:    //invalid tile
                fileName = "invalidTile.png";
                toolTip = "Tile: Invalid";
                break;
        }
        File imageFile = new File(fileName);
        Image image;
        //read image
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("invalid image found");
            return;
        }
        this.view = new TileView(image, toolTip);   //create new view for tile
    }

    /**
     * changing the tool tip text when a token moves on or off it
     */
    void changeToolTip() {
        //get token stored on tile
        Token tileToken = model.getToken();
        if(tileToken != null) {
            switch (tileToken.getSymbol()) {
                //characters
                case 'w':   //mrs white
                    view.setTokenToolTipText("Token: Mrs White");
                    break;
                case 'g':   //mr green
                    view.setTokenToolTipText("Token: Mr Green");
                    break;
                case 'k':   //mrs peacock
                    view.setTokenToolTipText("Token: Mrs Peacock");
                    break;
                case 'p':   //prof plum
                    view.setTokenToolTipText("Token: Prof Plum");
                    break;
                case 's':   //miss scarlett
                    view.setTokenToolTipText("Token: Miss Scarlett");
                    break;
                case 'm':   //col mustard
                    view.setTokenToolTipText("Token: Col. Mustard");
                    break;
                //weapons
                case 'c':   //candlestick
                    view.setTokenToolTipText("Token: Candlestick");
                    break;
                case 'd':   //dagger
                    view.setTokenToolTipText("Token: Dagger");
                    break;
                case 'l':   //lead pipe
                    view.setTokenToolTipText("Token: Lead Pipe");
                    break;
                case 'r':   //revolver
                    view.setTokenToolTipText("Token: Revolver");
                    break;
                case 'o':   //rope
                    view.setTokenToolTipText("Token: Rope");
                    break;
                case 'a':   //spanner
                    view.setTokenToolTipText("Token: Spanner");
                    break;
            }
        } else {
            view.setTokenToolTipText("");
        }
    }

}
