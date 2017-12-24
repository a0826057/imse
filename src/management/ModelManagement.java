package management;

// written by a01349198 - IB

import java.util.ArrayList;
import java.util.List;
import imse.Manufacturer;
import imse.Model;

public class ModelManagement {
	private List<Model> models;
	private static ModelManagement modelInstance = null;
	
	public ModelManagement(){
		models = new ArrayList<Model>(); 
	}
	
	public ModelManagement getInstance(){
		if(modelInstance == null){
			modelInstance = new ModelManagement();
		}
		
		return modelInstance;
	}
	
	public List<Model> getModelList() {
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
		//Model mod = new Model(manufacturer, price);
		//models.add(mod);
	}
	
	public void changeModel(Manufacturer manufacturer, double price){
		
	}
	
	public void deleteModel(int model_id){
		for(Model mod : models){
			if(mod.getModel_ID() == model_id){
				models.remove(mod);
				break;
			}
		}
	}
	
	public int getModelCount(){
		return models.size();
	}
	
	public Model getModelByManufacturerName(String name){
		return null;
	}
}
