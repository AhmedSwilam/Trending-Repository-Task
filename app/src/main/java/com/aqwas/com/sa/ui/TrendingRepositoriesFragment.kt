package com.aqwas.com.sa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.aqwas.com.sa.adapter.TrendingReposAdapter
import com.aqwas.com.sa.connectionstatus.ConnectionStatus
import com.aqwas.com.sa.databinding.FragmentTrendingRepositoriesBinding
import com.aqwas.com.sa.viewmodel.TrendingRepositoriesViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendingRepositoriesFragment : Fragment() {

    private lateinit var shimmer: ShimmerFrameLayout
    private lateinit var txtRetry: TextView
    private lateinit var recRepos: RecyclerView
    private lateinit var connectionStatus: ConnectionStatus
    private lateinit var connectionLayout: LinearLayout
    private lateinit var swiper: SwipeRefreshLayout
    private lateinit var binding: FragmentTrendingRepositoriesBinding
    private val trendingRepositoriesViewModel: TrendingRepositoriesViewModel by viewModels()
    private lateinit var trendingReposAdapter: TrendingReposAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrendingRepositoriesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectionStatus = ConnectionStatus(requireActivity().application)
        checkNetworkConnection()
        connectionLayout = binding.linNoInternet
        shimmer = binding.shimmer
        shimmer.startShimmer()
        retryFetchData()
        initRepositoriesRecycler()
        initViewModel()

    }

    private fun initRepositoriesRecycler() {

        recRepos = binding.recRepos
        recRepos.layoutManager = LinearLayoutManager(activity)
        val decorator = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recRepos.addItemDecoration(decorator)
        trendingReposAdapter = TrendingReposAdapter()
        recRepos.adapter = trendingReposAdapter
        swiper = binding.swiper
        swiper.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            trendingReposAdapter.notifyDataSetChanged()
            swiper.isRefreshing = false
        })

    }

    private fun initViewModel() {
        trendingRepositoriesViewModel.responseReposLiveData.observe(viewLifecycleOwner, {
            if (it != null) {
                shimmer.stopShimmer()
                shimmer.visibility = View.GONE
                recRepos.visibility = View.VISIBLE
                trendingReposAdapter.setUpdatedRepos(it)
            } else {
                Toast.makeText(activity, "Error Loading Data", Toast.LENGTH_SHORT).show()
            }
        })
        trendingRepositoriesViewModel.getAllTrendingRepos()
    }

    private fun checkNetworkConnection() {

        connectionStatus.observe(viewLifecycleOwner, { isConnected ->

            if (isConnected) {
                swiper.visibility = View.VISIBLE
                connectionLayout.visibility = View.GONE
                recRepos.visibility = View.VISIBLE
            } else {
                swiper.visibility = View.GONE
                connectionLayout.visibility = View.VISIBLE
                recRepos.visibility = View.GONE
            }

        })
    }

    private fun retryFetchData() {
        txtRetry = binding.txtRetry
        txtRetry.setOnClickListener {
            connectionStatus.observe(viewLifecycleOwner, { isConnected ->

                if (isConnected) {
                    shimmer.stopShimmer()
                    shimmer.visibility = View.GONE
                    connectionLayout.visibility = View.GONE
                    recRepos.visibility = View.VISIBLE

                } else if (!isConnected) {

                    shimmer.visibility = View.VISIBLE
                    shimmer.startShimmer()
                    connectionLayout.visibility = View.GONE


                }

            })

        }
    }
}