package com.redis.hibernate.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="product")
@Table(name="products")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	public Long barcode;

	public Integer product_gid;
	
	public String name;
	
	public Integer mrp;     /*< mrp in paisa */
	
	public String alternate_mrp;
	
	public String uom;				/*< Unit of Measure */
	
	public Integer measure;			/*< Measure in gms/ml */
	
	public Integer brand_id;
	
	public Integer category_id;
	
	public Integer vat_id;
	
	public String image;
	
	public Boolean is_disabled;
	
	public Boolean is_pc;
	
	public Integer rating;
	
	public Integer gst_id;
	
	public String hsn_code;

	@Type(type="timestamp")
	public Timestamp created_at;
	
	@Type(type="timestamp")
	public Timestamp updated_at;

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

	public Long getBarcode() {
		return barcode;
	}
	public void setBarcode(Long barcode) {
		this.barcode = barcode;
	}

	public Integer getProduct_gid() {
		return product_gid;
	}

	public void setProduct_gid(Integer product_gid) {
		this.product_gid = product_gid;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Integer getMrp() {
		return mrp;
	}

	public void setMrp(Integer mrp) {
		this.mrp = mrp;
	}

	public String getAlternate_mrp() {
		return alternate_mrp;
	}

	public void setAlternate_mrp(String alternate_mrp) {
		this.alternate_mrp = alternate_mrp;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public Integer getMeasure() {
		return measure;
	}

	public void setMeasure(Integer measure) {
		this.measure = measure;
	}

	public Integer getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public Integer getVat_id() {
		return vat_id;
	}

	public void setVat_id(Integer vat_id) {
		this.vat_id = vat_id;
	}

	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}

	public Boolean getIs_disabled() {
		return is_disabled;
	}

	public void setIs_disabled(Boolean is_disabled) {
		this.is_disabled = is_disabled;
	}


	public Boolean getIs_pc() {
		return is_pc;
	}


	public void setIs_pc(Boolean is_pc) {
		this.is_pc = is_pc;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getGst_id() {
		return gst_id;
	}

	public void setGst_id(Integer gst_id) {
		this.gst_id = gst_id;
	}

	public String getHsn_code() {
		return hsn_code;
	}

	public void setHsn_code(String hsn_code) {
		this.hsn_code = hsn_code;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
}