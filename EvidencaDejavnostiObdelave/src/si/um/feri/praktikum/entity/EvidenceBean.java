package si.um.feri.praktikum.entity;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import si.um.feri.praktikum.blockchain.Block;
import si.um.feri.praktikum.blockchain.BlockStorage;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class EvidenceBean {

	private Evidence newEvidence = new Evidence();
	public static final int difficulty = 5;

	public void addEvidence() {
		List<Block> blocks = BlockStorage.getInstance().getBlockchain();
		System.out.println(blocks.size());
		Block prevBlock = blocks.get(blocks.size()-1);
		blocks.add(new Block(prevBlock.getHash(), newEvidence));
		blocks.get(blocks.size() - 1).mineBlock(difficulty);
		newEvidence = new Evidence();
	}

	public static Boolean isChainValid() {
		Block current;
		Block previous;
		List<Block> blockchain = BlockStorage.getInstance().getBlockchain();
		for (int i = 1; i < blockchain.size(); i++) {
			current = blockchain.get(i);
			previous = blockchain.get(i - 1);

			if (!current.getHash().equals(current.calculateHash())) {
				System.out.println("Current hashes not equal!");
				return false;
			}

			if (!previous.getHash().equals(previous.calculateHash())) {
				System.out.println("Previous hashes not equal");
				return false;
			}

		}

		return true;
	}

	public Evidence getNewEvidence() {
		return newEvidence;
	}

	public void setNewEvidence(Evidence newEvidence) {
		this.newEvidence = newEvidence;
	}
	
	public List<Block> getBlocks(){
		return BlockStorage.getInstance().getBlockchain();
	}

}
