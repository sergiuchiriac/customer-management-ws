package services;

import org.apache.log4j.Logger;

import common.APIResponse;
import dao.ClientDAO;
import response.ClientResponse;

public class ClientWS {
	
	private static final Logger LOG = Logger.getLogger(ClientWS.class);
	
	public APIResponse addClient(ClientElement client){	
		
			APIResponse response = new APIResponse();	
			try {
				
			response = new ClientDAO().saveClient(client);
			
		} catch (Exception e) {
			LOG.error("Catch  exception when saving client.",e);
			response = new APIResponse("FATAL", e.getMessage());
			response.setSuccess(false);
		}

		return response;
	}
	
	public ClientResponse getClients() {

		ClientResponse response = new ClientResponse();


			ClientDAO clientDao = new ClientDAO();
			ClientResponse element = clientDao.listAll();

			if(element.getClientElement() != null && element.getClientElement().length > 0) {
				response = new ClientResponse("OK", "Listagem efetuada com sucesso. ", element.getClientElement());
			}else{
				LOG.error("Response without results");
				response = new ClientResponse("OK","Sem resultados",element.getClientElement());
			}

		return response;

	}
	
	
	public APIResponse deleteClient(Integer id) {

		APIResponse response = new APIResponse();

		try {
			ClientDAO clientDao = new ClientDAO();

			response = clientDao.deleteClient(id);

			if(!response.getErrorCode().equals("OK")) {
				LOG.error("The client could not be deleted.");
				response = new APIResponse("FATAL", "Não foi possível apagar o cliente.");
				response.setSuccess(false);
			}
		} catch (Exception e) {
			LOG.error("Catch exception when delete client.",e);
			response = new APIResponse("FATAL", e.getMessage());
			response.setSuccess(false);
		}

		return response;

	}
	
	
	public ClientResponse searchClient(String nome, String nif) {

		ClientResponse response = new ClientResponse();

		try {

			ClientDAO clientDao = new ClientDAO();

			ClientResponse element = clientDao.findClient(nome, nif);

			if(element.getClientElement().length > 0) {
				response = new ClientResponse("OK", "Pesquisa efetuada com sucesso. ", element.getClientElement());
			} else {
				response = new ClientResponse("OK", "Pesquisa retornou 0 resultados. ");
			}

		} catch (Exception e) {
			LOG.error("Catch exception when search client.",e);
			response = new ClientResponse("FATAL", e.getMessage());
			response.setSuccess(false);
		}

		return response;

	}
}
