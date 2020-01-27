package com.cleancoder.args;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

public class Args {
    private Parser parse;
    private ArgumentPopulater populate;
    public Args(String schema, String[] args) throws ArgsException {
        //call Parser to parse the schema
        parse=new Parser(schema);
        //call ArgumentPopulater to populate the arguments into corresponding Argument Marshaler
        populate=new ArgumentPopulater(schema,args, parse);
  }
  public boolean has(char arg) {
    return populate.argsFound.contains(arg);
  }

  public int nextArgument() {
    return populate.currentArgument.nextIndex();
  }

  public boolean getBoolean(char arg) {
    return BooleanArgumentMarshaler.getValue(parse.marshalers.get(arg));
  }

  public String getString(char arg) {
    return StringArgumentMarshaler.getValue(parse.marshalers.get(arg));
  }

  public int getInt(char arg) {
    return IntegerArgumentMarshaler.getValue(parse.marshalers.get(arg));
  }

  public double getDouble(char arg) {
    return DoubleArgumentMarshaler.getValue(parse.marshalers.get(arg));
  }

  public String[] getStringArray(char arg) {
    return StringArrayArgumentMarshaler.getValue(parse.marshalers.get(arg));
  }

  public Map<String, String> getMap(char arg) {
    return MapArgumentMarshaler.getValue(parse.marshalers.get(arg));
  }
}