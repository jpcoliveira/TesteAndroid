package jpcoliveira.com.br.testeandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.salomonbrys.kodein.KodeinAware
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by lazy { (applicationContext as MyApplication).kodein }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = TabAdapter(supportFragmentManager, this, kodein)
        viewpager.adapter = adapter
        tabLayout.setupWithViewPager(viewpager)
    }
}
