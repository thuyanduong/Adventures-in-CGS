import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SideScroller extends Game
{
  public static final int TILE_SIZE = 64;
  public static boolean isGameOver = false;
  public static boolean isAtTitleScreen = true;
  private String currentLevelPath;

  private Map gameMap;
  private Tile tileType;
  private Sprite background;
  private Camera camera;
  //private AudioPlayer audioPlayer;
  private javax.sound.sampled.Clip bgMusic;
  private String name = "Dalton";
  private PlayerCharacter character;
  private Collider collider;
  private ArrayList paralaxBackgroundImages;
  private ArrayList enemies;
  private ArrayList goals;
  public SideScroller()
  {
    super();
    Tiles.initialize(spriteLoader);
  }

  //Load all content into memory in this method.
  protected void loadContent()
  {	name=JOptionPane.showInputDialog("Which character would you like to be?");
    //audioPlayer = new AudioPlayer();
    gameMap = new Map(spriteLoader,30,10);
    camera = new Camera(0,0,800,600);
    background = spriteLoader.load("../Content/Textures/Backgrounds/cave2.png");
    AudioPlayer.play("../Content/Audio/level4.wav", true);
    //Main Character
    AnimatedSprite anime = new AnimatedSprite(200f,true, true);
    anime.addFrame(spriteLoader.load("../Content/Textures/Characters/" + name + "/1.png"));
    anime.addFrame(spriteLoader.load("../Content/Textures/Characters/" + name + "/2.png"));
    anime.addFrame(spriteLoader.load("../Content/Textures/Characters/" + name + "/3.png"));
    anime.addFrame(spriteLoader.load("../Content/Textures/Characters/" + name + "/4.png"));
    anime.addFrame(spriteLoader.load("../Content/Textures/Characters/" + name + "/jump.png"));
    anime.addFrame(spriteLoader.load("../Content/Textures/Characters/" + name + "/stand.png"));

    anime.addAnimation("walk",new int[] {0,1,2,1});
    anime.addAnimation("stand",new int[] {5});
    anime.addAnimation("dead",new int[] {4});

    character = new PlayerCharacter("Guy", anime,196,400,1);

    //gameMap.save("testmap.txt");

    gameMap = Map.load(spriteLoader, "../Data/Level 1.txt");
    loadLevel("../Data/Level 1_level.txt");
    this.currentLevelPath = "Level 1";
    collider = new Collider();

  }

  int count = 0;
  //Add Update (Game Logic) to this method
  protected void update()
  {



    count++;
    if (SideScroller.isAtTitleScreen)
    {
      if (input.isKeyPressed(Keys.Space))
        SideScroller.isAtTitleScreen = false;
      else
    	if(input.isKeyPressed(Keys.A))
    	{Runtime load = Runtime.getRuntime();

    		String program = "~/Applications/TextEdit.app";
    	String file = "../Data/About.txt";
    	try {
			load.exec(program + " " + file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}





    	}
    	else
    		return;
    }
    else if (SideScroller.isGameOver)
    {
      if (input.isKeyDown(Keys.Space))
      {
        SideScroller.isGameOver = false;
        SideScroller.isAtTitleScreen = true;
        //currentLevelPath="Level 1";
        loadLevel("../Data/"+this.currentLevelPath+"_level.txt");
        character.moveTo(64,400);
        camera.setPosition(0,0);
        character.lives = 3;
        character.score = 0;
      }
    }
    character.move(0,10);

    character.update(gameTime, camera, input);

    if (character.isAlive()) camera.follow(character);

    collider.collide(character,gameMap);

    for(int i = 0; i < enemies.size(); i++)
    {
      AICharacter enemy = ((AICharacter)enemies.get(i));
      enemy.move(0,10);
      collider.collide(enemy,gameMap);
      enemy.update(gameTime,camera);
      if (character.isAlive() && enemy.isAlive())
      {
        if (collider.collide(character,enemy) == 1)
        {
          enemy.die();
          character.score+= 100;
        }
        else if (collider.collide(character,enemy) == -1)
        {
          character.die();
          character.lives--;
        }
      }
    }
    checkEndOfLevel();
  }

  //Add Drawing logic here. (This should generally just conist of drawing things, if you want to update and change
  //values, use the update() method instead
  protected void draw(Graphics2D graphics)
  {
    if (SideScroller.isAtTitleScreen)
    {
      spriteLoader.load("../Content/Textures/titleScreen.png").draw(graphics,0,0);

      return;
    }
    background.draw(graphics,0,0);
    for (int i = 0; i < paralaxBackgroundImages.size(); i++)
    {
      ((ParalaxBackgroundImage)paralaxBackgroundImages.get(i)).draw(graphics,camera);
    }
    gameMap.draw(graphics,camera.getPosition());
    character.draw(graphics,camera);

    for(int i = 0; i < enemies.size(); i++)
    {
      ((AICharacter)enemies.get(i)).draw(graphics,camera);
    }


    drawUI(graphics);
  }
  private void checkEndOfLevel()
  {
    for (int i = 0; i < goals.size(); i++)
    {
      if (collider.collide(character,(Goal)goals.get(i)))
      {

        //Load new map/level here
        this.currentLevelPath = ((Goal)goals.get(i)).getLevel();
        if(((Goal)goals.get(i)).getLevel().equals("Level 2"))
        	background = spriteLoader.load("../Content/Textures/Backgrounds/river2.png");
        if(((Goal)goals.get(i)).getLevel().equals("Level 3"))
        	background = spriteLoader.load("../Content/Textures/Backgrounds/black2.png");
        if(((Goal)goals.get(i)).getLevel().equals("Level 4"))
        	background = spriteLoader.load("../Content/Textures/Backgrounds/orien2.png");
        if(((Goal)goals.get(i)).getLevel().equals("end"))
        	{

        	background = spriteLoader.load("../Content/Textures/endScreen.png");
        	gameMap = Map.load(spriteLoader,"../Data/end.txt");
        	loadLevel("../Data/endLevel.txt");

        	AudioPlayer.play("../Content/Audio/ending.wav", true);



        	}
        else{
        gameMap = Map.load(spriteLoader,"../Data/"+((Goal)goals.get(i)).getLevel()+".txt");
        loadLevel("../Data/"+((Goal)goals.get(i)).getLevel()+"_level.txt");
        character.moveTo(196,400);
        character.score+= 500;
        camera.setPosition(0,0);}
      }
    }
  }
  private void playBackgroundMusic()
  {

  }

  private void drawUI(Graphics2D graphics)
  {
    graphics.setFont(new java.awt.Font("Arial",java.awt.Font.PLAIN,32));
    graphics.setColor(Color.GREEN);
    character.getSprite().draw(graphics,0,8);
    graphics.drawString("x"+character.lives,68,76);

    graphics.drawString("Score: "+character.score,350,64);

    if(SideScroller.isGameOver)
    {
      graphics.setFont(new java.awt.Font("Arial",java.awt.Font.PLAIN,40));
      graphics.setColor(Color.GREEN);
      graphics.drawString("GAME OVER ",300,300);
      graphics.setFont(new java.awt.Font("Arial",java.awt.Font.PLAIN,28));
      graphics.drawString("Hit [Space] to go back to the main menu",220,360);
    }
  }

  private void loadLevel(String fileName)
  {
    paralaxBackgroundImages = new ArrayList();
    enemies = new ArrayList();
    goals = new ArrayList();

    java.io.BufferedReader reader;
    String line = new String();
    String[] elements;
    try
    {
      reader = new java.io.BufferedReader(new java.io.FileReader(fileName));
      //Keep looping through the level file until we reach the end
      while(!line.equals("eof"))
      {
        //Read the new line
        line = reader.readLine();
        //Split that line into its elements
        elements = line.split(":");
        //Check to see if we're dealing with an image
        if (elements[0].equals("image"))
        {
          ParalaxBackgroundImage img = new ParalaxBackgroundImage(spriteLoader.load(elements[1]),
                                                                  Integer.parseInt(elements[2]), Integer.parseInt(elements[3]),
                                                                  Integer.parseInt(elements[4]));;
          paralaxBackgroundImages.add(img);
        }
        //Check to see if we're dealing with an enemy
        else if (elements[0].equals("enemy"))
        {
          //Create the new enemy with the specified properties
          AICharacter enemy;
          if (elements[1].equals("Cheer"))
            enemy = AICharacter.createcheerleader(spriteLoader,Integer.parseInt(elements[2]),Integer.parseInt(elements[3]));
          else
        	  if(elements[1].equals("Emo"))
            enemy = AICharacter.createEmo(spriteLoader,Integer.parseInt(elements[2]),Integer.parseInt(elements[3]));
        	  else
        		  if(elements[1].equals("JROTC"))
        			  enemy= AICharacter.createJROTC(spriteLoader, Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
        		  else
        			  enemy = AICharacter.createRedneck(spriteLoader,Integer.parseInt(elements[2]),Integer.parseInt(elements[3]));


          enemy.patrol(Integer.parseInt(elements[4]));
          //Add the enemy to the list of enemies
          enemies.add(enemy);
        }
        //Check to see if we're dealing with a goal
        else if (elements[0].equals("goal"))
        {
          goals.add(new Goal(Integer.parseInt(elements[1]), Integer.parseInt(elements[2]), elements[3]));
        }
      }
      //Done reading the file, so close it
      reader.close();
    }
    catch(Exception e)
    {
      System.err.println("There was an error with the _level file");
      e.printStackTrace();
    }
  }
}
