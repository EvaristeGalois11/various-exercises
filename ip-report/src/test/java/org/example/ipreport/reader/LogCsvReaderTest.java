package org.example.ipreport.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import org.example.ipreport.util.PathUtils;
import org.junit.jupiter.api.Test;

class LogCsvReaderTest {
  private final LogCsvReader logCsvReader = new LogCsvReader();

  @Test
  void readEmptyLogsTest() throws IOException, URISyntaxException {
    var testFile = PathUtils.getTestFile("empty.log");
    var logs = logCsvReader.readLogs(testFile);
    assertEquals(0, logs.size());
  }

  @Test
  void readLogsTest() throws IOException, URISyntaxException {
    var testFile = PathUtils.getTestFile("test.log");
    var logs = logCsvReader.readLogs(testFile);
    assertEquals(5, logs.size());
  }
}
