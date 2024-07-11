package org.example.ipreport.reader;

import org.example.ipreport.model.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.List;

public class LogCsvReader implements LogReader {
  @Override
  public List<Log> readLogs(Path path) throws IOException {
    try (var lines = Files.lines(path)) {
      return lines
          .map(line -> line.split(";"))
          .map(
              a -> new Log(Instant.parse(a[0]), Long.parseLong(a[1]), Integer.parseInt(a[2]), a[3]))
          .toList();
    }
  }
}
