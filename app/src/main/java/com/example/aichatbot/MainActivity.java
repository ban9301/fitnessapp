package com.example.aichatbot;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // creating variables for our
    // widgets in xml file.
    private RecyclerView chatsRV;
    private ImageButton sendMsgIB;
    private EditText userMsgEdt;
    private ImageButton btnSpeak;
    private final String USER_KEY = "user";
    private final String BOT_KEY = "bot";
    private final int REQ_CODE_SPEECH_INPUT = 1000;
    public TextToSpeech t1;
    // creating a variable for
    // our volley request queue.
    TextToSpeech textToSpeech;
    // creating a variable for array list and adapter class.
    private ArrayList<ChatsModal> chatsModalArrayList;
    private ChatRVAdapter chatRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // on below line we are initializing all our views.
        chatsRV = findViewById(R.id.idRVChats);
        sendMsgIB = findViewById(R.id.idIBSend);
        userMsgEdt = findViewById(R.id.idEdtMessage);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        chatsModalArrayList = new ArrayList<>();
        chatRVAdapter = new ChatRVAdapter(chatsModalArrayList,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        chatsRV.setLayoutManager(manager);
        chatsRV.setAdapter(chatRVAdapter);
        sendMsgIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userMsgEdt.getText().toString().isEmpty()) {
                    // if the edit text is empty display a toast message.
                    Toast.makeText(MainActivity.this, "Please enter your message..", Toast.LENGTH_SHORT).show();
                    return;
                }
                getResponse(userMsgEdt.getText().toString());

                userMsgEdt.setText("");
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        chatsRV.setLayoutManager(linearLayoutManager);
        chatsRV.setAdapter(chatRVAdapter);

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                promptSpeechInput();

            }
        });
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(new Locale("yue", "HK"));
                }
            }
        });
        textToSpeech = new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status==TextToSpeech.SUCCESS){
                            int language = textToSpeech.setLanguage(new Locale("yue", "HK"));
                        }
                    }
                });
    }
    private void getResponse(String message) {
        chatsModalArrayList.add(new ChatsModal(message, USER_KEY));
        chatRVAdapter.notifyDataSetChanged();
        String url = "http://api.brainshop.ai/get?bid=164725&key=GVhJSOHPc9AjBCCP&uid=[uid]&msg=" + message;
        //http://api.brainshop.ai/get?bid=164725&key=GVhJSOHPc9AjBCCP&uid=[uid]&msg=
        //http://api.brainshop.ai/get?bid=164710&key=ef417u75bS4sFsa2&uid=[uid]&msg=
        //http://api.brainshop.ai/get?bid=164725&key=GVhJSOHPc9AjBCCP&uid=[uid]&msg=
        String BASE_URL = "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<MsgModal> call = retrofitAPI.getMessage(url);
        call.enqueue(new Callback<MsgModal>() {
            @Override
            public void onResponse(Call<MsgModal> call, retrofit2.Response<MsgModal> response) {
                if (response.isSuccessful()){
                    MsgModal modal = response.body();
                    chatsModalArrayList.add(new ChatsModal(modal.getCnt(),BOT_KEY));
                    chatRVAdapter.notifyDataSetChanged();
                    int speech = textToSpeech.speak(modal.getCnt(), textToSpeech.QUEUE_FLUSH,null);
                }
            }
            @Override
            public void onFailure(Call<MsgModal> call, Throwable t) {
                chatsModalArrayList.add(new ChatsModal("Please revert your question",BOT_KEY));
                chatRVAdapter.notifyDataSetChanged();
            }
        });

    }
    //speech start
    public void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try
        {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);

        }
        catch (ActivityNotFoundException a)
        {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }


    }
    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    userMsgEdt.setText(result.get(0));
/*
                    String res = chat.multisentenceRespond(userMsgEdt.getText().toString());
                    t1.speak(res, TextToSpeech.QUEUE_FLUSH, null);*/

                    if (TextUtils.isEmpty(userMsgEdt.getText().toString())) {
                        return;
                    }

                    userMsgEdt.getText().toString();
                    if (userMsgEdt.getText().toString().isEmpty()) {
                        // if the edit text is empty display a toast message.
                        Toast.makeText(MainActivity.this, "Please enter your message..", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    getResponse(userMsgEdt.getText().toString());
                    userMsgEdt.setText("");

                }

                break;
            }


        }




    }

}