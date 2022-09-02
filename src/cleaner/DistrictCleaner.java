package cleaner;

import java.io.IOException;

public class DistrictCleaner extends AbsractCleaner {
	public DistrictCleaner()
	{
		
	}
    public DistrictCleaner(String filePath, String cleanedFilePath, String dirtyFilePath) {
        super(filePath, cleanedFilePath, dirtyFilePath, 16);
    }

    @Override
    boolean validateRecord(String record) {
    	//System.out.println(fields[11]);
        if ( Utils.validateBankTo(fields[0]) ||
        Utils.validateBankTo(fields[1]) ||
        Utils.validateBankTo(fields[2]) ||
        Utils.validateBankTo(fields[3]) ||
        Utils.validateBankTo(fields[4]) ||
        Utils.validateBankTo(fields[5]) ||
        Utils.validateBankTo(fields[6]) ||
        Utils.validateBankTo(fields[7]) ||
        Utils.validateBankTo(fields[8]) ||
        Utils.validateBankTo(fields[9]) ||
        Utils.validateBankTo(fields[10]) ||
        Utils.validateBankTo(fields[11]) ||
        Utils.validateBankTo(fields[12]) ||
        Utils.validateBankTo(fields[13]) ||
        Utils.validateBankTo(fields[14]) ||
        Utils.validateBankTo(fields[15]) )
        	
        		return false;

        return true;
    }

    public static void nettoyer() throws IOException {
        String filePath = "dataset/district.csv";
        String cleanedFilePath = "dataset/district_clean.csv";
        String dirtyFilePath = "dataset/district_dirty.csv";

        AbsractCleaner cleaner = new DistrictCleaner(filePath, cleanedFilePath, dirtyFilePath);
        cleaner.run();
        cleaner.close();
    }
}
