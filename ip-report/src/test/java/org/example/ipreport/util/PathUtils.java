package org.example.ipreport.util;

import java.net.URISyntaxException;
import java.nio.file.Path;

public class PathUtils {
  public static Path getTestFile(String file) throws URISyntaxException {
    return Path.of(PathUtils.class.getClassLoader().getResource(file).toURI());
  }
}
