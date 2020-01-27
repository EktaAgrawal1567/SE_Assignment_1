package com.cleancoder.args;

import java.util.*;
import java.util.Iterator;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

public class ArgumentPopulater{
  public HashSet<Character> argsFound;
	public ListIterator<String> currentArgument;
  public ArgumentPopulater(String schema, String[] args, Parser parse) throws ArgsException{
    argsFound=new HashSet<Character>();
    parseArgumentStrings(Arrays.asList(args), parse);
  }
   public void parseArgumentStrings(List<String> argsList, Parser parse) throws ArgsException {
    for (currentArgument = argsList.listIterator(); currentArgument.hasNext();) {
      String argString = currentArgument.next();
      if (argString.startsWith("-")) {
        parseArgumentCharacters(argString.substring(1), parse);
      } else {
        currentArgument.previous();
        break;
      }
    }
  }
  private void parseArgumentCharacters(String argChars, Parser parse) throws ArgsException {
    for (int i = 0; i < argChars.length(); i++)
      parseArgumentCharacter(argChars.charAt(i), parse);
  }
  private void parseArgumentCharacter(char argChar, Parser parse) throws ArgsException {
    ArgumentMarshaler m = parse.marshalers.get(argChar);
    if (m == null) {
      throw new ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
    } else {
      argsFound.add(argChar);
      try {
        m.set(currentArgument);
      } catch (ArgsException e) {
        e.setErrorArgumentId(argChar);
        throw e;
      }
    }
  }

}