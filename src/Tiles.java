import java.util.Hashtable;
import java.util.ArrayList;

public class Tiles
{
  private static ArrayList tiles = new ArrayList();
  private static Hashtable lookUp = new Hashtable();
  private static int count = 0;

  public static void addTile(Tile tile)
  {
    tiles.add(tile);
  }

  public static Tile getTile(int index)
  {
    return (Tile)tiles.get(index);
  }

  public static int getCount()
  {
    return tiles.size();
  }

  public static void initialize(SpriteLoader spriteLoader)
  {
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/block.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/empty.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/brick.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/brownblock.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/brownbrick.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/bush.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/flowers.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/water.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/wave.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/dirt.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/dirttop.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/goldbrick.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/randomTile.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/grass.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/grassy.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/grayblock.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/greenblock.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/lightdirt.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/mud.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/orangeblock.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/redblock.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/redbrick.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/road.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/rock.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/stonebrick.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("../Content/Textures/Tiles/wood.png"),TileType.Solid));





  }
}
