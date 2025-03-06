/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.referencedemodata.patient;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;

import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.PersonName;

import static org.openmrs.module.referencedemodata.Randomizer.randomArrayEntry;
import static org.openmrs.module.referencedemodata.Randomizer.randomBetween;
import static org.openmrs.module.referencedemodata.Randomizer.randomSuffix;
import static org.openmrs.module.referencedemodata.ReferenceDemoDataUtils.toDate;

public class DemoPersonGenerator {
	
	private static final int MIN_AGE = 2;
	
	private static final int MAX_AGE = 90;
	
	private static final String[] GENDERS = {"M", "F"};

	private static final String[] MALE_FIRST_NAMES = { "Paul", "Salifou", "Robert", "Michael", "William", "David", "Richard",
			"Joseph", "Charles", "Thomas", "Ethan", "Daniel", "Mathieu", "Donald", "Antoine", "Paul", "Samuel",
			"Georges", "Stephane", "Kenneth", "Andre", "Edward", "Brian", "Junior", "Kevin" };

	private static final String[] FEMALE_FIRST_NAMES = { "Marie", "Patricia", "Elisabeth", "Jennifer", "Linda", "Barbara",
			"Suzanne", "Angele", "Jessica", "Felicia", "Sarah", "Karen", "Nancy", "Betty", "Lisa", "Sandra", "Eveline",
			"Donna", "Ashley", "Kimberly", "Carolle", "Michelle", "Amanda", "Emily", "Melissa" };

	private static final String[] FAMILY_NAMES = { "MBARGA", "ELONG", "FIANGA", "ETAM", "POUALA", "NDJOCK", "MOUELLE",
			"SAIBOU", "ENONA", "ONANA", "NANA", "KONTCHOU", "NDAM", "NJOYA", "BOUBA", "EMOUNE", "FEUGANG",
			"ESSOKA", "MIDIYAWA", "NTOCK NTOCK", "BIKOK", "NDOG KOBE", "MBEMBE", "EBENE", "BIDJECK", "TONYE", "DONFACK", "AYISSI",
			"ONDOUA", "YUFENYUY", "EYA", "MONKAM", "FRU", "NGASSA", "HAMADOU", "MAHAMAT", "KAMGA", "NGAH", "MEDJO", "TATAH",
			"NGOUNOU", "MATIKE", "EWANE", "ONDOA", "TAGNE", "ASHUY", "MAFFO", "MBANGA", "ATOH", "TAMBA" };
	
	public static Person populatePerson(Person person) {
		PersonName personName = new PersonName();
		String gender = randomArrayEntry(GENDERS);
		boolean male = gender.equals("M");
		personName.setGivenName(randomArrayEntry(male ? MALE_FIRST_NAMES : FEMALE_FIRST_NAMES));
		personName.setFamilyName(randomArrayEntry(FAMILY_NAMES));
		person.addName(personName);
		
		PersonAddress patientAddress = new PersonAddress();
		String randomSuffix = randomSuffix();
		patientAddress.setAddress1("Address1" + randomSuffix);
		patientAddress.setCityVillage("City" + randomSuffix);
		patientAddress.setStateProvince("State" + randomSuffix);
		patientAddress.setCountry("Country" + randomSuffix);
		patientAddress.setPostalCode(randomSuffix(5));
		person.addAddress(patientAddress);
		
		person.setBirthdate(randomBirthdate());
		person.setBirthdateEstimated(false);
		person.setGender(gender);
		
		return person;
	}
	
	private static Date randomBirthdate() {
		LocalDate now = LocalDate.now();
		
		int year = randomBetween(now.getYear() - MAX_AGE, now.getYear() - MIN_AGE);
		int month = randomBetween(1, 12);
		int day = randomBetween(1, YearMonth.of(year, month).lengthOfMonth());
		
		return toDate(LocalDate.of(year, month, day));
	}
	
}
