package be.fontainedejade.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
@RequestScoped
public class ContactBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private MapModel mapModel = new DefaultMapModel();

	public ContactBean() {
		mapModel.addOverlay(new Marker(new LatLng(50.8393878, 4.3979159), "La Fontaine de Jade", "resources/image/map_flag.png", "resources/image/map_flag.png"));
	}

	/**
	 * @return the mapModel
	 */
	public MapModel getMapModel() {
		return mapModel;
	}

	/**
	 * @param mapModel the mapModel to set
	 */
	public void setMapModel(MapModel mapModel) {
		this.mapModel = mapModel;
	}

	
}
