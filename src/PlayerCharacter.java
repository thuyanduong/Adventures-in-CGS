
public class PlayerCharacter extends Character
{
  public int score;
  public int lives = 3;

  public PlayerCharacter(String name, AnimatedSprite sprite, int x, int y, int speed)
  {
    super(name,sprite,x,y,speed);
  }

  public void update(float gameTime, Camera camera, KeyboardInput input)
  {
    if (isDead)
    {
      if (lives > 0)
      {AudioPlayer.play("../Content/Audio/deathnoise.wav");
        this.moveTo(128,400);
        camera.setPosition(0,0);
        this.isDead = false;
        sprite.play("stand");
      }
      else
      {
        SideScroller.isGameOver = true;}
      return;
    }
    super.update(gameTime,camera);
    handleInput(input);

    if (position.y > 900)
    {
      camera.setPosition(0,0);
      lives--;
      die();
    }
  }


  private void handleInput(KeyboardInput input)
  {

    if (input.isKeyDown(Keys.Right))
    {
      if (input.isKeyPressed(Keys.Right))
      {
        sprite.play("walk");
      }
      this.applyForce(3,0);
      direction = Direction.Right;
    }
    else if (input.isKeyDown(Keys.Left))
    {
      if (input.isKeyPressed(Keys.Left))
        sprite.play("walk");
      this.applyForce(-3,0);
      direction = Direction.Left;
    }
    else
    {
      if (!sprite.isPlaying("stand"))
        sprite.play("stand");
    }

    if (input.isKeyPressed(Keys.Space) && !this.isJumping)
    {
      this.isJumping = true;
      this.applyForce(0,-32);
      AudioPlayer.play("../Content/Audio/jump.wav");
    }
  }
}
