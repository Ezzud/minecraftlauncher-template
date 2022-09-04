package fr.ezzud.defaultlauncher.addons;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;



public class CustomTextField extends JPasswordField {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomTextField() {
            final UndoManager undoMgr = new UndoManager();

            // Add listener for inaudible events
            getDocument().addUndoableEditListener(new UndoableEditListener() {
                public void undoableEditHappened(UndoableEditEvent pEvt) {
                    undoMgr.addEdit(pEvt.getEdit());
                }
            });

            // Add undo/redo actions
            getActionMap().put("Undo", new AbstractAction("Undo") {
                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent pEvt) {
                    try {
                        if (undoMgr.canUndo()) {
                            undoMgr.undo();
                        }
                    } catch (CannotUndoException e) {
                        e.printStackTrace();
                    }
                }
            });
            getActionMap().put("Redo", new AbstractAction("Redo") {
                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent pEvt) {
                    try {
                        if (undoMgr.canRedo()) {
                            undoMgr.redo();
                        }
                    } catch (CannotRedoException e) {
                        e.printStackTrace();
                    }
                }
            });

            // Create keyboard accelerators for undo/redo actions (Ctrl+Z/Ctrl+Y)
            getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK),
            		"Undo");
            getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK),
            		"Redo");
        }
    }