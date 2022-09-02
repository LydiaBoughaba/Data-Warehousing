package cleaner;

import java.io.IOException;

public class CardCleaner extends AbsractCleaner {
	
	public CardCleaner()
	{
		
	}
    public CardCleaner(String filePath, String cleanedFilePath, String dirtyFilePath) {
        super(filePath, cleanedFilePath, dirtyFilePath,4);
    }

    @Override
    boolean validateRecord(String record) {

        if(Utils.validateBankTo(fields[0]) ||
        		Utils.validateBankTo(fields[1]) ||
        		Utils.validateBankTo(fields[2]) ||
        		Utils.validateBankTo(fields[3]))
            return false;
        return true;

    }

    public static void nettoyer() throws IOException {
        String filePath = "dataset/card.csv";
        String cleanedFilePath = "dataset/card_clean.csv";
        String dirtyFilePath = "dataset/card_dirty.csv";

        AbsractCleaner cleaner = new CardCleaner(filePath, cleanedFilePath, dirtyFilePath);
        cleaner.run();
        cleaner.close();
    }
}
