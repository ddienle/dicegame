package android.app;

import android.widget.ImageButton;

public class Dice {
	int order;
	int image;
	ImageButton btn;
	boolean isInPlay;
	boolean isChuongOver; 
	
	public boolean isChuongOver() {
		return isChuongOver;
	}

	public void setChuongOver(boolean isChuongOver) {
		this.isChuongOver = isChuongOver;
	}

	public Dice(int order, int image, ImageButton btn, boolean isInPlay, boolean isChuongOver)
	{
		this.order = order;
		this.image = image;
		this.btn = btn;
		this.isInPlay = isInPlay;
		this.isChuongOver = isChuongOver;
	}
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	public int getImage() {
		return image;
	}
	
	public void setImage(int image) {
		this.image = image;
	}
	
	public boolean isInPlay() {
		return isInPlay;
	}
	
	public void setInPlay(boolean isInPlay) {
		this.isInPlay = isInPlay;
	}
	
	public ImageButton getBtn() {
		return btn;
	}
	
	public void setBtn(ImageButton btn) {
		this.btn = btn;
	}
}
