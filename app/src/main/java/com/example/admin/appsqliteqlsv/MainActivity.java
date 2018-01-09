package com.example.admin.appsqliteqlsv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.appsqliteqlsv.adapter.CustomAdapter;
import com.example.admin.appsqliteqlsv.data.DBManager;
import com.example.admin.appsqliteqlsv.model.Students;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public EditText edtName;
    public EditText edtNumber;
    public EditText edtEmail;
    public EditText edtAddress;
    public EditText edtId;
    public Button btnSave;
    public Button btnUpdate;
    private CustomAdapter customAdapter;
    private ListView lvStudent;
    private List<Students> studentsList;
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this);
        initWidget();
        studentsList = dbManager.getAllStudents();
        setAdapter();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Students student = createStudent();
                dbManager.addStudent(student);
                studentsList.clear();
                studentsList.addAll(dbManager.getAllStudents());
                setAdapter();
            }
        });
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Students students = studentsList.get(position);
                edtId.setText(students.getId()+"");
                edtName.setText(students.getName());
                edtNumber.setText(students.getNumber());
                edtEmail.setText(students.getEmail());
                edtAddress.setText(students.getAddress());
                btnSave.setEnabled(false);//ẩn save hiện update
                btnUpdate.setEnabled(true);
            }
        });
        lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Students studen = studentsList.get(position);
                int result = dbManager.deleteStudent(studen.getId());
//                dbManager.deleteStudent(studentsList.get(position).getId());
                if(result>0){
                    Toast.makeText(MainActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                    updateListStudent();
                } else {
                    Toast.makeText(MainActivity.this, "Delete fail", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Students students = new Students();
                students.setId(Integer.parseInt(edtId.getText()+""));
                students.setName(edtName.getText()+"");
                students.setNumber(edtNumber.getText()+"");
                students.setEmail(edtEmail.getText()+"");
                students.setAddress(edtAddress.getText()+"");
                int result = dbManager.updateStudent(students);
                //hien thi nut save ẩn update
                if(result>0){
                    updateListStudent();
                }
                    btnSave.setEnabled(true);
                    btnUpdate.setEnabled(false);

            }
        });
//        db.hello();

    }
    public Students createStudent(){
        String name = edtName.getText().toString();
        String number = edtNumber.getText().toString();
        String email = edtEmail.getText().toString();
        String address = edtAddress.getText().toString();

        Students students = new Students(name,number,email,address);
        return students;
    }
    public void initWidget(){
        edtName = (EditText)findViewById(R.id.edtName);
        edtId = (EditText)findViewById(R.id.edtId);
        edtNumber = (EditText)findViewById(R.id.edtNumber);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtAddress = (EditText)findViewById(R.id.edtAddress);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        lvStudent = (ListView)findViewById(R.id.lv_student);
    }
    private void setAdapter(){
        if(customAdapter == null){
            customAdapter = new CustomAdapter(this, R.layout.item_list_student,studentsList);
            lvStudent.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            //tu dong keo den vi tri cuoi cung trong listview
            lvStudent.setSelection(customAdapter.getCount()-1);
        }

    }
    public void updateListStudent(){
        studentsList.clear();
        studentsList.addAll(dbManager.getAllStudents());
        if(customAdapter!=null){
            customAdapter.notifyDataSetChanged();
        }

    }
}
