package com.areguig.repository.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import lombok.Data;

@PersistenceCapable
@Data
public class CurriculumConsultation {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long	id;

	@Persistent
	private String	fnln;

	@Persistent
	private String	date;

	@Persistent
	private String	ipAddress;

	@Persistent
	private String	origin;

}
