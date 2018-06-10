package si.um.feri.praktikum.blockchain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import si.um.feri.praktikum.entity.Evidence;
import si.um.feri.praktikum.util.StringUtil;

/**
 * 
 * This class is used in our blockchain, in each block we can store one
 * transaction or String data, which represent record form for new GDPR law.
 * This class also has method mineBlock which is used to mine block so we can
 * ensure some security.
 *
 */
public class Block implements Serializable {

	private static final long serialVersionUID = 7928750175767085088L;
	private String hash;
	private String previousHash;
	private Evidence data;
	private Calendar timeStamp = new GregorianCalendar();
	private int nonce;

	/**
	 * 
	 * @param previousHash
	 *            String hash value of previous block.
	 * @param data
	 *            Data which is going to be stored in block.
	 */
	public Block(String previousHash, Evidence data) {
		this.previousHash = previousHash;
		this.data = data;
		this.timeStamp = Calendar.getInstance();
		this.hash = calculateHash();
	}

	/**
	 * 
	 * @return Returns hash of previous hash, current time, nonce, and Evidence to
	 *         string.
	 */
	public String calculateHash() {
		String calculatedHash = StringUtil
				.hashSHA256(previousHash + timeStamp.getTime().getTime() + Integer.toString(nonce) + data.toString());
		return calculatedHash;
	}

	/**
	 * The method is used for mining blocks as long as the number of zeroes of the
	 * beginning of the hash does not match the number of difficulty.
	 * 
	 * @param difficulty
	 *            Defines how many zeroes miner will have to calculate for hash.
	 */
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0');
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}

	}

	/**
	 * 
	 * @return Previous hash.
	 */
	public String getPreviousHash() {
		return previousHash;
	}

	/**
	 * 
	 * @return Returns data.
	 */
	public Evidence getData() {
		return data;
	}

	/**
	 * 
	 * @return Returns hash for this block.
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * 
	 * @return Returns time stamp.
	 */
	public Calendar getTimeStamp() {
		return timeStamp;
	}

}