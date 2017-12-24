package dao;

//written by a01349198 - IB

import java.util.List;

import imse.Manufacturer;
import imse.Model;

public interface ModelDAO {
	public List<Model> getModelList();
	public Model getModelById(int model_id);
	public void addModel(Manufacturer manufacturer, double price);
	public void changeModel(Manufacturer manufacturer, double price);
	public void deleteModel(int model_id);
	public int getModelCount();
	public Model getModelByManufacturerName(String name);
}
