package tpm.yu.mol.share.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import tpm.yu.mol.share.bin.SaManager
import tpm.yu.mol.share.bin.SaView
import tpm.yu.mol.share.demo.databinding.ActivityMainBinding
import tpm.yu.mol.share.util.SaLogger

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        SaLogger.instance.v("verbose")
        SaLogger.instance.d("debug")
        SaLogger.instance.i("info")
        SaLogger.instance.w("warning")
        SaLogger.instance.e("error")

        val manager: SaManager = SaManager.instance
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.apps.observe(this) {
            manager.setApps(apps = it)

            val saView = SaView(viewType = SaView.List(direction = SaView.Direction.HORIZONTAL))
//            val saView = SaView(viewType = SaView.Grid(direction = SaView.Direction.HORIZONTAL, count = 4))
            val view = manager.buildViewAllApp(context = this, data = saView)
            val lp = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            binding.root.addView(view, lp)
        }
        viewModel.dummyApps()
    }
}