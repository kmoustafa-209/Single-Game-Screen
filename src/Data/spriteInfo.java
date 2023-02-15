package Data;

public class spriteInfo {

	private int x;
	private int y;
	private Vector2D v2d = new Vector2D(x, y);
	String tag;
	
	public spriteInfo(Vector2D v2d, String tag){
		this.v2d = v2d;
		this.tag = tag;
		
	}
	
	public String getTag(){
		return tag;
	}
	
	public Vector2D getCoords(){
		return v2d;
	}
	
	public void setTag(String newTag){
		tag = newTag;
	}
	
	public void setCoords(Vector2D newV2D){
		v2d = newV2D;
	}
	
	public void setCoords(int x, int y){
		this.v2d = new Vector2D(x,y);
	}
	
	public String toString(){
		return v2d + " " + tag;
	}
}
