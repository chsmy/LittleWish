package com.chs.wish.main.mine

import android.os.Bundle
import com.chs.core.base.BaseActivity
import com.chs.wish.R
import kotlinx.android.synthetic.main.include_top_bar.*

/**
 * 作者：83734
 * 时间：2019/3/25
 * 描述：
 */
class MessageActivity : BaseActivity() {

    override fun setContentLayout(): Any {
        return R.layout.activity_my_message
    }

    override fun init(savedInstanceState: Bundle?) {
        tv_title.text = getString(R.string.main_mine_text)
        tv_right.text = getString(R.string.mine_finish)
    }
}