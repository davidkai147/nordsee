package com.ptbc.kotlin_mvvm.ui.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
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
import com.ptbc.kotlin_mvvm.ui.feed.FeedActivity
import com.ptbc.kotlin_mvvm.ui.login.LoginActivity
import com.ptbc.kotlin_mvvm.ui.main.MainActivity
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private var onBoardItems: ArrayList<OnBoardItem> = ArrayList()
    private var dotsCount = 0
    lateinit var dots: Array<ImageView?>

    lateinit var onboardPager: ViewPager
    lateinit var pager_indicator: LinearLayout
    lateinit var btnPrev: TextView
    lateinit var btnNext: TextView
    lateinit var btnStart: Button

    lateinit var adapter: SplashAdapter
    lateinit var mSplashViewModel: SplashViewModel

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
        val intent = FeedActivity.newIntent(this@SplashActivity)
        startActivity(intent)
        finish()
    }

    private fun loadData() {
        val header = arrayOf(R.string.welcome, R.string.discover, R.string.planning)
        val desc = arrayOf(R.string.no_description, R.string.discover_description, R.string.planning_description)
        val image_background = intArrayOf(R.drawable.screen, R.drawable.screen_blur, R.drawable.screen_blur)

        val countItem = image_background.size-1
        for (i in 0..countItem) {
            val item : OnBoardItem = OnBoardItem(0, "","")
            item.image_background = image_background[i]
            item.title = resources.getString(header[i])
            item.description = resources.getString(desc[i])
            onBoardItems.add(item)
        }
    }

    private fun setUiPageViewController() {
        dotsCount = adapter.count
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
            pager_indicator.addView(dots[i], params)
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

        if (mSplashViewModel.checkIsFirstTime()){
            openLoginActivity()
        }

        mSplashViewModel.startSeeding()

        var currentViewPager : Int

        onboardPager = findViewById(R.id.viewpager)
        pager_indicator = findViewById(R.id.viewPagerCountDots)
        btnPrev = findViewById(R.id.tv_previous)
        btnNext = findViewById(R.id.tv_next)
        btnStart = findViewById(R.id.btn_start)

        loadData()

        adapter = SplashAdapter(this, onBoardItems)
        onboardPager.adapter = adapter
        onboardPager.setCurrentItem(0)

        onboardPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                currentViewPager = position
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

        btnPrev.setOnClickListener{
            onboardPager.setCurrentItem((onboardPager.currentItem-1),true)
            if (onboardPager.currentItem<1){
                currentViewPager=0
                btnPrev.visibility=View.INVISIBLE

            }
            else{
                btnNext.visibility=View.VISIBLE
                btnStart.visibility=View.INVISIBLE
            }
        }

        btnNext.setOnClickListener {
            onboardPager.setCurrentItem((onboardPager.currentItem+1),true)
            if (onboardPager.currentItem>1){
                currentViewPager=2
                btnNext.visibility=View.INVISIBLE
                btnStart.visibility=View.VISIBLE
            }
            else{
                btnPrev.visibility=View.VISIBLE
            }
        }

        btnStart.setOnClickListener {
            mSplashViewModel.setFirstTime(true)
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
        setUiPageViewController()
    }
}

