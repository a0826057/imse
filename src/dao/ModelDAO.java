package dao;

//written by a01349198 - IB

import java.util.List;

import imse.Manufacturer;
import imse.Model;

public interface ModelDAO {
	public List<Model> getModelList();
	public Model getModelById(int model_id);
	public void addModel(Manufacturer manufacturer, String description, double price);
	public void changeModel(int model_ID, Manufacturer manufacturer, String description, double price);
	public void deleteModel(int model_id);
	public int getModelCount();
	public List<Model> getModelsByManufacturersName(String name);
}
