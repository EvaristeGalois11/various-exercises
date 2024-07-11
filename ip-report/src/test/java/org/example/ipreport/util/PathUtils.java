/*
 *Copyright (C) 2024 Claudio Nave
 *
 *This program is free software: you can redistribute it and/or modify
 *it under the terms of the GNU General Public License as published by
 *the Free Software Foundation, either version 3 of the License, or
 *(at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package org.example.ipreport.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathUtils {
  public static Path getTestFile(String file) throws URISyntaxException {
    return Path.of(PathUtils.class.getClassLoader().getResource(file).toURI());
  }

  public static void truncateFile(String file) throws IOException, URISyntaxException {
    var path = PathUtils.getTestFile(file);
    Files.deleteIfExists(path);
    Files.createFile(path);
  }
}
