package si.um.feri.praktikum.bean;


import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import si.um.feri.praktikum.blockchain.Block;
import si.um.feri.praktikum.blockchain.BlockStorage;
import si.um.feri.praktikum.entity.Record;
import si.um.feri.praktikum.entity.User;
import si.um.feri.praktikum.util.BlockChainUtil;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class RecordBean {
	private User user;
	private Record newEvidence = new Record();
	private Record selectedEvidence = new Record();
	private String isPrivate = "public";
	
	public static final int difficulty = 5;

	public String addEvidence() {
		List<Block> blocks = BlockStorage.getInstance().getBlockchain();
		System.out.println(blocks.size());
		Block prevBlock = blocks.get(blocks.size() - 1);
		blocks.add(new Block(prevBlock.getHash(), newEvidence));
		blocks.get(blocks.size() - 1).mineBlock(difficulty);
		newEvidence = new Record();
		return "viewEvidences.xhtml";
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
				System.out.println(
						"Current stored: " + current.getHash() + "; Current calculated: " + current.calculateHash());
				return false;
			}

			if (!previous.getHash().equals(previous.calculateHash())) {
				System.out.println("Previous hashes not equal");
				System.out.println("Previous stored: " + previous.getHash() + "; Previous calculated: "
						+ previous.calculateHash());
				return false;
			}

		}

		return true;
	}

	public void updateEvidence() {
		List<Block> blocks = BlockStorage.getInstance().getBlockchain();
		Block prevBlock = blocks.get(blocks.size() - 1);
		selectedEvidence.increaseVersion(selectedEvidence.getVersion());
		blocks.add(new Block(prevBlock.getHash(), selectedEvidence));
		blocks.get(blocks.size() - 1).mineBlock(difficulty);
		selectedEvidence = new Record();

	}
	
	public void isUserKeyValid() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
		user = (User) sessionMap.get("user");
		if(user.getId().equals(selectedEvidence.getUser().getId())) {
			updateEvidence();
		}

	}
	public String findSelectedEvidence() {
		Record result = BlockChainUtil.getEvidence(selectedEvidence.getPrimaryKey());
		
		
		
		
		
		return "details.xhtml";
	}
	


	public List<Record> getAllPublicEvidences() {

		// log user who is accessing
		return BlockChainUtil.getAllPublicEvidences();
	}

	public List<Record> getAllPublicLastVersionEvidences() {
		// log user
		return BlockChainUtil.getAllPublicEvidencesLastVersion();
	}

	public List<Record> getAllUserEvidences(String id) {
		// log user
		return BlockChainUtil.getAllUserEvidencesList(id);
	}

	public List<Record> getAllUserLastVersionEvidences(String id) {
		// log user
		return BlockChainUtil.getCurrentUserEvidencesList(id);
	}

	public Record getEvidence(UUID uuid) {
		// log user
		return BlockChainUtil.getEvidence(uuid);
	}

	public Record getNewEvidence() {
		return newEvidence;
	}

	public void setNewEvidence(Record newEvidence) {
		this.newEvidence = newEvidence;
	}

	public List<Block> getBlocks() {
		return BlockStorage.getInstance().getBlockchain();
	}

	public Record getSelectedEvidence() {
		return selectedEvidence;
	}

	public void setSelectedEvidence(Record selectedEvidence) {
		this.selectedEvidence = selectedEvidence;
	}

	public String getPrivate() {
		return isPrivate;
	}

	public void setPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}
	

}
