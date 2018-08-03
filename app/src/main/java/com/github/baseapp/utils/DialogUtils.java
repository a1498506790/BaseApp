package com.github.baseapp.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.github.baseapp.R;

/**
 * @data 2018-08-03
 * @desc
 */

public class DialogUtils {

    public static void showDialog(Context context, String message, View.OnClickListener onClickListener){
        showDialog(context, "温馨提示", message, false, onClickListener);
    }

    public static void showDialog(Context context, String title, String message, boolean isCancelable, final View.OnClickListener onClickListener){
        TextView tvTitle, tvMsg, tvCancel, tvAffirm;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog, null);
        tvTitle = view.findViewById(R.id.tv_title);
        tvMsg = view.findViewById(R.id.tv_msg);
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvAffirm = view.findViewById(R.id.tv_affirm);
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(isCancelable)
                .create();
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = dp2px(context, 300f);
        dialog.getWindow().setAttributes(layoutParams);
        if (!TextUtils.isEmpty(title)) tvTitle.setText(title);
        if (!TextUtils.isEmpty(message)) tvMsg.setText(message);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvAffirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                onClickListener.onClick(view);
            }
        });
    }

    public static void showEditDialog(Context context, String hint, final OnEdtDialogClickListener onEdtDialogClickListener){
        showEditDialog(context, "温馨提示", hint, onEdtDialogClickListener);
    }

    public static void showEditDialog(Context context, String title, String hint, final OnEdtDialogClickListener onEdtDialogClickListener){
        TextView tvTitle, tvCancel, tvAffirm;
        final EditText edt;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_edit_dialog, null);
        tvTitle = view.findViewById(R.id.tv_title);
        edt = view.findViewById(R.id.edt);
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvAffirm = view.findViewById(R.id.tv_affirm);
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = dp2px(context, 300f);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        if (!TextUtils.isEmpty(title)) tvTitle.setText(title);
        if (!TextUtils.isEmpty(hint)) edt.setHint(hint);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvAffirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (onEdtDialogClickListener != null){
                    onEdtDialogClickListener.onEditDialog(edt.getText().toString());
                }
            }
        });
    }

    public interface OnEdtDialogClickListener{
        void onEditDialog(String content);
    }

  public static void showItemDialog(Context context, String title, String[] items, final OnItemDialogClickListener onItemDialogClickListener){
        TextView tvTitle;
        ListView listView;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_dialog, null);
        tvTitle = view.findViewById(R.id.tv_title);
        listView = view.findViewById(R.id.listView);
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = dp2px(context, 300f);
        dialog.getWindow().setAttributes(layoutParams);
        if (!TextUtils.isEmpty(title)) tvTitle.setText(title);
        listView.setAdapter(new ArrayAdapter<>(context, R.layout.layout_item_dialog_item, items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialog.dismiss();
                if (onItemDialogClickListener != null){
                    onItemDialogClickListener.onItemDialog(i);
                }
            }
        });
    }

    interface OnItemDialogClickListener{
        void onItemDialog(int i);
    }

    private static int dp2px(Context context,float dpValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }

}
