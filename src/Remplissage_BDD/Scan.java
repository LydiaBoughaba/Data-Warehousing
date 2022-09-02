package Remplissage_BDD;

import java.util.ArrayList;

import java.util.List;

import cleaner.*;

public class Scan {
	private static final char DEFAULT__SEPARATOR = ',';
	private static final char DEFAULT__QUOTE = '"';
	
	public static List<String> parseLine(String cvsLine) { // 1 
	    return parseLine(cvsLine, DEFAULT__SEPARATOR, DEFAULT__QUOTE);
	}
	public static List<String> parseLine(String cvsLine, char separators) { // 2
	    return parseLine(cvsLine, separators, DEFAULT__QUOTE);
	}
	public static List<String> parseLine(String cvsLine, char separators, char customQuote) { // 3 
	List<String> result = new ArrayList<>();
	//if empty, return!
	String cvsLine2 = cvsLine;
	if (cvsLine == null && cvsLine2.isEmpty()) {
	     return result;
	 }
	if (customQuote == ' ') {
	    customQuote = DEFAULT__QUOTE;
	}
	if (separators == ' ') {
	    separators = DEFAULT__SEPARATOR;
	}
	StringBuffer curVal = new StringBuffer();
	boolean inQuotes = false;
	boolean startCollectChar = false;
	boolean doubleQuotesInColumn = false;
	char[]chars = cvsLine.toCharArray();
	for (char ch : chars) {
	if (inQuotes) {
	    startCollectChar = true;
	    if (ch == customQuote) {
	        inQuotes = false;
	        doubleQuotesInColumn = false;
	    } else {
	//Fixed : allow "" in custom quote enclosed
	 if (ch == '\"') {
	     if (!doubleQuotesInColumn) {
	         curVal.append(ch);
	         doubleQuotesInColumn = true;
	     }
	 } else {
	     curVal.append(ch);
	 }
	    }
	} else {
	    if (ch == customQuote) {
	inQuotes = true;
	//Fixed : allow "" in empty quote enclosed
	 if (chars[0]!= '"' && customQuote == '\"') {
	     curVal.append('"');
	 }
	//double quotes in column will hit this!
	 if (startCollectChar) {
	     curVal.append('"');
	 }
	} else if (ch == separators) {
	result.add(curVal.toString());
	curVal = new StringBuffer();
	startCollectChar = false;
	    } else if (ch == '\r') {
	       //ignore LF characters
	        continue;
	    } else if (ch == '\n') {
	       //the end, break!
	        break;
	    } else {
	        curVal.append(ch);
	    }
	}
	}
	result.add(curVal.toString());
	    return result;
	}
	
	
}