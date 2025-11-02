package com.example.threadhandlerwizard

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class ProgressFragment : Fragment(R.layout.frag_progress) {
    private val vm: WorkViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bar = view.findViewById<ProgressBar>(R.id.progressBar)
        val text = view.findViewById<TextView>(R.id.txt)
        bar.isIndeterminate = true  // 初始顯示「準備中…」

        vm.status.observe(viewLifecycleOwner) { s ->
            text.text = s
            // 一旦狀態變 Working…，顯示可見進度條
            if (s.startsWith("Working")) bar.isIndeterminate = false
        }
        vm.progress.observe(viewLifecycleOwner) { p ->
            if (!bar.isIndeterminate) {
                bar.max = 100
                bar.progress = p
                text.text = "Working… %d%%".format(p)
            }
        }
    }
}
