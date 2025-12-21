import pnc.solver.DictionaryIndexer;

public class Main {
    public static void main(String[] args) {
        DictionaryIndexer indexer = new DictionaryIndexer("");
        String wordToRank = "GOOGLE";
        long rank = indexer.rankWord(wordToRank);
        System.out.println("The rank of the word '" + wordToRank + "' is: " + rank);
    }
}
