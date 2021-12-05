package com.example.kirozh.chucknorrisjokesapp.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kirozh.chucknorrisjokesapp.R
import com.example.kirozh.chucknorrisjokesapp.databinding.FragmentApiViewBinding


const val API_URL = "https://www.icndb.com/api/"

class DashboardFragment : Fragment() {

    private var _binding: FragmentApiViewBinding? = null

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    private val binding get() = _binding!!

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentApiViewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        progressBar = root.findViewById(R.id.progressBar)
        progressBar.max = 100

        webView = root.findViewById(R.id.web_view)
        webView.settings.javaScriptEnabled = true

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress == 100)
                    progressBar.visibility = View.GONE
                else {
                    progressBar.visibility = View.VISIBLE
                    progressBar.progress = newProgress
                }
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                (activity as AppCompatActivity).supportActionBar?.subtitle = title
            }
        }

        webView.webViewClient = WebViewClient()
        webView.loadUrl(API_URL)
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}