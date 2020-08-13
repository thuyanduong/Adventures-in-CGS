

public class AICharacter extends Character
{
  private int distance;
  private int startPosition;
  private int distanceTraveled;
  public AICharacter(String name, AnimatedSprite sprite, int x, int y, int speed)
  {
    super(name,sprite,x,y,speed);
    sprite.play("walk");
  }

  public void update(float gameTime, Camera camera)
  {
    //If the character is dead, set its animation to show that, and dont do anything else
    if (isDead)
    {
      sprite.play("dead");
      return;
    }
    super.update(gameTime, camera);

    //Update the distance the character is traveled since it turned around
    distanceTraveled += speed;

    //Once the character has traveled it's patrol distance turn it around, and reset the distance traveled
    if (distanceTraveled >= distance)
    {
      if (this.direction == Direction.Right)
        this.direction = Direction.Left;
      else if (this.direction == Direction.Left)
        this.direction = Direction.Right;
      distanceTraveled = 0;
    }

    //Move the character in the direction traveled
    if (direction == Direction.Right)
      this.applyForce(speed,0);
    else
      this.applyForce(-speed,0);
  }

  //Sets the distance the AICharacter should walk
  public void patrol(int distance)
  {
    this.startPosition = this.position.x;
    this.distance = distance;
  }

  //A method to help make the Emo type of enemy
  //It is static so it is called by AICharacter.createEmo()
  public static AICharacter createEmo(SpriteLoader spriteLoader, int tileX, int tileY)
  {
    //Load the frames into the animation
    AnimatedSprite enemyAnim = new AnimatedSprite(200f,true, true);
    enemyAnim.addFrame(spriteLoader.load("../Content/Textures/Enemies/Emo/1.png"));
    enemyAnim.addFrame(spriteLoader.load("../Content/Textures/Enemies/Emo/2.png"));
    enemyAnim.addFrame(spriteLoader.load("../Content/Textures/Enemies/Emo/dead.png"));

    //Register animations
    enemyAnim.addAnimation("walk",new int[] {0,1,0});
    enemyAnim.addAnimation("dead",new int[] {2});

    //Create and return the new Emo with the animations we just made
    AICharacter Emo = new AICharacter("Emo", enemyAnim, tileX*64,tileY*64,1);
    return Emo;
  }

  //A method to help make the cheerleader type of enemy
  //It is static so it is called by AICharacter.createcheerleader()
  public static AICharacter createcheerleader(SpriteLoader spriteLoader, int tileX, int tileY)
  {
    //Load the frames into the animation
    AnimatedSprite enemyAnim = new AnimatedSprite(200f,true, true);
    enemyAnim.addFrame(spriteLoader.load("../Content/Textures/Enemies/Cheer/1.png"));
    enemyAnim.addFrame(spriteLoader.load("../Content/Textures/Enemies/Cheer/2.png"));
    enemyAnim.addFrame(spriteLoader.load("../Content/Textures/Enemies/Cheer/dead.png"));

    //Register animations
    enemyAnim.addAnimation("walk",new int[] {0,1,0});
    enemyAnim.addAnimation("dead",new int[] {2});

    //Create and return the new cheerleader with the animations we just made
    AICharacter cheerleader = new AICharacter("cheerleader", enemyAnim, tileX*64,tileY*64,1);
    return cheerleader;
  }
//A method to help make the redneck type of enemy
  //It is static so it is called by AICharacter.createRedneck()
  public static AICharacter createRedneck(SpriteLoader spriteLoader, int tileX, int tileY)
  {
    //Load the frames into the animation
    AnimatedSprite enemyAnim = new AnimatedSprite(200f,true, true);
    enemyAnim.addFrame(spriteLoader.load("../Content/Textures/Enemies/Redneck/1.png"));
    enemyAnim.addFrame(spriteLoader.load("../Content/Textures/Enemies/Redneck/2.png"));
    enemyAnim.addFrame(spriteLoader.load("../Content/Textures/Enemies/Redneck/dead.png"));

    //Register animations
    enemyAnim.addAnimation("walk",new int[] {0,1,0});
    enemyAnim.addAnimation("dead",new int[] {2});

    //Create and return the new redneck with the animations we just made
    AICharacter Redneck = new AICharacter("Redneck", enemyAnim, tileX*64,tileY*64,1);
    return Redneck;
  }
//A method to help make the JROTC type of enemy
  //It is static so it is called by AICharacter.createJROTC()
  public static AICharacter createJROTC(SpriteLoader spriteLoader, int tileX, int tileY)
  {
    //Load the frames into the animation
    AnimatedSprite enemyAnim = new AnimatedSprite(200f,true, true);
    enemyAnim.addFrame(spriteLoader.load("../Content/Textures/Enemies/Jrotc/1.png"));
    enemyAnim.addFrame(spriteLoader.load("../Content/Textures/Enemies/Jrotc/2.png"));
    enemyAnim.addFrame(spriteLoader.load("../Content/Textures/Enemies/Jrotc/dead.png"));

    //Register animations
    enemyAnim.addAnimation("walk",new int[] {0,1,0});
    enemyAnim.addAnimation("dead",new int[] {2});

    //Create and return the new JROTC with the animations we just made
    AICharacter Jrotc = new AICharacter("Jrotc", enemyAnim, tileX*64,tileY*64,1);
    return Jrotc;
  }


}
