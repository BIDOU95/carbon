package org.example.lacarteauxtresors.utils;

import org.example.lacarteauxtresors.exception.ExportMapException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

  private FileUtils() {}

  public static List<String> readFile(String filename) {
    List<String> lines = new ArrayList<>();
    File file = new File(filename);
    try {
      InputStream inputStream = new FileInputStream(file);
      try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
        String line;
        while ((line = br.readLine()) != null) {
          lines.add(line);
        }
      } catch (IOException exception) {
        // LOGGER
      }
    } catch (FileNotFoundException exception) {
      // LOGGER
    }
    return lines;
  }

  public static void exportFile(String filename, List<String> rows) throws ExportMapException {
    try (FileWriter fileWriter = new FileWriter(filename)) {
      for (String row : rows) {
          fileWriter.write(row + "\n");
      }
    } catch (IOException exception) {
      throw new ExportMapException("Error while writing file" + filename, exception);
    }
  }
}
