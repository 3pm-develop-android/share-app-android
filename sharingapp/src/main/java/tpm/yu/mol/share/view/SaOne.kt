package tpm.yu.mol.share.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import tpm.yu.mol.share.bin.AppInfo
import tpm.yu.mol.share.bin.SaData
import tpm.yu.mol.share.databinding.ItemListBinding
import tpm.yu.mol.share.util.SaConstant
import tpm.yu.mol.share.util.SaLogger

class SaOne : BaseView {
    private val binding: ItemListBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    init {
        SaLogger.instance.i("init binding")
        val inflater = LayoutInflater.from(context)
        binding = ItemListBinding.inflate(inflater, this, true)
    }

    fun createContent(app: AppInfo, data: SaData): SaOne {
        when (data.viewType) {
            is SaData.One -> {
                Glide.with(binding.layout.context).load(app.logo).into(binding.icon)
            }
            else -> {
                SaLogger.instance.w(SaConstant.Error.invalidType.format(data.viewType.toString()))
            }
        }
        return this
    }
}