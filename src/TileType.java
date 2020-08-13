
public enum TileType
{
  Blank,
  Cloud,
  Solid,
  Deadly;
  
  public static TileType parse(String str)
  {
    if (str.equals("Blank"))
      return TileType.Blank;
    else if (str.equals("Solid"))
      return TileType.Solid;
    else
      return TileType.Blank;
  }
}