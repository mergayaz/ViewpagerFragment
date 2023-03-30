package kz.kuz.viewpagerfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import java.util.*

class ViewPagerFragment : Fragment() {
    private lateinit var mViewPager: ViewPager2

    private inner class Item {
        var mTitle: String? = null
        var mPart1: String? = null
        var mPart2: String? = null
    }

    private val mItems: MutableList<Item> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_view_pager, container, false)
        mViewPager = view.findViewById(R.id.view_pager)
        for (i in 0..19) {
            val item: Item = Item()
            item.mTitle = "Title #" + (i + 1)
            item.mPart1 = "Part1 #" + (i + 1)
            item.mPart2 = "Part2 #" + (i + 1)
            mItems.add(item)
        }
        val fragmentManager = activity!!.supportFragmentManager
        val lifecycle = lifecycle
        mViewPager.adapter = object : FragmentStateAdapter(fragmentManager, lifecycle) {
            override fun getItemCount(): Int {
                return mItems.size
            }

            override fun createFragment(position: Int): Fragment {
                val item = mItems[position]
                return PagerFragment.newInstance(item.mTitle, item.mPart1, item.mPart2)
            }
        }
        return view
    }
}