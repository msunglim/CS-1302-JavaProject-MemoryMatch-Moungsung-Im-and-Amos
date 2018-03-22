/*
* Course: CS 1302
* Section: 9
* Professor:  Carlos A. Cepeda Mora
* Name: Moungsung Im, Amos Omobude
* Assignment #: JavaFXproject
*/
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;


public class JavaprojectMemoryMatchGame extends Application{
   @Override//Override the start method in the Application class
   public void start(Stage pr){
      
      BorderPane bp= new BorderPane();
      
      //Create HBox for buttons   
      HBox hb= new HBox();
      hb.setAlignment(Pos.CENTER);
      hb.setSpacing(10);
      
      //Create Welcome Meesage at the bottom of BorderPane
      Text welcomem= new Text("Welcome to play my program. This is Memory match game for the project in CS 1302.");
      bp.setBottom(welcomem);
      
      
      //home botton with Welcome Image and set the size of the image
      Button hom= new Button("Home");
      ImageView welcome= new ImageView(new Image("welcome.gif"));
      welcome.setFitHeight(600);
      welcome.setFitWidth(600);
         
      //bgml is the location of music
      String bgml="";
      try{//throw error if file doesn't exist
         bgml+=new java.io.File("memory.mp3").toURI().toURL();
      }
      catch(Exception e){
         System.out.println("File doesn't exist");
      }
      //Create Media for music
      Media bgm = new Media(bgml);
      MediaPlayer bgmp= new MediaPlayer(bgm);
      MediaView bgmv= new MediaView(bgmp);
      
      //vl1 is the location of video that introduces author 1(Moungsung Im)
      String v1l="";
      try{//throw error if file doesn't exist
         v1l+= new java.io.File("video1.mp4").toURI().toURL();}
      catch(Exception e){
         System.out.println("File doesn't exist");}
      //create Media for video
      Media v1= new Media(v1l);
      MediaPlayer vp1= new MediaPlayer(v1);
      MediaView vv1= new MediaView(vp1);
      
          //vl2 is the location of video that introduces author 2(Amos Omobude)
      String v12="";
      try{//throw error if file doesn't exist
         v12+= new java.io.File("video2.mp4").toURI().toURL();}
      catch(Exception e){
         System.out.println("File doesn't exist");}
      //create Media for video
      Media v2= new Media(v12);
      MediaPlayer vp2= new MediaPlayer(v2);
      MediaView vv2= new MediaView(vp2);
      
   
      //Create Buttons for author
      Button btau1= new Button("author1");
      
      AuthorPane bpau1= new AuthorPane(1);
      //if author button is pressed, it will delete everything (except buttons on the top) from BorderPane and set its own contents
      btau1.setOnAction(
         e-> {
             //make transition of AuthorPane
            FadeTransition ft = 
               new FadeTransition(Duration.millis(1000),bpau1.getBorderPane() );
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play(); 
            vp2.pause();// pasue video2
         
            bp.setRight(null);//delete PlayAgain button
            bgmp.pause();//pause music 
            bp.setBottom(null);//delete welcomemessage
            
            bpau1.setVideoMSI(vp1,vv1); 
            //set BorderPane that is used to place its contents in proper spaces

            bp.setCenter(bpau1.getBorderPane());    
         });
      
   
      Button btau2= new Button("author2");
      
      AuthorPane bpau2= new AuthorPane(2);
      //if author button is pressed, it will delete everything (except buttons on the top) from BorderPane and set its own contents
      btau2.setOnAction(
         e-> {
            //make transition of AuthorPane 
            FadeTransition ft = 
               new FadeTransition(Duration.millis(1000),bpau2.getBorderPane() );
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
            vp1.pause();// pasue video1
         
            bp.setRight(null);//delete PlayAgain button
            bgmp.pause();//pause music 
            bp.setBottom(null);//delete welcomemessage
            
            bpau2.setVideoAMS(vp2,vv2); 
            //set BorderPane that is used to place its contents in proper spaces
     
            bp.setCenter(bpau2.getBorderPane());    
         });
   
   
   
      //set Home Button on Action.     
      hom.setOnAction(
         e-> {
         //if home button is pressed, it will delete everything (except buttons on the top) from BorderPane and set its own contents
            bp.setRight(null);//delete PlayAgain button
            vp1.pause();// pasue video1
            vp2.pause();// pasue video2
            //Create transition for welcome message and welcome image
            FadeTransition ft = 
               new FadeTransition(Duration.millis(1000),welcomem );
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play(); 
            FadeTransition ft2 = 
               new FadeTransition(Duration.millis(1000),welcome );
            ft2.setFromValue(0.0);
            ft2.setToValue(1.0);
            ft2.play(); 
          
            //play music in home view
            bgmp.play();
            //place welcome message and welcome image in the BorderPane
            bp.setCenter(welcome); 
            bp.setBottom(welcomem);
         
         });
      
          
      //NewGame Object creates the first Game and playagain button
      NewGame makeGame= new NewGame("Play Again?");
      
      //Create game button
      Button game = new Button("Game");
      //if game button is pressed, it will delete everything (except buttons on the top) from BorderPane and set its own contents
      game.setOnAction(
         e-> { 
            
            //Game objects receives a game from makeGame class
            Game gameg= makeGame.getGame(); 
            bgmp.pause();//pause music
            vp1.pause();//pause video
            vp2.pause();// pasue video2
            
            makeGame.setGame(gameg);//set Game in NewGame object
            
            //place NewGame object 
            bp.setCenter(makeGame.getGame());
            bp.setBottom(null);//deletes welcome message 
            //place PlayAgain button
            bp.setRight(makeGame.getButton());
            
            //set PlayAgain button on action
            makeGame.getButton().setOnAction(
               e3-> {
                   //remove the current game and replace it with new game
                  bp.getChildren().remove(gameg);
                  bp.setCenter(makeGame.getNewGame()); //NewGame object creates new game
               });
            //set fadetransition for game
            FadeTransition ft = 
               new FadeTransition(Duration.millis(1000),gameg );
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play(); 
         });
      
      //HBox contains all the buttons 
      hb.getChildren().addAll(hom,btau1,btau2,game);
      //set HBox at Top of the BorderPane
      bp.setTop(hb);
      bp.setPadding(new Insets(20,20,20,20));
      
      //setWelcome message at Bottom of the BorderPane
      bp.setCenter(welcome);
      //play music
      bgmp.play();
      //set Title of the BorderPane
      pr.setTitle("JAVAPROJECT");
      //place BorderPane in new Scene
      pr.setScene(new Scene(bp,1000,800));
      pr.show();
   }
   
   //main method of the classes
   public static void main(String [] args){
      launch(args);}
}

//Game Object creates a memory game
class Game extends GridPane{
   private int imagename= 1,setted=0;
   private int mytime=1000;
   private ImageViewEX [][]list= new ImageViewEX [4][6]; //it holds where an image is placed
   private ImageViewEX ob= new ImageViewEX();
 //  private int point=0;
   
   //no arg constructor
   public Game(){
      //set answer images
      setBackground();
      setAlignment(Pos.CENTER);
      setPadding(new Insets(10,10,10,10));
      setHgap(10);
      setVgap(10);
      
      //set count for ImageViewEX because the user hasn't opened a card yet
      ob.setCount(0);
      
      //set time for time left. 
      int mytime=3;
      String mytext= ""+mytime;
      
      //generate TextEX object to show time left. mytext will be used to set value in Text.
      TextEX obt= new TextEX(mytext);
      //place obt at Game object. obt.getText() will return Text value.
      add(obt.getTextt(),9,0);
      
      //set EventHandler for time left before the program hides the answers
      EventHandler<ActionEvent> eventHandler2 = 
         es2->{
            //remove the Text object that is created 1 second ago and replace it with the current time left
            getChildren().remove(obt.getTextt());
            //decrease mytime
            obt.incMinus();
            //receive new Text for time left
            obt.setTextt(""+(mytime-obt.getTimeLeft()));
            //if it's time over, it doesn't show 0 and remove the object from the gridpane of this class.
            if(mytime-obt.getTimeLeft()!=0){
               add(obt.getTextt(),9,0);}
         };
      //set Duration to decrease my count for every 1 second and CycleCount with mytime
      Timeline ani2= new Timeline(new KeyFrame(Duration.millis(1000),eventHandler2));
      ani2.setCycleCount(mytime);
      ani2.play();
       
      //create TextEX object to generate Text for Score and place it to Game
      TextEX pointDesc= new TextEX("SCORE:");
      add(pointDesc.getTextt(),9,4);
      
      //create TextEX object (point) to generate Text for the amount of score that the user gains per getting correct answers and loses per getting wrong answers 
      TextEX point= new TextEX(""+0);
      //place it next to pointDesc
      add(point.getTextt(),10,4);
      
      //set EventHandler to hide answers
      EventHandler<ActionEvent> eventHandler = 
         es->{
            
            //create IamgeViewEX array to have informations of which hides the answers (back surface of answers)
            ImageViewEX [][] blist = new ImageViewEX[4][6];
         
           
            for (int i=0; i<4; i++){
               for(int j=0; j<6;j++){
                  //ImageViewEX bl sets new ImageView with its x value and y value (location)
                  ImageViewEX bl= new ImageViewEX("bla.png",j,i); //"bla.png" is made for back surface of answers
                  
                  //ivlist holds an answer for same location with its back surface
                  ImageViewEX ivlist= list[i][j];
                  
                  //set size of back surface of answer
                  bl.getImageView().setFitHeight(100); 
                  bl.getImageView().setFitWidth(100);
                  
               
                  blist[i][j]= bl;
                  //receive ImageView from bl object and place it with x and y value
                  add( bl.getImageView(),j,i);
                  //receive ImageView from blist and set it on MouseClick
                  blist[i][j].getImageView().setOnMouseClicked(
                     e->     {
                        //send the answers and back surface of it to ImageViewEX ob object to set first image and second image, and then compare them. in case of wrong answer, it saves the bl.
                        ob.setHandle(ivlist,bl);
                        
                        //create Fadetransition for back surface of answer.
                        FadeTransition ft = 
                           new FadeTransition(Duration.millis(100), bl.getImageView());
                        ft.setFromValue(1.0);
                        ft.setToValue(0.0);
                        ft.play(); 
                        
                        //ob.getReady() returns boolean value. if the user has chosen one image, it returns false. if the user has chosen two images, it returns true. 
                        if(ob.getReady()){
                           //ob.compare() returns boolean value. ob.getset1 and 2 returns ImageViewEX values. ob.compare() receives two ImageViewEX and get their URL(String value) and then compare the String value(URL).
                           if(ob.compare(ob.getset1(),ob.getset2())){     
                             
                              //remove the back surfaces of images            
                              getChildren().remove( ob.getRemember().getImageView());//ob.getRemember() returns ImageViewEX bl which is located at the same place with the image the user had chosen at the first time. 
                              getChildren().remove(bl.getImageView());
                              
                              //remove the point, increase it, and then replace it with new point that the user gains
                              getChildren().remove(point.getTextt());
                              point.incPoint();
                              point.setTextt(""+point.getPoint());
                              add(point.getTextt(),10,4);
                           
                           }
                           else
                           {
                              
                              
                              //set fadetransitions to hide the images 
                              
                              //fadetransition for the image that the user had chosen at the fisrt time
                              FadeTransition ft3 = 
                                 new FadeTransition(Duration.millis(1000), ob.getRemember().getImageView());
                              ft3.setFromValue(0.0);
                              ft3.setToValue(1.0);
                              ft3.play(); 
                              
                              //fadetransition for the image that the user has chosen in the second time
                              FadeTransition ft2 = 
                                 new FadeTransition(Duration.millis(1000), bl.getImageView());
                              ft2.setFromValue(0.0);
                              ft2.setToValue(1.0);
                              ft2.play(); 
                              
                              ob.getRemember().getImageView().setDisable(false);//allow the user click previous image in future guess
                              //remove the point, decrease it, and then replace it with new point that the user loses.
                              getChildren().remove(point.getTextt());
                              point.decPoint();
                              point.setTextt(""+point.getPoint());
                              add(point.getTextt(),10,4);
                           }
                        }
                        else{// not allow the user click the same image in the second guess
                           bl.getImageView().setDisable(true);}
                     });
               
               }
            }
         
         };
   
      //set Duration and CycleCount.
      Timeline ani= new Timeline(new KeyFrame(Duration.millis(3000),eventHandler));
      ani.setCycleCount(0);
      ani.play();
   
   }
  
   //generate random Answers 
   public void setBackground(){
      //imagename will be the file name from 1.png to 13.png
      while (imagename<13){
         //set images twice per an image to have a pair.
         while (setted<2){
            int r= (int)(Math.random()*4);
            int c= (int)(Math.random()*6);
            
            //place image in where other images are not placed yet.
            if(list[r][c]==null){
               
               //ImageViewEX iv makes new ImageView variable with its x value and y value in Game.
               ImageViewEX iv= new ImageViewEX(imagename+".png",c,r);
               //receive imageview from ImageViewEX object and set size of it
               iv.getImageView().setFitWidth(100);
               iv.getImageView().setFitHeight(100);
               //add value to list so list[r][c] is not null anymore
               list[r][c]=iv;
               //receive imageview from ImageViewEX object and place it in Game
               add( iv.getImageView(),c,r);
               setted++;
            }
         }
         //after an image is placed in two different places, increase imagename to call the next Image and reset setted for the next Image
         if(setted==2){
            setted=0; imagename++;
         }
               
      }
   }
  
}

//NewGame is made to create new Game.     
class NewGame extends Button{
   private Button bt;
   private Game p=new Game();;
   private boolean first=true;
   
   //receive String name to set Button bt's name
   public NewGame(String name){
      bt= new Button(name);
   }
   //return Button    
   public Button getButton(){
      return bt;}
   
   //receive Game value to set Game p's Game. this is very important because if PlayAgain is clicked in main class, it will assign new Game to Game p, else it will assign same Game to Game p. 
   public void setGame(Game p){
      this.p=p;
   }
   //generate new Game and return Game p
   public GridPane getNewGame(){
      p=new Game();
      return p;}
   
   //generate new Game and return p.    
   public Game getGame(){
      //first is true before the user click Game Button in main class. After the user has clicked Game Button once, it will be false.
      if(first){
         first=false;
         return new Game();}
      else{//return the current Game
         return p;}
   }
}
//ImageViewEX is made to contain URL and location of an Image, count for the number of amount that the user clicks the back surface of an answer, set the first answer and second answer,save the ImageViewEX value of bl, and then compare them. 
class ImageViewEX extends ImageView{
   
   private ImageView iv;
   private ImageViewEX set1;
   private ImageViewEX set2; 
   private static int count=0;
   private String url;
   private int x;
   private int y;
   private boolean ready=false;
   private ImageViewEX blfirst;
   
   //no arg constructor
   public ImageViewEX(){
   }
   
   //constructor with String url. initialize ImageView iv and String url. 
   public ImageViewEX( String url){
      iv= new ImageView(new Image(url));
      this.url=url;
   }
   
   //constructor with String url and two integers x and y. initialize ImageView iv, String url, x, and y
   public ImageViewEX( String url,int x, int y){
      iv= new ImageView(new Image(url));
      this.url=url;
      this.x= x;
      this.y= y;
   }
   
   //return ImageView iv
   public ImageView getImageView(){
      return iv;}
   //Return String url
   public String getURL(){
      return url;}
   //return int x   
   public int getXv(){
      return x;}
   //return int y   
   public int getYv(){
      return y;}
   
   //initialize ImageViewEX set1 and set2 with answers.
   public void setbl1(ImageViewEX iv1){
      //if it is the first guess, initialize set1 and increase count. ready is false because it is not ready to compare two images 
      if(count==0){
         set1=iv1; count++;
         ready=false;}
   }
   //if it is the second guess, initialize set2 and increase count to prevent more initialization. ready is true because it is ready to compare two images
   public void setbl2(ImageViewEX iv2){
      if(count==1){
         count++;
         set2=iv2;
         ready=true;
      } 
   }
   //return int count
   public int getCount(){
      return count;}
   //recieve intenger value and set count
   public void setCount(int n){
      count=n;
   }
   
   //receive an answer and bl from the main class. 
   public void setHandle(ImageViewEX iv,ImageViewEX blfirst){
      
      if(count==0){//if it is first guess, initialize blfirst for case of wrong answer
         setbl1(iv);
         setRemember(blfirst);
      }
      else if(count==1){
         setbl2(iv);
         count=0; //if it is second guess, assign 0 to count for future guess.
      }   
   }
   //return boolean ready
   public boolean getReady(){
      return ready;}
   //return ImageViewEX set1 and 2
   public ImageViewEX getset1(){
      return set1;}
   public ImageViewEX getset2(){
      return set2;}
   
   //compare ImageViewEX set1 and 2 with its URL and then return boolean value
   public boolean compare(ImageViewEX set1, ImageViewEX set2){
      if(set1.getURL().equals(set2.getURL())){// if set1 and set2 have same URL
         return true;}
      else{
      
         return false;
      }
   }
   //initialize blirst with bl for case of wrong answer
   public void setRemember(ImageViewEX blfirst)
   { this.blfirst=blfirst;}
   //return ImageViewEX blfirst for case of wrong answer
   public ImageViewEX getRemember(){
      return blfirst;}
   
}
//TextEX is made to manage time left and score that the user earns.
class TextEX extends Text{
   private Text mytext;
   private int pointearn=0;
   private int minus=0;
   
   //no args constructor
   TextEX(){
   }
   //constructor with String t. initialize Text mytext
   TextEX(String t){
      mytext=new Text(t);
   }
   //return Text mytext
   public Text getTextt(){
      return mytext;}
   //assign new value to mytext
   public void setTextt(String t){
      mytext=new Text(t);}
   //increase integer minus 
   public void incMinus(){
      minus++;}
   //return integer minus   
   public int getTimeLeft(){
      return minus;}
   //increase integer pointearn by 10   
   public void incPoint(){
      pointearn+=10;}
   //decrease integer pointearn by 10 
   public void decPoint(){
      pointearn-=10;}
   //return integer pointear   
   public int getPoint(){
      return pointearn;
   }
         
}
//AuthorPane has informations about the two authors of the program
class AuthorPane extends BorderPane{
   BorderPane bpau;
   
   //receive integer value and if it is 1, generate author 1's information else if it is 2, generate author 2's information. there is no more authors than 2.
   public AuthorPane(int n){
      bpau= new BorderPane();
           
      if(n==1){
         setMSI();
      }   
      if(n==2){
         setAMS();}
            //set the size of video and play
            
            
   }
   
   //input author1's information into the pane
   public void setMSI(){
           
      Image MSI= new Image("MoungsungIm.png");
      ImageView iv1=new ImageView(MSI);
      iv1.setFitHeight(150);
      iv1.setFitWidth(150);
            //create VBox to contain Image and Labels on the top of the BorderPane       
      VBox vb= new VBox(5);//vertical space between one another 
      vb.getChildren().add(iv1);        
            
            //spacing and align            
      vb.setPadding(new Insets(10,10,10,10));
      vb.setAlignment(Pos.CENTER);
      vb.setStyle("-fx-background-color: skyblue");
         //create Label and set size and font of Label in order of name,education stuatus, and hoblfirsty    
      Label [] l= { new Label("Moungsung Im"),new Label("Educational status:   Bachelors degree: Computer Science "), new Label("Hobby:   Playing games with friends, drawing, and studying Programming"), new Label("Nationality:   Republic of Korea")};
         
      for( Label inform : l){
         inform.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
            
         VBox.setMargin(inform, new Insets(1,1,1,10));
         vb.getChildren().add(inform);
      }
      bpau.setTop(vb);
   }
           
    //input author2's information into the pane         
   public void setAMS(){
      Image AMS= new Image("Amos Omobude.jpg");
      ImageView iv2=new ImageView(AMS);
      iv2.setFitHeight(150);
      iv2.setFitWidth(150);            
            //create VBox to contain Image and Labels on the top of the BorderPane       
      VBox vb= new VBox(5);//vertical space between one another 
      vb.getChildren().add(iv2);        
            
            //spacing and align            
      vb.setPadding(new Insets(10,10,10,10));
      vb.setAlignment(Pos.CENTER);
      vb.setStyle("-fx-background-color: yellow");
        //create Label and set size and font of Label in order of name,education stuatus, and hoblfirsty
   
      Label [] l= { new Label("Amos Omobude"),new Label("Educational status:   Bachelors degree: Computer Science "), new Label("Hobby:  AMOS HB"), new Label("Nationality:   USA")};
         
      for( Label inform : l){
         inform.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
            
         VBox.setMargin(inform, new Insets(1,1,1,10));
         vb.getChildren().add(inform);
      }
      bpau.setTop(vb);
   }
           
   //receive and play author1's introducing video
   public void setVideoMSI(MediaPlayer vp1, MediaView vv1){
      vv1.setFitWidth(400);
      vv1.setFitHeight(500);
      vv1.setRotate(-90);
      vp1.play();
      
      //place review button for the case if the user wants to play the video from the beginning 
      Button bg= new Button("Review the video");
      bg.setOnAction(eg-> {vp1.seek(Duration.ZERO);});
      bpau.setCenter(vv1);
      bpau.setRight(bg);
   
      bpau.setCenter(vv1);
   }
   //receive and play author2's introducing video
   public void setVideoAMS(MediaPlayer vp2, MediaView vv2){
      vv2.setFitWidth(400);
      vv2.setFitHeight(500);
      vv2.setRotate(-90);
      vp2.play();
      
      //place review button for the case if the user wants to play the video from the beginning 
      Button bg= new Button("Review the video");
      bg.setOnAction(eg-> {vp2.seek(Duration.ZERO);});
      bpau.setCenter(vv2);
      bpau.setRight(bg);
      
   }
   
   //return pane
   public BorderPane getBorderPane(){
      return bpau;}
}
