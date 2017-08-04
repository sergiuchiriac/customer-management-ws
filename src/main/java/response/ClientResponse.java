package response;

import common.APIResponse;
import services.ClientElement;


public class ClientResponse extends APIResponse{

	private  ClientElement[] clientElement;
	
	
	public ClientResponse() {this.clientElement = null;}

	public ClientResponse(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
	
    public ClientResponse(String errorCode, String errorMessage, ClientElement[] clientElement) {
        super(errorCode, errorMessage);
        this.clientElement = clientElement;
    }

	public ClientElement[] getClientElement() {
		return clientElement;
	}

	public void setClientElement(ClientElement[] clientElement) {
		this.clientElement = clientElement;
	}


    
}
