package com.qa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ResourceLoader {
	
	
	public static InputStream getResource(String path) throws IOException {
		InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
		if(Objects.nonNull(stream)) {
			return stream;
		}
		else {
			return Files.newInputStream(Path.of(path));
		}
	}

}
