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
package fr.ritaly.dualcommander;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.lang.StringUtils;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

public class DualCommander extends JFrame {

	private static final long serialVersionUID = 5445919782222373150L;

	// TODO Add icons for each action
	private final class ViewAction extends AbstractAction {

		private static final long serialVersionUID = -3885021368508066211L;

		public ViewAction() {
			super("F3 View");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(DualCommander.this, "Not implemented yet", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private final class EditAction extends AbstractAction {

		private static final long serialVersionUID = -4570243231693650751L;

		public EditAction() {
			super("F4 Edit");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(DualCommander.this, "Not implemented yet", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private final class CopyAction extends AbstractAction {

		private static final long serialVersionUID = -4681732083985135686L;

		public CopyAction() {
			super("F5 Copy");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(DualCommander.this, "Not implemented yet", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private final class MoveAction extends AbstractAction {

		private static final long serialVersionUID = -3452333607164390841L;

		public MoveAction() {
			super("F6 Move");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(DualCommander.this, "Not implemented yet", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private final class MkdirAction extends AbstractAction {

		private static final long serialVersionUID = -6354029847164675819L;

		public MkdirAction() {
			super("F7 Mkdir");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(DualCommander.this, "Not implemented yet", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private final class DeleteAction extends AbstractAction {

		private static final long serialVersionUID = -4059922323563836824L;

		public DeleteAction() {
			super("F8 Delete");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(DualCommander.this, "Not implemented yet", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private final class QuitAction extends AbstractAction {

		private static final long serialVersionUID = 388422465301643805L;

		public QuitAction() {
			super("Alt+F4 Quit");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Ask a user confirm before exiting
			DualCommander.this.dispose();
		}
	}

	private final JButton viewButton = new JButton(new ViewAction());

	private final JButton editButton = new JButton(new EditAction());

	private final JButton copyButton = new JButton(new CopyAction());

	private final JButton moveButton = new JButton(new MoveAction());

	private final JButton mkdirButton = new JButton(new MkdirAction());

	private final JButton deleteButton = new JButton(new DeleteAction());

	private final JButton quitButton = new JButton(new QuitAction());

	public DualCommander() {
		// TODO Generate a fat jar at build time
		// TODO Insert version number in frame's title & build id
		super("Dual Commander");

		try {
			// Apply the JGoodies L&F
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
		} catch (Exception e) {
			// Not supposed to happen
		}

		// Layout, columns & rows
		setLayout(new MigLayout("insets 5px", "[grow][grow]", "[grow][]"));

		final JList<Object> leftList = new JList<>();
		final JList<Object> rightList = new JList<>();

		getContentPane().add(new JScrollPane(leftList), "grow");
		getContentPane().add(new JScrollPane(rightList), "grow, wrap");

		// The 7 buttons must all have the same width (they must belong to the
		// same size group)
		final JPanel buttonPanel = new JPanel(
				new MigLayout("insets 0px", StringUtils.repeat("[grow, sizegroup g1]", 7), "[grow]"));
		buttonPanel.add(viewButton, "grow");
		buttonPanel.add(editButton, "grow");
		buttonPanel.add(copyButton, "grow");
		buttonPanel.add(moveButton, "grow");
		buttonPanel.add(mkdirButton, "grow");
		buttonPanel.add(deleteButton, "grow");
		buttonPanel.add(quitButton, "grow");

		getContentPane().add(buttonPanel, "grow, span 2");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new DualCommander().setVisible(true);
			}
		});
	}
}