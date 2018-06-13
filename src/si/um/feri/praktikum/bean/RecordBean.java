package si.um.feri.praktikum.bean;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import com.google.gson.Gson;

import si.um.feri.praktikum.PDF.JSONvXML;
import si.um.feri.praktikum.PDF.XmlVPDF;
import si.um.feri.praktikum.blockchain.Block;
import si.um.feri.praktikum.blockchain.BlockStorage;
import si.um.feri.praktikum.entity.Record;
import si.um.feri.praktikum.util.BlockChainUtil;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class RecordBean {

	private Record newEvidence = new Record();
	private Record selectedEvidence = new Record();
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

}
