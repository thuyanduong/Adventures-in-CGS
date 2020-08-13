import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Point;
public class MouseInput implements MouseListener, MouseMotionListener
{
  private Point position;
  private Point clickPosition;
  private Point changeInPosition;
  private boolean leftDown;
  private boolean leftClicked;
  private boolean rightDown;
  private boolean rightClicked;
  public MouseInput()
  {
    position = new Point(0,0);
    clickPosition = new Point(0,0);
    changeInPosition = new Point(0,0);
  }
  
  public synchronized void mousePressed(MouseEvent e)
  {
    if (e.getButton() == MouseEvent.BUTTON1)
      this.leftDown = true;
    else if (e.getButton() == MouseEvent.BUTTON3)
      this.rightDown = true;
  }
  
  public synchronized void mouseReleased(MouseEvent e)
  {
    if (e.getButton() == MouseEvent.BUTTON1)
      this.leftDown = false;
    else if (e.getButton() == MouseEvent.BUTTON3)
      this.rightDown = false;
  }
  
  public synchronized void mouseClicked(MouseEvent e)
  {
    if (e.getButton() == MouseEvent.BUTTON1)
    {
      this.leftDown = false;
      this.leftClicked = true;
    }
    this.clickPosition = e.getPoint();
  }
  
  public synchronized void mouseEntered(MouseEvent e)
  {
  }
  public synchronized void mouseExited(MouseEvent e)
  {
  }
  public synchronized void mouseMoved(MouseEvent e)
  {
    /*this.changeInPosition.x = this.position.x - e.getPoint().x;
    this.changeInPosition.y = this.position.y - e.getPoint().y;*/
    this.position = e.getPoint();
  }
  
  public synchronized void mouseDragged(MouseEvent e)
  {
    this.changeInPosition.x = this.position.x - e.getPoint().x;
    this.changeInPosition.y = this.position.y - e.getPoint().y;
    this.position = e.getPoint();
  }
  public Point getPosition()
  {
    return position;
  }
  
  public boolean isLeftClicked()
  {
    return leftClicked;
  }
  
  public boolean isLeftDown()
  {
    return leftDown;
  }
  
  public boolean isRightDown()
  {
    return rightDown;
  }
  
  public Point getClickedPosition()
  {
    return clickPosition;
  }
  
  public Point getChangeInPosition()
  {
    return changeInPosition;
  }
}