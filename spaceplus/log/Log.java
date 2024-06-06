package spaceplus.log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;

import java.util.Date;

public class Log implements Logger {
  private final String FILEPATH = "logs.txt";
  private final SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");

  public void error(String message) {
    log("Error", message);
  }

  public void warn(String message) {
    log("Warn", message);
  }

  public void info(String message) {
    log("Info", message);
  }

  public void log(String level, String message) {
    try (FileWriter fileWriter = new FileWriter(FILEPATH, true);
         PrintWriter writer = new PrintWriter(fileWriter)) {
      String time = dataFormat.format(new Date());
      writer.println("[" + time + "] " + level + ": " + message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
