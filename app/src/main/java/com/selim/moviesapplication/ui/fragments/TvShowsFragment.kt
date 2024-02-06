package com.selim.moviesapplication.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.selim.moviesapplication.R
import com.selim.moviesapplication.adapters.*
import com.selim.moviesapplication.databinding.FragmentTvShowsBinding
import com.selim.moviesapplication.model.DTO.TvShow
import com.selim.moviesapplication.ui.activities.TvShowDetailsActivity
import com.selim.moviesapplication.viewmodels.MainViewModel
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowsFragment : Fragment() {
    private lateinit var binding: FragmentTvShowsBinding
    private lateinit var popularTvShowsAdapter: PopularTvShowsAdapter
    private lateinit var trendingTvShowsAdapter: TrendingTvShowsAdapter
    private lateinit var topRatedTvShowsAdapter: TopRatedTvShowsAdapter
    private lateinit var sliderTvShowsAdapter: SliderTvShowsAdapter
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popularTvShowsAdapter = PopularTvShowsAdapter()
        trendingTvShowsAdapter = TrendingTvShowsAdapter()
        topRatedTvShowsAdapter= TopRatedTvShowsAdapter()
        sliderTvShowsAdapter = SliderTvShowsAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTvShowsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            shimmerTvShowsPopular.startShimmer()
            shimmerTvShowsTopRated.startShimmer()
            shimmerTvShowsTrending.startShimmer()
            shimmerTvShowsSlider.startShimmer()
            viewModel.popularTvShows.observe(requireActivity(), Observer {
                tvShows->
                tvShows.let {
                    popularTvShowsAdapter.setData(it as ArrayList<TvShow>)
                    shimmerTvShowsPopular.stopShimmer()
                    shimmerTvShowsPopular.visibility =View.GONE
                    recyclerPopularTvShows.visibility=View.VISIBLE
                }

            })
            recyclerPopularTvShows.adapter = popularTvShowsAdapter
            callWhenReachLastItem(recyclerPopularTvShows)

            viewModel.trendingTvShows.observe(requireActivity(), Observer {
                tvShows->
                tvShows.let {
                    trendingTvShowsAdapter.setData(it as ArrayList<TvShow>)
                    shimmerTvShowsTrending.stopShimmer()
                    shimmerTvShowsTrending.visibility =View.GONE
                    recyclerTrendingTvShows.visibility=View.VISIBLE
                }

            })
            recyclerTrendingTvShows.adapter = trendingTvShowsAdapter
            callWhenReachLastItem(recyclerTrendingTvShows)

            viewModel.topRatedTvShows.observe(requireActivity(), Observer {
                    tvShows->
                tvShows.let {
                    topRatedTvShowsAdapter.setData(it as ArrayList<TvShow>)
                    shimmerTvShowsTopRated.stopShimmer()
                    shimmerTvShowsTopRated.visibility =View.GONE
                    recyclerTopRatedTvShows.visibility=View.VISIBLE
                }

            })
            recyclerTopRatedTvShows.adapter = topRatedTvShowsAdapter
            callWhenReachLastItem(recyclerTopRatedTvShows)

            viewModel.onTheAirTvShows.observe(requireActivity(), Observer {
                tvShows->
                val list = ArrayList<TvShow>()
                tvShows?.let {
                    list.clear()
                    list.add(it[0]!!)
                    list.add(it[1]!!)
                    list.add(it[2]!!)
                    list.add(it[3]!!)
                    list.add(it[4]!!)
                    sliderTvShowsAdapter.setData(list)
                    shimmerTvShowsSlider.stopShimmer()
                    shimmerTvShowsSlider.visibility =View.GONE
                    sliderTvShows.visibility =View.VISIBLE
                }
                sliderTvShows.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
                sliderTvShows.setSliderAdapter(sliderTvShowsAdapter)
                sliderTvShows.scrollTimeInSec = 3
                sliderTvShows.isAutoCycle = true
                sliderTvShows.startAutoCycle()
            })
        }
        popularTvShowsAdapter.onTvShowItemClicked={
            startTvShowDetailsActivity(it)
        }
        topRatedTvShowsAdapter.onTvShowItemClicked={
            startTvShowDetailsActivity(it)
        }
        trendingTvShowsAdapter.onTvShowItemClicked={
            startTvShowDetailsActivity(it)
        }
        sliderTvShowsAdapter.onTvShowItemClicked={
            startTvShowDetailsActivity(it)
        }
    }

    private fun startTvShowDetailsActivity(movie: TvShow){
        movie.let {
            val intent = Intent(activity, TvShowDetailsActivity::class.java)
            intent.putExtra("tvShow",movie)
            startActivity(intent)
        }
    }

    private fun callWhenReachLastItem(recyclerView: RecyclerView){
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                if (lastVisibleItemPosition == totalItemCount - 1) {
                    when(recyclerView.id){
                        R.id.recycler_popularTvShows->{
                            viewModel.getPopularTvShows()
                        }
                        R.id.recycler_trendingTvShows->{
                            viewModel.getTrendingTvShows()
                        }
                        R.id.recycler_topRatedTvShows->{
                            viewModel.getTopRatedTvShows()
                        }
                    }

                }
            }
        })
    }

}