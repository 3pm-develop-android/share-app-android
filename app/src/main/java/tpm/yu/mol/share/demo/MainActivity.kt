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
        manager.requestApps("")
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.apps.observe(this) {
            manager.setApps(apps = it)
        }
        viewModel.dummyApps()

        val vertical = SaData.Direction.VERTICAL
        val horizontal = SaData.Direction.HORIZONTAL
        val gridSpanCount = 3
        binding.listVertical.setOnClickListener {
            val data = SaData.List(direction = vertical)
            buildView(data = SaData(viewType = data))
        }
        binding.listHorizontal.setOnClickListener {
            val data = SaData.List(direction = horizontal)
            buildView(data = SaData(viewType = data))
        }
        binding.gridVertical.setOnClickListener {
            val data = SaData.Grid(direction = vertical, count = gridSpanCount)
            buildView(data = SaData(viewType = data))
        }
        binding.gridHorizontal.setOnClickListener {
            val data = SaData.Grid(direction = horizontal, count = gridSpanCount)
            buildView(data = SaData(viewType = data))
        }
        binding.pagerVertical.setOnClickListener {
            val data = SaData.Pager(direction = vertical, fragmentActivity = this)
            buildView(data = SaData(viewType = data))
        }
        binding.pagerHorizontal.setOnClickListener {
            val data = SaData.Pager(direction = horizontal, fragmentActivity = this)
            buildView(data = SaData(viewType = data))
        }
        binding.one.setOnClickListener {
            val data = SaData.One()
            buildView(data = SaData(viewType = data))
        }
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