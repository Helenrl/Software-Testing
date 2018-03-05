package com.bottleworks.dailymoney.calculator2;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;
import org.javia.arity.Util;

public class Evaluator {
	
  private static final char MINUS = '\u2212';
  private static final String INFINITY_UNICODE = "\u221e";
  private static final String INFINITY = "Infinity"; 
  private static final String NAN      = "NaN";
  
  private Symbols mSymbols = new Symbols();
  private String mErrorString;
  
  public Evaluator() {
  	mErrorString = "Error";
    try {
        // in calculator we use log() for base-10,
        // unlike in arity-lib where log() is natural logarithm
        mSymbols.define(mSymbols.compileWithName("log(x)=log10(x)"));
    } catch (SyntaxException e) {
        throw new Error("" + e); //never
    }
  }
  
  static boolean isOperator(char c) {
    //plus minus times div
    return "+\u2212\u00d7\u00f7/*".indexOf(c) != -1;
  }
	
	 private static final int ROUND_DIGITS = 1;
   public String evaluate(String input, int lineLength) throws SyntaxException {
       if (input.trim().equals("")) {
           return "";
       }

       // drop final infix operators (they can only result in error)
       int size = input.length();
       while (size > 0 && isOperator(input.charAt(size - 1))) {
           input = input.substring(0, size - 1);
           --size;
       }

       String result = Util.doubleToString(mSymbols.eval(input), lineLength, ROUND_DIGITS);
       if (result.equals(NAN)) { // treat NaN as Error
           // mIsError = true;
           return mErrorString;
       }
       return result.replace('-', MINUS).replace(INFINITY, INFINITY_UNICODE);
   }
}
