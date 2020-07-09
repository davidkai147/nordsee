package com.ptbc.kotlin_mvvm.ui.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.ptbc.kotlin_mvvm.BR
import com.ptbc.kotlin_mvvm.R
import com.ptbc.kotlin_mvvm.ViewModelProviderFactory
import com.ptbc.kotlin_mvvm.databinding.ActivitySplashBinding
import com.ptbc.kotlin_mvvm.ui.base.BaseActivity
import com.ptbc.kotlin_mvvm.ui.login.LoginActivity
import com.ptbc.kotlin_mvvm.ui.main.MainActivity
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var mSplashViewModel: SplashViewModel

    private var dotsCount = 0
    lateinit var dots: Array<ImageView?>

    lateinit var onboard_pager: ViewPager
    lateinit var pager_indicator: LinearLayout
    lateinit var btn_prev: TextView
    lateinit var btn_next: TextView
    lateinit var btn_start: Button

    lateinit var sharedPreferences: SharedPreferences
    var firstTime: Boolean = false

    lateinit var adapter: SplashAdapter

    var onBoardItems: ArrayList<OnBoardItem> = ArrayList()

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_splash

    override val viewModel: SplashViewModel
        get() {
            mSplashViewModel = ViewModelProviders.of(this, factory).get(SplashViewModel::class.java)
            return mSplashViewModel
        }

    override fun openLoginActivity() {
        val intent = LoginActivity.newIntent(this@SplashActivity)
        startActivity(intent)
        finish()
    }

    override fun openMainActivity() {
        val intent = MainActivity.newIntent(this@SplashActivity)
        startActivity(intent)
        finish()
    }

    fun loadData() {
        var header = arrayOf(R.string.tilte1, R.string.tilte2, R.string.tilte3)
        var desc = arrayOf(R.string.desc1, R.string.desc2, R.string.desc3)
        var image_background = intArrayOf(R.drawable.screen, R.drawable.screen_blur, R.drawable.screen_blur)
        for (i in 0..image_background.size-1) {
            val item : OnBoardItem = OnBoardItem(0, "","")
            item.image_background = image_background[i]
            item.title = resources.getString(header[i])
            item.description = resources.getString(desc[i])
            onBoardItems.add(item)
        }
    }

    fun setUiPageViewController() {
        dotsCount = adapter!!.getCount()
        dots = arrayOfNulls(dotsCount)
        for (i in 0 until dotsCount) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    this@SplashActivity,
                    R.drawable.non_selected_item_dot
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(6, 0, 6, 0)
            pager_indicator!!.addView(dots[i], params)
        }
        dots[0]!!.setImageDrawable(
            ContextCompat.getDrawable(
                this@SplashActivity,
                R.drawable.selected_item_dot
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSplashViewModel.navigator = this

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        firstTime = sharedPreferences.getBoolean("firstTime",false)

        if (firstTime){
            openLoginActivity()
        }

        mSplashViewModel.startSeeding()

        var currentViewPager : Int

        onboard_pager = findViewById(R.id.viewpager)
        pager_indicator = findViewById(R.id.viewPagerCountDots)
        btn_prev = findViewById(R.id.tv_previous)
        btn_next = findViewById(R.id.tv_next)
        btn_start = findViewById(R.id.btn_start)

        loadData()

        adapter = SplashAdapter(this, onBoardItems)
        onboard_pager.adapter = adapter
        onboard_pager.setCurrentItem(0)


        onboard_pager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                currentViewPager = position
                // Change the current position intimation
                for (i in 0 until dotsCount) {
                    dots.get(i)!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            this@SplashActivity,
                            R.drawable.non_selected_item_dot
                        )
                    )
                }
                dots.get(position)!!.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@SplashActivity,
                        R.drawable.selected_item_dot
                    )
                )
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        btn_prev.setOnClickListener{
            onboard_pager.setCurrentItem((onboard_pager.currentItem-1),true)
            if (onboard_pager.currentItem<1){
                currentViewPager=0
                btn_prev.visibility=View.INVISIBLE

            }
            else{
                btn_next.visibility=View.VISIBLE
                btn_start.visibility=View.INVISIBLE
            }
        }

        btn_next.setOnClickListener {
            onboard_pager.setCurrentItem((onboard_pager.currentItem+1),true)
            if (onboard_pager.currentItem>1){
                currentViewPager=2
                btn_next.visibility=View.INVISIBLE
                btn_start.visibility=View.VISIBLE
            }
            else{
                btn_prev.visibility=View.VISIBLE
            }
        }

        btn_start.setOnClickListener {
            firstTime = true
            var editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean("firstTime",firstTime)
            editor.commit()
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
        setUiPageViewController()
    }
}

