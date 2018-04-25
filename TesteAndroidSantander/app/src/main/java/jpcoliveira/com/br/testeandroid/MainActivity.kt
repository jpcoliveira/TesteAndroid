package jpcoliveira.com.br.testeandroid

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.github.salomonbrys.kodein.KodeinAware
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by lazy { (applicationContext as MyApplication).kodein }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val adapter = TabAdapter(supportFragmentManager, this, kodein)

        viewpager.adapter = adapter

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                toolbar_title.text = adapter.getPageTitle(position)
            }
        })
        toolbar_title.text = adapter.getPageTitle(0)
        tabLayout.setupWithViewPager(viewpager)
    }
}
