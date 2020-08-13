import java.awt.Graphics;

public class Tile
{
  //Fields
  TileType tileType;
  Sprite sprite;
  
  //Constructor
  public Tile(Sprite sprite, TileType tileType)
  {
    this.sprite = sprite;
    this.tileType = tileType;
  }
  
  //Draws the tile at tehe specified position
  public void draw(Graphics graphics, int x, int y)
  {
    this.sprite.draw(graphics,x,y);
  }
  
  public String getImagePath()
  {
    return this.sprite.getImagePath();
  }
  //Gets the TileType of the tile.
  public TileType getTileType()
  {
    return tileType;
  }
  //Returns True if tile collides with the character (IE its solid)
  public boolean isSolid()
  {
    return tileType == TileType.Solid;
  }
  
  //Returns True if the tile kills any character that touches it (IE Lava, Water, Spikes....)
  public boolean killsCharacter()
  {
    return tileType == TileType.Deadly;
  }
  
  //Returns True if the tile is a 'cloud' tile. This does not mean it is litterally a cloud, it just means that you can walk through it, jump over it,
  //but if you land on it from the top it behaves like a solid tile
  public boolean isCloud()
  {
    return tileType == TileType.Cloud;
  }
}