package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import common.APIResponse;

import entities.Client;
import response.ClientResponse;
import services.ClientElement;

import org.apache.log4j.Logger;

public class ClientDAO {
	private static final Logger LOG = Logger.getLogger(ClientDAO.class);
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@SuppressWarnings({ "unchecked" })
	public ClientResponse listAll() {
		Session session = null;
		ClientResponse response = new ClientResponse();
		List<Client> client = new ArrayList<Client>();
		List<ClientElement> results = null;
		try {

			session = sessionFactory.openSession();
			session.beginTransaction();

			if (!this.sessionFactory.getCurrentSession().getTransaction().isActive()) {

				client = session.createCriteria(Client.class).list();
			}
			results = new ArrayList<ClientElement>();
			for (Client cl : client) {
				ClientElement element = new ClientElement();
				element.setId(cl.getId());
				element.setMorada(cl.getMorada());
				element.setNif(cl.getNif());
				element.setNome(cl.getNome());
				element.setTelefone(cl.getTelefone());

				results.add(element);
			}
			ClientElement[] elementArr = new ClientElement[results.size()];
			elementArr = results.toArray(elementArr);
			response.setClientElement(elementArr);
			
			if (!session.getTransaction().wasCommitted()){
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Caught database exception.", e);	
			response.setErrorCode("FATAL");
			response.setMessage(e.getMessage());
		} finally {

			if (session != null) {				
				session.close();
			}
		}
		
		return response;
	}

	@SuppressWarnings({ "unchecked" })
	public ClientResponse findClient(String nome, String nif) {
		Session session = null;
		ClientResponse response = new ClientResponse();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(Client.class);

			if(nome != null && !nome.equals("")) {
				cr.add(Restrictions.like("nome", nome + '%'));
			}
			if(nif != null && !nif.equals("")){
				cr.add(Restrictions.eq("nif", nif));

			}

			List<Client> client = new ArrayList<Client>();
			if(!this.sessionFactory.getCurrentSession().getTransaction().isActive()) {
				client = cr.list();
			}

			List<ClientElement> results = new ArrayList<ClientElement>();
			for (Client cl : client) {
				ClientElement element = new ClientElement();
				element.setId(cl.getId());
				element.setMorada(cl.getMorada());
				element.setNif(cl.getNif());
				element.setNome(cl.getNome());
				element.setTelefone(cl.getTelefone());

				results.add(element);
			}
			
			ClientElement[] elementArr = new ClientElement[results.size()];
			elementArr = results.toArray(elementArr);
			response.setClientElement(elementArr);
			
			if(!session.getTransaction().wasCommitted()){
				session.getTransaction().commit();
			}

		} catch (Exception e) {
			LOG.error("Caught database exception.", e);
			response.setErrorCode("FATAL");
			response.setMessage(e.getMessage());
		} finally {

			if (session != null) {
				session.close();
			}
		}
		return response;
	}

	public APIResponse saveClient(ClientElement client) {
		APIResponse response = new APIResponse();

		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Client cl = new Client();

			cl.setMorada(client.getMorada());
			cl.setNif(client.getNif());
			cl.setNome(client.getNome());
			cl.setTelefone(client.getTelefone());
			if(!this.sessionFactory.getCurrentSession().getTransaction().isActive()) {
				session.save(cl);
				transaction.commit();

				response.setErrorCode("OK");
				response.setSuccess(true);
				response.setMessage("Cliente inserido com sucesso.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Caught database exception.", e);
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return response;
	}

	public APIResponse deleteClient(Integer id) {

		APIResponse response = new APIResponse();

		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			if(!this.sessionFactory.getCurrentSession().getTransaction().isActive()) {

				Client client = (Client) session.get(Client.class, id);
				session.delete(client);
				transaction.commit();

				response.setErrorCode("OK");
				response.setSuccess(true);
				response.setMessage("Cliente apagado com sucesso.");
			}

		} catch (Exception e) {
			LOG.error("Caught database exception.", e);
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return response;

	}

}
