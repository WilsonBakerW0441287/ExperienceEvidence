@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">{{ __('Edit Post') }}</div>
                    <div class="card-body">
                        @if (session('status'))
                            <div class="alert alert-success" role="alert">
                                {{ session('status') }}
                            </div>
                        @endif
                        <form action="{{route('posts.update', $post->id)}}" method="POST">
                            @csrf
                            {{--his allows us to do a laravel specific function that will handle the data differently--}}
                            @method('PUT')
                            <div class="form-group mb-3">
                                <label for="title">Title of post</label>
                                <input type="text" class="form-control" id="title" name="title"
                                       value="{{$post->title ?? old('title')}}" placeholder="Enter New Title">

                                @error('title')
                                <div class="alert alert-danger">{{ $message }}</div>
                                @enderror
                            </div>
                            <div class="row mb-3">
                                <label for="content"
                                       class="content">Content</label>
                                <div class="">
                                    <input id="content" type="text"
                                           class="form-control"
                                           name="content"
                                             value="{{$post->content ?? old('content')}}" placeholder="Enter New Content">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label for="imageUrl"
                                       class="content">Image Url - Optional</label>
                                <div class="">
                                    <input id="imageUrl" type="text"
                                           class="form-control"
                                           name="imageUrl"
                                           value="{{$post->imageUrl ?? old('content')}}" placeholder="Enter image Url">
                                </div>
                            </div>

                            <button type="submit" class="btn btn-success">Submit</button>
                            <a href="{{route('posts.index')}}">
                                <button type="button" class="btn btn-outline-danger">cancel</button>
                            </a>
                        </form>


                    </div>
                </div>

            </div>

        </div>

    </div>
@endsection
