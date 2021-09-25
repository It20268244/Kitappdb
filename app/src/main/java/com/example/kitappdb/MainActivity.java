package com.example.kitappdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button send , btnback;
    EditText id, name, author, publisher , price, quantity,description;
    DatabaseReference reference;
    ImageView pic;
    Book b1;
    TextView tv;
    long maxid =0;

    //image
    Button btnbrowse, btnupload;
    EditText txtdata ;
    ImageView imgview;
    Uri FilePathUri;
    StorageReference storageReference;

    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;


    //method to clear all user inpyts
    private void clearControls(){
        id.setText("");
        name.setText("");
        author.setText("");
    }

   // FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference reference = database.getReference().child("users");        // if i write only refrence it will write under the mainn db but if we add
                                                                        //child reference it will write under specified child
    //DatabaseReference reference = database.getReference();   // can use the old obgect refeerence as well



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       id = findViewById(R.id.bid);
        name = findViewById(R.id.bname);
        author = findViewById(R.id.bAname);
        publisher = findViewById(R.id.bpname);
        price = findViewById(R.id.bprice);
        quantity = findViewById(R.id.bquan);
        description = findViewById(R.id.bdesc);

        send = findViewById(R.id.insert);


        b1 = new Book();


        //image
        storageReference = FirebaseStorage.getInstance().getReference("images");
        reference = FirebaseDatabase.getInstance().getReference().child("bitem").child("bb");
        btnbrowse = (Button) findViewById(R.id.btnbrowse);
        txtdata = (EditText) findViewById(R.id.txtdata);
        imgview = (ImageView) findViewById(R.id.image_view);
        progressDialog = new ProgressDialog(MainActivity.this);// context name as per your project name


        btnbrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);

            }
        });

        //image

        btnback = findViewById(R.id.df);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                reference = FirebaseDatabase.getInstance().getReference().child("bitem").child("bb");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                            maxid = (snapshot.getChildrenCount());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                UploadImage();

                try {
                    if (TextUtils.isEmpty(id.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter id", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(author.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter author name", Toast.LENGTH_SHORT).show();
                    else {
                        b1.setBookID(id.getText().toString().trim());
                        b1.setBook_name(name.getText().toString().trim());
                        b1.setAuthor(author.getText().toString().trim());
                        b1.setPublisher(publisher.getText().toString().trim());
                        b1.setPrice(Float.valueOf(price.getText().toString().trim()));
                        b1.setQuantity(Integer.parseInt(quantity.getText().toString().trim()));
                        b1.setDescription(description.getText().toString().trim());
                        reference.push().setValue(b1);

                        Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid contact number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                imgview.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    public void UploadImage() {

        if (FilePathUri != null) {

            progressDialog.setTitle("Image is Uploading...");
            progressDialog.show();
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String TempImageName = txtdata.getText().toString().trim();

                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
                            @SuppressWarnings("VisibleForTests")
                            com.example.kitappdb.Book imageUploadInfo = new com.example.kitappdb.Book(b1.getBook_name(),b1.getAuthor(),
                                    b1.getPublisher(),b1.getPrice(),b1.getQuantity(),b1.getDescription(),TempImageName, taskSnapshot.getUploadSessionUri().toString());
                            reference.child(String.valueOf(maxid+1)).setValue(imageUploadInfo);
                            clearControls();
                        }
                    });
        }
        else {

            Toast.makeText(MainActivity.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }



}}