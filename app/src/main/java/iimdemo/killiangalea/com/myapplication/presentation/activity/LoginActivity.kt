package iimdemo.killiangalea.com.myapplication.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import iimdemo.killiangalea.com.myapplication.R
import iimdemo.killiangalea.com.myapplication.data.model.User
import iimdemo.killiangalea.com.myapplication.data.network.ApiClient
import iimdemo.killiangalea.com.myapplication.domain.StringUtils
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.*


const val EXTRA_USER = "com.example.myapplication.EXTRA_USER"

class LoginActivity : AppCompatActivity() {
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            val email: String = inputLogin.text.toString()
            val password: String = inputPassword.text.toString()

            inputLogin.error = if (!StringUtils.isValidEmail(email)) getString(R.string.valid_email) else null
            inputPassword.error = if (password.isEmpty()) getString(R.string.required_field) else null

            if(email.isNotBlank() && password.isNotBlank()) {
                user = User(email, password)

                login(user, this)
            } else {
                Log.d("LoginActivity","Username ou mot de passe invalide")
            }
        }
    }

    private fun login(user: User, context: Context) {
        val call = ApiClient.apiService.login()

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() == 200) {
                    val intent = Intent(context, FeedActivity::class.java).apply {
                        putExtra(EXTRA_USER, user.email)
                    }
                    startActivity(intent)
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("LoginActivity","Error when login user")
            }
        })

    }
}