package si.um.feri.praktikum.bean;

import java.util.ArrayList;
import java.util.HashMap;
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
	private Record cloned= new Record();

	public static final int difficulty = 5;

	public String addEvidence() {
		if (isPrivate.equals("public")) {
			List<Block> blocks = BlockStorage.getInstance().getBlockchain();
			System.out.println(blocks.size());
			Block prevBlock = blocks.get(blocks.size() - 1);
			blocks.add(new Block(prevBlock.getHash(), newEvidence));
			blocks.get(blocks.size() - 1).mineBlock(difficulty);
			newEvidence = new Record();
			return "viewEvidences.xhtml";
		} else {
			// kdo je user
			FacesContext ctx = FacesContext.getCurrentInstance();
			Map<String, Object> smap = ctx.getExternalContext().getSessionMap();
			user = (User) smap.get("user");
			HashMap<String, List<Block>> privateChains = BlockStorage.getInstance().getPrivateBlockchains();
			List<Block> privateBlocks = privateChains.get(user.getId());
			if (privateBlocks == null) {
				privateBlocks = new ArrayList<>();
				privateChains.put(user.getId(), privateBlocks);
			}
			if (privateBlocks.size() == 0)
				privateBlocks.add(new Block("0", new Record()));
			Block prevBlock = privateBlocks.get(privateBlocks.size() - 1);
			privateBlocks.add(new Block(prevBlock.getHash(), newEvidence));
			privateBlocks.get(privateBlocks.size() - 1).mineBlock(difficulty);
			newEvidence = new Record();
			return "viewPrivate.xhtml";
		}
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

	public String updatePrivateEvidence() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> smap = context.getExternalContext().getSessionMap();
		user = (User) smap.get("user");
		HashMap<String, List<Block>> privateChains = BlockStorage.getInstance().getPrivateBlockchains();
		List<Block> privateBlocks = privateChains.get(user.getId());
		Block prevBlock =privateBlocks.get(privateBlocks.size() - 1);
		cloned.setVersion(cloned.getVersion() + 1);
		privateBlocks.add(new Block(prevBlock.getHash(), cloned));
		privateBlocks.get(privateBlocks.size() - 1).mineBlock(difficulty);
		cloned = null;
		
		
		return "viewPrivate.xhtml";
	}
	public String updateEvidence() {
		List<Block> blocks = BlockStorage.getInstance().getBlockchain();
		Block prevBlock = blocks.get(blocks.size() - 1);
		cloned.setVersion(cloned.getVersion() + 1);
		blocks.add(new Block(prevBlock.getHash(), cloned));
		blocks.get(blocks.size() - 1).mineBlock(difficulty);
		cloned = null;
		
		return "viewEvidences.xhtml";
	}

	public void isUserKeyValid() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
		user = (User) sessionMap.get("user");
		if (user.getId().equals(selectedEvidence.getUser().getId())) {
			updateEvidence();
		}

	}

	public String findSelectedEvidence() {
		Record result = BlockChainUtil.getEvidence(selectedEvidence.getPrimaryKey());

		return "details.xhtml";
	}
	
	public String findSelectedEvidencePrivate() {
		Record result = BlockChainUtil.getEvidence(selectedEvidence.getPrimaryKey());

		return "privateDetails.xhtml";
	}

	public String findSelectedEvidence2Edit() {
		try {
			cloned = (Record) selectedEvidence.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "editForm.xhtml";
	}
	public String findSelectedEvidence2EditPrivate() {
		try {
			cloned = (Record) selectedEvidence.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "privateEditForm.xhtml";
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

	public List<Block> getPrivateChain() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Map<String, Object> smap = ctx.getExternalContext().getSessionMap();
		user = (User) smap.get("user");
		HashMap<String, List<Block>> privateChains = BlockStorage.getInstance().getPrivateBlockchains();
		System.out.println(user.getId());
		System.out.println(privateChains.get(user.getId()).size());
		return privateChains.get(user.getId());
	}

	public Record getCloned() {
		return cloned;
	}

	public void setCloned(Record cloned) {
		this.cloned = cloned;
	}

}
