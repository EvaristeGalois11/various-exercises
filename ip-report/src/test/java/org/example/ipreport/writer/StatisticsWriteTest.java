package org.example.ipreport.writer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

import org.example.ipreport.util.PathUtils;
import org.junit.jupiter.api.BeforeEach;

abstract class StatisticsWriteTest {
  @BeforeEach
  void prepareFile() throws IOException, URISyntaxException {
    var testFile = PathUtils.getTestFile("write-statistics.csv");
    Files.deleteIfExists(testFile);
    Files.createFile(testFile);
  }
}
