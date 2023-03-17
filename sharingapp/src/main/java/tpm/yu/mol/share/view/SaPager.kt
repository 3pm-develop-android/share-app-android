package tpm.yu.mol.share.view

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.gson.Gson
import tpm.yu.mol.share.bin.AppInfo
import tpm.yu.mol.share.bin.SaData
import tpm.yu.mol.share.databinding.ItemPagerBinding
import tpm.yu.mol.share.databinding.ViewPagerBinding
import tpm.yu.mol.share.util.SaConstant
import tpm.yu.mol.share.util.SaLogger

class SaPager : BaseView {
    private val binding: ViewPagerBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    init {
        SaLogger.instance.i("init binding")
        val inflater = LayoutInflater.from(context)
        binding = ViewPagerBinding.inflate(inflater, this, true)
    }

    fun createContent(apps: List<AppInfo>, data: SaData): SaPager {
        when (data.viewType) {
            is SaData.Pager -> {
                binding.pager.orientation = when (data.viewType.direction) {
                    SaData.Direction.VERTICAL -> ViewPager2.ORIENTATION_VERTICAL
                    SaData.Direction.HORIZONTAL -> ViewPager2.ORIENTATION_HORIZONTAL
                }

                val adapter = PagerAdapter(apps, data.viewType.fragmentActivity)
                binding.pager.adapter = adapter
            }
            else -> {
                SaLogger.instance.w(SaConstant.Error.invalidType.format(data.viewType.toString()))
            }
        }
        return this
    }

    class PagerAdapter(private val data: List<AppInfo>, fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = data.size

        override fun createFragment(position: Int): Fragment {
            val item = data[position]
            return PagerFragment.newInstance(item)
        }
    }

    class PagerFragment : Fragment() {
        companion object {
            private const val ARG_OBJECT = "object"
            fun newInstance(app: AppInfo): PagerFragment {
                val args = Bundle()
                args.putString(ARG_OBJECT, Gson().toJson(app, AppInfo::class.java))
                val fragment = PagerFragment()
                fragment.arguments = args
                return fragment
            }
        }

        private lateinit var binding: ItemPagerBinding

        override fun onCreateView(
            inflater: LayoutInflater, ccontainer: ViewGroup?, savedInstanceState: Bundle?
        ): View {
            binding = ItemPagerBinding.inflate(inflater, null, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            arguments?.takeIf {
                it.containsKey(ARG_OBJECT)
            }?.apply {
                val gson = Gson()
                val item = gson.fromJson(getString(ARG_OBJECT), AppInfo::class.java)

                Glide.with(binding.layout.context).load(item.logo).into(binding.icon)
            }
        }
    }
}