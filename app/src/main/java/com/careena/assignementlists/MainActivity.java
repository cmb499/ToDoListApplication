package com.careena.assignementlists;

import android.app.Activity;
import android.graphics.Color;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.content.Context;
import android.view.LayoutInflater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.view.ViewGroup;

import android.widget.Button;


public class MainActivity extends Activity {

    ArrayList<toDoBean> sendList = new ArrayList<toDoBean>();
    private Button addButton;
    private EditText editTitle;
    private EditText editDesc;
    CheckBox checkBox;
    CustomAdapter toDoAdapter;
    ListView toDoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File file = getBaseContext().getFileStreamPath("myTextFile.txt");
        if(file.exists()) {
            System.out.println("read called");
            readFromFile();

        }

        toDoView = (ListView) findViewById(R.id.toDoList);
        toDoAdapter = new CustomAdapter(this, sendList);

        toDoView.setAdapter(toDoAdapter);

        //toDoView.setOnClickListener(this);

        addButton = (Button) findViewById(R.id.addButton) ;

        editTitle = (EditText) findViewById(R.id.editTitle);
        editDesc = (EditText) findViewById(R.id.editDesc);
        checkBox = (CheckBox) findViewById( R.id.checkBox );

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String newTitle = editTitle.getText().toString();
                String newDesc = editDesc.getText().toString();

                if (newTitle.equals("") || newDesc.equals("")){
                    Toast.makeText(MainActivity.this, "You should do some task!!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    toDoBean newTask = new toDoBean(newTitle, newDesc, 0);
                    toDoAdapter.add(newTask);
                    editTitle.setText("");
                    editDesc.setText("");

                    writeToFile();

                    Toast.makeText(MainActivity.this, "Task " + newTitle + " inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });




//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
//        {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
//            {
//                if ( isChecked )
//                {
//                    toDoAdapter.remove(position);
//                }
//
//            }
//        });


        toDoView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
                toDoBean toDoItem = (toDoBean) parent.getItemAtPosition(position);

                System.out.println("403 --- list item clicked");
                toDoAdapter.remove(position);

               // Toast.makeText(MainActivity.this, toDoItem.getTitle() + " has been deleted !!", Toast.LENGTH_SHORT).show();

            }
        });

        toDoView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

               // parent.getItemAtPosition(position);
                toDoBean toDoItem = (toDoBean) parent.getItemAtPosition(position);

                toDoAdapter.remove(position);

                Toast.makeText(MainActivity.this, toDoItem.getTitle() + " has been deleted !!", Toast.LENGTH_SHORT).show();

                return false;
            }
        });


}


    public  void writeToFile()
    {
        try {


            FileOutputStream fileout = openFileOutput("myTextFile.txt", MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fileout);


            for(toDoBean bean: sendList) {
                String title = bean.getTitle();
                String desc = bean.getDesc();
                writer.write(title + "\t" + desc + "\n");
            }


            writer.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }


    // Read text from file
    public void readFromFile() {
        //reading text from file

        try {
            Scanner scanner = new Scanner(openFileInput("myTextFile.txt"));

            while(scanner.hasNextLine()){

                String line = scanner.nextLine();

                String[] lines = line.split("\t");

                System.out.println(lines[0] + "  " + lines[1]);

                toDoBean newTask = new toDoBean(lines[0], lines[1], 0);
               // System.out.println(line);
                sendList.add(newTask);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("write called");
        writeToFile();

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("editTitle", editTitle.getText().toString());
        outState.putString("editDesc", editDesc.getText().toString());
        outState.putSerializable("list", sendList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        editTitle.setText(savedInstanceState.getString("editTitle"));
        editDesc.setText(savedInstanceState.getString("editDesc"));

        sendList = (ArrayList<toDoBean>) savedInstanceState.getSerializable("list");

        System.out.println(sendList.get(0).getTitle());

        toDoAdapter = new CustomAdapter(this, sendList);
        toDoView.setAdapter(toDoAdapter);

    }
}



