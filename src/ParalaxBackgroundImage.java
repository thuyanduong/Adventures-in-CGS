import java.awt.Graphics;
public class ParalaxBackgroundImage
{
  private Sprite sprite;
  private int x;
  private int y;
  private int paralaxMultiplier;
  public ParalaxBackgroundImage(Sprite sprite, int x, int y, int paralaxMultiplier)
  {    
    this.sprite = sprite;
    this.x = x;
    this.y = y;
    this.paralaxMultiplier = paralaxMultiplier;
  }
  
  public void draw(Graphics graphics, Camera camera)
  {
    sprite.draw(graphics,x-camera.getPosition().x/paralaxMultiplier,y);
  }
}