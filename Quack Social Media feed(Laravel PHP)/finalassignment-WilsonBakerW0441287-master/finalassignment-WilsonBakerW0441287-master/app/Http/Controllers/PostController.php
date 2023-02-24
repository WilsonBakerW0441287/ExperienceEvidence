<?php

namespace App\Http\Controllers;

use App\Models\Post;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class PostController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\View\Factory|\Illuminate\Contracts\View\View
     */
    public function index()
    {
        $users = User::all();
        $posts = Post::with('user')->orderBy('created_at', 'desc')->get();
        return view('posts.index', compact(['posts', 'users']));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\View\Factory|\Illuminate\Contracts\View\View
     */
    public function create()
    {
        //
        return view('posts.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function store(Request $request)
    {
        //validate the incoming data
        $request->validate([
            'title' => 'required',
            'content' => 'required'
        ]);
        $created_byID = Auth::id() ?? 1;//if the user is not logged in, then the user id will be 1

        //save the data
        Post::create([
            'title' => $request['title'],
            'content' => $request['content'],
            'created_by' => $created_byID
        ]);
        //redirect the user
        return redirect(route('posts.index'))->with('status','Post Created Successfully');
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Post  $post
     * @return \Illuminate\Http\Response
     */
    public function show(Post $post)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Post  $post
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\View\Factory|\Illuminate\Contracts\View\View
     */
    public function edit(Post $post)
    {
        //
        return view('posts.edit', compact('post'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Post  $post
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function update(Request $request, Post $post)
    {
        //validate the incoming data
        $request->validate([
            'title' => 'required',
            'content' => 'required'
        ]);

        $created_byID = Auth::id() ?? 1;//if the user is not logged in, then the user id will be 1

        //save the data
//dd($post);
        $post->title = $request['title'];
        $post->content = $request['content'];
        $post->imageUrl = $request['imageUrl'] ?? null;
        $post->created_by = $created_byID;
        $post->save();
        return redirect(route('posts.index'))->with('status','Post Updated Successfully');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Post  $post
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function destroy(Post $post)
    {
        //add usual delete code here
        $post->delete();
        $post->deleted_by = Auth::id() ?? 1;
        $post->save();
        return redirect(route('posts.index'))->with('status','Post Deleted Successfully');
    }
}
