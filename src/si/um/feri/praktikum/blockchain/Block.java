package si.um.feri.praktikum.blockchain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import si.um.feri.praktikum.entity.Evidence;

public class Block implements Serializable {

	private static final long serialVersionUID = 7928750175767085088L;
	private String hash;
	private String previousHash;
	private Evidence data;
	private Calendar timeStamp = new GregorianCalendar();
	private int nonce;

	public Block(String previousHash, Evidence data) {
		this.previousHash = previousHash;
		this.data = data;
		this.timeStamp = Calendar.getInstance();
		this.hash = calculateHash();
	}

	public String calculateHash() {
		String calculatedHash = StringUtil
				.hashSHA256(previousHash + timeStamp.getTime().getTime() + Integer.toString(nonce) + data.toString());
		return calculatedHash;
	}

	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0');
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}

	}

	public String getPreviousHash() {
		return previousHash;
	}

	public Evidence getData() {
		return data;
	}

	public String getHash() {
		return hash;
	}

	public Calendar getTimeStamp() {
		return timeStamp;
	}

}
