package cleaner;

import java.io.IOException;

public class LoanCleaner extends AbsractCleaner {
	public LoanCleaner()
	{
		
	}
    public LoanCleaner(String filePath, String cleanedFilePath, String dirtyFilePath) {
        super(filePath, cleanedFilePath, dirtyFilePath, 7);
    }

    @Override
    boolean validateRecord(String record) {
        if (Utils.validateBankTo(fields[0]) ||
        		Utils.validateBankTo(fields[1]) ||
        		Utils.validateBankTo(fields[2]) ||
        		Utils.validateBankTo(fields[3]) ||
        		Utils.validateBankTo(fields[4]) ||
        		Utils.validateBankTo(fields[5]) ||
        		Utils.validateBankTo(fields[6]))
            return false;
        return true;
    }

    public static void nettoyer() throws IOException {
        String filePath = "dataset/loan.csv";
        String cleanedFilePath = "dataset/loan_clean.csv";
        String dirtyFilePath = "dataset/loan_dirty.csv";

        AbsractCleaner cleaner = new LoanCleaner(filePath, cleanedFilePath, dirtyFilePath);
        cleaner.run();
        cleaner.close();
    }
}
