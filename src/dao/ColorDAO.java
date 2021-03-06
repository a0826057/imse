package dao;

//written by a01349198 - IB

import java.util.List;

import model.Color;

public interface ColorDAO {
	public List<Color> getColorList();
	public Color getColorById(int color_id);
	public void addColor(String description, String color_code);
	public void changeColor(int color_ID, String description, String color_code);
	public void deleteColor(int color_id);
	public int getColorCount();
	public List<Color> getColorsByDescription(String description);
}
