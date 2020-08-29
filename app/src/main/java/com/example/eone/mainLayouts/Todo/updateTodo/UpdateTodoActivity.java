package com.example.eone.mainLayouts.Todo.updateTodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.eone.R;
import com.example.eone.database.DBRepository;
import com.example.eone.database.TodoEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UpdateTodoActivity extends AppCompatActivity {
    private EditText title,desc,time,date;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    DBRepository dbRepository;

    private FloatingActionButton submitTodo;
    String text;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Productivity");
        setContentView(R.layout.activity_update_todo);
        radioGroup = (RadioGroup)findViewById(R.id.radioGrp);
        submitTodo = (FloatingActionButton)findViewById(R.id.submitTodo);
        title = (EditText)findViewById(R.id.todoTitle);
        title.setText(getIntent().getStringExtra("title"));
        desc = (EditText)findViewById(R.id.todoDesc);
        desc.setText(getIntent().getStringExtra("desc"));
        date = (EditText)findViewById(R.id.todoDate);
        date.setText(getIntent().getStringExtra("date"));
        time = (EditText)findViewById(R.id.todoTime);
        time.setText(getIntent().getStringExtra("time"));
        dbRepository = new DBRepository(getApplication());
        id = getIntent().getLongExtra("id",0);
        Toast.makeText(getApplicationContext(),""+id,Toast.LENGTH_LONG).show();
        submitTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Toast.makeText(getApplicationContext(),radioButton.getText(),Toast.LENGTH_LONG).show();
                if (radioGroup.getCheckedRadioButtonId() == -1)
                {

                    Toast.makeText(getApplicationContext(),"Please Select priority",Toast.LENGTH_LONG).show();
                }
                else
                {
                    int isSelected = radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton)findViewById(isSelected);
                    text = radioButton.getText().toString();
                    if (title.getText().toString().isEmpty() || desc.getText().toString().isEmpty() || date.getText().toString().isEmpty()
                            || time.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(),"Please fill all the fields",Toast.LENGTH_SHORT).show();
                    }else {
                        TodoEntity todoEntity = new TodoEntity(title.getText().toString(),desc.getText().toString(),
                                date.getText().toString(),time.getText().toString(),text);
                        todoEntity.setTodoId(id);

                        dbRepository.updateTodo(todoEntity);
                        Toast.makeText(getApplicationContext(),"Task Updated",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }

            }
        });
    }
}