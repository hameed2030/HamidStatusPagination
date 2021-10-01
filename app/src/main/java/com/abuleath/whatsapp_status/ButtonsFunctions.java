package com.abuleath.whatsapp_status;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import static android.content.Context.CLIPBOARD_SERVICE;

public class ButtonsFunctions {

    ClipboardManager clipboardManager;
    ClipData clipData;
    public void ShareBtn(View view , String status)
    {
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        String shareSub = "حالات واتساب كتابية";
        myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        myIntent.putExtra(Intent.EXTRA_TEXT, status);
        view.getContext().startActivity(Intent.createChooser(myIntent, "مشاركة عبر"));
    }

    public void CopyBtn(View view , String status)
    {
        clipboardManager = (ClipboardManager) view.getContext().getSystemService(CLIPBOARD_SERVICE);
        clipData = ClipData.newPlainText("status" , status);
        clipboardManager.setPrimaryClip(clipData);

        Toast.makeText(view.getContext().getApplicationContext() , "تم نسخ الحالة" , Toast.LENGTH_SHORT).show();
    }

    public void wh_share_btn(View view , String status)
    {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, status);
        try{
            view.getContext().startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(view.getContext().getApplicationContext() , "الواتساب ليس مثبت لديك." , Toast.LENGTH_SHORT).show();
        }

    }
}
