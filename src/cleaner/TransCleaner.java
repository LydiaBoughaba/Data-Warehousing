package cleaner;

import java.io.IOException;

public class TransCleaner extends AbsractCleaner {
	public TransCleaner()
	{
		
	}
    public TransCleaner(String filePath, String cleanedFilePath, String dirtyFilePath) {
        super(filePath, cleanedFilePath, dirtyFilePath, 10);
    }

    @Override
    boolean validateRecord(String record) {
        if (Utils.validateBankTo(fields[0]) ||
	        Utils.validateBankTo(fields[1]) ||
	        Utils.validateBankTo(fields[2]) ||
	        Utils.validateBankTo(fields[3]) ||
	        Utils.validateBankTo(fields[4]) ||
	        Utils.validateBankTo(fields[5]) ||
	        Utils.validateBankTo(fields[6]) ||
	        Utils.validateBankTo(fields[7]) ||
	        Utils.validateBankTo(fields[8]) ||
	        Utils.validateBankTo(fields[9]))
            return false;
        return true;
        
    }

    public static void nettoyer() throws IOException {
        String filePath = "dataset/trans.csv";
        String cleanedFilePath = "dataset/trans_clean.csv";
        String dirtyFilePath = "dataset/trans_dirty.csv";

        AbsractCleaner cleaner = new TransCleaner(filePath, cleanedFilePath, dirtyFilePath);
        cleaner.run();
        cleaner.close();
    }
}
