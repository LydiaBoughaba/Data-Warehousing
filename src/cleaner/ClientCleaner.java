package cleaner;

import java.io.IOException;

public class ClientCleaner extends AbsractCleaner {

	public ClientCleaner()
	{
		
	}
    public ClientCleaner(String filePath, String cleanedFilePath, String dirtyFilePath) {
        super(filePath, cleanedFilePath, dirtyFilePath, 4);
    }

    @Override
    boolean validateRecord(String record) {
        if (Utils.validateBankTo(fields[0]) ||
    		Utils.validateBankTo(fields[1]) ||
    		Utils.validateBankTo(fields[2]) ||
    		Utils.validateBankTo(fields[3]))
        	return false;
        return true;
    }
        
 

    public static void nettoyer() throws IOException {
        String filePath = "dataset/client.csv";
        String cleanedFilePath = "dataset/client_clean.csv";
        String dirtyFilePath = "dataset/client_dirty.csv";

        AbsractCleaner cleaner = new ClientCleaner(filePath, cleanedFilePath, dirtyFilePath);
        cleaner.run();
        cleaner.close();
    }
}
