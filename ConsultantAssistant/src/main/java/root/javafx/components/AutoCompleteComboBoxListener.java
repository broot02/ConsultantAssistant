package root.javafx.components;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.IndexRange;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;

public class AutoCompleteComboBoxListener<T> implements EventHandler<KeyEvent> {

	private ComboBox<T> comboBox;
	private StringBuilder sb;
	private int lastLength;

	public AutoCompleteComboBoxListener(ComboBox<T> comboBox) {
		this.comboBox = comboBox;
		sb = new StringBuilder();
		this.comboBox.setOnKeyPressed((keyEvent) -> {

			if (comboBox.getValue() != null) {
				if (keyEvent.getCode() == KeyCode.ESCAPE) {
					comboBox.getSelectionModel().select(10);
					/*
					 * ObservableList<T> list = comboBox.getItems(); for (int i
					 * = 0; i < list.size(); i++) { if (list.get(i).toString()
					 * .startsWith(sb.toString())) {
					 * comboBox.getSelectionModel().select(i); break; } }
					 */

				}
			
			}

		});
		this.comboBox.setEditable(true);
		this.comboBox.setOnKeyReleased(AutoCompleteComboBoxListener.this);

		// add a focus listener such that if not in focus, reset the filtered
		// typed keys

		this.comboBox.getEditor().focusedProperty()
				.addListener((observable, oldValue, newValue) -> {

					if (newValue) {
						// in focus
						selectClosestResultBasedOnTextFieldValue(false, false);
					} else {
						lastLength = 0;
						sb.delete(0, sb.length());
						selectClosestResultBasedOnTextFieldValue(false, false);
					}

				});

		this.comboBox.setOnMouseClicked((event) -> {
			selectClosestResultBasedOnTextFieldValue(true, true);

		});
	}

	@Override
	public void handle(KeyEvent event) {
		// this variable is used to bypass the auto complete process if the
		// length is the same.
		// this occurs if user types fast, the length of text field will record
		// after the user
		// has typed after a certain delay.
		if (lastLength != (comboBox.getEditor().getLength() - comboBox
				.getEditor().getSelectedText().length()))
			lastLength = comboBox.getEditor().getLength()
					- comboBox.getEditor().getSelectedText().length();

		if (event.isControlDown() || event.getCode() == KeyCode.BACK_SPACE
				|| event.getCode() == KeyCode.RIGHT
				|| event.getCode() == KeyCode.LEFT
				|| event.getCode() == KeyCode.DELETE
				|| event.getCode() == KeyCode.HOME
				|| event.getCode() == KeyCode.END
				|| event.getCode() == KeyCode.TAB)
			return;

		IndexRange ir = comboBox.getEditor().getSelection();
		sb.delete(0, sb.length());
		sb.append(comboBox.getEditor().getText());
		// remove selected string index until end so only unselected text will
		// be recorded
		try {
			sb.delete(ir.getStart(), sb.length());
		} catch (Exception e) {
		}

		ObservableList<T> items = comboBox.getItems();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).toString().toLowerCase()
					.startsWith(comboBox.getEditor().getText().toLowerCase())) {
				try {
					comboBox.getEditor().setText(
							sb.toString()
									+ items.get(i).toString()
											.substring(sb.toString().length()));
				} catch (Exception e) {
					comboBox.getEditor().setText(sb.toString());
				}
				comboBox.getEditor().positionCaret(sb.toString().length());
				comboBox.getEditor().selectEnd();
				break;
			}
		}
	}

	/*
	 * selectClosestResultBasedOnTextFieldValue() - selects the item and scrolls
	 * to it when the popup is shown.
	 * 
	 * parameters: affect - true if combobox is clicked to show popup so text
	 * and caret position will be readjusted. inFocus - true if combobox has
	 * focus. If not, programmatically press enter key to add new entry to list.
	 */
	private void selectClosestResultBasedOnTextFieldValue(boolean affect,
			boolean inFocus) {
		ObservableList<T> items = AutoCompleteComboBoxListener.this.comboBox
				.getItems();
		boolean found = false;
		for (int i = 0; i < items.size(); i++) {
			if (AutoCompleteComboBoxListener.this.comboBox.getEditor()
					.getText().toLowerCase()
					.equals(items.get(i).toString().toLowerCase())) {
				try {
					ListView<T> lv = ((ComboBoxListViewSkin) AutoCompleteComboBoxListener.this.comboBox
							.getSkin()).getListView();
					lv.getSelectionModel().clearAndSelect(i);
					lv.scrollTo(lv.getSelectionModel().getSelectedIndex());
					found = true;
					break;
				} catch (Exception e) {
				}
			}
		}

		String s = comboBox.getEditor().getText();
		if (!found && affect) {
			comboBox.getSelectionModel().clearSelection();
			comboBox.getEditor().setText(s);
			comboBox.getEditor().end();
		}

	}
}