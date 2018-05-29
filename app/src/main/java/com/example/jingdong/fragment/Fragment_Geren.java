package com.example.jingdong.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jingdong.DengLu.LoginActivity;
import com.example.jingdong.R;
import com.example.jingdong.shezhi.SheZhiActivity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static android.app.Activity.RESULT_OK;

public class Fragment_Geren extends Fragment implements View.OnClickListener {
    //private View view;
    private ImageView mHeadIv;
    /**
     * 未登录,请登录
     */
    private TextView mLogin;
    private TextView mdengluzhuce;
    private LinearLayout mAddress;
    private LinearLayout mBackLogin;
    //private boolean isReady = false;
    private Bitmap head;// 头像Bitmap
    //protected boolean isVisible;
    private static String path = "/sdcard/myHead/";// sd路径
    /**
     * 从相册中选取
     */
    private TextView mTvSelectGallay;
    /**
     * 拍摄照片
     */
    private TextView mTvSelectCamera;
    /**
     * 请输入省份...
     */
    private EditText mUserCity;
    /**
     * 请输入详细地址
     */
    private EditText mUserAddress;
   // private Fragment_Geren fragmentGeren;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_geren, container, false);

        initView(view);
        return view;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //点击更换头像
            case R.id.head_iv:

                /*String touuid = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
                        .getString("uid", "");
                String touName = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
                        .getString("uName", "");
                if (touuid.isEmpty() && touName.isEmpty()) {
                    Toast.makeText(getActivity(), "尚未登录,请登录,不能上传头像", Toast.LENGTH_LONG).show();
                } else {
                    showTypeDialog();
                }*/
                showTypeDialog();
                break;

            //修改姓名
            case R.id.login:
                updateUserName();
               /* if (!mLogin.getText().equals("未登录,请登录!")) {
                    Toast.makeText(getActivity(), "来给自己换个高大上的名字吧!!!", Toast.LENGTH_SHORT).show();
                    updateUserName();
                } else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }*/
                break;
            case R.id.dengluzhuce:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

                break;

            case R.id.address:
                /*String dizhiuid = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
                        .getString("uid", "");
                String dizhiuName = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
                        .getString("uName", "");
                if (dizhiuid.isEmpty() && dizhiuName.isEmpty()) {
                    Toast.makeText(getActivity(), "尚未登录,不能添加地址", Toast.LENGTH_SHORT).show();
                } else {
                    inAddress();
                }*/
                inAddress();
                break;

            //设置
            case R.id.backLogin:

                /*String uid = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
                        .getString("uid", "");
                String uName = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
                        .getString("uName", "");
                if (uid.isEmpty() && uName.isEmpty()) {
                    Toast.makeText(getActivity(), "尚未登录,请登录", Toast.LENGTH_LONG).show();
                } else {
                    SharedPreferences userSettings = getActivity().getSharedPreferences("user", 0);
                    SharedPreferences.Editor edit = userSettings.edit();
                    edit.clear();
                    edit.commit();
                    Toast.makeText(getActivity(), "成功退出登录", Toast.LENGTH_LONG).show();
                    Intent tui = new Intent(getActivity(), MainActivity.class);
                    tui.putExtra("page", 4);
                    getActivity().startActivity(tui);
                }*/

                Intent shezhi = new Intent(getActivity(), SheZhiActivity.class);
                startActivity(shezhi);
                break;
        }
    }

    /**
     * 添加地址
     */

    private void inAddress() {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.dialog_address, null);
        new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userCity = mUserCity.getText().toString().trim();
                        String userAddress = mUserAddress.getText().toString().trim();
                        if (userCity.length()<5 || userAddress.length()<5){
                            Toast.makeText(getActivity(), "地址不合法!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("取消",null).create().show();
    }

    /**
     * 修改,上传头像
     */

    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialog = builder.create();
        View view = View.inflate(getActivity(), R.layout.dialog_select_photo, null);
        mTvSelectGallay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tuku = new Intent(Intent.ACTION_PICK, null);
                tuku.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(tuku, 1);
                dialog.dismiss();
            }
        });
        mTvSelectCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pai = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                pai.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile
                        (new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(pai, 2);
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    //剪裁图片
                    cropPhoto(data.getData());
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    //剪裁图片
                    cropPhoto(Uri.fromFile(temp));
                }
                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        setPicToView(head);
                        mHeadIv.setImageBitmap(head);
                    }
                }
                break;
        }

    }

    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        //宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //剪裁图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);

    }

    private void setPicToView(Bitmap bitmap) {
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();
        String fileName = path + "head.jpg";
        try {
            b = new FileOutputStream(fileName);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*private void init(){
        fragmentGeren = new Fragment_Geren();
        String uid = getActivity().getSharedPreferences("user",
                Context.MODE_PRIVATE).getString("uid", "");
        String uName = getActivity().getSharedPreferences("user",
                Context.MODE_PRIVATE).getString("uName", "");
        String uImg = getActivity().getSharedPreferences("user",
                Context.MODE_PRIVATE).getString("headimg", "");
        if (uid.length()<1){
            mLogin.setText("还没有登录,请登录");
        }else {
            mLogin.setText("欢迎:"+uName);
            if (uImg.length()<1){
                Toast.makeText(getActivity(), "没有头像,请上传头像", Toast.LENGTH_SHORT).show();
            }else {
                Glide.with(getActivity()).load(uImg).into(mHeadIv);
            }
        }

    }*/

    /**
     * 修改名字
     */
    private void updateUserName() {
        LayoutInflater factory = LayoutInflater.from(getActivity());//提示框
        final View view = factory.inflate(R.layout.dialog_user_name, null);//这里必须是final的
        final EditText userName = (EditText) view.findViewById(R.id.username);//获得输入框对象
        new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("确定",//提示框的两个按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //事件
                                String trim = userName.getText().toString().trim();
                                if (trim.length() < 1) {
                                    Toast.makeText(getActivity(), "名字不合法!!!", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                mLogin.setText(trim);
                            }
                        }).setNegativeButton("取消", null).create().show();
    }

    private void initView(View view) {
        mHeadIv = (ImageView) view.findViewById(R.id.head_iv);
        mLogin = (TextView) view.findViewById(R.id.login);
        mdengluzhuce= (TextView) view.findViewById(R.id.dengluzhuce);

        mAddress = (LinearLayout) view.findViewById(R.id.address);
        mBackLogin = (LinearLayout) view.findViewById(R.id.backLogin);
        mTvSelectGallay = (TextView) view.findViewById(R.id.tv_select_gallay);
        mTvSelectCamera = (TextView) view.findViewById(R.id.tv_select_camera);
        mUserCity = (EditText) view.findViewById(R.id.userCity);
        mUserAddress = (EditText) view.findViewById(R.id.userAddress);
        mHeadIv.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mdengluzhuce.setOnClickListener(this);

        mAddress.setOnClickListener(this);
        mBackLogin.setOnClickListener(this);

    }
}
