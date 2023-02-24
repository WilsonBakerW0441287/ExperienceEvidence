@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">

            <div class="card">
                <div class="card-header">{{ __('Posts') }}</div>

                <div class="card-body">
                    @if (session('status'))
                        <div class="alert alert-success" role="alert">
                            {{ session('status') }}
                        </div>
                    @endif
                    @guest()
                            <a class="btn btn-success disabled "> Sign in Post Something! </a>
                    @else
                        {{-- we will need an if statment to know what roles the user has--}}
                        <a href="{{route('posts.create')}}">
                            <button type="button" class="btn btn-success">Post Something!</button>
                        </a>
                    @endguest
                    @foreach($posts as $post)
                        {{--Display Post information--}}
                        <div class="card mb-3"> {{--this is the card for the post--}}
                            <div class="card-header"> {{--this is the card header--}}
                                <h5 class="card-title">
                                    {{$post->title}}
                                </h5>
                                <p>Created By {{$users->find($post->created_by)->name}}</p>
                                <p>{{ $post->created_at->diffForHumans() }}</p>
                            </div>
                            <div class="card-body">
                                <p>
                                    {{$post->content}}
                                </p>
                            </div>
                            <img class="card-img mx-3 mb-4" style="width: 18rem;" src="{{$post->imageUrl}}" alt="{{$post->imageUrl}}">

                            <div class="card-footer">
                                {{-- in the card footer we will add the ability to edit, or delete buttons--}}
                                @guest()

                                @else
                                    <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                                    @if (Auth::user()->id == $post->created_by || auth()->user()->roles()->get()->contains(\App\Models\Role::where('name','User Admin')->first()))
                                        <a class="btn btn-warning" href="{{route('posts.edit', $post->id)}}">Edit</a>
                                    @endif
                                    @if (Auth::user()->id == $post->created_by || auth()->user()->roles()->get()->contains(\App\Models\Role::where('name','Moderator')->first() || \App\Models\Role::where('name','User Admin')->first()))
                                        <form action="{{route('posts.destroy', $post->id)}}" method="POST">
                                            @csrf
                                            @method('DELETE')
                                            <button type="submit" class="btn btn-danger">Delete</button>
                                        </form>
                                    @endif
                                @endguest
                                    </div>
                            </div>
                        </div>
                    @endforeach
                </div>
            </div>
        </div>
    </div>

@endsection
