package sg.edu.rp.c346.id22021136.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etToDo;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ListView lvToDo;
    Spinner spinnerOpt;

    ArrayList<String> alToDo;
    ArrayAdapter<String> aaToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etToDo = findViewById(R.id.editNewToDo);
        btnAdd = findViewById(R.id.buttonAddToDo);
        btnClear = findViewById(R.id.buttonClearToDo);
        lvToDo = findViewById(R.id.listViewToDo);
        spinnerOpt = findViewById(R.id.spinner);
        btnDelete = findViewById(R.id.buttonDeleteToDo);

        alToDo = new ArrayList<>();
        aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alToDo);
        lvToDo.setAdapter(aaToDo);

        spinnerOpt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOpt = parent.getItemAtPosition(position).toString();
                if (selectedOpt.equals("Add a new task")) {
                    etToDo.setHint("Enter a new task");
                    btnDelete.setEnabled(false);
                    btnAdd.setEnabled(true);
                } else if (selectedOpt.equals("Remove a task")) {
                    etToDo.setHint("Enter index of the task to be removed");
                    btnDelete.setEnabled(true);
                    btnAdd.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todo = etToDo.getText().toString();
                alToDo.add(todo);
                aaToDo.notifyDataSetChanged();
                etToDo.setText(null);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String indexStr=etToDo.getText().toString();
                if (!indexStr.isEmpty()){
                    int index=Integer.parseInt(indexStr);
                    if (index>=0&&index<alToDo.size()){
                        alToDo.remove(index);
                        aaToDo.notifyDataSetChanged();
                        etToDo.setText(null);
                    }else{
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                        Log.e("MainActivity", "Wrong index number: " + index);
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alToDo.clear();
                aaToDo.notifyDataSetChanged();
            }
        });

    }
}