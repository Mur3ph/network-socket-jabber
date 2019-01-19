package main.ie.murph.network.blockchain.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import main.ie.murph.network.utility.StringUtil;

public class RunBlockChainMining {

	private static final Logger LOGGER = Logger.getLogger(RunBlockChainMining.class);
	private static List<Block> blockchain = new ArrayList<Block>();
	private final static int difficultyLength = 5;

	public static void main(String[] args) {
		runMining();
		createBlockChainJson();
	}

	private static void runMining() {
		// Add our blocks to the Blockchain ArrayList
		LOGGER.info("++runMining(): Attempting to mine first block... ");
		addBlockToChainList(new Block("First block - €100: ", "0"));

		LOGGER.info("++runMining(): Attempting to mine second block... ");
		addBlockToChainList(new Block("Second block - €20: ", blockchain.get(blockchain.size() - 1).hash));

		LOGGER.info("++runMining(): Attempting to mine third block... ");
		addBlockToChainList(new Block("Third block - €1100: ", blockchain.get(blockchain.size() - 1).calculateHash()));
	}

	public static void addBlockToChainList(Block newBlock) {
		newBlock.mineBlock(difficultyLength);
		blockchain.add(newBlock);
	}

	private static void createBlockChainJson() {
		LOGGER.info("\n++createBlockChainJson(): Blockchain is Valid: " + isChainValid());
		String blockchainJson = StringUtil.getJson(blockchain);
		LOGGER.info("\n++createBlockChainJson(): The block chain: ");
		LOGGER.info("++createBlockChainJson(): " + blockchainJson);
	}

	public static Boolean isChainValid() {
		LOGGER.info("++isChainValid()");
		Block currentBlock;
		Block previousBlock;
		String hashTarget = replaceCharactersWithZerosOfLengthDifficulty();
		boolean isValid = true;

		// loop through blockchain to check hashes:
		for (int iteration = 1; iteration < sizeOfBlockChainArray(); iteration++) {
			currentBlock = getCurrentBlockchainElement(iteration);
			previousBlock = getPreviousBlockchainElement(iteration);
			isValid = isBlockChainHashValid(currentBlock, previousBlock, hashTarget, isValid);
		}
		LOGGER.info("--isChainValid()");
		return isValid;
	}

	private static String replaceCharactersWithZerosOfLengthDifficulty() {
		LOGGER.info("++replaceCharactersWithZerosOfLengthDifficulty()");
		return new String(new char[difficultyLength]).replace('\0', '0');
	}

	private static int sizeOfBlockChainArray() {
		LOGGER.info("++sizeOfBlockChainArray()");
		return blockchain.size();
	}

	private static Block getCurrentBlockchainElement(int iteration) {
		LOGGER.info("++getCurrentBlockchainElement()");
		return blockchain.get(iteration);
	}

	private static Block getPreviousBlockchainElement(int iteration) {
		LOGGER.info("++getCurrentBlockchainElement()");
		return blockchain.get(iteration - 1);
	}

	private static boolean isBlockChainHashValid(Block currentBlock, Block previousBlock, String hashTarget,
			boolean isValid) {
		LOGGER.info("++blockchainHasNotBeenMined()");

		// compare registered hash and calculated hash:
		if (currentCryptoAlgorithmAreNotEqual(currentBlock)) {
			LOGGER.info("++isBlockChainHashValid(): Current registered hash public variable .hash and calculated() method hash Hashes are not equal");
			isValid = false;
		}

		// compare previous hash and registered previous hash
		if (currentAndPreviousCryptoAlgorithmHashesAreNotEqual(previousBlock, currentBlock)) {
			LOGGER.info("++isBlockChainHashValid(): Previous hash Hashes not equal");
			isValid = false;
		}

		// check if hash is solved
		if (blockchainHasNotBeenMined(currentBlock, hashTarget)) {
			LOGGER.info("++blockchainHasNotBeenMined(): This block hasn't been mined");
			isValid = false;
		}
		LOGGER.info("--blockchainHasNotBeenMined()");
		return isValid;
	}

	// So, both .hash public variable and calculateHash() method should be equal
	private static boolean currentCryptoAlgorithmAreNotEqual(Block currentBlock) {
		LOGGER.info("++currentCryptoAlgorithmAreNotEqual()");
		return !currentBlock.hash.equals(currentBlock.calculateHash());
	}

	private static boolean currentAndPreviousCryptoAlgorithmHashesAreNotEqual(Block previousBlock, Block currentBlock) {
		LOGGER.info("++currentAndPreviousCryptoAlgorithmHashesAreNotEqual()");
		return !previousBlock.hash.equals(currentBlock.previousHash);
	}

	private static boolean blockchainHasNotBeenMined(Block currentBlock, String hashTarget) {
		LOGGER.info("++currentAndPreviousCryptoAlgorithmHashesAreNotEqual()");
		return !currentBlock.hash.substring(0, difficultyLength).equals(hashTarget);
	}
}
