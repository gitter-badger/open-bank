package com.app.swe443.openbankapp;


import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.swe443.openbankapp.Support.Account;


import com.app.swe443.openbankapp.Support.AccountTypeEnum;
import com.app.swe443.openbankapp.Support.Transaction;
import com.app.swe443.openbankapp.Support.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class AccountDetails extends AppCompatActivity implements AccountFrag.OnAccountFragCallbackListener ,TransferFrag.OnTransferFragCallbackListener, NfcAdapter.OnNdefPushCompleteCallback,
        NfcAdapter.CreateNdefMessageCallback, TransactionFrag.OnTransactionFragCallbackListener{


    //Variables to initalize tabs menu
    ViewPager viewerPager;
    FragmentPageAdapter fragmentPagerAdapter;
    private Toolbar toolbar;
    public ActionBar actionBar;

    //Fields of the account selected

    private NfcAdapter mNfcAdapter;
    private String mNfcAccount;
    private String accountnum;
    private String balance;
    private String type;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        //Get accountIndex from MainActivity
        System.out.println("Viewing account fragment for account: "+accountnum);

         /*
            Get the account that these fragments will use
         */
        Bundle extras = getIntent().getExtras();

        type = extras.getString("type");
        balance = extras.getString("balance");
        accountnum =  extras.getString("accountnum");


        //Set of the Pager fragments
        fragmentPagerAdapter = new FragmentPageAdapter(getSupportFragmentManager(), Integer.valueOf(accountnum));
        viewerPager = (ViewPager)findViewById(R.id.pager);
        viewerPager.setAdapter(fragmentPagerAdapter);

        DrawerLayout Drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView drawerList = (ListView) findViewById(R.id.left_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);






        /* NFC */
        //Check if NFC is available on device
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter != null && mNfcAdapter.isEnabled()) {
            //This will refer back to createNdefMessage for what it will send
            mNfcAdapter.setNdefPushMessageCallback(this, this);

            //This will be called if the message is sent successfully
            mNfcAdapter.setOnNdefPushCompleteCallback(this, this);
        }
        else {
            Toast.makeText(this, "NFC is unavailable", Toast.LENGTH_LONG).show();
        }

    }

    //On back press by the user, go back to the MainActivity and display HomeFrag
    public void backNavigation(View view){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    //When user selects to go back from the top left arrow option on the header, go to Mainactivity
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }


    //Return the account associated to these fragments.
    //Accessed by AccountFrag
    public String[] getAccountInfo(){
        String[] accountInfo = new String[3];
        accountInfo[0] = String.valueOf(accountnum);
        accountInfo[1] = String.valueOf(balance);
        accountInfo[2]= type;
        return accountInfo;
    }

    //Return the transactions associated to this account
    //Used by TransactionFrag
    /*
        TODO IMPLMENT TRANSACTIONS SERVER CALL
     */
    public LinkedList<Transaction> getTransactions(){
        return null;
    }




    public void onTransferSelected() {
        System.out.println("onTransferSelected method initiated");
        fragmentPagerAdapter.notifyDataSetChanged();
        viewerPager.invalidate();

    }

    //This will be called when another NFC capable device is detected.
    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        //Places the accountnum into the record
        byte[] payload = Integer.toString(Integer.valueOf(accountnum)).getBytes(Charset.forName("UTF-8"));
        NdefRecord record = NdefRecord.createMime("text/plain", payload);
        return new NdefMessage(record);
    }

    // Called when NDef message was successful
    @Override
    public void onNdefPushComplete(NfcEvent event) {
        Toast.makeText(this, "Account info received", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNewIntent(Intent intent) {
        handleNfcIntent(intent);
    }

    private void handleNfcIntent(Intent NfcIntent) {
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(NfcIntent.getAction())) {
            Parcelable[] receivedArray =
                    NfcIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            if (receivedArray != null) {
                this.updateNFCAccount("");
                NdefMessage receivedMessage = (NdefMessage) receivedArray[0];
                NdefRecord[] attachedRecords = receivedMessage.getRecords();

                for (NdefRecord record : attachedRecords) {
                    String string = new String(record.getPayload());
                    //Make sure we don't pass along our AAR (Android Application Record)
                    if (string.equals(getPackageName())) {
                        continue;
                    }
                    this.updateNFCAccount(new String(record.getPayload()));
                }
            } else {
                Toast.makeText(this, "Received Blank Parcel", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void updateBalance(String balance){
        this.balance = balance;
    }

    //Updates NFCAccount (WIP)
    public void updateNFCAccount(String account) {
        if (balance != null){
            this.mNfcAccount = account;
        }
    }

    public String pullNFCAccount(){return this.mNfcAccount;}


}