package management;

import java.util.List;

import imse.Accessory;
import imse.Manufacturer;
import imse.Model;

public class ModelManagement {
	List<Model> models;

	public ModelManagement(){
		//models = 
	}
	
	public List<Model> getModels() {
		return models;
	}

	public void setModels(List<Model> models) {
		this.models = models;
	}
	
	public Model getModelById(int model_id){
		for(Model mod : models){
			if(mod.getModel_ID() == model_id){
				return mod;
			}
		}
		return null;
	}
	
	public void addModel(Manufacturer manufacturer, double price){
		
	}
	
	public void changeModel(Manufacturer manufacturer, double price){
		
	}
	
	public void deleteModel(int model_id){
		
	}
	
	public int getModelCount(){
		return models.size();
	}
	
	public Model getModelByManufacturerName(String name){
		return null;
	}
}
