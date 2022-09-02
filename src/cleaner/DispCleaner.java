package cleaner;

import java.io.IOException;

public class DispCleaner extends AbsractCleaner {
	public DispCleaner()
	{
		
	}
    public DispCleaner(String filePath, String cleanedFilePath, String dirtyFilePath ) {
        super(filePath, cleanedFilePath, dirtyFilePath, 4);
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
        String filePath = "dataset/disp.csv";
        String cleanedFilePath = "dataset/disp_clean.csv";
        String dirtyFilePath = "dataset/disp_dirty.csv";

        AbsractCleaner cleaner = new DispCleaner(filePath, cleanedFilePath, dirtyFilePath);
        cleaner.run();
        cleaner.close();
    }
}
