package demo.prashant.com.externaldatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

import android.database.SQLException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Cursor c = null;
    TextView textView,textView1;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);
        textView1 = (TextView) findViewById(R.id.textview1);
        button = (Button) findViewById(R.id.button01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatabaseHelper myDbHelper = new DatabaseHelper(MainActivity.this);
                try {

                    myDbHelper.createDataBase();

                } catch (IOException ioe) {

                    throw new Error("Unable to create database");

                }

                try {

                    myDbHelper.openDataBase();

                } catch (SQLException sqle) {

                    throw sqle;

                }
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();


                c = myDbHelper.query("EMP_TABLE", null, null, null, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        textView.setText("_id: " + c.getString(0) + "\n" +
                                "E_NAME: " + c.getString(1) + "\n" +
                                "E_AGE: " + c.getString(2) + "\n" +
                                "E_DEPT:  " + c.getString(3));
                        /*Toast.makeText(MainActivity.this,
                                "_id: " + c.getString(1) + "\n" +
                                        "E_NAME: " + c.getString(1) + "\n" +
                                        "E_AGE: " + c.getString(2) + "\n" +
                                        "E_DEPT:  " + c.getString(3),
                                Toast.LENGTH_LONG).show();*/
                        textView1.setText("_id: " + c.getString(0) + "\n" +
                                "E_NAME: " + c.getString(1) + "\n" +
                                "E_AGE: " + c.getString(2) + "\n" +
                                "E_DEPT:  " + c.getString(3));

                    } while (c.moveToNext());
                }
            }
        });


    }
}
