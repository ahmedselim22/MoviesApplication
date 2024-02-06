package com.selim.moviesapplication.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.selim.moviesapplication.R
import com.selim.moviesapplication.adapters.*
import com.selim.moviesapplication.model.DTO.Movie
import com.selim.moviesapplication.databinding.FragmentMoviesBinding
import com.selim.moviesapplication.ui.activities.MovieDetailsActivity
import com.selim.moviesapplication.viewmodels.MainViewModel
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment: Fragment() {
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var topRatedMoviesAdapter: TopRatedMoviesAdapter
    private lateinit var trendingMoviesAdapter: TrendingMoviesAdapter
    private lateinit var sliderMoviesAdapter: SliderMoviesAdapter
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popularMoviesAdapter = PopularMoviesAdapter()
        topRatedMoviesAdapter= TopRatedMoviesAdapter()
        trendingMoviesAdapter= TrendingMoviesAdapter()
        sliderMoviesAdapter = SliderMoviesAdapter()
        viewModel =ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.shimmerMoviesPopular.startShimmer()
        binding.shimmerMoviesTopRated.startShimmer()
        binding.shimmerMoviesUpComing.startShimmer()


        binding.apply {
            viewModel.popularMovies.observe(requireActivity(), Observer {
                movies->
                Log.d("here popular movies", movies.toString())
                popularMoviesAdapter.setData(movies as ArrayList<Movie>)
                binding.shimmerMoviesPopular.stopShimmer()
                binding.shimmerMoviesPopular.visibility =View.GONE
                binding.recyclerPopularMovies.visibility=View.VISIBLE
            })
            recyclerPopularMovies.adapter =popularMoviesAdapter
            callWhenReachLastItem(recyclerPopularMovies)
        }
        binding.apply {
            viewModel.topRatedMovies.observe(requireActivity(), Observer {
                movies->
                Log.d("here topRated movies", movies.toString())
                topRatedMoviesAdapter.setData(movies as ArrayList<Movie>)
                binding.shimmerMoviesTopRated.stopShimmer()
                binding.shimmerMoviesTopRated.visibility =View.GONE
                binding.recyclerTopRatedMovies.visibility=View.VISIBLE
            })
            recyclerTopRatedMovies.adapter = topRatedMoviesAdapter
            callWhenReachLastItem(recyclerTopRatedMovies)
        }
        binding.apply {
            viewModel.trendingMovies.observe(requireActivity(), Observer {
                    movies->
                Log.d("here trending movies", movies.toString())
                movies?.let {
                    trendingMoviesAdapter.setData(movies as ArrayList<Movie>)
                }
                binding.shimmerMoviesUpComing.stopShimmer()
                binding.shimmerMoviesUpComing.visibility =View.GONE
                binding.recyclerTrendingMovies.visibility =View.VISIBLE
            })
            recyclerTrendingMovies.adapter = trendingMoviesAdapter
            callWhenReachLastItem(recyclerTrendingMovies)
        }
        binding.apply {
            viewModel.upComingMovies.observe(requireActivity(), Observer {
                movies->
                val list=ArrayList<Movie>()
                Log.d("here slider upComing movies", movies.toString())
                movies?.let {
                    list.clear()
                    list.add(it[0]!!)
                    list.add(it[1]!!)
                    list.add(it[2]!!)
                    list.add(it[3]!!)
                    list.add(it[4]!!)
                    sliderMoviesAdapter.setData(list)
                    shimmerMoviesSlider.stopShimmer()
                    binding.sliderMovies.visibility=View.VISIBLE
                    binding.shimmerMoviesSlider.visibility= View.GONE
                }
            })
            sliderMovies.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
            sliderMovies.setSliderAdapter(sliderMoviesAdapter)
            sliderMovies.scrollTimeInSec = 3
            sliderMovies.isAutoCycle = true
            sliderMovies.startAutoCycle()
        }
        popularMoviesAdapter.onMovieItemClicked={
            startMovieDetailsActivity(it)
        }
        topRatedMoviesAdapter.onMovieItemClicked={
            startMovieDetailsActivity(it)
        }
        trendingMoviesAdapter.onMovieItemClicked={
            startMovieDetailsActivity(it)
        }
        sliderMoviesAdapter.onMovieItemClicked={
            startMovieDetailsActivity(it)
        }
    }

    private fun startMovieDetailsActivity(movie: Movie){
        movie.let {
            val intent = Intent(activity, MovieDetailsActivity::class.java)
            intent.putExtra("movie",movie)
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
                        R.id.recycler_popularMovies->{
                            viewModel.getPopularMovies()
                        }
                        R.id.recycler_trendingMovies->{
                            viewModel.getTrendingMovies()
                        }
                        R.id.recycler_topRatedMovies->{
                            viewModel.getTopRatedMovies()
                        }
                    }
                }
            }
        })
    }
}