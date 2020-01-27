package com.cleancoder.args;

import java.util.*;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

public class Map{
	public HashMap<Character, ArgumentMarshaler> marshalers=new HashMap<Character, ArgumentMarshaler>();
    public HashSet<Character> argsFound=new HashSet<Character>();
    public HashMap<Character, ArgumentMarshaler> mapgetter()
    {
      return marshalers;
    }
    public HashSet<Character> setgetter()
    {
      return argsFound;
    }
}