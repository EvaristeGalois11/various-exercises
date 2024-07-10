package org.example.ipreportgenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import org.example.ipreportgenerator.reader.LogCsvReader;
import org.junit.jupiter.api.Test;

class LogCsvReaderTest {
  private final LogCsvReader logCsvReader = new LogCsvReader();

  @Test
  void readEmptyLogsTest() throws IOException, URISyntaxException {
    var testFile = Path.of(getClass().getClassLoader().getResource("empty.log").toURI());
    var logs = logCsvReader.readLogs(testFile);
    assertEquals(0, logs.size());
  }

  @Test
  void readLogsTest() throws IOException, URISyntaxException {
    var testFile = Path.of(getClass().getClassLoader().getResource("test.log").toURI());
    var logs = logCsvReader.readLogs(testFile);
    assertEquals(5, logs.size());
  }
}
