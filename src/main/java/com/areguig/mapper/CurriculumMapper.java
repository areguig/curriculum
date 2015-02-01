package com.areguig.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.areguig.controller.form.About;
import com.areguig.controller.form.Curriculum;
import com.areguig.controller.form.EducationEntry;
import com.areguig.controller.form.Language;
import com.areguig.controller.form.Location;
import com.areguig.controller.form.Networks;
import com.areguig.controller.form.WorkExperience;

@Component
public class CurriculumMapper {

	public Curriculum toAkliCurriculum() {
		Curriculum result = new Curriculum();
		// The comment
		result.setComment("This is Akli's JSON curriculum");
		// About
		About about = new About();
		about.setEmail("akli[dot]reguig[@]gmail[dot]com");
		about.setFirstName("Akli");
		about.setLastName("REGUIG");
		about.setPhone("");
		Location location = new Location();
		location.setAddress("Rue AIME CESAIRE");
		location.setCity("Bezons");
		location.setPostalCode("95870");
		location.setCountry("France");
		about.setLocation(location);

		result.setAbout(about);
		// Networks
		Networks networks = new Networks();
		networks.setGithub("http://github.com/ragnoros");
		networks.setLinkedIn("http://fr.linkedin.com/pub/akli-reguig/32/649/370");
		networks.setTwitter("http://twitter.com/ragnoros");
		result.setNetworks(networks);
		// Education
		List<EducationEntry> education = new ArrayList<EducationEntry>();
		EducationEntry masterEducationEntry = new EducationEntry();
		masterEducationEntry.setArea("Software Development");
		masterEducationEntry.setDiploma("Master's degree");
		masterEducationEntry.setStartDate("2007-09");
		masterEducationEntry.setEndDate("2012-09");
		masterEducationEntry
				.setInstitution("Université Pierre et Marie Curie (UPMC)");
		masterEducationEntry.setCity("Paris");
		education.add(masterEducationEntry);
		result.setEducation(education);

		// Work experiences
		List<WorkExperience> work = new ArrayList<WorkExperience>();
		WorkExperience work1 = new WorkExperience();
		work1.setCompany("Capgemini Technologie Services");
		work1.setWebsite("http://www.fr.capgemini.com/");
		work1.setPosition("Consultant SI");
		work1.setStartDate("2012-04");
		work1.setEndDate("2014-12");
		work1.setSummary("Great experience!");
		work.add(work1);
		result.setWorkExperience(work);
		// languages
		List<Language> languages = new ArrayList<Language>();
		Language french = new Language();
		french.setName("French");
		french.setLevel("Mother tongue");
		languages.add(french);
		Language english = new Language();
		english.setName("English");
		english.setLevel("Very good command");
		languages.add(english);
		result.setLanguages(languages);
		return result;
	}
}
