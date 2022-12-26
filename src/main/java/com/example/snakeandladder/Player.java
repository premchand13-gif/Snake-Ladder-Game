package com.example.snakeandladder;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
     private Circle coin;
     private int coinPosition;
     private String name;
     public static Board gameBoard=new Board();
     public Player(int tileSize,String name,Color pColor){
         coinPosition=1;
         coin=new Circle(tileSize/2);
         coin.setFill(pColor);
         coin.setTranslateX(20);
         coin.setStroke(Color.BLACK);
         coin.setTranslateY(380);
         this.name=name;
     }

    public Circle getCoin() {
        return coin;
    }

    public void moveCoin(int diceNumber){
         if(diceNumber+coinPosition<=100){

             coinPosition+=diceNumber;
             if(gameBoard.twistInTheGame.containsKey(coinPosition)){
                 coinPosition=gameBoard.twistInTheGame.get(coinPosition);
             }
//             coin.setTranslateX(gameBoard.getCoordinateX(coinPosition));
//             coin.setTranslateY(gameBoard.getCoordinateY(coinPosition));

              translateTransition();
         }
    }
    public void translateTransition(){
        TranslateTransition animation=new TranslateTransition(Duration.millis(1000),this.coin);
        animation.setToX(gameBoard.getCoordinateX(coinPosition));
        animation.setToY(gameBoard.getCoordinateY(coinPosition));
        animation.setAutoReverse(false);
        animation.play();
    }

    public int getCoinPosition() {
        return coinPosition;
    }

    public String getName() {
        return name;
    }
}
