package cleaner;

import java.io.IOException;

public class AccountCleaner extends AbsractCleaner {


    public AccountCleaner(String filePath, String cleanedFilePath, String dirtyFilePath) {
        super(filePath, cleanedFilePath, dirtyFilePath, 4);
    }
    public AccountCleaner()
    {
    	
    }
    boolean validateRecord(String record) {
        if (Utils.validateBankTo(fields[0]) ||
    		Utils.validateBankTo(fields[1]) ||
    		Utils.validateBankTo(fields[2]) ||
    		Utils.validateBankTo(fields[3]))
            return false;
        return true;

    }

    public static void nettoyer() throws IOException {
        String filePath = "dataset/account.csv";
        String cleanedFilePath = "dataset/account_clean.csv";
        String dirtyFilePath = "dataset/account_dirty.csv";

        AbsractCleaner cleaner = new AccountCleaner(filePath, cleanedFilePath, dirtyFilePath);
        cleaner.run();
        cleaner.close();
    }
}
