@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">{{ __('Dashboard') }}</div>

                    <div class="card-body">
                        @if (session('status'))
                            <div class="alert alert-success" role="alert">
                                {{ session('status') }}
                            </div>
                        @endif

                        {{--button to take users to user mgmt--}}
                        <a href="{{route('users.index')}}">
                            <button type="button" class="btn btn-outline-success">User Management</button>
                        </a>

                        <a href="{{route('posts.index')}}">
                            <button type="button" class="btn btn-outline-success">Post Management</button>
                        </a>


                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
