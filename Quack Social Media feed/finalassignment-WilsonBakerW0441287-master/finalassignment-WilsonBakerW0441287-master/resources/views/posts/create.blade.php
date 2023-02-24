@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">{{ __('Make a Post') }}</div>

                    <div class="card-body">
                        @if (session('status'))
                            <div class="alert alert-success" role="alert">
                                {{ session('status') }}
                            </div>
                        @endif
                        <form action="{{route('posts.store')}}" method="POST">
                            @csrf
                            <div class="form-group mb-3">
                                <label for="title">Title</label>
                                <input type="text" class="form-control" id="title" name="title"
                                       value="{{old('first_name')}}" placeholder="Today I accomplished...">

                                @error('title')
                                <div class="alert alert-danger">{{ $message }}</div>
                                @enderror
                            </div>
                            <div class="row mb-3">
                                <label for="content"
                                       class="">Content</label>

                                <div class="">
                                    <input id="content" type="text"
                                           class="form-control @error('content') is-invalid @enderror"
                                           name="content"
                                           value="" required autocomplete="email" placeholder="An amazing feat">

                                    @error('content')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label for="imageUrl"
                                       class="">Image Url (Optional)</label>

                                <div class="">
                                    <input id="imageUrl" type="url" class="form-control"
                                           name="imageUrl" placeholder="www....">
                                </div>
                            </div>

                            <div class="row mb-0">
                                <div class="">
                                    <button type="submit" class="btn btn-success">Submit</button>
                                    <a href="{{route('posts.index')}}">
                                        <button type="button" class="btn btn-outline-danger">cancel</button>
                                    </a>
                                </div>
                            </div>


                        </form>


                    </div>
                </div>

            </div>

        </div>

    </div>
@endsection
