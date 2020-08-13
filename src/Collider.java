import java.awt.Point;
public class Collider
{
  public Collider()
  {
    
  }
  
  public void collide(Character character, Map map)
  {
    if (!character.isAlive())
      return;
    Point topLeftTile;
    Point topRightTile;
    Point bottomLeftTile;
    Point bottomRightTile;
    
    topLeftTile = new Point(character.getBounds().x,character.getBounds().y);
    topRightTile = new Point(character.getBounds().x+character.getBounds().width,character.getBounds().y);
    bottomLeftTile = new Point(character.getBounds().x,character.getBounds().y+character.getBounds().height);
    bottomRightTile = new Point(character.getBounds().x+character.getBounds().width,character.getBounds().y+character.getBounds().height);
    
    topLeftTile.x /= SideScroller.TILE_SIZE;
    topLeftTile.y /= SideScroller.TILE_SIZE;
    topRightTile.x /= SideScroller.TILE_SIZE;
    topRightTile.y /= SideScroller.TILE_SIZE;
    bottomLeftTile.x /= SideScroller.TILE_SIZE;
    bottomLeftTile.y /= SideScroller.TILE_SIZE;
    bottomRightTile.x /= SideScroller.TILE_SIZE;
    bottomRightTile.y /= SideScroller.TILE_SIZE;
    
    try
    {
    //Bottom Tiles
    if (map.getTile(bottomLeftTile).isSolid())
    {
      if (!map.getTile(bottomLeftTile.x,bottomLeftTile.y-1).isSolid())
      {
        character.moveTo(character.getPosition().x, bottomRightTile.y*SideScroller.TILE_SIZE-character.getSprite().getHeight());
        character.isJumping = false;
      }
    }
    
    if (map.getTile(bottomRightTile).isSolid())
    {
      if (!map.getTile(bottomRightTile.x,bottomRightTile.y-1).isSolid())
      {
        character.moveTo(character.getPosition().x, bottomRightTile.y*SideScroller.TILE_SIZE-character.getSprite().getHeight());
        character.isJumping = false;
      }
    }
    
    //Top Tiles
    if (map.getTile(topLeftTile.x, topLeftTile.y).isSolid() && character.isJumping)
    {
      //character.applyForce(0,32);
    }
    if (map.getTileAtPosition(topRightTile).isSolid())
    {
      //character.applyForce(0,32);
      ///System.out.println("TOP RIGHT COLLISION");
    }
    
    //Right Collide
    if (map.getTile(bottomRightTile.x, bottomRightTile.y-1).isSolid() && character.direction == Direction.Right)
    {
      character.applyForce(-character.getForce().x,0);
    }
    //Left Collide
    if (map.getTile(bottomLeftTile.x, bottomLeftTile.y-1).isSolid() && character.direction == Direction.Left)
    {
      character.applyForce(-character.getForce().x,0);
    }
    } catch(Exception e) {}
  }
  
  public int collide(Character characterA, Character characterB)
  {
    int retVal = 0;
    if ((characterA.getPosition().x <= characterB.getPosition().x+characterB.getBounds().width)
       && (characterA.getPosition().x + characterA.getBounds().width >= characterB.getPosition().x))
    {
      //They are intersecting X coordinants, lets check and see if we have a collision by checking vertical too
      if ((characterA.getPosition().y <= characterB.getPosition().y+characterB.getBounds().height)
       && (characterA.getPosition().y + characterA.getBounds().height >= characterB.getPosition().y))
      {
        if (characterA.getPosition().y < characterB.getPosition().y-64)
          retVal = 1;
        else
          retVal = -1;
      }
    }
    return retVal;
  }
  
    public boolean collide(Character character, Goal goal)
    {
    if ((character.getPosition().x <= goal.x+64)
       && (character.getPosition().x + 64 >= goal.x))
    {
      //They are intersecting X coordinants, lets check and see if we have a collision by checking vertical too
      if ((character.getPosition().y <= goal.y+128)
       && (character.getPosition().y + 128 >= goal.y))
        return true;
    }
    return false;
  }
}