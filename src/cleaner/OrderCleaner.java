package cleaner;

import java.io.IOException;

public class OrderCleaner extends AbsractCleaner {
	public OrderCleaner()
	{
		
	}

    public OrderCleaner(String filePath, String cleanedFilePath, String dirtyFilePath) {
        super(filePath, cleanedFilePath, dirtyFilePath, 6);
    }

    @Override
    boolean validateRecord(String record) {
        if (Utils.validateBankTo(fields[0]) ||
    		Utils.validateBankTo(fields[1]) ||
    		Utils.validateBankTo(fields[2]) ||
    		Utils.validateBankTo(fields[3]) ||
    		Utils.validateBankTo(fields[4]) ||
    		Utils.validateBankTo(fields[5]) )
            return false;
        return true;
        }

      

    public static void nettoyer() throws IOException {
        String filePath = "dataset/order.csv";
        String cleanedFilePath = "dataset/order_clean.csv";
        String dirtyFilePath = "dataset/order_dirty.csv";

        AbsractCleaner cleaner = new OrderCleaner(filePath, cleanedFilePath, dirtyFilePath);
        cleaner.run();
        cleaner.close();
    }
}
