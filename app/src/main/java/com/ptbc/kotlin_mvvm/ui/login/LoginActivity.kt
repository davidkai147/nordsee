package com.ptbc.kotlin_mvvm.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.ptbc.kotlin_mvvm.BR
import com.ptbc.kotlin_mvvm.ui.main.MainActivity
import com.ptbc.kotlin_mvvm.R
import com.ptbc.kotlin_mvvm.ViewModelProviderFactory
import com.ptbc.kotlin_mvvm.databinding.ActivityLoginBinding
import com.ptbc.kotlin_mvvm.ui.base.BaseActivity
import com.ptbc.kotlin_mvvm.ui.feed.FeedActivity
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    lateinit var mLoginViewModel: LoginViewModel
    lateinit var chkRememberMe: CheckBox
    private var mActivityLoginBinding: ActivityLoginBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_login

    override val viewModel: LoginViewModel
        get() {
            mLoginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
            return mLoginViewModel
        }

    override fun handleError(throwable: Throwable) {
        // handle error
    }

    override fun login() {
        val email = mActivityLoginBinding!!.etEmail.getText().toString()
        val password = mActivityLoginBinding!!.etPassword.getText().toString()
        if (mLoginViewModel.isEmailAndPasswordValid(email, password)) {
            hideKeyboard()
            if (email.equals("admin@gmail.com") && password.equals("123456")){
                if (chkRememberMe.isChecked){
                    mLoginViewModel.rememberLogin(email, password)
                }
                else{
                    mLoginViewModel.forgetLogin(email,password)
                }
            }
           else{
                Toast.makeText(this, getString(R.string.invalid_user), Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(this, getString(R.string.invalid_email_password), Toast.LENGTH_SHORT).show()
        }
    }

    override fun openMainActivity() {
        val intent = FeedActivity.newIntent(this@LoginActivity)
        startActivity(intent)
        finish()
    }

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLoginBinding = viewDataBinding
        mLoginViewModel.navigator = this

        chkRememberMe = findViewById(R.id.chkboxRememberMe)

    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}