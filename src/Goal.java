public class Goal
{
  public int x;
  public int y;
  private String level;
  
  public Goal(int x, int y, String level)
  {
    this.x = x;
    this.y = y;
    this.level = level;
  }
  
  public String getLevel()
  {
    return level;
  }
}