package com.example.demo;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelloController2 {

    @FXML
    private Button Player1com;

    @FXML
    private Button Player2com;

    @FXML
    private Button Start_Gamecom;

    @FXML
    private ImageView roller;

    @FXML
    private ImageView token_1com;

    @FXML
    private ImageView token_2com;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private int player1_pos_on_board = 0;

    @FXML
    private int player2_pos_on_board = 0;

    @FXML
    public boolean player1_unlock = false;

    @FXML
    public boolean player2_unlock = false;

    @FXML
    private int dice_result;

    private boolean check_computer_status = false;

    @FXML
    private ImageView exit;

    private Stage stage;

    private Scene scene;



    public static int[] coordinate_xaxis = {80,160,240,320,400,480,560,640,720,800,800,720,640,560,480,400,320,240,160,80,80,160,240,320,400,480,560,640,720,800,800,720,640,560,480,400,320,240,160,80,80,160,240,320,400,480,560,640,720,800,800,720,640,560,480,400,320,240,160,80,80,160,240,320,400,480,560,640,720,800,800,720,640,560,480,400,320,240,160,80,80,160,240,320,400,480,560,640,720,800,800,720,640,560,480,400,320,240,160,80};
    public static int[] coordinate_yaxis = {777,777,777,777,777,777,777,777,777,777,697,697,697,697,697,697,697,697,697,697,617,617,617,617,617,617,617,617,617,617,537,537,537,537,537,537,537,537,537,537,457,457,457,457,457,457,457,457,457,457,377,377,377,377,377,377,377,377,377,377,297,297,297,297,297,297,297,297,297,297,217,217,217,217,217,217,217,217,217,217,137,137,137,137,137,137,137,137,137,137,57,57,57,57,57,57,57,57,57,57};

    public static int[] snake_head ={17,64,89,95,99};
    public static int[] snake_tail ={7,60,26,75,78};

    public static int[] ladder_start = {4, 9, 20, 28, 40, 51, 63};
    public static int[] ladder_end = {14, 31, 38, 84, 59, 67, 81};

    @FXML
    void Roll1com(MouseEvent event) throws InterruptedException {
        Player1com.setDisable(false);
        File rol = new File("src/main/resources/com/example/demo/dice-roll.gif");
        roller.setImage(new Image(rol.toURI().toString()));

        //add the waiting time

        dice_result = Randomnumber.Randomnumber();
        File file = new File("src/main/resources/com/example/demo/dice "+dice_result+".PNG");
        roller.setImage(new Image(file.toURI().toString()));
        Player1com.setDisable(true);
        Player2com.setDisable(false);
        move_token1com(event);
    }

    @FXML
    void Roll2com() throws InterruptedException {
        Player2com.setDisable(false);
        File rol = new File("src/main/resources/com/example/demo/dice-roll.gif");
        roller.setImage(new Image(rol.toURI().toString()));

        //add the waiting time

        dice_result = Randomnumber.Randomnumber();
        File file = new File("src/main/resources/com/example/demo/dice "+dice_result+".PNG");
        roller.setImage(new Image(file.toURI().toString()));
        Player2com.setDisable(true);
        Player1com.setDisable(false);
        move_token2com();
    }

    @FXML
    public void game_startedcom(MouseEvent mouseEvent) {
        Start_Gamecom.setText("Game Started");
        exit.setDisable(false);
        Player1com.setDisable(false);
        Start_Gamecom.setDisable(true);
    }

    public void move_token1com(MouseEvent mouseEvent) throws InterruptedException {
        if((player1_unlock==false) && (dice_result==6)){
            player1_unlock = true;
            Player1com.setDisable(false);
            Player2com.setDisable(true);
            return;
        }

        if((player1_unlock) && (dice_result==6) && (player1_pos_on_board==0)){
            player1_pos_on_board = player1_pos_on_board + dice_result;
            token_1com.setLayoutX(coordinate_xaxis[player1_pos_on_board-1]);
            token_1com.setLayoutY(coordinate_yaxis[player1_pos_on_board-1]);
            Player1com.setDisable(false);
            Player2com.setDisable(true);
            return;
        }

        if((player1_unlock) && ((player1_pos_on_board+dice_result)<101)){
            player1_pos_on_board = player1_pos_on_board + dice_result;
            token_1com.setLayoutX(coordinate_xaxis[player1_pos_on_board-1]);
            token_1com.setLayoutY(coordinate_yaxis[player1_pos_on_board-1]);

            for(int i =0; i<snake_head.length;i++){
                if(player1_pos_on_board==snake_head[i]){




                    player1_pos_on_board = snake_tail[i];
                    token_1com.setLayoutX(coordinate_xaxis[player1_pos_on_board-1]);
                    token_1com.setLayoutY(coordinate_yaxis[player1_pos_on_board-1]);
                }
            }
            for(int i =0; i<ladder_start.length;i++){
                if(player1_pos_on_board==ladder_start[i]){




                    player1_pos_on_board = ladder_end[i];
                    token_1com.setLayoutX(coordinate_xaxis[player1_pos_on_board-1]);
                    token_1com.setLayoutY(coordinate_yaxis[player1_pos_on_board-1]);
                }
            }

            if(dice_result==6){
                Player1com.setDisable(false);
                Player2com.setDisable(true);
            }

            if(player1_pos_on_board==100){
                File file = new File("src/main/resources/com/example/demo/p1.png");
                image1.setImage(new Image(file.toURI().toString()));
                image1.setDisable(false);

                File file1 = new File("src/main/resources/com/example/demo/win 2.gif");
                image2.setImage(new Image(file1.toURI().toString()));
                image2.setDisable(false);

                Player1com.setDisable(true);
                Player2com.setDisable(true);

                DropShadow drop = new DropShadow();
                image1.setEffect(drop);
                image2.setEffect(drop);
                drop.setBlurType(BlurType.THREE_PASS_BOX);
                drop.setWidth(220.31);
                drop.setHeight(215.92);
                drop.setRadius(108.56);
                drop.setSpread(0.46);

            }
        }
        //check_computer_status = true;
        Roll2com();

    }



    public void move_token2com() throws InterruptedException {
        if((player2_unlock==false) && (dice_result==6)){
            player2_unlock = true;
            Player2com.setDisable(false);
            Player1com.setDisable(true);
            return;
        }

        if((player1_unlock) && (dice_result==6) && (player2_pos_on_board==0)){
            player2_pos_on_board = player2_pos_on_board + dice_result;
            token_2com.setLayoutX(coordinate_xaxis[player2_pos_on_board-1]);
            token_2com.setLayoutY(coordinate_yaxis[player2_pos_on_board-1]);
            Player2com.setDisable(false);
            Player1com.setDisable(true);
            return;
        }

        if((player2_unlock) && ((player2_pos_on_board+dice_result)<101)){
            player2_pos_on_board = player2_pos_on_board + dice_result;
            token_2com.setLayoutX(coordinate_xaxis[player2_pos_on_board-1]);
            token_2com.setLayoutY(coordinate_yaxis[player2_pos_on_board-1]);

            for(int i =0; i<snake_head.length;i++){
                if(player2_pos_on_board==snake_head[i]){



                    player2_pos_on_board = snake_tail[i];
                    token_2com.setLayoutX(coordinate_xaxis[player2_pos_on_board-1]);
                    token_2com.setLayoutY(coordinate_yaxis[player2_pos_on_board-1]);

                }
            }
            for(int i =0; i<ladder_start.length;i++){
                if(player2_pos_on_board==ladder_start[i]){



                    player2_pos_on_board = ladder_end[i];
                    token_2com.setLayoutX(coordinate_xaxis[player2_pos_on_board-1]);
                    token_2com.setLayoutY(coordinate_yaxis[player2_pos_on_board-1]);
                }
            }

            if(dice_result==6){
                Player2com.setDisable(false);
                Player1com.setDisable(true);
            }

            if(player2_pos_on_board==100){
                File file = new File("src/main/resources/com/example/demo/p2.png");
                image1.setImage(new Image(file.toURI().toString()));
                image1.setDisable(false);

                File file1 = new File("src/main/resources/com/example/demo/win 2.gif");
                image2.setImage(new Image(file1.toURI().toString()));
                image2.setDisable(false);

                Player1com.setDisable(true);
                Player2com.setDisable(true);

                DropShadow drop = new DropShadow();
                image1.setEffect(drop);
                image2.setEffect(drop);
                drop.setBlurType(BlurType.THREE_PASS_BOX);
                drop.setWidth(220.31);
                drop.setHeight(215.92);
                drop.setRadius(108.56);
                drop.setSpread(0.46);

            }
        }
    }

    public void exit_buttoncom(MouseEvent mouseEvent) throws IOException {

        Start_Gamecom.setText("Start Game");
        File rol = new File("src/main/resources/com/example/demo/dice-roll.gif");
        roller.setImage(new Image(rol.toURI().toString()));

        Start_Gamecom.setDisable(true);
        Player1com.setDisable(true);
        Player2com.setDisable(true);
        player1_pos_on_board = 0;
        player2_pos_on_board = 0;
        token_1com.setLayoutX(13);
        token_1com.setLayoutY(740);
        token_2com.setLayoutX(13);
        token_2com.setLayoutY(795);
        player1_unlock = false;
        player2_unlock = false;

        PerspectiveTransform per = new PerspectiveTransform();
        image1.setEffect(per);
        image2.setEffect(per);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Start_interface.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
}


//class Randomnumber2 {
//    public static int Randomnumber(){
//        Random rand = new Random();
//        int code1 = rand.nextInt(6)+1;
//        return code1;
//    }
//
//}