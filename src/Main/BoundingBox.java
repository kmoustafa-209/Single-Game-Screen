package Main;

public class BoundingBox 
{
	public int x, y, w, h;
	
	public BoundingBox()
	{
		x = 0;
		y = 0;
		w = 0;
		h = 0;
	}
	
	public BoundingBox(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public int GetX()
	{
		return x;
	}
	
	public int GetY()
	{
		return y;
	}
	
	public int GetW()
	{
		return w;
	}
	
	public int GetH()
	{
		return h;
	}
	
	
	public static boolean BoundaryCheck(BoundingBox box1, BoundingBox box2)
	{
		if (box1.GetX() > box2.GetW() 
			|| box1.GetW() < box2.GetX() 
			|| box1.GetY() > box2.GetH() 
			|| box1.GetH() < box2.GetY())
			
			return false;
		
		else
			return true;
	}
	
	public String toString()
	{
		return x + " " + y + " " + w + " " + h;
	}
}
