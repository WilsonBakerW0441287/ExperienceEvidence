<?php

namespace App\Http\Controllers;

use App\Models\Post;
use App\Models\User;
use Illuminate\Http\Request;

class HomeController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('auth');
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Contracts\Support\Renderable
     */
    public function index()
    {
        //should route to the regular home page
//        return view('home');
        //for test purposes we will route to the users page
//        $users = User::all();
//        return view('users.index', compact('users'));
        //for test purposes we will route to the posts page
        $posts = Post::all();
        $users = User::all();
        return view('posts.index', compact(['posts', 'users']));
    }
}
