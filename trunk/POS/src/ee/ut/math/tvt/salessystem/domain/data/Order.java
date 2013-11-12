package ee.ut.math.tvt.salessystem.domain.data;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;

@Entity
@Table(name = "ORDER")
public class Order implements Cloneable, DisplayableItem {
	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", sum=" + sum
				+ ", solditems=" + solditems + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "date")
	private Date date;
	@Column(name = "total_sum")
	private float sum;
	@OneToMany
	@JoinTable(name = "ORDERS_TO_SOLDITEMS", joinColumns = @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "SOLDITEM_ID", referencedColumnName = "ID"))
	List<SoldItem> solditems;

	public Order(long id, float sum, List<SoldItem> solditems) {
		this.id = id;
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
		return "order nr " + id;
	}

}
