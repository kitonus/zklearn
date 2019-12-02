package com.jatis.test.zk.vm;

import java.util.List;
import java.util.UUID;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.jatis.test.zk.entity.CarEntity;
import com.jatis.test.zk.service.CarService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CarSearchViewModel {
    private String keyword;
    private CarEntity selectedCar;
    
    private boolean adding = false;
    private boolean editing = false;
	@WireVariable
	private CarService carService;
	
	private List<CarEntity> carList;

	public List<CarEntity> getCarList() {
		return carList;
	}

	public void setCarList(List<CarEntity> carList) {
		this.carList = carList;
	}
	
    @Command
    @NotifyChange("carList")
	public void search() {
		this.carList = carService.search(keyword);
	}
    
    @Command
    @NotifyChange("*")
    public void save() {
    	this.adding = false;
    	this.editing = false;
    	this.selectedCar = this.carService.save(selectedCar);
    	this.carList = carService.search(keyword);
    }

    @Command
    @NotifyChange("*")
    public void add() {
    	this.adding = true;
    	this.selectedCar = new CarEntity();
    }
    
    @Command
    @NotifyChange({"editing", "selectedCar"})
    public void select(@BindingParam("id") UUID id) {
    	this.editing = true;
    	this.selectedCar = this.carService.findById(id);
    }

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public CarEntity getSelectedCar() {
		return selectedCar;
	}

	public void setSelectedCar(CarEntity selectedCar) {
		this.selectedCar = selectedCar;
	}

	public boolean isAdding() {
		return adding;
	}

	public void setAdding(boolean adding) {
		this.adding = adding;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	
}
