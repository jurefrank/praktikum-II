package si.um.feri.praktikum.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

import si.um.feri.praktikum.blockchain.BlockChainUtil;

public class Evidence implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// MANAGER
	private String nameManager;
	private String emailManager;
	private String phoneNumberManager;
	private User user;

	// PROCESSING
	private String processingPurpose;
	private String descriptionCategoriesOfDataSubjectsTypesOfPersonalData;
	private String categoriesOfUsersWhomPersonalDataDisclosed;
	private String informationOnTransfersOfPersonalData;
	private Calendar dataDeletionDate;
	private String generalDescriptionOfTechnicalSecurityMeasures;

	// DATE LAST SAVED AND VERSION
	private Calendar lastSaved;
	private int version;
	// PRIMARY KEY
	private UUID primaryKey;

	// CONSTRUCTORS
	public Evidence() {
		this("", "", "", "", "", "", "", undefinedDate(), "", Calendar.getInstance(), null);
	}

	public Evidence(UUID primaryKey) {
		this("", "", "", "", "", "", "", undefinedDate(), "", Calendar.getInstance(), primaryKey);
	}

	public Evidence(String nameManager, String emailManager, String phoneNumberManager, String processingPurpose,
			String descriptionCategoriesOfDataSubjectsTypesOfPersonalData,
			String categoriesOfUsersWhomPersonalDataDisclosed, String informationOnTransfersOfPersonalData,
			Calendar dataDeletionDate, String generalDescriptionOfTechnicalSecurityMeasures, Calendar lastSaved,
			UUID primaryKey) {
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
		this.primaryKey = primaryKey == null ? UUID.randomUUID() : primaryKey;
		this.version = BlockChainUtil.getEvidence(this.primaryKey) == null ? 1
				: increaseVersion(BlockChainUtil.getEvidence(this.primaryKey).getVersion());
	}

	public int increaseVersion(int version) {
		return version++;
	}
	
	private static Calendar undefinedDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 100);
		return cal;
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

	public UUID getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(UUID primaryKey) {
		this.primaryKey = primaryKey;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	// To String
	@Override
	public String toString() {
		return nameManager + ";" + emailManager + ";" + phoneNumberManager + ";" + processingPurpose + ";"
				+ descriptionCategoriesOfDataSubjectsTypesOfPersonalData + ";"
				+ categoriesOfUsersWhomPersonalDataDisclosed + ";" + informationOnTransfersOfPersonalData;
	}

}
