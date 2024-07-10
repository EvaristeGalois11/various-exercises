package org.example.ipreportgenerator.reader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.example.ipreportgenerator.model.Log;

public interface LogReader {
  List<Log> readLogs(Path path) throws IOException;
}
