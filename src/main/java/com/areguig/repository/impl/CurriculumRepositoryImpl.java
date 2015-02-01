package com.areguig.repository.impl;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Repository;

import com.areguig.repository.CurriculumRepository;
import com.areguig.repository.PMF;
import com.areguig.repository.entity.CurriculumConsultation;

@Repository
public class CurriculumRepositoryImpl implements CurriculumRepository {

	@Override
	public void createConsultation(String fnln, String ipAddress, String date,
			String origin) {
		CurriculumConsultation consultation = new CurriculumConsultation();
		consultation.setFnln(fnln);
		consultation.setDate(date);
		consultation.setIpAddress(ipAddress);
		consultation.setOrigin(origin);
		PersistenceManager persistenceManager = PMF.get()
				.getPersistenceManager();
		try {
			persistenceManager.makePersistent(consultation);
		} catch (JDOObjectNotFoundException e) {
			return;
		} finally {
			persistenceManager.close();
		}

	}

}
