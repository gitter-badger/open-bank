package com.app.swe443.openbankapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import static android.content.ContentValues.TAG;

public class TransferFrag extends Fragment implements View.OnClickListener {

    static public EditText accountTo;
    static public EditText accountToConfirm;
    private EditText amount;
    private EditText amountNFC;
    private EditText amountQR;

    private Button transferManuallyButton;
    private Button transferNFCButton;
    private Button transferQRButton;
    private Button cancelManualTransfer;
    private Button confirmManualTransfer;
    private Button cancelNFCTransfer;
    private Button confirmNFCTransfer;
    private Button cancelQRTransfer;
    private Button confirmQRTransfer;


    private LinearLayout transferNFCLayout;
    private LinearLayout transferManuallyLayout;
    private LinearLayout transferQRLayout;
    private LinearLayout transferLayout;


    private HashMap<String,String> params;
    private TransferFrag.OnTransferFragCallbackListener mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Container Activity must implement this interface
    public interface OnTransferFragCallbackListener {
        public void onTransferSelected();
        public String[] getAccountInfo();
        public void updateBalance(String accountInfo);
        public String pullNFCAccount();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_transfer, container, false);

        accountTo = (EditText) v.findViewById(R.id.accountnumToInput);
        accountToConfirm = (EditText) v.findViewById(R.id.accountnumToConfirmInput);
        amount = (EditText) v.findViewById(R.id.amountInput);
        amountNFC = (EditText) v.findViewById(R.id.amount_NFC);
        amountQR = (EditText) v.findViewById(R.id.QR_amount);

        transferManuallyButton = (Button) v.findViewById(R.id.transferManuallyButton);
        transferNFCButton = (Button) v.findViewById(R.id.NFCTransferButton);
        transferQRButton = (Button) v.findViewById(R.id.QRTransferButton);
        transferNFCButton.setOnClickListener(this);
        transferQRButton.setOnClickListener(this);
        transferManuallyButton.setOnClickListener(this);

        transferNFCLayout = (LinearLayout) v.findViewById(R.id.transferUsingNFC);
        transferQRLayout = (LinearLayout) v.findViewById(R.id.transferUsingQR);
        transferManuallyLayout = (LinearLayout) v.findViewById(R.id.transferManually);
        transferLayout = (LinearLayout) v.findViewById(R.id.transfer);

        cancelManualTransfer = (Button) v.findViewById(R.id.cancelTransfer);
        cancelNFCTransfer = (Button) v.findViewById(R.id.cancelNFC);
        cancelQRTransfer = (Button) v.findViewById(R.id.cancelQR);
        confirmManualTransfer = (Button) v.findViewById(R.id.confirmTransfer);
        confirmNFCTransfer = (Button) v.findViewById(R.id.confirmNFC);
        confirmQRTransfer = (Button) v.findViewById(R.id.cancelQR);

        cancelManualTransfer.setOnClickListener(this);
        cancelNFCTransfer.setOnClickListener(this);
        cancelQRTransfer.setOnClickListener(this);
        confirmManualTransfer.setOnClickListener(this);
        confirmNFCTransfer.setOnClickListener(this);
        confirmQRTransfer.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        ProgressDialog progress = new ProgressDialog(getActivity());
        Handler handler;
        switch (v.getId()) {
            case R.id.transferManuallyButton:
                setOptionsVisibility(0);
                break;
            case R.id.NFCTransferButton:
                progress.setTitle("Loading");
                progress.setMessage("Waiting For NFC Connection ...");
                progress.setCancelable(true);
                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                    progress.create();
                }
                progress.show();

                /*Todo NFC transfer*/

                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        progress.dismiss();
                    }
                }, 3000); // 3000 milliseconds delay

                setOptionsVisibility(1);
                break;
            case R.id.QRTransferButton:
                progress.setTitle("Loading");
                progress.setMessage("Waiting For QR ...");
                progress.setCancelable(true);
                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                    progress.create();
                }
                progress.show();

                /*Todo QR transfer*/

                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        progress.dismiss();
                    }
                }, 3000); // 3000 milliseconds delay

                setOptionsVisibility(2);
                break;
            case R.id.cancelTransfer:
                transferManuallyLayout.setVisibility(View.GONE);
                setOptionsVisibility(3);
                break;
            case R.id.cancelNFC:
                transferNFCLayout.setVisibility(View.GONE);
                setOptionsVisibility(3);
                break;
            case R.id.cancelQR:
                transferQRLayout.setVisibility(View.GONE);
                setOptionsVisibility(3);
                break;
            case R.id.confirmNFC:
                transferNFCLayout.setVisibility(View.GONE);
                setOptionsVisibility(3);
                break;
            case R.id.confirmQR:
                transferQRLayout.setVisibility(View.GONE);
                setOptionsVisibility(3);
                break;
            case R.id.confirmTransfer://For manual transfer.
                /*
                    Input:  transferType : [transferType]
                            toAccountId : [accountNum]
                            amount : [Double amount]
                            fromAccountId : [accountNum]

                 */
                boolean incomplete = false;
                if(accountTo.getText().toString().equals("") ) {
                    accountTo.setError("Required field is missing");
                    incomplete = true;
                }
                if(accountToConfirm.getText().toString().equals("")) {
                    accountToConfirm.setError("Required field is missing");
                    incomplete = true;
                }
                if(!(accountTo.getText().toString().equals(accountToConfirm.getText().toString()))){
                    accountToConfirm.setError("Values do not match");
                    incomplete = true;
                }
                if(amount.getText().toString().equals("")) {
                    amount.setError("Required field is missing");
                    incomplete = true;
                }
                if(Double.valueOf(amount.getText().toString()) <= 0){
                    amount.setError("Can't transfer a negative amount");
                    incomplete = true;
                }else {
                    params = new HashMap<String, String>();
                    params.put("transferType", "TRANSFER");
                    params.put("toAccountId", accountTo.getText().toString());
                    params.put("amount", amount.getText().toString());
                    //Sender's accountnum is stored in the AccountDetails activity (parent activity)
                    params.put("fromAccountId",  mCallback.getAccountInfo()[0]);
                }
                if(incomplete)
                    break;
                else{
                    confirmTransfer();
                    break;
                }
        }
    }
    public void confirmTransfer(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        postTransferToServer();
                        Toast.makeText(getContext(), "SENDING TRANSFER REQUEST",
                                Toast.LENGTH_SHORT).show();
                        //Contact AccountDetails activity that it needs to refresh the tabs with new transfer info
                        mCallback.onTransferSelected();

                        //Display the TransferToUser/TransferBetweenUsers Options menu
                        setOptionsVisibility(1);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Are you sure you want to transfer " + amount.getText().toString() + " to  "+
                accountTo.getText().toString()+ "?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void setOptionsVisibility(int value){
        switch(value){
            case 0:
                transferLayout.setVisibility(View.GONE);
                transferManuallyLayout.setVisibility(View.VISIBLE);
                break;
            case 1:
                transferLayout.setVisibility(View.GONE);
                transferNFCLayout.setVisibility(View.VISIBLE);
                break;
            case 2:
                transferLayout.setVisibility(View.GONE);
                transferQRLayout.setVisibility(View.VISIBLE);
                break;
            case 3:
                transferLayout.setVisibility(View.VISIBLE);
                break;
        }
    }
    //Post request to make transfer.
    public void postTransferToServer(){
        StringRequest stringRequest;
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://54.87.197.206:8080/SparkServer/api/v1/transaction";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(),response.toString(),Toast.LENGTH_LONG).show();
                try {
                    JSONObject obj = new JSONObject(response);
                    responseHandler(obj);
                }catch(JSONException e){
                    e.printStackTrace();
                    Log.d(TAG,response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                return params;
            }
        };
        System.out.println("REQUESTING TRANSFER TRANSACTION");
        queue.add(stringRequest);
    }

     String answer;
    //Deals with the response received by the post request.
    private void responseHandler(JSONObject obj) {
        try {
            answer = obj.get("request").toString();
                if(answer.equals("failed")){
                    if(obj.get("reason").toString().equals("reason”:”you do not have enough funds to transfer"))
                    {//case not enough funds.
                        new AlertDialog.Builder(this.getContext())
                                .setTitle("Not Enough Fund")
                                .setMessage("Please, make sure you have enough funds to transfer.")
                                .setNeutralButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                    else {//Case of any other errors from server.
                        new AlertDialog.Builder(this.getContext())
                                .setTitle("Error")
                                .setMessage("Unfortunately, we have have ran in to an error.")
                                .setNeutralButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        startActivity(getParentFragment().getActivity().getIntent());
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                }
                else{
                   String balance = obj.get("balance").toString();
                    mCallback.updateBalance(balance);
                }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "Problem with response");
        }
    }


}
