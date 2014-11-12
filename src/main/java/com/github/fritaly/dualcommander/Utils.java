/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.fritaly.dualcommander;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.JLabel;

import org.apache.commons.lang.Validate;

import com.github.fritaly.dualcommander.event.FileEvent;
import com.github.fritaly.dualcommander.event.FileListener;

public class Utils {

	/**
	 * Recursively deletes the given file or directory and notifies the provided
	 * listener when a file / directory has been deleted.
	 *
	 * @param file
	 *            the file / directory to delete. Can't be null.
	 * @param listener
	 *            a possible listener to be notified of file deletions. Can be
	 *            null.
	 */
	public static void deleteRecursively(File file, FileListener listener) {
		// The given file can be a file or a directory
		// The given listener can be null
		Validate.notNull(file, "The given file is null");

		if (file.isDirectory()) {
			// Delete the directory recursively
			for (File entry : file.listFiles()) {
				deleteRecursively(entry, listener);
			}
		}

		// Delete the file or the empty directory
		file.delete();

		if (listener != null) {
			listener.fileDeleted(new FileEvent(file));
		}
	}

	public static Color getDefaultBackgroundColor() {
		return new JLabel().getBackground();
	}

	private static Properties getApplicationProperties() {
		try {
			final Properties properties = new Properties();
			properties.load(Utils.class.getResourceAsStream("/application.properties"));

			return properties;
		} catch (IOException e) {
			// Should never happen
			throw new RuntimeException("Unable to load application properties from resource file 'application.properties'", e);
		}
	}

	public static String getApplicationVersion() {
		return getApplicationProperties().getProperty("version");
	}

	public static Date getApplicationReleaseDate() {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm.ss (Z)").parse(getApplicationProperties().getProperty("release.date"));
		} catch (ParseException e) {
			// Return null (happens when the value hasn't been replaced and is
			// set to @RELEASE_DATE@)
			return null;
		}
	}
}