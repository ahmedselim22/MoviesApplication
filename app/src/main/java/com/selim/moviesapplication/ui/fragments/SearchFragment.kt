package com.selim.moviesapplication.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.selim.moviesapplication.adapters.PopularMoviesAdapter
import com.selim.moviesapplication.adapters.PopularTvShowsAdapter
import com.selim.moviesapplication.adapters.SearchMoviesAdapter
import com.selim.moviesapplication.adapters.SearchTvShowsAdapter
import com.selim.moviesapplication.databinding.FragmentSearchBinding
import com.selim.moviesapplication.model.DTO.Movie
import com.selim.moviesapplication.model.DTO.TvShow
import com.selim.moviesapplication.ui.activities.MovieDetailsActivity
import com.selim.moviesapplication.ui.activities.TvShowDetailsActivity
import com.selim.moviesapplication.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var searchMoviesAdapter: SearchMoviesAdapter
    private lateinit var searchTvShowsAdapter: SearchTvShowsAdapter
    private lateinit var binding: FragmentSearchBinding
    private var isMovieFlag: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchMoviesAdapter = SearchMoviesAdapter()
        searchTvShowsAdapter = SearchTvShowsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        radioGroupSearch.setOnCheckedChangeListener { group, checkedId ->
                            if (checkedId == binding.radioButtonSearchMovies.id) {
                                viewModel.getSearchedMovies(it)
                                isMovieFlag = true
                            } else if (checkedId == binding.radioButtonSearchTvShows.id) {
                                viewModel.getSearchedTvShows(it)
                                isMovieFlag = false
                            }
                        }
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        if (isMovieFlag) {
                            viewModel.getSearchedMovies(it)
                        } else if (!isMovieFlag) {
                            viewModel.getSearchedTvShows(it)
                        }
                    }
                    return false
                }
            })
            binding.recyclerSearchMovies.layoutManager =
                GridLayoutManager(requireActivity(), 2, GridLayoutManager.VERTICAL, false)

            viewModel.searchedMovies.observe(requireActivity(), Observer { movies ->
                Log.d("here search movies", movies.toString())
                movies?.let {
                    if (isMovieFlag) {
                        searchMoviesAdapter.setData(it as ArrayList<Movie>)
                        searchTvShowsAdapter.setData(ArrayList())
                        binding.recyclerSearchMovies.adapter = searchMoviesAdapter
                    }
                }
            })
            viewModel.searchedTvShows.observe(requireActivity(), Observer { tvShows ->
                tvShows?.let {
                    Log.d("here search tvShows", tvShows.toString())
                    if (!isMovieFlag) {
                        searchMoviesAdapter.setData(ArrayList())
                        searchTvShowsAdapter.setData(it as ArrayList<TvShow>)
                        binding.recyclerSearchMovies.adapter = searchTvShowsAdapter
                    }
                }
            })
        }
        searchMoviesAdapter.onMovieItemClicked={
            startMovieDetailsActivity(it)
        }
        searchTvShowsAdapter.onTvShowItemClicked={
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
    private fun startMovieDetailsActivity(movie: Movie){
        movie.let {
            val intent = Intent(activity, MovieDetailsActivity::class.java)
            intent.putExtra("movie",movie)
            startActivity(intent)
        }
    }

}
