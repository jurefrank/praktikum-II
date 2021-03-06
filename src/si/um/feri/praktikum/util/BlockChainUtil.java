package si.um.feri.praktikum.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import si.um.feri.praktikum.blockchain.Block;
import si.um.feri.praktikum.blockchain.BlockStorage;
import si.um.feri.praktikum.entity.Record;

public class BlockChainUtil {

	/**
	 * 
	 * @param key
	 *            UUID key which is able to find specified evidence in list.
	 * @return Null if no result, or it returns last version of the document.
	 */
	public static Record getEvidence(UUID key) {
		Record ret = null;
		for (Block b : BlockStorage.getInstance().getBlockchain())
			if (b.getData().getPrimaryKey().equals(key))
				if (ret == null)
					ret = b.getData();
				else if (b.getData().getVersion() > ret.getVersion())
					ret = b.getData();
		return ret;
	}

	/**
	 * 
	 * @return List of all public evidences.
	 */
	public static List<Record> getAllPublicEvidences() {
		List<Record> evidences = new ArrayList<>();
		for (Block b : BlockStorage.getInstance().getBlockchain())
			evidences.add(b.getData());
		return evidences;
	}

	/**
	 * 
	 * @return List of all last version public evidences.
	 */
	public static List<Record> getAllPublicEvidencesLastVersion() {
		List<Record> evidences = new ArrayList<>();
		for (Block b : BlockStorage.getInstance().getBlockchain())
			evidences.add(getEvidence(b.getData().getPrimaryKey()));
		return evidences;
	}

	/**
	 * 
	 * @param id
	 *            User id.
	 * @return List of all user Evidences.
	 */
	public static List<Record> getAllUserEvidencesList(String id) {
		List<Record> ret = new ArrayList<>();
		for (Block b : BlockStorage.getInstance().getBlockchain())
			if (b.getData().getUser().getId().equals(id))
				ret.add(b.getData());
		return ret;
	}

	/**
	 * 
	 * @param list
	 *            List of all evidences we want to check.
	 * @return Returns count of how many different evidences there is in list.
	 */
	@SuppressWarnings("unused")
	private static int getDifferentEvidencesCount(List<Record> list) {
		List<UUID> uuids = new ArrayList<>();
		for (Record e : list)
			if (!uuids.contains(e.getPrimaryKey()))
				uuids.add(e.getPrimaryKey());
		return uuids.size();
	}

	/**
	 * 
	 * @param list
	 *            List of all evidences we want to check.
	 * @return Returns list of all different UUIDs which are primary keys of
	 *         Evidences.
	 */
	private static List<UUID> getDifferentUUIDList(List<Record> list) {
		List<UUID> uuids = new ArrayList<>();
		for (Record e : list)
			if (!uuids.contains(e.getPrimaryKey()))
				uuids.add(e.getPrimaryKey());
		return uuids;
	}

	/**
	 * 
	 * @param id
	 *            User id.
	 * @return Returns only last saved versions of Evidences for specified user.
	 */
	public static List<Record> getCurrentUserEvidencesList(String id) {
		List<Record> ret = new ArrayList<>();
		for (UUID uuid : getDifferentUUIDList(getAllUserEvidencesList(id)))
			ret.add(getEvidence(uuid));
		return ret;
	}

}