package com.cleancoder.args;

public class ArgsMain {
  public static void main(String[] args) {
    try {
      Args arg = new Args("l,s*,n#,a##,p[*]", args);
      boolean logging = arg.getBoolean('l');
      int port = arg.getInt('n');
      String directory = arg.getString('s');
      executeApplication(logging, port, directory);
    } catch (ArgsException e) {
      System.out.printf("Argument error: %s\n", e.errorMessage());
    }
  }

  private static void executeApplication(boolean logging, int port, String directory) {
    System.out.printf("logging is %s, port:%d, directory:%s\n",logging, port, directory);
  }
}