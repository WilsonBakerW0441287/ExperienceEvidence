@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">{{ __('Add a NEW theme user') }}</div>

                    <div class="card-body">
                        @if (session('status'))
                            <div class="alert alert-success" role="alert">
                                {{ session('status') }}
                            </div>
                        @endif
                        <form action="{{route('themes.store')}}" method="POST">
                            @csrf
                            <div class="form-group mb-3">
                                <label for="name">Themes Name</label>
                                <input type="text" class="form-control" id="name" name="name"
                                       value="{{old('name')}}" placeholder="Enter Theme Name">

                                @error('name')
                                <div class="alert alert-danger">{{ $message }}</div>
                                @enderror
                            </div>

                            <div class="row mb-3">
                                <label for="cdn_url" class="col-sm-2">CDN URL</label>
                                <div class="">
                                    <input id="cdn_url" type="url"
                                           class="form-control"
                                           name="cdn_url">
                                </div>
                            </div>

                            <div class="row mb-0">
                                <div class="">
                                    <button type="submit" class="btn btn-success">Submit</button>
                                    <a href="{{route('themes.index')}}">
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
