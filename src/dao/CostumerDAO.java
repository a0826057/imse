package dao;

import java.util.List;
import model.Costumer;

public interface CostumerDAO {
	public List<Costumer> getCostumerList();
	public Costumer getCostumerById(int ID);
	public void addCostumer(Costumer c);
	public void changeCostumer(Costumer c);
	public void deleteCostumer(int ID);
}
