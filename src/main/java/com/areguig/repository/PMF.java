package com.areguig.repository;

import java.util.HashMap;
import java.util.Map;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * The persistence manager factory class
 *
 * @author aklireguig
 */
public final class PMF {

	// private static final PersistenceManagerFactory pmfInstance = JDOHelper
	// .getPersistenceManagerFactory("transactions-optional");
	private static PersistenceManagerFactory	pmfInstance;
	static {
		Map<String, String> props = new HashMap<String, String>();
		props.put("javax.jdo.PersistenceManagerFactoryClass",
				"org.datanucleus.api.jdo.JDOPersistenceManagerFactory");
		props.put("javax.jdo.option.ConnectionURL", "appengine");
		props.put("javax.jdo.option.NontransactionalRead", "true");
		props.put("javax.jdo.option.NontransactionalWrite", "true");
		props.put("javax.jdo.option.RetainValues", "true");
		props.put("datanucleus.appengine.autoCreateDatastoreTxns", "true");
		props.put("datanucleus.appengine.singletonPMFForName", "true");
		// props.put("datanucleus.cache.level2.type", "none");
		pmfInstance = JDOHelper.getPersistenceManagerFactory(props);
	}

	private PMF() {
	}

	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}
}
