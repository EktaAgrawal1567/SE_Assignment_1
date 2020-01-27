package com.cleancoder.args;

import java.util.*;
import java.util.Iterator;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

public class Parser{
	public HashMap<Character, ArgumentMarshaler> marshalers;
	public Parser(String schema) throws ArgsException{
		marshalers=new HashMap<Character, ArgumentMarshaler>();
		parseSchema(schema);
	}
    public void parseSchema(String schema) throws ArgsException {
    //separate the arguments through ','
      for (String element : schema.split(","))
        if (element.length() > 0)
          parseSchemaElement(element.trim());
    }
    //insert the element id and corrresponding argument marshaler in map
    public void parseSchemaElement(String element) throws ArgsException {
      char elementId = element.charAt(0);
      String elementTail = element.substring(1);
      validateSchemaElementId(elementId);
      if (elementTail.length() == 0)
        marshalers.put(elementId, new BooleanArgumentMarshaler());
      else if (elementTail.equals("*"))
        marshalers.put(elementId, new StringArgumentMarshaler());
      else if (elementTail.equals("#"))
        marshalers.put(elementId, new IntegerArgumentMarshaler());
      else if (elementTail.equals("##"))
        marshalers.put(elementId, new DoubleArgumentMarshaler());
      else if (elementTail.equals("[*]"))
        marshalers.put(elementId, new StringArrayArgumentMarshaler());
      else if (elementTail.equals("&"))
        marshalers.put(elementId, new MapArgumentMarshaler());
      else
        throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, elementTail);
    }
    private void validateSchemaElementId(char elementId) throws ArgsException {
    if (!Character.isLetter(elementId))
      throw new ArgsException(INVALID_ARGUMENT_NAME, elementId, null);
  }
}