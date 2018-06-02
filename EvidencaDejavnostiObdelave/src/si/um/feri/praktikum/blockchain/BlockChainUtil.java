package si.um.feri.praktikum.blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import si.um.feri.praktikum.entity.Evidence;

public class BlockChainUtil {

	public static Evidence getEvidence(UUID key) {
		for (Block b : BlockStorage.getInstance().getBlockchain())
			if (b.getData().getPrimaryKey().equals(key))
				return b.getData();
		return null;
	}
	
	/**
	 * 
	 * @param id User id.
	 * @return List of all user Evidences.
	 */
	public static List<Evidence> getUserEvidencesList(int id) {
		List<Evidence> ret = new ArrayList<>();
		for (Block b : BlockStorage.getInstance().getBlockchain())
			if (b.getData().getUser().getId() == id)
				ret.add(b.getData());
		return ret;
	}

}
