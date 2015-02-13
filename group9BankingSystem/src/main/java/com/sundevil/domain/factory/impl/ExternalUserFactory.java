/**
 * 
 */
package com.sundevil.domain.factory.impl;

import org.springframework.stereotype.Service;

import com.sundevil.domain.IExternalUser;
import com.sundevil.domain.factory.IExternalUserFactory;
import com.sundevil.domain.implementation.ExternalUser;

/**
 * @author satyaswaroop
 *
 */
@Service
public class ExternalUserFactory implements IExternalUserFactory {

	@Override
	public  IExternalUser createExtUserObject(){
		return new ExternalUser();
		
	}
}
