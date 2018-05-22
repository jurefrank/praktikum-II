package si.um.feri.praktikum.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.google.gson.Gson;

public class Evidence implements Serializable {
	// MANAGER
	private String nameManager;
	private String emailManager;
	private String phoneNumberManager;

	// PROCESSING
	private String processingPurpose;
	private String descriptionCategoriesOfDataSubjectsTypesOfPersonalData;
	private String categoriesOfUsersWhomPersonalDataDisclosed;
	private String informationOnTransfersOfPersonalData;
	private Calendar dataDeletionDate = new GregorianCalendar();
	private String generalDescriptionOfTechnicalSecurityMeasures;

	// DATE LAST SAVED
	private Calendar lastSaved;

	// CONSTRUCTORS
	public Evidence() {

	}

	public Evidence(String nameManager, String emailManager, String phoneNumberManager, String processingPurpose,
			String descriptionCategoriesOfDataSubjectsTypesOfPersonalData,
			String categoriesOfUsersWhomPersonalDataDisclosed, String informationOnTransfersOfPersonalData,
			Calendar dataDeletionDate, String generalDescriptionOfTechnicalSecurityMeasures, Calendar lastSaved) {
		this.nameManager = nameManager;
		this.emailManager = emailManager;
		this.phoneNumberManager = phoneNumberManager;
		this.processingPurpose = processingPurpose;
		this.descriptionCategoriesOfDataSubjectsTypesOfPersonalData = descriptionCategoriesOfDataSubjectsTypesOfPersonalData;
		this.categoriesOfUsersWhomPersonalDataDisclosed = categoriesOfUsersWhomPersonalDataDisclosed;
		this.informationOnTransfersOfPersonalData = informationOnTransfersOfPersonalData;
		this.dataDeletionDate = dataDeletionDate;
		this.generalDescriptionOfTechnicalSecurityMeasures = generalDescriptionOfTechnicalSecurityMeasures;
		this.lastSaved = lastSaved;
	}

	// GETTERS AND SETTERS
	public String getNameManager() {
		return nameManager;
	}

	public void setNameManager(String nameManager) {
		this.nameManager = nameManager;
	}

	public String getEmailManager() {
		return emailManager;
	}

	public void setEmailManager(String emailManager) {
		this.emailManager = emailManager;
	}

	public String getPhoneNumberManager() {
		return phoneNumberManager;
	}

	public void setPhoneNumberManager(String phoneNumberManager) {
		this.phoneNumberManager = phoneNumberManager;
	}

	public String getProcessingPurpose() {
		return processingPurpose;
	}

	public void setProcessingPurpose(String processingPurpose) {
		this.processingPurpose = processingPurpose;
	}

	public String getDescriptionCategoriesOfDataSubjectsTypesOfPersonalData() {
		return descriptionCategoriesOfDataSubjectsTypesOfPersonalData;
	}

	public void setDescriptionCategoriesOfDataSubjectsTypesOfPersonalData(
			String descriptionCategoriesOfDataSubjectsTypesOfPersonalData) {
		this.descriptionCategoriesOfDataSubjectsTypesOfPersonalData = descriptionCategoriesOfDataSubjectsTypesOfPersonalData;
	}

	public String getCategoriesOfUsersWhomPersonalDataDisclosed() {
		return categoriesOfUsersWhomPersonalDataDisclosed;
	}

	public void setCategoriesOfUsersWhomPersonalDataDisclosed(String categoriesOfUsersWhomPersonalDataDisclosed) {
		this.categoriesOfUsersWhomPersonalDataDisclosed = categoriesOfUsersWhomPersonalDataDisclosed;
	}

	public String getInformationOnTransfersOfPersonalData() {
		return informationOnTransfersOfPersonalData;
	}

	public void setInformationOnTransfersOfPersonalData(String informationOnTransfersOfPersonalData) {
		this.informationOnTransfersOfPersonalData = informationOnTransfersOfPersonalData;
	}

	public Calendar getDataDeletionDate() {
		return dataDeletionDate;
	}

	public void setDataDeletionDate(Calendar dataDeletionDate) {
		this.dataDeletionDate = dataDeletionDate;
	}

	public String getGeneralDescriptionOfTechnicalSecurityMeasures() {
		return generalDescriptionOfTechnicalSecurityMeasures;
	}

	public void setGeneralDescriptionOfTechnicalSecurityMeasures(String generalDescriptionOfTechnicalSecurityMeasures) {
		this.generalDescriptionOfTechnicalSecurityMeasures = generalDescriptionOfTechnicalSecurityMeasures;
	}

	public Calendar getLastSaved() {
		return lastSaved;
	}

	public void setLastSaved(Calendar lastSaved) {
		this.lastSaved = lastSaved;
	}

	// To String
	@Override
	public String toString() {
		return nameManager + ";" + emailManager + ";" + phoneNumberManager + ";" + processingPurpose + ";"
				+ descriptionCategoriesOfDataSubjectsTypesOfPersonalData + ";"
				+ categoriesOfUsersWhomPersonalDataDisclosed + ";" + informationOnTransfersOfPersonalData;
	}

}
