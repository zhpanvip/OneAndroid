package com.zhpan.oneandroid.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhpan.oneandroid.model.bean.ProjectClassify
import com.zhpan.oneandroid.ui.project.list.ProjectListFragment


/**
 * <pre>
 *   Created by zhpan on 2020/7/23.
 *   Description:
 * </pre>
 */
class ProjectFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    var projectTree: List<ProjectClassify> = ArrayList()

    override fun getItemCount(): Int {
        return projectTree.size
    }

    override fun createFragment(position: Int): Fragment {
        return ProjectListFragment.newInstance(projectTree[position].id.toString())
    }
}