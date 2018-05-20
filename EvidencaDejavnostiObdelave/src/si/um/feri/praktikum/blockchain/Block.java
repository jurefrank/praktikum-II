package si.um.feri.praktikum.blockchain;

import java.util.Calendar;

import si.um.feri.praktikum.entity.Evidence;

public class Block {

	private String hash;
	private String previousHash;
	private Evidence data;
	private Calendar timeStamp;

	public Block(String previousHash, Evidence data) {
		this.previousHash = previousHash;
		this.data = data;
	}

}
