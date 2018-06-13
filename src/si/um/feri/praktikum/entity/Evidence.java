package si.um.feri.praktikum.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

import si.um.feri.praktikum.util.BlockChainUtil;

/**
 * 
 * This class is model for our record form to hold specified data/info according
 * to new GDPR law.
 *
 */
public class Evidence implements Serializable {

	private static final long serialVersionUID = 1L;
	// MANAGER
	private String nameManager;
	private String emailManager;
	private String phoneNumberManager;
	private User user;

	// PROCESSING
	private String title;
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
	/**
	 * By default deletion date is 100 years from now.
	 */
	public Evidence() {
		this("","", "", "", "", "", "", "", undefinedDate(), "", Calendar.getInstance(), null);
	}

	/**
	 * By default deletion date is 100 years from now.
	 * 
	 * @param primaryKey
	 */
	public Evidence(UUID primaryKey) {
		this("","", "", "", "", "", "", "", undefinedDate(), "", Calendar.getInstance(), primaryKey);
	}

	/**
	 * @param title
	 * @param nameManager
	 * @param emailManager
	 * @param phoneNumberManager
	 * @param processingPurpose
	 * @param descriptionCategoriesOfDataSubjectsTypesOfPersonalData
	 * @param categoriesOfUsersWhomPersonalDataDisclosed
	 * @param informationOnTransfersOfPersonalData
	 * @param dataDeletionDate
	 * @param generalDescriptionOfTechnicalSecurityMeasures
	 * @param lastSaved
	 * @param primaryKey
	 */
	public Evidence(String title,String nameManager, String emailManager, String phoneNumberManager, String processingPurpose,
			String descriptionCategoriesOfDataSubjectsTypesOfPersonalData,
			String categoriesOfUsersWhomPersonalDataDisclosed, String informationOnTransfersOfPersonalData,
			Calendar dataDeletionDate, String generalDescriptionOfTechnicalSecurityMeasures, Calendar lastSaved,
			UUID primaryKey) {
		this.title = title;
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

	/**
	 * When user wants to edit form and persist it this method is called to increase
	 * version.
	 * 
	 * @param version
	 *            Current version.
	 * @return New version.
	 */
	public int increaseVersion(int version) {
		return version++;
	}

	private static Calendar undefinedDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 100);
		return cal;
	}

	// GETTERS AND SETTERS
	/**
	 * 
	 * @return title of record
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 
	 * @param title
	 * 			Sets name of the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return Name of the manager.
	 */
	public String getNameManager() {
		return nameManager;
	}

	/**
	 * @param nameManager
	 *            Sets name of the manager.
	 */
	public void setNameManager(String nameManager) {
		this.nameManager = nameManager;
	}

	/**
	 * @return Gets manager email.
	 */
	public String getEmailManager() {
		return emailManager;
	}

	/**
	 * @param emailManager
	 *            Sets manager email.
	 */
	public void setEmailManager(String emailManager) {
		this.emailManager = emailManager;
	}

	/**
	 * @return Gets manager phone number.
	 */
	public String getPhoneNumberManager() {
		return phoneNumberManager;
	}

	/**
	 * @param phoneNumberManager
	 *            Sets manager phone number.
	 */
	public void setPhoneNumberManager(String phoneNumberManager) {
		this.phoneNumberManager = phoneNumberManager;
	}

	/**
	 * @return Gets processing purpose.
	 */
	public String getProcessingPurpose() {
		return processingPurpose;
	}

	/**
	 * @param processingPurpose
	 *            Sets processing purpose.
	 */
	public void setProcessingPurpose(String processingPurpose) {
		this.processingPurpose = processingPurpose;
	}

	/**
	 * @return Gets description categories of data subjects types of personal data.
	 */
	public String getDescriptionCategoriesOfDataSubjectsTypesOfPersonalData() {
		return descriptionCategoriesOfDataSubjectsTypesOfPersonalData;
	}

	/**
	 * @param descriptionCategoriesOfDataSubjectsTypesOfPersonalData
	 *            Sets description categories of data subjects types of personal
	 *            data.
	 */
	public void setDescriptionCategoriesOfDataSubjectsTypesOfPersonalData(
			String descriptionCategoriesOfDataSubjectsTypesOfPersonalData) {
		this.descriptionCategoriesOfDataSubjectsTypesOfPersonalData = descriptionCategoriesOfDataSubjectsTypesOfPersonalData;
	}

	/**
	 * @return Gets categories of users whom personal data disclosed.
	 */
	public String getCategoriesOfUsersWhomPersonalDataDisclosed() {
		return categoriesOfUsersWhomPersonalDataDisclosed;
	}

	/**
	 * @param categoriesOfUsersWhomPersonalDataDisclosed
	 *            Sets categories of users whom personal data disclosed.
	 */
	public void setCategoriesOfUsersWhomPersonalDataDisclosed(String categoriesOfUsersWhomPersonalDataDisclosed) {
		this.categoriesOfUsersWhomPersonalDataDisclosed = categoriesOfUsersWhomPersonalDataDisclosed;
	}

	/**
	 * @return Gets information on transfers of personal data.
	 */
	public String getInformationOnTransfersOfPersonalData() {
		return informationOnTransfersOfPersonalData;
	}

	/**
	 * @param informationOnTransfersOfPersonalData
	 *            Sets information on transfers of personal data.
	 */
	public void setInformationOnTransfersOfPersonalData(String informationOnTransfersOfPersonalData) {
		this.informationOnTransfersOfPersonalData = informationOnTransfersOfPersonalData;
	}

	/**
	 * @return Gets data deletion date.
	 */
	public Calendar getDataDeletionDate() {
		return dataDeletionDate;
	}

	/**
	 * @param dataDeletionDate
	 *            Sets data deletion date.
	 */
	public void setDataDeletionDate(Calendar dataDeletionDate) {
		this.dataDeletionDate = dataDeletionDate;
	}

	/**
	 * @return Gets general description of technical security measures.
	 */
	public String getGeneralDescriptionOfTechnicalSecurityMeasures() {
		return generalDescriptionOfTechnicalSecurityMeasures;
	}

	/**
	 * @param generalDescriptionOfTechnicalSecurityMeasures
	 *            Sets general description of technical security measures.
	 */
	public void setGeneralDescriptionOfTechnicalSecurityMeasures(String generalDescriptionOfTechnicalSecurityMeasures) {
		this.generalDescriptionOfTechnicalSecurityMeasures = generalDescriptionOfTechnicalSecurityMeasures;
	}

	/**
	 * @return Gets Calendar last saved.
	 */
	public Calendar getLastSaved() {
		return lastSaved;
	}

	/**
	 * @param lastSaved
	 *            Sets last saved.
	 */
	public void setLastSaved(Calendar lastSaved) {
		this.lastSaved = lastSaved;
	}

	/**
	 * @return Gets primary key.
	 */
	public UUID getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @param primaryKey
	 *            Sets primary key.
	 */
	public void setPrimaryKey(UUID primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * @return Gets user.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            Sets user.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return Gets version.
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            Sets version.
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This will return string ready to be hashed with data we want to hash
	 * </p>
	 */
	@Override
	public String toString() {
		return nameManager + ";" + emailManager + ";" + phoneNumberManager + ";" + processingPurpose + ";"
				+ descriptionCategoriesOfDataSubjectsTypesOfPersonalData + ";"
				+ categoriesOfUsersWhomPersonalDataDisclosed + ";" + informationOnTransfersOfPersonalData;
	}

}