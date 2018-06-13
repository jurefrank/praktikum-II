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
import si.um.feri.praktikum.entity.Evidence;
import si.um.feri.praktikum.util.BlockChainUtil;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class EvidenceBean {

	private Evidence newEvidence = new Evidence();
	private Evidence selectedEvidence = new Evidence();
	public static final int difficulty = 5;

	public void addEvidence() {
		List<Block> blocks = BlockStorage.getInstance().getBlockchain();
		System.out.println(blocks.size());
		Block prevBlock = blocks.get(blocks.size() - 1);
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
		selectedEvidence = new Evidence();

	}

		
		
	public String json() throws IOException, TransformerException, SAXException, ParserConfigurationException {
		Gson gson = new Gson();
		
		String result = gson.toJson(selectedEvidence);
		String xml = JSONvXML.readFile(result);
	
		
		
		return xml;
	}
	
	public void download() throws IOException, TransformerException, SAXException, ParserConfigurationException {
	
		
		String fileName = "Verzija";
	    FacesContext fc = FacesContext.getCurrentInstance();
	    ExternalContext ec = fc.getExternalContext();

	    ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
	    ec.setResponseContentType("application/pdf");
	    ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); // Shrani z Pop-up window

	    OutputStream output = ec.getResponseOutputStream();
	    
		String xml = json();
		OutputStreamWriter osw = new OutputStreamWriter(output);
		PrintWriter writer = new PrintWriter(osw);
		writer.write(xml);
		writer.flush();
		writer.close();
		
		XmlVPDF v = new XmlVPDF();
		
		v.vPDF(xml, output);
		

	    fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
	}
	
	
	

	public List<Evidence> getAllPublicEvidences() {
		// log user who is accessing
		return BlockChainUtil.getAllPublicEvidences();
	}

	public List<Evidence> getAllPublicLastVersionEvidences() {
		// log user
		return BlockChainUtil.getAllPublicEvidencesLastVersion();
	}

	public List<Evidence> getAllUserEvidences(String id) {
		// log user
		return BlockChainUtil.getAllUserEvidencesList(id);
	}

	public List<Evidence> getAllUserLastVersionEvidences(String id) {
		// log user
		return BlockChainUtil.getCurrentUserEvidencesList(id);
	}

	public Evidence getEvidence(UUID uuid) {
		// log user
		return BlockChainUtil.getEvidence(uuid);
	}

	public Evidence getNewEvidence() {
		return newEvidence;
	}

	public void setNewEvidence(Evidence newEvidence) {
		this.newEvidence = newEvidence;
	}

	public List<Block> getBlocks() {
		return BlockStorage.getInstance().getBlockchain();
	}

	public Evidence getSelectedEvidence() {
		return selectedEvidence;
	}

	public void setSelectedEvidence(Evidence selectedEvidence) {
		this.selectedEvidence = selectedEvidence;
	}

}
