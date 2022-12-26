package com.example.snakeandladder;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.snakeandladder.SnakeLadder.tileSize;

public class Board {
    public HashMap<Integer,Integer> twistInTheGame;
    private ArrayList<Pair<Integer,Integer>> coinPositionStore;
    public Board(){
        coinPositionMapper();
        gameChanger();
    }
    private void coinPositionMapper(){
        int c=0;
        coinPositionStore=new ArrayList<>();
        coinPositionStore.add(new Pair<>(0,0));
        for(int i=(SnakeLadder.width*tileSize-(tileSize/2));i>0;i=i-tileSize){
            if(c%2==0){
             for(int j=tileSize-(tileSize/2);j<SnakeLadder.height*tileSize;j+=tileSize){
                 coinPositionStore.add(new Pair<>(j,i));
             }
        }
            else{
                for(int j=(SnakeLadder.width*tileSize-(tileSize/2));j>0;j=j-tileSize){
                    coinPositionStore.add(new Pair<>(j,i));
                }
            }
            c++;
//            i=i-40;
        }
    }
    public void gameChanger(){
        twistInTheGame=new HashMap<>();
        twistInTheGame.put(40,3);
        twistInTheGame.put(27,5);
        twistInTheGame.put(4,25);
        twistInTheGame.put(13,46);
        twistInTheGame.put(43,18);
        twistInTheGame.put(54,31);
        twistInTheGame.put(33,49);
        twistInTheGame.put(50,69);
        twistInTheGame.put(42,63);
        twistInTheGame.put(62,81);
        twistInTheGame.put(66,45);
        twistInTheGame.put(76,58);
        twistInTheGame.put(74,92);
        twistInTheGame.put(89,53);
        twistInTheGame.put(99,41);

    }
    public int getCoordinateX(int diceNumber){
//        if(twistInTheGame.containsKey(diceNumber)){
//            diceNumber= twistInTheGame.get(diceNumber);
//        }
        return coinPositionStore.get(diceNumber).getKey();
    }
    public int getCoordinateY(int diceNumber){
//        if(twistInTheGame.containsKey(diceNumber)){
//            diceNumber= twistInTheGame.get(diceNumber);
//        }
        return coinPositionStore.get(diceNumber).getValue();
    }

    public static void main(String[] args) {
        Board board=new Board();
        board.coinPositionMapper();
        for(int i=0;i<board.coinPositionStore.size();i++){
            System.out.println(i+" x="+board.coinPositionStore.get(i).getKey()+" y"+board.coinPositionStore.get(i).getValue());
        }
    }

}
