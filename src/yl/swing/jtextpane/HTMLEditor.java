package yl.swing.jtextpane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

//import java.awt.*;
//import java.awt.event.*;
//import java.io.*;
//
//import javax.swing.*;
//import javax.swing.event.*;
//import javax.swing.text.*;
//import javax.swing.text.html.*;
//import javax.swing.undo.*;


/**
 * 
 * @author Michael Forseth
 *
 */
public class HTMLEditor{
	private HTMLDocument document;
	private JTextPane textPane = new JTextPane();
	private HTMLEditorKit editorKit;
	protected UndoableEditListener undoHandler = new UndoHandler();
	protected UndoManager undo = new UndoManager();
	
	private UndoAction undoAction = new UndoAction();
	private RedoAction redoAction = new RedoAction();
		
	private Action cutAction = new DefaultEditorKit.CutAction();
	private Action copyAction = new DefaultEditorKit.CopyAction();
	private Action pasteAction = new DefaultEditorKit.PasteAction();

	private Action boldAction = new StyledEditorKit.BoldAction();
	private Action underlineAction = new StyledEditorKit.UnderlineAction();
	private Action italicAction = new StyledEditorKit.ItalicAction();
	
	private Action colorRedAction = new StyledEditorKit.ForegroundAction("Red",Color.red);
	private Action colorBlackAction = new StyledEditorKit.ForegroundAction("Black",Color.black);
	private Action colorBlueAction = new StyledEditorKit.ForegroundAction("Blue",Color.blue);
	private Action colorCyanAction = new StyledEditorKit.ForegroundAction("Cyan",Color.cyan);
	private Action colorDarkGrayAction = new StyledEditorKit.ForegroundAction("DarkGray",Color.darkGray);
	private Action colorGrayAction = new StyledEditorKit.ForegroundAction("Gray",Color.gray);
	private Action colorGreenAction = new StyledEditorKit.ForegroundAction("Green",Color.green);
	private Action colorLightGrayAction = new StyledEditorKit.ForegroundAction("LightGray",Color.lightGray);
	private Action colorMagentaAction = new StyledEditorKit.ForegroundAction("Magenta",Color.magenta);
	private Action colorOrangeAction = new StyledEditorKit.ForegroundAction("Orange",Color.orange);
	private Action colorPinkAction = new StyledEditorKit.ForegroundAction("Pink",Color.pink);
	private Action colorWhiteAction = new StyledEditorKit.ForegroundAction("White",Color.white);
	private Action colorYellowAction = new StyledEditorKit.ForegroundAction("Yellow",Color.yellow);
	
	private Action fontSansSerifAction = new StyledEditorKit.FontFamilyAction("SansSerif", "SansSerif");
	private Action fontSerifAction = new StyledEditorKit.FontFamilyAction("Serif", "Serif");
	private Action fontMonospacedAction = new StyledEditorKit.FontFamilyAction("Monospaced", "Monospaced");
	private Action fontDialogAction = new StyledEditorKit.FontFamilyAction("Dialog", "Dialog");
	private Action fontDialogInputAction = new StyledEditorKit.FontFamilyAction("DialogInput", "DialogInput");
		
	private Action fontSizeAction6 = new StyledEditorKit.FontSizeAction("6", 6);
	private Action fontSizeAction8 = new StyledEditorKit.FontSizeAction("8", 8);
	private Action fontSizeAction10 = new StyledEditorKit.FontSizeAction("10", 10);
	private Action fontSizeAction12 = new StyledEditorKit.FontSizeAction("12", 12);
	private Action fontSizeAction14 = new StyledEditorKit.FontSizeAction("14", 14);
	private Action fontSizeAction16 = new StyledEditorKit.FontSizeAction("16", 16);
	private Action fontSizeAction20 = new StyledEditorKit.FontSizeAction("20", 20);
	private Action fontSizeAction24 = new StyledEditorKit.FontSizeAction("24", 24);
	private Action fontSizeAction32 = new StyledEditorKit.FontSizeAction("32", 32);
	private Action fontSizeAction36 = new StyledEditorKit.FontSizeAction("36", 36);
	private Action fontSizeAction48 = new StyledEditorKit.FontSizeAction("48", 48);
	private Action fontSizeAction72 = new StyledEditorKit.FontSizeAction("72", 72);
	
	private Action alignLeftAction = new StyledEditorKit.AlignmentAction("Left Align",StyleConstants.ALIGN_LEFT);
	private Action alignCenterAction = new StyledEditorKit.AlignmentAction("Center Align",StyleConstants.ALIGN_CENTER);
	private Action alignRIghtAction = new StyledEditorKit.AlignmentAction("Right Align",StyleConstants.ALIGN_RIGHT);
	
	private int lastFind = 0;
	private String findStr = "";
	private Highlighter.HighlightPainter yellowPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
	private Highlighter.HighlightPainter orangePainter = new DefaultHighlighter.DefaultHighlightPainter(Color.orange);
		
	private Action insertBreakAction = new DefaultEditorKit.InsertBreakAction();
	
	public static void main(String[]a) {
		HTMLEditor editor = new HTMLEditor();
		JFrame frame = new JFrame();
		
	}

	public HTMLEditor(){
		editorKit = new HTMLEditorKit();
		document = (HTMLDocument) editorKit.createDefaultDocument();
		init();
	}

	public void init(){
		document.addUndoableEditListener(undoHandler);
		resetUndoManager();
		textPane = new JTextPane(document);
		textPane.setContentType("text/html");
		startNewDocument();
		undo.setLimit(200000);
	}
	 
	protected void resetUndoManager() {
		undo.discardAllEdits();
		undoAction.update();
		redoAction.update();
	}
	
	public void startNewDocument(){
		Document oldDoc = textPane.getDocument();
		if(oldDoc != null)
			oldDoc.removeUndoableEditListener(undoHandler);
		editorKit = new HTMLEditorKit();
		document = (HTMLDocument) editorKit.createDefaultDocument();
		textPane.setDocument(document);	
		textPane.getDocument().addUndoableEditListener(undoHandler);
		resetUndoManager();
	}

	public void clear(){
		startNewDocument();
	}

	public void selectAll(){
		textPane.selectAll();
	}
	
	class SubscriptAction extends StyledEditorKit.StyledTextAction{
		private static final long serialVersionUID = 1L;
		public SubscriptAction(){
			super(StyleConstants.Subscript.toString());
		}
		public void actionPerformed(ActionEvent ae){
			JEditorPane editor = getEditor(ae);
			if (editor != null) {
				StyledEditorKit kit = getStyledEditorKit(editor);
				MutableAttributeSet attr = kit.getInputAttributes();
				boolean subscript = (StyleConstants.isSubscript(attr)) ? false : true;
				SimpleAttributeSet sas = new SimpleAttributeSet();
				StyleConstants.setSubscript(sas, subscript);
				setCharacterAttributes(editor, sas, false);
			}
		}
	}
	
	 class UndoHandler implements UndoableEditListener {
		public void undoableEditHappened(UndoableEditEvent e) {
			undo.addEdit(e.getEdit());
			undoAction.update();
			redoAction.update();
		}
	}
	
	class UndoAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public UndoAction() {
			super("Undo");
			setEnabled(false);
		}

		public void actionPerformed(ActionEvent e) {
			try {
				undo.undo();
			} catch (CannotUndoException ex) {
				ex.printStackTrace();
			}
			update();
			redoAction.update();
		}

		protected void update() {
			if(undo.canUndo()) {
				setEnabled(true);
				putValue(Action.NAME, undo.getUndoPresentationName());
			}else {
				setEnabled(false);
				putValue(Action.NAME, "Undo");
			}
		}
	}

	class RedoAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public RedoAction() {
			super("Redo");
			setEnabled(false);
		}

		public void actionPerformed(ActionEvent e) {
			try {
				undo.redo();
			} catch (CannotRedoException ex) {
				ex.printStackTrace();
			}
			update();
			undoAction.update();
		}
	
		protected void update() {
			if(undo.canRedo()) {
				setEnabled(true);
				putValue(Action.NAME, undo.getRedoPresentationName());
			}else {
				setEnabled(false);
				putValue(Action.NAME, "Redo");
			}
		}
	}

	public JTextPane getTextPane() {
		return textPane;
	}

	public Action getBoldAction() {
		return boldAction;
	}
	
	public void addTable(int widthPercent, int border, int rows, int columns) throws BadLocationException, IOException{
		int pos = textPane.getCaretPosition();
		String table = "<table border=\""+border+"\" style=\"width:"+ widthPercent +"%\">";
		for(int i = 0; i < rows; i++){
			table += "<tr>";
			for(int m = 0; m < columns; m++){
				table += "<td></td>";
			}
			table += "</tr>";
		}
		table += "</table><br>";
		editorKit.insertHTML((HTMLDocument) textPane.getDocument(), pos, table, 0, 0, null);
	}

	public UndoAction getUndoAction() {
		return undoAction;
	}

	public RedoAction getRedoAction() {
		return redoAction;
	}

	public Action getCutAction() {
		return cutAction;
	}

	public Action getCopyAction() {
		return copyAction;
	}

	public Action getPasteAction() {
		return pasteAction;
	}

	public Action getUnderlineAction() {
		return underlineAction;
	}

	public Action getItalicAction() {
		return italicAction;
	}

	public Action getInsertBreakAction() {
		return insertBreakAction;
	}

	public Action getColorRedAction() {
		return colorRedAction;
	}

	public Action getColorBlackAction() {
		return colorBlackAction;
	}

	public Action getColorBlueAction() {
		return colorBlueAction;
	}

	public Action getColorCyanAction() {
		return colorCyanAction;
	}

	public Action getColorDarkGrayAction() {
		return colorDarkGrayAction;
	}

	public Action getColorGrayAction() {
		return colorGrayAction;
	}

	public Action getColorGreenAction() {
		return colorGreenAction;
	}

	public Action getColorLightGrayAction() {
		return colorLightGrayAction;
	}

	public Action getColorMagentaAction() {
		return colorMagentaAction;
	}

	public Action getColorOrangeAction() {
		return colorOrangeAction;
	}

	public Action getColorPinkAction() {
		return colorPinkAction;
	}

	public Action getColorWhiteAction() {
		return colorWhiteAction;
	}

	public Action getColorYellowAction() {
		return colorYellowAction;
	}

	public Action getFontSansSerifAction() {
		return fontSansSerifAction;
	}

	public Action getFontSerifAction() {
		return fontSerifAction;
	}

	public Action getFontMonospacedAction() {
		return fontMonospacedAction;
	}

	public Action getFontDialogAction() {
		return fontDialogAction;
	}

	public Action getFontDialogInputAction() {
		return fontDialogInputAction;
	}

	public Action getFontSizeAction6() {
		return fontSizeAction6;
	}

	public Action getFontSizeAction8() {
		return fontSizeAction8;
	}

	public Action getFontSizeAction10() {
		return fontSizeAction10;
	}

	public Action getFontSizeAction12() {
		return fontSizeAction12;
	}

	public Action getFontSizeAction14() {
		return fontSizeAction14;
	}

	public Action getFontSizeAction16() {
		return fontSizeAction16;
	}

	public Action getFontSizeAction20() {
		return fontSizeAction20;
	}

	public Action getFontSizeAction24() {
		return fontSizeAction24;
	}

	public Action getFontSizeAction32() {
		return fontSizeAction32;
	}

	public Action getFontSizeAction36() {
		return fontSizeAction36;
	}

	public Action getFontSizeAction48() {
		return fontSizeAction48;
	}

	public Action getFontSizeAction72() {
		return fontSizeAction72;
	}

	public Action getAlignLeftAction() {
		return alignLeftAction;
	}

	public Action getAlignCenterAction() {
		return alignCenterAction;
	}

	public Action getAlignRIghtAction() {
		return alignRIghtAction;
	}
	
	public HTMLDocument getDocument() {
		return document;
	}

	public HTMLEditorKit getEditorKit() {
		return editorKit;
	}

	public UndoableEditListener getUndoHandler() {
		return undoHandler;
	}

	public UndoManager getUndo() {
		return undo;
	}
	
	public boolean find(String text) throws BadLocationException{
		text = text.toLowerCase();
		Highlighter h = textPane.getHighlighter();
		h.removeAllHighlights();
		String s = document.getText(0, document.getLength()).toLowerCase();
		
		if(findStr.equals(s)){
			lastFind++;
		}else{
			findStr = s;
			lastFind = 0;
		}
		
		int count = 0;
		int lastIndex = -1;
		do{
			lastIndex = s.indexOf(text, lastIndex);
			if(lastIndex != -1){
				count++;
				lastIndex+=text.length();
			}
		}while(lastIndex != -1);
		if(lastFind > count-1){
			lastFind = 0;
		}
		
		
		int pos = s.indexOf(text);
		int l = 0;
		if(pos == -1){
			return false;
		}
		while(pos != -1){
			try {
				if(lastFind == l){
					h.addHighlight(pos, pos + text.length(), orangePainter);
				}else{
					h.addHighlight(pos, pos + text.length(), yellowPainter);
				}
				pos = s.indexOf(text, pos + text.length());
				l++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public void clearFind() {
		lastFind = 0;
		findStr = "";
	}
}