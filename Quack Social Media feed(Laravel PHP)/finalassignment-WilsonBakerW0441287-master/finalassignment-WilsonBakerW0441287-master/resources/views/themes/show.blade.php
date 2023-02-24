@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">{{ __('Theme info') }}</div>

                    <div class="card-body">
                        @if (session('status'))
                            <div class="alert alert-success" role="alert">
                                {{ session('status') }}
                            </div>
                        @endif
                        {{--                        add a create new admin user button--}}
                        <a href="{{route('themes.index')}}">
                            <button type="button" class="btn btn-outline-danger">Go Back</button>
                        </a>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">URL</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>{{$theme->name}}</td>
                                <td style="width: 100%">{{$theme->cdn_url}}</td>
                            </tr>

                            </tbody>
                        </table>

                    </div>
                </div>

            </div>

        </div>

    </div>
@endsection
