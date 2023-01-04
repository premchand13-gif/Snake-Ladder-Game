package com.example.snakeandladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.io.IOException;


public class SnakeLadder extends Application {
   public static final int tileSize=40,height=10,width=10;
   public int diceNumber;
   public int playerOneTrigger=0;
    public int playerTwoTrigger=0;
    Label globalPlayerOne=new Label("");
    Label globalPlayerTwo=new Label("");
   public boolean playerOneAccess=false,playerTwoAccess=false,startGame=true;
    //creating random number generator function for rolling dice
    public void randNumberGenerator(){
        diceNumber= (int)(Math.random()*6+1);

    }
    Pane createContent(){
        Pane root=new Pane();
        // Setting grid of size equal to board
        root.setPrefSize(width*tileSize,height*tileSize+100);
        for(int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                Tile tile=new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().addAll(tile);
            }
        }
        //Start button functionality
        Button startButton=new Button("START");
        startButton.setTranslateX(150);
        startButton.setTranslateY(height*tileSize+60);
        startButton.setTextFill(Color.GREEN);
        Label wildCardLabel=new Label("WildCard->56");

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(startGame){
                    playerOneAccess=true;
                    startGame=false;
                }
            }
        });

        // Setting Players coin
        Player playerOne=new Player(tileSize,"Prem", Color.BLUE);
        Player playerTwo=new Player(tileSize-10,"Yasin",Color.RED);

        //create dice label
        Label diceLabel=new Label("Start Game");
        diceLabel.setTextFill(Color.RED);
        diceLabel.setTranslateX(150);
        diceLabel.setTranslateY(height*tileSize+10);
        wildCardLabel.setTranslateX(150);
        wildCardLabel.setTranslateY(height*tileSize+30);

        // Players button option
        Button player1=new Button("Player one");
        player1.setTranslateY(height*tileSize+10);
        player1.setTranslateX(20);
        Label player1Score=new Label(playerOne.getName()+" : "+playerOne.getCoinPosition());
        player1Score.setTranslateY(height*tileSize+50);
        player1Score.setTranslateX(20);
        Label player2Score=new Label(playerTwo.getName()+" : "+playerTwo.getCoinPosition());
        player2Score.setTranslateX(300);
        player2Score.setTranslateY(height*tileSize+50);
        player1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(playerOneAccess) {
                    randNumberGenerator();
                    diceLabel.setText("DiceNumber : " + diceNumber);
                    playerOne.moveCoin(diceNumber,playerOneTrigger);
                    player1Score.setText( playerOne.getName()+" : "+playerOne.getCoinPosition());
                   playerOneAccess=false;
                   playerTwoAccess=true;
                   if(playerOne.getCoinPosition()==100||playerOne.getCoinPosition()==56){
                       playerOneTrigger=1;
                       globalPlayerOne.setText("Reached Half Path");
                       globalPlayerOne.setTextFill(Color.GREEN);
                       diceLabel.setText(playerOne.getName()+" Reached Top!");
                   }
                   if(playerOne.getCoinPosition()==1&& playerOneTrigger==1){
                       playerTwoAccess=false;
                       globalPlayerOne.setText("");
                       diceLabel.setText(playerOne.getName()+" Won..!");
                       diceLabel.setTextFill(Color.GREEN);
                   }
                }
            }
        });
        Button player2=new Button("Player Two");
        player2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(playerTwoAccess) {
                    randNumberGenerator();
                    diceLabel.setText("DiceNumber : " + diceNumber);
                    playerTwo.moveCoin(diceNumber,playerTwoTrigger);
                    player2Score.setText(playerTwo.getName()+" : "+playerTwo.getCoinPosition());
                    playerOneAccess=true;
                    playerTwoAccess=false;
                    if(playerTwo.getCoinPosition()==100||playerTwo.getCoinPosition()==56){
                        playerTwoTrigger=1;
                        globalPlayerTwo.setText("Reached Half Path");
                        diceLabel.setText(playerTwo.getName()+" Reached Top!");
                        diceLabel.setTextFill(Color.GREEN);
                    }
                    if(playerTwoTrigger==1&&playerTwo.getCoinPosition()==1){
                        playerOneAccess=false;
                        globalPlayerTwo.setText("");
                        diceLabel.setText(playerTwo.getName()+" Won..!");
                        diceLabel.setTextFill(Color.GREEN);
                    }

                }
            }
        });
        player2.setTranslateY(height*tileSize+10);
        player2.setTranslateX(300);
        globalPlayerOne.setTranslateX(20);
        globalPlayerOne.setTranslateY(height*tileSize+70);
        globalPlayerTwo.setTranslateX(300);
        globalPlayerTwo.setTranslateY(height*tileSize+70);
        Button reStart =new Button("Restart");
        reStart.setTextFill(Color.RED);
        reStart.setTranslateY(height*tileSize+60);
        reStart.setTranslateX(220);
        reStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                playerTwoTrigger=0;
                playerOneTrigger=0;
                startGame=true;
                playerOneAccess=false;
                playerTwoAccess=false;
                globalPlayerTwo.setText("");
                globalPlayerOne.setText("");
                 playerOne.setCoinPosition(1);
                 player1Score.setText(playerOne.getName()+" : "+playerOne.getCoinPosition());
                 playerOne.moveCoin(0,0);
                 playerTwo.setCoinPosition(1);
                 player2Score.setText(playerTwo.getName()+" : "+playerTwo.getCoinPosition());
                 playerTwo.moveCoin(0,0);
            }
        });


        // Setting Background
        Image img=new Image("C:\\Users\\Admin\\IdeaProjects\\SnakeAndLadder\\src\\main\\SnakeLadderBoard12Nov.jpg");
        ImageView board=new ImageView();
        board.setImage(img);
        board.setFitHeight(tileSize*height);
        board.setFitWidth(tileSize*width);
        root.getChildren().addAll(board,diceLabel,reStart,wildCardLabel,player1Score,player2Score,globalPlayerTwo,globalPlayerOne,player1,player2,playerOne.getCoin(),playerTwo.getCoin(),startButton);
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake&Ladder!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}