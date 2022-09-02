package cleaner;

import cons.Status;

import java.io.*;

public abstract class AbsractCleaner {


    protected String filePath;
    protected String cleanedFilePath;
    protected String dirtyFilePath;

    private BufferedReader reader;
    private BufferedWriter cleanWriter;
    private BufferedWriter dirtyWriter;

    protected String repairedRecord;
    protected String replacement;
    protected boolean isRepaired;
    protected int numFields;
    protected String[] fields;

    public AbsractCleaner()
	{
		
	}
    public AbsractCleaner(String filePath, String cleanedFilePath, String dirtyFilePath, int numFields) {
        this.filePath = filePath;
        this.cleanedFilePath = cleanedFilePath;
        this.dirtyFilePath = dirtyFilePath;
        this.numFields = numFields;
    }

    public void run() throws IOException {

        reader = new BufferedReader(new FileReader(filePath));
        cleanWriter = new BufferedWriter(new FileWriter(cleanedFilePath));
        dirtyWriter = new BufferedWriter(new FileWriter(dirtyFilePath));

        String line = reader.readLine();
        // header
        writeClean(line);
        writeDirty(line);
        while ((line = reader.readLine()) != null) {
            Status recordStatus = isRecordLegal(line);
            if (recordStatus == Status.LEGAL)
                writeClean(line);
            else if (recordStatus == Status.REPAIRED)
                writeClean(repairedRecord);
            else
                writeDirty(line);
        }
    }

    Status isRecordLegal(String record) {
        isRepaired = false;
        if (!validateFieldNum(record))
            return Status.ILLEGAL;

        if(validateRecord(record)) {
            if (!isRepaired)
                return Status.LEGAL;
            else {
                repairedRecord = String.join(Utils.COMMA, fields);
                return Status.REPAIRED;
            }
        } else {
            return Status.ILLEGAL;
        }
    }

    abstract boolean validateRecord(String record);

    boolean validateFieldNum(String record) {
        fields = record.split(Utils.COMMA);
        return fields.length == numFields;
    }

    void writeClean(String record) throws IOException {
        cleanWriter.write(record);
        cleanWriter.newLine();
    }

    void writeDirty(String record) throws IOException {
        dirtyWriter.write(record);
        dirtyWriter.newLine();
    }

    public void close() throws IOException {
        reader.close();
        cleanWriter.close();
        dirtyWriter.close();
    }

    protected Status searchStr(String str, String[][] candidateLists) {

        for (String[] candidateList : candidateLists) {
            for (int i = 0; i < candidateList.length; i++) {
                if (str.equals(candidateList[i])) {
                    if (i == 0) {
                        return Status.LEGAL;
                    } else {
                        replacement = candidateList[0];
                        return Status.REPAIRED;
                    }
                }
            }
        }
        return Status.ILLEGAL;
    }
}
