package com.appsdeveloperblog.keycloak;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

public class RemoteUserStorageProviderFactory implements UserStorageProviderFactory<RemoteUserStorageProvider>{
	
	public static final String PROVIDER_NAME = "my-remote-mysql-user-storage-provider";

	@Override
	public RemoteUserStorageProvider create(KeycloakSession session, ComponentModel model) {
		// TODO Auto-generated method stub
		return new RemoteUserStorageProvider(session, model);
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
