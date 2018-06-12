package si.um.feri.praktikum.blockchain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import si.um.feri.praktikum.entity.Evidence;
import si.um.feri.praktikum.util.LoggerUtil;
import si.um.feri.praktikum.util.MongoConnectionUtil;

/**
 * Block storage implements ServletContextListener so it is used when server
 * starts to call some init or setup methods, it also loads or creates
 * blockchain.data and on shutdown it saves loaded data.
 *
 */
public class BlockStorage implements Serializable, ServletContextListener {
	private static final long serialVersionUID = 1L;
	private final String STORELOCATION = "blockchain.data";
	private List<Block> blockchain = new ArrayList<>();
	private static BlockStorage INSTANCE = new BlockStorage();
	private File blockchainFile = null;

	/**
	 * Default constructor.
	 */
	private BlockStorage() {
	}

	/**
	 * 
	 * @return Returns instance which contains blockchain collection.
	 */
	public static BlockStorage getInstance() {
		return INSTANCE;
	}

	public void addBlock(Block block) {
		blockchain.add(block);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This method saves blockchain collection to file on server.
	 * </p>
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Writing file here: " + blockchainFile.getAbsolutePath());
		try {
			@SuppressWarnings("resource")
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(blockchainFile));
			oos.writeObject(BlockStorage.getInstance().blockchain);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This method loads or creates blockchain.data and calls
	 * MongoConnectionUtil.setup() to setup connection settings so wildfly can
	 * connect to mongo database.
	 * </p>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		System.out.println("Loading blockchain on startup...");
		blockchainFile = new File(STORELOCATION);
		if (blockchainFile != null && blockchainFile.exists() && !blockchainFile.isDirectory()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(blockchainFile));
				BlockStorage.getInstance().blockchain = (List<Block>) ois.readObject();
				ois.close();
				if (BlockStorage.getInstance().blockchain.size() == 0)
					BlockStorage.getInstance().blockchain.add(new Block("0", new Evidence()));
				System.out.println("Blockchain loaded.");
				System.out.println("blockchain size: " + BlockStorage.INSTANCE.blockchain.size());
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (!blockchainFile.exists()) {
			BlockStorage.getInstance().blockchain.add(new Block("0", new Evidence()));
		}
		LoggerUtil.init();
		MongoConnectionUtil.setup();

	}

	/**
	 * 
	 * @return Return blockchain collection.
	 */
	public List<Block> getBlockchain() {
		return blockchain;
	}

	/**
	 * 
	 * @param blockchain
	 *            Sets blockchain collection.
	 */
	public void setBlockchain(List<Block> blockchain) {
		this.blockchain = blockchain;
	}

}
