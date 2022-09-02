package cons;

public class Constants {

    public static String[][] freqNames = new String[][] {
            {"POPLATEK MESICNE", "POPLATEKMESICNE"},
            {"POPLATEK TYDNE", "POPLATEKTYDNE"},
            {"POPLATEK PO OBRATU", "POPLATEKPOOBRATU"}
    };

    public static String[][] cardTypes = new String[][] {
            {"classic"},
            {"junior"},
            {"gold", "golden"}
    };

    public static String[][] dispTypes = new String[][] {
            {"OWNER"},
            {"DISPONENT"}
    };

    public static String[][] loanTypes = new String[][] {
            {"A"},
            {"B"},
            {"C"},
            {"D"}
    };

    public static String[][] kSymbolTypes = new String[][] {
            {"SIPO"},
            {"POJISTNE"},
            {"LEASING"},
            {"UVER"},
            {"DUCHOD"},
            {"SLUZBY"},
            {"UROK"},
            {"SANKC. UROK"},
            {""}
    };

    public static String[][] transTypes = new String[][] {
            {"PRIJEM"},
            {"VYDAJ"}
    };

    public static String[][] operationTypes = new String[][] {
            {"VYBER KARTOU"},
            {"VKLAD"},
            {"PREVOD Z UCTU"},
            {"VYBER"},
            {"PREVOD NA UCET"}
    };
}
