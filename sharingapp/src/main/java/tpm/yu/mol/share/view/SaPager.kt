package tpm.yu.mol.share.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tpm.yu.mol.share.bin.AppInfo
import tpm.yu.mol.share.bin.SaData
import tpm.yu.mol.share.databinding.ItemListBinding
import tpm.yu.mol.share.databinding.ViewListBinding
import tpm.yu.mol.share.util.SaConstant
import tpm.yu.mol.share.util.SaLogger

class SaPager : BaseView {
    private val binding: ViewListBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    init {
        SaLogger.instance.i("init binding")
        val inflater = LayoutInflater.from(context)
        binding = ViewListBinding.inflate(inflater, this, true)
    }

    fun createContent(apps: List<AppInfo>, data: SaData): SaPager {
        when (data.viewType) {
            is SaData.ListData -> {
                SaLogger.instance.i("create layout manager")
                val direction = when (data.viewType.direction) {
                    SaData.Direction.VERTICAL -> RecyclerView.VERTICAL
                    SaData.Direction.HORIZONTAL -> RecyclerView.HORIZONTAL
                }
                val layoutManager = when (data.viewType) {
                    is SaData.List -> LinearLayoutManager(context, direction, false)
                    is SaData.Grid -> GridLayoutManager(
                        context,
                        data.viewType.count,
                        direction,
                        false
                    )
                    else -> return this
                }
                binding.list.layoutManager = layoutManager

                SaLogger.instance.i("create adapter")
                val adapter = ListAdapter(data = apps)
                binding.list.adapter = adapter

                SaLogger.instance.i("end")

            }
            else -> {
                SaLogger.instance.w(SaConstant.Error.invalidType.format(data.viewType.toString()))
            }
        }
        return this
    }

    class ListAdapter(private val data: List<AppInfo>) :
        RecyclerView.Adapter<ListAdapter.ViewHolder>() {
        override fun getItemCount(): Int = data.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val context = parent.context
            val inflater = LayoutInflater.from(context)
            val binding: ItemListBinding = ItemListBinding.inflate(inflater, null, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item: AppInfo = data[position]
            holder.setData(item)
        }

        class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
            fun setData(item: AppInfo) {
                Glide.with(binding.layout.context).load(item.logo).into(binding.icon)
            }
        }
    }
}