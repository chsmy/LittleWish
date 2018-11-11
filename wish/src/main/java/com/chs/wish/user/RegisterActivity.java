package com.chs.wish.user;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatTextView;

import com.blankj.utilcode.util.StringUtils;
import com.chs.core.base.BaseActivity;
import com.chs.core.base.BaseEntity;
import com.chs.core.http.DialogCallback;
import com.chs.core.utils.StatusBarCompat;
import com.chs.wish.Api;
import com.chs.wish.R;
import com.chs.wish.R2;
import com.chs.wish.main.home.HomeActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：chs
 * 时间：2018-10-23 11:24
 * 描述：
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R2.id.tv_goto_login)
    AppCompatTextView tvGotoLogin;
    @BindView(R2.id.edit_sign_in_phone)
    TextInputEditText mTvName;
    @BindView(R2.id.tl_sign_in_phone)
    TextInputLayout mTLName;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mTvPassword;
    @BindView(R2.id.tl_sign_in_password)
    TextInputLayout mTLPassword;
    @BindView(R2.id.edit_sign_in_aff_password)
    TextInputEditText mTvConfirmPassword;
    @BindView(R2.id.tl_sign_in_aff_password)
    TextInputLayout mTLConfirmPassword;
    @Override
    protected Object setContentLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        tvGotoLogin.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
    }
    @OnClick(R2.id.tv_goto_login)
    void gotToLogin() {
        finish();
    }
    @OnClick(R2.id.rl_register)
    void register(){
        String name = mTvName.getText().toString();
        String password = mTvPassword.getText().toString();
        String confirmPassword = mTvConfirmPassword.getText().toString();

        if(validateAccount(name)&&validatePassword(password)&&validateSame(password,confirmPassword)){
            OkGo.<BaseEntity>post(Api.REGISTER)
                    .tag(this)
                    .params("username",name)
                    .params("password",password)
                    .params("name",name)
                    .execute(new DialogCallback<BaseEntity>(BaseEntity.class,this) {
                        @Override
                        public void onSuccess(Response<BaseEntity> response) {
                            String returncode = response.body().getReturncode();
                            if(returncode.equals("0")){
                                showToast("注册成功");
                                startActivity(HomeActivity.class);
                            }else {
                                showToast("注册失败"+response.message());
                            }
                        }
                    });
        }
    }

    /**
     * 验证用户名
     */
    private boolean validateAccount(String account){
        if(StringUtils.isEmpty(account)){
            showError(mTLName,"用户名不能为空");
            return false;
        }
        return true;
    }

    /**
     * 验证密码
     */
    private boolean validatePassword(String password) {
        if (StringUtils.isEmpty(password)) {
            showError(mTLPassword,"密码不能为空");
            return false;
        }
        if (password.length() < 6 || password.length() > 18) {
            showError(mTLPassword,"密码长度为6-18位");
            return false;
        }
        return true;
    }

    private boolean validateSame(String password,String confirmPassword){
        return password.equals(confirmPassword);
    }

    /**
     * 显示错误提示，并获取焦点
     */
    private void showError(TextInputLayout textInputLayout, String error){
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }
}
