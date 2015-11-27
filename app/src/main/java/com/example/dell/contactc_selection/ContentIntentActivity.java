package com.example.dell.contactc_selection;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContentIntentActivity extends AppCompatActivity {
    private final int PHONE = 0;
    private final int WEBSITE = 1;
    private ListView intentListView;
    private ArrayAdapter<String> adapter;
    private List<ContactObject> contactList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_intent);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intentListView = (ListView) findViewById(R.id.listIntent);
        // initializate the list and add item
        contactList = new ArrayList<ContactObject>();
        contactList.add(new ContactObject("AndroidOne", "1111-1111-1111", "www.androidATC.com"));
        contactList.add(new ContactObject("Androidtwo", "2222-2222-2222", "www.androidATC.com"));
        contactList.add(new ContactObject("Androidthree", "3333-3333-3333", "www.androidATC.com"));
        contactList.add(new ContactObject("Androidfour", "4444-4444-4444", "www.androidATC.com"));

        List<String> listName = new ArrayList<String>();
        for (int i = 0; i < contactList.size(); i++) {
            listName.add(contactList.get(i).getName());
        }
        // initializate the ArrayAdapter object
        adapter = new ArrayAdapter<String>(ContentIntentActivity.this, android.R.layout.simple_list_item_1, listName);
        //set the dadpter of the ListView
        intentListView.setAdapter(adapter);

        intentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //llama a un activity y espera una respuesta del mismo
                Intent i = new Intent(ContentIntentActivity.this, ContactPageActivity.class);
                i.putExtra("Object", contactList.get(position));
                startActivityForResult(i, 0);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultcode, Intent data) {// despues del setResult()  se llama a esta funcion para hacer  "algo" con la respuesta del activity
        if (data == null) {
            return;
        }
        Bundle resultData = data.getExtras();
        String value = resultData.getString("value");
        switch (resultcode) {
            case PHONE:
                //implicit intent make call
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + value)));
                break;
            case WEBSITE:
                //IMPLICIT INTENT TO VISIT WEBSITE
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + value)));
                break;
        }
    }


}
