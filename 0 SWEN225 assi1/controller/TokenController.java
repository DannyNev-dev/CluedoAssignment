package controller;

import model.Token;
import view.TokenView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TokenController {
    Token model;
    TokenView view;

    public TokenController(Token aModel) {
        this.model = aModel;
        //get symbol of token
        char symbol = model.getSymbol();
        String fileName = "";
        switch (symbol) {
            //characters
            case 'w':   //mrs white
                fileName = "charTiles//whiteToken.png";
                break;
            case 'g':   //mr green
                fileName = "charTiles//greenToken.png";
                break;
            case 'k':   //mrs peacock
                fileName = "charTiles//peacockToken.png";
                break;
            case 'p':   //prof plum
                fileName = "charTiles//plumToken.png";
                break;
            case 's':   //miss scarlett
                fileName = "charTiles//scarlettToken.png";
                break;
            case 'm':   //col mustard
                fileName = "charTiles//mustardToken.png";
                break;
            //weapons
            case 'c':   //candlestick
                fileName = "WeaponTiles//CANDLE.png";
                break;
            case 'd':   //dagger
                fileName = "WeaponTiles//KNIFE.png";
                break;
            case 'l':   //lead pipe
                fileName = "WeaponTiles//PIPE.png";
                break;
            case 'r':   //revolver
                fileName = "WeaponTiles//GUN.png";
                break;
            case 'o':   //rope
                fileName = "WeaponTiles//ROPE.png";
                break;
            default:    //spanner
                fileName = "WeaponTiles//WRENCH.png";
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
        this.view = new TokenView(image);   //create new view for token
    }
}
