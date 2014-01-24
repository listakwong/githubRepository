package com.list.or.bean.maint;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.tak.bean.BaseBean;

@Entity
@Table(name = "venue_type")
public class VenueType extends BaseBean implements Serializable {

	private static final long serialVersionUID = -2450495778665345280L;
	private String venue_type_code;
	private String venue_type_name;
	private String active_ind;
	private String create_by;
	private String last_upd_by;
	
	private Timestamp create_date;
	private Timestamp last_upd_date;

	private Set<OctopusServiceType> octopusServices = new HashSet<OctopusServiceType>();

	public VenueType() {
		super();
	}

	public VenueType(String venue_type_code) {
		super();
		this.venue_type_code = venue_type_code;
	}

	@Id
	public String getVenue_type_code() {
		return venue_type_code;
	}

	public void setVenue_type_code(String venue_type_code) {
		this.venue_type_code = venue_type_code;
	}

	public String getVenue_type_name() {
		return venue_type_name;
	}

	public void setVenue_type_name(String venue_type_name) {
		this.venue_type_name = venue_type_name;
	}

	public String getActive_ind() {
		return active_ind;
	}

	public void setActive_ind(String active_ind) {
		this.active_ind = active_ind;
	}
	
	@Column(nullable=true, updatable=false) 
	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	
	@Column(nullable=true, updatable=false) 
	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}

	@Column(nullable=true) 
	public String getLast_upd_by() {
		return last_upd_by;
	}

	public void setLast_upd_by(String last_upd_by) {
		this.last_upd_by = last_upd_by;
	}

	@Column(nullable=true) 
	public Timestamp getLast_upd_date() {
		return last_upd_date;
	}

	public void setLast_upd_date(Timestamp last_upd_date) {
		this.last_upd_date = last_upd_date;
	}

	@OneToMany(targetEntity = OctopusServiceType.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "venue_type_code",insertable=false, updatable=false)
	public Set<OctopusServiceType> getOctopusServices() {
		return octopusServices;
	}

	public void setOctopusServices(Set<OctopusServiceType> octopusServices) {
		this.octopusServices = octopusServices;
	}

}
