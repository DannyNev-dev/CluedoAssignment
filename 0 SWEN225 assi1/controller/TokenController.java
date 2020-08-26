package controller;

import model.Token;
import view.TileView;
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
                fileName = "white.png";
                break;
            case 'g':   //mr green
                fileName = "green.png";
                break;
            case 'k':   //mrs peacock
                fileName = "peacock.png";
                break;
            case 'p':   //prof plum
                fileName = "plum.png";
                break;
            case 's':   //miss scarlett
                fileName = "scarlett.png";
                break;
            case 'm':   //col mustard
                fileName = "mustard.png";
                break;
            //weapons
            case 'c':   //candlestick
                fileName = "CANDLE.png";
                break;
            case 'd':   //dagger
                fileName = "KNIFE.png";
                break;
            case 'l':   //lead pipe
                fileName = "PIPE.png";
                break;
            case 'r':   //revolver
                fileName = "GUN.png";
                break;
            case 'o':   //rope
                fileName = "ROPE.png";
                break;
            default:    //spanner
                fileName = "WRENCH.png";
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
