package org.sofaecke.vialshuffler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * The main activity of the VialShuffler app.
 * @author david
 */
public class MainActivity extends Activity {

	private static String[] COLUMNS = { "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J" };
	private static String[] ROWS = { "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "10" };
	private static int MAX_INDEX = (COLUMNS.length * ROWS.length) - 1;

	private VialSet vialSet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		updateVialSet();
		updateTable();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Clears the view.
	 */
	private void clearTable() {
		for (int i = 0; i <= MAX_INDEX; i++) {
			TextView cell = getTableCell(i);
			cell.setText(getResources().getString(R.string.default_cellcontent));
		}
	}

	/**
	 * Updates the view from the model.
	 */
	private void updateTable() {
		for (int i = 0; i < vialSet.getItems().size(); i++) {
			TextView cell = getTableCell(i);
			cell.setText(vialSet.getItems().get(i));
		}
	}

	/**
	 * Gets the n-th cell view in the table.
	 * 
	 * @param number
	 *            A number between 0 and MAX_INDEX.
	 * @return The view corresponding to that index.
	 */
	private TextView getTableCell(int number) {
		if (number < 0) {
			Log.e("VialShuffler.Main", "Trying to get negative table cell: "
					+ number);
			number = 0;
		} else if (number > MAX_INDEX) {
			Log.e("VialShuffler.Main", "Trying to get nonexistant table cell: "
					+ number);
			number = MAX_INDEX;
		}

		String col = COLUMNS[number % COLUMNS.length];
		String row = ROWS[number / ROWS.length];

		int resID = getResources().getIdentifier("tableCell" + col + row, "id",
				getPackageName());
		TextView cell = (TextView) findViewById(resID);
		return cell;
	}

	/**
	 * Gets the current value from the GUI and updates the model.
	 */
	private void updateVialSet() {
		EditText vialsField = (EditText) findViewById(R.id.vials_field);
		int number = Integer.parseInt(vialsField.getText().toString());
		Log.d("VialShuffler.Main", "We want '" + number + "' vials.");
		vialSet = new VialSet(number);
	}

	/**
	 * Shuffles the model.
	 */
	public void shuffle(View v) {
		updateVialSet();

		vialSet.shuffle();

		clearTable();
		updateTable();
	}
}
