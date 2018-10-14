package main.ie.murph.blockchain.algorithm;

import java.util.ArrayList;
import java.util.List;

public class TestBlockChain {

	private static List<Block> blockchain = new ArrayList<Block>();
	private final static int difficultyLength = 5;

	public static void main(String[] args) {
		runMining();
		createBlockChainJson();
	}

	private static void runMining() {
		// Add our blocks to the Blockchain ArrayList
		System.out.println("Trying to Mine block 1... ");
		addBlock(new Block("First block - €100: ", "0"));

		System.out.println("Trying to Mine block 2... ");
		addBlock(new Block("Second block - €20: ", blockchain.get(blockchain.size() - 1).hash));

		System.out.println("Trying to Mine block 3... ");
		addBlock(new Block("Third block - €1100: ", blockchain.get(blockchain.size() - 1).calculateHash()));
	}
	
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficultyLength);
		blockchain.add(newBlock);
	}
	
	private static void createBlockChainJson() {
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		String blockchainJson = StringUtil.getJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
	}

	public static Boolean isChainValid() {
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
		return isValid;
	}
	
	private static String replaceCharactersWithZerosOfLengthDifficulty() {
		return new String(new char[difficultyLength]).replace('\0', '0');
	}
	
	private static int sizeOfBlockChainArray() {
		return blockchain.size();
	}
	
	private static Block getCurrentBlockchainElement(int iteration) {
		return blockchain.get(iteration);
	}

	private static Block getPreviousBlockchainElement(int iteration) {
		return blockchain.get(iteration - 1);
	}

	private static boolean isBlockChainHashValid(Block currentBlock, Block previousBlock, String hashTarget,
			boolean isValid) {

		// compare registered hash and calculated hash:
		if (currentCryptoAlgorithmAreNotEqual(currentBlock)) {
			System.out.println("Current registered hash public variable .hash and calculated() method hash Hashes are not equal");
			isValid = false;
		}

		// compare previous hash and registered previous hash
		if (currentAndPreviousCryptoAlgorithmHashesAreNotEqual(previousBlock, currentBlock)) {
			System.out.println("Previous hash Hashes not equal");
			isValid = false;
		}

		// check if hash is solved
		if (blockchainHasNotBeenMined(currentBlock, hashTarget)) {
			System.out.println("This block hasn't been mined");
			isValid = false;
		}

		return isValid;
	}

//	So, both .hash public variable and calculateHash() method should be equal
	private static boolean currentCryptoAlgorithmAreNotEqual(Block currentBlock) {
		return !currentBlock.hash.equals(currentBlock.calculateHash());
	}

	private static boolean currentAndPreviousCryptoAlgorithmHashesAreNotEqual(Block previousBlock, Block currentBlock) {
		return !previousBlock.hash.equals(currentBlock.previousHash);
	}

	private static boolean blockchainHasNotBeenMined(Block currentBlock, String hashTarget) {
		return !currentBlock.hash.substring(0, difficultyLength).equals(hashTarget);
	}
}
