package tpm.yu.mol.share.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import tpm.yu.mol.share.bin.SaData
import tpm.yu.mol.share.bin.SaManager
import tpm.yu.mol.share.demo.databinding.ActivityMainBinding
import tpm.yu.mol.share.util.SaLogger

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var manager: SaManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        SaLogger.instance.v("verbose")
        SaLogger.instance.d("debug")
        SaLogger.instance.i("info")
        SaLogger.instance.w("warning")
        SaLogger.instance.e("error")

        manager = SaManager.instance
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.apps.observe(this) {
            manager.setApps(apps = it)
        }
        viewModel.dummyApps()

        binding.listVertical.setOnClickListener {
            val data = SaData.List(direction = SaData.Direction.VERTICAL)
            buildView(data = SaData(viewType = data))
        }
        binding.listHorizontal.setOnClickListener {
            val data = SaData.List(direction = SaData.Direction.HORIZONTAL)
            buildView(data = SaData(viewType = data))
        }
        binding.gridVertical.setOnClickListener {
            val data = SaData.Grid(direction = SaData.Direction.VERTICAL, count = 4)
            buildView(data = SaData(viewType = data))
        }
        binding.gridHorizontal.setOnClickListener {
            val data = SaData.Grid(direction = SaData.Direction.HORIZONTAL, count = 4)
            buildView(data = SaData(viewType = data))
        }
        binding.pagerVertical.setOnClickListener { }
        binding.pagerHorizontal.setOnClickListener { }
    }

    private fun buildView(data: SaData) {
        binding.content.removeAllViews()
        val view = manager.buildView(context = this, data = data)
        val lp = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        binding.content.addView(view, lp)
    }
}