package ee.ut.math.tvt.salessystem.domain.data;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;

public class Order implements Cloneable, DisplayableItem{
	long id;
	Date date;
	float sum;
	List<SoldItem> solditems;
	
	public Order(long id,float sum, List<SoldItem> solditems) {
		this.id=id;
		this.date = Calendar.getInstance().getTime();
		this.sum = sum;
		this.solditems = solditems;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getSum() {
		return sum;
	}

	public void setPrice(float sum) {
		this.sum = sum;
	}

	public List<SoldItem> getSolditems() {
		return solditems;
	}

	public void setSolditem(List<SoldItem> solditems) {
		this.solditems = solditems;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getName() {
		return "order nr "+id;
	}
	
}
