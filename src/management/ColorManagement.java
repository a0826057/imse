package management;

//written by a01349198 - IB

import java.util.ArrayList;
import java.util.List;
import imse.Color;

public class ColorManagement {
	List<Color> colors;

	public ColorManagement(){
		this.colors = new ArrayList<Color>();
	}
	
	public ColorManagement(List<Color> ls){
		this.colors = ls;
	}
	public List<Color> getColorList() {
		return colors;
	}

	public void setColors(List<Color> colors) {
		this.colors = colors;
	}
	
	public Color getColorById(int color_id){
		for(Color col : colors){
			if(col.getColor_ID() == color_id){
				return col;
			}
		}
		return null;
	}
	
	public void addColor(String description, String color_code){
		Color col = new Color(description, color_code);
		colors.add(col);
	}
	
	public void changeColor(String description, String color_code){
		
	}
	
	public void deleteColor(int color_id){
		for(Color col : colors){
			if(col.getColor_ID() == color_id){
				colors.remove(col);
			}
		}
	}
	
	public int getColorCount(){
		return colors.size();
	}
	
	public Color getColorByDescription(String description){
		for(Color col : colors){
			if(col.getDescription() == description){
				return col;
			}
		}
		return null;
	}
}
