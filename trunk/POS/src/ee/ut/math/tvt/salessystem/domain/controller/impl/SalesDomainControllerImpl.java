package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {

	private static final Logger log = Logger
			.getLogger(SalesDomainControllerImpl.class);

	public void submitCurrentPurchase(List<SoldItem> goods)
			throws VerificationFailedException {
		// Let's assume we have checked and found out that the buyer is
		// underaged and
		// cannot buy chupa-chups
		// throw new VerificationFailedException("Underaged!");
		// XXX - Save purchase

	}

	public void cancelCurrentPurchase() throws VerificationFailedException {
		// XXX - Cancel current purchase
	}

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase

	}

	@SuppressWarnings("unchecked")
	public List<StockItem> loadWarehouseState() {
		/*
		 * // XXX mock implementation List<StockItem> dataset = null;
		 * ObjectInputStream ois = null; try { ois = new ObjectInputStream(new
		 * FileInputStream( "res/warehouse.data")); dataset = (List<StockItem>)
		 * ois.readObject(); } catch (IOException | ClassNotFoundException e) {
		 * log.error(e.toString()); return new ArrayList<StockItem>(); } catch
		 * (Exception e) { log.error(e.toString()); return new
		 * ArrayList<StockItem>(); } finally { try { ois.close(); } catch
		 * (Exception e) { log.error(e.toString()); } } return dataset;
		 */
		List<StockItem> dataset = null;

		Session session = HibernateUtil.currentSession();

		Query query = session.createQuery("from StockItem");
		dataset = query.list();
		return dataset;
	}

	public boolean saveWarehouseState(List<StockItem> dataset) {
		/*
		 * ObjectOutputStream oout = null; try { oout = new
		 * ObjectOutputStream(new FileOutputStream( "res/warehouse.data"));
		 * oout.writeObject(dataset);
		 * 
		 * } catch (IOException e) { log.error(e.toString()); return false; }
		 * finally { try { oout.close(); } catch (Exception e) {
		 * log.error(e.toString()); } }
		 */
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("JPAService");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		for (StockItem stockItem : dataset) {
			em.persist(stockItem);
		}

		return true;
	}

	public void endSession() {
		HibernateUtil.closeSession();
	}
}
